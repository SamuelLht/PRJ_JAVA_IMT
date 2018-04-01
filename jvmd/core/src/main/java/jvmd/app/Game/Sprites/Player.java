package jvmd.app.Game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

import jvmd.app.Constants;
import jvmd.app.Game.GameScreen;

public class Player extends AnimatedSprite {
	
	private boolean Left;
	private boolean Right;
	private boolean Down;
	private int Space;
	private boolean alive;

	public static final float DAMPING = 0.87f;
	
	private final Vector2 position;
	private final Vector2 velocity;
	private State state;
	private float stateTime;
	private boolean facesRight;
	private boolean grounded;
	
	private Texture marioTexture;
	private TextureRegion[][] regions;
	
	public Player(GameScreen p_screen, float x, float y) {
		super(p_screen, 20, 50, x, y);
		this.position = new Vector2();
		this.velocity = new Vector2();
		this.state = State.Walking;
		this.stateTime = 0;
		this.facesRight = true;
		this.grounded = false;
		this.position.x = x;
		this.position.y = y;
		setTexture(new Texture(Gdx.files.internal("mario_simple.png")));
		this.WIDTH = getTexture().getWidth() / 16f;
		this.HEIGHT = getTexture().getHeight() / 16f;
		this.alive = true;
		this.Left = false;
		this.Right = false;
		this.Space = 0;
	}
	
	public void Jump() {
		velocity.y += JUMP_VELOCITY;
		state = State.Jumping;
	}

	public void update(float delta) {
		if (delta == 0) return;

		if (delta > 0.001f)
			delta = 0.001f;

		stateTime += delta;

		// check input and apply to velocity & state
		if (Space == 1 && grounded) {
			velocity.y += JUMP_VELOCITY;
			state = State.Jumping;
			grounded = false;
		}

		if (Left) {
			velocity.x = -MAX_VELOCITY;
			if (grounded) state = State.Walking;
			facesRight = false;
		}

		if (Right) {
			velocity.x = MAX_VELOCITY;
			if (grounded) state = State.Walking;
			facesRight = true;
		}

		// apply gravity if we are falling
		velocity.add(0, Constants.GRAVITY);

		// clamp the velocity to the maximum, x-axis only
		velocity.x = MathUtils.clamp(velocity.x,
				-MAX_VELOCITY, MAX_VELOCITY);

		// If the velocity is < 1, set it to 0 and set state to Standing
		if (Math.abs(velocity.x) < 1) {
			velocity.x = 0;
			if (grounded) state = State.Standing;
		}

		// multiply by delta time so we know how far we go
		// in this frame
		velocity.scl(delta);

		// perform collision detection & response, on each axis, separately
		// if mario is moving right, check the tiles to the right of it's
		// right bounding box edge, otherwise check the ones to the left
		Rectangle marioRect = game.pool.obtain();
		marioRect.set(position.x, position.y, WIDTH, HEIGHT);
		int startX, startY, endX, endY;
		if (velocity.x > 0) {
			startX = endX = (int)(position.x + WIDTH + velocity.x);
		} else {
			startX = endX = (int)(position.x + velocity.x);
		}
		startY = (int)(position.y);
		endY = (int)(position.y + HEIGHT);
		game.getTiles(startX, startY, endX, endY, "walls");
		marioRect.x += velocity.x;
		if(game.overlapsTiles(marioRect) != null)
				velocity.x = 0;
		marioRect.x = position.x;

		// if the mario is moving upwards, check the tiles to the top of its
		// top bounding box edge, otherwise check the ones to the bottom
		if (velocity.y > 0) {
			startY = endY = (int)(position.y + HEIGHT + velocity.y);
		} else {
			startY = endY = (int)(position.y + velocity.y);
		}
		startX = (int)(position.x);
		endX = (int)(position.x + WIDTH);
		game.getTiles(startX, startY, endX, endY, "walls");
		marioRect.y += velocity.y;
		Rectangle tileTested = game.overlapsTiles(marioRect);
		if(tileTested != null) {
			if (velocity.y > 0) {
				position.y = tileTested.y - HEIGHT;
			} else {
				position.y = tileTested.y + tileTested.height;
				// if we hit the ground, mark us as grounded so we can jump
				grounded = true;
			}
			velocity.y = 0;
		}
		game.getTiles(startX, startY, endX, endY, "breakable");
		tileTested = game.overlapsTiles(marioRect);
		if(tileTested != null) {
			if (velocity.y > 0) {
				position.y = tileTested.y - HEIGHT;
				// we hit a block jumping upwards, let's destroy it!
				TiledMapTileLayer layer = (TiledMapTileLayer) game.map.getLayers().get("breakable");
				layer.setCell((int)tileTested.x, (int)tileTested.y, null);
			} else {
				position.y = tileTested.y + tileTested.height;
				grounded = true;
				if(Down) {
					TiledMapTileLayer layer = (TiledMapTileLayer) game.map.getLayers().get("breakable");
					layer.setCell((int)tileTested.x, (int)tileTested.y, null);
				}
			}
			velocity.y = 0;
		}
		game.pool.free(marioRect);

		// unscale the velocity by the inverse delta time and set
		// the latest position
		position.add(velocity);
		velocity.scl(1 / delta);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		velocity.x *= DAMPING;
		
		if(position.y + HEIGHT< 0) {
			this.alive = false;
		}
	}

	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return Left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(boolean left) {
		Left = left;
	}

	/**
	 * @return the right
	 */
	public boolean isRight() {
		return Right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(boolean right) {
		Right = right;
	}

	/**
	 * @param left the left to set
	 */
	public void setDown(boolean down) {
		Down = down;
	}

	/**
	 * @return the right
	 */
	public boolean isDown() {
		return Down;
	}

	/**
	 * @return the space
	 */
	public int getSpace() {
		return Space;
	}

	/**
	 * @param space the space to set
	 */
	public void setSpace(int space) {
		Space = space;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the stateTime
	 */
	public float getStateTime() {
		return stateTime;
	}

	/**
	 * @param stateTime the stateTime to set
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	/**
	 * @return the facesRight
	 */
	public boolean isFacesRight() {
		return facesRight;
	}

	/**
	 * @param facesRight the facesRight to set
	 */
	public void setFacesRight(boolean facesRight) {
		this.facesRight = facesRight;
	}

	/**
	 * @return the grounded
	 */
	public boolean isGrounded() {
		return grounded;
	}

	/**
	 * @param grounded the grounded to set
	 */
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

	/**
	 * @return the marioTexture
	 */
	public Texture getMarioTexture() {
		return marioTexture;
	}

	/**
	 * @param marioTexture the marioTexture to set
	 */
	public void setMarioTexture(Texture marioTexture) {
		this.marioTexture = marioTexture;
	}

	/**
	 * @return the regions
	 */
	public TextureRegion[][] getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(TextureRegion[][] regions) {
		this.regions = regions;
	}

	/**
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * @return the velocity
	 */
	public Vector2 getVelocity() {
		return velocity;
	}
	
	
}
