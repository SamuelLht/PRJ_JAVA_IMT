package jvmd.app.Game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import jvmd.app.Game.GameScreen;

public class Player extends AnimatedSprite {
	
	public boolean Left = false;
	public boolean Right = false;
	public boolean Space = false;

	static float DAMPING = 0.87f;
	
	public final Vector2 position = new Vector2();
	final Vector2 velocity = new Vector2();
	State state = State.Walking;
	float stateTime = 0;
	boolean facesRight = true;
	boolean grounded = false;
	
	public int ConstantVitesse = 500;
	public int HauteurSaut = 2000;
	
	Texture marioTexture;
	TextureRegion[][] regions;
	
	public boolean KeyRightDown, KeyLeftDown, KeyJumpDown;
	
	public Player(GameScreen p_screen, float x, float y) {
		super(p_screen, 20, 50, x, y);
		position.x = x;
		position.y = y;
		setTexture(new Texture(Gdx.files.internal("mario_simple.png")));
		this.WIDTH = getTexture().getWidth() / 16f;
		this.HEIGHT = getTexture().getHeight() / 16f;
	}
	
	public void Jump() {
		velocity.y += JUMP_VELOCITY;
		state = State.Jumping;
	}

	public void update(float delta) {
		if (delta == 0) return;

		if (delta > 0.05f)
			delta = 0.05f;

		stateTime += delta;

		// check input and apply to velocity & state
		if (Space && grounded) {
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
		velocity.add(0, -5f); //TODO: -2.5f as GRAVITY Constant

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
		// if the mario is moving right, check the tiles to the right of it's
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
		game.getTiles(startX, startY, endX, endY);
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
		game.getTiles(startX, startY, endX, endY);
		marioRect.y += velocity.y;
		Rectangle tileTested = game.overlapsTiles(marioRect);
		if(tileTested != null) {
			if (velocity.y > 0) {
				position.y = tileTested.y - HEIGHT;
				// we hit a block jumping upwards, let's destroy it!
				TiledMapTileLayer layer = (TiledMapTileLayer) game.map.getLayers().get("walls");
				layer.setCell((int)tileTested.x, (int)tileTested.y, null);
			} else {
				position.y = tileTested.y + tileTested.height;
				// if we hit the ground, mark us as grounded so we can jump
				grounded = true;
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
	}

}
