package com.google.android.material.appbar;

import android.view.View;
import c.g.k.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f3362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3365d;
    private int e;
    private boolean f = true;
    private boolean g = true;

    public d(View view) {
        this.f3362a = view;
    }

    void a() {
        View view = this.f3362a;
        t.V(view, this.f3365d - (view.getTop() - this.f3363b));
        View view2 = this.f3362a;
        t.U(view2, this.e - (view2.getLeft() - this.f3364c));
    }

    public int b() {
        return this.f3363b;
    }

    public int c() {
        return this.f3365d;
    }

    void d() {
        this.f3363b = this.f3362a.getTop();
        this.f3364c = this.f3362a.getLeft();
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
        if (!this.f || this.f3365d == i) {
            return false;
        }
        this.f3365d = i;
        a();
        return true;
    }
}
