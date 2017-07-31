package com.mygdx.deleter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.deleter.DeleterProject;



public class MainScreen extends AbstractScreen {



    TextureAtlas textureAtlas;
    Skin skin;

    Image imgBackground;

    //Tables
   private boolean tableDebug = false;

    private Table tableMain;
    private Table tableInner;
    private Table tableScrollable;


    public MainScreen(DeleterProject dp) {
        super();
        init();
        test();
    }

    private void test() {
        for(int i = 1; i<20;i++){
            addMessage("DCM000"+i);
        }
    }


    protected void init() {
        initAtlasSkin();
        initBackground();
        initTables();

    }
    private void addMessage(String message) {
        tableScrollable.add(new Label(message, skin)).left();
        tableScrollable.row();
    }

    private void initTables() {
        tableMain = new Table();
        tableMain.setFillParent(true);
        tableMain.setDebug(tableDebug);
        tableMain.top().left().padTop(10f);


        tableInner = new Table();
        tableInner.setDebug(tableDebug);
        tableInner.top();
        ///TOP
        tableInner.add().width(230f).height(145f).padLeft(10f);
        tableInner.add().width(230f).height(145f).padLeft(45f);
        tableInner.add().width(95f).height(145f).padLeft(65f);
        tableInner.row();
        //MID
        tableInner.add().expandX().height(140f).colspan(3).padTop(35f);
        tableInner.row();
        // ScrollingTable
        tableScrollable = new Table();
        tableScrollable.setDebug(false);
        tableScrollable.top().left();

        ScrollPane scrollPane = new ScrollPane(tableScrollable);
        scrollPane.setOverscroll(false ,false);

        //BOT
        tableInner.add(scrollPane).width(510f).height(135f).colspan(2).padTop(28f);
        tableInner.add().width(100f).height(55f).padTop(28f).padLeft(30f);



        tableMain.add(tableInner);
        stage.addActor(tableMain);
    }


    private void initAtlasSkin() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
        skin = new Skin(Gdx.files.internal("uiskin.json"),textureAtlas);
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
        stage.act();

        stage.draw();


        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
