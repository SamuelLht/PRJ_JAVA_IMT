package Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Classe utilitaire permettant de generer des TiledMap qu'on peut ensuite afficher facilement o� on veut.
 * 
 * @author Yazid
 *
 */

public class TileMap {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	
	public TileMap() {
		tiledMap = new TiledMap();
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}
	
	public TileMap(String PathToTmx, OrthographicCamera camera) {
		tiledMap = new TmxMapLoader().load(PathToTmx);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	public TiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	public void setTiledMapRenderer(TiledMapRenderer tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}	
}
