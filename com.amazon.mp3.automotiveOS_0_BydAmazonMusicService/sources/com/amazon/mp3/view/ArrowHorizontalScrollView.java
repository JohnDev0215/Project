package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import com.amazon.mp3.fragment.FragmentTabWidget;

/* JADX INFO: loaded from: classes4.dex */
public class ArrowHorizontalScrollView extends HorizontalScrollView {
    private ViewGroup mChildContainer;
    private View mLeftArrow;
    private Rect mRect;
    private View mRightArrow;

    private float getFadingEdgeStrength(float strength) {
        return strength > 0.0f ? 1.0f : 0.0f;
    }

    public ArrowHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            updateArrows();
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        updateArrows();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected float getLeftFadingEdgeStrength() {
        return getFadingEdgeStrength(super.getLeftFadingEdgeStrength());
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected float getRightFadingEdgeStrength() {
        return getFadingEdgeStrength(super.getRightFadingEdgeStrength());
    }

    public void setLeftArrow(View leftArrow) {
        this.mLeftArrow = leftArrow;
    }

    public void setRightArrow(View rightArrow) {
        this.mRightArrow = rightArrow;
    }

    public void setContent(FragmentTabWidget tabBar) {
        this.mChildContainer = tabBar;
    }

    private void updateArrows() {
        if (this.mChildContainer != null) {
            if (getScrollX() <= 0) {
                showLeftArrow(false);
            } else {
                showLeftArrow(true);
            }
            if (this.mRect == null) {
                this.mRect = new Rect();
            }
            getDrawingRect(this.mRect);
            if (this.mRect.right >= this.mChildContainer.getMeasuredWidth()) {
                showRightArrow(false);
            } else {
                showRightArrow(true);
            }
        }
    }

    private void showLeftArrow(boolean show) {
        View view = this.mLeftArrow;
        if (view == null) {
            return;
        }
        view.setVisibility(show ? 0 : 8);
    }

    private void showRightArrow(boolean show) {
        View view = this.mRightArrow;
        if (view == null) {
            return;
        }
        view.setVisibility(show ? 0 : 8);
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        if (visibility == 0) {
            updateArrows();
        } else {
            showLeftArrow(false);
            showRightArrow(false);
        }
        super.setVisibility(visibility);
    }
}
