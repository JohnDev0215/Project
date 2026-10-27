package V5;

import S8.AbstractC0727y;
import T.C0738e0;
import q7.InterfaceC2189c;
import x0.InterfaceC2660a;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class l implements InterfaceC2660a {
    public final o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final X8.c f9523m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f9524n;
    public final G6.i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f9525p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f9526q;

    public l(o oVar, X8.c cVar, boolean z6, G6.i iVar) {
        this.l = oVar;
        this.f9523m = cVar;
        this.f9524n = z6;
        this.o = iVar;
    }

    @Override // x0.InterfaceC2660a
    public final Object B(long j3, InterfaceC2189c interfaceC2189c) {
        o oVar = this.l;
        if (!oVar.b() && oVar.a() >= this.f9526q) {
            this.o.a();
        }
        oVar.d.setValue(Boolean.FALSE);
        return new a1.o(0L);
    }

    @Override // x0.InterfaceC2660a
    public final long N(int i7, long j3, long j10) {
        if (!this.f9525p || this.l.b()) {
            return 0L;
        }
        boolean z6 = this.f9524n;
        if (z6 && i7 == 1 && m0.c.e(j10) > 0.0f) {
            return a(j10);
        }
        if (z6 || i7 != 1 || m0.c.e(j10) >= 0.0f) {
            return 0L;
        }
        return a(j10);
    }

    public final long a(long j3) {
        o oVar = this.l;
        C0738e0 c0738e0 = oVar.d;
        boolean z6 = this.f9524n;
        if ((z6 && m0.c.e(j3) > 0.0f) || (!z6 && m0.c.e(j3) < 0.0f)) {
            c0738e0.setValue(Boolean.TRUE);
        } else if (C7.a.W(oVar.a()) == 0) {
            c0738e0.setValue(Boolean.FALSE);
        }
        float fR = z6 ? AbstractC2673a.r(oVar.a() + (m0.c.e(j3) * 0.5f), 0.0f) - oVar.a() : -(oVar.a() + AbstractC2673a.s((m0.c.e(j3) * 0.5f) - oVar.a(), 0.0f));
        if (Math.abs(fR) < 0.5f) {
            return 0L;
        }
        AbstractC0727y.w(this.f9523m, null, null, new k(this, fR, null), 3);
        return z6 ? h6.e.a(0.0f, fR / 0.5f) : h6.e.a(0.0f, (-fR) / 0.5f);
    }

    @Override // x0.InterfaceC2660a
    public final long n(long j3, int i7) {
        if (!this.f9525p || this.l.b()) {
            return 0L;
        }
        boolean z6 = this.f9524n;
        if (z6 && i7 == 1 && m0.c.e(j3) < 0.0f) {
            return a(j3);
        }
        if (z6 || i7 != 1 || m0.c.e(j3) <= 0.0f) {
            return 0L;
        }
        return a(j3);
    }
}
