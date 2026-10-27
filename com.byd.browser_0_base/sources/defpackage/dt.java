package defpackage;

import android.util.Log;
import defpackage.ct;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.Callable;
import javax.crypto.KeyGenerator;

/* JADX INFO: compiled from: CipherFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class dt implements Callable<ct.b> {
    public final /* synthetic */ ct a;

    public dt(ct ctVar) {
        this.a = ctVar;
    }

    @Override // java.util.concurrent.Callable
    public final ct.b call() throws Throwable {
        Throwable th;
        FileInputStream fileInputStream;
        try {
            this.a.d.getClass();
            try {
                fileInputStream = new FileInputStream("/dev/urandom");
                try {
                    byte[] bArr = new byte[16];
                    if (16 != fileInputStream.read(bArr)) {
                        throw new GeneralSecurityException("Not enough random data available");
                    }
                    fileInputStream.close();
                    try {
                        SecureRandom secureRandom = new SecureRandom();
                        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                        keyGenerator.init(128, secureRandom);
                        return new ct.b(keyGenerator.generateKey(), bArr);
                    } catch (GeneralSecurityException unused) {
                        Log.e("cr_CipherFactory", "Couldn't get generator instances.");
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        } catch (IOException unused2) {
            Log.e("cr_CipherFactory", "Couldn't get generator data.");
            return null;
        } catch (GeneralSecurityException unused3) {
            Log.e("cr_CipherFactory", "Couldn't get generator data.");
            return null;
        }
    }
}
