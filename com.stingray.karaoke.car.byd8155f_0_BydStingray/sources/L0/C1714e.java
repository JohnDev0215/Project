package l0;

import F0.C0223q;
import X5.F;
import u.AbstractC2457E;
import u.C2453A;

/* JADX INFO: renamed from: l0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1714e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0223q f18327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final F f18328b;
    public final C2453A c;
    public final C2453A d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2453A f18329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2453A f18330f;

    public C1714e(C0223q c0223q, F f10) {
        this.f18327a = c0223q;
        this.f18328b = f10;
        int i7 = AbstractC2457E.f22585a;
        this.c = new C2453A();
        this.d = new C2453A();
        this.f18329e = new C2453A();
        this.f18330f = new C2453A();
    }

    public final boolean a() {
        return this.c.h() || this.f18329e.h() || this.d.h();
    }

    public final void b(C2453A c2453a, Object obj) {
        if (c2453a.a(obj) && this.c.d + this.d.d + this.f18329e.d == 1) {
            this.f18327a.b(new F(0, this, C1714e.class, "invalidateNodes", "invalidateNodes()V", 0, 8));
        }
    }
}
