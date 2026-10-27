package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import b.g.k.t;
import c.a.a.a.f;
import c.a.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextView f2485b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Button f2486c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2487d;
    private int e;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    private static void a(View view, int i, int i2) {
        if (t.R(view)) {
            t.w0(view, t.E(view), i, t.D(view), i2);
        } else {
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), i2);
        }
    }

    private boolean b(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.f2485b.getPaddingTop() == i2 && this.f2485b.getPaddingBottom() == i3) {
            return z;
        }
        a(this.f2485b, i2, i3);
        return true;
    }

    public Button getActionView() {
        return this.f2486c;
    }

    public TextView getMessageView() {
        return this.f2485b;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f2485b = (TextView) findViewById(f.v);
        this.f2486c = (Button) findViewById(f.u);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            int r0 = r7.f2487d
            if (r0 <= 0) goto L18
            int r0 = r7.getMeasuredWidth()
            int r1 = r7.f2487d
            if (r0 <= r1) goto L18
            r8 = 1073741824(0x40000000, float:2.0)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            super.onMeasure(r8, r9)
        L18:
            android.content.res.Resources r0 = r7.getResources()
            int r1 = c.a.a.a.d.p
            int r0 = r0.getDimensionPixelSize(r1)
            android.content.res.Resources r1 = r7.getResources()
            int r2 = c.a.a.a.d.o
            int r1 = r1.getDimensionPixelSize(r2)
            android.widget.TextView r2 = r7.f2485b
            android.text.Layout r2 = r2.getLayout()
            int r2 = r2.getLineCount()
            r3 = 0
            r4 = 1
            if (r2 <= r4) goto L3c
            r2 = r4
            goto L3d
        L3c:
            r2 = r3
        L3d:
            if (r2 == 0) goto L56
            int r5 = r7.e
            if (r5 <= 0) goto L56
            android.widget.Button r5 = r7.f2486c
            int r5 = r5.getMeasuredWidth()
            int r6 = r7.e
            if (r5 <= r6) goto L56
            int r1 = r0 - r1
            boolean r0 = r7.b(r4, r0, r1)
            if (r0 == 0) goto L61
            goto L60
        L56:
            if (r2 == 0) goto L59
            goto L5a
        L59:
            r0 = r1
        L5a:
            boolean r0 = r7.b(r3, r0, r0)
            if (r0 == 0) goto L61
        L60:
            r3 = r4
        L61:
            if (r3 == 0) goto L66
            super.onMeasure(r8, r9)
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.onMeasure(int, int):void");
    }

    public void setMaxInlineActionWidth(int i) {
        this.e = i;
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.z5);
        this.f2487d = typedArrayObtainStyledAttributes.getDimensionPixelSize(l.A5, -1);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(l.H5, -1);
        typedArrayObtainStyledAttributes.recycle();
    }
}
