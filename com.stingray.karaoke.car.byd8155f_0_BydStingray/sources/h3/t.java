package h3;

import B2.AbstractC0062b;
import B2.C0061a;
import B2.L;
import B2.N;
import e2.C1207o;
import e2.C1208p;
import e2.M;
import h2.AbstractC1346c;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class t implements InterfaceC1358i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15449b;
    public final h2.t c;
    public final N d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public L f15450e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f15451f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C1208p f15452g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15453h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15454i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f15455j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f15456k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15457m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15458n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f15459p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f15460q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f15461r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f15462s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f15463t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f15464u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f15465v;

    public t(String str, int i7) {
        this.f15448a = str;
        this.f15449b = i7;
        h2.t tVar = new h2.t(1024);
        this.c = tVar;
        byte[] bArr = tVar.f15241a;
        this.d = new N(bArr.length, bArr);
        this.l = -9223372036854775807L;
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        this.f15453h = 0;
        this.l = -9223372036854775807L;
        this.f15457m = false;
    }

    @Override // h3.InterfaceC1358i
    public final void c(h2.t tVar) throws M {
        int i7;
        int i10;
        boolean zH;
        AbstractC1346c.k(this.f15450e);
        while (tVar.a() > 0) {
            int i11 = this.f15453h;
            if (i11 != 0) {
                if (i11 != 1) {
                    h2.t tVar2 = this.c;
                    N n10 = this.d;
                    if (i11 == 2) {
                        int iV = ((this.f15456k & (-225)) << 8) | tVar.v();
                        this.f15455j = iV;
                        if (iV > tVar2.f15241a.length) {
                            tVar2.E(iV);
                            byte[] bArr = tVar2.f15241a;
                            n10.getClass();
                            n10.p(bArr.length, bArr);
                        }
                        this.f15454i = 0;
                        this.f15453h = 3;
                    } else {
                        if (i11 != 3) {
                            throw new IllegalStateException();
                        }
                        int iMin = Math.min(tVar.a(), this.f15455j - this.f15454i);
                        tVar.f(this.f15454i, iMin, n10.d);
                        int i12 = this.f15454i + iMin;
                        this.f15454i = i12;
                        if (i12 == this.f15455j) {
                            n10.r(0);
                            if (n10.h()) {
                                if (this.f15457m) {
                                }
                                this.f15453h = 0;
                            } else {
                                this.f15457m = true;
                                int i13 = n10.i(1);
                                int i14 = i13 == 1 ? n10.i(1) : 0;
                                this.f15458n = i14;
                                if (i14 != 0) {
                                    throw M.a(null, null);
                                }
                                if (i13 == 1) {
                                    n10.i((n10.i(2) + 1) * 8);
                                }
                                if (!n10.h()) {
                                    throw M.a(null, null);
                                }
                                this.o = n10.i(6);
                                int i15 = n10.i(4);
                                int i16 = n10.i(3);
                                if (i15 != 0 || i16 != 0) {
                                    throw M.a(null, null);
                                }
                                if (i13 == 0) {
                                    int iG = n10.g();
                                    int iB = n10.b();
                                    C0061a c0061aN = AbstractC0062b.n(n10, true);
                                    this.f15465v = c0061aN.f528a;
                                    this.f15462s = c0061aN.f529b;
                                    this.f15464u = c0061aN.c;
                                    int iB2 = iB - n10.b();
                                    n10.r(iG);
                                    byte[] bArr2 = new byte[(iB2 + 7) / 8];
                                    n10.j(iB2, bArr2);
                                    C1207o c1207o = new C1207o();
                                    c1207o.f14237a = this.f15451f;
                                    c1207o.f14246m = e2.L.l("audio/mp4a-latm");
                                    c1207o.f14244j = this.f15465v;
                                    c1207o.B = this.f15464u;
                                    c1207o.f14228C = this.f15462s;
                                    c1207o.f14248p = Collections.singletonList(bArr2);
                                    c1207o.d = this.f15448a;
                                    c1207o.f14240f = this.f15449b;
                                    C1208p c1208p = new C1208p(c1207o);
                                    if (!c1208p.equals(this.f15452g)) {
                                        this.f15452g = c1208p;
                                        this.f15463t = 1024000000 / ((long) c1208p.f14296D);
                                        this.f15450e.d(c1208p);
                                    }
                                } else {
                                    int iB3 = n10.b();
                                    C0061a c0061aN2 = AbstractC0062b.n(n10, true);
                                    this.f15465v = c0061aN2.f528a;
                                    this.f15462s = c0061aN2.f529b;
                                    this.f15464u = c0061aN2.c;
                                    n10.u(n10.i((n10.i(2) + 1) * 8) - (iB3 - n10.b()));
                                }
                                int i17 = n10.i(3);
                                this.f15459p = i17;
                                if (i17 == 0) {
                                    n10.u(8);
                                } else if (i17 == 1) {
                                    n10.u(9);
                                } else if (i17 == 3 || i17 == 4 || i17 == 5) {
                                    n10.u(6);
                                } else {
                                    if (i17 != 6 && i17 != 7) {
                                        throw new IllegalStateException();
                                    }
                                    n10.u(1);
                                }
                                boolean zH2 = n10.h();
                                this.f15460q = zH2;
                                this.f15461r = 0L;
                                if (zH2) {
                                    if (i13 == 1) {
                                        this.f15461r = n10.i((n10.i(2) + 1) * 8);
                                    } else {
                                        do {
                                            zH = n10.h();
                                            this.f15461r = (this.f15461r << 8) + ((long) n10.i(8));
                                        } while (zH);
                                    }
                                }
                                if (n10.h()) {
                                    n10.u(8);
                                }
                            }
                            if (this.f15458n != 0) {
                                throw M.a(null, null);
                            }
                            if (this.o != 0) {
                                throw M.a(null, null);
                            }
                            if (this.f15459p != 0) {
                                throw M.a(null, null);
                            }
                            int i18 = 0;
                            do {
                                i7 = n10.i(8);
                                i18 += i7;
                            } while (i7 == 255);
                            int iG2 = n10.g();
                            if ((iG2 & 7) == 0) {
                                tVar2.H(iG2 >> 3);
                                i10 = 0;
                            } else {
                                n10.j(i18 * 8, tVar2.f15241a);
                                i10 = 0;
                                tVar2.H(0);
                            }
                            this.f15450e.a(tVar2, i18, i10);
                            AbstractC1346c.j(this.l != -9223372036854775807L);
                            this.f15450e.b(this.l, 1, i18, 0, null);
                            this.l += this.f15463t;
                            if (this.f15460q) {
                                n10.u((int) this.f15461r);
                            }
                            this.f15453h = 0;
                        } else {
                            continue;
                        }
                    }
                } else {
                    int iV2 = tVar.v();
                    if ((iV2 & 224) == 224) {
                        this.f15456k = iV2;
                        this.f15453h = 2;
                    } else if (iV2 != 86) {
                        this.f15453h = 0;
                    }
                }
            } else if (tVar.v() == 86) {
                this.f15453h = 1;
            }
        }
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.l = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15450e = tVar.u(h10.d, 1);
        h10.b();
        this.f15451f = h10.f15293e;
    }
}
