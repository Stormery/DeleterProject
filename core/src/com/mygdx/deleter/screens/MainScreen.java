package com.mygdx.deleter.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.deleter.DeleterProject;


public class MainScreen extends AbstractScreen {

    SpriteBatch batch;
    Image img;
    protected Stage stage;

    public MainScreen(DeleterProject dp) {
        super();
        init();
    }


    protected void init() {
        batch = new SpriteBatch();
        stage = new Stage();
        img = new Image( new Texture("background.png"));
        stage.addActor(img);
    }


    @Override
    public void show() {}

    @Override
    public void render(float delta) {
     super.render(delta);
        batch.begin();

        stage.draw();

        batch.end();

    }


}
