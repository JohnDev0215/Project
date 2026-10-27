package N;

import A7.m;
import H7.H;
import K.X;
import N0.C0406a;
import N0.L;
import N0.t;
import P8.n;
import S0.InterfaceC0702n;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L f4239b;
    public InterfaceC0702n c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4240e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4241f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4242g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a1.b f4244i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C0406a f4245j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4246k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f4247m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public t f4248n;
    public a1.k o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f4243h = a.f4216a;
    public long l = H.c(0, 0);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f4249p = io.sentry.config.a.U(0, 0, 0, 0);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4250q = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f4251r = -1;

    public e(String str, L l, InterfaceC0702n interfaceC0702n, int i7, boolean z6, int i10, int i11) {
        this.f4238a = str;
        this.f4239b = l;
        this.c = interfaceC0702n;
        this.d = i7;
        this.f4240e = z6;
        this.f4241f = i10;
        this.f4242g = i11;
    }

    public final int a(int i7, a1.k kVar) {
        int i10 = this.f4250q;
        int i11 = this.f4251r;
        if (i7 == i10 && i10 != -1) {
            return i11;
        }
        int iN = X.n(b(io.sentry.config.a.e(0, i7, 0, Integer.MAX_VALUE), kVar).b());
        this.f4250q = i7;
        this.f4251r = iN;
        return iN;
    }

    public final C0406a b(long j3, a1.k kVar) {
        int i7;
        t tVarD = d(kVar);
        long jQ = l9.d.Q(j3, this.f4240e, this.d, tVarD.e());
        boolean z6 = this.f4240e;
        int i10 = this.d;
        int i11 = this.f4241f;
        if (z6 || !C7.a.F(i10, 2)) {
            if (i11 < 1) {
                i11 = 1;
            }
            i7 = i11;
        } else {
            i7 = 1;
        }
        return new C0406a((V0.c) tVarD, i7, C7.a.F(this.d, 2), jQ);
    }

    public final void c(a1.b bVar) {
        long jA;
        a1.b bVar2 = this.f4244i;
        if (bVar != null) {
            int i7 = a.f4217b;
            jA = a.a(bVar.getDensity(), bVar.p());
        } else {
            jA = a.f4216a;
        }
        if (bVar2 == null) {
            this.f4244i = bVar;
            this.f4243h = jA;
            return;
        }
        if (bVar == null || this.f4243h != jA) {
            this.f4244i = bVar;
            this.f4243h = jA;
            this.f4245j = null;
            this.f4248n = null;
            this.o = null;
            this.f4250q = -1;
            this.f4251r = -1;
            this.f4249p = io.sentry.config.a.U(0, 0, 0, 0);
            this.l = H.c(0, 0);
            this.f4246k = false;
        }
    }

    public final t d(a1.k kVar) {
        t cVar = this.f4248n;
        if (cVar == null || kVar != this.o || cVar.c()) {
            this.o = kVar;
            String str = this.f4238a;
            L lT = n.T(this.f4239b, kVar);
            a1.b bVar = this.f4244i;
            m.c(bVar);
            InterfaceC0702n interfaceC0702n = this.c;
            u uVar = u.l;
            cVar = new V0.c(str, lT, uVar, uVar, interfaceC0702n, bVar);
        }
        this.f4248n = cVar;
        return cVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphLayoutCache(paragraph=");
        sb.append(this.f4245j != null ? "<paragraph>" : "null");
        sb.append(", lastDensity=");
        long j3 = this.f4243h;
        int i7 = a.f4217b;
        sb.append((Object) ("InlineDensity(density=" + Float.intBitsToFloat((int) (j3 >> 32)) + ", fontScale=" + Float.intBitsToFloat((int) (j3 & 4294967295L)) + ')'));
        sb.append(')');
        return sb.toString();
    }
}
