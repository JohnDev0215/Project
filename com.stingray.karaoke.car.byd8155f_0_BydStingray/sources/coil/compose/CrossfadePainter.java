package coil.compose;

import C0.InterfaceC0103j;
import C0.c0;
import E0.H;
import T.C0730a0;
import T.C0732b0;
import T.C0735d;
import T.C0738e0;
import T.S;
import android.os.SystemClock;
import h6.j;
import kotlin.Metadata;
import l3.C1764u;
import m0.f;
import n0.C1883l;
import p0.C2071b;
import r0.AbstractC2268a;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcoil/compose/CrossfadePainter;", "Lr0/a;", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CrossfadePainter extends AbstractC2268a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public AbstractC2268a f12652q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AbstractC2268a f12653r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final InterfaceC0103j f12654s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f12655t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f12656u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f12657v;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f12660y;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0732b0 f12658w = C0735d.M(0);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f12659x = -1;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C0730a0 f12661z = C0735d.L(1.0f);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C0738e0 f12651A = C0735d.O(null, S.f8224q);

    public CrossfadePainter(AbstractC2268a abstractC2268a, AbstractC2268a abstractC2268a2, InterfaceC0103j interfaceC0103j, int i7, boolean z6, boolean z10) {
        this.f12652q = abstractC2268a;
        this.f12653r = abstractC2268a2;
        this.f12654s = interfaceC0103j;
        this.f12655t = i7;
        this.f12656u = z6;
        this.f12657v = z10;
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f12661z.j(f10);
        return true;
    }

    @Override // r0.AbstractC2268a
    public final boolean e(C1883l c1883l) {
        this.f12651A.setValue(c1883l);
        return true;
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        AbstractC2268a abstractC2268a = this.f12652q;
        long jH = abstractC2268a != null ? abstractC2268a.h() : 0L;
        AbstractC2268a abstractC2268a2 = this.f12653r;
        long jH2 = abstractC2268a2 != null ? abstractC2268a2.h() : 0L;
        boolean z6 = jH != 9205357640488583168L;
        boolean z10 = jH2 != 9205357640488583168L;
        if (z6 && z10) {
            return j.f(Math.max(f.d(jH), f.d(jH2)), Math.max(f.b(jH), f.b(jH2)));
        }
        if (this.f12657v) {
            if (z6) {
                return jH;
            }
            if (z10) {
                return jH2;
            }
        }
        return 9205357640488583168L;
    }

    @Override // r0.AbstractC2268a
    public final void i(H h10) {
        boolean z6 = this.f12660y;
        C0730a0 c0730a0 = this.f12661z;
        AbstractC2268a abstractC2268a = this.f12653r;
        if (z6) {
            j(h10, abstractC2268a, c0730a0.i());
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.f12659x == -1) {
            this.f12659x = jUptimeMillis;
        }
        float f10 = (jUptimeMillis - this.f12659x) / this.f12655t;
        float fI = c0730a0.i() * AbstractC2673a.u(f10, 0.0f, 1.0f);
        float fI2 = this.f12656u ? c0730a0.i() - fI : c0730a0.i();
        this.f12660y = f10 >= 1.0f;
        j(h10, this.f12652q, fI2);
        j(h10, abstractC2268a, fI);
        if (this.f12660y) {
            this.f12652q = null;
        } else {
            C0732b0 c0732b0 = this.f12658w;
            c0732b0.j(c0732b0.i() + 1);
        }
    }

    public final void j(H h10, AbstractC2268a abstractC2268a, float f10) {
        if (abstractC2268a == null || f10 <= 0.0f) {
            return;
        }
        C2071b c2071b = h10.l;
        long jE = c2071b.e();
        long jH = abstractC2268a.h();
        long j3 = (jH == 9205357640488583168L || f.e(jH) || jE == 9205357640488583168L || f.e(jE)) ? jE : c0.j(jH, this.f12654s.a(jH, jE));
        C0738e0 c0738e0 = this.f12651A;
        if (jE == 9205357640488583168L || f.e(jE)) {
            abstractC2268a.g(h10, j3, f10, (C1883l) c0738e0.getValue());
            return;
        }
        float f11 = 2;
        float fD = (f.d(jE) - f.d(j3)) / f11;
        float fB = (f.b(jE) - f.b(j3)) / f11;
        ((C1764u) c2071b.f20638m.f15499m).N(fD, fB, fD, fB);
        abstractC2268a.g(h10, j3, f10, (C1883l) c0738e0.getValue());
        float f12 = -fD;
        float f13 = -fB;
        ((C1764u) c2071b.f20638m.f15499m).N(f12, f13, f12, f13);
    }
}
