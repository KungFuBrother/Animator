package com.smartown.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tiger on 2016-04-20.
 */
public class WaveLoadingView extends View {

    private int animationDuration = 5000;
    private int waveColor = Color.RED;

    private float progress = 0;

    private Path wavePath = new Path();
    private Path clipPath = new Path();

    private float viewWidth = 0;
    private float waveHeight = 0;
    private float xSpeed = 0;
    private float xLength = 0;


    private ValueAnimator animator;

    private Paint paint;

    public WaveLoadingView(Context context) {
        this(context, null);
    }

    public WaveLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAnimator();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
        waveHeight = viewWidth / 16.0f;
        xSpeed = viewWidth / 64.0f;
        clipPath.addCircle(viewWidth / 2.0f, viewWidth / 2.0f, viewWidth / 2.0f, Path.Direction.CCW);
        start();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(waveColor);
    }

    private void initAnimator() {
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(animationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                xLength = 0;
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipPath(clipPath);
        wavePath.reset();
        xLength += xSpeed;

        for (int i = 0; i < viewWidth; i++) {
            wavePath.lineTo(i, viewWidth - (float) (viewWidth * progress + waveHeight * Math.sin((i + xLength) / viewWidth * 2 * Math.PI)));
        }
        wavePath.lineTo(viewWidth, viewWidth);
        wavePath.lineTo(0, viewWidth);
        wavePath.close();

        canvas.drawPath(wavePath, paint);
    }

    public void start() {
        animator.start();
    }

    public void stop() {
        animator.cancel();
    }

}
