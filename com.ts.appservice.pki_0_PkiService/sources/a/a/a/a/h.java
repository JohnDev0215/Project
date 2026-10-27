package a.a.a.a;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static String a(String str, String str2, Boolean bool) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            String hexString = Hex.toHexString(str.getBytes(StandardCharsets.UTF_8));
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("isOfficial=");
            sb.append(bool);
            sb.append(" HmacSHA256 message = ");
            sb.append(bool.booleanValue() ? str : hexString);
            printStream.println(sb.toString());
            if (!bool.booleanValue()) {
                str = hexString;
            }
            byte[] bArrDecode = Hex.decode(str);
            mac.init(new SecretKeySpec(Hex.decode(str2), "HmacSHA256"));
            return b(mac.doFinal(bArrDecode));
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ======" + e.getMessage());
            return "";
        }
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; bArr != null && i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toLowerCase();
    }

    public static byte[] c(String str, byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, str);
        Mac mac = Mac.getInstance(str);
        mac.init(secretKeySpec);
        return mac.doFinal(bArr2);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        return c("HmacSHA256", bArr, bArr2);
    }
}
