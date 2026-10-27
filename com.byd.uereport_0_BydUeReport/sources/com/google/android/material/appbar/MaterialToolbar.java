package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import e6.h;
import e6.i;
import i6.a;
import o0.t;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialToolbar extends Toolbar {
    public static final int S = R$style.Widget_MaterialComponents_Toolbar;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public final void P(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            h hVar = new h();
            hVar.X(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            hVar.N(context);
            hVar.W(t.v(this));
            t.o0(this, hVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        i.d(this, f8);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i8) {
        super(a.c(context, attributeSet, i8, S), attributeSet, i8);
        P(getContext());
    }
}
