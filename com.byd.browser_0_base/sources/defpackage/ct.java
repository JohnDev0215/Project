package defpackage;

import android.os.Bundle;
import android.util.Log;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: CipherFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class ct {
    public FutureTask<b> b;
    public b c;
    public final Object a = new Object();
    public final n3 d = new n3(0);

    /* JADX INFO: compiled from: CipherFactory.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ct.this.getClass();
        }
    }

    /* JADX INFO: compiled from: CipherFactory.java */
    public static class b {
        public final Key a;
        public final byte[] b;

        public b(SecretKey secretKey, byte[] bArr) {
            this.a = secretKey;
            this.b = bArr;
        }
    }

    /* JADX INFO: compiled from: CipherFactory.java */
    public static class c {
        public static final ct a = new ct();
    }

    public final Cipher a(int i) {
        b bVarB = b(true);
        if (bVarB != null) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(i, bVarB.a, new IvParameterSpec(bVarB.b));
                return cipher;
            } catch (GeneralSecurityException unused) {
            }
        }
        Log.e("cr_CipherFactory", "Error in creating cipher instance.");
        return null;
    }

    public final b b(boolean z) {
        synchronized (this.a) {
            if (this.c == null && z) {
                d();
                try {
                    b bVar = this.b.get();
                    if (this.c == null) {
                        this.c = bVar;
                        PostTask.c(rh3.a, new a());
                    }
                } catch (InterruptedException e) {
                    throw new uv(e);
                } catch (ExecutionException e2) {
                    throw new uv(e2);
                }
            }
        }
        return this.c;
    }

    public final boolean c(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        byte[] byteArray = bundle.getByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY");
        byte[] byteArray2 = bundle.getByteArray("org.chromium.content.browser.crypto.CipherFactory.IV");
        if (byteArray != null && byteArray2 != null) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(byteArray, "AES");
                synchronized (this.a) {
                    b bVar = this.c;
                    if (bVar == null) {
                        this.c = new b(secretKeySpec, byteArray2);
                        return true;
                    }
                    if (bVar.a.equals(secretKeySpec) && Arrays.equals(this.c.b, byteArray2)) {
                        return true;
                    }
                    Log.e("cr_CipherFactory", "Attempted to restore different cipher data.");
                }
            } catch (IllegalArgumentException unused) {
                Log.e("cr_CipherFactory", "Error in restoring the key from the bundle.");
            }
        }
        return false;
    }

    public final void d() {
        if (this.c == null && this.b == null) {
            FutureTask<b> futureTask = new FutureTask<>(new dt(this));
            this.b = futureTask;
            i9.e.execute(futureTask);
        }
    }
}
