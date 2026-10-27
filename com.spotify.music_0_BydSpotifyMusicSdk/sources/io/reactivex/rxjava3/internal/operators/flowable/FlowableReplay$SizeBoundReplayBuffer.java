package io.reactivex.rxjava3.internal.operators.flowable;

/* JADX INFO: loaded from: classes.dex */
final class FlowableReplay$SizeBoundReplayBuffer<T> extends FlowableReplay$BoundedReplayBuffer<T> {
    private static final long serialVersionUID = -5898283885385201806L;
    final int limit;

    public FlowableReplay$SizeBoundReplayBuffer(int i, boolean z) {
        super(z);
        this.limit = i;
    }
}
