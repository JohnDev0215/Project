package com.google.android.material.transformation;

import F1.H;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.ArrayList;
import t1.AbstractC2425a;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends AbstractC2425a {
    public ExpandableBehavior() {
    }

    @Override // t1.AbstractC2425a
    public abstract void b(View view);

    @Override // t1.AbstractC2425a
    public final boolean d(View view, View view2) {
        view2.getClass();
        throw new ClassCastException();
    }

    @Override // t1.AbstractC2425a
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i7) {
        int[] iArr = H.f2303a;
        if (!view.isLaidOut()) {
            ArrayList arrayListJ = coordinatorLayout.j(view);
            int size = arrayListJ.size();
            for (int i10 = 0; i10 < size; i10++) {
                b(view);
            }
        }
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
    }
}
