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

import static com.fornothing.projects.utilities.Constants.FOLLOW_MULTIPLIER;
import static com.fornothing.projects.utilities.Constants.PADDLE_HEIGHT_SCALE;
import static com.fornothing.projects.utilities.Constants.PADDLE_SCALE;
import static com.fornothing.projects.utilities.Constants.PADDLE_SPEED;

public class Paddle extends InputAdapter {
    public static final String TAG = Paddle.class.getName();

    Viewport viewport;
    public Vector2 paddlePosition;
    public Vector2 paddleVelocity;
    public Texture paddleImage;
    public Sprite paddle;
    Vector2 targetPosition;
    boolean following = false;
    public float paddleWidthScaled, paddleHeightScaled;
    private CollisionRect rect;

    public Paddle(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        paddleImage = new Texture("Images/bluePaddle.png");
        paddle = new Sprite(paddleImage);
        paddleWidthScaled = paddle.getWidth() * PADDLE_SCALE;
        paddleHeightScaled = paddle.getHeight() * PADDLE_SCALE;
        paddlePosition = new Vector2(viewport.getWorldWidth() / 2 - paddleWidthScaled / 2,
                viewport.getWorldHeight() * PADDLE_HEIGHT_SCALE);
        paddleVelocity = new Vector2();

        this.rect = new CollisionRect(
                paddlePosition.x + paddleWidthScaled / 2,
                paddlePosition.y,
                paddleWidthScaled,
                paddleHeightScaled);
    }

    public void update(float delta) {
        paddlePosition.mulAdd(paddleVelocity, delta);
        playerMovement(delta, paddleWidthScaled / 2);
        wallCollisionPaddle(paddleWidthScaled, viewport.getWorldWidth());
        rect.move(paddlePosition.x, paddlePosition.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(paddle, paddlePosition.x, paddlePosition.y,
                paddleWidthScaled, paddleHeightScaled);
    }

    public void wallCollisionPaddle(float paddleWidth, float viewportWidth) {
        if (paddlePosition.x < 0) {
            paddlePosition.x = 0;
            paddleVelocity.x = 0;
        }
        if (paddlePosition.x + paddleWidth > viewportWidth) {
            paddlePosition.x = viewportWidth - paddleWidth;
            paddleVelocity.x = 0;
        }
    }

    public void playerMovement(float delta, float paddleCenter) {
        //touch movement
        if (following) {
            Vector2 followVector = new Vector2((targetPosition.x - paddleCenter) - paddlePosition.x, paddlePosition.y);
            paddleVelocity.x = FOLLOW_MULTIPLIER * followVector.x;
        }
        paddlePosition.x += delta * paddleVelocity.x;
        //keyboard movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            paddleVelocity.x -= delta * PADDLE_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            paddleVelocity.x += delta * PADDLE_SPEED;
        } else {
            paddleVelocity.x = 0;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        targetPosition = worldClick;
        following = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        following = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (following) {
            targetPosition = viewport.unproject(new Vector2(screenX, screenY));
        }
        return true;
    }

    public CollisionRect getCollisionRect() {
        return rect;
    }

}



//    public void render(ShapeRenderer renderer) {
//        renderer.setColor(BALL_COLOR);
//        renderer.set(ShapeRenderer.ShapeType.Filled);
//        renderer.rectLine(
//                paddlePosition.x + paddleWidthScaled /2,
//                paddlePosition.y,
//                paddlePosition.x + paddleWidthScaled /2,
//                paddlePosition.y + paddleHeightScaled,
//                paddleWidthScaled
//        );
//    }
