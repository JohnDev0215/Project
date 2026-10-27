package com.google.android.material.appbar;

import android.view.View;
import i0.u;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f4079a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4080b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4082d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4083e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4084f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4085g = true;

    public d(View view) {
        this.f4079a = view;
    }

    void a() {
        View view = this.f4079a;
        u.V(view, this.f4082d - (view.getTop() - this.f4080b));
        View view2 = this.f4079a;
        u.U(view2, this.f4083e - (view2.getLeft() - this.f4081c));
    }

    public int b() {
        return this.f4082d;
    }

    void c() {
        this.f4080b = this.f4079a.getTop();
        this.f4081c = this.f4079a.getLeft();
    }

    public boolean d(int i7) {
        if (!this.f4085g || this.f4083e == i7) {
            return false;
        }
        this.f4083e = i7;
        a();
        return true;
    }

    public boolean e(int i7) {
        if (!this.f4084f || this.f4082d == i7) {
            return false;
        }
        this.f4082d = i7;
        a();
        return true;
    }
}
