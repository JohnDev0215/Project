package x;

import T.C0735d;
import T.C0741g;
import T.C0753m;
import T.C0761q;
import q0.C2162a;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2631e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2649o f23503a = new C2649o(Float.POSITIVE_INFINITY);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2650p f23504b = new C2650p(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    public static final C2651q c = new C2651q(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    public static final r d = new r(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2649o f23505e = new C2649o(Float.NEGATIVE_INFINITY);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2650p f23506f = new C2650p(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2651q f23507g = new C2651q(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final r f23508h = new r(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    public static C2629d a(float f10) {
        return new C2629d(Float.valueOf(f10), E0.f23396a, Float.valueOf(0.01f), 8);
    }

    public static C2648n b(float f10, int i7) {
        if ((i7 & 2) != 0) {
            f10 = 0.0f;
        }
        return new C2648n(E0.f23396a, Float.valueOf(0.0f), new C2649o(f10), Long.MIN_VALUE, Long.MIN_VALUE, false);
    }

    public static final Object c(float f10, float f11, float f12, InterfaceC2647m interfaceC2647m, InterfaceC2785n interfaceC2785n, s7.i iVar) throws Throwable {
        D0 d02 = E0.f23396a;
        Float f13 = new Float(f10);
        Float f14 = new Float(f11);
        C2649o c2649o = new C2649o(new Float(f12).floatValue());
        Object objD = d(new C2648n(d02, f13, c2649o, 56), new p0(interfaceC2647m, d02, f13, f14, c2649o), Long.MIN_VALUE, new C2162a(interfaceC2785n), iVar);
        EnumC2303a enumC2303a = EnumC2303a.l;
        m7.q qVar = m7.q.f19267a;
        if (objD != enumC2303a) {
            objD = qVar;
        }
        return objD == enumC2303a ? objD : qVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d(x.C2648n r24, x.InterfaceC2639i r25, long r26, z7.InterfaceC2782k r28, s7.AbstractC2410c r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x.AbstractC2631e.d(x.n, x.i, long, z7.k, s7.c):java.lang.Object");
    }

    public static /* synthetic */ Object e(float f10, float f11, C0 c02, InterfaceC2785n interfaceC2785n, s7.i iVar, int i7) {
        InterfaceC2647m interfaceC2647mP = c02;
        if ((i7 & 8) != 0) {
            interfaceC2647mP = p(0.0f, 7, null);
        }
        return c(f10, f11, 0.0f, interfaceC2647mP, interfaceC2785n, iVar);
    }

    public static final C2620I f(L l, float f10, float f11, C2619H c2619h, String str, C0761q c0761q, int i7, int i10) {
        return h(l, Float.valueOf(f10), Float.valueOf(f11), E0.f23396a, c2619h, (i10 & 8) != 0 ? "FloatAnimation" : str, c0761q, 33208 | ((i7 << 3) & 458752), 0);
    }

    public static Object g(C2648n c2648n, Float f10, C2640i0 c2640i0, boolean z6, InterfaceC2782k interfaceC2782k, s7.i iVar, int i7) throws Throwable {
        if ((i7 & 2) != 0) {
            c2640i0 = p(0.0f, 7, null);
        }
        C2640i0 c2640i02 = c2640i0;
        if ((i7 & 8) != 0) {
            interfaceC2782k = o0.f23585n;
        }
        Object objD = d(c2648n, new p0(c2640i02, c2648n.l, c2648n.f23569m.getValue(), f10, c2648n.f23570n), z6 ? c2648n.o : Long.MIN_VALUE, interfaceC2782k, iVar);
        return objD == EnumC2303a.l ? objD : m7.q.f19267a;
    }

    public static final C2620I h(L l, Number number, Number number2, D0 d02, C2619H c2619h, String str, C0761q c0761q, int i7, int i10) {
        Object objG = c0761q.G();
        T.S s6 = C0753m.f8270a;
        if (objG == s6) {
            objG = new C2620I(l, number, number2, d02, c2619h);
            c0761q.a0(objG);
        }
        C2620I c2620i = (C2620I) objG;
        boolean z6 = true;
        boolean z10 = ((((i7 & 112) ^ 48) > 32 && c0761q.h(number)) || (i7 & 48) == 32) | ((((i7 & 896) ^ 384) > 256 && c0761q.h(number2)) || (i7 & 384) == 256);
        if ((((57344 & i7) ^ 24576) <= 16384 || !c0761q.h(c2619h)) && (i7 & 24576) != 16384) {
            z6 = false;
        }
        boolean z11 = z10 | z6;
        Object objG2 = c0761q.G();
        if (z11 || objG2 == s6) {
            objG2 = new K.G(number, c2620i, number2, c2619h, 3);
            c0761q.a0(objG2);
        }
        C0735d.i((InterfaceC2772a) objG2, c0761q);
        boolean zH = c0761q.h(l);
        Object objG3 = c0761q.G();
        if (zH || objG3 == s6) {
            objG3 = new C0741g(l, 25, c2620i);
            c0761q.a0(objG3);
        }
        C0735d.d(c2620i, (InterfaceC2782k) objG3, c0761q);
        return c2620i;
    }

    public static final AbstractC2652s i(AbstractC2652s abstractC2652s) {
        AbstractC2652s abstractC2652sC = abstractC2652s.c();
        int iB = abstractC2652sC.b();
        for (int i7 = 0; i7 < iB; i7++) {
            abstractC2652sC.e(abstractC2652s.a(i7), i7);
        }
        return abstractC2652sC;
    }

    public static C2648n j(C2648n c2648n, float f10) {
        float f11 = ((C2649o) c2648n.f23570n).f23577a;
        return new C2648n(c2648n.l, Float.valueOf(f10), new C2649o(f11), c2648n.o, c2648n.f23571p, c2648n.f23572q);
    }

    public static final void k(C2645l c2645l, long j3, float f10, InterfaceC2639i interfaceC2639i, C2648n c2648n, InterfaceC2782k interfaceC2782k) {
        long jC = f10 == 0.0f ? interfaceC2639i.c() : (long) ((j3 - c2645l.c) / f10);
        c2645l.f23556g = j3;
        c2645l.f23554e.setValue(interfaceC2639i.b(jC));
        c2645l.f23555f = interfaceC2639i.f(jC);
        if (interfaceC2639i.g(jC)) {
            c2645l.f23557h = c2645l.f23556g;
            c2645l.f23558i.setValue(Boolean.FALSE);
        }
        r(c2645l, c2648n);
        interfaceC2782k.b(c2645l);
    }

    public static final float l(InterfaceC2194h interfaceC2194h) {
        g0.p pVar = (g0.p) interfaceC2194h.F(g0.b.f14661A);
        float fC = pVar != null ? pVar.C() : 1.0f;
        if (fC >= 0.0f) {
            return fC;
        }
        throw new IllegalStateException("negative scale factor");
    }

    public static C2619H m(InterfaceC2659z interfaceC2659z, int i7, int i10) {
        if ((i10 & 2) != 0) {
            i7 = 1;
        }
        return new C2619H(interfaceC2659z, i7, 0);
    }

    public static final L n(String str, C0761q c0761q, int i7) {
        Object objG = c0761q.G();
        if (objG == C0753m.f8270a) {
            objG = new L();
            c0761q.a0(objG);
        }
        L l = (L) objG;
        l.a(0, c0761q);
        return l;
    }

    public static C2636g0 o() {
        return new C2636g0(0);
    }

    public static C2640i0 p(float f10, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            f10 = 1500.0f;
        }
        if ((i7 & 4) != 0) {
            obj = null;
        }
        return new C2640i0(1.0f, f10, obj);
    }

    public static C0 q(int i7, int i10, InterfaceC2612A interfaceC2612A, int i11) {
        if ((i11 & 1) != 0) {
            i7 = 300;
        }
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        if ((i11 & 4) != 0) {
            interfaceC2612A = AbstractC2614C.f23390a;
        }
        return new C0(i7, i10, interfaceC2612A);
    }

    public static final void r(C2645l c2645l, C2648n c2648n) {
        c2648n.f23569m.setValue(c2645l.f23554e.getValue());
        AbstractC2652s abstractC2652s = c2648n.f23570n;
        AbstractC2652s abstractC2652s2 = c2645l.f23555f;
        int iB = abstractC2652s.b();
        for (int i7 = 0; i7 < iB; i7++) {
            abstractC2652s.e(abstractC2652s2.a(i7), i7);
        }
        c2648n.f23571p = c2645l.f23557h;
        c2648n.o = c2645l.f23556g;
        c2648n.f23572q = ((Boolean) c2645l.f23558i.getValue()).booleanValue();
    }

    public static final Object s(InterfaceC2782k interfaceC2782k, AbstractC2410c abstractC2410c) {
        if (abstractC2410c.getContext().F(F0.B0.l) == null) {
            return C0735d.I(abstractC2410c.getContext()).o(interfaceC2782k, abstractC2410c);
        }
        throw new ClassCastException();
    }
}
