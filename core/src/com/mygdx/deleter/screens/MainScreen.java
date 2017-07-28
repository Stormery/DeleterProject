package com.mygdx.deleter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.deleter.DeleterProject;


public class MainScreen implements Screen {

    SpriteBatch batch;
    Texture img;
    protected Stage stage;

    public MainScreen(DeleterProject dp) {
        super();
        init();
    }


    protected void init() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        //stage.addActor(img);
    }


    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(img,50,0);

        batch.end();

    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}
