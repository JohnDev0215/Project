package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleVerticalLayoutView extends ViewGroup {
    public SimpleVerticalLayoutView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                paddingTop = childAt.getMeasuredHeight() + paddingTop;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            childAt.getLayoutParams();
            childAt.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
            measuredHeight += childAt.getMeasuredHeight();
        }
        setMeasuredDimension(size, View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }
}
