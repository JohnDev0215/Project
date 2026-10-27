package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class h5 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ h5(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((ObservableUnsubscribeOn$UnsubscribeObserver) obj).upstream.dispose();
                break;
            default:
                ((ObservableWindowTimed$WindowExactUnboundedObserver) obj).d();
                break;
        }
    }
}
