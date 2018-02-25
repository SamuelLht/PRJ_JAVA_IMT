package jvmd.app.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import jvmd.app.Constants;
import jvmd.app.JVMD;

public class PauseScreen implements Screen{
    
	final JVMD app;
	final GameScreen game;
    private OrthographicCamera camera;
    
    public PauseScreen(final JVMD app, final GameScreen game){
        
    	this.app = app;
    	this.game = game;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();
        
        Gdx.input.setInputProcessor(new PauseInputProcessor(this));
    }
    
    public void resumeGame() {
    	app.setScreen(game);
    }
    
    public void newGame() {
    	app.launchGame();
    }
    
    public void launchMenu() {
    	app.launchMenu();
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub      
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}