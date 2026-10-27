package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f3687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3688b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3689c;

    public c() {
        this.f3688b = 0;
        this.f3689c = 0;
    }

    public int E() {
        d dVar = this.f3687a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    protected void F(CoordinatorLayout coordinatorLayout, V v, int i2) {
        coordinatorLayout.M(v, i2);
    }

    public boolean G(int i2) {
        d dVar = this.f3687a;
        if (dVar != null) {
            return dVar.f(i2);
        }
        this.f3688b = i2;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        F(coordinatorLayout, v, i2);
        if (this.f3687a == null) {
            this.f3687a = new d(v);
        }
        this.f3687a.d();
        this.f3687a.a();
        int i3 = this.f3688b;
        if (i3 != 0) {
            this.f3687a.f(i3);
            this.f3688b = 0;
        }
        int i4 = this.f3689c;
        if (i4 == 0) {
            return true;
        }
        this.f3687a.e(i4);
        this.f3689c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3688b = 0;
        this.f3689c = 0;
    }
}
