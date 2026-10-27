package h3;

import B2.L;
import B2.N;

/* JADX INFO: loaded from: classes.dex */
public final class v implements InterfaceC1358i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f15478e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public L f15479f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15482i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f15484k;
    public int l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15486n;
    public int o;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f15490s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f15492u;
    public int d = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h2.t f15476a = new h2.t(2, new byte[15]);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f15477b = new N();
    public final h2.t c = new h2.t();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final w f15487p = new w();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f15488q = -2147483647;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f15489r = -1;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f15491t = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15483j = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15485m = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public double f15480g = -9.223372036854776E18d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double f15481h = -9.223372036854776E18d;

    @Override // h3.InterfaceC1358i
    public final void b() {
        this.d = 0;
        this.l = 0;
        this.f15476a.E(2);
        this.f15486n = 0;
        this.o = 0;
        this.f15488q = -2147483647;
        this.f15489r = -1;
        this.f15490s = 0;
        this.f15491t = -1L;
        this.f15492u = false;
        this.f15482i = false;
        this.f15485m = true;
        this.f15483j = true;
        this.f15480g = -9.223372036854776E18d;
        this.f15481h = -9.223372036854776E18d;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0400  */
    @Override // h3.InterfaceC1358i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(h2.t r23) throws e2.M {
        /*
            Method dump skipped, instruction units count: 1340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.v.c(h2.t):void");
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.f15484k = i7;
        if (!this.f15483j && (this.o != 0 || !this.f15485m)) {
            this.f15482i = true;
        }
        if (j3 != -9223372036854775807L) {
            if (this.f15482i) {
                this.f15481h = j3;
            } else {
                this.f15480g = j3;
            }
        }
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15478e = h10.f15293e;
        h10.b();
        this.f15479f = tVar.u(h10.d, 1);
    }
}
