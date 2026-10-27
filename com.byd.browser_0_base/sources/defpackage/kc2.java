package defpackage;

/* JADX INFO: compiled from: RootUiCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kc2 extends bh0 {
    public boolean e;
    public final /* synthetic */ nc2 f;

    public kc2(nc2 nc2Var) {
        this.f = nc2Var;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void d(int i, int i2) {
        if (i == 0) {
            this.e = false;
            return;
        }
        if (i == 1 || i == 2 || i == 3) {
            if (!this.e) {
                this.e = true;
            }
            this.f.D0.a();
        }
    }
}
