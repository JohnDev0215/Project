package com.byd.viewsup.supviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.byd.viewsup.BydInterceptHelper;
import com.byd.viewsup.BydScrollerTouchChildView;

/* JADX INFO: loaded from: classes5.dex */
public class BydClickableChildScrollerView extends ScrollView implements BydScrollerTouchChildView {
    private BydInterceptHelper mBydOverScrollHelper;

    public BydClickableChildScrollerView(Context context) {
        super(context);
    }

    public BydClickableChildScrollerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BydClickableChildScrollerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
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

    @Override // android.widget.ScrollView, android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), 0);
    }
}
