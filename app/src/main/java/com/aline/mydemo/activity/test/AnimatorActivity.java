package com.aline.mydemo.activity.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

public class AnimatorActivity extends AppCompatActivity {

    private View animView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mContext = AnimatorActivity.this;
        animView = this.findViewById(R.id.view_anim);
        this.findViewById(R.id.bt_show_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_translate));

            }
        });
        this.findViewById(R.id.bt_show_object_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showObjectAnim();
            }
        });

        //引用属性动画
        showXmlObjectAnim();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);

    }

    private void showXmlObjectAnim() {
        Animator animator = AnimatorInflater.loadAnimator(mContext, R.animator.sacle);
        animator.setTarget(animView);
        animator.start();
    }

    /**
     * 属性动画
     */
    private void showObjectAnim() {
        ObjectAnimator.ofFloat(animView, "translationX", 0, 300).setDuration(1000).start();
    }


    public void showTranslate(View view) {
        View animView = this.findViewById(R.id.view_anim);
        animView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_translate));
    }
}
