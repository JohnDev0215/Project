package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import defpackage.AbstractC0678dn;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BoundedLinearLayout extends LinearLayout {
    public TypedValue s;
    public TypedValue t;
    public final int u;

    public BoundedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = new TypedValue();
        this.t = new TypedValue();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.w);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
        typedArrayObtainStyledAttributes.getValue(1, this.s);
        typedArrayObtainStyledAttributes.getValue(2, this.t);
        typedArrayObtainStyledAttributes.recycle();
        this.u = dimensionPixelSize > 0 ? dimensionPixelSize : -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
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
            android.util.TypedValue r1 = r7.t
            goto L20
        L1e:
            android.util.TypedValue r1 = r7.s
        L20:
            int r3 = r1.type
            r4 = -1
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == 0) goto L4c
            r6 = 5
            if (r3 != r6) goto L2f
            float r0 = r1.getDimension(r0)
            goto L39
        L2f:
            r6 = 6
            if (r3 != r6) goto L3b
            int r0 = r0.widthPixels
            float r0 = (float) r0
            float r0 = r1.getFraction(r0, r0)
        L39:
            int r0 = (int) r0
            goto L3c
        L3b:
            r0 = -1
        L3c:
            if (r2 <= r0) goto L4c
            if (r0 <= 0) goto L4c
            int r8 = android.view.View.MeasureSpec.getMode(r8)
            if (r8 != 0) goto L48
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
        L48:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r8)
        L4c:
            int r0 = android.view.View.MeasureSpec.getSize(r9)
            int r1 = r7.u
            if (r1 == r4) goto L62
            if (r0 <= r1) goto L62
            int r9 = android.view.View.MeasureSpec.getMode(r9)
            if (r9 != 0) goto L5d
            goto L5e
        L5d:
            r5 = r9
        L5e:
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r5)
        L62:
            super.onMeasure(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.widget.BoundedLinearLayout.onMeasure(int, int):void");
    }
}
