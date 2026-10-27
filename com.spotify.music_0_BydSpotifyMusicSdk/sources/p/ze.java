package p;

/* JADX INFO: loaded from: classes.dex */
public final class ze implements a42 {
    public final /* synthetic */ d42 a;

    public ze(xa xaVar, ui0 ui0Var, lw1 lw1Var, cg2 cg2Var, b42 b42Var) {
        d51 d51Var = new d51(ui0Var, xaVar, lw1Var, 0);
        v03.j(xaVar, "appMetadata");
        v03.j(lw1Var, "policyInputs");
        this.a = new d42(vk1.a, cg2Var, d51Var, b42Var, 16);
    }

    @Override // p.a42
    public final void a() {
        this.a.a();
    }

    @Override // p.jx
    public final my1 b(bj bjVar) {
        return this.a.b(bjVar);
    }

    @Override // p.a42
    public final boolean c(byte[] bArr) {
        v03.j(bArr, "configuration");
        return this.a.c(bArr);
    }

    @Override // p.a42
    public final void clear() {
        this.a.clear();
    }

    @Override // p.a42
    public final pc d(String str, String str2) {
        return this.a.d(str, str2);
    }

    @Override // p.a42
    public final Long getSnapshotId() {
        return this.a.getSnapshotId();
    }
}
