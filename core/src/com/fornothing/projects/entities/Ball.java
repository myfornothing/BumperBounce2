package com.fornothing.projects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fornothing.projects.utilities.CollisionRect;

import static com.fornothing.projects.utilities.Constants.BALL_ACCELLERATION;
import static com.fornothing.projects.utilities.Constants.BALL_SCALE;
import static com.fornothing.projects.utilities.Constants.BALL_SPEED;
import static com.fornothing.projects.utilities.Constants.MAX_SPEED;

public class Ball extends InputAdapter {
    public static final String TAG = Ball.class.getName();

    Viewport viewport;
    public Vector2 ballPosition;
    public Vector2 ballVelocity;
    public float BALL_SPEED_FACTOR = 10f;
    Texture ballImage;
    Sprite ball;
    public float ballScaled;
    private CollisionRect rect;

    public Ball(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        ballImage = new Texture("Images/playerBall.png");
        ball = new Sprite(ballImage);
        ballScaled = ball.getWidth() * BALL_SCALE;
        ballPosition = new Vector2(viewport.getWorldWidth() / 2 - ballScaled / 2,
                viewport.getWorldHeight() * 0.8f);
        ballVelocity = new Vector2(-200, BALL_SPEED);

        this.rect = new CollisionRect(
                ballPosition.x + ballScaled /2,
                ballPosition.y,
                ballScaled,
                ballScaled);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            init();
        }
        ballPosition.mulAdd(ballVelocity, delta);
        ballVelocity.clamp(0, MAX_SPEED);
        ballVelocity.mulAdd(BALL_ACCELLERATION, delta);
        wallCollisionBall(ballScaled, viewport.getWorldWidth(),
                viewport.getWorldHeight());
        rect.move(ballPosition.x, ballPosition.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(ball, ballPosition.x, ballPosition.y, ballScaled, ballScaled);
    }

    private void wallCollisionBall(float radius, float viewportWidth, float viewportHeight) {
        if (ballPosition.x < 0) {
            ballPosition.x = 0;
            ballVelocity.x = -ballVelocity.x;
        }
        if (ballPosition.x + radius > viewportWidth) {
            ballPosition.x = viewportWidth - radius;
            ballVelocity.x = -ballVelocity.x;
        }
        if (ballPosition.y < 0) {
            ballPosition.y = 0;
            ballVelocity.y = -ballVelocity.y;
        }
        if (ballPosition.y + radius > viewportHeight) {
            ballPosition.y = viewportHeight - radius;
            ballVelocity.y = -ballVelocity.y;
        }
    }
    public CollisionRect getCollisionRect () {
        return rect;
    }

}

