package defpackage;

/* JADX INFO: compiled from: ShareSheetCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wk2 extends bh0 {
    public final /* synthetic */ xk2 e;

    public wk2(xk2 xk2Var) {
        this.e = xk2Var;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void b(ii iiVar) {
        xk2 xk2Var = this.e;
        vk2 vk2Var = xk2Var.t;
        if (vk2Var == null) {
            return;
        }
        if (iiVar == vk2Var) {
            vk2Var.i.addOnLayoutChangeListener(new uw(2, xk2Var));
        } else {
            vk2Var.i.removeOnLayoutChangeListener(new nm1(1, xk2Var));
        }
    }
}
