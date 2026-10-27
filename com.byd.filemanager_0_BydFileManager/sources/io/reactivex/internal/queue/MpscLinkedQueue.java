package io.reactivex.internal.queue;

import d.a.n.b.c;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class MpscLinkedQueue<T> implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicReference<LinkedQueueNode<T>> f4569b = new AtomicReference<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AtomicReference<LinkedQueueNode<T>> f4570c = new AtomicReference<>();

    static final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        LinkedQueueNode() {
        }

        public E getAndNullValue() {
            E eLpValue = lpValue();
            spValue(null);
            return eLpValue;
        }

        public E lpValue() {
            return this.value;
        }

        public LinkedQueueNode<E> lvNext() {
            return get();
        }

        public void soNext(LinkedQueueNode<E> linkedQueueNode) {
            lazySet(linkedQueueNode);
        }

        public void spValue(E e2) {
            this.value = e2;
        }

        LinkedQueueNode(E e2) {
            spValue(e2);
        }
    }

    public MpscLinkedQueue() {
        LinkedQueueNode<T> linkedQueueNode = new LinkedQueueNode<>();
        d(linkedQueueNode);
        e(linkedQueueNode);
    }

    LinkedQueueNode<T> a() {
        return this.f4570c.get();
    }

    LinkedQueueNode<T> b() {
        return this.f4570c.get();
    }

    LinkedQueueNode<T> c() {
        return this.f4569b.get();
    }

    @Override // d.a.n.b.c
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    void d(LinkedQueueNode<T> linkedQueueNode) {
        this.f4570c.lazySet(linkedQueueNode);
    }

    LinkedQueueNode<T> e(LinkedQueueNode<T> linkedQueueNode) {
        return this.f4569b.getAndSet(linkedQueueNode);
    }

    @Override // d.a.n.b.c
    public boolean isEmpty() {
        return b() == c();
    }

    @Override // d.a.n.b.c
    public boolean offer(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        LinkedQueueNode<T> linkedQueueNode = new LinkedQueueNode<>(t);
        e(linkedQueueNode).soNext(linkedQueueNode);
        return true;
    }

    @Override // d.a.n.b.c
    public T poll() {
        LinkedQueueNode<T> linkedQueueNodeLvNext;
        LinkedQueueNode<T> linkedQueueNodeA = a();
        LinkedQueueNode<T> linkedQueueNodeLvNext2 = linkedQueueNodeA.lvNext();
        if (linkedQueueNodeLvNext2 != null) {
            T andNullValue = linkedQueueNodeLvNext2.getAndNullValue();
            d(linkedQueueNodeLvNext2);
            return andNullValue;
        }
        if (linkedQueueNodeA == c()) {
            return null;
        }
        do {
            linkedQueueNodeLvNext = linkedQueueNodeA.lvNext();
        } while (linkedQueueNodeLvNext == null);
        T andNullValue2 = linkedQueueNodeLvNext.getAndNullValue();
        d(linkedQueueNodeLvNext);
        return andNullValue2;
    }
}
