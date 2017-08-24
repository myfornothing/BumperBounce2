package com.fornothing.projects;

import com.badlogic.gdx.Game;
import com.fornothing.projects.screens.GameScreen1;

public class MainClass extends Game {
	public static final String TAG = MainClass.class.getName();


	@Override
	public void create () {
		showGameScreen1();
	}

    private void showGameScreen1() {
        setScreen(new GameScreen1(this));
    }

}
