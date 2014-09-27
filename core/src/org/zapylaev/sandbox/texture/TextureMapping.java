package org.zapylaev.sandbox.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.zapylaev.sandbox.map.DiamondSquareGenerator;

import java.util.EnumMap;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class TextureMapping {

    private final EnumMap<TextureEnum, TextureRegion> mRegionMap;
    public static final int TEXTURE_COUNT = 64;
    private final TextureRegion[][] mSplitTiles;
    private final TextureRegion[] mTexturesGradient;

    public TextureMapping() {
        Texture tiles = new Texture(Gdx.files.internal("tiles.png"));
        mSplitTiles = TextureRegion.split(tiles, 32, 32);
        mRegionMap = new EnumMap<TextureEnum, TextureRegion>(TextureEnum.class);
        mRegionMap.put(TextureEnum.WATER, mSplitTiles[0][0]);
        mRegionMap.put(TextureEnum.SAND, mSplitTiles[0][1]);
        mRegionMap.put(TextureEnum.EARTH, mSplitTiles[0][2]);
        mRegionMap.put(TextureEnum.MOUNTAIN, mSplitTiles[0][3]);
        mRegionMap.put(TextureEnum.PLAYER, mSplitTiles[0][4]);

        mTexturesGradient = new TextureRegion[TEXTURE_COUNT];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 16; j++) {
                mTexturesGradient[count] = mSplitTiles[i][j];
                count++;
            }
        }
    }

    public TextureRegion get(int cell) {
        if (cell < 64 && cell >= 0) {
            return mTexturesGradient[cell];
        } else {
            return mTexturesGradient[63];
        }
    }

    public enum TextureEnum {
        WATER, SAND, EARTH, MOUNTAIN, PLAYER;

        public static TextureEnum valueOf(int id) {
            if (id < 5) {
                return WATER;
            } else if (id < 10) {
                return SAND;
            } else if (id < 20) {
                return EARTH;
            } else {
                return MOUNTAIN;
            }
        }
    }
}
