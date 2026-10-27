package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import p.j00;
import p.m00;
import p.u4;
import p.xz1;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton$BaseBehavior<T> extends j00 {
    public FloatingActionButton$BaseBehavior() {
    }

    @Override // p.j00
    public final boolean a(View view) {
        u4.u(view);
        throw null;
    }

    @Override // p.j00
    public final void c(m00 m00Var) {
        if (m00Var.h == 0) {
            m00Var.h = 80;
        }
    }

    @Override // p.j00
    public final boolean d(View view, View view2) {
        u4.u(view);
        throw null;
    }

    @Override // p.j00
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
        u4.u(view);
        throw null;
    }

    public FloatingActionButton$BaseBehavior(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.g);
        typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }
}
