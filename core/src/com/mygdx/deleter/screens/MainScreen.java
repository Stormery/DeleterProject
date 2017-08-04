package com.mygdx.deleter.screens;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.deleter.DeleterProject;

public class MainScreen extends AbstractScreen {

	static Skin skin;

	Image imgBackground;

	// Tables
	private boolean tableDebug = false;

	private Table tableMain;
	private Table tableInner;
	private static Table tableScrollable;
	private static ScrollPane scrollPane;

	public boolean filesLoaded;
	public static boolean textLoaded;
	private static List<String> fotoListFromTXT;
	private static List<String> inputFilesList;

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
		inputFilesList = new ArrayList<String>();
		initAtlasSkin();
		initBackground();
		initTables();
		// initDragnDrop(); // potrzebuje przycisk wlaczajacy to

	}

	public static void initDragNDrop(Lwjgl3ApplicationConfiguration config) {

		config.setWindowListener(new Lwjgl3WindowAdapter() {
			@Override
			public void filesDropped(String[] files) {
				for (String file : files) {
					Gdx.app.log("FileDropped: ", file);

					// If found TXT file with list
					if (file.toString().contains(".txt")) {
						addMessage(".TXT file found!");
						readTxtFile(file);
						textLoaded = true;
					}
					// Add each drop to arrayList
					inputFilesList.add(file);
				}
				// TODO przerzucic do przycisku
				makeTXTFile(inputFilesList);
			}
		});
	}

	private static void makeTXTFile(List<String> name) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("PhotoList.txt");
			for (String str : name) {
				writer.write(str);
				writer.write("\r\n");
			}
			addMessage("PhotoList.txt file made");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void readTxtFile(String yourFile) {
		fotoListFromTXT = new ArrayList<String>();
		try {
			FileInputStream fstream_school = new FileInputStream(yourFile);
			DataInputStream data_input = new DataInputStream(fstream_school);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
			String str_line;
			while ((str_line = buffer.readLine()) != null) {
				str_line = str_line.trim();
				if ((str_line.length() != 0)) {
					addMessage("IMG: " + str_line);
					fotoListFromTXT.add(str_line);
				}
			}
			for (String listaZdjec : fotoListFromTXT) {
				Gdx.app.log("from TXT: ", listaZdjec);
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void addMessage(String message) {
		skin.getFont("default-font").getData().setScale(0.8f);
		skin.getFont("default-font").setColor(0, 0, 0, 1);
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
		/// TOP

		tableInner.add().width(230f).height(145f).padLeft(10f);
		tableInner.add().width(230f).height(145f).padLeft(45f);
		tableInner.add(new Image(new Texture("buttons/horizontalRectangleButton.png"))).width(95f).height(145f)
				.padLeft(65f);
		tableInner.row();
		// MID
		tableInner.add().expandX().height(140f).colspan(3).padTop(35f);
		tableInner.row();
		// ScrollingTable
		tableScrollable = new Table();
		tableScrollable.setDebug(false);
		tableScrollable.top().left();

		scrollPane = new ScrollPane(tableScrollable);
		scrollPane.setOverscroll(false, false);

		// BOT
		tableInner.add(scrollPane).width(505f).height(135f).colspan(2).padTop(28f);
		tableInner.add(new Image(new Texture("buttons/smallButton.png"))).width(100f).height(55f).padTop(28f)
				.padLeft(30f); //

		tableMain.add(tableInner);
		stage.addActor(tableMain);
	}

	private void initAtlasSkin() {
		skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
	}

	private void initBackground() {
		imgBackground = new Image(new Texture("Background.png"));
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
