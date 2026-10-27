package Q;

import T.C0754m0;
import T.C0761q;
import x.AbstractC2614C;
import x.AbstractC2631e;
import x.C2656w;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class K0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5570a = 90;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final x.C0 f5571b;

    static {
        C2656w c2656w = AbstractC2614C.f23390a;
        f5571b = AbstractC2631e.q(250, 0, c2656w, 2);
        AbstractC2631e.q(250, 0, c2656w, 2);
    }

    public static final void a(int i7, g0.o oVar, long j3, long j10, float f10, InterfaceC2786o interfaceC2786o, InterfaceC2785n interfaceC2785n, b0.b bVar, C0761q c0761q, int i10) {
        int i11;
        int i12;
        long jC;
        long j11;
        c0761q.S(-497821003);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.d(i7) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.c(f10) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2786o) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 1048576 : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= c0761q.h(bVar) ? 8388608 : 4194304;
        }
        if ((4793491 & i11) == 4793490 && c0761q.x()) {
            c0761q.K();
            j11 = j10;
        } else {
            c0761q.M();
            if ((i10 & 1) == 0 || c0761q.w()) {
                int i13 = F0.f5522a;
                i12 = i11 & (-7169);
                jC = AbstractC0520n.c(S.j.f7697a, c0761q);
            } else {
                c0761q.K();
                i12 = i11 & (-7169);
                jC = j10;
            }
            c0761q.q();
            int i14 = i12 << 3;
            b(i7, interfaceC2786o, oVar, j3, jC, f10, interfaceC2785n, bVar, h6.r.q(c0761q), c0761q, (i12 & 14) | ((i12 >> 12) & 112) | (i14 & 896) | (i14 & 7168) | (i14 & 458752) | (3670016 & i12) | (i12 & 29360128));
            j11 = jC;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G0(i7, oVar, j3, j11, f10, interfaceC2786o, interfaceC2785n, bVar, i10);
        }
    }

    public static final void b(int i7, InterfaceC2786o interfaceC2786o, g0.o oVar, long j3, long j10, float f10, InterfaceC2785n interfaceC2785n, b0.b bVar, y.y0 y0Var, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(-955409947);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.d(i7) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2786o) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.e(j3) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.e(j10) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.c(f10) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 1048576 : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i11 |= c0761q.h(bVar) ? 8388608 : 4194304;
        }
        if ((100663296 & i10) == 0) {
            i11 |= c0761q.f(y0Var) ? 67108864 : 33554432;
        }
        if ((38347923 & i11) == 38347922 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.M();
            if ((i10 & 1) != 0 && !c0761q.w()) {
                c0761q.K();
            }
            c0761q.q();
            int i12 = ((i11 >> 6) & 14) | 12582912;
            int i13 = i11 >> 3;
            AbstractC0530s0.a(oVar, null, j3, j10, b0.g.b(-1572959552, new I0(y0Var, f10, bVar, interfaceC2785n, interfaceC2786o, i7), c0761q), c0761q, i12 | (i13 & 896) | (i13 & 7168), 114);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new J0(i7, interfaceC2786o, oVar, j3, j10, f10, interfaceC2785n, bVar, y0Var, i10);
        }
    }
}
