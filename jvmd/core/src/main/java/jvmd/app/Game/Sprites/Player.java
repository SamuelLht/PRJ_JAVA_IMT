package jvmd.app.Game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import jvmd.app.Game.GameInputProcessor;
import jvmd.app.Game.GameScreen;

public class Player extends AnimatedSprite {

	public int positionX;
	public int positionY;
	
	private GameInputProcessor GameInputProc;
	private GameScreen screen;
	
	public int ConstantVitesse = 10;
	public int HauteurSaut = 40;
	
	public boolean KeyRightDown, KeyLeftDown, KeyJumpDown;
	
	public Player(GameScreen p_screen, float x, float y) {
		super(p_screen, x, y);
		screen = p_screen;
		positionX = 150;
		positionY = 150;
	}

	public void DeplacementX() {
		if (KeyRightDown == true) {
			positionX = positionX + ConstantVitesse;
			GameInputProc.StatutToucheMAJ();
		}
			
		if (KeyLeftDown == true) {
			positionX = positionX - ConstantVitesse;
			GameInputProc.StatutToucheMAJ();
		}
	}
	
	public void DeplacementY() {
		if (KeyJumpDown == true) {
			positionY = positionY + ConstantVitesse;
			GameInputProc.StatutToucheMAJ();
			
		}
	}
	
	public void Saut() {
		for(int i = 0; i < 1000; i++) {
			positionY = positionY + 1;
		}
		
		for(int i = 0; i < 1000; i++) {
			positionY = positionY - 1;
		}
		
	}
	
	public void getGameInputProcessor(GameInputProcessor pProcessor) {
		GameInputProc = pProcessor;
	}
	
	public void MajDeplacement() {
		DeplacementX();
		DeplacementY();
	}

}
