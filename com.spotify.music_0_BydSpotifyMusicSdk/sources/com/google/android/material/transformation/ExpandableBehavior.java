package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.ArrayList;
import java.util.WeakHashMap;
import p.j00;
import p.lt2;
import p.u4;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends j00 {
    public ExpandableBehavior() {
    }

    @Override // p.j00
    public abstract void b(View view);

    @Override // p.j00
    public final boolean d(View view, View view2) {
        u4.z(view2);
        throw null;
    }

    @Override // p.j00
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = zt2.a;
        if (!lt2.c(view)) {
            ArrayList arrayListJ = coordinatorLayout.j(view);
            int size = arrayListJ.size();
            for (int i2 = 0; i2 < size; i2++) {
                b(view);
            }
        }
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
    }
}
