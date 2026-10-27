package c.a.j.f;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: SHA256Utils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; bArr != null && i2 < bArr.length && i2 < 16; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toLowerCase();
    }

    public static String b(File file) throws NoSuchAlgorithmException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bArr = new byte[104857600];
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 == -1) {
                fileInputStream.close();
                bufferedInputStream.close();
                return g.a.f.j.d.d(messageDigest.digest()).toLowerCase();
            }
            messageDigest.update(bArr, 0, i2);
        }
    }

    public static String c(String str) throws NoSuchAlgorithmException, IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bArr = new byte[104857600];
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 == -1) {
                fileInputStream.close();
                bufferedInputStream.close();
                return g.a.f.j.d.d(messageDigest.digest()).toLowerCase();
            }
            messageDigest.update(bArr, 0, i2);
        }
    }

    public static String d(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return g.a.f.j.d.d(messageDigest.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String e(String str, String str2, Boolean bool) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            String strD = g.a.f.j.d.d(str.getBytes(StandardCharsets.UTF_8));
            System.out.println("isOfficial=" + bool);
            if (!bool.booleanValue()) {
                str = strD;
            }
            byte[] bArrA = g.a.f.j.d.a(str);
            mac.init(new SecretKeySpec(g.a.f.j.d.a(str2), "HmacSHA256"));
            return a(mac.doFinal(bArrA));
        } catch (Exception e2) {
            System.out.println("Error HmacSHA256 ======" + e2.getMessage());
            return "";
        }
    }
}
