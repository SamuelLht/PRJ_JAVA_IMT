package jvmd.app.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jvmd.app.Constants;
import jvmd.app.JVMD;

public class MenuScreen implements Screen{
    
	final JVMD app;
    private OrthographicCamera camera;    
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private TextureAtlas atlas;
    protected Skin skin;
    private BitmapFont font;
    private TextButtonStyle textButtonStyle;
    
    public MenuScreen(final JVMD app){
        
    	this.app = app;
    	
    	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("super_mario_bros.ttf"));
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = 40;
    	font = generator.generateFont(parameter);
        
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("sprites.atlas"));
        skin.addRegions(atlas);
        
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("default_button");
        textButtonStyle.down = skin.getDrawable("default_button");
        textButtonStyle.checked = skin.getDrawable("default_button");
        
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();
        viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();
        
        stage = new Stage(viewport, batch);
        
        Gdx.input.setInputProcessor(new MenuInputProcessor(this));
        
    }

    @Override
    public void render(float delta) {
		// TODO Auto-generated method stub
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.top();

        //Create buttons
        TextButton playButton = new TextButton("Play", textButtonStyle);
        TextButton exitButton = new TextButton("Exit", textButtonStyle);

        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.launchGame();
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(exitButton);

        //Add table to stage
        stage.addActor(mainTable);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
        skin.dispose();
        atlas.dispose();
	}

}