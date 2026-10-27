package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ QueryInterceptorDatabase b;
    public final /* synthetic */ String c;

    public /* synthetic */ q(QueryInterceptorDatabase queryInterceptorDatabase, String str, int i) {
        this.a = i;
        this.b = queryInterceptorDatabase;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$query$6(this.c);
                break;
            default:
                this.b.lambda$execSQL$10(this.c);
                break;
        }
    }
}
