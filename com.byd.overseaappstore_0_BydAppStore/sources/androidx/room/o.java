package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ QueryInterceptorDatabase b;

    public /* synthetic */ o(QueryInterceptorDatabase queryInterceptorDatabase, int i) {
        this.a = i;
        this.b = queryInterceptorDatabase;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$beginTransactionNonExclusive$1();
                break;
            case 1:
                this.b.lambda$setTransactionSuccessful$5();
                break;
            default:
                this.b.lambda$beginTransaction$0();
                break;
        }
    }
}
