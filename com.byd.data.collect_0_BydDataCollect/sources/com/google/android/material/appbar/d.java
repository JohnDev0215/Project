package com.google.android.material.appbar;

import android.view.View;
import b.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f3690a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3691b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3692c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3693d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3694e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3695f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3696g = true;

    public d(View view) {
        this.f3690a = view;
    }

    void a() {
        View view = this.f3690a;
        t.V(view, this.f3693d - (view.getTop() - this.f3691b));
        View view2 = this.f3690a;
        t.U(view2, this.f3694e - (view2.getLeft() - this.f3692c));
    }

    public int b() {
        return this.f3691b;
    }

    public int c() {
        return this.f3693d;
    }

    void d() {
        this.f3691b = this.f3690a.getTop();
        this.f3692c = this.f3690a.getLeft();
    }

    public boolean e(int i2) {
        if (!this.f3696g || this.f3694e == i2) {
            return false;
        }
        this.f3694e = i2;
        a();
        return true;
    }

    public boolean f(int i2) {
        if (!this.f3695f || this.f3693d == i2) {
            return false;
        }
        this.f3693d = i2;
        a();
        return true;
    }
}
