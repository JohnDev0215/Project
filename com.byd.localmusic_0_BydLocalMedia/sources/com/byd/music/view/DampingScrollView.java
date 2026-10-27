package com.byd.music.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DampingScrollView extends ScrollView {
    private View childView;
    private int currentX;
    private int currentY;
    private int deltaY;
    private boolean isScroll;
    private boolean isVertical;
    private int mMaxHeight;
    private float moveHeight;
    private int previousY;
    private int startX;
    private int startY;
    private int top;
    private Rect topRect;

    public DampingScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.previousY = 0;
        this.startY = 0;
        this.currentY = 0;
        this.deltaY = 0;
        this.startX = 0;
        this.currentX = 0;
        this.topRect = new Rect();
        this.top = 0;
        this.isVertical = true;
        this.isScroll = true;
        setOverScrollMode(2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            this.childView = childAt;
            this.top = this.isVertical ? childAt.getTop() : childAt.getLeft();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0091, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0138  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.DampingScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private void upDownMoveAnimation() {
        TranslateAnimation translateAnimation;
        if (this.isVertical) {
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.childView.getTop(), this.top);
        } else {
            translateAnimation = new TranslateAnimation(this.childView.getLeft(), this.top, 0.0f, 0.0f);
        }
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.childView.setAnimation(translateAnimation);
    }

    public void setScroll(boolean z) {
        this.isScroll = z;
    }

    public void setVertical(boolean z) {
        this.isVertical = z;
    }

    public void setMaxHeight(int i) {
        this.mMaxHeight = i;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.mMaxHeight;
        if (i3 > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
