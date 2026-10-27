package h3;

import B2.L;
import B2.N;
import e2.C1208p;

/* JADX INFO: renamed from: h3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1352c implements InterfaceC1358i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15296a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f15297b;
    public final h2.t c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f15298e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f15299f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public L f15300g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15301h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15302i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15303j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f15304k;
    public C1208p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15305m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f15306n;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C1352c() {
        this(null, 0, 0);
        this.f15296a = 0;
    }

    private final void a(boolean z6) {
    }

    private final void g(boolean z6) {
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        switch (this.f15296a) {
            case 0:
                this.f15301h = 0;
                this.f15302i = 0;
                this.f15303j = false;
                this.f15306n = -9223372036854775807L;
                break;
            default:
                this.f15301h = 0;
                this.f15302i = 0;
                this.f15303j = false;
                this.f15306n = -9223372036854775807L;
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03e6  */
    @Override // h3.InterfaceC1358i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(h2.t r23) {
        /*
            Method dump skipped, instruction units count: 1256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.C1352c.c(h2.t):void");
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
        int i7 = this.f15296a;
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        switch (this.f15296a) {
            case 0:
                this.f15306n = j3;
                break;
            default:
                this.f15306n = j3;
                break;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        switch (this.f15296a) {
            case 0:
                h10.a();
                h10.b();
                this.f15299f = h10.f15293e;
                h10.b();
                this.f15300g = tVar.u(h10.d, 1);
                break;
            default:
                h10.a();
                h10.b();
                this.f15299f = h10.f15293e;
                h10.b();
                this.f15300g = tVar.u(h10.d, 1);
                break;
        }
    }

    public C1352c(String str, int i7, int i10) {
        this.f15296a = i10;
        switch (i10) {
            case 1:
                N n10 = new N(16, new byte[16]);
                this.f15297b = n10;
                this.c = new h2.t(n10.d);
                this.f15301h = 0;
                this.f15302i = 0;
                this.f15303j = false;
                this.f15306n = -9223372036854775807L;
                this.d = str;
                this.f15298e = i7;
                break;
            default:
                N n11 = new N(128, new byte[128]);
                this.f15297b = n11;
                this.c = new h2.t(n11.d);
                this.f15301h = 0;
                this.f15306n = -9223372036854775807L;
                this.d = str;
                this.f15298e = i7;
                break;
        }
    }
}
