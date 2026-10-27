package defpackage;

/* JADX INFO: compiled from: StatusIndicatorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kt2 extends vl {
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ qt2 g;

    public kt2(qt2 qt2Var, gt2 gt2Var) {
        this.g = qt2Var;
        this.f = gt2Var;
    }

    @Override // defpackage.vl
    public final void b() {
        this.f.run();
        this.g.p = null;
    }
}
