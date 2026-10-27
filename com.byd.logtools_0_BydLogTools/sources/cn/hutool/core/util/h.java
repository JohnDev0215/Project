package cn.hutool.core.util;

import java.util.concurrent.ThreadLocalRandom;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RandomUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static ThreadLocalRandom a() {
        return ThreadLocalRandom.current();
    }

    public static byte[] b(int i) {
        byte[] bArr = new byte[i];
        a().nextBytes(bArr);
        return bArr;
    }

    public static int c(int i) {
        return a().nextInt(i);
    }

    public static String d(int i) {
        return e("abcdefghijklmnopqrstuvwxyz0123456789", i);
    }

    public static String e(String str, int i) {
        if (c.a.a.d.b.j(str)) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder(i);
        if (i < 1) {
            i = 1;
        }
        int length = str.length();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(str.charAt(c(length)));
        }
        return sb.toString();
    }
}
