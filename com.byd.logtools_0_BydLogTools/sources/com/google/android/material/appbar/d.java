package com.google.android.material.appbar;

import android.view.View;
import b.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f3309a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3312d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3313e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3314f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3315g = true;

    public d(View view) {
        this.f3309a = view;
    }

    void a() {
        View view = this.f3309a;
        t.V(view, this.f3312d - (view.getTop() - this.f3310b));
        View view2 = this.f3309a;
        t.U(view2, this.f3313e - (view2.getLeft() - this.f3311c));
    }

    public int b() {
        return this.f3310b;
    }

    public int c() {
        return this.f3312d;
    }

    void d() {
        this.f3310b = this.f3309a.getTop();
        this.f3311c = this.f3309a.getLeft();
    }

    public boolean e(int i) {
        if (!this.f3315g || this.f3313e == i) {
            return false;
        }
        this.f3313e = i;
        a();
        return true;
    }

    public boolean f(int i) {
        if (!this.f3314f || this.f3312d == i) {
            return false;
        }
        this.f3312d = i;
        a();
        return true;
    }
}
