package Q;

import T.C0754m0;
import T.C0761q;
import T.C0773y;
import n0.C1891t;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0496b {
    static {
        C0773y c0773y = e1.m.f13798a;
    }

    public static final void a(b0.b bVar, InterfaceC2772a interfaceC2772a, g0.o oVar, InterfaceC2785n interfaceC2785n, InterfaceC2785n interfaceC2785n2, boolean z6, C0543z c0543z, D.L l, C.k kVar, C0761q c0761q, int i7) {
        int i10;
        boolean z10;
        int i11;
        InterfaceC2785n interfaceC2785n3;
        C.k kVar2;
        C0543z c0543z2;
        InterfaceC2785n interfaceC2785n4;
        boolean z11;
        C0543z c0543z3;
        C.k kVar3;
        c0761q.S(1826340448);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(bVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        int i12 = i10 | 3072;
        if ((i7 & 24576) == 0) {
            i12 |= c0761q.h(interfaceC2785n2) ? 16384 : 8192;
        }
        int i13 = 196608 | i12;
        if ((1572864 & i7) == 0) {
            i13 = 720896 | i12;
        }
        if ((12582912 & i7) == 0) {
            i13 |= c0761q.f(l) ? 8388608 : 4194304;
        }
        int i14 = 100663296 | i13;
        if ((38347923 & i14) == 38347922 && c0761q.x()) {
            c0761q.K();
            interfaceC2785n4 = interfaceC2785n;
            z11 = z6;
            c0543z3 = c0543z;
            kVar3 = kVar;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                int i15 = AbstractC0541y.f6070a;
                C0516l c0516l = (C0516l) c0761q.k(AbstractC0520n.f5997a);
                C0543z c0543z4 = c0516l.f5955M;
                if (c0543z4 == null) {
                    c0543z4 = new C0543z(AbstractC0520n.b(c0516l, S.g.f7648i), AbstractC0520n.b(c0516l, S.g.f7649j), AbstractC0520n.b(c0516l, S.g.f7650k), C1891t.b(S.g.f7642a, AbstractC0520n.b(c0516l, S.g.f7645f)), C1891t.b(S.g.f7643b, AbstractC0520n.b(c0516l, S.g.f7646g)), C1891t.b(S.g.c, AbstractC0520n.b(c0516l, S.g.f7647h)));
                    c0516l.f5955M = c0543z4;
                }
                z10 = true;
                i11 = i14 & (-3670017);
                interfaceC2785n3 = null;
                kVar2 = null;
                c0543z2 = c0543z4;
            } else {
                c0761q.K();
                i11 = i14 & (-3670017);
                interfaceC2785n3 = interfaceC2785n;
                z10 = z6;
                c0543z2 = c0543z;
                kVar2 = kVar;
            }
            c0761q.q();
            C.a(bVar, interfaceC2772a, oVar, interfaceC2785n3, interfaceC2785n2, z10, c0543z2, l, kVar2, c0761q, i11 & 268435454);
            interfaceC2785n4 = interfaceC2785n3;
            z11 = z10;
            c0543z3 = c0543z2;
            kVar3 = kVar2;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0494a(bVar, interfaceC2772a, oVar, interfaceC2785n4, interfaceC2785n2, z11, c0543z3, l, kVar3, i7, 0);
        }
    }
}
