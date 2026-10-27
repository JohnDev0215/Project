package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class x0 extends Flowable {
    public final /* synthetic */ int e = 0;
    public final long k;
    public final TimeUnit l;
    public final Object m;

    public x0(Future future, long j, TimeUnit timeUnit) {
        this.m = future;
        this.k = j;
        this.l = timeUnit;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        int i = this.e;
        long j = this.k;
        Object obj = this.m;
        TimeUnit timeUnit = this.l;
        switch (i) {
            case 0:
                FlowableTimer$TimerSubscriber flowableTimer$TimerSubscriber = new FlowableTimer$TimerSubscriber(rg2Var);
                rg2Var.onSubscribe(flowableTimer$TimerSubscriber);
                Disposable disposableD = ((Scheduler) obj).d(flowableTimer$TimerSubscriber, j, timeUnit);
                while (!flowableTimer$TimerSubscriber.compareAndSet(null, disposableD)) {
                    if (flowableTimer$TimerSubscriber.get() != null) {
                        if (flowableTimer$TimerSubscriber.get() == DisposableHelper.c) {
                            disposableD.dispose();
                        }
                        break;
                    }
                }
                break;
            default:
                DeferredScalarSubscription deferredScalarSubscription = new DeferredScalarSubscription(rg2Var);
                rg2Var.onSubscribe(deferredScalarSubscription);
                try {
                    Object obj2 = timeUnit != null ? ((Future) obj).get(j, timeUnit) : ((Future) obj).get();
                    if (obj2 != null) {
                        deferredScalarSubscription.c(obj2);
                    } else {
                        rg2Var.onError(io.reactivex.rxjava3.internal.util.c.b("The future returned a null value."));
                    }
                } catch (Throwable th) {
                    he1.R(th);
                    if (deferredScalarSubscription.get() == 4) {
                        return;
                    }
                    rg2Var.onError(th);
                }
                break;
        }
    }

    public x0(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.k = j;
        this.l = timeUnit;
        this.m = scheduler;
    }
}
