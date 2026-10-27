package rxdogtag2;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Objects;
import p.sa0;
import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
final class DogTagObserver<T> implements Observer<T>, io.reactivex.rxjava3.observers.b {
    private final RxDogTag.Configuration config;
    private final Observer<T> delegate;
    private final Throwable t = new Throwable();

    public DogTagObserver(RxDogTag.Configuration configuration, Observer<T> observer) {
        this.config = configuration;
        this.delegate = observer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onComplete$6(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onComplete");
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
    public /* synthetic */ void lambda$onNext$2(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onNext");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNext$3(Object obj) {
        this.delegate.onNext(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubscribe$0(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onSubscribe");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubscribe$1(Disposable disposable) {
        this.delegate.onSubscribe(disposable);
    }

    @Override // io.reactivex.rxjava3.observers.b
    public boolean hasCustomOnError() {
        Observer<T> observer = this.delegate;
        return (observer instanceof io.reactivex.rxjava3.observers.b) && ((io.reactivex.rxjava3.observers.b) observer).hasCustomOnError();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (!this.config.guardObserverCallbacks) {
            this.delegate.onComplete();
            return;
        }
        d dVar = new d(this, 1);
        Observer<T> observer = this.delegate;
        Objects.requireNonNull(observer);
        RxDogTag.guardedDelegateCall(dVar, new sa0(2, observer));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        Observer<T> observer = this.delegate;
        if (!(observer instanceof RxDogTagErrorReceiver)) {
            RxDogTag.reportError(this.config, this.t, th, null);
            return;
        }
        if (observer instanceof RxDogTagTaggedExceptionReceiver) {
            observer.onError(RxDogTag.createException(this.config, this.t, th, null));
        } else if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new d(this, 0), new b(this, th, 5));
        } else {
            observer.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new d(this, 3), new b(this, t, 7));
        } else {
            this.delegate.onNext(t);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new d(this, 2), new b(this, disposable, 6));
        } else {
            this.delegate.onSubscribe(disposable);
        }
    }
}
