package com.qtxa.qtxadefend;

import android.app.ActivityThread;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f122b;

    g() {
    }

    void a() {
        if (TextUtils.isEmpty(f121a) || TextUtils.isEmpty(f122b)) {
            try {
                byte[] bArr = new byte[1024];
                String[] strArrSplit = new String(bArr, 0, ActivityThread.currentApplication().getResources().getAssets().open("file").read(bArr)).split("UUUUU");
                f121a = new String(Base64.decode(strArrSplit[0], 0));
                f122b = new String(Base64.decode(strArrSplit[1], 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    String b(InputStream inputStream, boolean z) throws IOException {
        InputStream cipherInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            a();
            if (z) {
                Cipher cipher = Cipher.getInstance(f121a);
                cipher.init(2, new SecretKeySpec(f122b.getBytes(), f121a));
                cipherInputStream = new CipherInputStream(inputStream, cipher);
            } else {
                cipherInputStream = inputStream;
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[512];
                while (true) {
                    int i = cipherInputStream.read(bArr);
                    if (i == -1) {
                        String string = byteArrayOutputStream2.toString();
                        cipherInputStream.close();
                        byteArrayOutputStream2.close();
                        return string;
                    }
                    byteArrayOutputStream2.write(bArr, 0, i);
                }
            } catch (Throwable unused) {
                byteArrayOutputStream = byteArrayOutputStream2;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw new IOException("error json file");
            }
        } catch (Throwable unused2) {
        }
    }

    public void c(OutputStream outputStream, String str, boolean z) {
        OutputStream cipherOutputStream;
        try {
            try {
                try {
                    a();
                    if (z) {
                        Cipher cipher = Cipher.getInstance(f121a);
                        cipher.init(1, new SecretKeySpec(f122b.getBytes(), f121a));
                        cipherOutputStream = new CipherOutputStream(outputStream, cipher);
                    } else {
                        cipherOutputStream = outputStream;
                    }
                    cipherOutputStream.write(str.getBytes());
                    cipherOutputStream.close();
                } catch (InvalidKeyException unused) {
                    outputStream.close();
                } catch (NoSuchPaddingException unused2) {
                    outputStream.close();
                }
            } catch (IOException unused3) {
                outputStream.close();
            } catch (NoSuchAlgorithmException unused4) {
                outputStream.close();
            }
        } catch (IOException unused5) {
        }
    }
}
