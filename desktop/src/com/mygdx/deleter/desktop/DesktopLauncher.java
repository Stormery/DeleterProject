package com.mygdx.deleter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.deleter.DeleterProject;
import com.mygdx.deleter.screens.MainScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DeleterProject.WIDHT;
		config.height = DeleterProject.HEIGHT;
		config.resizable = false;
		config.title = DeleterProject.APP_NAME;
		new LwjglApplication(new DeleterProject(), config);
	}
}
