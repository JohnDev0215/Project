package c.a.a.g;

import java.text.DecimalFormat;

/* JADX INFO: compiled from: ConvertUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f3416a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f3417b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(int i2, double d2) {
        return new DecimalFormat(i2 == 0 ? "0" : i2 == 1 ? "#.#" : "#.##").format(d2);
    }

    public static String b(String str) {
        if (str == null || str.length() < 5) {
            return str;
        }
        return "*****" + str.substring(5);
    }

    public static long c(String str, long j) {
        Long l = new Long(j);
        try {
            l = Long.valueOf(str);
        } catch (Exception unused) {
        }
        return l.longValue();
    }
}
