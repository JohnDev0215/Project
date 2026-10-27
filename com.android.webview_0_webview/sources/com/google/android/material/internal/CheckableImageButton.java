package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;
import defpackage.AbstractC0245Nm;
import defpackage.AbstractC1251o3;
import defpackage.C0064Dk;
import defpackage.C0803g0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CheckableImageButton extends C0803g0 implements Checkable {
    public static final int[] v = {R.attr.state_checked};
    public boolean u;

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, AbstractC0245Nm.l4);
        AbstractC1251o3.B(this, new C0064Dk(this));
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.u;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArr = v;
        return this.u ? ImageButton.mergeDrawableStates(super.onCreateDrawableState(i + iArr.length), iArr) : super.onCreateDrawableState(i);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.u != z) {
            this.u = z;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.u);
    }
}
