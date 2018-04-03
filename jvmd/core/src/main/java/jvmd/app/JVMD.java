package jvmd.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jvmd.app.Game.GameScreen;
import jvmd.app.Menu.MenuScreen;

public class JVMD extends Game implements ApplicationListener {
    
	public AssetManager assets;

    @Override
    public void create() {
        new SpriteBatch();
        assets = new AssetManager();

        launchMenu();
    }

    @Override
    public void render() {
    	super.render();
    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

	public void launchGame() {
		this.setScreen(new GameScreen(this));
	}
	
	public void launchMenu() {
		this.setScreen(new MenuScreen(this));
	}
}
