package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f3359a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3361c;

    public c() {
        this.f3360b = 0;
        this.f3361c = 0;
    }

    public int E() {
        d dVar = this.f3359a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    protected void F(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.M(v, i);
    }

    public boolean G(int i) {
        d dVar = this.f3359a;
        if (dVar != null) {
            return dVar.f(i);
        }
        this.f3360b = i;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        F(coordinatorLayout, v, i);
        if (this.f3359a == null) {
            this.f3359a = new d(v);
        }
        this.f3359a.d();
        this.f3359a.a();
        int i2 = this.f3360b;
        if (i2 != 0) {
            this.f3359a.f(i2);
            this.f3360b = 0;
        }
        int i3 = this.f3361c;
        if (i3 == 0) {
            return true;
        }
        this.f3359a.e(i3);
        this.f3361c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3360b = 0;
        this.f3361c = 0;
    }
}
