package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class p4 implements Runnable {
    public final /* synthetic */ int c;
    public final Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ p4(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                ((q4) obj2).c.subscribe((ObservableSubscribeOn$SubscribeOnObserver) obj);
                break;
            default:
                ((z0) obj2).c.onNext(obj);
                break;
        }
    }
}
