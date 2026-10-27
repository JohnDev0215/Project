package com.google.android.material.appbar;

import a.g.k.t;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f2970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2973d;
    private int e;
    private boolean f = true;
    private boolean g = true;

    public d(View view) {
        this.f2970a = view;
    }

    void a() {
        View view = this.f2970a;
        t.T(view, this.f2973d - (view.getTop() - this.f2971b));
        View view2 = this.f2970a;
        t.S(view2, this.e - (view2.getLeft() - this.f2972c));
    }

    public int b() {
        return this.f2973d;
    }

    void c() {
        this.f2971b = this.f2970a.getTop();
        this.f2972c = this.f2970a.getLeft();
    }

    public boolean d(int i) {
        if (!this.g || this.e == i) {
            return false;
        }
        this.e = i;
        a();
        return true;
    }

    public boolean e(int i) {
        if (!this.f || this.f2973d == i) {
            return false;
        }
        this.f2973d = i;
        a();
        return true;
    }
}
