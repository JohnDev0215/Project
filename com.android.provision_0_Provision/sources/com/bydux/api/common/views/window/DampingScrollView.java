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

/* JADX INFO: loaded from: classes.dex */
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

    public DampingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        initialize(context, attrs);
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

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0144  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.window.DampingScrollView.dispatchTouchEvent(android.view.MotionEvent):boolean");
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

    public void setScroll(boolean scroll) {
        this.isScroll = scroll;
    }

    private void initialize(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DampingScrollView);
        this.mMaxHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.DampingScrollView_maxHeight, this.mMaxHeight);
        this.isVertical = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.DampingScrollView_orientation, 1) == 1;
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = this.mMaxHeight;
        if (i > 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
