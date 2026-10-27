package rxdogtag2;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Objects;
import p.sa0;
import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
final class DogTagMaybeObserver<T> implements MaybeObserver<T>, io.reactivex.rxjava3.observers.b {
    private final RxDogTag.Configuration config;
    private final MaybeObserver<T> delegate;
    private final Throwable t = new Throwable();

    public DogTagMaybeObserver(RxDogTag.Configuration configuration, MaybeObserver<T> maybeObserver) {
        this.config = configuration;
        this.delegate = maybeObserver;
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
        MaybeObserver<T> maybeObserver = this.delegate;
        return (maybeObserver instanceof io.reactivex.rxjava3.observers.b) && ((io.reactivex.rxjava3.observers.b) maybeObserver).hasCustomOnError();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public void onComplete() {
        if (!this.config.guardObserverCallbacks) {
            this.delegate.onComplete();
            return;
        }
        c cVar = new c(this, 2);
        MaybeObserver<T> maybeObserver = this.delegate;
        Objects.requireNonNull(maybeObserver);
        RxDogTag.guardedDelegateCall(cVar, new sa0(1, maybeObserver));
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public void onError(Throwable th) {
        MaybeObserver<T> maybeObserver = this.delegate;
        if (!(maybeObserver instanceof RxDogTagErrorReceiver)) {
            RxDogTag.reportError(this.config, this.t, th, null);
            return;
        }
        if (maybeObserver instanceof RxDogTagTaggedExceptionReceiver) {
            maybeObserver.onError(RxDogTag.createException(this.config, this.t, th, null));
        } else if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new c(this, 1), new b(this, th, 3));
        } else {
            maybeObserver.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public void onSubscribe(Disposable disposable) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new c(this, 0), new b(this, disposable, 2));
        } else {
            this.delegate.onSubscribe(disposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T t) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new c(this, 3), new b(this, t, 4));
        } else {
            this.delegate.onSuccess(t);
        }
    }
}
