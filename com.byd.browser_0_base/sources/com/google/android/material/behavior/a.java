package com.google.android.material.behavior;

import android.view.View;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SwipeDismissBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements androidx.core.view.accessibility.a {
    public final /* synthetic */ SwipeDismissBehavior e;

    public a(SwipeDismissBehavior swipeDismissBehavior) {
        this.e = swipeDismissBehavior;
    }

    @Override // androidx.core.view.accessibility.a
    public final boolean f(View view) {
        SwipeDismissBehavior swipeDismissBehavior = this.e;
        boolean z = false;
        if (!swipeDismissBehavior.s(view)) {
            return false;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z2 = om3.e.c(view) == 1;
        int i = swipeDismissBehavior.c;
        if ((i == 0 && z2) || (i == 1 && !z2)) {
            z = true;
        }
        int width = view.getWidth();
        if (z) {
            width = -width;
        }
        view.offsetLeftAndRight(width);
        view.setAlpha(0.0f);
        swipeDismissBehavior.getClass();
        return true;
    }
}
