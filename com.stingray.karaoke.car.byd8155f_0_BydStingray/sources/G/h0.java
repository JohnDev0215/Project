package G;

/* JADX INFO: loaded from: classes.dex */
public final class h0 implements U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f2566b;
    public final i0 c;
    public C0.d0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2568f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2569g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f0 f2570h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2571i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ l3.P f2572j;

    public h0(l3.P p10, int i7, long j3, i0 i0Var) {
        this.f2572j = p10;
        this.f2565a = i7;
        this.f2566b = j3;
        this.c = i0Var;
    }

    @Override // G.U
    public final void a() {
        this.f2571i = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x015b A[Catch: all -> 0x014a, TryCatch #2 {all -> 0x014a, blocks: (B:70:0x0119, B:72:0x0121, B:74:0x0127, B:78:0x0133, B:81:0x014c, B:82:0x0153, B:84:0x015b, B:88:0x016b, B:89:0x0171), top: B:128:0x0119 }] */
    /* JADX WARN: Type inference failed for: r11v2, types: [A7.o, z7.k] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(B2.v r14) {
        /*
            Method dump skipped, instruction units count: 507
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G.h0.b(B2.v):boolean");
    }

    public final boolean c() {
        if (!this.f2568f) {
            int iB = ((I) ((G) this.f2572j.f18537m).f2504b.a()).b();
            int i7 = this.f2565a;
            if (i7 >= 0 && i7 < iB) {
                return true;
            }
        }
        return false;
    }

    @Override // G.U
    public final void cancel() {
        if (this.f2568f) {
            return;
        }
        this.f2568f = true;
        C0.d0 d0Var = this.d;
        if (d0Var != null) {
            d0Var.a();
        }
        this.d = null;
    }

    public final void d() {
        if (!c()) {
            throw new IllegalArgumentException("Callers should check whether the request is still valid before calling performComposition()");
        }
        if (this.d != null) {
            throw new IllegalArgumentException("Request was already composed!");
        }
        l3.P p10 = this.f2572j;
        I i7 = (I) ((G) p10.f18537m).f2504b.a();
        int i10 = this.f2565a;
        Object objC = i7.c(i10);
        this.d = ((C0.f0) p10.f18538n).a().g(objC, ((G) p10.f18537m).a(objC, i10, i7.d(i10)));
    }

    public final void e(long j3) {
        if (this.f2568f) {
            throw new IllegalArgumentException("Callers should check whether the request is still valid before calling performMeasure()");
        }
        if (this.f2567e) {
            throw new IllegalArgumentException("Request was already measured!");
        }
        this.f2567e = true;
        C0.d0 d0Var = this.d;
        if (d0Var == null) {
            throw new IllegalArgumentException("performComposition() must be called before performMeasure()");
        }
        int iC = d0Var.c();
        for (int i7 = 0; i7 < iC; i7++) {
            d0Var.e(j3, i7);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("HandleAndRequestImpl { index = ");
        sb.append(this.f2565a);
        sb.append(", constraints = ");
        sb.append((Object) a1.a.l(this.f2566b));
        sb.append(", isComposed = ");
        sb.append(this.d != null);
        sb.append(", isMeasured = ");
        sb.append(this.f2567e);
        sb.append(", isCanceled = ");
        sb.append(this.f2568f);
        sb.append(" }");
        return sb.toString();
    }
}
