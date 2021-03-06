package com.fornothing.projects.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public final class GameCamera extends Camera {

    private OrthographicCamera gamecam;
    private FitViewport viewport;

    public GameCamera(int width, int height) {
        gamecam = new OrthographicCamera();
        viewport = new FitViewport(width, height, gamecam);
        viewport.apply();
        gamecam.position.set(width / 2, height / 2, 0);
        gamecam.update();
    }

    public Vector2 getInputInGameWorld() {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(),
                Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = gamecam.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }

    public void update(int width, int height) {
        viewport.update(width, height);
    }

    public Matrix4 combined() {
        return gamecam.combined;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public FitViewport getViewport() {
        return viewport;
    }

    @Override
    public void update() {
    }

    @Override
    public void update(boolean updateFrustum) {
    }
}