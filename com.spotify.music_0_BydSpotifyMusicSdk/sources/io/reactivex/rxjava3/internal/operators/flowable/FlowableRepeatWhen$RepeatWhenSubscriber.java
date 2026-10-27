package io.reactivex.rxjava3.internal.operators.flowable;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRepeatWhen$RepeatWhenSubscriber<T> extends FlowableRepeatWhen$WhenSourceSubscriber<T, Object> {
    private static final long serialVersionUID = -2680129890138081029L;

    @Override // p.rg2
    public final void onComplete() {
        h(0);
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRepeatWhen$WhenSourceSubscriber, p.rg2
    public final void onError(Throwable th) {
        this.receiver.cancel();
        this.downstream.onError(th);
    }
}
