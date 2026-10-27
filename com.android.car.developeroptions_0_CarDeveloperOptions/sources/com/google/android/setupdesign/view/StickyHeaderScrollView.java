package com.google.android.setupdesign.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class StickyHeaderScrollView extends BottomScrollView {
    private int statusBarInset;
    private View sticky;
    private View stickyContainer;

    public StickyHeaderScrollView(Context context) {
        super(context);
        this.statusBarInset = 0;
    }

    public StickyHeaderScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.statusBarInset = 0;
    }

    public StickyHeaderScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.statusBarInset = 0;
    }

    @Override // com.google.android.setupdesign.view.BottomScrollView, android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.sticky == null) {
            updateStickyView();
        }
        updateStickyHeaderPosition();
    }

    public void updateStickyView() {
        this.sticky = findViewWithTag("sticky");
        this.stickyContainer = findViewWithTag("stickyContainer");
    }

    private void updateStickyHeaderPosition() {
        View view;
        if (Build.VERSION.SDK_INT < 11 || (view = this.sticky) == null) {
            return;
        }
        View view2 = this.stickyContainer;
        if (view2 != null) {
            view = view2;
        }
        if ((view.getTop() - getScrollY()) + (this.stickyContainer != null ? this.sticky.getTop() : 0) < this.statusBarInset || !view.isShown()) {
            view.setTranslationY(getScrollY() - r1);
        } else {
            view.setTranslationY(0.0f);
        }
    }

    @Override // com.google.android.setupdesign.view.BottomScrollView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        updateStickyHeaderPosition();
    }

    @Override // android.view.View
    @TargetApi(21)
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (!getFitsSystemWindows()) {
            return windowInsets;
        }
        this.statusBarInset = windowInsets.getSystemWindowInsetTop();
        return windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), 0, windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
    }
}
