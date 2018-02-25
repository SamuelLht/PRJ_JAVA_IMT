package jvmd.app.Game.Sprites;

import jvmd.app.Game.GameScreen;

public abstract class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite {
	
	public GameScreen screen;
	
	public Sprite(GameScreen screen, float x, float y) {
		
        this.screen = screen;

        setPosition(x, y);

    }
	
}
