package com.fornothing.projects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.fornothing.projects.MainClass;
import com.fornothing.projects.entities.Ball;
import com.fornothing.projects.entities.Paddle;
import com.fornothing.projects.utilities.BallBounce;


public class GameScreen1 extends ScreenAdapter implements Screen {

    MainClass main;
    OrthographicCamera gameCam;
    ExtendViewport viewport;
    public SpriteBatch batch;
    Sprite background;
    Texture woodFloor;
    Paddle paddle;
    Ball ball;
//    ShapeRenderer renderer;

    public GameScreen1(MainClass main) {
        this.main = main;
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        woodFloor = new Texture("Images/woodfloor2.png");
        background = new Sprite(woodFloor);
        ball = new Ball(viewport);
        paddle = new Paddle(viewport);
        Gdx.input.setInputProcessor(paddle);
//        renderer = new ShapeRenderer();
//        renderer.setAutoShapeType(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        ball.update(delta);
        paddle.update(delta);
        BallBounce.updateBounce(ball, paddle, delta);
//        renderer.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(background, -viewport.getWorldWidth(), -viewport.getWorldHeight() ,
                viewport.getScreenWidth() *2, viewport.getScreenHeight() *2);
        paddle.render(batch);
        ball.render(batch);
        batch.end();
        //TODO DEBUGGING
//        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        paddle.render(renderer);
//        ball.render(renderer);
//        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ball.init();
        paddle.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
//        renderer.dispose();
    }
}
