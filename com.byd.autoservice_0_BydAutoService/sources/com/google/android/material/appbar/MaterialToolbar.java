package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import l0.t;
import n2.h;
import n2.i;
import r2.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public static final int Q = R$style.Widget_MaterialComponents_Toolbar;

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
            t.m0(this, hVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        i.d(this, f3);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i3) {
        super(a.c(context, attributeSet, i3, Q), attributeSet, i3);
        P(getContext());
    }
}
