package com.android.launcher3.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes4.dex */
public class WorkFooterContainer extends RelativeLayout {
    public WorkFooterContainer(Context context) {
        super(context);
    }

    public WorkFooterContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WorkFooterContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        updateTranslation();
    }

    @Override // android.view.View
    public void offsetTopAndBottom(int offset) {
        super.offsetTopAndBottom(offset);
        updateTranslation();
    }

    private void updateTranslation() {
        if (getParent() instanceof View) {
            View view = (View) getParent();
            setTranslationY(Math.max(0, (view.getHeight() - view.getPaddingBottom()) - getBottom()));
        }
    }
}
