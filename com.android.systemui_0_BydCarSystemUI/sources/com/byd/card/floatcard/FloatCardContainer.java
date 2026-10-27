package com.byd.card.floatcard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public class FloatCardContainer {
    private static final String TAG = "CardContainer";
    private boolean isRemove = false;
    private final Context mContext;
    private AnimatorSet mHideAnimatorSet;
    private final WindowManager.LayoutParams mLayoutParams;
    private AnimatorSet mShowAnimatorSet;
    private View mView;
    private final WindowManager mWindowManager;
    OnOutsideListener onOutsideListener;

    public interface OnOutsideListener {
        void onOutside(boolean out);
    }

    public void setOnOutsideListener(OnOutsideListener onOutsideListener) {
        this.onOutsideListener = onOutsideListener;
    }

    public FloatCardContainer(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.format = 1;
        layoutParams.flags = 262408;
        layoutParams.windowAnimations = 0;
        layoutParams.setTitle("Card Container");
        layoutParams.width = -1;
    }

    public void setSize(int width, int height) {
        this.mLayoutParams.width = width;
        this.mLayoutParams.height = height;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setLayoutParamsFlag() {
        this.mLayoutParams.flags = 262144;
    }

    public void setLocation(int xOffset, int yOffset) {
        this.mLayoutParams.x = xOffset;
        this.mLayoutParams.y = yOffset;
    }

    public View getView() {
        return this.mView;
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        this.mLayoutParams.gravity = gravity;
        this.mLayoutParams.x = xOffset;
        this.mLayoutParams.y = yOffset;
    }

    public void init() {
        this.mLayoutParams.type = 2002;
        this.mWindowManager.addView(this.mView, this.mLayoutParams);
        this.mView.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.floatcard.FloatCardContainer.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (FloatCardContainer.this.onOutsideListener != null) {
                    FloatCardContainer.this.onOutsideListener.onOutside(event.getAction() == 4);
                }
                return false;
            }
        });
    }

    public void updateLayout(int width, int height) {
        if (this.isRemove) {
            return;
        }
        this.mLayoutParams.width = width;
        this.mLayoutParams.height = height;
        this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
    }

    public void dismiss() {
        this.isRemove = true;
        this.mWindowManager.removeView(this.mView);
    }

    public void hide() {
        View view = this.mView;
        if (view != null && view.getVisibility() == 0) {
            this.mView.setVisibility(8);
        }
    }

    public void show() {
        View view = this.mView;
        if (view == null) {
            Log.w(TAG, "show mView is null");
            return;
        }
        view.setVisibility(0);
        this.mView.setAlpha(1.0f);
        this.mView.setTranslationY(0.0f);
    }

    public void animShow() {
        AnimatorSet animatorSet = this.mHideAnimatorSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mHideAnimatorSet.cancel();
        }
        View view = this.mView;
        if (view == null || view.getVisibility() == 0) {
            Log.w(TAG, "animShow mView is VISIBLE");
            return;
        }
        this.mView.setVisibility(0);
        AnimatorSet animatorSet2 = this.mShowAnimatorSet;
        if (animatorSet2 == null) {
            AnimatorSet animatorSet3 = new AnimatorSet();
            this.mShowAnimatorSet = animatorSet3;
            animatorSet3.playTogether(ObjectAnimator.ofFloat(this.mView, "translationY", r4.getHeight(), 0.0f), ObjectAnimator.ofFloat(this.mView, "alpha", 0.0f, 1.0f));
        } else if (!animatorSet2.isRunning()) {
            this.mShowAnimatorSet.start();
        }
        this.mShowAnimatorSet.setDuration(200L).start();
    }

    public void animHide() {
        AnimatorSet animatorSet = this.mShowAnimatorSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mShowAnimatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mHideAnimatorSet;
        if (animatorSet2 == null) {
            AnimatorSet animatorSet3 = new AnimatorSet();
            this.mHideAnimatorSet = animatorSet3;
            animatorSet3.playTogether(ObjectAnimator.ofFloat(this.mView, "translationY", 0.0f, r3.getHeight()), ObjectAnimator.ofFloat(this.mView, "alpha", 1.0f, 0.0f));
            this.mHideAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.byd.card.floatcard.FloatCardContainer.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    FloatCardContainer.this.mView.setVisibility(8);
                }
            });
            this.mHideAnimatorSet.setDuration(200L);
            this.mHideAnimatorSet.start();
            return;
        }
        if (animatorSet2.isRunning()) {
            return;
        }
        this.mHideAnimatorSet.start();
    }
}
