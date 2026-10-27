package com.google.android.material.floatingactionbutton;

import K4.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import t1.AbstractC2425a;
import t1.C2428d;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior<T> extends AbstractC2425a {
    public ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior() {
    }

    @Override // t1.AbstractC2425a
    public final /* synthetic */ boolean a(View view) {
        throw new ClassCastException();
    }

    @Override // t1.AbstractC2425a
    public final void c(C2428d c2428d) {
        if (c2428d.f22434h == 0) {
            c2428d.f22434h = 80;
        }
    }

    @Override // t1.AbstractC2425a
    public final boolean d(View view, View view2) {
        throw new ClassCastException();
    }

    @Override // t1.AbstractC2425a
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i7) {
        throw new ClassCastException();
    }

    public ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f3588f);
        typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.getBoolean(1, true);
        typedArrayObtainStyledAttributes.recycle();
    }
}
