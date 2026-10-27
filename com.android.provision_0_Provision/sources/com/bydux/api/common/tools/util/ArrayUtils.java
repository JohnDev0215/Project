package com.bydux.api.common.tools.util;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ArrayUtils {
    private ArrayUtils() {
        throw new AssertionError();
    }

    public static <T> T[] concat(T[] tArr, T[] tArr2) {
        if (tArr == null) {
            return tArr2;
        }
        if (tArr2 == null) {
            return tArr;
        }
        T[] tArr3 = (T[]) Arrays.copyOf(tArr, tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }

    public static <V> boolean isEmpty(V[] sourceArray) {
        return sourceArray == null || sourceArray.length == 0;
    }

    public static <V> V getLast(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }
        int i = 0;
        while (true) {
            if (i >= sourceArray.length) {
                i = -1;
                break;
            }
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                break;
            }
            i++;
        }
        if (i == -1) {
            return defaultValue;
        }
        if (i == 0) {
            return isCircle ? sourceArray[sourceArray.length - 1] : defaultValue;
        }
        return sourceArray[i - 1];
    }

    public static <V> V getLast(V[] vArr, V v, boolean z) {
        return (V) getLast(vArr, v, (Object) null, z);
    }

    public static long getLast(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0");
        }
        return ((Long) getLast(ObjectUtils.transformLongArray(sourceArray), Long.valueOf(value), Long.valueOf(defaultValue), isCircle)).longValue();
    }

    public static int getLast(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0");
        }
        return ((Integer) getLast(ObjectUtils.transformIntArray(sourceArray), Integer.valueOf(value), Integer.valueOf(defaultValue), isCircle)).intValue();
    }

    public static <V> V getNext(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }
        int i = 0;
        while (true) {
            if (i >= sourceArray.length) {
                i = -1;
                break;
            }
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                break;
            }
            i++;
        }
        if (i == -1) {
            return defaultValue;
        }
        if (i == sourceArray.length - 1) {
            return isCircle ? sourceArray[0] : defaultValue;
        }
        return sourceArray[i + 1];
    }

    public static <V> V getNext(V[] vArr, V v, boolean z) {
        return (V) getNext(vArr, v, (Object) null, z);
    }

    public static int getNext(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0");
        }
        return ((Integer) getNext(ObjectUtils.transformIntArray(sourceArray), Integer.valueOf(value), Integer.valueOf(defaultValue), isCircle)).intValue();
    }

    public static long getNext(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0");
        }
        return ((Long) getNext(ObjectUtils.transformLongArray(sourceArray), Long.valueOf(value), Long.valueOf(defaultValue), isCircle)).longValue();
    }
}
