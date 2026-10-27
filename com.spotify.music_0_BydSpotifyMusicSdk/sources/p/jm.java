package p;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class jm extends Observable {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ jm(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((Observable) obj).subscribe(new im(observer));
                break;
            case 1:
                ((Observable) obj).subscribe(new y42(observer));
                break;
            default:
                ((CompletableSource) obj).subscribe(new io.reactivex.rxjava3.internal.operators.observable.v1(observer));
                break;
        }
    }
}
