package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class HeuristicsGraph {
    private final double[] mXValues;
    private final double[] mYValues;

    public static HeuristicsGraph parseHeuristicsGraphFromString(@Nonnull String str) {
        Preconditions.checkNotNull(str, "input");
        try {
            double[][] dArr = (double[][]) JacksonCache.OBJECT_MAPPER.readValue(str, double[][].class);
            Preconditions.checkArgument(dArr.length > 0, "Incorrect format of input: %s", str);
            Preconditions.checkArgument(dArr[0].length == 2, "Incorrect format of input: %s", str);
            int length = dArr.length;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 2, length);
            for (int i = 0; i < length; i++) {
                double[] dArr3 = dArr2[0];
                double[] dArr4 = dArr[i];
                dArr3[i] = dArr4[0];
                dArr2[1][i] = dArr4[1];
            }
            return new HeuristicsGraph(dArr2[0], dArr2[1]);
        } catch (IOException e) {
            DLog.errorf("Failed to parse heuristics graph with exception: %s", e);
            throw new IllegalArgumentException(String.format(Locale.US, "Incorrect format of input: %s", str));
        }
    }

    public HeuristicsGraph(@Nonnull double[] dArr, @Nonnull double[] dArr2) {
        double[] dArr3 = (double[]) Preconditions.checkNotNull(dArr, "xValues");
        this.mXValues = dArr3;
        double[] dArr4 = (double[]) Preconditions.checkNotNull(dArr2, "yValues");
        this.mYValues = dArr4;
        Preconditions.checkArgument(dArr3.length == dArr4.length, "length of xValues must be equal to length of yValues");
    }

    @Nonnull
    public double[] getXValues() {
        return this.mXValues;
    }

    @Nonnull
    public double[] getYValues() {
        return this.mYValues;
    }

    public double project(double d) {
        int i;
        double d2;
        double d3;
        int length = this.mYValues.length - 1;
        int i2 = 0;
        do {
            i = i2;
            while (length > i2) {
                i = ((length - i2) / 2) + i2;
                double[] dArr = this.mXValues;
                d2 = dArr[i];
                if (d < d2) {
                    length = i;
                } else {
                    i2 = i + 1;
                    d3 = dArr[i2];
                }
            }
            return this.mYValues[i];
        } while (d >= d3);
        double[] dArr2 = this.mYValues;
        double d4 = dArr2[i2];
        double d5 = dArr2[i];
        return (((d - d2) / (d3 - d2)) * (d4 - d5)) + d5;
    }
}
