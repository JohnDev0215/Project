package G;

import T.C0732b0;
import T.C0735d;
import T.C0738e0;

/* JADX INFO: loaded from: classes.dex */
public final class Q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f2520b;
    public final C0732b0 c = C0735d.M(-1);
    public final C0732b0 d = C0735d.M(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f2521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f2522f;

    public Q(Object obj, S s6) {
        this.f2519a = obj;
        this.f2520b = s6;
        T.S s10 = T.S.f8224q;
        this.f2521e = C0735d.O(null, s10);
        this.f2522f = C0735d.O(null, s10);
    }

    public final Q a() {
        C0732b0 c0732b0 = this.d;
        if (c0732b0.i() == 0) {
            this.f2520b.l.add(this);
            Q q4 = (Q) this.f2522f.getValue();
            if (q4 != null) {
                q4.a();
            } else {
                q4 = null;
            }
            this.f2521e.setValue(q4);
        }
        c0732b0.j(c0732b0.i() + 1);
        return this;
    }

    public final void b() {
        C0732b0 c0732b0 = this.d;
        if (c0732b0.i() <= 0) {
            throw new IllegalStateException("Release should only be called once");
        }
        c0732b0.j(c0732b0.i() - 1);
        if (c0732b0.i() == 0) {
            this.f2520b.l.remove(this);
            C0738e0 c0738e0 = this.f2521e;
            Q q4 = (Q) c0738e0.getValue();
            if (q4 != null) {
                q4.b();
            }
            c0738e0.setValue(null);
        }
    }
}
