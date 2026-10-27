package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p42 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object k;

    public /* synthetic */ p42(int i, int i2, Object obj) {
        this.c = i2;
        this.k = obj;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        int i2 = this.e;
        Object obj = this.k;
        switch (i) {
            case 0:
                ((zu) obj).u0(i2);
                break;
            default:
                ((e0) obj).f118p.r(i2);
                break;
        }
    }
}
