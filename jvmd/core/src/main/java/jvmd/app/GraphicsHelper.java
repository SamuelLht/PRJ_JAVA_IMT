package jvmd.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class GraphicsHelper
{   
	
	// Singleton
	
	private static GraphicsHelper INSTANCE = null;
    
    public static synchronized GraphicsHelper getInstance()
    {           
        if (INSTANCE == null){   
        	INSTANCE = new GraphicsHelper(); 
        }
        return INSTANCE;
    }
	
    // Properties
    
	Skin UISkin;
	
	FreeTypeFontGenerator MarioFontGenerator;
	FreeTypeFontGenerator RetroFontGenerator;
	
	TextButtonStyle UITextButtonStyle;
	
	// Constructor
	
    private GraphicsHelper()
    {
    	
    	UISkin = new Skin(new TextureAtlas(Gdx.files.internal("sprites.atlas")));
    	
    	MarioFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("super_mario_bros.ttf"));
    	RetroFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("retro.ttf"));
    	
    	UITextButtonStyle = new TextButtonStyle();
    	UITextButtonStyle.up = UISkin.getDrawable("default_button");
    	UITextButtonStyle.down = UISkin.getDrawable("default_button");
    	UITextButtonStyle.checked = UISkin.getDrawable("default_button");
    	
    }
    
    // Helper methods
    
    public TextButton getTextButton(String content, int size, InputListener listener) {
    	UITextButtonStyle.font = getMarioFont(size);
    	TextButton button = new TextButton(content, UITextButtonStyle);
    	button.addListener(listener);
    	return button;
    }
    
    public Label getMarioLabel(String content, int size, Color fontColor) {
    	return new Label(content, 
    			new LabelStyle(getMarioFont(size), fontColor != null ? fontColor : Color.WHITE));
    }
    
    public Label getRetroLabel(String content, int size, Color fontColor) {
    	return new Label(content, 
    			new LabelStyle(getRetroFont(size), fontColor != null ? fontColor : Color.WHITE));
    }
    
    public BitmapFont getMarioFont (int size) {
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = size;
    	return MarioFontGenerator.generateFont(parameter);
    }
    
    public BitmapFont getRetroFont (int size) {
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = size;
    	return RetroFontGenerator.generateFont(parameter);
    }
 
}
