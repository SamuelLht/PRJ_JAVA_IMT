package jvmd.app.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import jvmd.app.Constants;
import jvmd.app.JVMD;
import jvmd.app.Game.Sprites.Enemy;
import jvmd.app.Game.Sprites.Item;
import jvmd.app.Game.Sprites.Player;

public class GameScreen extends ApplicationAdapter implements Screen{
	
	final JVMD app;
    private OrthographicCamera camera;
    private World world;
    private TiledMap tiledMap;
    
    private SpriteBatch batch;
    private Texture texture;
    private Player Mario;
    private Array<Enemy> enemies;
    private Array<Item> items;
    
    public GameScreen(final JVMD app){   
    		this.app = app;
    		this.create();
    }
    

	public void create() {
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("resources/koalio-single.png"));
		Mario = new Player(this, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2);
		Mario.setTexture(texture);
	}

	public void render() {
		super.render();
		batch.begin();
		Mario.draw(batch);
		batch.end();
	}

	public void render(float delta) {
		//super.render();
		
		batch.begin();
		Mario.draw(batch);
		batch.end();
	}

	public void launchMenu() {
    	app.launchMenu();
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		world = new World(Constants.GRAVITY, true);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();
        
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load("");
        
        Gdx.input.setInputProcessor(new GameInputProcessor(this, world));
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		app.setScreen(new PauseScreen(app, this));
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
		batch.dispose();
		texture.dispose();
	}

}