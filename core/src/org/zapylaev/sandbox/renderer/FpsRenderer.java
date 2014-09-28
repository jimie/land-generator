package org.zapylaev.sandbox.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class FpsRenderer implements Renderer {

    private BitmapFont mFont;
    private SpriteBatch mBatch;

    public FpsRenderer() {
        mFont = new BitmapFont();
        mBatch = new SpriteBatch();
    }

    @Override
    public void render(OrthographicCamera camera) {
        mBatch.begin();
        mFont.draw(mBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 100, 20);
        mBatch.end();
    }
}
