package com.smartown.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tiger on 2016-04-20.
 */
public class WaveLoadingView extends View {

    private int animationDuration = 5000;
    private int primaryColor = Color.BLUE;
    private int secondColor = Color.blue(100);

    private float progress = 0;

    private Path primaryPath = new Path();
    private Path secondPath = new Path();

    private float viewWidth = 0;
    private float waveHeight = 0;

    private ValueAnimator animator;

    private Paint paint;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

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
        waveHeight = viewWidth / 8.0f;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
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
    }

    int w = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        primaryPath.reset();
        secondPath.reset();
        w += 4;

        for (int i = 0; i < viewWidth; i++) {
            primaryPath.lineTo(i, (float) (viewWidth * progress + waveHeight * Math.sin((float) (i + w) / viewWidth * 2 * Math.PI)));
            secondPath.lineTo(i, (float) (viewWidth * progress - waveHeight * Math.sin((float) (i + w) / viewWidth * 2 * Math.PI)));
        }
        primaryPath.lineTo(viewWidth, viewWidth);
        primaryPath.lineTo(0, viewWidth);
        primaryPath.close();

        secondPath.lineTo(viewWidth, viewWidth);
        secondPath.lineTo(0, viewWidth);
        secondPath.close();
        paint.setColor(primaryColor);
        canvas.drawPath(primaryPath, paint);
        paint.setColor(secondColor);
        canvas.drawPath(secondPath, paint);
        if (!animator.isRunning()) {
            start();
        }
    }


    public void start() {
        animator.start();
    }

}
