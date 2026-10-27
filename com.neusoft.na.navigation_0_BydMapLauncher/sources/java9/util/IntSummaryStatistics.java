package java9.util;

import java9.util.function.IntConsumer;

/* JADX INFO: loaded from: classes3.dex */
public class IntSummaryStatistics implements IntConsumer {
    private long count;
    private int max;
    private int min;
    private long sum;

    public IntSummaryStatistics() {
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
    }

    public IntSummaryStatistics(long j, int i, int i2, long j2) throws IllegalArgumentException {
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
        if (j < 0) {
            throw new IllegalArgumentException("Negative count value");
        }
        if (j > 0) {
            if (i > i2) {
                throw new IllegalArgumentException("Minimum greater than maximum");
            }
            this.count = j;
            this.sum = j2;
            this.min = i;
            this.max = i2;
        }
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        this.count++;
        this.sum += (long) i;
        this.min = Math.min(this.min, i);
        this.max = Math.max(this.max, i);
    }

    public void combine(IntSummaryStatistics intSummaryStatistics) {
        this.count += intSummaryStatistics.count;
        this.sum += intSummaryStatistics.sum;
        this.min = Math.min(this.min, intSummaryStatistics.min);
        this.max = Math.max(this.max, intSummaryStatistics.max);
    }

    public final long getCount() {
        return this.count;
    }

    public final long getSum() {
        return this.sum;
    }

    public final int getMin() {
        return this.min;
    }

    public final int getMax() {
        return this.max;
    }

    public final double getAverage() {
        if (getCount() > 0) {
            return getSum() / getCount();
        }
        return 0.0d;
    }

    public String toString() {
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", getClass().getSimpleName(), Long.valueOf(getCount()), Long.valueOf(getSum()), Integer.valueOf(getMin()), Double.valueOf(getAverage()), Integer.valueOf(getMax()));
    }
}
