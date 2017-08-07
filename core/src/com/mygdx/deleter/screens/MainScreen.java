package com.mygdx.deleter.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.deleter.DeleterProject;
import com.mygdx.deleter.TableContainer;
import com.mygdx.deleter.buttons.ButtonReset;
import com.mygdx.deleter.buttons.ButtonStart;
import com.mygdx.deleter.buttons.ButtonTxtMake;
import com.mygdx.deleter.ui.DeleteFilesClass;
import com.mygdx.deleter.ui.IClickCallback;
import com.mygdx.deleter.ui.TxtFileHandler;

public class MainScreen extends AbstractScreen {
	
	public final static String HIDDEN_PREFS = "devstormery.pl.hidden";
	public final static String HIDDEN_COUNTING = "devstormery.pl.hidden.counting";
	private static Preferences prefs;
	private static int deleteCounting;
	

	public static Skin skin;

	public Image imgBackground;

	public static boolean filesLoaded;
	public static boolean textLoaded;
	private static boolean itsPhoto = false;
	private static String fileType;
	
	public static List<String> inputFilesList;
	public static List<String> fotoListFromTXT;
	
	public static ButtonStart startButton;
	public static ButtonTxtMake buttonTxtStart;
	public static ButtonReset buttonReset;
	
	
	
	
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
	
		updateHidden();
		initAtlasSkin();
		initBackground();
		initButtons();
		TableContainer.initTables();

	}
	private void updateHidden() {
		prefs = Gdx.app.getPreferences(HIDDEN_PREFS);
		deleteCounting = prefs.getInteger(HIDDEN_COUNTING);		
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
			
		 buttonReset = new ButtonReset(new IClickCallback() {
			
			@Override
			public void onClick() {
				popupReset();
			}

			
		});
		
		}
	private void popupReset() {
		Dialog dialog = new Dialog("Warning", MainScreen.skin, "dialog") {
		    public void result(Object obj) {
		        System.out.println("result "+obj);
		        resetCurrentState();
		    }
		};
		dialog.text("Are you sure you want reset current state?");
		dialog.button("Yes", true); //sends "true" as the result
		dialog.button("No", false);  //sends "false" as the result
		dialog.key(Keys.ENTER, true); //sends "true" when the ENTER key is pressed
		dialog.show(MainScreen.stage);
				
	}
	public static void resetCurrentState() {
		TableContainer.tableLeftScrollable.clearChildren();
		TableContainer.tableBottomScrollable.clearChildren();
		inputFilesList.clear();
		fotoListFromTXT.clear();
		DeleteFilesClass.setAmountOfDelete(0);
		DeleteFilesClass.setAmountOfSurvive(0);
		
		filesLoaded = false;
		textLoaded = false;
		itsPhoto = false;
		
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
					hidden(file);					
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
	
	private static void hidden(String file) {
		if(file.toString().contains("devstormerycookie.txt")){
				TableContainer.addMessageBottomPannel("Hi Stormery");
				new File(file).delete();
				TableContainer.addMessageBottomPannel(DeleterProject.VERSION);
				TableContainer.addMessageBottomPannel("Jak do tej pory usunieto: " + prefs.getInteger(HIDDEN_COUNTING ));
				
		}
		if(file.toString().contains("devstormeryreset.txt")){
			TableContainer.addMessageBottomPannel("Reset licznika");
			prefs.putInteger(HIDDEN_COUNTING, 0);
			TableContainer.addMessageBottomPannel(":" + prefs.getInteger(HIDDEN_COUNTING));
			prefs.flush();
			new File(file).delete();
		}
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
	
	///Getter Setter
	public int getDeleteCounting() {
		return deleteCounting;
	}

	public static void setDeleteCounting(int addAmount) {
		deleteCounting += addAmount;
		prefs.putInteger(HIDDEN_COUNTING, deleteCounting);
		prefs.flush();
		
		//System.err.println(prefs.getInteger(HIDDEN_COUNTING));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
	}
}
