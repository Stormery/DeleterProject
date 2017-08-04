package com.mygdx.deleter.screens;

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
import com.mygdx.deleter.ui.TxtFileHandler;

public class MainScreen extends AbstractScreen {

	static Skin skin;

	Image imgBackground;

	// Tables
	private boolean tableDebug = false;

	private Table tableMain;
	private Table tableInner;
	private Table tableMid;
	private static Table tableLeftScrollable;
	private static Table tableBottomScrollable;
	
	
	private static ScrollPane scrollPaneLeft;
	private static ScrollPane scrollPaneBottom;

	public static boolean filesLoaded;
	private static String fileType;
	public static boolean textLoaded;
	
	private static List<String> inputFilesList;
	public static List<String> fotoListFromTXT;
	public MainScreen(DeleterProject dp) {
		super();
		init();
		// test();
	}

	private void test() {
		for (int i = 1; i < 20; i++) {
			addMessageLeftPannel("DCM000" + i);
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
					//Shows only name of file, not full path
					addMessageLeftPannel(file.substring(file.lastIndexOf("\\")+1));
					// If found TXT file with list
					if (file.toString().contains(".txt")) {
						addMessageBottomPannel("Txt file loaded");
						TxtFileHandler.readTxtFile(file);
						textLoaded = true;
					}else {
						fileType = file.substring(file.lastIndexOf(".")+1);
						filesLoaded = true;
					}
					// Add each drop to arrayList
					inputFilesList.add(file);
				}
				addMessageBottomPannel("File type: ." + fileType);
				// TODO przerzucic do przycisku
				TxtFileHandler.makeTXTFile(inputFilesList);
			}
		});
	}

	public static void addMessageLeftPannel(String message) {
		tableLeftScrollable.add(new Label(message, skin)).left();
		tableLeftScrollable.row();
		scrollPaneLeft.layout();
		scrollPaneLeft.setScrollPercentY(100);
		scrollPaneLeft.setScrollPercentX(0);
		scrollPaneLeft.updateVisualScroll(); 
	}
	public static void addMessageBottomPannel(String message) {	
		tableBottomScrollable.add(new Label(message, skin)).left();
		tableBottomScrollable.row();
		scrollPaneBottom.layout();
		scrollPaneBottom.setScrollPercentY(100);
		scrollPaneBottom.setScrollPercentX(0);
		scrollPaneBottom.updateVisualScroll(); 
	}


	private void initTables() {
		tableMain = new Table();
		tableMain.setFillParent(true);
		tableMain.setDebug(tableDebug);
		tableMain.top().left();

		tableInner = new Table(skin);
		tableInner.setDebug(tableDebug);
		tableInner.top();
		
		tableMid = new Table(skin);
		tableMid.setDebug(tableDebug);
		tableMid.top().left();
		// ScrollingTable
				tableLeftScrollable = new Table();
				tableLeftScrollable.setDebug(false);
				tableLeftScrollable.top().left();
				
				tableBottomScrollable = new Table();
				tableBottomScrollable.setDebug(false);
				tableBottomScrollable.top().left();

				scrollPaneLeft = new ScrollPane(tableLeftScrollable);
				scrollPaneLeft.setOverscroll(false, false);
				
				scrollPaneBottom = new ScrollPane(tableBottomScrollable);
				scrollPaneBottom.setOverscroll(false, false);
				/////////////
				
		//Left Collumn
		tableInner.add(scrollPaneLeft).width(153f).height(485f).padLeft(6f).padTop(10f);
		//Mid Collumn
		tableInner.add(tableMid).width(315f).padLeft(10f).left().top();
		tableMid.add("1").height(170f).expandX().padBottom(10f);
		tableMid.row();
		tableMid.add("2").height(145f).expandX().padTop(10f);
		tableMid.row();
		tableMid.add(scrollPaneBottom).height(135f).width(315f).padBottom(5f).padTop(25f);
		
		//Right Collumn
		tableInner.add().width(200f);
		


		tableMain.add(tableInner);
		stage.addActor(tableMain);
	}

	private void initAtlasSkin() {
		skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
		skin.getFont("default-font").getData().setScale(0.8f);
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
