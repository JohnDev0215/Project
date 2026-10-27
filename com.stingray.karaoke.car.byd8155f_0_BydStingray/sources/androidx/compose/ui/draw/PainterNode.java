package androidx.compose.ui.draw;

import C0.InterfaceC0103j;
import C0.J;
import C0.L;
import C0.M;
import C0.V;
import C0.X;
import C0.c0;
import E0.H;
import E0.InterfaceC0165o;
import E0.InterfaceC0174y;
import E0.Q;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/draw/PainterNode;", "LE0/y;", "Lg0/n;", "LE0/o;", "Lr0/a;", "painter", "Lr0/a;", "H0", "()Lr0/a;", "M0", "(Lr0/a;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PainterNode extends n implements InterfaceC0174y, InterfaceC0165o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC0103j f11554A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C1883l f11555C;
    private AbstractC2268a painter;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f11556y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public c f11557z;

    public PainterNode(AbstractC2268a abstractC2268a, boolean z6, c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l) {
        this.painter = abstractC2268a;
        this.f11556y = z6;
        this.f11557z = cVar;
        this.f11554A = interfaceC0103j;
        this.B = f10;
        this.f11555C = c1883l;
    }

    public static boolean J0(long j3) {
        if (!f.a(j3, 9205357640488583168L)) {
            float fB = f.b(j3);
            if (!Float.isInfinite(fB) && !Float.isNaN(fB)) {
                return true;
            }
        }
        return false;
    }

    public static boolean K0(long j3) {
        if (!f.a(j3, 9205357640488583168L)) {
            float fD = f.d(j3);
            if (!Float.isInfinite(fD) && !Float.isNaN(fD)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: H0, reason: from getter */
    public final AbstractC2268a getPainter() {
        return this.painter;
    }

    public final boolean I0() {
        return this.f11556y && this.painter.h() != 9205357640488583168L;
    }

    public final long L0(long j3) {
        boolean z6 = false;
        boolean z10 = a1.a.d(j3) && a1.a.c(j3);
        if (a1.a.f(j3) && a1.a.e(j3)) {
            z6 = true;
        }
        if ((!I0() && z10) || z6) {
            return a1.a.a(j3, a1.a.h(j3), 0, a1.a.g(j3), 0, 10);
        }
        long jH = this.painter.h();
        long jF = j.f(io.sentry.config.a.S(j3, K0(jH) ? Math.round(f.d(jH)) : a1.a.j(j3)), io.sentry.config.a.R(j3, J0(jH) ? Math.round(f.b(jH)) : a1.a.i(j3)));
        if (I0()) {
            long jF2 = j.f(!K0(this.painter.h()) ? f.d(jF) : f.d(this.painter.h()), !J0(this.painter.h()) ? f.b(jF) : f.b(this.painter.h()));
            jF = (f.d(jF) == 0.0f || f.b(jF) == 0.0f) ? 0L : c0.j(jF2, this.f11554A.a(jF2, jF));
        }
        return a1.a.a(j3, io.sentry.config.a.S(j3, Math.round(f.d(jF))), 0, io.sentry.config.a.R(j3, Math.round(f.b(jF))), 0, 10);
    }

    public final void M0(AbstractC2268a abstractC2268a) {
        this.painter = abstractC2268a;
    }

    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        long jH = this.painter.h();
        boolean zK0 = K0(jH);
        C2071b c2071b = h10.l;
        long jF = j.f(zK0 ? f.d(jH) : f.d(c2071b.e()), J0(jH) ? f.b(jH) : f.b(c2071b.e()));
        long j3 = (f.d(c2071b.e()) == 0.0f || f.b(c2071b.e()) == 0.0f) ? 0L : c0.j(jF, this.f11554A.a(jF, c2071b.e()));
        long jA = this.f11557z.a(H7.H.c(Math.round(f.d(j3)), Math.round(f.b(j3))), H7.H.c(Math.round(f.d(c2071b.e())), Math.round(f.b(c2071b.e()))), h10.getLayoutDirection());
        float f10 = (int) (jA >> 32);
        float f11 = (int) (jA & 4294967295L);
        ((C1764u) c2071b.f20638m.f15499m).X(f10, f11);
        try {
            this.painter.g(h10, j3, this.B, this.f11555C);
            ((C1764u) c2071b.f20638m.f15499m).X(-f10, -f11);
            h10.a();
        } catch (Throwable th) {
            ((C1764u) c2071b.f20638m.f15499m).X(-f10, -f11);
            throw th;
        }
    }

    @Override // E0.InterfaceC0174y
    public final int b(Q q4, J j3, int i7) {
        if (!I0()) {
            return j3.c(i7);
        }
        long jL0 = L0(io.sentry.config.a.f(i7, 0, 13));
        return Math.max(a1.a.i(jL0), j3.c(i7));
    }

    @Override // E0.InterfaceC0174y
    public final int c(Q q4, J j3, int i7) {
        if (!I0()) {
            return j3.P(i7);
        }
        long jL0 = L0(io.sentry.config.a.f(0, i7, 7));
        return Math.max(a1.a.j(jL0), j3.P(i7));
    }

    @Override // E0.InterfaceC0174y
    public final int d(Q q4, J j3, int i7) {
        if (!I0()) {
            return j3.V(i7);
        }
        long jL0 = L0(io.sentry.config.a.f(0, i7, 7));
        return Math.max(a1.a.j(jL0), j3.V(i7));
    }

    @Override // E0.InterfaceC0174y
    public final int f(Q q4, J j3, int i7) {
        if (!I0()) {
            return j3.a0(i7);
        }
        long jL0 = L0(io.sentry.config.a.f(i7, 0, 13));
        return Math.max(a1.a.i(jL0), j3.a0(i7));
    }

    @Override // E0.InterfaceC0174y
    public final L g(M m10, J j3, long j10) {
        V vB = j3.b(L0(j10));
        return m10.m0(vB.l, vB.f871m, v.l, new X(vB, 11));
    }

    public final String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.f11556y + ", alignment=" + this.f11557z + ", alpha=" + this.B + ", colorFilter=" + this.f11555C + ')';
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
