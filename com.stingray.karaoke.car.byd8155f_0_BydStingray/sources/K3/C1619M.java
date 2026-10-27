package k3;

import e2.C1195c;
import h5.C1373E;

/* JADX INFO: renamed from: k3.M, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1619M implements X, P0 {
    public final /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f17827m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f17828n;

    public /* synthetic */ C1619M(Object obj, Object obj2, boolean z6) {
        this.f17827m = obj;
        this.f17828n = obj2;
        this.l = z6;
    }

    @Override // k3.P0
    public void d(C1679y0 c1679y0) {
        Q0 q02 = (Q0) this.f17827m;
        q02.getClass();
        C1373E c1373eP = q02.f17843f.p(c1679y0, d5.I.B((e2.F) this.f17828n), -1, -9223372036854775807L);
        t4.e eVar = new t4.e(q02, c1679y0, this.l);
        c1373eP.b(new h5.t(c1373eP, 0, eVar), h5.r.l);
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        C1628b0 c1628b0 = (C1628b0) this.f17827m;
        c1628b0.getClass();
        interfaceC1655m.t0(c1628b0.c, i7, ((C1195c) this.f17828n).c(), this.l);
    }
}
