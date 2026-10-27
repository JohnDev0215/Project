package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ v(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((QueryInterceptorStatement) this.b).lambda$executeInsert$2();
                break;
            default:
                ((InvalidationTracker) this.b).onAutoCloseCallback();
                break;
        }
    }
}
