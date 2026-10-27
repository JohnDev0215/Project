package com.bydux.api.common.tools.util;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectUtils {
    private ObjectUtils() {
        throw new AssertionError();
    }

    public static <V> int compare(V v7, V v8) {
        if (v7 == null) {
            return v8 == null ? 0 : -1;
        }
        if (v8 == null) {
            return 1;
        }
        return ((Comparable) v7).compareTo(v8);
    }

    public static boolean isEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null ? obj.equals(obj2) : obj2 == null);
    }

    public static String nullStrToEmpty(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj.toString();
    }

    public static Integer[] transformIntArray(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i8 = 0; i8 < iArr.length; i8++) {
            numArr[i8] = Integer.valueOf(iArr[i8]);
        }
        return numArr;
    }

    public static Long[] transformLongArray(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i8 = 0; i8 < jArr.length; i8++) {
            lArr[i8] = Long.valueOf(jArr[i8]);
        }
        return lArr;
    }

    public static int[] transformIntArray(Integer[] numArr) {
        int[] iArr = new int[numArr.length];
        for (int i8 = 0; i8 < numArr.length; i8++) {
            iArr[i8] = numArr[i8].intValue();
        }
        return iArr;
    }

    public static long[] transformLongArray(Long[] lArr) {
        long[] jArr = new long[lArr.length];
        for (int i8 = 0; i8 < lArr.length; i8++) {
            jArr[i8] = lArr[i8].longValue();
        }
        return jArr;
    }
}
