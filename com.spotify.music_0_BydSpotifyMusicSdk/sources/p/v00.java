package p;

/* JADX INFO: loaded from: classes.dex */
public final class v00 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ w00 e;

    public /* synthetic */ v00(w00 w00Var, int i) {
        this.c = i;
        this.e = w00Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        w00 w00Var = this.e;
        switch (i) {
            case 0:
                w00Var.m = w00Var.a();
                break;
            default:
                w00Var.b();
                break;
        }
    }
}
