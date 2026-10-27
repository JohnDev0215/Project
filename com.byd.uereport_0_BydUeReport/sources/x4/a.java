package x4;

import android.content.Context;
import android.util.TypedValue;

/* JADX INFO: compiled from: ConvertUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f9081a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f9082b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static int a(Context context, float f8) {
        return (int) TypedValue.applyDimension(1, f8, context.getResources().getDisplayMetrics());
    }

    public static String b(String str) {
        if (str == null || str.length() < 5) {
            return str;
        }
        return "*****" + str.substring(5);
    }

    public static int c(String str, int i8) {
        if (str == null) {
            return i8;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception unused) {
            return i8;
        }
    }

    public static long d(String str) {
        Long l8 = new Long(0L);
        try {
            l8 = Long.valueOf(str);
        } catch (Exception unused) {
        }
        return l8.longValue();
    }

    public static long e(String str, long j8) {
        Long l8 = new Long(j8);
        try {
            l8 = Long.valueOf(str);
        } catch (Exception unused) {
        }
        return l8.longValue();
    }
}
