package com.byd.ota.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

/* JADX INFO: loaded from: classes.dex */
public class FlowRadioGroup extends RadioGroup {
    public FlowRadioGroup(Context context) {
        super(context);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i7 += measuredWidth;
                int i10 = (i8 * measuredHeight) + measuredHeight;
                if (i7 > i6) {
                    i8++;
                    i10 = (i8 * measuredHeight) + measuredHeight;
                    i7 = measuredWidth;
                }
                childAt.layout(i7 - measuredWidth, i10 - measuredHeight, i7, i10);
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                childAt.measure(0, 0);
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i5 += measuredWidth;
                int i8 = (i6 * measuredHeight) + measuredHeight;
                if (i5 > size) {
                    i6++;
                    i5 = measuredWidth;
                    i4 = (i6 * measuredHeight) + measuredHeight;
                } else {
                    i4 = i8;
                }
            }
        }
        setMeasuredDimension(size, i4);
    }

    public FlowRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
