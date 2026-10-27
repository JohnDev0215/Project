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

    public static <V> V getLast(V[] vArr, V v3, V v4, boolean z3) {
        if (isEmpty(vArr)) {
            return v4;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= vArr.length) {
                i3 = -1;
                break;
            }
            if (ObjectUtils.isEquals(v3, vArr[i3])) {
                break;
            }
            i3++;
        }
        return i3 == -1 ? v4 : i3 == 0 ? z3 ? vArr[vArr.length - 1] : v4 : vArr[i3 - 1];
    }

    public static <V> V getNext(V[] vArr, V v3, V v4, boolean z3) {
        if (isEmpty(vArr)) {
            return v4;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= vArr.length) {
                i3 = -1;
                break;
            }
            if (ObjectUtils.isEquals(v3, vArr[i3])) {
                break;
            }
            i3++;
        }
        return i3 == -1 ? v4 : i3 == vArr.length + (-1) ? z3 ? vArr[0] : v4 : vArr[i3 + 1];
    }

    public static <V> boolean isEmpty(V[] vArr) {
        return vArr == null || vArr.length == 0;
    }

    public static <V> V getLast(V[] vArr, V v3, boolean z3) {
        return (V) getLast(vArr, v3, (Object) null, z3);
    }

    public static long getLast(long[] jArr, long j3, long j4, boolean z3) {
        if (jArr.length != 0) {
            return ((Long) getLast(ObjectUtils.transformLongArray(jArr), Long.valueOf(j3), Long.valueOf(j4), z3)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static <V> V getNext(V[] vArr, V v3, boolean z3) {
        return (V) getNext(vArr, v3, (Object) null, z3);
    }

    public static int getNext(int[] iArr, int i3, int i4, boolean z3) {
        if (iArr.length != 0) {
            return ((Integer) getNext(ObjectUtils.transformIntArray(iArr), Integer.valueOf(i3), Integer.valueOf(i4), z3)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static int getLast(int[] iArr, int i3, int i4, boolean z3) {
        if (iArr.length != 0) {
            return ((Integer) getLast(ObjectUtils.transformIntArray(iArr), Integer.valueOf(i3), Integer.valueOf(i4), z3)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }

    public static long getNext(long[] jArr, long j3, long j4, boolean z3) {
        if (jArr.length != 0) {
            return ((Long) getNext(ObjectUtils.transformLongArray(jArr), Long.valueOf(j3), Long.valueOf(j4), z3)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0");
    }
}
