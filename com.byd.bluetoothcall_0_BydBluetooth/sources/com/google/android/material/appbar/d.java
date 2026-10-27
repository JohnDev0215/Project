package com.google.android.material.appbar;

import android.view.View;
import b.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f3604a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3607d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3608e;
    private boolean f = true;
    private boolean g = true;

    public d(View view) {
        this.f3604a = view;
    }

    void a() {
        View view = this.f3604a;
        t.V(view, this.f3607d - (view.getTop() - this.f3605b));
        View view2 = this.f3604a;
        t.U(view2, this.f3608e - (view2.getLeft() - this.f3606c));
    }

    public int b() {
        return this.f3605b;
    }

    public int c() {
        return this.f3607d;
    }

    void d() {
        this.f3605b = this.f3604a.getTop();
        this.f3606c = this.f3604a.getLeft();
    }

    public boolean e(int i) {
        if (!this.g || this.f3608e == i) {
            return false;
        }
        this.f3608e = i;
        a();
        return true;
    }

    public boolean f(int i) {
        if (!this.f || this.f3607d == i) {
            return false;
        }
        this.f3607d = i;
        a();
        return true;
    }
}
