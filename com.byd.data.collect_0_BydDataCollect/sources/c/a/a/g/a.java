package c.a.a.g;

import java.util.Arrays;

/* JADX INFO: compiled from: ArrayUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends g {
    public static Object d(Object obj, Object obj2, int i2) {
        System.arraycopy(obj, 0, obj2, 0, i2);
        return obj2;
    }

    public static boolean e(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public static <T> boolean f(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static String g(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        }
        if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        }
        if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        }
        if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        }
        if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        }
        if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        }
        if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        }
        if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        }
        if (e(obj)) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception unused) {
            }
        }
        return obj.toString();
    }
}
