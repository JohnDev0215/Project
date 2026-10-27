package defpackage;

/* JADX INFO: compiled from: StatusIndicatorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nt2 extends vl {
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ qt2 g;

    public nt2(qt2 qt2Var, lq1 lq1Var) {
        this.g = qt2Var;
        this.f = lq1Var;
    }

    @Override // defpackage.vl
    public final void b() {
        this.f.run();
        this.g.r = null;
    }
}
