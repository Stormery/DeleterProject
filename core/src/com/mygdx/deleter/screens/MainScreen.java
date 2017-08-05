package com.mygdx.deleter.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.deleter.DeleterProject;
import com.mygdx.deleter.ui.ButtonStart;
import com.mygdx.deleter.ui.ButtonTxtMake;
import com.mygdx.deleter.ui.DeleteFilesClass;
import com.mygdx.deleter.ui.IClickCallback;
import com.mygdx.deleter.ui.TxtFileHandler;

public class MainScreen extends AbstractScreen {

	public static Skin skin;

	Image imgBackground;

	public static boolean filesLoaded;
	private static String fileType;
	public static boolean textLoaded;
	private static boolean itsPhoto = false;
	
	public static List<String> inputFilesList;
	public static List<String> fotoListFromTXT;
	
	public static ButtonStart startButton;
	public static ButtonTxtMake buttonTxtStart;
	
	
	
	
	public MainScreen(DeleterProject dp) {
		super();
		init();
		// test();
	}

	private void test() {
		for (int i = 1; i < 20; i++) {
			TableContainer.addMessageLeftPannel("DCM000" + i);
		}
	}

	protected void init() {
		inputFilesList = new ArrayList<String>();
		fotoListFromTXT = new ArrayList<String>();
		
		initAtlasSkin();
		initBackground();
		initButtons();
		TableContainer.initTables();

	}

	private void initButtons() {

		 startButton = new ButtonStart(new IClickCallback() {
			
			@Override
			public void onClick() {
				System.err.println("click start");	
				if(filesLoaded){
				DeleteFilesClass.deleteFiles();
				}else{
					TableContainer.addMessageBottomPannel("~NO FILES FOUND\n~FIRST UPLOAD FILES");
				}
			}
		});
		
		 buttonTxtStart = new ButtonTxtMake(new IClickCallback() {
			
			@Override
			public void onClick() {
				if(filesLoaded){
					TxtFileHandler.makeTXTFile(inputFilesList);
				}else{
					TableContainer.addMessageBottomPannel("~NO POSITIONS TO MAKE A LIST! \n~FIRST UPLOAD FILES");
				}
			}
		});
			 	 
		}
	

	public static void initDragNDrop(Lwjgl3ApplicationConfiguration config) {
		 
		 
		config.setWindowListener(new Lwjgl3WindowAdapter() {
			@Override
			public void filesDropped(String[] files) {
				for (String file : files) {
					Gdx.app.log("FileDropped: ", file);
					//Shows only name of file, not full path
					TableContainer.addMessageLeftPannel(file.substring(file.lastIndexOf("\\")+1));
					// If found TXT file with list
					if (file.toString().contains(".txt")) {
						TableContainer.	addMessageBottomPannel("Txt file loaded");
						TxtFileHandler.readTxtFile(file);
						textLoaded = true;
						itsPhoto = false;
					}else {
						fileType = file.substring(file.lastIndexOf(".")+1);
						filesLoaded = true;
						itsPhoto = true;
					}
					// Add each drop to arrayList
					inputFilesList.add(file);
				}
				if(itsPhoto){
					TableContainer.addMessageBottomPannel("Files Loaded \nFile type detected: ." + fileType);
					itsPhoto = false;
				}
				
			}
		});
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
