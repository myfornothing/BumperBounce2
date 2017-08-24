package com.fornothing.projects.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fornothing.projects.utilities.Constants;
import com.fornothing.projects.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Constants.APP_TITLE + " v" + Constants.APP_VERSION;
		config.width = Constants.APP_DESKTOP_WIDTH;
		config.height = Constants.APP_DESKTOP_HEIGHT;
		config.backgroundFPS = Constants.APP_FPS;
		config.foregroundFPS = Constants.APP_FPS;

		new LwjglApplication(new MainClass(), config);
	}
}
