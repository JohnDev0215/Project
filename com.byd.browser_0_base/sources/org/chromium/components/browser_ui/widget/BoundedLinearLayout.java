package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class BoundedLinearLayout extends LinearLayout {
    public final TypedValue e;
    public final TypedValue f;
    public final int g;
    public boolean h;
    public boolean i;

    public BoundedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedValue typedValue = new TypedValue();
        this.e = typedValue;
        TypedValue typedValue2 = new TypedValue();
        this.f = typedValue2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BoundedLinearLayout);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BoundedLinearLayout_maxHeight, -1);
        typedArrayObtainStyledAttributes.getValue(R$styleable.BoundedLinearLayout_maxWidthLandscape, typedValue);
        typedArrayObtainStyledAttributes.getValue(R$styleable.BoundedLinearLayout_maxWidthPortrait, typedValue2);
        typedArrayObtainStyledAttributes.recycle();
        this.g = dimensionPixelSize > 0 ? dimensionPixelSize : -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            android.content.Context r0 = r7.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r1 = r0.widthPixels
            int r2 = r0.heightPixels
            if (r1 >= r2) goto L14
            r1 = 1
            goto L15
        L14:
            r1 = 0
        L15:
            int r2 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 == 0) goto L1e
            android.util.TypedValue r1 = r7.f
            goto L20
        L1e:
            android.util.TypedValue r1 = r7.e
        L20:
            int r3 = r1.type
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = -1
            if (r3 == 0) goto L4f
            boolean r6 = r7.h
            if (r6 != 0) goto L4f
            r6 = 5
            if (r3 != r6) goto L33
            float r0 = r1.getDimension(r0)
            goto L3d
        L33:
            r6 = 6
            if (r3 != r6) goto L3f
            int r0 = r0.widthPixels
            float r0 = (float) r0
            float r0 = r1.getFraction(r0, r0)
        L3d:
            int r0 = (int) r0
            goto L40
        L3f:
            r0 = r5
        L40:
            if (r2 <= r0) goto L4f
            if (r0 <= 0) goto L4f
            int r8 = android.view.View.MeasureSpec.getMode(r8)
            if (r8 != 0) goto L4b
            r8 = r4
        L4b:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r8)
        L4f:
            int r0 = android.view.View.MeasureSpec.getSize(r9)
            int r1 = r7.g
            if (r1 == r5) goto L69
            if (r0 <= r1) goto L69
            boolean r0 = r7.i
            if (r0 != 0) goto L69
            int r9 = android.view.View.MeasureSpec.getMode(r9)
            if (r9 != 0) goto L64
            goto L65
        L64:
            r4 = r9
        L65:
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r4)
        L69:
            super.onMeasure(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.widget.BoundedLinearLayout.onMeasure(int, int):void");
    }
}
