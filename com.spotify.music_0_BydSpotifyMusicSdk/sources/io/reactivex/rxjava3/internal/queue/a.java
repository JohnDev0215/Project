package io.reactivex.rxjava3.internal.queue;

import io.reactivex.rxjava3.operators.f;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class a implements f {
    public final AtomicReference c;
    public final AtomicReference e;

    public a() {
        AtomicReference atomicReference = new AtomicReference();
        this.c = atomicReference;
        AtomicReference atomicReference2 = new AtomicReference();
        this.e = atomicReference2;
        MpscLinkedQueue$LinkedQueueNode mpscLinkedQueue$LinkedQueueNode = new MpscLinkedQueue$LinkedQueueNode();
        atomicReference2.lazySet(mpscLinkedQueue$LinkedQueueNode);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return ((MpscLinkedQueue$LinkedQueueNode) this.e.get()) == ((MpscLinkedQueue$LinkedQueueNode) this.c.get());
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        MpscLinkedQueue$LinkedQueueNode mpscLinkedQueue$LinkedQueueNode = new MpscLinkedQueue$LinkedQueueNode(obj);
        ((MpscLinkedQueue$LinkedQueueNode) this.c.getAndSet(mpscLinkedQueue$LinkedQueueNode)).lazySet(mpscLinkedQueue$LinkedQueueNode);
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        MpscLinkedQueue$LinkedQueueNode mpscLinkedQueue$LinkedQueueNode;
        AtomicReference atomicReference = this.e;
        MpscLinkedQueue$LinkedQueueNode mpscLinkedQueue$LinkedQueueNode2 = (MpscLinkedQueue$LinkedQueueNode) atomicReference.get();
        MpscLinkedQueue$LinkedQueueNode mpscLinkedQueue$LinkedQueueNode3 = mpscLinkedQueue$LinkedQueueNode2.get();
        if (mpscLinkedQueue$LinkedQueueNode3 != null) {
            Object objA = mpscLinkedQueue$LinkedQueueNode3.a();
            atomicReference.lazySet(mpscLinkedQueue$LinkedQueueNode3);
            return objA;
        }
        if (mpscLinkedQueue$LinkedQueueNode2 == ((MpscLinkedQueue$LinkedQueueNode) this.c.get())) {
            return null;
        }
        do {
            mpscLinkedQueue$LinkedQueueNode = mpscLinkedQueue$LinkedQueueNode2.get();
        } while (mpscLinkedQueue$LinkedQueueNode == null);
        Object objA2 = mpscLinkedQueue$LinkedQueueNode.a();
        atomicReference.lazySet(mpscLinkedQueue$LinkedQueueNode);
        return objA2;
    }
}
