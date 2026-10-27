package com.bydux.api.common.tools.util;

/* JADX INFO: loaded from: classes.dex */
public class ObjectUtils {
    private ObjectUtils() {
        throw new AssertionError();
    }

    public static <V> int compare(V v3, V v4) {
        if (v3 == null) {
            return v4 == null ? 0 : -1;
        }
        if (v4 == null) {
            return 1;
        }
        return ((Comparable) v3).compareTo(v4);
    }

    public static boolean isEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null ? obj.equals(obj2) : obj2 == null);
    }

    public static String nullStrToEmpty(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj.toString();
    }

    public static Integer[] transformIntArray(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            numArr[i3] = Integer.valueOf(iArr[i3]);
        }
        return numArr;
    }

    public static Long[] transformLongArray(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i3 = 0; i3 < jArr.length; i3++) {
            lArr[i3] = Long.valueOf(jArr[i3]);
        }
        return lArr;
    }

    public static int[] transformIntArray(Integer[] numArr) {
        int[] iArr = new int[numArr.length];
        for (int i3 = 0; i3 < numArr.length; i3++) {
            iArr[i3] = numArr[i3].intValue();
        }
        return iArr;
    }

    public static long[] transformLongArray(Long[] lArr) {
        long[] jArr = new long[lArr.length];
        for (int i3 = 0; i3 < lArr.length; i3++) {
            jArr[i3] = lArr[i3].longValue();
        }
        return jArr;
    }
}
