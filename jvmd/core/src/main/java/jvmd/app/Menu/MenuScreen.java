package jvmd.app.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jvmd.app.Constants;
import jvmd.app.GraphicsHelper;
import jvmd.app.JVMD;

public class MenuScreen implements Screen {

	final JVMD app;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	protected Stage stage;
	private Viewport viewport;
	private GraphicsHelper graphicsHelper = GraphicsHelper.getInstance();

	public MenuScreen(final JVMD app) {

		this.app = app;

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		camera.update();
		viewport = new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
		viewport.apply();

		stage = new Stage(viewport, batch);

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

		// Create Table
		Table mainTable = new Table();
		// Set table to fill stage
		mainTable.setFillParent(true);
		// Set alignment of contents in the table.
		mainTable.center();

		// Main title
		Label title = graphicsHelper.getMarioLabel("Menu", 200, null);
		title.setAlignment(2, 2);
		mainTable.add(title).row();
		
		// Subtitle
		Label version = graphicsHelper.getRetroLabel("version "+Constants.CURRENT_VERSION, 25, Color.valueOf("963230"));
		Label jvmd = graphicsHelper.getRetroLabel("Je Veux Mon Diplome", 35, Color.valueOf("963230"));
		VerticalGroup subtitle = new VerticalGroup().center().padTop(30).padBottom(50);
		subtitle.addActor(jvmd);
		subtitle.addActor(version);
		mainTable.add(subtitle).row();
		
		// Buttons
		TextButton playButton = graphicsHelper.getTextButton("Play", 40, new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				app.launchGame();
			}
		});
		
		TextButton exitButton = graphicsHelper.getTextButton("Exit", 40, new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		HorizontalGroup buttons = new HorizontalGroup().center().padTop(20);
		buttons.addActor(playButton);
		buttons.addActor(exitButton);
		mainTable.add(buttons);

		// Add table to stage
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
		
	}

}