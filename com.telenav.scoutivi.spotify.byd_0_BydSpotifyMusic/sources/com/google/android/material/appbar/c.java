package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f4076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4077b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4078c;

    public c() {
        this.f4077b = 0;
        this.f4078c = 0;
    }

    public int E() {
        d dVar = this.f4076a;
        if (dVar != null) {
            return dVar.b();
        }
        return 0;
    }

    protected void F(CoordinatorLayout coordinatorLayout, V v6, int i7) {
        coordinatorLayout.I(v6, i7);
    }

    public boolean G(int i7) {
        d dVar = this.f4076a;
        if (dVar != null) {
            return dVar.e(i7);
        }
        this.f4077b = i7;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v6, int i7) {
        F(coordinatorLayout, v6, i7);
        if (this.f4076a == null) {
            this.f4076a = new d(v6);
        }
        this.f4076a.c();
        this.f4076a.a();
        int i8 = this.f4077b;
        if (i8 != 0) {
            this.f4076a.e(i8);
            this.f4077b = 0;
        }
        int i9 = this.f4078c;
        if (i9 == 0) {
            return true;
        }
        this.f4076a.d(i9);
        this.f4078c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4077b = 0;
        this.f4078c = 0;
    }
}
