package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f2967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2968b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2969c;

    public c() {
        this.f2968b = 0;
        this.f2969c = 0;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2968b = 0;
        this.f2969c = 0;
    }

    public int E() {
        d dVar = this.f2967a;
        if (dVar != null) {
            return dVar.b();
        }
        return 0;
    }

    protected void F(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.I(v, i);
    }

    public boolean G(int i) {
        d dVar = this.f2967a;
        if (dVar != null) {
            return dVar.e(i);
        }
        this.f2968b = i;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        F(coordinatorLayout, v, i);
        if (this.f2967a == null) {
            this.f2967a = new d(v);
        }
        this.f2967a.c();
        this.f2967a.a();
        int i2 = this.f2968b;
        if (i2 != 0) {
            this.f2967a.e(i2);
            this.f2968b = 0;
        }
        int i3 = this.f2969c;
        if (i3 == 0) {
            return true;
        }
        this.f2967a.d(i3);
        this.f2969c = 0;
        return true;
    }
}
