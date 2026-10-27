package java9.util;

import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public class LongSummaryStatistics implements LongConsumer, IntConsumer {
    private long count;
    private long max;
    private long min;
    private long sum;

    public LongSummaryStatistics() {
        this.min = LongCompanionObject.MAX_VALUE;
        this.max = Long.MIN_VALUE;
    }

    public LongSummaryStatistics(long j, long j2, long j3, long j4) throws IllegalArgumentException {
        this.min = LongCompanionObject.MAX_VALUE;
        this.max = Long.MIN_VALUE;
        if (j < 0) {
            throw new IllegalArgumentException("Negative count value");
        }
        if (j > 0) {
            if (j2 > j3) {
                throw new IllegalArgumentException("Minimum greater than maximum");
            }
            this.count = j;
            this.sum = j4;
            this.min = j2;
            this.max = j3;
        }
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        accept(i);
    }

    @Override // java9.util.function.LongConsumer
    public void accept(long j) {
        this.count++;
        this.sum += j;
        this.min = Math.min(this.min, j);
        this.max = Math.max(this.max, j);
    }

    public void combine(LongSummaryStatistics longSummaryStatistics) {
        this.count += longSummaryStatistics.count;
        this.sum += longSummaryStatistics.sum;
        this.min = Math.min(this.min, longSummaryStatistics.min);
        this.max = Math.max(this.max, longSummaryStatistics.max);
    }

    public final long getCount() {
        return this.count;
    }

    public final long getSum() {
        return this.sum;
    }

    public final long getMin() {
        return this.min;
    }

    public final long getMax() {
        return this.max;
    }

    public final double getAverage() {
        if (getCount() > 0) {
            return getSum() / getCount();
        }
        return 0.0d;
    }

    public String toString() {
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", getClass().getSimpleName(), Long.valueOf(getCount()), Long.valueOf(getSum()), Long.valueOf(getMin()), Double.valueOf(getAverage()), Long.valueOf(getMax()));
    }
}
