package com.google.android.material.appbar;

import android.view.View;
import b.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f2553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2556d;
    private int e;
    private boolean f = true;
    private boolean g = true;

    public d(View view) {
        this.f2553a = view;
    }

    void a() {
        View view = this.f2553a;
        t.V(view, this.f2556d - (view.getTop() - this.f2554b));
        View view2 = this.f2553a;
        t.U(view2, this.e - (view2.getLeft() - this.f2555c));
    }

    public int b() {
        return this.f2554b;
    }

    public int c() {
        return this.f2556d;
    }

    void d() {
        this.f2554b = this.f2553a.getTop();
        this.f2555c = this.f2553a.getLeft();
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
        if (!this.f || this.f2556d == i) {
            return false;
        }
        this.f2556d = i;
        a();
        return true;
    }
}
