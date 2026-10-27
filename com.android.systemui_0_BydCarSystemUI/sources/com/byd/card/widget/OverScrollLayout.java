package com.byd.card.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class OverScrollLayout extends LinearLayout {
    private static final int ANIM_TIME = 200;
    private static final float DAMPING_COEFFICIENT = 0.3f;
    private RecyclerView childView;
    private boolean isMoved;
    private boolean isSuccess;
    private ScrollListener mScrollListener;
    private Rect original;
    private float startYpos;

    public interface ScrollListener {
        void onScroll();
    }

    public OverScrollLayout(Context context) {
        this(context, null);
    }

    public OverScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.original = new Rect();
        this.isMoved = false;
        this.isSuccess = false;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.childView = (RecyclerView) getChildAt(0);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.original.set(this.childView.getLeft(), this.childView.getTop(), this.childView.getRight(), this.childView.getBottom());
    }

    public void setScrollListener(ScrollListener listener) {
        this.mScrollListener = listener;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        if (y >= this.original.bottom || y <= this.original.top) {
            if (this.isMoved) {
                recoverLayout();
            }
            return true;
        }
        int action = ev.getAction();
        if (action == 0) {
            this.startYpos = ev.getY();
        } else {
            if (action == 1) {
                if (this.isMoved) {
                    recoverLayout();
                }
                return !this.isSuccess || super.dispatchTouchEvent(ev);
            }
            if (action != 2) {
                return true;
            }
        }
        int y2 = (int) (ev.getY() - this.startYpos);
        boolean z = y2 > 0 && canPullDown();
        boolean z2 = y2 < 0 && canPullUp();
        if (z || z2) {
            cancelChild(ev);
            int i = (int) (y2 * DAMPING_COEFFICIENT);
            this.childView.layout(this.original.left, this.original.top + i, this.original.right, this.original.bottom + i);
            ScrollListener scrollListener = this.mScrollListener;
            if (scrollListener != null) {
                scrollListener.onScroll();
            }
            this.isMoved = true;
            this.isSuccess = false;
            return true;
        }
        this.startYpos = ev.getY();
        this.isMoved = false;
        this.isSuccess = true;
        return super.dispatchTouchEvent(ev);
    }

    private void cancelChild(MotionEvent ev) {
        ev.setAction(3);
        super.dispatchTouchEvent(ev);
    }

    private void recoverLayout() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.childView.getTop() - this.original.top, 0.0f);
        translateAnimation.setDuration(200L);
        this.childView.startAnimation(translateAnimation);
        this.childView.layout(this.original.left, this.original.top, this.original.right, this.original.bottom);
        this.isMoved = false;
    }

    private boolean canPullDown() {
        if (((LinearLayoutManager) this.childView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0 || this.childView.getAdapter().getItemCount() == 0) {
            return (this.childView.getChildCount() > 0 ? this.childView.getChildAt(0).getTop() : 0) >= 0;
        }
        return false;
    }

    private boolean canPullUp() {
        int itemCount = this.childView.getAdapter().getItemCount() - 1;
        int iFindLastCompletelyVisibleItemPosition = ((LinearLayoutManager) this.childView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
        if (iFindLastCompletelyVisibleItemPosition >= itemCount) {
            View childAt = this.childView.getChildAt(Math.min(iFindLastCompletelyVisibleItemPosition - ((LinearLayoutManager) this.childView.getLayoutManager()).findFirstCompletelyVisibleItemPosition(), this.childView.getChildCount() - 1));
            return childAt != null && childAt.getBottom() <= this.childView.getBottom() - this.childView.getTop();
        }
        return false;
    }
}
