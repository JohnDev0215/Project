package Q;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Y0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0.V f5760m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f5761n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0.V f5762p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0.V f5763q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0.V f5764r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C0.V f5765s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ C0.V f5766t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ C0.V f5767u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ C0.V f5768v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ C0.V f5769w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ Z0 f5770x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f5771y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ C0.M f5772z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y0(C0.V v10, int i7, int i10, C0.V v11, C0.V v12, C0.V v13, C0.V v14, C0.V v15, C0.V v16, C0.V v17, C0.V v18, Z0 z02, int i11, C0.M m10) {
        super(1);
        this.f5760m = v10;
        this.f5761n = i7;
        this.o = i10;
        this.f5762p = v11;
        this.f5763q = v12;
        this.f5764r = v13;
        this.f5765s = v14;
        this.f5766t = v15;
        this.f5767u = v16;
        this.f5768v = v17;
        this.f5769w = v18;
        this.f5770x = z02;
        this.f5771y = i11;
        this.f5772z = m10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        int iW;
        C0.V v10;
        C0.U u4 = (C0.U) obj;
        C0.V v11 = this.f5762p;
        C0.V v12 = this.f5768v;
        C0.M m10 = this.f5772z;
        C0.V v13 = this.f5769w;
        C0.V v14 = this.f5767u;
        C0.V v15 = this.f5766t;
        C0.V v16 = this.f5765s;
        C0.V v17 = this.f5764r;
        C0.V v18 = this.f5763q;
        int i7 = this.o;
        int i10 = this.f5761n;
        Z0 z02 = this.f5770x;
        C0.V v19 = this.f5760m;
        if (v19 != null) {
            boolean z6 = z02.f5781a;
            int i11 = v19.f871m + this.f5771y;
            float density = m10.getDensity();
            float f10 = X0.f5757a;
            C0.U.e(u4, v12, 0L);
            int iE = i7 - R.O.e(v13);
            if (v17 != null) {
                C0.U.f(u4, v17, 0, Math.round((1 + 0.0f) * ((iE - v17.f871m) / 2.0f)));
            }
            if (z6) {
                iW = Math.round((1 + 0.0f) * ((iE - v19.f871m) / 2.0f));
            } else {
                iW = C7.a.W(R.O.f7278b * density);
            }
            C0.U.f(u4, v19, R.O.f(v17), iW - C7.a.W((iW - r0) * z02.f5782b));
            if (v15 != null) {
                v10 = v15;
                C0.U.f(u4, v10, R.O.f(v17), i11);
            } else {
                v10 = v15;
            }
            int iF = R.O.f(v10) + R.O.f(v17);
            C0.U.f(u4, v11, iF, i11);
            if (v18 != null) {
                C0.U.f(u4, v18, iF, i11);
            }
            if (v14 != null) {
                C0.U.f(u4, v14, (i10 - R.O.f(v16)) - v14.l, i11);
            }
            if (v16 != null) {
                C0.U.f(u4, v16, i10 - v16.l, Math.round((1 + 0.0f) * ((iE - v16.f871m) / 2.0f)));
            }
            if (v13 != null) {
                C0.U.f(u4, v13, 0, iE);
            }
        } else {
            boolean z10 = z02.f5781a;
            float density2 = m10.getDensity();
            float f11 = X0.f5757a;
            C0.U.e(u4, v12, 0L);
            int iE2 = i7 - R.O.e(v13);
            int iW2 = C7.a.W(z02.c.d() * density2);
            if (v17 != null) {
                C0.U.f(u4, v17, 0, Math.round((1 + 0.0f) * ((iE2 - v17.f871m) / 2.0f)));
            }
            if (v15 != null) {
                C0.U.f(u4, v15, R.O.f(v17), X0.d(z10, iE2, iW2, v15));
            }
            int iF2 = R.O.f(v15) + R.O.f(v17);
            C0.U.f(u4, v11, iF2, X0.d(z10, iE2, iW2, v11));
            if (v18 != null) {
                C0.U.f(u4, v18, iF2, X0.d(z10, iE2, iW2, v18));
            }
            if (v14 != null) {
                C0.U.f(u4, v14, (i10 - R.O.f(v16)) - v14.l, X0.d(z10, iE2, iW2, v14));
            }
            if (v16 != null) {
                C0.U.f(u4, v16, i10 - v16.l, Math.round((1 + 0.0f) * ((iE2 - v16.f871m) / 2.0f)));
            }
            if (v13 != null) {
                C0.U.f(u4, v13, 0, iE2);
            }
        }
        return m7.q.f19267a;
    }
}
