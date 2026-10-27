package io.reactivex.rxjava3.internal.queue;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class MpscLinkedQueue$LinkedQueueNode<E> extends AtomicReference<MpscLinkedQueue$LinkedQueueNode<E>> {
    private static final long serialVersionUID = 2404266111789071508L;
    private E value;

    public MpscLinkedQueue$LinkedQueueNode() {
    }

    public final Object a() {
        E e = this.value;
        this.value = null;
        return e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MpscLinkedQueue$LinkedQueueNode(Object obj) {
        this.value = obj;
    }
}
