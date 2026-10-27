package h3;

import B2.L;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class n implements InterfaceC1358i {
    public static final float[] l = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final M.t f15380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.t f15381b;
    public final boolean[] c = new boolean[4];
    public final l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final x f15382e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public m f15383f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f15384g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f15385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public L f15386i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15387j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f15388k;

    public n(M.t tVar) {
        this.f15380a = tVar;
        l lVar = new l();
        lVar.f15373e = new byte[128];
        this.d = lVar;
        this.f15388k = -9223372036854775807L;
        this.f15382e = new x(178);
        this.f15381b = new h2.t();
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        i2.n.a(this.c);
        l lVar = this.d;
        lVar.f15371a = false;
        lVar.c = 0;
        lVar.f15372b = 0;
        m mVar = this.f15383f;
        if (mVar != null) {
            mVar.f15375b = false;
            mVar.c = false;
            mVar.d = false;
            mVar.f15376e = -1;
        }
        x xVar = this.f15382e;
        if (xVar != null) {
            xVar.g();
        }
        this.f15384g = 0L;
        this.f15388k = -9223372036854775807L;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0252  */
    @Override // h3.InterfaceC1358i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(h2.t r19) {
        /*
            Method dump skipped, instruction units count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.n.c(h2.t):void");
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
        AbstractC1346c.k(this.f15383f);
        if (z6) {
            this.f15383f.b(0, this.f15384g, this.f15387j);
            m mVar = this.f15383f;
            mVar.f15375b = false;
            mVar.c = false;
            mVar.d = false;
            mVar.f15376e = -1;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.f15388k = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15385h = h10.f15293e;
        h10.b();
        L lU = tVar.u(h10.d, 2);
        this.f15386i = lU;
        this.f15383f = new m(lU);
        this.f15380a.y(tVar, h10);
    }
}
