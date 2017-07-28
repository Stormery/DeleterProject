package com.mygdx.deleter.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.deleter.DeleterProject;


public class MainScreen extends AbstractScreen {

    SpriteBatch batch;
    protected Stage stage;

    Image imgBackground;

    //Tables
   private boolean tableDebug = true;
    private Table tableMain;
    private Table tableTop;


    public MainScreen(DeleterProject dp) {
        super();
        init();
    }


    protected void init() {
        batch = new SpriteBatch();
        stage = new Stage();
        initBackground();
         initTables();

    }

    private void initTables() {
        tableMain = new Table();
        tableMain.setFillParent(true);
        tableMain.setDebug(true);
        tableMain.top().left().padTop(10f);
        stage.addActor(tableMain);

        tableTop = new Table();
        tableTop.setDebug(true);
        tableTop.top();
        ///TOP
        tableTop.add().width(230f).height(145f).padLeft(10f);
        tableTop.add().width(230f).height(145f).padLeft(45f);
        tableTop.add().width(95f).height(145f).padLeft(65f);
        tableTop.row();
        //MID
        tableTop.add().expandX().height(140f).colspan(3).padTop(35f);
        tableTop.row();
        //BOT
        tableTop.add().width(510f).height(140f).colspan(2).padTop(28f);
        tableTop.add().width(100f).height(55f).padTop(28f).padLeft(30f);
        


        tableMain.add(tableTop);
    }

    private void initBackground() {
        imgBackground = new Image( new Texture("background.png"));
        stage.addActor(imgBackground);
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
