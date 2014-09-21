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

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GdxSandboxGame extends ApplicationAdapter {
    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private OrthoCamController cameraController;
    private Texture tiles;
    private BitmapFont font;
    private SpriteBatch batch;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        cameraController = new OrthoCamController(camera);
        Gdx.input.setInputProcessor(cameraController);

        font = new BitmapFont();
        batch = new SpriteBatch();

        tiles = new Texture(Gdx.files.internal("tiles.png"));
        final TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);
        map = new TiledMap();

        renderer = new OrthogonalTiledMapRenderer(map);

        Scanner mapScanner = new Scanner(Gdx.files.internal("sample_map").read());
        int[][] mapBuffer = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapBuffer[i][j] = mapScanner.nextInt();
            }
        }

        MapLayers layers = map.getLayers();
        TiledMapTileLayer layer = new TiledMapTileLayer(10, 10, 32, 32);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(splitTiles[0][mapBuffer[i][j]]));
                layer.setCell(j, i, cell);
            }
        }
        layers.add(layer);
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

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = camera.viewportWidth * height / width;
        camera.update();
    }
}
