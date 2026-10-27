package com.byd.viewsup.supviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.byd.viewsup.BydInterceptHelper;
import com.byd.viewsup.BydScrollerTouchChildView;

/* JADX INFO: loaded from: classes5.dex */
public class BydClickableChildFrameLayout extends FrameLayout implements BydScrollerTouchChildView {
    private BydInterceptHelper mBydOverScrollHelper;

    public BydClickableChildFrameLayout(Context context) {
        super(context);
    }

    public BydClickableChildFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BydClickableChildFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        BydInterceptHelper bydInterceptHelper = this.mBydOverScrollHelper;
        if (bydInterceptHelper == null || !bydInterceptHelper.onInterceptTouchEvent(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // com.byd.viewsup.BydScrollerTouchChildView
    public void setHorizontal(boolean z) {
        this.mBydOverScrollHelper = new BydInterceptHelper(z, this);
    }
}
