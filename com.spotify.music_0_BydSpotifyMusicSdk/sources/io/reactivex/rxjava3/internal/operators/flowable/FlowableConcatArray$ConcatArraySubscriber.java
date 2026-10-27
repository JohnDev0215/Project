package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import p.az1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableConcatArray$ConcatArraySubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -8158322871608889516L;
    final boolean delayError;
    final rg2 downstream;
    List<Throwable> errors;
    int index;
    long produced;
    final az1[] sources;
    final AtomicInteger wip = new AtomicInteger();

    public FlowableConcatArray$ConcatArraySubscriber(az1[] az1VarArr, boolean z, rg2 rg2Var) {
        this.downstream = rg2Var;
        this.sources = az1VarArr;
        this.delayError = z;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.wip.getAndIncrement() == 0) {
            az1[] az1VarArr = this.sources;
            int length = az1VarArr.length;
            int i = this.index;
            while (i != length) {
                az1 az1Var = az1VarArr[i];
                if (az1Var == null) {
                    NullPointerException nullPointerException = new NullPointerException("A Publisher entry is null");
                    if (!this.delayError) {
                        this.downstream.onError(nullPointerException);
                        return;
                    }
                    List arrayList = this.errors;
                    if (arrayList == null) {
                        arrayList = new ArrayList((length - i) + 1);
                        this.errors = arrayList;
                    }
                    arrayList.add(nullPointerException);
                    i++;
                } else {
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0L;
                        f(j);
                    }
                    az1Var.subscribe(this);
                    i++;
                    this.index = i;
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
            List<Throwable> list = this.errors;
            if (list == null) {
                this.downstream.onComplete();
            } else if (list.size() == 1) {
                this.downstream.onError(list.get(0));
            } else {
                this.downstream.onError(new CompositeException(list));
            }
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (!this.delayError) {
            this.downstream.onError(th);
            return;
        }
        List arrayList = this.errors;
        if (arrayList == null) {
            arrayList = new ArrayList((this.sources.length - this.index) + 1);
            this.errors = arrayList;
        }
        arrayList.add(th);
        onComplete();
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.produced++;
        this.downstream.onNext(obj);
    }
}
