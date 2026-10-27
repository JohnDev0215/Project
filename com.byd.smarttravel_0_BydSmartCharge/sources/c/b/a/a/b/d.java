package c.b.a.a.b;

import android.view.View;
import b.g.j.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1678a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1679b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1680c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1681d;
    public int e;
    public boolean f = true;
    public boolean g = true;

    public d(View view) {
        this.f1678a = view;
    }

    public void a() {
        View view = this.f1678a;
        t.V(view, this.f1681d - (view.getTop() - this.f1679b));
        View view2 = this.f1678a;
        t.U(view2, this.e - (view2.getLeft() - this.f1680c));
    }

    public int b() {
        return this.f1679b;
    }

    public int c() {
        return this.f1681d;
    }

    public void d() {
        this.f1679b = this.f1678a.getTop();
        this.f1680c = this.f1678a.getLeft();
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
        if (!this.f || this.f1681d == i) {
            return false;
        }
        this.f1681d = i;
        a();
        return true;
    }
}
