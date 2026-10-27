package N;

import A7.m;
import K.X;
import N0.C0411f;
import N0.G;
import N0.H;
import N0.L;
import N0.o;
import N0.q;
import P8.n;
import S0.InterfaceC0702n;
import java.util.List;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0411f f4226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L f4227b;
    public InterfaceC0702n c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4228e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4229f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4230g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List f4231h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f4232i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public a1.b f4234k;
    public q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public a1.k f4235m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public H f4236n;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f4233j = a.f4216a;
    public int o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4237p = -1;

    public d(C0411f c0411f, L l, InterfaceC0702n interfaceC0702n, int i7, boolean z6, int i10, int i11, List list) {
        this.f4226a = c0411f;
        this.f4227b = l;
        this.c = interfaceC0702n;
        this.d = i7;
        this.f4228e = z6;
        this.f4229f = i10;
        this.f4230g = i11;
        this.f4231h = list;
    }

    public final int a(int i7, a1.k kVar) {
        int i10 = this.o;
        int i11 = this.f4237p;
        if (i7 == i10 && i10 != -1) {
            return i11;
        }
        int iN = X.n(b(io.sentry.config.a.e(0, i7, 0, Integer.MAX_VALUE), kVar).f4393e);
        this.o = i7;
        this.f4237p = iN;
        return iN;
    }

    public final o b(long j3, a1.k kVar) {
        q qVarD = d(kVar);
        long jQ = l9.d.Q(j3, this.f4228e, this.d, qVarD.e());
        boolean z6 = this.f4228e;
        int i7 = this.d;
        int i10 = this.f4229f;
        int i11 = 1;
        if (z6 || !C7.a.F(i7, 2)) {
            if (i10 < 1) {
                i10 = 1;
            }
            i11 = i10;
        }
        return new o(qVarD, jQ, i11, C7.a.F(this.d, 2));
    }

    public final void c(a1.b bVar) {
        long jA;
        a1.b bVar2 = this.f4234k;
        if (bVar != null) {
            int i7 = a.f4217b;
            jA = a.a(bVar.getDensity(), bVar.p());
        } else {
            jA = a.f4216a;
        }
        if (bVar2 == null) {
            this.f4234k = bVar;
            this.f4233j = jA;
        } else if (bVar == null || this.f4233j != jA) {
            this.f4234k = bVar;
            this.f4233j = jA;
            this.l = null;
            this.f4236n = null;
            this.f4237p = -1;
            this.o = -1;
        }
    }

    public final q d(a1.k kVar) {
        q qVar = this.l;
        if (qVar == null || kVar != this.f4235m || qVar.c()) {
            this.f4235m = kVar;
            C0411f c0411f = this.f4226a;
            L lT = n.T(this.f4227b, kVar);
            a1.b bVar = this.f4234k;
            m.c(bVar);
            InterfaceC0702n interfaceC0702n = this.c;
            List list = this.f4231h;
            if (list == null) {
                list = u.l;
            }
            qVar = new q(c0411f, lT, list, bVar, interfaceC0702n);
        }
        this.l = qVar;
        return qVar;
    }

    public final H e(a1.k kVar, long j3, o oVar) {
        float fMin = Math.min(oVar.f4391a.e(), oVar.d);
        C0411f c0411f = this.f4226a;
        L l = this.f4227b;
        List list = this.f4231h;
        if (list == null) {
            list = u.l;
        }
        int i7 = this.f4229f;
        boolean z6 = this.f4228e;
        int i10 = this.d;
        a1.b bVar = this.f4234k;
        m.c(bVar);
        return new H(new G(c0411f, l, list, i7, z6, i10, bVar, kVar, this.c, j3), oVar, io.sentry.config.a.P(j3, H7.H.c(X.n(fMin), X.n(oVar.f4393e))));
    }
}
