package rxdogtag2;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Objects;
import p.sa0;
import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
final class DogTagCompletableObserver implements CompletableObserver, io.reactivex.rxjava3.observers.b {
    private final RxDogTag.Configuration config;
    private final CompletableObserver delegate;
    private final Throwable t = new Throwable();

    public DogTagCompletableObserver(RxDogTag.Configuration configuration, CompletableObserver completableObserver) {
        this.config = configuration;
        this.delegate = completableObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onComplete$4(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onComplete");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$2(Throwable th) {
        RxDogTag.reportError(this.config, this.t, th, "onError");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$3(Throwable th) {
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

    @Override // io.reactivex.rxjava3.observers.b
    public boolean hasCustomOnError() {
        CompletableObserver completableObserver = this.delegate;
        return (completableObserver instanceof io.reactivex.rxjava3.observers.b) && ((io.reactivex.rxjava3.observers.b) completableObserver).hasCustomOnError();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onComplete() {
        if (!this.config.guardObserverCallbacks) {
            this.delegate.onComplete();
            return;
        }
        a aVar = new a(this, 1);
        CompletableObserver completableObserver = this.delegate;
        Objects.requireNonNull(completableObserver);
        RxDogTag.guardedDelegateCall(aVar, new sa0(0, completableObserver));
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onError(Throwable th) {
        CompletableObserver completableObserver = this.delegate;
        if (!(completableObserver instanceof RxDogTagErrorReceiver)) {
            RxDogTag.reportError(this.config, this.t, th, null);
            return;
        }
        if (completableObserver instanceof RxDogTagTaggedExceptionReceiver) {
            completableObserver.onError(RxDogTag.createException(this.config, this.t, th, null));
        } else if (!this.config.guardObserverCallbacks) {
            completableObserver.onError(th);
        } else {
            int i = 0;
            RxDogTag.guardedDelegateCall(new a(this, i), new b(this, th, i));
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new a(this, 2), new b(this, disposable, 1));
        } else {
            this.delegate.onSubscribe(disposable);
        }
    }
}
