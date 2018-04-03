package jvmd.app.Game.Sprites;

import jvmd.app.Game.GameScreen;

public abstract class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite {
	
	protected GameScreen game;
	protected float WIDTH;
	protected float HEIGHT;
	
	public Sprite() {
		this.game = null;
		this.WIDTH = 0;
		this.HEIGHT = 0;
	}
	
	public Sprite(GameScreen screen, float width, float height, float x, float y) {
        this.game = screen;
        this.WIDTH = width;
        this.HEIGHT = height;
        setPosition(x, y);
    }

	/**
	 * @return the game
	 */
	public GameScreen getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(GameScreen game) {
		this.game = game;
	}

	/**
	 * @return the wIDTH
	 */
	public float getWIDTH() {
		return WIDTH;
	}

	/**
	 * @param wIDTH the wIDTH to set
	 */
	public void setWIDTH(float wIDTH) {
		WIDTH = wIDTH;
	}

	/**
	 * @return the hEIGHT
	 */
	public float getHEIGHT() {
		return HEIGHT;
	}

	/**
	 * @param hEIGHT the hEIGHT to set
	 */
	public void setHEIGHT(float hEIGHT) {
		HEIGHT = hEIGHT;
	}
	
	
}
