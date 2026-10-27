package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nj implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ oj e;
    public final /* synthetic */ int k;

    public /* synthetic */ nj(oj ojVar, int i, int i2) {
        this.c = i2;
        this.e = ojVar;
        this.k = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        int i2 = this.k;
        oj ojVar = this.e;
        switch (i) {
            case 0:
                ojVar.getClass();
                ojVar.f.c(i2 == 1);
                break;
            default:
                ojVar.f.k(i2);
                break;
        }
    }
}
