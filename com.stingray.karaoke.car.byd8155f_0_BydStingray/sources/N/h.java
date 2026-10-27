package N;

import A7.m;
import C0.J;
import E0.H;
import E0.InterfaceC0165o;
import E0.InterfaceC0174y;
import E0.Q;
import E0.p0;
import H7.w;
import K.X;
import K7.K;
import L0.r;
import L0.t;
import L0.u;
import N0.C0411f;
import N0.D;
import N0.L;
import N0.o;
import S0.InterfaceC0702n;
import g0.n;
import h8.AbstractC1387d;
import java.util.List;
import java.util.Map;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.C1891t;
import n0.InterfaceC1889r;
import n0.InterfaceC1892u;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h extends n implements InterfaceC0174y, InterfaceC0165o, p0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC0702n f4256A;
    public InterfaceC2782k B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f4257C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f4258D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f4259E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f4260F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public List f4261G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public InterfaceC2782k f4262H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public InterfaceC1892u f4263I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public InterfaceC2782k f4264J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public Map f4265K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public d f4266L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public g f4267M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public f f4268N;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C0411f f4269y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public L f4270z;

    @Override // E0.p0
    public final void H(L0.j jVar) {
        g gVar = this.f4267M;
        if (gVar == null) {
            gVar = new g(this, 0);
            this.f4267M = gVar;
        }
        C0411f c0411f = this.f4269y;
        w[] wVarArr = t.f3964a;
        jVar.l(r.f3957u, AbstractC1387d.j(c0411f));
        f fVar = this.f4268N;
        if (fVar != null) {
            C0411f c0411f2 = fVar.f4253b;
            u uVar = r.f3958v;
            w[] wVarArr2 = t.f3964a;
            w wVar = wVarArr2[14];
            uVar.a(jVar, c0411f2);
            boolean z6 = fVar.c;
            u uVar2 = r.f3959w;
            w wVar2 = wVarArr2[15];
            uVar2.a(jVar, Boolean.valueOf(z6));
        }
        jVar.l(L0.i.f3894j, new L0.a(null, new g(this, 1)));
        jVar.l(L0.i.f3895k, new L0.a(null, new g(this, 2)));
        jVar.l(L0.i.l, new L0.a(null, new K(this, 6)));
        t.c(jVar, gVar);
    }

    public final d H0() {
        if (this.f4266L == null) {
            this.f4266L = new d(this.f4269y, this.f4270z, this.f4256A, this.f4257C, this.f4258D, this.f4259E, this.f4260F, this.f4261G);
        }
        d dVar = this.f4266L;
        m.c(dVar);
        return dVar;
    }

    public final d I0(a1.b bVar) {
        d dVar;
        f fVar = this.f4268N;
        if (fVar != null && fVar.c && (dVar = fVar.d) != null) {
            dVar.c(bVar);
            return dVar;
        }
        d dVarH0 = H0();
        dVarH0.c(bVar);
        return dVarH0;
    }

    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        if (this.f14695x) {
            InterfaceC1889r interfaceC1889rM = h10.l.f20638m.m();
            N0.H h11 = I0(h10).f4236n;
            if (h11 == null) {
                throw new IllegalStateException("You must call layoutWithConstraints first");
            }
            long j3 = h11.c;
            float f10 = (int) (j3 >> 32);
            o oVar = h11.f4349b;
            boolean z6 = true;
            boolean z10 = ((f10 > oVar.d ? 1 : (f10 == oVar.d ? 0 : -1)) < 0 || oVar.c || (((float) ((int) (j3 & 4294967295L))) > oVar.f4393e ? 1 : (((float) ((int) (j3 & 4294967295L))) == oVar.f4393e ? 0 : -1)) < 0) && !C7.a.F(this.f4257C, 3);
            if (z10) {
                m0.d dVarA = h6.f.a(0L, h6.j.f((int) (j3 >> 32), (int) (j3 & 4294967295L)));
                interfaceC1889rM.m();
                InterfaceC1889r.q(interfaceC1889rM, dVarA);
            }
            try {
                D d = this.f4270z.f4358a;
                Y0.j jVar = d.f4333m;
                if (jVar == null) {
                    jVar = Y0.j.f10619b;
                }
                Y0.j jVar2 = jVar;
                C1865O c1865o = d.f4334n;
                if (c1865o == null) {
                    c1865o = C1865O.d;
                }
                C1865O c1865o2 = c1865o;
                p0.e eVar = d.f4335p;
                if (eVar == null) {
                    eVar = p0.g.f20641a;
                }
                p0.e eVar2 = eVar;
                AbstractC1887p abstractC1887pB = d.f4324a.b();
                if (abstractC1887pB != null) {
                    o.h(oVar, interfaceC1889rM, abstractC1887pB, this.f4270z.f4358a.f4324a.c(), c1865o2, jVar2, eVar2);
                } else {
                    InterfaceC1892u interfaceC1892u = this.f4263I;
                    long jA = interfaceC1892u != null ? interfaceC1892u.a() : C1891t.f19452k;
                    if (jA == 16) {
                        jA = this.f4270z.c() != 16 ? this.f4270z.c() : C1891t.f19445b;
                    }
                    o.g(oVar, interfaceC1889rM, jA, c1865o2, jVar2, eVar2);
                }
                if (z10) {
                    interfaceC1889rM.k();
                }
                f fVar = this.f4268N;
                if (!((fVar == null || !fVar.c) ? w0.c.U(this.f4269y) : false)) {
                    List list = this.f4261G;
                    if (list != null && !list.isEmpty()) {
                        z6 = false;
                    }
                    if (z6) {
                        return;
                    }
                }
                h10.a();
            } catch (Throwable th) {
                if (z10) {
                    interfaceC1889rM.k();
                }
                throw th;
            }
        }
    }

    @Override // E0.InterfaceC0174y
    public final int b(Q q4, J j3, int i7) {
        return I0(q4).a(i7, q4.getLayoutDirection());
    }

    @Override // E0.InterfaceC0174y
    public final int c(Q q4, J j3, int i7) {
        return X.n(I0(q4).d(q4.getLayoutDirection()).b());
    }

    @Override // E0.InterfaceC0174y
    public final int d(Q q4, J j3, int i7) {
        return X.n(I0(q4).d(q4.getLayoutDirection()).e());
    }

    @Override // E0.InterfaceC0174y
    public final int f(Q q4, J j3, int i7) {
        return I0(q4).a(i7, q4.getLayoutDirection());
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007e  */
    @Override // E0.InterfaceC0174y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final C0.L g(C0.M r8, C0.J r9, long r10) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N.h.g(C0.M, C0.J, long):C0.L");
    }
}
