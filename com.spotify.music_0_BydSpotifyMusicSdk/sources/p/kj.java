package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kj implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ oj e;
    public final /* synthetic */ long k;

    public /* synthetic */ kj(oj ojVar, long j, int i) {
        this.c = i;
        this.e = ojVar;
        this.k = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        long j = this.k;
        oj ojVar = this.e;
        switch (i) {
            case 0:
                ojVar.f.o((int) j);
                break;
            default:
                ojVar.f.n((int) j);
                break;
        }
    }
}
