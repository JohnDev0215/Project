package p;

import io.reactivex.rxjava3.core.ObservableEmitter;

/* JADX INFO: loaded from: classes.dex */
public final class wg2 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ ObservableEmitter e;

    public /* synthetic */ wg2(ObservableEmitter observableEmitter, int i) {
        this.c = i;
        this.e = observableEmitter;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        ObservableEmitter observableEmitter = this.e;
        switch (i) {
            case 0:
                v03.j(obj, "value");
                observableEmitter.onNext(obj);
                break;
            default:
                Throwable th = (Throwable) obj;
                v03.j(th, "t");
                observableEmitter.tryOnError(th);
                break;
        }
    }
}
