package org.zapylaev.sandbox.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.zapylaev.sandbox.map.DiamondSquareGenerator;
import org.zapylaev.sandbox.map.GenerateFromFile;
import org.zapylaev.sandbox.map.MapGenerator;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class LineRenderer implements Renderer {

    private final MapGenerator mMapGenerator;
    private int[] mLine;
    private ShapeRenderer mShapeRenderer;

    public LineRenderer() {
        mMapGenerator = new DiamondSquareGenerator();
        mLine = mMapGenerator.generateLine(128);
        mShapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(OrthographicCamera camera) {
        mShapeRenderer.setProjectionMatrix(camera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.setColor(Color.RED);
        for (int i = 0; i < mLine.length; i++) {
            float y = mLine[i] * 10;
            float x = i * 10;
            mShapeRenderer.line(x, 0, x, y);
            if (i < mLine.length - 1) {
                mShapeRenderer.line(x, y, (i + 1) * 10, mLine[i + 1] * 10);
            }
        }
        mShapeRenderer.end();
    }
}
