package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import defpackage.AbstractC0082Ek;
import defpackage.AbstractC0299Qm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0425Xm;
import defpackage.AbstractC0750f3;
import defpackage.AbstractC1251o3;
import defpackage.C0100Fk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends AbstractC0082Ek {
    public static final int[] P = {R.attr.state_checked};
    public final CheckedTextView N;
    public final AbstractC0750f3 O;

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.O = new C0100Fk(this);
        if (this.v != 0) {
            this.v = 0;
            requestLayout();
        }
        LayoutInflater.from(context).inflate(AbstractC0425Xm.b0, (ViewGroup) this, true);
        context.getResources().getDimensionPixelSize(AbstractC0299Qm.W1);
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(AbstractC0371Um.O0);
        this.N = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        AbstractC1251o3.B(this.N, this.O);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        return super.onCreateDrawableState(i + 1);
    }
}
