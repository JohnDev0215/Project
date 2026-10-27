package C0;

import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f870a;

    /* JADX WARN: Multi-variable type inference failed */
    public static final void a(U u4, V v10) {
        u4.getClass();
        if (v10 instanceof E0.W) {
            ((E0.W) v10).H(u4.f870a);
        }
    }

    public static void d(U u4, V v10, int i7, int i10) {
        u4.getClass();
        long jD = AbstractC2673a.d(i7, i10);
        a(u4, v10);
        v10.l0(a1.h.d(jD, v10.f873p), 0.0f, null);
    }

    public static void e(U u4, V v10, long j3) {
        u4.getClass();
        a(u4, v10);
        v10.l0(a1.h.d(j3, v10.f873p), 0.0f, null);
    }

    public static void f(U u4, V v10, int i7, int i10) {
        long jD = AbstractC2673a.d(i7, i10);
        if (u4.b() == a1.k.l || u4.c() == 0) {
            a(u4, v10);
            v10.l0(a1.h.d(jD, v10.f873p), 0.0f, null);
        } else {
            long jD2 = AbstractC2673a.d((u4.c() - v10.l) - ((int) (jD >> 32)), (int) (jD & 4294967295L));
            a(u4, v10);
            v10.l0(a1.h.d(jD2, v10.f873p), 0.0f, null);
        }
    }

    public static void g(U u4, V v10, int i7, int i10) {
        int i11 = W.f875b;
        C0113u c0113u = C0113u.o;
        long jD = AbstractC2673a.d(i7, i10);
        if (u4.b() == a1.k.l || u4.c() == 0) {
            a(u4, v10);
            v10.l0(a1.h.d(jD, v10.f873p), 0.0f, c0113u);
        } else {
            long jD2 = AbstractC2673a.d((u4.c() - v10.l) - ((int) (jD >> 32)), (int) (jD & 4294967295L));
            a(u4, v10);
            v10.l0(a1.h.d(jD2, v10.f873p), 0.0f, c0113u);
        }
    }

    public static void h(U u4, V v10, int i7, int i10, InterfaceC2782k interfaceC2782k, int i11) {
        if ((i11 & 8) != 0) {
            int i12 = W.f875b;
            interfaceC2782k = C0113u.o;
        }
        u4.getClass();
        long jD = AbstractC2673a.d(i7, i10);
        a(u4, v10);
        v10.l0(a1.h.d(jD, v10.f873p), 0.0f, interfaceC2782k);
    }

    public static void i(U u4, V v10, long j3) {
        int i7 = W.f875b;
        C0113u c0113u = C0113u.o;
        u4.getClass();
        a(u4, v10);
        v10.l0(a1.h.d(j3, v10.f873p), 0.0f, c0113u);
    }

    public abstract a1.k b();

    public abstract int c();
}
