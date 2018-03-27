package jvmd.app.Game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World; 
import jvmd.app.Game.Sprites.Player;
import com.badlogic.gdx.Input.Keys;

public class GameInputProcessor implements InputProcessor {

	private GameScreen screen;
	private World world;
	private Player MainPlayer;
	
	public boolean KeyRight = false;
	public boolean KeyLeft = false;
	public boolean KeyJump = false;
	
	public GameInputProcessor(GameScreen screen, World world, Player MainPlayer) {

		// TODO Auto-generated constructor stub
		this.screen = screen;
		this.world = world;
		this.MainPlayer = MainPlayer;
	}

	@Override
    public boolean keyDown(int keycode)
    {
	    switch (keycode)
	    {
			case Keys.LEFT:
				KeyLeft = true;
				StatutToucheMAJ();
				MainPlayer.DeplacementX();
				break;
			case Keys.RIGHT:
				KeyRight = true;
				StatutToucheMAJ();
				MainPlayer.DeplacementX();
				break;
			case Keys.SPACE:
				KeyJump = true;
			 	StatutToucheMAJ();
				MainPlayer.Saut();
				//MainPlayer.DeplacementY();
				break;
	    }
	    return true;
    }
    @Override
    public boolean keyUp(int keycode)
    {
	    switch (keycode)
	    {
			case Keys.LEFT:
				KeyLeft = false;
				StatutToucheMAJ();
				break;
			case Keys.RIGHT:
				KeyRight = false;
				StatutToucheMAJ();
				break;
			case Keys.SPACE:
				KeyJump = false;
				StatutToucheMAJ();
				break;
      case Keys.ESCAPE:
				screen.pause();
				break;
				
			default:
				break;
	    }
	    return true;
    }
    
    public void StatutToucheMAJ() {
    		MainPlayer.KeyRightDown = KeyRight;
    		MainPlayer.KeyLeftDown = KeyLeft;
    		MainPlayer.KeyJumpDown = KeyJump;
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
