package N;

import A7.m;
import C0.AbstractC0096c;
import C0.C0107n;
import C0.J;
import C0.M;
import C0.V;
import E0.AbstractC0156f;
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
import N0.C0406a;
import N0.C0411f;
import N0.D;
import N0.L;
import O0.x;
import S0.InterfaceC0702n;
import g0.n;
import h8.AbstractC1387d;
import java.util.LinkedHashMap;
import java.util.Map;
import l9.l;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.C1891t;
import n0.InterfaceC1889r;
import n0.InterfaceC1892u;

/* JADX INFO: loaded from: classes.dex */
public final class k extends n implements InterfaceC0174y, InterfaceC0165o, p0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC0702n f4275A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f4276C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f4277D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f4278E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public InterfaceC1892u f4279F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Map f4280G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public e f4281H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public j f4282I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public i f4283J;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f4284y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public L f4285z;

    @Override // E0.p0
    public final void H(L0.j jVar) {
        j jVar2 = this.f4282I;
        if (jVar2 == null) {
            jVar2 = new j(this, 0);
            this.f4282I = jVar2;
        }
        C0411f c0411f = new C0411f(this.f4284y, null, 6);
        w[] wVarArr = t.f3964a;
        jVar.l(r.f3957u, AbstractC1387d.j(c0411f));
        i iVar = this.f4283J;
        if (iVar != null) {
            boolean z6 = iVar.c;
            u uVar = r.f3959w;
            w[] wVarArr2 = t.f3964a;
            w wVar = wVarArr2[15];
            uVar.a(jVar, Boolean.valueOf(z6));
            C0411f c0411f2 = new C0411f(iVar.f4272b, null, 6);
            u uVar2 = r.f3958v;
            w wVar2 = wVarArr2[14];
            uVar2.a(jVar, c0411f2);
        }
        jVar.l(L0.i.f3894j, new L0.a(null, new j(this, 1)));
        jVar.l(L0.i.f3895k, new L0.a(null, new j(this, 2)));
        jVar.l(L0.i.l, new L0.a(null, new K(this, 7)));
        t.c(jVar, jVar2);
    }

    public final e H0() {
        if (this.f4281H == null) {
            this.f4281H = new e(this.f4284y, this.f4285z, this.f4275A, this.B, this.f4276C, this.f4277D, this.f4278E);
        }
        e eVar = this.f4281H;
        m.c(eVar);
        return eVar;
    }

    public final e I0(a1.b bVar) {
        e eVar;
        i iVar = this.f4283J;
        if (iVar != null && iVar.c && (eVar = iVar.d) != null) {
            eVar.c(bVar);
            return eVar;
        }
        e eVarH0 = H0();
        eVarH0.c(bVar);
        return eVarH0;
    }

    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        if (this.f14695x) {
            e eVarI0 = I0(h10);
            C0406a c0406a = eVarI0.f4245j;
            if (c0406a == null) {
                throw new IllegalArgumentException(("no paragraph (layoutCache=" + this.f4281H + ", textSubstitution=" + this.f4283J + ')').toString());
            }
            InterfaceC1889r interfaceC1889rM = h10.l.f20638m.m();
            boolean z6 = eVarI0.f4246k;
            if (z6) {
                long j3 = eVarI0.l;
                interfaceC1889rM.m();
                interfaceC1889rM.i(0.0f, 0.0f, (int) (j3 >> 32), (int) (j3 & 4294967295L), 1);
            }
            try {
                D d = this.f4285z.f4358a;
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
                    c0406a.g(interfaceC1889rM, abstractC1887pB, this.f4285z.f4358a.f4324a.c(), c1865o2, jVar2, eVar2, 3);
                } else {
                    InterfaceC1892u interfaceC1892u = this.f4279F;
                    long jA = interfaceC1892u != null ? interfaceC1892u.a() : C1891t.f19452k;
                    if (jA == 16) {
                        jA = this.f4285z.c() != 16 ? this.f4285z.c() : C1891t.f19445b;
                    }
                    c0406a.f(interfaceC1889rM, jA, c1865o2, jVar2, eVar2, 3);
                }
                if (z6) {
                    interfaceC1889rM.k();
                }
            } catch (Throwable th) {
                if (z6) {
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

    @Override // E0.InterfaceC0174y
    public final C0.L g(M m10, J j3, long j10) {
        long jA;
        boolean z6;
        N0.t tVar;
        e eVarI0 = I0(m10);
        a1.k layoutDirection = m10.getLayoutDirection();
        if (eVarI0.f4242g > 1) {
            b bVar = eVarI0.f4247m;
            L l = eVarI0.f4239b;
            a1.b bVar2 = eVarI0.f4244i;
            m.c(bVar2);
            b bVarL = l.L(bVar, layoutDirection, l, bVar2, eVarI0.c);
            eVarI0.f4247m = bVarL;
            jA = bVarL.a(j10, eVarI0.f4242g);
        } else {
            jA = j10;
        }
        C0406a c0406a = eVarI0.f4245j;
        if (c0406a == null || (tVar = eVarI0.f4248n) == null || tVar.c() || layoutDirection != eVarI0.o || (!a1.a.b(jA, eVarI0.f4249p) && (a1.a.h(jA) != a1.a.h(eVarI0.f4249p) || a1.a.g(jA) < c0406a.b() || c0406a.d.d))) {
            C0406a c0406aB = eVarI0.b(jA, layoutDirection);
            eVarI0.f4249p = jA;
            long jP = io.sentry.config.a.P(jA, H7.H.c(X.n(c0406aB.d()), X.n(c0406aB.b())));
            eVarI0.l = jP;
            eVarI0.f4246k = !C7.a.F(eVarI0.d, 3) && (((float) ((int) (jP >> 32))) < c0406aB.d() || ((float) ((int) (jP & 4294967295L))) < c0406aB.b());
            eVarI0.f4245j = c0406aB;
            z6 = true;
        } else {
            if (!a1.a.b(jA, eVarI0.f4249p)) {
                C0406a c0406a2 = eVarI0.f4245j;
                m.c(c0406a2);
                long jP2 = io.sentry.config.a.P(jA, H7.H.c(X.n(Math.min(c0406a2.f4362a.f9296t.b(), c0406a2.d())), X.n(c0406a2.b())));
                eVarI0.l = jP2;
                eVarI0.f4246k = !C7.a.F(eVarI0.d, 3) && (((float) ((int) (jP2 >> 32))) < c0406a2.d() || ((float) ((int) (jP2 & 4294967295L))) < c0406a2.b());
                eVarI0.f4249p = jA;
            }
            z6 = false;
        }
        N0.t tVar2 = eVarI0.f4248n;
        if (tVar2 != null) {
            tVar2.c();
        }
        C0406a c0406a3 = eVarI0.f4245j;
        m.c(c0406a3);
        long j11 = eVarI0.l;
        if (z6) {
            AbstractC0156f.r(this, 2).W0();
            Map linkedHashMap = this.f4280G;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap(2);
            }
            C0107n c0107n = AbstractC0096c.f889a;
            x xVar = c0406a3.d;
            linkedHashMap.put(c0107n, Integer.valueOf(Math.round(xVar.d(0))));
            linkedHashMap.put(AbstractC0096c.f890b, Integer.valueOf(Math.round(xVar.d(xVar.f5051g - 1))));
            this.f4280G = linkedHashMap;
        }
        int i7 = (int) (j11 >> 32);
        int i10 = (int) (j11 & 4294967295L);
        int iMin = Math.min(i7, 262142);
        int iMin2 = i7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i7, 262142);
        int iJ = io.sentry.config.a.J(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        V vB = j3.b(io.sentry.config.a.e(iMin, iMin2, Math.min(iJ, i10), i10 != Integer.MAX_VALUE ? Math.min(iJ, i10) : Integer.MAX_VALUE));
        Map map = this.f4280G;
        m.c(map);
        return m10.m0(i7, i10, map, new C0.X(vB, 8));
    }
}
