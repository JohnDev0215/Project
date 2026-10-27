package io.reactivex.rxjava3.internal.operators.flowable;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRetryWhen$RetryWhenSubscriber<T> extends FlowableRepeatWhen$WhenSourceSubscriber<T, Throwable> {
    private static final long serialVersionUID = -2680129890138081029L;

    @Override // p.rg2
    public final void onComplete() {
        this.receiver.cancel();
        this.downstream.onComplete();
    }
}
