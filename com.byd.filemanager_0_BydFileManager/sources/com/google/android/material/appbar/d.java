package com.google.android.material.appbar;

import android.view.View;
import b.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f4003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4004b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4005c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4006d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4007e;
    private boolean f = true;
    private boolean g = true;

    public d(View view) {
        this.f4003a = view;
    }

    void a() {
        View view = this.f4003a;
        t.V(view, this.f4006d - (view.getTop() - this.f4004b));
        View view2 = this.f4003a;
        t.U(view2, this.f4007e - (view2.getLeft() - this.f4005c));
    }

    public int b() {
        return this.f4004b;
    }

    public int c() {
        return this.f4006d;
    }

    void d() {
        this.f4004b = this.f4003a.getTop();
        this.f4005c = this.f4003a.getLeft();
    }

    public boolean e(int i) {
        if (!this.g || this.f4007e == i) {
            return false;
        }
        this.f4007e = i;
        a();
        return true;
    }

    public boolean f(int i) {
        if (!this.f || this.f4006d == i) {
            return false;
        }
        this.f4006d = i;
        a();
        return true;
    }
}
