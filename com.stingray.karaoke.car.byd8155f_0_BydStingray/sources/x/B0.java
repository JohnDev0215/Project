package x;

import T.C0735d;
import T.C0741g;
import T.C0753m;
import T.C0761q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class B0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f23389a = h6.r.n(m7.g.f19257m, y0.f23671m);

    /* JADX WARN: Type inference failed for: r10v3, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r7v5, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r9v3, types: [A7.o, z7.k] */
    public static final r0 a(x0 x0Var, D0 d02, String str, C0761q c0761q, int i7, int i10) {
        q0 q0Var;
        if ((i10 & 2) != 0) {
            str = "DeferredAnimation";
        }
        int i11 = (i7 & 14) ^ 6;
        boolean z6 = true;
        boolean z10 = (i11 > 4 && c0761q.f(x0Var)) || (i7 & 6) == 4;
        Object objG = c0761q.G();
        Object obj = C0753m.f8270a;
        if (z10 || objG == obj) {
            objG = new r0(x0Var, d02, str);
            c0761q.a0(objG);
        }
        r0 r0Var = (r0) objG;
        if ((i11 <= 4 || !c0761q.f(x0Var)) && (i7 & 6) != 4) {
            z6 = false;
        }
        boolean zH = c0761q.h(r0Var) | z6;
        Object objG2 = c0761q.G();
        if (zH || objG2 == obj) {
            objG2 = new C0741g(x0Var, 28, r0Var);
            c0761q.a0(objG2);
        }
        C0735d.d(r0Var, (InterfaceC2782k) objG2, c0761q);
        if (x0Var.g() && (q0Var = (q0) r0Var.f23614b.getValue()) != null) {
            ?? r72 = q0Var.f23610n;
            x0 x0Var2 = r0Var.c;
            q0Var.l.h(r72.b(x0Var2.f().a()), q0Var.f23610n.b(x0Var2.f().c()), (InterfaceC2615D) q0Var.f23609m.b(x0Var2.f()));
        }
        return r0Var;
    }

    public static final u0 b(x0 x0Var, Object obj, Object obj2, InterfaceC2615D interfaceC2615D, D0 d02, C0761q c0761q, int i7) {
        boolean zF = c0761q.f(x0Var);
        Object objG = c0761q.G();
        Object obj3 = C0753m.f8270a;
        if (zF || objG == obj3) {
            AbstractC2652s abstractC2652s = (AbstractC2652s) d02.f23394a.b(obj2);
            abstractC2652s.d();
            objG = new u0(x0Var, obj, abstractC2652s, d02);
            c0761q.a0(objG);
        }
        u0 u0Var = (u0) objG;
        if (x0Var.g()) {
            u0Var.h(obj, obj2, interfaceC2615D);
        } else {
            u0Var.i(obj2, interfaceC2615D);
        }
        boolean zF2 = c0761q.f(x0Var) | c0761q.f(u0Var);
        Object objG2 = c0761q.G();
        if (zF2 || objG2 == obj3) {
            objG2 = new C0741g(x0Var, 29, u0Var);
            c0761q.a0(objG2);
        }
        C0735d.d(u0Var, (InterfaceC2782k) objG2, c0761q);
        return u0Var;
    }

    public static final x0 c(Object obj, String str, C0761q c0761q, int i7) {
        Object objG = c0761q.G();
        T.S s6 = C0753m.f8270a;
        if (objG == s6) {
            objG = new x0(new P(obj), null, str);
            c0761q.a0(objG);
        }
        x0 x0Var = (x0) objG;
        x0Var.a(obj, c0761q, (i7 & 8) | 48 | (i7 & 14));
        Object objG2 = c0761q.G();
        if (objG2 == s6) {
            objG2 = new w.v(x0Var, 2);
            c0761q.a0(objG2);
        }
        C0735d.d(x0Var, (InterfaceC2782k) objG2, c0761q);
        return x0Var;
    }
}
