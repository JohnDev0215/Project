package x;

import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.Q0;
import d8.C1134u;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2637h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2640i0 f23537a = AbstractC2631e.p(0.0f, 7, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2640i0 f23538b;

    static {
        Object obj = L0.f23437a;
        f23538b = AbstractC2631e.p(0.0f, 3, new a1.e(0.1f));
        h6.j.f(0.5f, 0.5f);
        h6.e.a(0.5f, 0.5f);
    }

    public static final Q0 a(float f10, C0 c02, String str, C0761q c0761q, int i7, int i10) {
        InterfaceC2647m interfaceC2647m = c02;
        if ((i10 & 2) != 0) {
            interfaceC2647m = f23538b;
        }
        InterfaceC2647m interfaceC2647m2 = interfaceC2647m;
        if ((i10 & 4) != 0) {
            str = "DpAnimation";
        }
        return c(new a1.e(f10), E0.c, interfaceC2647m2, null, str, null, c0761q, ((i7 << 3) & 896) | ((i7 << 6) & 57344), 8);
    }

    public static final Q0 b(float f10, InterfaceC2659z interfaceC2659z, String str, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7, int i10) {
        InterfaceC2647m interfaceC2647m;
        int i11 = i10 & 2;
        C2640i0 c2640i0 = f23537a;
        InterfaceC2647m interfaceC2647m2 = i11 != 0 ? c2640i0 : interfaceC2659z;
        InterfaceC2782k interfaceC2782k2 = (i10 & 16) != 0 ? null : interfaceC2782k;
        if (interfaceC2647m2 == c2640i0) {
            c0761q.Q(1125598679);
            boolean zC = c0761q.c(0.01f);
            Object objG = c0761q.G();
            if (zC || objG == C0753m.f8270a) {
                objG = AbstractC2631e.p(0.0f, 3, Float.valueOf(0.01f));
                c0761q.a0(objG);
            }
            c0761q.p(false);
            interfaceC2647m = (C2640i0) objG;
        } else {
            c0761q.Q(1125708605);
            c0761q.p(false);
            interfaceC2647m = interfaceC2647m2;
        }
        return c(Float.valueOf(f10), E0.f23396a, interfaceC2647m, Float.valueOf(0.01f), str, interfaceC2782k2, c0761q, 24576, 0);
    }

    public static final Q0 c(Object obj, D0 d02, InterfaceC2647m interfaceC2647m, Float f10, String str, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7, int i10) {
        InterfaceC2647m c2640i0 = interfaceC2647m;
        Object obj2 = C0753m.f8270a;
        Float f11 = (i10 & 8) != 0 ? null : f10;
        Object objG = c0761q.G();
        if (objG == obj2) {
            objG = C0735d.O(null, T.S.f8224q);
            c0761q.a0(objG);
        }
        T.X x8 = (T.X) objG;
        Object objG2 = c0761q.G();
        if (objG2 == obj2) {
            objG2 = new C2629d(obj, d02, f11);
            c0761q.a0(objG2);
        }
        C2629d c2629d = (C2629d) objG2;
        T.X xR = C0735d.R(interfaceC2782k, c0761q);
        if (f11 != null && (c2640i0 instanceof C2640i0)) {
            C2640i0 c2640i02 = (C2640i0) c2640i0;
            if (!A7.m.a(c2640i02.c, f11)) {
                c2640i0 = new C2640i0(c2640i02.f23544a, c2640i02.f23545b, f11);
            }
        }
        T.X xR2 = C0735d.R(c2640i0, c0761q);
        Object objG3 = c0761q.G();
        if (objG3 == obj2) {
            objG3 = AbstractC2673a.a(-1, 6, null);
            c0761q.a0(objG3);
        }
        U8.p pVar = (U8.p) objG3;
        boolean zH = c0761q.h(pVar) | ((((i7 & 14) ^ 6) > 4 && c0761q.h(obj)) || (i7 & 6) == 4);
        Object objG4 = c0761q.G();
        if (zH || objG4 == obj2) {
            objG4 = new C1134u(pVar, 4, obj);
            c0761q.a0(objG4);
        }
        C0735d.i((InterfaceC2772a) objG4, c0761q);
        boolean zH2 = c0761q.h(pVar) | c0761q.h(c2629d) | c0761q.f(xR2) | c0761q.f(xR);
        Object objG5 = c0761q.G();
        if (zH2 || objG5 == obj2) {
            objG5 = new C2635g(pVar, c2629d, xR2, xR, null);
            c0761q.a0(objG5);
        }
        C0735d.f(c0761q, pVar, (InterfaceC2785n) objG5);
        Q0 q02 = (Q0) x8.getValue();
        return q02 == null ? c2629d.c : q02;
    }
}
