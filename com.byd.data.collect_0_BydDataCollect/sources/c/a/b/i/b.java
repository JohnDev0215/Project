package c.a.b.i;

import c.a.a.g.c;
import c.a.a.g.g;
import c.a.b.h;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/* JADX INFO: compiled from: Digester.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Serializable {
    private static final long serialVersionUID = 1;
    private MessageDigest digest;
    protected int digestCount;
    protected byte[] salt;
    protected int saltPosition;

    public b(a aVar) {
        this(aVar.a());
    }

    private byte[] e(byte[]... bArr) {
        for (byte[] bArr2 : bArr) {
            if (bArr2 != null) {
                this.digest.update(bArr2);
            }
        }
        return this.digest.digest();
    }

    private byte[] i(byte[] bArr) {
        int iMax = Math.max(1, this.digestCount);
        h();
        for (int i2 = 0; i2 < iMax - 1; i2++) {
            bArr = e(bArr);
            h();
        }
        return bArr;
    }

    public byte[] a(String str) {
        return b(str, c.f3076b);
    }

    public byte[] b(String str, Charset charset) {
        return d(c.a.a.f.b.a(str, charset));
    }

    public byte[] d(byte[] bArr) {
        byte[] bArrE;
        int i2 = this.saltPosition;
        if (i2 <= 0) {
            bArrE = e(this.salt, bArr);
        } else if (i2 >= bArr.length) {
            bArrE = e(bArr, this.salt);
        } else if (g.b(this.salt)) {
            this.digest.update(bArr, 0, this.saltPosition);
            this.digest.update(this.salt);
            MessageDigest messageDigest = this.digest;
            int i3 = this.saltPosition;
            messageDigest.update(bArr, i3, bArr.length - i3);
            bArrE = this.digest.digest();
        } else {
            bArrE = e(bArr);
        }
        return i(bArrE);
    }

    public b g(String str, Provider provider) {
        if (provider == null) {
            this.digest = h.b(str);
        } else {
            try {
                this.digest = MessageDigest.getInstance(str, provider);
            } catch (NoSuchAlgorithmException e2) {
                throw new c.a.b.b(e2);
            }
        }
        return this;
    }

    public b h() {
        this.digest.reset();
        return this;
    }

    public b(String str) {
        this(str, null);
    }

    public b(String str, Provider provider) {
        g(str, provider);
    }
}
