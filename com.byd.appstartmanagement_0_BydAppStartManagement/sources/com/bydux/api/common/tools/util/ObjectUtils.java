package com.bydux.api.common.tools.util;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectUtils {
    private ObjectUtils() {
        throw new AssertionError();
    }

    public static <V> int compare(V v, V v2) {
        if (v == null) {
            return v2 == null ? 0 : -1;
        }
        if (v2 == null) {
            return 1;
        }
        return ((Comparable) v).compareTo(v2);
    }

    public static boolean isEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null ? obj.equals(obj2) : obj2 == null);
    }

    public static String nullStrToEmpty(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj.toString();
    }

    public static Integer[] transformIntArray(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    public static Long[] transformLongArray(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    public static int[] transformIntArray(Integer[] numArr) {
        int[] iArr = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public static long[] transformLongArray(Long[] lArr) {
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }
}
