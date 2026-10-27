package defpackage;

/* JADX INFO: compiled from: StatusIndicatorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pt2 extends vl {
    public final /* synthetic */ qt2 f;

    public pt2(qt2 qt2Var) {
        this.f = qt2Var;
    }

    @Override // defpackage.vl
    public final void b() {
        qt2 qt2Var = this.f;
        if (qt2Var.m.get().booleanValue()) {
            qt2Var.n.onResult(new d92(4, this));
        } else {
            qt2Var.b(true);
        }
        qt2Var.s = null;
    }
}
