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
import com.mygdx.deleter.ui.FileDrop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainScreen extends AbstractScreen {

    Skin skin;

    Image imgBackground;

    //Tables
    private boolean tableDebug = false;

    private Table tableMain;
    private Table tableInner;
    private Table tableScrollable;
    private ScrollPane scrollPane;

    public boolean filesLoaded;
    public boolean textLoaded;

    public MainScreen(DeleterProject dp) {
        super();
        init();
       // test();
    }

    private void test() {
        for (int i = 1; i < 20; i++) {
            addMessage("DCM000" + i);
        }
    }

    protected void init() {
        initAtlasSkin();
        initBackground();
        initTables();
        initDragnDrop(); // potrzebuje przycisk wlaczajacy to
    }

    private void initDragnDrop() {
        //TODO Tlo zeby bylo wiadomo ze tam dropic itemy

        javax.swing.JFrame frame = new javax.swing.JFrame("FileDrop");
        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        frame.getContentPane().add(
                new javax.swing.JScrollPane(text),
                java.awt.BorderLayout.CENTER);

        new FileDrop(System.out, text, /*dragBorder,*/ new FileDrop.Listener() {
            public void filesDropped(java.io.File[] files) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        text.append( files[i].getCanonicalPath() + "\n"  );

                      if(files[i].toString().contains(".txt")){
                          addMessage(".TXT file found!");
                          readTxtFile(files[i].getCanonicalPath());
                      }
                    }   // end try
                    catch (java.io.IOException e) {
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

        frame.setBounds(0, 0, 450, 300);
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }

    private void readTxtFile(String yourFile) {
        String[] arr= null;
        List<String> itemsSchool = new ArrayList<String>();
        try {
            FileInputStream fstream_school = new FileInputStream(yourFile);
            DataInputStream data_input = new DataInputStream(fstream_school);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
            String str_line;
            while ((str_line = buffer.readLine()) != null)
            {
                str_line = str_line.trim();
                if ((str_line.length()!=0))
                {
                    addMessage("IMG: " + str_line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMessage(String message) {
        tableScrollable.add(new Label(message, skin)).left();
        tableScrollable.row();
        scrollPane.layout();
        scrollPane.setScrollPercentY(100);
        scrollPane.setScrollPercentX(0);
        scrollPane.updateVisualScroll();
    }

    private void initTables() {
        tableMain = new Table();
        tableMain.setFillParent(true);
        tableMain.setDebug(tableDebug);
        tableMain.top().left().padTop(10f);


        tableInner = new Table(skin);
        tableInner.setDebug(tableDebug);
        tableInner.top();
        ///TOP

        tableInner.add().width(230f).height(145f).padLeft(10f);
        tableInner.add().width(230f).height(145f).padLeft(45f);
        tableInner.add(new Image(new Texture("buttons/horizontalRectangleButton.png"))).width(95f).height(145f).padLeft(65f);
        tableInner.row();
        //MID
        tableInner.add().expandX().height(140f).colspan(3).padTop(35f);
        tableInner.row();
        // ScrollingTable
        tableScrollable = new Table();
        tableScrollable.setDebug(false);
        tableScrollable.top().left();

        scrollPane = new ScrollPane(tableScrollable);
        scrollPane.setOverscroll(false, false);

        //BOT
        tableInner.add(scrollPane).width(505f).height(135f).colspan(2).padTop(28f);
        tableInner.add(new Image(new Texture("buttons/smallButton.png"))).width(100f).height(55f).padTop(28f).padLeft(30f);

        tableMain.add(tableInner);
        stage.addActor(tableMain);
    }


    private void initAtlasSkin() {
        skin = new Skin(Gdx.files.internal("uiskin.json"),
                new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
    }

    private void initBackground() {
        imgBackground = new Image(new Texture("background.png"));
        stage.addActor(imgBackground);
    }

    @Override
    public void show() {
    }

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
