package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class f0 implements FlowableSubscriber, Disposable {
    public final MaybeObserver c;
    public final io.reactivex.rxjava3.functions.c e;
    public Object k;
    public tg2 l;
    public boolean m;

    public f0(MaybeObserver maybeObserver, io.reactivex.rxjava3.functions.c cVar) {
        this.c = maybeObserver;
        this.e = cVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l.cancel();
        this.m = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.m) {
            return;
        }
        this.m = true;
        Object obj = this.k;
        MaybeObserver maybeObserver = this.c;
        if (obj != null) {
            maybeObserver.onSuccess(obj);
        } else {
            maybeObserver.onComplete();
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
        } else {
            this.m = true;
            this.c.onError(th);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.m) {
            return;
        }
        Object obj2 = this.k;
        if (obj2 == null) {
            this.k = obj;
            return;
        }
        try {
            Object objK = this.e.k(obj2, obj);
            Objects.requireNonNull(objK, "The reducer returned a null value");
            this.k = objK;
        } catch (Throwable th) {
            he1.R(th);
            this.l.cancel();
            onError(th);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.l, tg2Var)) {
            this.l = tg2Var;
            this.c.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
