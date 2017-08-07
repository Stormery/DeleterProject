package com.mygdx.deleter;

import com.badlogic.gdx.Game;
import com.mygdx.deleter.screens.MainScreen;

public class DeleterProject extends Game {



	public final static String APP_NAME = "PhotoDeleter by Stormery";
	public final static String VERSION = "ver 0.02";

	public final static int WIDHT = 700;
	public final static int HEIGHT = 500;


	@Override
	public void create () {
		init();
		this.setScreen(new MainScreen(this));

	}

	private void init() {

	}
}
