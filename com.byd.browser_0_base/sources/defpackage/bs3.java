package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: WebappAuthenticator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bs3 {
    public static final Object a = new Object();
    public static SecretKey b;

    public static SecretKey a() {
        synchronized (a) {
            SecretKey secretKey = b;
            if (secretKey != null) {
                return secretKey;
            }
            Context context = xz.a;
            SecretKeySpec secretKeySpecC = c(context);
            if (secretKeySpecC != null) {
                b = secretKeySpecC;
                return secretKeySpecC;
            }
            SecretKey secretKeyGenerateKey = b;
            if (secretKeyGenerateKey == null) {
                try {
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
                    keyGenerator.init(256, new SecureRandom());
                    secretKeyGenerateKey = keyGenerator.generateKey();
                } catch (NoSuchAlgorithmException e) {
                    throw new uv(e);
                }
            }
            b = secretKeyGenerateKey;
            if (d(context, secretKeyGenerateKey)) {
                return b;
            }
            b = null;
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] b(java.lang.String r4) {
        /*
            r0 = 0
            javax.crypto.SecretKey r1 = a()     // Catch: java.security.GeneralSecurityException -> L12
            if (r1 != 0) goto L8
            goto L1a
        L8:
            java.lang.String r2 = "HmacSHA256"
            javax.crypto.Mac r2 = javax.crypto.Mac.getInstance(r2)     // Catch: java.security.GeneralSecurityException -> L12
            r2.init(r1)     // Catch: java.security.GeneralSecurityException -> L12
            goto L1b
        L12:
            r1 = move-exception
            java.lang.String r2 = "WebappAuthenticator"
            java.lang.String r3 = "Error in creating MAC instance"
            android.util.Log.w(r2, r3, r1)
        L1a:
            r2 = r0
        L1b:
            if (r2 != 0) goto L1e
            return r0
        L1e:
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            byte[] r4 = r4.getBytes(r0)
            byte[] r4 = r2.doFinal(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bs3.b(java.lang.String):byte[]");
    }

    public static SecretKeySpec c(Context context) throws Throwable {
        FileInputStream fileInputStream;
        File fileStreamPath = context.getFileStreamPath("webapp-authenticator");
        FileInputStream fileInputStream2 = null;
        try {
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        if (fileStreamPath.length() != 32) {
            Log.w("WebappAuthenticator", "Could not read key from '" + fileStreamPath + "': invalid file contents");
            return null;
        }
        byte[] bArr = new byte[32];
        fileInputStream = new FileInputStream(fileStreamPath);
        try {
            try {
                if (32 != fileInputStream.read(bArr)) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        Log.e("WebappAuthenticator", "Could not close key input stream '" + fileStreamPath + "': " + e2);
                    }
                    return null;
                }
                try {
                    try {
                        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "HmacSHA256");
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            Log.e("WebappAuthenticator", "Could not close key input stream '" + fileStreamPath + "': " + e3);
                        }
                        return secretKeySpec;
                    } catch (IllegalArgumentException unused) {
                        fileInputStream.close();
                        return null;
                    }
                } catch (IOException e4) {
                    Log.e("WebappAuthenticator", "Could not close key input stream '" + fileStreamPath + "': " + e4);
                    return null;
                }
            } catch (Exception e5) {
                e = e5;
                Log.w("WebappAuthenticator", "Could not read key from '" + fileStreamPath + "': " + e);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e6) {
                        Log.e("WebappAuthenticator", "Could not close key input stream '" + fileStreamPath + "': " + e6);
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
        }
        th = th2;
        fileInputStream2 = fileInputStream;
        if (fileInputStream2 != null) {
            try {
                fileInputStream2.close();
            } catch (IOException e7) {
                Log.e("WebappAuthenticator", "Could not close key input stream '" + fileStreamPath + "': " + e7);
            }
        }
        throw th;
    }

    public static boolean d(Context context, SecretKey secretKey) throws Throwable {
        FileOutputStream fileOutputStream;
        File fileStreamPath = context.getFileStreamPath("webapp-authenticator");
        byte[] encoded = secretKey.getEncoded();
        if (32 != encoded.length) {
            C0173a.q(new StringBuilder("writeKeyToFile got key encoded bytes length "), encoded.length, "; expected 32", "WebappAuthenticator");
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(fileStreamPath);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            fileOutputStream.write(encoded);
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException e2) {
                Log.e("WebappAuthenticator", "Could not close key output stream '" + fileStreamPath + "': " + e2);
                return true;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            Log.e("WebappAuthenticator", "Could not write key to '" + fileStreamPath + "': " + e);
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    Log.e("WebappAuthenticator", "Could not close key output stream '" + fileStreamPath + "': " + e4);
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    Log.e("WebappAuthenticator", "Could not close key output stream '" + fileStreamPath + "': " + e5);
                }
            }
            throw th;
        }
    }
}
