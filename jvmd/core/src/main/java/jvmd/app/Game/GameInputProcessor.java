package jvmd.app.Game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;

public class GameInputProcessor implements InputProcessor {
	
	private GameScreen screen;
	
	public GameInputProcessor(GameScreen screen, World world) {
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
			case Keys.DOWN:
				screen.Mario.setDown(true);
				break;
			case Keys.SPACE:
				if(screen.Mario.getSpace() == 0)
					screen.Mario.setSpace(1);
				else
					screen.Mario.setSpace(2);
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
			case Keys.DOWN:
				screen.Mario.setDown(false);
				break;
			case Keys.SPACE:
				screen.Mario.setSpace(0);
				break;
	    }
	    return true;
    }

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
