package c.a.d.s;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: AndroidAESUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(byte[] bArr) {
        return h.a.a.a.a.a.q(bArr);
    }

    public static String b(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, c(str2));
            return a(cipher.doFinal(str.getBytes("utf-8")));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static SecretKeySpec c(String str) {
        try {
            return new SecretKeySpec(str.getBytes("utf-8"), "AES");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
