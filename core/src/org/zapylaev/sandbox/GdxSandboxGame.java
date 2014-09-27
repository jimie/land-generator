package org.zapylaev.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.zapylaev.sandbox.renderer.FpsRenderer;
import org.zapylaev.sandbox.renderer.LineRenderer;
import org.zapylaev.sandbox.renderer.MapRenderer;
import org.zapylaev.sandbox.renderer.Renderer;

public class GdxSandboxGame extends ApplicationAdapter {

    private OrthographicCamera mMainCamera;
    private Renderer mFpsRenderer;
    private Renderer mMapRenderer;
    private Renderer mLineRenderer;

    @Override
    public void create() {
        mMainCamera = new OrthographicCamera();
        mMainCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mMainCamera.update();

        Gdx.input.setInputProcessor(new OrthoCamController(mMainCamera));

        mFpsRenderer = new FpsRenderer();
        mMapRenderer = new MapRenderer();
        mLineRenderer = new LineRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mMainCamera.update();

        mMapRenderer.render(mMainCamera);
        mLineRenderer.render(mMainCamera);
        mFpsRenderer.render(mMainCamera);
    }

    @Override
    public void resize(int width, int height) {
        mMainCamera.viewportWidth = width;
        mMainCamera.viewportHeight = mMainCamera.viewportWidth * height / width;
        mMainCamera.update();
    }
}
