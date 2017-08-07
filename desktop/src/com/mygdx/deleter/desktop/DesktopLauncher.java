package com.mygdx.deleter.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.deleter.DeleterProject;
import com.mygdx.deleter.screens.MainScreen;

public class DesktopLauncher {
	static Lwjgl3ApplicationConfiguration config;
	public static void main (String[] arg) {
		 config = new Lwjgl3ApplicationConfiguration();

		config.setWindowedMode(DeleterProject.WIDHT, DeleterProject.HEIGHT);
		config.setResizable(false);
		config.setTitle(DeleterProject.APP_NAME);
		MainScreen.initDragNDrop(config);
		new Lwjgl3Application(new DeleterProject(), config);
	}

	
}
//full path from last "\" to "."  and from that -4 to get onliv