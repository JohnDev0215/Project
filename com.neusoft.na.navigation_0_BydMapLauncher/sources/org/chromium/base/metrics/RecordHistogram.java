package org.chromium.base.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.time.DurationKt;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class RecordHistogram {
    private static Map<String, Long> sCache = Collections.synchronizedMap(new HashMap());
    private static boolean sIsDisabledForTests = false;

    private static int clampToInt(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    private static native int nativeGetHistogramValueCountForTesting(String str, int i);

    private static native void nativeInitialize();

    private static native long nativeRecordBooleanHistogram(String str, long j, boolean z);

    private static native long nativeRecordCustomCountHistogram(String str, long j, int i, int i2, int i3, int i4);

    private static native long nativeRecordCustomTimesHistogramMilliseconds(String str, long j, int i, int i2, int i3, int i4);

    private static native long nativeRecordEnumeratedHistogram(String str, long j, int i, int i2);

    private static native long nativeRecordLinearCountHistogram(String str, long j, int i, int i2, int i3, int i4);

    private static native long nativeRecordSparseHistogram(String str, long j, int i);

    public static void disableForTests() {
        sIsDisabledForTests = true;
    }

    private static long getCachedHistogramKey(String str) {
        Long l = sCache.get(str);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public static void recordBooleanHistogram(String str, boolean z) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordBooleanHistogram = nativeRecordBooleanHistogram(str, cachedHistogramKey, z);
        if (jNativeRecordBooleanHistogram != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordBooleanHistogram));
        }
    }

    public static void recordEnumeratedHistogram(String str, int i, int i2) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordEnumeratedHistogram = nativeRecordEnumeratedHistogram(str, cachedHistogramKey, i, i2);
        if (jNativeRecordEnumeratedHistogram != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordEnumeratedHistogram));
        }
    }

    public static void recordCountHistogram(String str, int i) {
        recordCustomCountHistogram(str, i, 1, DurationKt.NANOS_IN_MILLIS, 50);
    }

    public static void recordCount100Histogram(String str, int i) {
        recordCustomCountHistogram(str, i, 1, 100, 50);
    }

    public static void recordCount1000Histogram(String str, int i) {
        recordCustomCountHistogram(str, i, 1, 1000, 50);
    }

    public static void recordCustomCountHistogram(String str, int i, int i2, int i3, int i4) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordCustomCountHistogram = nativeRecordCustomCountHistogram(str, cachedHistogramKey, i, i2, i3, i4);
        if (jNativeRecordCustomCountHistogram != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordCustomCountHistogram));
        }
    }

    public static void recordLinearCountHistogram(String str, int i, int i2, int i3, int i4) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordLinearCountHistogram = nativeRecordLinearCountHistogram(str, cachedHistogramKey, i, i2, i3, i4);
        if (jNativeRecordLinearCountHistogram != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordLinearCountHistogram));
        }
    }

    public static void recordPercentageHistogram(String str, int i) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordEnumeratedHistogram = nativeRecordEnumeratedHistogram(str, cachedHistogramKey, i, 101);
        if (jNativeRecordEnumeratedHistogram != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordEnumeratedHistogram));
        }
    }

    public static void recordSparseSlowlyHistogram(String str, int i) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordSparseHistogram = nativeRecordSparseHistogram(str, cachedHistogramKey, i);
        if (jNativeRecordSparseHistogram != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordSparseHistogram));
        }
    }

    public static void recordTimesHistogram(String str, long j, TimeUnit timeUnit) {
        recordCustomTimesHistogramMilliseconds(str, timeUnit.toMillis(j), 1L, TimeUnit.SECONDS.toMillis(10L), 50);
    }

    public static void recordMediumTimesHistogram(String str, long j, TimeUnit timeUnit) {
        recordCustomTimesHistogramMilliseconds(str, timeUnit.toMillis(j), 10L, TimeUnit.MINUTES.toMillis(3L), 50);
    }

    public static void recordLongTimesHistogram(String str, long j, TimeUnit timeUnit) {
        recordCustomTimesHistogramMilliseconds(str, timeUnit.toMillis(j), 1L, TimeUnit.HOURS.toMillis(1L), 50);
    }

    public static void recordCustomTimesHistogram(String str, long j, long j2, long j3, TimeUnit timeUnit, int i) {
        recordCustomTimesHistogramMilliseconds(str, timeUnit.toMillis(j), timeUnit.toMillis(j2), timeUnit.toMillis(j3), i);
    }

    private static void recordCustomTimesHistogramMilliseconds(String str, long j, long j2, long j3, int i) {
        if (sIsDisabledForTests) {
            return;
        }
        long cachedHistogramKey = getCachedHistogramKey(str);
        long jNativeRecordCustomTimesHistogramMilliseconds = nativeRecordCustomTimesHistogramMilliseconds(str, cachedHistogramKey, clampToInt(j), clampToInt(j2), clampToInt(j3), i);
        if (jNativeRecordCustomTimesHistogramMilliseconds != cachedHistogramKey) {
            sCache.put(str, Long.valueOf(jNativeRecordCustomTimesHistogramMilliseconds));
        }
    }

    public static int getHistogramValueCountForTesting(String str, int i) {
        return nativeGetHistogramValueCountForTesting(str, i);
    }

    public static void initialize() {
        if (sIsDisabledForTests) {
            return;
        }
        nativeInitialize();
    }
}
