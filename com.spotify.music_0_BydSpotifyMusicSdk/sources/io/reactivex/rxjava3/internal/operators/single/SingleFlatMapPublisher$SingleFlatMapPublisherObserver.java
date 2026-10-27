package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.az1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMapPublisher$SingleFlatMapPublisherObserver<S, T> extends AtomicLong implements SingleObserver<S>, FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = 7759721921468635667L;
    Disposable disposable;
    final rg2 downstream;
    final io.reactivex.rxjava3.functions.o mapper;
    final AtomicReference<tg2> parent = new AtomicReference<>();

    public SingleFlatMapPublisher$SingleFlatMapPublisherObserver(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = rg2Var;
        this.mapper = oVar;
    }

    @Override // p.tg2
    public final void a(long j) {
        SubscriptionHelper.c(this.parent, this, j);
    }

    @Override // p.tg2
    public final void cancel() {
        this.disposable.dispose();
        SubscriptionHelper.b(this.parent);
    }

    @Override // p.rg2
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
        this.downstream.onSubscribe(this);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "the mapper returned a null Publisher");
            az1 az1Var = (az1) objApply;
            if (this.parent.get() != SubscriptionHelper.c) {
                az1Var.subscribe(this);
            }
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        SubscriptionHelper.d(this.parent, this, tg2Var);
    }
}
