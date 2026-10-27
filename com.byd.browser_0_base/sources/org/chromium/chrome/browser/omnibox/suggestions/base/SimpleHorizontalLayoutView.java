package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleHorizontalLayoutView extends ViewGroup {

    public static class a extends ViewGroup.LayoutParams {
    }

    public SimpleHorizontalLayoutView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = getLayoutDirection() == 1;
        int i5 = z2 ? -1 : 1;
        int measuredHeight = getMeasuredHeight();
        int paddingLeft = getPaddingLeft();
        for (int childCount = z2 ? getChildCount() - 1 : 0; childCount >= 0 && childCount < getChildCount(); childCount += i5) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() != 8) {
                int measuredHeight2 = (measuredHeight - childAt.getMeasuredHeight()) / 2;
                if (measuredHeight2 < 0) {
                    measuredHeight2 = 0;
                }
                childAt.layout(paddingLeft, measuredHeight2, childAt.getMeasuredWidth() + paddingLeft, measuredHeight - measuredHeight2);
                paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            a aVar = (a) childAt.getLayoutParams();
            aVar.getClass();
            if (childAt.getVisibility() != 8 && (i3 = ((ViewGroup.LayoutParams) aVar).width) > 0) {
                size -= i3;
            }
        }
        View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        View.MeasureSpec.makeMeasureSpec(0, 0);
        throw null;
    }
}
