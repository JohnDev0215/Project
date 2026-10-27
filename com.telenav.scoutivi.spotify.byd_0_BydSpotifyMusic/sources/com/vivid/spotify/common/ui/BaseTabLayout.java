package com.vivid.spotify.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public class BaseTabLayout extends TabLayout {
    private final int V;
    private Scroller W;

    public BaseTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.V = 500;
        this.W = new Scroller(context);
    }

    public void Q(int i7, int i8, int i9) {
        this.W.startScroll(getScrollX(), getScrollY(), i7, i8, i9);
        invalidate();
    }

    public void R(int i7) {
        int scrollX = i7 - getScrollX();
        int scrollY = 0 - getScrollY();
        int iAbs = Math.abs(scrollX * 2);
        if (iAbs < 500) {
            iAbs = 500;
        }
        Q(scrollX, scrollY, iAbs);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void computeScroll() {
        if (this.W.computeScrollOffset()) {
            scrollTo(this.W.getCurrX(), this.W.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }
}
