package org.zapylaev.sandbox.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import org.zapylaev.sandbox.Constants;
import org.zapylaev.sandbox.map.GenerateFromFile;
import org.zapylaev.sandbox.map.MapGenerator;
import org.zapylaev.sandbox.texture.TextureMapping;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class MapRenderer implements Renderer {

    private TiledMapRenderer mTiledMapRenderer;
    private TiledMap mTiledMap;
    private TextureMapping mTextureMapping;

    public MapRenderer() {
        mTextureMapping = new TextureMapping();
        MapGenerator mapGenerator = new GenerateFromFile();
        int[][] generatedMap = mapGenerator.generateMap(Constants.MAP_SIZE);
        mTiledMap = new TiledMap();
        mTiledMapRenderer = new OrthogonalTiledMapRenderer(mTiledMap);
        MapLayers layers = mTiledMap.getLayers();
        TiledMapTileLayer layer = new TiledMapTileLayer(Constants.MAP_SIZE, Constants.MAP_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
        for (int i = 0; i < Constants.MAP_SIZE; i++) {
            for (int j = 0; j < Constants.MAP_SIZE; j++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                int mapCellValue = generatedMap[i][j];
                cell.setTile(new StaticTiledMapTile(mTextureMapping.get(mapCellValue)));
                layer.setCell(j, i, cell);
            }
        }
        layers.add(layer);
    }

    @Override
    public void render(OrthographicCamera camera) {
        mTiledMapRenderer.setView(camera);
        mTiledMapRenderer.render();
    }
}
