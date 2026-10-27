package y;

/* JADX INFO: loaded from: classes.dex */
public final class v0 extends g0.n implements E0.p0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f24015A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public y0 f24016y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f24017z;

    @Override // E0.p0
    public final void H(L0.j jVar) {
        H7.w[] wVarArr = L0.t.f3964a;
        L0.u uVar = L0.r.l;
        H7.w[] wVarArr2 = L0.t.f3964a;
        H7.w wVar = wVarArr2[6];
        uVar.a(jVar, Boolean.TRUE);
        L0.h hVar = new L0.h(new u0(this, 0), new u0(this, 1), this.f24017z);
        if (this.f24015A) {
            L0.u uVar2 = L0.r.f3952p;
            H7.w wVar2 = wVarArr2[11];
            uVar2.a(jVar, hVar);
        } else {
            L0.u uVar3 = L0.r.o;
            H7.w wVar3 = wVarArr2[10];
            uVar3.a(jVar, hVar);
        }
    }
}
