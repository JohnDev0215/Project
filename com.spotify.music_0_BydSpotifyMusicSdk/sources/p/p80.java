package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p80 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ q80 e;

    public /* synthetic */ p80(q80 q80Var, int i) {
        this.c = i;
        this.e = q80Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        q80 q80Var = this.e;
        switch (i) {
            case 0:
                q80.b(q80Var);
                break;
            case 1:
                q80.a(q80Var);
                break;
            case 2:
                q80.a(q80Var);
                break;
            case 3:
                q80.b(q80Var);
                break;
            default:
                q80.b(q80Var);
                break;
        }
    }
}
