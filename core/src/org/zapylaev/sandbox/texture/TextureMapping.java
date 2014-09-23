package org.zapylaev.sandbox.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.EnumMap;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class TextureMapping {

    private final EnumMap<TextureEnum, TextureRegion> mRegionMap;

    public TextureMapping() {
        Texture tiles = new Texture(Gdx.files.internal("tiles.png"));
        TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);
        mRegionMap = new EnumMap<TextureEnum, TextureRegion>(TextureEnum.class);
        mRegionMap.put(TextureEnum.WATER, splitTiles[0][0]);
        mRegionMap.put(TextureEnum.SAND, splitTiles[0][1]);
        mRegionMap.put(TextureEnum.EARTH, splitTiles[0][2]);
        mRegionMap.put(TextureEnum.MOUNTAIN, splitTiles[0][3]);
        mRegionMap.put(TextureEnum.PLAYER, splitTiles[0][4]);
    }

    public TextureRegion get(int cell) {
        return mRegionMap.get(TextureEnum.valueOf(cell));
    }

    public enum TextureEnum {
        WATER, SAND, EARTH, MOUNTAIN, PLAYER;

        public static TextureEnum valueOf(int id) {
            switch (id) {
                case 0: return WATER;
                case 1: return SAND;
                case 2: return EARTH;
                case 3: return MOUNTAIN;
                case 4: return PLAYER;
                default: throw new IllegalArgumentException("Unknown value: " + id);
            }
        }
    }
}
