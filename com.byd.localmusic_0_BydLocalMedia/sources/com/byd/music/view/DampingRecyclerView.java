package com.byd.music.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DampingRecyclerView extends LinearLayout {
    private static final int ANIM_TIME = 300;
    private static final float DAMPING_COEFFICIENT = 0.5f;
    private RecyclerView childView;
    private boolean isMoved;
    private boolean isSuccess;
    private ScrollListener mScrollListener;
    private Rect original;
    private float startYpos;

    public interface ScrollListener {
        void onScroll();
    }

    public DampingRecyclerView(Context context) {
        this(context, null);
    }

    public DampingRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DampingRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.original.set(this.childView.getLeft(), this.childView.getTop(), this.childView.getRight(), this.childView.getBottom());
    }

    public void setScrollListener(ScrollListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        if (y >= this.original.bottom || y <= this.original.top) {
            if (this.isMoved) {
                recoverLayout();
            }
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startYpos = motionEvent.getY();
        } else {
            if (action == 1) {
                if (this.isMoved) {
                    recoverLayout();
                }
                return !this.isSuccess || super.dispatchTouchEvent(motionEvent);
            }
            if (action != 2) {
                return true;
            }
        }
        int y2 = (int) (motionEvent.getY() - this.startYpos);
        boolean z = y2 > 0 && canPullDown();
        boolean z2 = y2 < 0 && canPullUp();
        if (z || z2) {
            cancelChild(motionEvent);
            int i = (int) (y2 * 0.5f);
            this.childView.layout(this.original.left, this.original.top + i, this.original.right, this.original.bottom + i);
            ScrollListener scrollListener = this.mScrollListener;
            if (scrollListener != null) {
                scrollListener.onScroll();
            }
            this.isMoved = true;
            this.isSuccess = false;
            return true;
        }
        this.startYpos = motionEvent.getY();
        this.isMoved = false;
        this.isSuccess = true;
        return super.dispatchTouchEvent(motionEvent);
    }

    private void cancelChild(MotionEvent motionEvent) {
        motionEvent.setAction(3);
        super.dispatchTouchEvent(motionEvent);
    }

    private void recoverLayout() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.childView.getTop() - this.original.top, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(PathInterpolatorUtils.getStandard());
        this.childView.startAnimation(translateAnimation);
        this.childView.layout(this.original.left, this.original.top, this.original.right, this.original.bottom);
        this.isMoved = false;
    }

    private boolean canPullDown() {
        if (this.childView.getLayoutManager() == null || this.childView.getAdapter() == null) {
            return false;
        }
        if (((LinearLayoutManager) this.childView.getLayoutManager()).findFirstVisibleItemPosition() == 0 || this.childView.getAdapter().getItemCount() == 0) {
            return (this.childView.getChildCount() > 0 ? this.childView.getChildAt(0).getTop() : 0) >= 0;
        }
        return false;
    }

    private boolean canPullUp() {
        if (this.childView.getLayoutManager() == null) {
            return false;
        }
        int itemCount = this.childView.getAdapter().getItemCount() - 1;
        int iFindLastVisibleItemPosition = ((LinearLayoutManager) this.childView.getLayoutManager()).findLastVisibleItemPosition();
        if (iFindLastVisibleItemPosition < itemCount) {
            return false;
        }
        View childAt = this.childView.getChildAt(Math.min(iFindLastVisibleItemPosition - ((LinearLayoutManager) this.childView.getLayoutManager()).findFirstVisibleItemPosition(), this.childView.getChildCount() - 1));
        return childAt != null && childAt.getBottom() <= this.childView.getBottom() - this.childView.getTop();
    }
}
