package c.b.a.a.n;

import android.view.View;
import b.h.m.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f4049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4050b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4051c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4052d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4053e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4054f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4055g = true;

    public d(View view) {
        this.f4049a = view;
    }

    public void a() {
        View view = this.f4049a;
        t.V(view, this.f4052d - (view.getTop() - this.f4050b));
        View view2 = this.f4049a;
        t.U(view2, this.f4053e - (view2.getLeft() - this.f4051c));
    }

    public int b() {
        return this.f4050b;
    }

    public int c() {
        return this.f4052d;
    }

    public void d() {
        this.f4050b = this.f4049a.getTop();
        this.f4051c = this.f4049a.getLeft();
    }

    public boolean e(int i2) {
        if (!this.f4055g || this.f4053e == i2) {
            return false;
        }
        this.f4053e = i2;
        a();
        return true;
    }

    public boolean f(int i2) {
        if (!this.f4054f || this.f4052d == i2) {
            return false;
        }
        this.f4052d = i2;
        a();
        return true;
    }
}
