package rxdogtag2;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import java.util.Objects;
import p.rg2;
import p.sa0;
import p.tg2;
import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
final class DogTagSubscriber<T> implements FlowableSubscriber<T>, io.reactivex.rxjava3.observers.b {
    private final RxDogTag.Configuration config;
    private final rg2 delegate;
    private final Throwable t = new Throwable();

    public DogTagSubscriber(RxDogTag.Configuration configuration, rg2 rg2Var) {
        this.config = configuration;
        this.delegate = rg2Var;
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
    public /* synthetic */ void lambda$onSubscribe$1(tg2 tg2Var) {
        this.delegate.onSubscribe(tg2Var);
    }

    @Override // io.reactivex.rxjava3.observers.b
    public boolean hasCustomOnError() {
        rg2 rg2Var = this.delegate;
        return (rg2Var instanceof io.reactivex.rxjava3.observers.b) && ((io.reactivex.rxjava3.observers.b) rg2Var).hasCustomOnError();
    }

    @Override // p.rg2
    public void onComplete() {
        if (!this.config.guardObserverCallbacks) {
            this.delegate.onComplete();
            return;
        }
        f fVar = new f(this, 0);
        rg2 rg2Var = this.delegate;
        Objects.requireNonNull(rg2Var);
        RxDogTag.guardedDelegateCall(fVar, new sa0(3, rg2Var));
    }

    @Override // p.rg2
    public void onError(Throwable th) {
        rg2 rg2Var = this.delegate;
        if (!(rg2Var instanceof RxDogTagErrorReceiver)) {
            RxDogTag.reportError(this.config, this.t, th, null);
            return;
        }
        if (rg2Var instanceof RxDogTagTaggedExceptionReceiver) {
            rg2Var.onError(RxDogTag.createException(this.config, this.t, th, null));
        } else if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new f(this, 3), new b(this, th, 13));
        } else {
            rg2Var.onError(th);
        }
    }

    @Override // p.rg2
    public void onNext(T t) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new f(this, 1), new b(this, t, 11));
        } else {
            this.delegate.onNext(t);
        }
    }

    @Override // p.rg2
    public void onSubscribe(tg2 tg2Var) {
        if (this.config.guardObserverCallbacks) {
            RxDogTag.guardedDelegateCall(new f(this, 2), new b(this, tg2Var, 12));
        } else {
            this.delegate.onSubscribe(tg2Var);
        }
    }
}
