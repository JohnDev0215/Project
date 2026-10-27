package io.reactivex.internal.queue;

import d.a.n.b.d;
import io.reactivex.internal.util.c;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class SpscArrayQueue<E> extends AtomicReferenceArray<E> implements d<E> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Integer f4296b = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    private static final long serialVersionUID = -1296597691183856449L;
    final AtomicLong consumerIndex;
    final int lookAheadStep;
    final int mask;
    final AtomicLong producerIndex;
    long producerLookAhead;

    public SpscArrayQueue(int i) {
        super(c.a(i));
        this.mask = length() - 1;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
        this.lookAheadStep = Math.min(i / 4, f4296b.intValue());
    }

    int calcElementOffset(long j) {
        return ((int) j) & this.mask;
    }

    int calcElementOffset(long j, int i) {
        return ((int) j) & i;
    }

    @Override // d.a.n.b.e
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // d.a.n.b.e
    public boolean isEmpty() {
        return this.producerIndex.get() == this.consumerIndex.get();
    }

    E lvElement(int i) {
        return get(i);
    }

    @Override // d.a.n.b.e
    public boolean offer(E e2) {
        Objects.requireNonNull(e2, "Null is not a valid element");
        int i = this.mask;
        long j = this.producerIndex.get();
        int iCalcElementOffset = calcElementOffset(j, i);
        if (j >= this.producerLookAhead) {
            long j2 = ((long) this.lookAheadStep) + j;
            if (lvElement(calcElementOffset(j2, i)) == null) {
                this.producerLookAhead = j2;
            } else if (lvElement(iCalcElementOffset) != null) {
                return false;
            }
        }
        soElement(iCalcElementOffset, e2);
        soProducerIndex(j + 1);
        return true;
    }

    @Override // d.a.n.b.d, d.a.n.b.e
    public E poll() {
        long j = this.consumerIndex.get();
        int iCalcElementOffset = calcElementOffset(j);
        E eLvElement = lvElement(iCalcElementOffset);
        if (eLvElement == null) {
            return null;
        }
        soConsumerIndex(j + 1);
        soElement(iCalcElementOffset, null);
        return eLvElement;
    }

    void soConsumerIndex(long j) {
        this.consumerIndex.lazySet(j);
    }

    void soElement(int i, E e2) {
        lazySet(i, e2);
    }

    void soProducerIndex(long j) {
        this.producerIndex.lazySet(j);
    }

    public boolean offer(E e2, E e3) {
        return offer(e2) && offer(e3);
    }
}
