package jvmd.app.Game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import jvmd.app.Game.GameScreen;

public abstract class AnimatedSprite extends Sprite {
	
	enum State {
		Standing, Walking, Jumping
	}

	protected Animation<TextureRegion> stand;
	protected Animation<TextureRegion> walk;
	protected Animation<TextureRegion> jump;

	public static final float MAX_VELOCITY = 10f;
	public static final float JUMP_VELOCITY = 60f;
	final Vector2 position = new Vector2();
	final Vector2 velocity = new Vector2();
	State state = State.Walking;

	public AnimatedSprite(GameScreen screen, float width, float height, float x, float y) {
		super(screen, width, height, x, y);
	}
	
	public void Up() {
		setY(getY()+1);
	}
	
	public void Down() {
		setY(getY()-1);
	}
	
	public void Left() {
		setX(getX()-1);
	}
	
	public void Right() {
		setX(getX()+1);
	}
	
}
