package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import b.g.k.t;
import d.b.a.a.k;
import d.b.a.a.w.h;
import d.b.a.a.w.i;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    private static final int Q = k.D;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    private void P(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            h hVar = new h();
            hVar.X(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            hVar.N(context);
            hVar.W(t.u(this));
            t.m0(this, hVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        i.d(this, f2);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.O);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, Q), attributeSet, i);
        P(getContext());
    }
}
