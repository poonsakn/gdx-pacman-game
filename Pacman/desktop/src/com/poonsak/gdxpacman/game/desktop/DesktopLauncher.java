package com.poonsak.gdxpacman.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.poonsak.gdxpacman.game.PacmanDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 80;
		config.height = 80;
		new LwjglApplication(new PacmanDemo(), config);
	}
}
