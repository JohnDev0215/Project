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

    public static <V> V getLast(V[] vArr, V v7, V v8, boolean z7) {
        if (isEmpty(vArr)) {
            return v8;
        }
        int i8 = 0;
        while (true) {
            if (i8 >= vArr.length) {
                i8 = -1;
                break;
            }
            if (ObjectUtils.isEquals(v7, vArr[i8])) {
                break;
            }
            i8++;
        }
        return i8 == -1 ? v8 : i8 == 0 ? z7 ? vArr[vArr.length - 1] : v8 : vArr[i8 - 1];
    }

    public static <V> V getNext(V[] vArr, V v7, V v8, boolean z7) {
        if (isEmpty(vArr)) {
            return v8;
        }
        int i8 = 0;
        while (true) {
            if (i8 >= vArr.length) {
                i8 = -1;
                break;
            }
            if (ObjectUtils.isEquals(v7, vArr[i8])) {
                break;
            }
            i8++;
        }
        return i8 == -1 ? v8 : i8 == vArr.length + (-1) ? z7 ? vArr[0] : v8 : vArr[i8 + 1];
    }

    public static <V> boolean isEmpty(V[] vArr) {
        return vArr == null || vArr.length == 0;
    }

    public static <V> V getLast(V[] vArr, V v7, boolean z7) {
        return (V) getLast(vArr, v7, (Object) null, z7);
    }

    public static long getLast(long[] jArr, long j8, long j9, boolean z7) {
        if (jArr.length != 0) {
            return ((Long) getLast(ObjectUtils.transformLongArray(jArr), Long.valueOf(j8), Long.valueOf(j9), z7)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static <V> V getNext(V[] vArr, V v7, boolean z7) {
        return (V) getNext(vArr, v7, (Object) null, z7);
    }

    public static int getNext(int[] iArr, int i8, int i9, boolean z7) {
        if (iArr.length != 0) {
            return ((Integer) getNext(ObjectUtils.transformIntArray(iArr), Integer.valueOf(i8), Integer.valueOf(i9), z7)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static int getLast(int[] iArr, int i8, int i9, boolean z7) {
        if (iArr.length != 0) {
            return ((Integer) getLast(ObjectUtils.transformIntArray(iArr), Integer.valueOf(i8), Integer.valueOf(i9), z7)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static long getNext(long[] jArr, long j8, long j9, boolean z7) {
        if (jArr.length != 0) {
            return ((Long) getNext(ObjectUtils.transformLongArray(jArr), Long.valueOf(j8), Long.valueOf(j9), z7)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }
}
