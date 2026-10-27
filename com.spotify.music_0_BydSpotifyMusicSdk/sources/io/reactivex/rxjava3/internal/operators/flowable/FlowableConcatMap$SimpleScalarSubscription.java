package io.reactivex.rxjava3.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicBoolean;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableConcatMap$SimpleScalarSubscription<T> extends AtomicBoolean implements tg2 {
    private static final long serialVersionUID = -7606889335172043256L;
    final rg2 downstream;
    final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public FlowableConcatMap$SimpleScalarSubscription(Object obj, rg2 rg2Var) {
        this.value = obj;
        this.downstream = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (j <= 0 || !compareAndSet(false, true)) {
            return;
        }
        rg2 rg2Var = this.downstream;
        rg2Var.onNext(this.value);
        rg2Var.onComplete();
    }

    @Override // p.tg2
    public final void cancel() {
    }
}
