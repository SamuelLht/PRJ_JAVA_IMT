package jvmd.app.Game.Sprites;

import jvmd.app.Game.GameScreen;

public abstract class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite {
	
	public GameScreen game;
	public float WIDTH;
	public float HEIGHT;
	
	public Sprite(GameScreen screen, float width, float height, float x, float y) {
		
        this.game = screen;
        
        WIDTH = width;
        HEIGHT = height;
        
        setPosition(x, y);

    }
	
}
