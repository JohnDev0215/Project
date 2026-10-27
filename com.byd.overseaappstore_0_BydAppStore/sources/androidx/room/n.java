package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ QueryInterceptorDatabase b;

    public /* synthetic */ n(QueryInterceptorDatabase queryInterceptorDatabase, int i) {
        this.a = i;
        this.b = queryInterceptorDatabase;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$endTransaction$4();
                break;
            default:
                this.b.lambda$beginTransactionWithListener$2();
                break;
        }
    }
}
