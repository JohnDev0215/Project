package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class gg1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ hg1 f;
    public final /* synthetic */ Runnable g;

    public /* synthetic */ gg1(hg1 hg1Var, Runnable runnable, int i) {
        this.e = i;
        this.f = hg1Var;
        this.g = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Runnable runnable = this.g;
        hg1 hg1Var = this.f;
        switch (i) {
            case 0:
                ((jq) hg1Var.d).c();
                ((jq) hg1Var.d).a();
                hg1Var.c = null;
                hg1Var.a = null;
                runnable.run();
                break;
            default:
                ((jq) hg1Var.d).a();
                runnable.run();
                break;
        }
    }
}
