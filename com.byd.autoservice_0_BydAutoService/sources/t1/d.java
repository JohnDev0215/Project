package t1;

import android.view.View;
import l0.t;

/* JADX INFO: compiled from: ViewOffsetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f5033a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5034b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5035c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5036d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5037e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5038f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5039g = true;

    public d(View view) {
        this.f5033a = view;
    }

    public void a() {
        View view = this.f5033a;
        t.V(view, this.f5036d - (view.getTop() - this.f5034b));
        View view2 = this.f5033a;
        t.U(view2, this.f5037e - (view2.getLeft() - this.f5035c));
    }

    public int b() {
        return this.f5034b;
    }

    public int c() {
        return this.f5036d;
    }

    public void d() {
        this.f5034b = this.f5033a.getTop();
        this.f5035c = this.f5033a.getLeft();
    }

    public boolean e(int i3) {
        if (!this.f5039g || this.f5037e == i3) {
            return false;
        }
        this.f5037e = i3;
        a();
        return true;
    }

    public boolean f(int i3) {
        if (!this.f5038f || this.f5036d == i3) {
            return false;
        }
        this.f5036d = i3;
        a();
        return true;
    }
}
