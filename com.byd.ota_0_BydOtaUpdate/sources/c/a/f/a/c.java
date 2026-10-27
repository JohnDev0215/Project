package c.a.f.a;

import c.a.b.d.d;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* JADX INFO: compiled from: SHA256Utils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static String a(String str) throws Throwable {
        String lowerCase;
        FileInputStream fileInputStream;
        byte[] bArr;
        MessageDigest messageDigest;
        ?? r0 = 0;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            bArr = new byte[2048];
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            d.p(fileInputStream2);
            lowerCase = "";
            r0 = fileInputStream2;
        } catch (Throwable th2) {
            th = th2;
            r0 = fileInputStream;
            d.p(r0);
            throw th;
        }
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 == -1) {
                break;
            }
            messageDigest.update(bArr, 0, i2);
            return lowerCase;
        }
        lowerCase = g.a.f.j.d.d(messageDigest.digest()).toLowerCase();
        d.p(fileInputStream);
        r0 = messageDigest;
        return lowerCase;
    }
}
