package com.bydux.api.common.tools.util;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
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

    public static <V> V getLast(V[] vArr, V v, V v2, boolean z) {
        if (isEmpty(vArr)) {
            return v2;
        }
        int i = 0;
        while (true) {
            if (i >= vArr.length) {
                i = -1;
                break;
            }
            if (ObjectUtils.isEquals(v, vArr[i])) {
                break;
            }
            i++;
        }
        return i == -1 ? v2 : i == 0 ? z ? vArr[vArr.length - 1] : v2 : vArr[i - 1];
    }

    public static <V> V getNext(V[] vArr, V v, V v2, boolean z) {
        if (isEmpty(vArr)) {
            return v2;
        }
        int i = 0;
        while (true) {
            if (i >= vArr.length) {
                i = -1;
                break;
            }
            if (ObjectUtils.isEquals(v, vArr[i])) {
                break;
            }
            i++;
        }
        return i == -1 ? v2 : i == vArr.length + (-1) ? z ? vArr[0] : v2 : vArr[i + 1];
    }

    public static <V> boolean isEmpty(V[] vArr) {
        return vArr == null || vArr.length == 0;
    }

    public static <V> V getLast(V[] vArr, V v, boolean z) {
        return (V) getLast(vArr, v, (Object) null, z);
    }

    public static long getLast(long[] jArr, long j, long j2, boolean z) {
        if (jArr.length != 0) {
            return ((Long) getLast(ObjectUtils.transformLongArray(jArr), Long.valueOf(j), Long.valueOf(j2), z)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static <V> V getNext(V[] vArr, V v, boolean z) {
        return (V) getNext(vArr, v, (Object) null, z);
    }

    public static int getNext(int[] iArr, int i, int i2, boolean z) {
        if (iArr.length != 0) {
            return ((Integer) getNext(ObjectUtils.transformIntArray(iArr), Integer.valueOf(i), Integer.valueOf(i2), z)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static int getLast(int[] iArr, int i, int i2, boolean z) {
        if (iArr.length != 0) {
            return ((Integer) getLast(ObjectUtils.transformIntArray(iArr), Integer.valueOf(i), Integer.valueOf(i2), z)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static long getNext(long[] jArr, long j, long j2, boolean z) {
        if (jArr.length != 0) {
            return ((Long) getNext(ObjectUtils.transformLongArray(jArr), Long.valueOf(j), Long.valueOf(j2), z)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }
}
