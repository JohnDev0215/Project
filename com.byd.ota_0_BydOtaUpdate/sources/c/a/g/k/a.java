package c.a.g.k;

import android.hardware.bydauto.pki.PkiManager;
import android.text.TextUtils;
import c.a.b.d.e;
import c.a.b.d.f;
import g.a.f.j.d;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: GcySecure.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2757a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PkiManager f2758b;

    /* JADX INFO: renamed from: c.a.g.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GcySecure.java */
    public class C0054a implements FilenameFilter {
        public C0054a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith("enc");
        }
    }

    /* JADX INFO: compiled from: GcySecure.java */
    public class b implements FilenameFilter {
        public b() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith("signature");
        }
    }

    public a(PkiManager pkiManager) {
        this.f2758b = pkiManager;
    }

    public void a(String str, File file, File file2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(d.a(str), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, secretKeySpec);
        FileInputStream fileInputStream = new FileInputStream(file);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(file2), cipher);
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 == -1) {
                fileInputStream.close();
                cipherOutputStream.close();
                return;
            }
            cipherOutputStream.write(bArr, 0, i2);
        }
    }

    public final PublicKey b(byte[] bArr) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr)));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(g.a.f.j.a.a(str)));
                }
                if (!line.contains("BEGIN ") && !line.contains("END ")) {
                    str = str + line;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean c(String str, String str2) {
        File file = new File(str);
        File file2 = file.listFiles(new C0054a())[0];
        String str3 = f2757a;
        e.c(str3, "verify packageFile path = " + file2.getAbsolutePath());
        if (!TextUtils.isEmpty(str2)) {
            try {
                File file3 = new File(c.a.b.d.d.r(file2.getAbsolutePath()));
                e.c(str3, "encryptFile = " + file2.getAbsolutePath() + ",decryptFile = " + file3.getAbsolutePath());
                a(str2, file2, file3);
                file2 = file3;
            } catch (Exception e2) {
                e2.printStackTrace();
                e.b(f2757a, "decryptFile is fail, msg = " + e2.getMessage() + ", e = " + e2.toString());
                return false;
            }
        }
        try {
            boolean zD = d(f.a(file2).getBytes(StandardCharsets.UTF_8), g.a.f.j.a.a(c.a.f.a.a.a(file.listFiles(new b())[0])), b(this.f2758b.getDevicePublicKey()));
            e.c(str3, "verifySignature result is " + zD);
            return zD;
        } catch (Exception e3) {
            e3.printStackTrace();
            e.b(f2757a, "verifySignature is fail, msg = " + e3.getMessage() + ", e = " + e3.toString());
            return false;
        }
    }

    public final boolean d(byte[] bArr, byte[] bArr2, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initVerify(publicKey);
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
