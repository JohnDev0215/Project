package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class a52 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ c52 f;

    public /* synthetic */ a52(c52 c52Var, int i) {
        this.e = i;
        this.f = c52Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        c52 c52Var = this.f;
        switch (i) {
            case 0:
                c52Var.l.scrollTo(0, c52Var.g.getTop());
                break;
            default:
                c52Var.l.pageScroll(130);
                break;
        }
    }
}
