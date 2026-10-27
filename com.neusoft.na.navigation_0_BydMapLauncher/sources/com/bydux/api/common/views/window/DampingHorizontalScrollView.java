package com.bydux.api.common.views.window;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DampingHorizontalScrollView extends ScrollView {
    private View childView;
    private int currentX;
    private int currentY;
    private int deltaY;
    private boolean isScroll;
    private int left;
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
        this.left = 0;
        this.isScroll = true;
        setOverScrollMode(2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            this.childView = childAt;
            this.left = childAt.getLeft();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00da  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.window.DampingHorizontalScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private void upDownMoveAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(this.childView.getLeft(), this.left, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.childView.setAnimation(translateAnimation);
    }

    public void setScroll(boolean scroll) {
        this.isScroll = scroll;
    }
}
