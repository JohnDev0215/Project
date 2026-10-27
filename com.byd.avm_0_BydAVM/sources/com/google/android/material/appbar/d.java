package com.google.android.material.appbar;

import android.view.View;
import b.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f2163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2166d;
    private int e;
    private boolean f = true;
    private boolean g = true;

    public d(View view) {
        this.f2163a = view;
    }

    void a() {
        View view = this.f2163a;
        t.V(view, this.f2166d - (view.getTop() - this.f2164b));
        View view2 = this.f2163a;
        t.U(view2, this.e - (view2.getLeft() - this.f2165c));
    }

    public int b() {
        return this.f2164b;
    }

    public int c() {
        return this.f2166d;
    }

    void d() {
        this.f2164b = this.f2163a.getTop();
        this.f2165c = this.f2163a.getLeft();
    }

    public boolean e(int i) {
        if (!this.g || this.e == i) {
            return false;
        }
        this.e = i;
        a();
        return true;
    }

    public boolean f(int i) {
        if (!this.f || this.f2166d == i) {
            return false;
        }
        this.f2166d = i;
        a();
        return true;
    }
}
