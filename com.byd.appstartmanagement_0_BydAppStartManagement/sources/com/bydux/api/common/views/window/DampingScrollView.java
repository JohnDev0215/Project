package com.bydux.api.common.views.window;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.bydux.api.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DampingScrollView extends ScrollView {
    private View childView;
    private int currentX;
    private int currentY;
    private int deltaY;
    private boolean isScroll;
    private boolean isVertical;
    private int mMaxHeight;
    private ScrollListener mScrollListener;
    private float moveHeight;
    private int previousY;
    private int startX;
    private int startY;
    private int top;
    private Rect topRect;

    public interface ScrollListener {
        void onScroll();

        void onScrollEnd();

        void onScrollStart();
    }

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
        initialize(context, attributeSet);
        setOverScrollMode(2);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DampingScrollView);
        this.mMaxHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.DampingScrollView_maxHeight, this.mMaxHeight);
        this.isVertical = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.DampingScrollView_orientation, 1) == 1;
        typedArrayObtainStyledAttributes.recycle();
    }

    private void upDownMoveAnimation() {
        TranslateAnimation translateAnimation = this.isVertical ? new TranslateAnimation(0.0f, 0.0f, this.childView.getTop(), this.top) : new TranslateAnimation(this.childView.getLeft(), this.top, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.childView.setAnimation(translateAnimation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0137  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.window.DampingScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            this.childView = childAt;
            this.top = this.isVertical ? childAt.getTop() : childAt.getLeft();
        }
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.mMaxHeight;
        if (i3 > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }

    public void setScroll(boolean z) {
        this.isScroll = z;
    }

    public void setScrollListener(ScrollListener scrollListener) {
        this.mScrollListener = scrollListener;
    }
}
