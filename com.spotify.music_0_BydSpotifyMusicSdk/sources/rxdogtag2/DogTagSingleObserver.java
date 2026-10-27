package rxdogtag2;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
final class DogTagSingleObserver<T> implements SingleObserver<T>, io.reactivex.rxjava3.observers.b {
    private final RxDogTag.Configuration config;
    private final SingleObserver<T> delegate;
    private final Throwable t = new Throwable();

    public DogTagSingleObserver(RxDogTag.Configuration configuration, SingleObserver<T> singleObserver) {
        this.config = configuration;
        this.delegate = singleObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$4(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onError");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$5(Throwable th) {
        this.delegate.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubscribe$0(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onSubscribe");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubscribe$1(Disposable disposable) {
        this.delegate.onSubscribe(disposable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSuccess$2(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onSuccess");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSuccess$3(Object obj) {
        this.delegate.onSuccess(obj);
    }

    @Override // io.reactivex.rxjava3.observers.b
    public boolean hasCustomOnError() {
        SingleObserver<T> singleObserver = this.delegate;
        return (singleObserver instanceof io.reactivex.rxjava3.observers.b) && ((io.reactivex.rxjava3.observers.b) singleObserver).hasCustomOnError();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onError(Throwable th) {
        SingleObserver<T> singleObserver = this.delegate;
        if (!(singleObserver instanceof RxDogTagErrorReceiver)) {
            RxDogTag.reportError(this.config, this.t, th, null);
            return;
        }
        if (singleObserver instanceof RxDogTagTaggedExceptionReceiver) {
            singleObserver.onError(RxDogTag.createException(this.config, this.t, th, null));
        } else if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new e(this, 1), new b(this, th, 9));
        } else {
            singleObserver.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSubscribe(Disposable disposable) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new e(this, 2), new b(this, disposable, 10));
        } else {
            this.delegate.onSubscribe(disposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T t) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new e(this, 0), new b(this, t, 8));
        } else {
            this.delegate.onSuccess(t);
        }
    }
}
