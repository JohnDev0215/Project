package c.a.a.g;

import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: compiled from: RandomUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static ThreadLocalRandom a() {
        return ThreadLocalRandom.current();
    }

    public static byte[] b(int i2) {
        byte[] bArr = new byte[i2];
        a().nextBytes(bArr);
        return bArr;
    }

    public static int c(int i2) {
        return a().nextInt(i2);
    }

    public static String d(int i2) {
        return e("abcdefghijklmnopqrstuvwxyz0123456789", i2);
    }

    public static String e(String str, int i2) {
        if (c.a.a.f.b.i(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i2);
        if (i2 < 1) {
            i2 = 1;
        }
        int length = str.length();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(str.charAt(c(length)));
        }
        return sb.toString();
    }
}
