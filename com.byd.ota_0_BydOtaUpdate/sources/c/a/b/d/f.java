package c.a.b.d;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* JADX INFO: compiled from: MD5Util.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        byte[] bArr;
        MessageDigest messageDigest;
        int i2;
        String string = "";
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bArr = new byte[2048];
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Exception e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            d.p(fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            d.p(fileInputStream2);
            throw th;
        }
        while (true) {
            int i3 = fileInputStream.read(bArr);
            if (i3 == -1) {
                break;
            }
            messageDigest.update(bArr, 0, i3);
            return string;
        }
        string = new BigInteger(1, messageDigest.digest()).toString(16);
        if (string.length() < 32) {
            StringBuilder sb = new StringBuilder();
            int length = 32 - string.length();
            for (i2 = 0; i2 < length; i2++) {
                sb.append("0");
            }
            sb.append(string);
            string = sb.toString();
        }
        d.p(fileInputStream);
        return string;
    }
}
