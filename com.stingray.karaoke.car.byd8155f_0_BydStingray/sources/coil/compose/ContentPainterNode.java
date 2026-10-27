package coil.compose;

import A3.r;
import A3.z;
import C0.InterfaceC0103j;
import C0.J;
import C0.L;
import C0.M;
import C0.V;
import C0.a0;
import C0.c0;
import E0.H;
import E0.InterfaceC0165o;
import E0.InterfaceC0174y;
import E0.Q;
import L3.e;
import a1.a;
import g0.c;
import g0.n;
import h6.j;
import kotlin.Metadata;
import l3.C1764u;
import m0.f;
import n0.C1883l;
import n7.v;
import p0.C2071b;
import r0.AbstractC2268a;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcoil/compose/ContentPainterNode;", "Lg0/n;", "LE0/o;", "LE0/y;", "Lr0/a;", "painter", "Lr0/a;", "I0", "()Lr0/a;", "K0", "(Lr0/a;)V", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ContentPainterNode extends n implements InterfaceC0165o, InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f12648A;
    public C1883l B;
    private AbstractC2268a painter;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public c f12649y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public InterfaceC0103j f12650z;

    public ContentPainterNode(AbstractC2268a abstractC2268a, c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l) {
        this.painter = abstractC2268a;
        this.f12649y = cVar;
        this.f12650z = interfaceC0103j;
        this.f12648A = f10;
        this.B = c1883l;
    }

    public final long H0(long j3) {
        if (f.e(j3)) {
            return 0L;
        }
        long jH = this.painter.h();
        if (jH == 9205357640488583168L) {
            return j3;
        }
        float fD = f.d(jH);
        if (Float.isInfinite(fD) || Float.isNaN(fD)) {
            fD = f.d(j3);
        }
        float fB = f.b(jH);
        if (Float.isInfinite(fB) || Float.isNaN(fB)) {
            fB = f.b(j3);
        }
        long jF = j.f(fD, fB);
        long jA = this.f12650z.a(jF, j3);
        float fA = a0.a(jA);
        if (!Float.isInfinite(fA) && !Float.isNaN(fA)) {
            float fB2 = a0.b(jA);
            if (!Float.isInfinite(fB2) && !Float.isNaN(fB2)) {
                return c0.j(jF, jA);
            }
        }
        return j3;
    }

    /* JADX INFO: renamed from: I0, reason: from getter */
    public final AbstractC2268a getPainter() {
        return this.painter;
    }

    public final long J0(long j3) {
        float fJ;
        int i7;
        float fU;
        boolean zF = a.f(j3);
        boolean zE = a.e(j3);
        if (zF && zE) {
            return j3;
        }
        boolean z6 = a.d(j3) && a.c(j3);
        long jH = this.painter.h();
        if (jH == 9205357640488583168L) {
            return z6 ? a.a(j3, a.h(j3), 0, a.g(j3), 0, 10) : j3;
        }
        if (z6 && (zF || zE)) {
            fJ = a.h(j3);
            i7 = a.g(j3);
        } else {
            float fD = f.d(jH);
            float fB = f.b(jH);
            if (Float.isInfinite(fD) || Float.isNaN(fD)) {
                fJ = a.j(j3);
            } else {
                e eVar = z.f409b;
                fJ = AbstractC2673a.u(fD, a.j(j3), a.h(j3));
            }
            if (!Float.isInfinite(fB) && !Float.isNaN(fB)) {
                e eVar2 = z.f409b;
                fU = AbstractC2673a.u(fB, a.i(j3), a.g(j3));
                long jH0 = H0(j.f(fJ, fU));
                return a.a(j3, io.sentry.config.a.S(j3, C7.a.W(f.d(jH0))), 0, io.sentry.config.a.R(j3, C7.a.W(f.b(jH0))), 0, 10);
            }
            i7 = a.i(j3);
        }
        fU = i7;
        long jH02 = H0(j.f(fJ, fU));
        return a.a(j3, io.sentry.config.a.S(j3, C7.a.W(f.d(jH02))), 0, io.sentry.config.a.R(j3, C7.a.W(f.b(jH02))), 0, 10);
    }

    public final void K0(AbstractC2268a abstractC2268a) {
        this.painter = abstractC2268a;
    }

    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        C2071b c2071b = h10.l;
        long jH0 = H0(c2071b.e());
        c cVar = this.f12649y;
        e eVar = z.f409b;
        long jC = H7.H.c(C7.a.W(f.d(jH0)), C7.a.W(f.b(jH0)));
        long jE = c2071b.e();
        long jA = cVar.a(jC, H7.H.c(C7.a.W(f.d(jE)), C7.a.W(f.b(jE))), h10.getLayoutDirection());
        float f10 = (int) (jA >> 32);
        float f11 = (int) (jA & 4294967295L);
        ((C1764u) c2071b.f20638m.f15499m).X(f10, f11);
        this.painter.g(h10, jH0, this.f12648A, this.B);
        ((C1764u) c2071b.f20638m.f15499m).X(-f10, -f11);
        h10.a();
    }

    @Override // E0.InterfaceC0174y
    public final int b(Q q4, J j3, int i7) {
        if (this.painter.h() == 9205357640488583168L) {
            return j3.c(i7);
        }
        int iC = j3.c(a.h(J0(io.sentry.config.a.f(i7, 0, 13))));
        return Math.max(C7.a.W(f.b(H0(j.f(i7, iC)))), iC);
    }

    @Override // E0.InterfaceC0174y
    public final int c(Q q4, J j3, int i7) {
        if (this.painter.h() == 9205357640488583168L) {
            return j3.P(i7);
        }
        int iP = j3.P(a.g(J0(io.sentry.config.a.f(0, i7, 7))));
        return Math.max(C7.a.W(f.d(H0(j.f(iP, i7)))), iP);
    }

    @Override // E0.InterfaceC0174y
    public final int d(Q q4, J j3, int i7) {
        if (this.painter.h() == 9205357640488583168L) {
            return j3.V(i7);
        }
        int iV = j3.V(a.g(J0(io.sentry.config.a.f(0, i7, 7))));
        return Math.max(C7.a.W(f.d(H0(j.f(iV, i7)))), iV);
    }

    @Override // E0.InterfaceC0174y
    public final int f(Q q4, J j3, int i7) {
        if (this.painter.h() == 9205357640488583168L) {
            return j3.a0(i7);
        }
        int iA0 = j3.a0(a.h(J0(io.sentry.config.a.f(i7, 0, 13))));
        return Math.max(C7.a.W(f.b(H0(j.f(i7, iA0)))), iA0);
    }

    @Override // E0.InterfaceC0174y
    public final L g(M m10, J j3, long j10) {
        V vB = j3.b(J0(j10));
        return m10.m0(vB.l, vB.f871m, v.l, new r(vB, 1));
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
