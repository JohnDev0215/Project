package io.reactivex.rxjava3.internal.operators.flowable;

/* JADX INFO: loaded from: classes.dex */
final class FlowableOnBackpressureLatest$BackpressureLatestSubscriber<T> extends AbstractBackpressureThrottlingSubscriber<T, T> {
    private static final long serialVersionUID = 163080509307634843L;

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // p.rg2
    public final void onNext(Object obj) {
        this.current.lazySet((R) obj);
        c();
    }
}
