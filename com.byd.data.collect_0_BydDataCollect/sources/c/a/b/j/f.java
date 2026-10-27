package c.a.b.j;

import java.io.Serializable;
import java.security.spec.AlgorithmParameterSpec;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;

/* JADX INFO: compiled from: SymmetricCrypto.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements h, g, Serializable {
    private static final long serialVersionUID = 1;
    private c.a.b.a cipherWrapper;
    private boolean isZeroPadding;
    private final Lock lock;
    private SecretKey secretKey;

    public f(e eVar) {
        this(eVar, (byte[]) null);
    }

    private Cipher j(int i2) {
        return this.cipherWrapper.b(i2, this.secretKey).a();
    }

    private f k(String str, AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec == null) {
            byte[] bArrB = (byte[]) c.a.a.d.f.e(this.cipherWrapper).d(new Function() { // from class: c.a.b.j.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((c.a.b.a) obj).a();
                }
            }).d(new Function() { // from class: c.a.b.j.b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Cipher) obj).getIV();
                }
            }).b();
            if (c.a.a.f.b.p(str, "PBE")) {
                if (bArrB == null) {
                    bArrB = c.a.a.g.h.b(8);
                }
                algorithmParameterSpec = new PBEParameterSpec(bArrB, 100);
            } else if (c.a.a.f.b.p(str, "AES") && bArrB != null) {
                algorithmParameterSpec = new IvParameterSpec(bArrB);
            }
        }
        return q(algorithmParameterSpec);
    }

    private byte[] m(byte[] bArr, int i2) {
        int length;
        int length2;
        return (!this.isZeroPadding || (length2 = (length = bArr.length) % i2) <= 0) ? bArr : c.a.a.g.g.c(bArr, (length + i2) - length2);
    }

    private byte[] n(byte[] bArr, int i2) {
        if (!this.isZeroPadding || i2 <= 0) {
            return bArr;
        }
        int length = bArr.length;
        if (length % i2 != 0) {
            return bArr;
        }
        int i3 = length - 1;
        while (i3 >= 0 && bArr[i3] == 0) {
            i3--;
        }
        return c.a.a.g.g.c(bArr, i3 + 1);
    }

    @Override // c.a.b.j.h
    public byte[] d(byte[] bArr) {
        this.lock.lock();
        try {
            try {
                Cipher cipherJ = j(1);
                return cipherJ.doFinal(m(bArr, cipherJ.getBlockSize()));
            } catch (Exception e2) {
                throw new c.a.b.b(e2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // c.a.b.j.g
    public byte[] g(byte[] bArr) {
        this.lock.lock();
        try {
            try {
                Cipher cipherJ = j(2);
                int blockSize = cipherJ.getBlockSize();
                byte[] bArrDoFinal = cipherJ.doFinal(bArr);
                this.lock.unlock();
                return n(bArrDoFinal, blockSize);
            } catch (Exception e2) {
                throw new c.a.b.b(e2);
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public f i(String str, SecretKey secretKey) {
        c.a.a.d.d.d(str, "'algorithm' must be not blank !", new Object[0]);
        this.secretKey = secretKey;
        c.a.b.f fVar = c.a.b.f.ZeroPadding;
        if (str.contains(fVar.name())) {
            str = c.a.a.f.b.m(str, fVar.name(), c.a.b.f.NoPadding.name());
            this.isZeroPadding = true;
        }
        this.cipherWrapper = new c.a.b.a(str);
        return this;
    }

    public f o(IvParameterSpec ivParameterSpec) {
        return q(ivParameterSpec);
    }

    public f p(byte[] bArr) {
        return o(new IvParameterSpec(bArr));
    }

    public f q(AlgorithmParameterSpec algorithmParameterSpec) {
        this.cipherWrapper.c(algorithmParameterSpec);
        return this;
    }

    public f(e eVar, byte[] bArr) {
        this(eVar.a(), bArr);
    }

    public f(String str, byte[] bArr) {
        this(str, c.a.b.d.f(str, bArr));
    }

    public f(String str, SecretKey secretKey) {
        this(str, secretKey, null);
    }

    public f(String str, SecretKey secretKey, AlgorithmParameterSpec algorithmParameterSpec) {
        this.lock = new ReentrantLock();
        i(str, secretKey);
        k(str, algorithmParameterSpec);
    }
}
