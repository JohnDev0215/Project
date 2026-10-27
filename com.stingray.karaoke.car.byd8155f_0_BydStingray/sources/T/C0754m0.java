package T;

import u.C2481u;
import u.C2484x;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0754m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8271a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0768u f8272b;
    public C0733c c;
    public InterfaceC2785n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8273e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C2481u f8274f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C2484x f8275g;

    public C0754m0(C0768u c0768u) {
        this.f8272b = c0768u;
    }

    public static boolean a(D d, C2484x c2484x) {
        A7.m.d(d, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
        G0 g02 = d.f8182n;
        if (g02 == null) {
            g02 = S.f8224q;
        }
        return !g02.a(d.j().f8179f, c2484x.e(d));
    }

    public final boolean b() {
        if (this.f8272b == null) {
            return false;
        }
        C0733c c0733c = this.c;
        return c0733c != null ? c0733c.a() : false;
    }

    public final int c(Object obj) {
        int iP;
        C0768u c0768u = this.f8272b;
        if (c0768u == null || (iP = c0768u.p(this, obj)) == 0) {
            return 1;
        }
        return iP;
    }

    public final void d() {
        C0768u c0768u = this.f8272b;
        if (c0768u != null) {
            c0768u.f8382z = true;
        }
        this.f8272b = null;
        this.f8274f = null;
        this.f8275g = null;
    }

    public final void e(boolean z6) {
        if (z6) {
            this.f8271a |= 32;
        } else {
            this.f8271a &= -33;
        }
    }
}
