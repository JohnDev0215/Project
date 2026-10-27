package com.google.android.material.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.R$id;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout {
    public TextView e;
    public Button f;
    public int g;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    public final boolean a(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.e.getPaddingTop() == i2 && this.e.getPaddingBottom() == i3) {
            return z;
        }
        TextView textView = this.e;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.e.f(textView)) {
            om3.e.i(textView, om3.e.e(textView), i2, om3.e.d(textView), i3);
            return true;
        }
        textView.setPadding(textView.getPaddingLeft(), i2, textView.getPaddingRight(), i3);
        return true;
    }

    public Button getActionView() {
        return this.f;
    }

    public TextView getMessageView() {
        return this.e;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.snackbar_text);
        this.f = (Button) findViewById(R$id.snackbar_action);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            int r0 = r7.getOrientation()
            r1 = 1
            if (r0 != r1) goto Lb
            return
        Lb:
            android.content.res.Resources r0 = r7.getResources()
            int r2 = com.google.android.material.R$dimen.design_snackbar_padding_vertical_2lines
            int r0 = r0.getDimensionPixelSize(r2)
            android.content.res.Resources r2 = r7.getResources()
            int r3 = com.google.android.material.R$dimen.design_snackbar_padding_vertical
            int r2 = r2.getDimensionPixelSize(r3)
            android.widget.TextView r3 = r7.e
            android.text.Layout r3 = r3.getLayout()
            int r3 = r3.getLineCount()
            r4 = 0
            if (r3 <= r1) goto L2e
            r3 = r1
            goto L2f
        L2e:
            r3 = r4
        L2f:
            if (r3 == 0) goto L48
            int r5 = r7.g
            if (r5 <= 0) goto L48
            android.widget.Button r5 = r7.f
            int r5 = r5.getMeasuredWidth()
            int r6 = r7.g
            if (r5 <= r6) goto L48
            int r2 = r0 - r2
            boolean r0 = r7.a(r1, r0, r2)
            if (r0 == 0) goto L53
            goto L54
        L48:
            if (r3 == 0) goto L4b
            goto L4c
        L4b:
            r0 = r2
        L4c:
            boolean r0 = r7.a(r4, r0, r0)
            if (r0 == 0) goto L53
            goto L54
        L53:
            r1 = r4
        L54:
            if (r1 == 0) goto L59
            super.onMeasure(r8, r9)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.onMeasure(int, int):void");
    }

    public void setMaxInlineActionWidth(int i) {
        this.g = i;
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
