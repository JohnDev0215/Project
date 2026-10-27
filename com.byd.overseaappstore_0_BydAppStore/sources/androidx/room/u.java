package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ QueryInterceptorStatement b;

    public /* synthetic */ u(QueryInterceptorStatement queryInterceptorStatement, int i) {
        this.a = i;
        this.b = queryInterceptorStatement;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$executeUpdateDelete$1();
                break;
            default:
                this.b.lambda$simpleQueryForString$4();
                break;
        }
    }
}
