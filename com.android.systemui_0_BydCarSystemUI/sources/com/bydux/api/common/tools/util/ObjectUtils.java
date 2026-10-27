package com.bydux.api.common.tools.util;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectUtils {
    private ObjectUtils() {
        throw new AssertionError();
    }

    public static boolean isEquals(Object actual, Object expected) {
        return actual == expected || (actual != null ? actual.equals(expected) : expected == null);
    }

    public static String nullStrToEmpty(Object str) {
        return str == null ? "" : str instanceof String ? (String) str : str.toString();
    }

    public static Long[] transformLongArray(long[] source) {
        Long[] lArr = new Long[source.length];
        for (int i = 0; i < source.length; i++) {
            lArr[i] = Long.valueOf(source[i]);
        }
        return lArr;
    }

    public static long[] transformLongArray(Long[] source) {
        long[] jArr = new long[source.length];
        for (int i = 0; i < source.length; i++) {
            jArr[i] = source[i].longValue();
        }
        return jArr;
    }

    public static Integer[] transformIntArray(int[] source) {
        Integer[] numArr = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            numArr[i] = Integer.valueOf(source[i]);
        }
        return numArr;
    }

    public static int[] transformIntArray(Integer[] source) {
        int[] iArr = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            iArr[i] = source[i].intValue();
        }
        return iArr;
    }

    public static <V> int compare(V v1, V v2) {
        if (v1 == null) {
            return v2 == null ? 0 : -1;
        }
        if (v2 == null) {
            return 1;
        }
        return ((Comparable) v1).compareTo(v2);
    }
}
