package com.mygdx.deleter.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.deleter.DeleterProject;


public class MainScreen extends AbstractScreen {

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
      //  stage.addActor(img);
    }


    @Override
    public void show() {}

    @Override
    public void render(float delta) {
     super.render(delta);
        batch.begin();

        batch.draw(img,0,0);

        batch.end();

    }


}
