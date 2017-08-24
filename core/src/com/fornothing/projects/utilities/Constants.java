package com.fornothing.projects.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public final class Constants {
    public static final String TAG = Constants.class.getName();
    //App info
    public static final String APP_TITLE = "Bumper Bounce 2";
    public static final double APP_VERSION = 1.01;
    public static final int APP_DESKTOP_WIDTH = 900;
    public static final int APP_DESKTOP_HEIGHT = 1500;
    public static final int APP_FPS = 60;
    public static final int V_WIDTH = 420;
    public static final int V_HEIGHT = 720;

    //Ball info
    public static final Color BALL_COLOR = Color.GREEN;
    public static final float BALL_SPEED = -1000f;
    public static final float MAX_SPEED = -2500.0f;
    public static final float BALL_BOUNCE_EXCEL = 1.02f;
    public static final float BALL_SCALE = .12f;
    public static final float RADIUS_FACTOR = 1.0f / 30;
    public static final float RADIUS_GROWTH_RATE = 1.5f;
    public static final Vector2 BALL_ACCELLERATION = new Vector2(0, 0);

    //Paddle info
    public static final Vector2 PADDLE_ACCELLERATION = new Vector2(0, 0);
    public static final float DRAG = 1.0f;
    public static final float PADDLE_SPEED = 5000f;
    public static final float PADDLE_SCALE = .33f;
    public static final float PADDLE_HEIGHT_SCALE = .1f;
    public static final float FOLLOW_MULTIPLIER = 20.0f;
    public static final float MAX_ADD_ANGLE_BOUNCE = 40f;
    public static final float MIN_ADD_ANGLE_BOUNCE = 1.0f;

}
