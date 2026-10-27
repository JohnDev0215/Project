package l7;

import A7.k;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: renamed from: l7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1782a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f18819a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f18820b;
    public static int c;
    public static String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f18821e;

    static {
        char[] charArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_".toCharArray();
        f18819a = charArray;
        int length = charArray.length;
        f18820b = length;
        c = 0;
        f18821e = new HashMap(length);
        for (int i7 = 0; i7 < f18820b; i7++) {
            f18821e.put(Character.valueOf(f18819a[i7]), Integer.valueOf(i7));
        }
    }

    public static String a(long j3) {
        StringBuilder sb = new StringBuilder();
        do {
            int i7 = f18820b;
            sb.insert(0, f18819a[(int) (j3 % ((long) i7))]);
            j3 /= (long) i7;
        } while (j3 > 0);
        return sb.toString();
    }

    public static String b() {
        String strA = a(new Date().getTime());
        if (!strA.equals(d)) {
            c = 0;
            d = strA;
            return strA;
        }
        StringBuilder sbL = k.l(strA, ".");
        int i7 = c;
        c = i7 + 1;
        sbL.append(a(i7));
        return sbL.toString();
    }
}
