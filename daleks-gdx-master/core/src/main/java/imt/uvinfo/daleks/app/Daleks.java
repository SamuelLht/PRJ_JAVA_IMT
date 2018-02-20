package imt.uvinfo.daleks.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Daleks implements ApplicationListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private float elapsed;
    private Sprite alien;
    private Animation<TextureRegion> idle;

    @Override

    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        batch = new SpriteBatch();

        Texture alienSpriteMap = new Texture(Gdx.files.internal("alienGreen.png"));
        alien = new Sprite(alienSpriteMap,
                           70, 92,
                           66, 92);
        idle = new Animation<TextureRegion>(0.5f, new Array<>(new TextureRegion[]{
                new TextureRegion(alienSpriteMap, 70, 92, 66, 92),
                new TextureRegion(alienSpriteMap, 69, 379, 66, 92),
                new TextureRegion(alienSpriteMap, 135, 379, -66, 92),
        }));
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        elapsed += Gdx.graphics.getDeltaTime();

        // Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        alien.setPosition(400 + 100 * (float) Math.cos(elapsed), 300 + 25 * (float) Math.sin(elapsed));

        TextureRegion currentAlien = idle.getKeyFrame(elapsed, true);
        batch.begin();
        alien.draw(batch);
        batch.draw(currentAlien, 50, 50);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
