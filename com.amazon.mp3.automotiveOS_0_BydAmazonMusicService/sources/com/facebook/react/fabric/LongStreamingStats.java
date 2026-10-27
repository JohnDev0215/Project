package com.facebook.react.fabric;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/* JADX INFO: compiled from: DevToolsReactPerfLogger.java */
/* JADX INFO: loaded from: classes6.dex */
class LongStreamingStats {
    private Queue<Long> minHeap = new PriorityQueue(Comparator.naturalOrder());
    private Queue<Long> maxHeap = new PriorityQueue(Comparator.reverseOrder());
    private double streamingAverage = 0.0d;
    private int len = 0;
    private long max = 0;

    LongStreamingStats() {
    }

    public void add(long j) {
        if (j != 0) {
            if (this.minHeap.size() == this.maxHeap.size()) {
                this.maxHeap.offer(Long.valueOf(j));
                this.minHeap.offer(this.maxHeap.poll());
            } else {
                this.minHeap.offer(Long.valueOf(j));
                this.maxHeap.offer(this.minHeap.poll());
            }
        }
        int i = this.len + 1;
        this.len = i;
        if (i == 1) {
            this.streamingAverage = j;
        } else {
            this.streamingAverage = (this.streamingAverage / ((double) (i / (i - 1)))) + (j / ((long) i));
        }
        long j2 = this.max;
        if (j <= j2) {
            j = j2;
        }
        this.max = j;
    }

    public double getMedian() {
        long jLongValue;
        if (this.minHeap.size() == 0 && this.maxHeap.size() == 0) {
            return 0.0d;
        }
        if (this.minHeap.size() > this.maxHeap.size()) {
            jLongValue = this.minHeap.peek().longValue();
        } else {
            jLongValue = (this.minHeap.peek().longValue() + this.maxHeap.peek().longValue()) / 2;
        }
        return jLongValue;
    }

    public double getAverage() {
        return this.streamingAverage;
    }

    public long getMax() {
        return this.max;
    }
}
