package org.zapylaev.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.zapylaev.sandbox.renderer.FpsRenderer;
import org.zapylaev.sandbox.renderer.LineRenderer;
import org.zapylaev.sandbox.renderer.MapRenderer;
import org.zapylaev.sandbox.renderer.Renderer;

public class GdxSandboxGame extends ApplicationAdapter {

    private Stage mUI;
    private OrthographicCamera mMainCamera;
    private Renderer mFpsRenderer;
    private Renderer mMapRenderer;
    private Renderer mLineRenderer;

    @Override
    public void create() {
        mMainCamera = new OrthographicCamera();
        mMainCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mMainCamera.update();

        mUI = new Stage();
        Gdx.input.setInputProcessor(mUI);
        initUI();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mUI);
        inputMultiplexer.addProcessor(new OrthoCamController(mMainCamera));
        Gdx.input.setInputProcessor(inputMultiplexer);

        mFpsRenderer = new FpsRenderer();
        mMapRenderer = new MapRenderer();
        mLineRenderer = new LineRenderer();
    }

    private void initUI() {
        TextButton button = new TextButton("Reset", new Skin(Gdx.files.internal("skin/uiskin.json")));
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                mMapRenderer = new MapRenderer();
            }
        });
        mUI.addActor(button);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mMainCamera.update();

        mMapRenderer.render(mMainCamera);
//        mLineRenderer.render(mMainCamera);
        mFpsRenderer.render(mMainCamera);
        mUI.act();
        mUI.draw();
    }

    @Override
    public void resize(int width, int height) {
        mMainCamera.viewportWidth = width;
        mMainCamera.viewportHeight = mMainCamera.viewportWidth * height / width;
        mMainCamera.update();
    }
}
