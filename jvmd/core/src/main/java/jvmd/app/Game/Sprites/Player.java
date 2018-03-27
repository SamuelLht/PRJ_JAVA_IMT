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
	
	//Pour méthode déplacement
	public boolean KeyRightDown, KeyLeftDown, KeyJumpDown;
	
	//Pour méthode Jump()
	int etape = 0;
	boolean sens = true; //true saut - false chute
	
	public Player(GameScreen p_screen, float x, float y) {
		super(p_screen, x, y);
		screen = p_screen;
		positionX = 150;
		positionY = 150;
	}
	
	public void MajDeplacement() {
		Right();
		Left();
		Jump();
	}

	public void Right() {
		if (KeyRightDown == true) {
			positionX = positionX + ConstantVitesse;
			GameInputProc.UpdateKeys();
		}
	}
	
	public void Left() {
		if (KeyLeftDown == true) {
			positionX = positionX - ConstantVitesse;
			GameInputProc.UpdateKeys();
		}
	}
	
	public void Jump() {		
		if (KeyJumpDown == true) {
			if (sens == true) {
				if (etape < 10) {
					positionY = positionY + ConstantVitesse;				
					etape = etape + 1;
				}
				if (etape == 10) {
					sens = false;
				}
			}
			if (sens == false) {
				if (etape > 0) {
					positionY = positionY - ConstantVitesse;				
					etape = etape - 1;
				}
				if (etape == 0) {
					sens = true;
					KeyJumpDown = false;
					GameInputProc.KeyJump = false;
				}
			}
		}
		/*
		if (sens == true) {
			if (etape < 30) {
				positionY = positionY + 1;
				etape = etape + 1;
			}
			if (etape == 30) {
				sens = false;
			}
		}*/
		
		/*if (sens == false) {
			if (etape > 0) {
				positionY = positionY - 1;
				etape = etape - 1; 
			}
		}*/
		
		/*if (etape != 0) {
			Jump();
		}*/
	}
	
	public void getGameInputProcessor(GameInputProcessor pProcessor) {
		GameInputProc = pProcessor;
	}

}
