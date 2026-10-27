package com.byd.automultipletheme.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes.dex */
public class MyScrollView extends ScrollView {
    private boolean animationFinish;
    private View inner;
    private Rect normal;
    private float y;

    public MyScrollView(Context context) {
        super(context);
        this.normal = new Rect();
        this.animationFinish = true;
    }

    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.normal = new Rect();
        this.animationFinish = true;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            this.inner = getChildAt(0);
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.inner == null) {
            return super.onTouchEvent(motionEvent);
        }
        commOnTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    private void commOnTouchEvent(MotionEvent motionEvent) {
        if (this.animationFinish) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.y = motionEvent.getY();
                super.onTouchEvent(motionEvent);
                return;
            }
            if (action == 1) {
                this.y = 0.0f;
                if (isNeedAnimation()) {
                    animation();
                }
                super.onTouchEvent(motionEvent);
                return;
            }
            if (action != 2) {
                return;
            }
            float y = this.y;
            if (y == 0.0f) {
                y = motionEvent.getY();
            }
            float y2 = motionEvent.getY();
            int i = (int) (y - y2);
            this.y = y2;
            if (isNeedMove()) {
                if (this.normal.isEmpty()) {
                    this.normal.set(this.inner.getLeft(), this.inner.getTop(), this.inner.getRight(), this.inner.getBottom());
                }
                View view = this.inner;
                int i2 = i / 2;
                view.layout(view.getLeft(), this.inner.getTop() - i2, this.inner.getRight(), this.inner.getBottom() - i2);
                return;
            }
            super.onTouchEvent(motionEvent);
        }
    }

    private void animation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, this.normal.top - this.inner.getTop());
        translateAnimation.setDuration(200L);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.automultipletheme.widget.MyScrollView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                MyScrollView.this.animationFinish = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MyScrollView.this.inner.clearAnimation();
                MyScrollView.this.inner.layout(MyScrollView.this.normal.left, MyScrollView.this.normal.top, MyScrollView.this.normal.right, MyScrollView.this.normal.bottom);
                MyScrollView.this.normal.setEmpty();
                MyScrollView.this.animationFinish = true;
            }
        });
        this.inner.startAnimation(translateAnimation);
    }

    private boolean isNeedAnimation() {
        return !this.normal.isEmpty();
    }

    private boolean isNeedMove() {
        int measuredHeight = this.inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return scrollY == 0 || scrollY == measuredHeight;
    }
}
