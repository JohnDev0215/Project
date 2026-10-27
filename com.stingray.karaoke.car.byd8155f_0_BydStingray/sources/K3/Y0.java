package k3;

import e2.C1195c;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Y0 implements l1, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f17872m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f17873n;

    public /* synthetic */ Y0(int i7, Object obj, boolean z6) {
        this.l = i7;
        this.f17873n = obj;
        this.f17872m = z6;
    }

    @Override // k3.l1
    public Object a(I0 i02, C1679y0 c1679y0, int i7) {
        switch (this.l) {
            case 0:
                d5.d0 d0VarB = d5.I.B((e2.F) this.f17873n);
                boolean z6 = this.f17872m;
                return i02.p(c1679y0, d0VarB, z6 ? -1 : i02.f17811t.b0(), z6 ? -9223372036854775807L : i02.f17811t.N0());
            default:
                boolean z10 = this.f17872m;
                return i02.p(c1679y0, (d5.d0) this.f17873n, z10 ? -1 : i02.f17811t.b0(), z10 ? -9223372036854775807L : i02.f17811t.N0());
        }
    }

    @Override // h2.g
    public void accept(Object obj) {
        ((u1) obj).U((C1195c) this.f17873n, this.f17872m);
    }
}
