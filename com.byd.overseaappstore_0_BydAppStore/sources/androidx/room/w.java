package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ w(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((QueryInterceptorStatement) this.b).lambda$execute$0();
                break;
            case 1:
                ((QueryInterceptorStatement) this.b).lambda$simpleQueryForLong$3();
                break;
            default:
                ((QueryInterceptorDatabase) this.b).lambda$beginTransactionWithListenerNonExclusive$3();
                break;
        }
    }
}
