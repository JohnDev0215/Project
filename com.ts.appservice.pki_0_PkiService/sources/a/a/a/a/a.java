package a.a.a.a;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(String str, String str2, String str3) {
        return h.a("5d21b0b95049e9ac45baf24cc91d269d9cec4d355d186efb6f45b20f2e2f7af0", Hex.toHexString(d(str, g.c(str2, "privatekeyalias", str3))), Boolean.TRUE);
    }

    public static String b(byte[] bArr) {
        return h.a("5d21b0b95049e9ac45baf24cc91d269d9cec4d355d186efb6f45b20f2e2f7af0", Hex.toHexString(bArr), Boolean.TRUE);
    }

    public static boolean c(byte[] bArr, String str, byte[] bArr2, String str2) {
        try {
            g.e(bArr2, "cert", str2).verify(g.e(bArr, "cert", str).getPublicKey());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] d(String str, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            if (privateKey == null) {
                return null;
            }
            cipher.init(2, privateKey);
            return cipher.doFinal(Base64.decode(str));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (BadPaddingException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static byte[] e(String str, byte[] bArr, String str2) {
        return d(str, g.h(bArr, "privatekeyalias", str2));
    }
}
