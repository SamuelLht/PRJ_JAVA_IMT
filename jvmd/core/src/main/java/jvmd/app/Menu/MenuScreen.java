package jvmd.app.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import Map.TileMap;
import jvmd.app.Constants;
import jvmd.app.JVMD;

public class MenuScreen implements Screen{
    
	final JVMD app;
    private OrthographicCamera camera;
    private TileMap map;
    
    public MenuScreen(final JVMD app){
        
    	this.app = app;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();
        
        map = new TileMap("level1.tmx", camera);
        
        Gdx.input.setInputProcessor(new MenuInputProcessor(this));
        
    }
    
    public void launchGame() {
    	app.launchGame();
    }

    @Override
    public void render(float delta) {
        camera.update();
        map.getTiledMapRenderer().setView(camera); // Si quelqu'un a une meilleur proposition ici je suis preneur !
        map.getTiledMapRenderer().render();
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