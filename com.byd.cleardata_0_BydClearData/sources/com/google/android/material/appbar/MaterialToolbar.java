package com.google.android.material.appbar;

import a.g.k.t;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import b.a.a.a.b0.g;
import b.a.a.a.b0.h;
import b.a.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    private static final int Q = k.t;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.a.a.a.b.D);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, Q), attributeSet, i);
        O(getContext());
    }

    private void O(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            g gVar = new g();
            gVar.W(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            gVar.M(context);
            gVar.V(t.t(this));
            t.k0(this, gVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.e(this);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        h.d(this, f);
    }
}
