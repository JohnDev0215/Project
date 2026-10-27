package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.cn3;

/* JADX INFO: loaded from: classes.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public cn3 a;
    public int b;

    public ViewOffsetBehavior() {
        this.b = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean h(CoordinatorLayout coordinatorLayout, V v, int i) {
        t(coordinatorLayout, v, i);
        if (this.a == null) {
            this.a = new cn3(v);
        }
        cn3 cn3Var = this.a;
        View view = cn3Var.a;
        cn3Var.b = view.getTop();
        cn3Var.c = view.getLeft();
        this.a.a();
        int i2 = this.b;
        if (i2 == 0) {
            return true;
        }
        this.a.b(i2);
        this.b = 0;
        return true;
    }

    public final int s() {
        cn3 cn3Var = this.a;
        if (cn3Var != null) {
            return cn3Var.d;
        }
        return 0;
    }

    public void t(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.w(v, i);
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
    }
}
