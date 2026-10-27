package K;

import D.AbstractC0120b;
import O.InterfaceC0480k;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import a.AbstractC0904a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0334d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f3243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f3244b;

    static {
        float f10 = 25;
        f3243a = f10;
        f3244b = (f10 * 2.0f) / 2.4142137f;
    }

    public static final void a(InterfaceC0480k interfaceC0480k, g0.o oVar, long j3, C0761q c0761q, int i7) {
        int i10;
        int i11;
        c0761q.S(1776202187);
        if ((i7 & 6) == 0) {
            i10 = ((i7 & 8) == 0 ? c0761q.f(interfaceC0480k) : c0761q.h(interfaceC0480k) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                i11 = i10 & (-897);
                j3 = 9205357640488583168L;
            } else {
                c0761q.K();
                i11 = i10 & (-897);
            }
            c0761q.q();
            int i12 = i11 & 14;
            boolean z6 = i12 == 4 || ((i11 & 8) != 0 && c0761q.h(interfaceC0480k));
            Object objG = c0761q.G();
            if (z6 || objG == C0753m.f8270a) {
                objG = new A.F(interfaceC0480k, 19);
                c0761q.a0(objG);
            }
            AbstractC0904a.e(interfaceC0480k, g0.b.f14662m, b0.g.b(-1653527038, new R.G(2, j3, L0.k.a(oVar, false, (InterfaceC2782k) objG)), c0761q), c0761q, i12 | 432);
        }
        long j10 = j3;
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new R.F(interfaceC0480k, oVar, j10, i7);
        }
    }

    public static final void b(g0.o oVar, C0761q c0761q, int i7, int i10) {
        int i11;
        c0761q.S(694251107);
        int i12 = i10 & 1;
        if (i12 != 0) {
            i11 = i7 | 6;
        } else if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i11 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            if (i12 != 0) {
                oVar = g0.l.f14684a;
            }
            AbstractC0120b.b(c0761q, g0.a.b(androidx.compose.foundation.layout.c.m(oVar, f3244b, f3243a), C0332c.f3241m));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0328a(oVar, i7, i10);
        }
    }
}
