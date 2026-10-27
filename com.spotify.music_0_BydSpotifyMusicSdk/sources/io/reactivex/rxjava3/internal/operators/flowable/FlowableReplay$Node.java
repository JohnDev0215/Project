package io.reactivex.rxjava3.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class FlowableReplay$Node extends AtomicReference<FlowableReplay$Node> {
    private static final long serialVersionUID = 245354315435971818L;
    final long index;
    final Object value;

    public FlowableReplay$Node(long j, Object obj) {
        this.value = obj;
        this.index = j;
    }
}
