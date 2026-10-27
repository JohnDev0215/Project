package I;

import A.I0;
import C.k;
import H7.w;
import L0.g;
import L0.j;
import L0.r;
import L0.t;
import L0.u;
import y.C2719y;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends C2719y {

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f2902S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public InterfaceC2782k f2903T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final I0 f2904U;

    public c(boolean z6, k kVar, boolean z10, g gVar, InterfaceC2782k interfaceC2782k) {
        super(kVar, null, z10, null, gVar, new b(0, interfaceC2782k, z6));
        this.f2902S = z6;
        this.f2903T = interfaceC2782k;
        this.f2904U = new I0(this, 21);
    }

    @Override // y.AbstractC2705j
    public final void K0(j jVar) {
        M0.a aVar = this.f2902S ? M0.a.l : M0.a.f4182m;
        w[] wVarArr = t.f3964a;
        u uVar = r.B;
        w wVar = t.f3964a[22];
        uVar.a(jVar, aVar);
    }
}
