package com.amazon.avod.media.framework.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes2.dex */
public class RollingMedian {
    private long numSamples;
    private final PriorityQueue<Double> mSmallerMaxHeap = new PriorityQueue<>(11, Collections.reverseOrder());
    private final PriorityQueue<Double> mLargerMinHeap = new PriorityQueue<>();
    private final Object mMutex = new Object();

    public void addSample(double d) {
        synchronized (this.mMutex) {
            if (this.mSmallerMaxHeap.isEmpty() || d < this.mSmallerMaxHeap.peek().doubleValue()) {
                this.mSmallerMaxHeap.offer(Double.valueOf(d));
            } else {
                this.mLargerMinHeap.offer(Double.valueOf(d));
            }
            if (this.mSmallerMaxHeap.size() < this.mLargerMinHeap.size()) {
                this.mSmallerMaxHeap.offer(this.mLargerMinHeap.poll());
            }
            if (this.mSmallerMaxHeap.size() > this.mLargerMinHeap.size() + 1) {
                this.mLargerMinHeap.offer(this.mSmallerMaxHeap.poll());
            }
            this.numSamples++;
        }
    }

    public double getMedian() {
        synchronized (this.mMutex) {
            if (this.mSmallerMaxHeap.size() == 0) {
                return 0.0d;
            }
            if (this.mSmallerMaxHeap.size() == this.mLargerMinHeap.size()) {
                return (this.mSmallerMaxHeap.peek().doubleValue() + this.mLargerMinHeap.peek().doubleValue()) / 2.0d;
            }
            return this.mSmallerMaxHeap.peek().doubleValue();
        }
    }

    public double getSum() {
        double dDoubleValue;
        synchronized (this.mMutex) {
            dDoubleValue = 0.0d;
            Iterator<Double> it = this.mSmallerMaxHeap.iterator();
            while (it.hasNext()) {
                dDoubleValue += it.next().doubleValue();
            }
            Iterator<Double> it2 = this.mLargerMinHeap.iterator();
            while (it2.hasNext()) {
                dDoubleValue += it2.next().doubleValue();
            }
        }
        return dDoubleValue;
    }
}
