package com.fornothing.projects.utilities;

import com.badlogic.gdx.ScreenAdapter;
import com.fornothing.projects.entities.Ball;
import com.fornothing.projects.entities.Paddle;

import java.util.Random;

import static com.fornothing.projects.utilities.Constants.BALL_BOUNCE_EXCEL;
import static com.fornothing.projects.utilities.Constants.MAX_ADD_ANGLE_BOUNCE;
import static com.fornothing.projects.utilities.Constants.MIN_ADD_ANGLE_BOUNCE;

public class BallBounce extends ScreenAdapter {

    private static Random RANDOM;
    private static float BALL_RANDOM_BOUNCE;

    public BallBounce() {  }

    public static void updateBounce(Ball ball, Paddle paddle, float delta) {

        if (ball.getCollisionRect().collidesWith(paddle.getCollisionRect())) {
            if ((ball.ballPosition.x + ball.ballScaled /2 <=  //left side; right angle
                    paddle.paddlePosition.x + paddle.paddleWidthScaled /2) &&
                    ball.ballVelocity.x > 0) {
                ball.ballVelocity.x = -ball.ballVelocity.x + -BALL_RANDOM_BOUNCE;
                ball.ballVelocity.y = -ball.ballVelocity.y * BALL_BOUNCE_EXCEL;
            }else if ((ball.ballPosition.x + ball.ballScaled /2 <=  //left side; left angle
                    paddle.paddlePosition.x + paddle.paddleWidthScaled /2) &&
                    ball.ballVelocity.x < 0) {
                ball.ballVelocity.x = ball.ballVelocity.x + BALL_RANDOM_BOUNCE;
                ball.ballVelocity.y = -ball.ballVelocity.y * BALL_BOUNCE_EXCEL;
            }else if ((ball.ballPosition.x + ball.ballScaled /2 >  //right side; right angle
                    paddle.paddlePosition.x + paddle.paddleWidthScaled /2) &&
                    ball.ballVelocity.x > 0) {
                ball.ballVelocity.x = ball.ballVelocity.x + BALL_RANDOM_BOUNCE;
                ball.ballVelocity.y = -ball.ballVelocity.y * BALL_BOUNCE_EXCEL;
            }else if ((ball.ballPosition.x + ball.ballScaled /2 >  //right side; left angle
                    paddle.paddlePosition.x + paddle.paddleWidthScaled /2) &&
                    ball.ballVelocity.x < 0) {
                ball.ballVelocity.x = -ball.ballVelocity.x + -BALL_RANDOM_BOUNCE;
                ball.ballVelocity.y = -ball.ballVelocity.y * BALL_BOUNCE_EXCEL;
            }
        }
        //BALL DEAD
        if (ball.ballPosition.y < paddle.paddlePosition.y + paddle.paddleHeightScaled /3){
            ball.ballVelocity.x = 0;
            ball.ballVelocity.y = -800;
        }
    }

    @Override
    public void show() {
        BALL_RANDOM_BOUNCE = RANDOM.nextFloat() *
                (MAX_ADD_ANGLE_BOUNCE - MIN_ADD_ANGLE_BOUNCE) + MIN_ADD_ANGLE_BOUNCE;

    }

}
