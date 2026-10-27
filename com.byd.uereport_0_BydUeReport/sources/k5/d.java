package k5;

import android.view.View;
import o0.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f5894a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5895b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5896c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5897d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5898e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5899f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5900g = true;

    public d(View view) {
        this.f5894a = view;
    }

    public void a() {
        View view = this.f5894a;
        t.W(view, this.f5897d - (view.getTop() - this.f5895b));
        View view2 = this.f5894a;
        t.V(view2, this.f5898e - (view2.getLeft() - this.f5896c));
    }

    public int b() {
        return this.f5895b;
    }

    public int c() {
        return this.f5897d;
    }

    public void d() {
        this.f5895b = this.f5894a.getTop();
        this.f5896c = this.f5894a.getLeft();
    }

    public boolean e(int i8) {
        if (!this.f5900g || this.f5898e == i8) {
            return false;
        }
        this.f5898e = i8;
        a();
        return true;
    }

    public boolean f(int i8) {
        if (!this.f5899f || this.f5897d == i8) {
            return false;
        }
        this.f5897d = i8;
        a();
        return true;
    }
}
