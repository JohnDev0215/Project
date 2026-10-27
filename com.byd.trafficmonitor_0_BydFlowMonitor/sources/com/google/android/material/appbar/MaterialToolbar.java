package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import b.h.m.t;
import c.b.a.a.b;
import c.b.a.a.i0.h;
import c.b.a.a.i0.i;
import c.b.a.a.k;
import c.b.a.a.m0.a.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public static final int Q = k.Widget_MaterialComponents_Toolbar;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public final void P(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            h hVar = new h();
            hVar.X(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            hVar.N(context);
            hVar.W(t.u(this));
            t.n0(this, hVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        i.d(this, f2);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.toolbarStyle);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i2) {
        super(a.c(context, attributeSet, i2, Q), attributeSet, i2);
        P(getContext());
    }
}
