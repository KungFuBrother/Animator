package com.smartown.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.main_text);
        button= (Button) findViewById(R.id.main_animate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate();
            }
        });
    }



    private void animate() {

//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
//        valueAnimator.setDuration(2000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator value_animator) {
//                textView.setText(String.valueOf(value_animator.getAnimatedValue()));
//            }
//        });
//        valueAnimator.start();

//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
//        anim.setDuration(300);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator value_animator) {
//                float currentValue = (float) value_animator.getAnimatedValue();
//                Log.d("TAG", "cuurent value is " + currentValue);
//            }
//        });
//        anim.start();

//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 5f, 3f, 10f);
//        anim.setDuration(5000);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator value_animator) {
//                textView.setText(String.valueOf(value_animator.getAnimatedValue()));
//            }
//        });
//        anim.start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f, 1f);
//        animator.setDuration(5000);
//        animator.start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "rotation", 0f, 360f);
//        animator.setDuration(5000);
//        animator.start();

//        float curTranslationX = textView.getTranslationX();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationX", curTranslationX, -500f, curTranslationX);
//        animator.setDuration(5000);
//        animator.start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "scaleY", 1f, 3f, 1f);
//        animator.setDuration(5000);
//        animator.start();

//        ObjectAnimator moveIn = ObjectAnimator.ofFloat(textView, "translationX", -500f, 0f);
//        ObjectAnimator rotate = ObjectAnimator.ofFloat(textView, "rotation", 0f, 360f);
//        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f, 1f);
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(rotate).with(fadeInOut).after(moveIn);
//        animSet.setDuration(5000);
//        animSet.start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "scaleY", 1f, 3f, 1f);
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator value_animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator value_animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator value_animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator value_animator) {
//
//            }
//        });
//        animator.addListener(new AnimatorListenerAdapter() {
//        });

//        Animator animator= AnimatorInflater.loadAnimator(MainActivity.this,R.animator.set_animator);
//        animator.setTarget(textView);
//        animator.start();

        Point point1 = new Point(0, 0);
        Point point2 = new Point(300, 300);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
        anim.setDuration(5000);
        anim.start();

    }

}
