package c.a.d.s;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: DigestUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f3686a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] a(String str, byte[] bArr) {
        return e(str).digest(bArr);
    }

    public static char[] b(String str, byte[] bArr) {
        return d(a(str, bArr));
    }

    public static String c(String str, byte[] bArr) {
        return new String(b(str, bArr));
    }

    public static char[] d(byte[] bArr) {
        char[] cArr = new char[32];
        for (int i2 = 0; i2 < 32; i2 += 2) {
            byte b2 = bArr[i2 / 2];
            char[] cArr2 = f3686a;
            cArr[i2] = cArr2[(b2 >>> 4) & 15];
            cArr[i2 + 1] = cArr2[b2 & 15];
        }
        return cArr;
    }

    public static MessageDigest e(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + str + "\"", e2);
        }
    }

    public static String f(byte[] bArr) {
        return c("MD5", bArr);
    }
}
