package org.zapylaev.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GdxSandboxGame extends ApplicationAdapter {
    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private OrthoCamController cameraController;
    private AssetManager assetManager;
    private Texture tiles;
    private Texture texture;
    private BitmapFont font;
    private SpriteBatch batch;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void create() {
        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.setToOrtho(false, 1024, 768);
        camera.update();

        cameraController = new OrthoCamController(camera);
        Gdx.input.setInputProcessor(cameraController);

        font = new BitmapFont();
        batch = new SpriteBatch();

        tiles = new Texture(Gdx.files.internal("tiles.png"));
        final TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);
        map = new TiledMap();
        MapLayers layers = map.getLayers();
        TiledMapTileLayer layer = new TiledMapTileLayer(100, 100, 32, 32);
        layers.add(layer);

        renderer = new OrthogonalTiledMapRenderer(map);
        scheduledExecutorService = Executors.newScheduledThreadPool(1500);

        for (int i = 0; i < 1500; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    MapLayers layers = map.getLayers();
                    TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(0);
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][((int) (Math.random() * 4))]));
                    layer.setCell(((int) (Math.random() * 100)), ((int) (Math.random() * 100)), cell);
                }
            }, 0, 100 * i + 1, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
    }
}
