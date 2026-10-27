package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f2550a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2551b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2552c;

    public c() {
        this.f2551b = 0;
        this.f2552c = 0;
    }

    public int E() {
        d dVar = this.f2550a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    protected void F(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.M(v, i);
    }

    public boolean G(int i) {
        d dVar = this.f2550a;
        if (dVar != null) {
            return dVar.f(i);
        }
        this.f2551b = i;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        F(coordinatorLayout, v, i);
        if (this.f2550a == null) {
            this.f2550a = new d(v);
        }
        this.f2550a.d();
        this.f2550a.a();
        int i2 = this.f2551b;
        if (i2 != 0) {
            this.f2550a.f(i2);
            this.f2551b = 0;
        }
        int i3 = this.f2552c;
        if (i3 == 0) {
            return true;
        }
        this.f2550a.e(i3);
        this.f2552c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2551b = 0;
        this.f2552c = 0;
    }
}
