package defpackage;

/* JADX INFO: compiled from: NotificationPermissionRationaleBottomSheet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class uo1 extends bh0 {
    public final /* synthetic */ vo1 e;

    public uo1(vo1 vo1Var) {
        this.e = vo1Var;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void c(int i) {
        vo1 vo1Var = this.e;
        if (vo1Var.g == null) {
            return;
        }
        if (i == 1) {
            vo1Var.c(1, 6);
            return;
        }
        if (i == 2) {
            vo1Var.c(1, 5);
        } else if (i != 3) {
            vo1Var.c(1, 10);
        } else {
            vo1Var.c(1, 7);
        }
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void e() {
        vo1 vo1Var = this.e;
        if (vo1Var.e.h() == vo1Var) {
            vo1Var.j = true;
        }
    }
}
