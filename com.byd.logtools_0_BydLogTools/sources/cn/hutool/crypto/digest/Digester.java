package cn.hutool.crypto.digest;

import c.a.a.d.b;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.e;
import cn.hutool.core.util.c;
import cn.hutool.core.util.g;
import cn.hutool.crypto.CryptoException;
import cn.hutool.crypto.d;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import okio.Segment;

/* JADX INFO: loaded from: classes.dex */
public class Digester implements Serializable {
    private static final long serialVersionUID = 1;
    private MessageDigest digest;
    protected int digestCount;
    protected byte[] salt;
    protected int saltPosition;

    public Digester(DigestAlgorithm digestAlgorithm) {
        this(digestAlgorithm.getValue());
    }

    private byte[] a(InputStream inputStream, int i) throws IOException {
        if (this.saltPosition <= 0) {
            this.digest.update(this.salt);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (true) {
            int i3 = inputStream.read(bArr, 0, i);
            if (i3 <= -1) {
                break;
            }
            i2 += i3;
            int i4 = this.saltPosition;
            if (i4 <= 0 || i2 < i4) {
                this.digest.update(bArr, 0, i3);
            } else {
                if (i2 != i4) {
                    this.digest.update(bArr, 0, i2 - i4);
                }
                this.digest.update(this.salt);
                this.digest.update(bArr, i2 - this.saltPosition, i3);
            }
        }
        if (i2 < this.saltPosition) {
            this.digest.update(this.salt);
        }
        return this.digest.digest();
    }

    private byte[] b(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        while (true) {
            int i2 = inputStream.read(bArr, 0, i);
            if (i2 <= -1) {
                return this.digest.digest();
            }
            this.digest.update(bArr, 0, i2);
        }
    }

    private byte[] c(byte[]... bArr) {
        for (byte[] bArr2 : bArr) {
            if (bArr2 != null) {
                this.digest.update(bArr2);
            }
        }
        return this.digest.digest();
    }

    private byte[] d(byte[] bArr) {
        int iMax = Math.max(1, this.digestCount);
        reset();
        for (int i = 0; i < iMax - 1; i++) {
            bArr = c(bArr);
            reset();
        }
        return bArr;
    }

    public byte[] digest(String str, String str2) {
        return digest(str, c.a(str2));
    }

    public String digestHex(String str, String str2) {
        return digestHex(str, c.a(str2));
    }

    public MessageDigest getDigest() {
        return this.digest;
    }

    public int getDigestLength() {
        return this.digest.getDigestLength();
    }

    public Digester init(String str, Provider provider) {
        if (provider == null) {
            this.digest = d.b(str);
        } else {
            try {
                this.digest = MessageDigest.getInstance(str, provider);
            } catch (NoSuchAlgorithmException e2) {
                throw new CryptoException(e2);
            }
        }
        return this;
    }

    public Digester reset() {
        this.digest.reset();
        return this;
    }

    public Digester setDigestCount(int i) {
        this.digestCount = i;
        return this;
    }

    public Digester setSalt(byte[] bArr) {
        this.salt = bArr;
        return this;
    }

    public Digester setSaltPosition(int i) {
        this.saltPosition = i;
        return this;
    }

    public Digester(String str) {
        this(str, (Provider) null);
    }

    public byte[] digest(String str, Charset charset) {
        return digest(b.b(str, charset));
    }

    public String digestHex(String str, Charset charset) {
        return cn.hutool.core.util.d.d(digest(str, charset));
    }

    public Digester(DigestAlgorithm digestAlgorithm, Provider provider) {
        init(digestAlgorithm.getValue(), provider);
    }

    public byte[] digest(String str) {
        return digest(str, c.f2949b);
    }

    public String digestHex(String str) {
        return digestHex(str, "UTF-8");
    }

    public byte[] digest(File file) throws Throwable {
        BufferedInputStream bufferedInputStreamA;
        try {
            bufferedInputStreamA = cn.hutool.core.io.d.a(file);
        } catch (Throwable th) {
            th = th;
            bufferedInputStreamA = null;
        }
        try {
            byte[] bArrDigest = digest(bufferedInputStreamA);
            e.a(bufferedInputStreamA);
            return bArrDigest;
        } catch (Throwable th2) {
            th = th2;
            e.a(bufferedInputStreamA);
            throw th;
        }
    }

    public String digestHex(File file) {
        return cn.hutool.core.util.d.d(digest(file));
    }

    public Digester(String str, Provider provider) {
        init(str, provider);
    }

    public String digestHex(byte[] bArr) {
        return cn.hutool.core.util.d.d(digest(bArr));
    }

    public String digestHex(InputStream inputStream) {
        return cn.hutool.core.util.d.d(digest(inputStream));
    }

    public String digestHex(InputStream inputStream, int i) {
        return cn.hutool.core.util.d.d(digest(inputStream, i));
    }

    public byte[] digest(byte[] bArr) {
        byte[] bArrC;
        int i = this.saltPosition;
        if (i <= 0) {
            bArrC = c(this.salt, bArr);
        } else if (i >= bArr.length) {
            bArrC = c(bArr, this.salt);
        } else if (g.b(this.salt)) {
            this.digest.update(bArr, 0, this.saltPosition);
            this.digest.update(this.salt);
            MessageDigest messageDigest = this.digest;
            int i2 = this.saltPosition;
            messageDigest.update(bArr, i2, bArr.length - i2);
            bArrC = this.digest.digest();
        } else {
            bArrC = c(bArr);
        }
        return d(bArrC);
    }

    public byte[] digest(InputStream inputStream) {
        return digest(inputStream, Segment.SIZE);
    }

    public byte[] digest(InputStream inputStream, int i) throws IORuntimeException {
        byte[] bArrA;
        if (i < 1) {
            i = Segment.SIZE;
        }
        try {
            if (g.a(this.salt)) {
                bArrA = b(inputStream, i);
            } else {
                bArrA = a(inputStream, i);
            }
            return d(bArrA);
        } catch (IOException e2) {
            throw new IORuntimeException(e2);
        }
    }
}
