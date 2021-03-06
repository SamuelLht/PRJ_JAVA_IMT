package jvmd.app.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import jvmd.app.JVMD;
import jvmd.app.Game.Sprites.Player;
import jvmd.app.Constants;

public class GameScreen extends ApplicationAdapter implements Screen {

	final JVMD app;
	private GameInputProcessor GameInputProc;
	private OrthographicCamera camera;
	private World world;
	public TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	private Batch batch;
	private Texture texture;
	public Player Mario;

	public Pool<Rectangle> pool = new Pool<Rectangle>() {
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	public Array<Rectangle> tiles = new Array<Rectangle>();

	Box2DDebugRenderer debugRenderer;

	public GameScreen(final JVMD app) {
		this.app = app;
		this.create();
	}

	public void create() {

		debugRenderer = new Box2DDebugRenderer();

		// world = new World(new Vector2(0, -10), true);

		GameInputProc = new GameInputProcessor(this, world);
		Gdx.input.setInputProcessor(GameInputProc);

		map = new TmxMapLoader().load("tilset/Niveau1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 20f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();
		
		batch = renderer.getBatch();
		Mario = new Player(this, 2, 30);
	}

	public void render(float delta) {

		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float deltaTime = Gdx.graphics.getDeltaTime();

		Mario.update(deltaTime);
		camera.position.x = Mario.getPosition().x;
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
		if(!Mario.isAlive()) {
			launchMenu();
		}
		
		TextureRegion frame = null;
		switch (Mario.state) {
			case Standing:
				frame = Mario.stand.getKeyFrame(0);
				break;
			case Walking:
				frame = Mario.walk.getKeyFrame(Mario.stateTime);
				break;
			case Jumping:
				frame = Mario.jump.getKeyFrame(Mario.stateTime);
				break;
		}

		Batch batch = renderer.getBatch();
		batch.begin();
		if (Mario.facesRight) {
			batch.draw(frame, Mario.position.x, Mario.position.y, Mario.getWIDTH(), Mario.getHEIGHT());
		} else {
			batch.draw(frame, Mario.position.x + Mario.getWIDTH(), Mario.position.y, -Mario.getWIDTH(), Mario.getHEIGHT());
		}
		batch.end();
		
		/*batch.begin();
		batch.draw(Mario.getTexture(), Mario.getPosition().x, Mario.getPosition().y, Mario.getWIDTH(), Mario.getHEIGHT());
		batch.end();*/
	}
	
	public void launchMenu() {
		app.launchMenu();
	}

	public void getTiles(int startX, int startY, int endX, int endY, String layers) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layers);
		pool.freeAll(tiles);
		tiles.clear();
		for (int y = 0; y <= endY; y++) {
			for (int x = 0; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = pool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}

	public Rectangle overlapsTiles(Rectangle rect) {
		for (Rectangle tile : tiles) {
			if (rect.overlaps(tile)) {
				return tile;
			}
		}
		return null;
	}

	@Override
	public void pause() {
		app.setScreen(new PauseScreen(app, this));
	}

	@Override
	public void resume() {
		Gdx.input.setInputProcessor(GameInputProc);
	}

	@Override
	public void dispose() {
		if (batch != null)
			batch.dispose();
		if (texture != null)
			texture.dispose();
	}

	@Override
	public void show() {}
	
	@Override
	public void hide() {	}
}