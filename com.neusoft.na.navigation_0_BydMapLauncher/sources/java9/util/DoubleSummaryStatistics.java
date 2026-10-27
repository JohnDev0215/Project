package java9.util;

import java9.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes3.dex */
public class DoubleSummaryStatistics implements DoubleConsumer {
    private long count;
    private double max;
    private double min;
    private double simpleSum;
    private double sum;
    private double sumCompensation;

    public DoubleSummaryStatistics() {
        this.min = Double.POSITIVE_INFINITY;
        this.max = Double.NEGATIVE_INFINITY;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [boolean, int] */
    public DoubleSummaryStatistics(long j, double d, double d2, double d3) throws IllegalArgumentException {
        this.min = Double.POSITIVE_INFINITY;
        this.max = Double.NEGATIVE_INFINITY;
        if (j < 0) {
            throw new IllegalArgumentException("Negative count value");
        }
        if (j > 0) {
            if (d > d2) {
                throw new IllegalArgumentException("Minimum greater than maximum");
            }
            int iIsNaN = Double.isNaN(d);
            int i = Double.isNaN(d2) ? iIsNaN + 1 : iIsNaN;
            int i2 = Double.isNaN(d3) ? i + 1 : i;
            if (i2 > 0 && i2 < 3) {
                throw new IllegalArgumentException("Some, not all, of the minimum, maximum, or sum is NaN");
            }
            this.count = j;
            this.sum = d3;
            this.simpleSum = d3;
            this.sumCompensation = 0.0d;
            this.min = d;
            this.max = d2;
        }
    }

    @Override // java9.util.function.DoubleConsumer
    public void accept(double d) {
        this.count++;
        this.simpleSum += d;
        sumWithCompensation(d);
        this.min = Math.min(this.min, d);
        this.max = Math.max(this.max, d);
    }

    public void combine(DoubleSummaryStatistics doubleSummaryStatistics) {
        this.count += doubleSummaryStatistics.count;
        this.simpleSum += doubleSummaryStatistics.simpleSum;
        sumWithCompensation(doubleSummaryStatistics.sum);
        sumWithCompensation(-doubleSummaryStatistics.sumCompensation);
        this.min = Math.min(this.min, doubleSummaryStatistics.min);
        this.max = Math.max(this.max, doubleSummaryStatistics.max);
    }

    private void sumWithCompensation(double d) {
        double d2 = d - this.sumCompensation;
        double d3 = this.sum;
        double d4 = d3 + d2;
        this.sumCompensation = (d4 - d3) - d2;
        this.sum = d4;
    }

    public final long getCount() {
        return this.count;
    }

    public final double getSum() {
        double d = this.sum - this.sumCompensation;
        return (Double.isNaN(d) && Double.isInfinite(this.simpleSum)) ? this.simpleSum : d;
    }

    public final double getMin() {
        return this.min;
    }

    public final double getMax() {
        return this.max;
    }

    public final double getAverage() {
        if (getCount() > 0) {
            return getSum() / getCount();
        }
        return 0.0d;
    }

    public String toString() {
        return String.format("%s{count=%d, sum=%f, min=%f, average=%f, max=%f}", getClass().getSimpleName(), Long.valueOf(getCount()), Double.valueOf(getSum()), Double.valueOf(getMin()), Double.valueOf(getAverage()), Double.valueOf(getMax()));
    }
}
