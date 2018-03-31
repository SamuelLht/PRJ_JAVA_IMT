package jvmd.app.Game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;

public class GameInputProcessor implements InputProcessor {
	
	private GameScreen screen;
	public boolean KeyRight = false;
	public boolean KeyLeft = false;
	public boolean KeyJump = false;
	
	public GameInputProcessor(GameScreen screen, World world) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
	}

	@Override
    public boolean keyDown(int keycode)
    {
	    switch (keycode)
	    {
			case Keys.LEFT:
				screen.Mario.setLeft(true);
				break;
			case Keys.RIGHT:
				screen.Mario.setRight(true);
				break;
			case Keys.SPACE:
				screen.Mario.setSpace(true);
				break;
			case Keys.ESCAPE:
				screen.pause();
	    }
	    return true;
    }
    @Override
    public boolean keyUp(int keycode)
    {
	    switch (keycode)
	    {
			case Keys.LEFT:
				screen.Mario.setLeft(false);
				break;
			case Keys.RIGHT:
				screen.Mario.setRight(false);
				break;
			case Keys.SPACE:
				screen.Mario.setSpace(false);
				break;
	    }
	    return true;
    }

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}
