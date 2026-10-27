package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ st1 e;

    public /* synthetic */ c0(st1 st1Var, int i) {
        this.c = i;
        this.e = st1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        st1 st1Var = this.e;
        switch (i) {
            case 0:
                st1Var.i();
                break;
            case 1:
                st1Var.q();
                break;
            case 2:
                st1Var.h();
                break;
            case 3:
                st1Var.m();
                break;
            case 4:
                st1Var.next();
                break;
            default:
                st1Var.h();
                break;
        }
    }
}
