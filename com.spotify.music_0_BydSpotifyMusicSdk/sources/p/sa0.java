package p;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sa0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ sa0(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((CompletableObserver) obj).onComplete();
                break;
            case 1:
                ((MaybeObserver) obj).onComplete();
                break;
            case 2:
                ((Observer) obj).onComplete();
                break;
            default:
                ((rg2) obj).onComplete();
                break;
        }
    }
}
