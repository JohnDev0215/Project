package spi;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class gi {
    public static String a(String str) {
        byte[] bArrDigest;
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(bytes);
            bArrDigest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            ct.b("Md5Utility encrypt error msg:%s", e.getMessage());
            bArrDigest = new byte[0];
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int length = bArrDigest.length;
        char[] cArr2 = new char[length * 2];
        for (int i = 0; i < length; i++) {
            byte b = bArrDigest[i];
            int i2 = i * 2;
            cArr2[i2] = cArr[(b >>> 4) & 15];
            cArr2[i2 + 1] = cArr[b & 15];
        }
        return new String(cArr2);
    }
}
