package spi;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import spi.es;
import spi.ta;

/* JADX INFO: loaded from: classes.dex */
public final class wn {
    public final gh<ig, String> a = new gh<>(1000);
    public final Pools.Pool<b> b = (ta.c) ta.a(10, new a(this));

    public class a implements ta.b<b> {
        public a(wn wnVar) {
        }

        @Override // spi.ta.b
        public final b a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static final class b implements ta.d {
        public final MessageDigest a;
        public final es.b b = new es.b();

        public b(MessageDigest messageDigest) {
            this.a = messageDigest;
        }

        @Override // spi.ta.d
        @NonNull
        public final es b() {
            return this.b;
        }
    }

    public final String a(ig igVar) {
        String strF;
        synchronized (this.a) {
            strF = this.a.f(igVar);
        }
        if (strF == null) {
            b bVarAcquire = this.b.acquire();
            Objects.requireNonNull(bVarAcquire, "Argument must not be null");
            b bVar = bVarAcquire;
            try {
                igVar.a(bVar.a);
                byte[] bArrDigest = bVar.a.digest();
                char[] cArr = tu.b;
                synchronized (cArr) {
                    for (int i = 0; i < bArrDigest.length; i++) {
                        int i2 = bArrDigest[i] & 255;
                        int i3 = i * 2;
                        char[] cArr2 = tu.a;
                        cArr[i3] = cArr2[i2 >>> 4];
                        cArr[i3 + 1] = cArr2[i2 & 15];
                    }
                    strF = new String(cArr);
                }
            } finally {
                this.b.release(bVar);
            }
        }
        synchronized (this.a) {
            this.a.i(igVar, strF);
        }
        return strF;
    }
}
