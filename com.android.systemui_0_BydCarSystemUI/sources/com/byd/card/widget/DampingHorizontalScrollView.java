package com.byd.card.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes.dex */
public class DampingHorizontalScrollView extends ScrollView {
    private View childView;
    private int currentX;
    private int currentY;
    private int deltaY;
    private float moveHeight;
    private int previousY;
    private int startX;
    private int startY;
    private Rect topRect;

    public DampingHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.previousY = 0;
        this.startY = 0;
        this.currentY = 0;
        this.deltaY = 0;
        this.startX = 0;
        this.currentX = 0;
        this.topRect = new Rect();
        setOverScrollMode(2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            this.childView = getChildAt(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.childView == null) {
            return super.dispatchTouchEvent(event);
        }
        int action = event.getAction();
        float f = 0.0f;
        if (action == 0) {
            this.startY = (int) event.getX();
            this.startX = (int) event.getY();
            this.previousY = this.startY;
            this.topRect.set(this.childView.getLeft(), this.childView.getTop(), this.childView.getRight(), this.childView.getBottom());
            this.moveHeight = 0.0f;
        } else if (action == 1) {
            if (!this.topRect.isEmpty()) {
                upDownMoveAnimation();
                this.childView.layout(this.topRect.left, this.topRect.top, this.topRect.right, this.topRect.bottom);
            }
            this.startY = 0;
            this.currentY = 0;
            this.topRect.setEmpty();
        } else if (action == 2) {
            this.currentY = (int) event.getX();
            int y = (int) event.getY();
            this.currentX = y;
            int i = this.currentY;
            this.deltaY = i - this.previousY;
            this.previousY = i;
            if (Math.abs(y - this.startX) <= Math.abs(this.currentY - this.startY) && Math.abs(this.currentY - this.startY) >= 80 && ((!this.childView.canScrollHorizontally(-1) && this.currentY - this.startY > 0) || (!this.childView.canScrollHorizontally(1) && this.currentY - this.startY < 0))) {
                float f2 = this.currentY - this.startY;
                if (f2 < 0.0f) {
                    f2 *= -1.0f;
                }
                float width = getWidth();
                if (width == 0.0f) {
                    f = 0.5f;
                } else if (f2 <= width) {
                    f = (width - f2) / width;
                }
                if (this.currentY - this.startY < 0) {
                    f = 1.0f - f;
                }
                this.moveHeight += this.deltaY * ((float) (((double) ((float) (((double) f) * 0.25d))) + 0.25d));
                this.childView.layout((int) (this.topRect.left + this.moveHeight), this.topRect.top, (int) (this.topRect.right + this.moveHeight), this.topRect.bottom);
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void upDownMoveAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(this.childView.getLeft(), this.topRect.left, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.childView.setAnimation(translateAnimation);
    }
}
