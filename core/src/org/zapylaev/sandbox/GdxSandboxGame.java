package org.zapylaev.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxSandboxGame extends ApplicationAdapter {
    private Texture mTexture;
    private OrthographicCamera mMainCamera;
    private SpriteBatch mMainBatch;
    private Pixmap mPixmap;

    @Override
    public void create() {
        mMainBatch = new SpriteBatch();
        mPixmap = new Pixmap((int) Constants.SCREEN_WIDTH, (int) Constants.SCREEN_HEIGHT, Pixmap.Format.RGBA8888);
        mPixmap.setColor(Color.BLUE);
        mPixmap.fill();
        mPixmap.setColor(Color.ORANGE);
        mPixmap.drawPixel(0, 0);
        mPixmap.drawPixel(1, 1);
        mPixmap.drawPixel(2, 2);
        mTexture = new Texture(mPixmap);
        mPixmap.dispose();
        mMainCamera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        mMainCamera.update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mMainBatch.setProjectionMatrix(mMainCamera.combined);
        mMainBatch.begin();
        mMainBatch.draw(mTexture, -Constants.SCREEN_WIDTH / 2, -Constants.SCREEN_HEIGHT / 2);
        mMainBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / height;
        mMainCamera.viewportWidth = Constants.SCREEN_HEIGHT * aspectRatio;
        mMainCamera.update();
    }
}
