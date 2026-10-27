package com.amazon.scrublib.utils;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: DoubleBuffer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0019\u0010\u001aB\u0011\b\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001cJ\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007J\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005H\u0086\u0002J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0096\u0002R\u0016\u0010\u000f\u001a\u00020\u00078\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/amazon/scrublib/utils/DoubleBuffer;", "", "", "", "reset", "", "len", "", "popNum", "doubleArray", "addAll", "index", "get", "", "iterator", "arr", "[D", "Ljava/util/concurrent/atomic/AtomicInteger;", "_size", "Ljava/util/concurrent/atomic/AtomicInteger;", "tail", "head", "getSize", "()I", "size", "<init>", "()V", "bufferSize", "(I)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class DoubleBuffer implements Iterable<Double>, KMappedMarker {
    private AtomicInteger _size;
    private double[] arr;
    private AtomicInteger head;
    private AtomicInteger tail;

    public DoubleBuffer() {
        this._size = new AtomicInteger(0);
        this.tail = new AtomicInteger(0);
        this.head = new AtomicInteger(0);
    }

    public DoubleBuffer(int i) {
        this();
        this.arr = new double[i];
    }

    public final int getSize() {
        return this._size.get();
    }

    public final void reset() {
        this.head.set(this.tail.get());
        this._size.set(0);
    }

    public final double[] popNum(int len) {
        double[] dArr;
        double[] dArr2 = new double[len];
        if (len > getSize()) {
            return dArr2;
        }
        int i = this.head.get();
        int i2 = 0;
        while (true) {
            dArr = null;
            if (i2 >= len) {
                break;
            }
            double[] dArr3 = this.arr;
            if (dArr3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arr");
                dArr3 = null;
            }
            int i3 = i2 + i;
            double[] dArr4 = this.arr;
            if (dArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arr");
            } else {
                dArr = dArr4;
            }
            dArr2[i2] = dArr3[i3 % dArr.length];
            i2++;
        }
        AtomicInteger atomicInteger = this.head;
        int i4 = atomicInteger.get() + len;
        double[] dArr5 = this.arr;
        if (dArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arr");
        } else {
            dArr = dArr5;
        }
        atomicInteger.set(i4 % dArr.length);
        AtomicInteger atomicInteger2 = this._size;
        atomicInteger2.set(atomicInteger2.get() - len);
        return dArr2;
    }

    public final void addAll(double[] doubleArray) {
        Intrinsics.checkNotNullParameter(doubleArray, "doubleArray");
        int length = doubleArray.length;
        int i = this.tail.get();
        int i2 = this._size.get() + length;
        double[] dArr = this.arr;
        if (dArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arr");
            dArr = null;
        }
        if (i2 >= dArr.length) {
            return;
        }
        AtomicInteger atomicInteger = this._size;
        int i3 = atomicInteger.get() + length;
        double[] dArr2 = this.arr;
        if (dArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arr");
            dArr2 = null;
        }
        atomicInteger.set(i3 % dArr2.length);
        AtomicInteger atomicInteger2 = this.tail;
        int i4 = atomicInteger2.get() + length;
        double[] dArr3 = this.arr;
        if (dArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arr");
            dArr3 = null;
        }
        atomicInteger2.set(i4 % dArr3.length);
        for (int i5 = 0; i5 < length; i5++) {
            double[] dArr4 = this.arr;
            if (dArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arr");
                dArr4 = null;
            }
            int i6 = i + i5;
            double[] dArr5 = this.arr;
            if (dArr5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arr");
                dArr5 = null;
            }
            dArr4[i6 % dArr5.length] = doubleArray[i5];
        }
    }

    public final double get(int index) {
        if (this._size.get() == 0 || index > this._size.get() || index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        double[] dArr = this.arr;
        double[] dArr2 = null;
        if (dArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arr");
            dArr = null;
        }
        int i = this.head.get() + index;
        double[] dArr3 = this.arr;
        if (dArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arr");
        } else {
            dArr2 = dArr3;
        }
        return dArr[i % dArr2.length];
    }

    /* JADX INFO: renamed from: com.amazon.scrublib.utils.DoubleBuffer$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: DoubleBuffer.kt */
    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u0005\u001a\u00020\u0006H\u0096\u0002J\u000e\u0010\u0007\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0002\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"com/amazon/scrublib/utils/DoubleBuffer$iterator$1", "", "", "index", "Ljava/util/concurrent/atomic/AtomicInteger;", "hasNext", "", "next", "()Ljava/lang/Double;", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Double>, KMappedMarker {
        private final AtomicInteger index = new AtomicInteger(0);

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index.get() < DoubleBuffer.this.getSize();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Double next() {
            return Double.valueOf(DoubleBuffer.this.get(this.index.getAndIncrement()));
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Double> iterator() {
        return new AnonymousClass1();
    }
}
