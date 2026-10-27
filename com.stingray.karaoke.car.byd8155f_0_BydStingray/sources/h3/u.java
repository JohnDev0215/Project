package h3;

import B2.L;
import e2.C1207o;
import e2.C1208p;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class u implements InterfaceC1358i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h2.t f15466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B2.D f15467b;
    public final String c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public L f15468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f15469f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f15470g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15471h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15472i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15473j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f15474k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f15475m;

    public u(String str, int i7) {
        h2.t tVar = new h2.t(4);
        this.f15466a = tVar;
        tVar.f15241a[0] = -1;
        this.f15467b = new B2.D();
        this.f15475m = -9223372036854775807L;
        this.c = str;
        this.d = i7;
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        this.f15470g = 0;
        this.f15471h = 0;
        this.f15473j = false;
        this.f15475m = -9223372036854775807L;
    }

    @Override // h3.InterfaceC1358i
    public final void c(h2.t tVar) {
        AbstractC1346c.k(this.f15468e);
        while (tVar.a() > 0) {
            int i7 = this.f15470g;
            h2.t tVar2 = this.f15466a;
            if (i7 == 0) {
                byte[] bArr = tVar.f15241a;
                int i10 = tVar.f15242b;
                int i11 = tVar.c;
                while (true) {
                    if (i10 >= i11) {
                        tVar.H(i11);
                        break;
                    }
                    byte b10 = bArr[i10];
                    boolean z6 = (b10 & 255) == 255;
                    boolean z10 = this.f15473j && (b10 & 224) == 224;
                    this.f15473j = z6;
                    if (z10) {
                        tVar.H(i10 + 1);
                        this.f15473j = false;
                        tVar2.f15241a[1] = bArr[i10];
                        this.f15471h = 2;
                        this.f15470g = 1;
                        break;
                    }
                    i10++;
                }
            } else if (i7 == 1) {
                int iMin = Math.min(tVar.a(), 4 - this.f15471h);
                tVar.f(this.f15471h, iMin, tVar2.f15241a);
                int i12 = this.f15471h + iMin;
                this.f15471h = i12;
                if (i12 >= 4) {
                    tVar2.H(0);
                    int iH = tVar2.h();
                    B2.D d = this.f15467b;
                    if (d.a(iH)) {
                        this.l = d.f504b;
                        if (!this.f15472i) {
                            this.f15474k = (((long) d.f506f) * 1000000) / ((long) d.c);
                            C1207o c1207o = new C1207o();
                            c1207o.f14237a = this.f15469f;
                            c1207o.f14246m = e2.L.l((String) d.f507g);
                            c1207o.f14247n = 4096;
                            c1207o.B = d.d;
                            c1207o.f14228C = d.c;
                            c1207o.d = this.c;
                            c1207o.f14240f = this.d;
                            this.f15468e.d(new C1208p(c1207o));
                            this.f15472i = true;
                        }
                        tVar2.H(0);
                        this.f15468e.a(tVar2, 4, 0);
                        this.f15470g = 2;
                    } else {
                        this.f15471h = 0;
                        this.f15470g = 1;
                    }
                }
            } else {
                if (i7 != 2) {
                    throw new IllegalStateException();
                }
                int iMin2 = Math.min(tVar.a(), this.l - this.f15471h);
                this.f15468e.a(tVar, iMin2, 0);
                int i13 = this.f15471h + iMin2;
                this.f15471h = i13;
                if (i13 >= this.l) {
                    AbstractC1346c.j(this.f15475m != -9223372036854775807L);
                    this.f15468e.b(this.f15475m, 1, this.l, 0, null);
                    this.f15475m += this.f15474k;
                    this.f15471h = 0;
                    this.f15470g = 0;
                }
            }
        }
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.f15475m = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15469f = h10.f15293e;
        h10.b();
        this.f15468e = tVar.u(h10.d, 1);
    }
}
