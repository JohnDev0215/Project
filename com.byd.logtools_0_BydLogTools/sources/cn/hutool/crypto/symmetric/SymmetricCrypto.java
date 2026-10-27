package cn.hutool.crypto.symmetric;

import c.a.a.c.f;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.e;
import cn.hutool.core.util.g;
import cn.hutool.core.util.h;
import cn.hutool.crypto.CipherMode;
import cn.hutool.crypto.CryptoException;
import cn.hutool.crypto.Padding;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import okio.Segment;

/* JADX INFO: loaded from: classes.dex */
public class SymmetricCrypto implements d, c, Serializable {
    private static final long serialVersionUID = 1;
    private cn.hutool.crypto.a cipherWrapper;
    private boolean isZeroPadding;
    private final Lock lock;
    private SecretKey secretKey;

    public SymmetricCrypto(SymmetricAlgorithm symmetricAlgorithm) {
        this(symmetricAlgorithm, (byte[]) null);
    }

    private static void a(CipherInputStream cipherInputStream, OutputStream outputStream, int i) throws IOException {
        int iMax = i * (8192 > i ? Math.max(1, Segment.SIZE / i) : 1);
        byte[] bArr = new byte[iMax];
        byte[] bArr2 = new byte[iMax];
        boolean z = true;
        int i2 = 0;
        while (true) {
            int i3 = cipherInputStream.read(bArr2);
            if (i3 == -1) {
                break;
            }
            if (z) {
                z = false;
            } else {
                outputStream.write(bArr, 0, i2);
            }
            cn.hutool.core.util.a.d(bArr2, bArr, i3);
            i2 = i3;
        }
        int i4 = i2 - 1;
        while (i4 >= 0 && bArr[i4] == 0) {
            i4--;
        }
        outputStream.write(bArr, 0, i4 + 1);
        outputStream.flush();
    }

    private Cipher b(int i) throws InvalidKeyException, InvalidAlgorithmParameterException {
        return this.cipherWrapper.b(i, this.secretKey).a();
    }

    private SymmetricCrypto c(String str, AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec == null) {
            byte[] bArrB = (byte[]) f.e(this.cipherWrapper).d(new Function() { // from class: cn.hutool.crypto.symmetric.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((cn.hutool.crypto.a) obj).a();
                }
            }).d(new Function() { // from class: cn.hutool.crypto.symmetric.b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Cipher) obj).getIV();
                }
            }).b();
            if (c.a.a.d.b.q(str, "PBE")) {
                if (bArrB == null) {
                    bArrB = h.b(8);
                }
                algorithmParameterSpec = new PBEParameterSpec(bArrB, 100);
            } else if (c.a.a.d.b.q(str, "AES") && bArrB != null) {
                algorithmParameterSpec = new IvParameterSpec(bArrB);
            }
        }
        return setParams(algorithmParameterSpec);
    }

    private byte[] d(byte[] bArr, int i) {
        int length;
        int length2;
        return (!this.isZeroPadding || (length2 = (length = bArr.length) % i) <= 0) ? bArr : g.c(bArr, (length + i) - length2);
    }

    private byte[] e(byte[] bArr, int i) {
        if (!this.isZeroPadding || i <= 0) {
            return bArr;
        }
        int length = bArr.length;
        if (length % i != 0) {
            return bArr;
        }
        int i2 = length - 1;
        while (i2 >= 0 && bArr[i2] == 0) {
            i2--;
        }
        return g.c(bArr, i2 + 1);
    }

    public /* bridge */ /* synthetic */ byte[] decrypt(InputStream inputStream) throws IORuntimeException {
        return super.decrypt(inputStream);
    }

    public /* bridge */ /* synthetic */ byte[] decrypt(String str) {
        return super.decrypt(str);
    }

    @Override // cn.hutool.crypto.symmetric.c
    public byte[] decrypt(byte[] bArr) {
        this.lock.lock();
        try {
            try {
                Cipher cipherB = b(2);
                int blockSize = cipherB.getBlockSize();
                byte[] bArrDoFinal = cipherB.doFinal(bArr);
                this.lock.unlock();
                return e(bArrDoFinal, blockSize);
            } catch (Exception e2) {
                throw new CryptoException(e2);
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public /* bridge */ /* synthetic */ String decryptStr(InputStream inputStream) {
        return super.decryptStr(inputStream);
    }

    public /* bridge */ /* synthetic */ String decryptStr(InputStream inputStream, Charset charset) {
        return super.decryptStr(inputStream, charset);
    }

    public /* bridge */ /* synthetic */ String decryptStr(String str) {
        return super.decryptStr(str);
    }

    public /* bridge */ /* synthetic */ String decryptStr(String str, Charset charset) {
        return super.decryptStr(str, charset);
    }

    public /* bridge */ /* synthetic */ String decryptStr(byte[] bArr) {
        return super.decryptStr(bArr);
    }

    public /* bridge */ /* synthetic */ String decryptStr(byte[] bArr, Charset charset) {
        return super.decryptStr(bArr, charset);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(InputStream inputStream) throws IORuntimeException {
        return super.encrypt(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(String str) {
        return super.encrypt(str);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(String str, String str2) {
        return super.encrypt(str, str2);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ byte[] encrypt(String str, Charset charset) {
        return super.encrypt(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public byte[] encrypt(byte[] bArr) {
        this.lock.lock();
        try {
            try {
                Cipher cipherB = b(1);
                return cipherB.doFinal(d(bArr, cipherB.getBlockSize()));
            } catch (Exception e2) {
                throw new CryptoException(e2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(InputStream inputStream) {
        return super.encryptBase64(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(String str) {
        return super.encryptBase64(str);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(String str, String str2) {
        return super.encryptBase64(str, str2);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(String str, Charset charset) {
        return super.encryptBase64(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptBase64(byte[] bArr) {
        return super.encryptBase64(bArr);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(InputStream inputStream) {
        return super.encryptHex(inputStream);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(String str) {
        return super.encryptHex(str);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(String str, String str2) {
        return super.encryptHex(str, str2);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(String str, Charset charset) {
        return super.encryptHex(str, charset);
    }

    @Override // cn.hutool.crypto.symmetric.d
    public /* bridge */ /* synthetic */ String encryptHex(byte[] bArr) {
        return super.encryptHex(bArr);
    }

    public Cipher getCipher() {
        return this.cipherWrapper.a();
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    public SymmetricCrypto init(String str, SecretKey secretKey) {
        c.a.a.c.d.d(str, "'algorithm' must be not blank !", new Object[0]);
        this.secretKey = secretKey;
        Padding padding = Padding.ZeroPadding;
        if (str.contains(padding.name())) {
            str = c.a.a.d.b.n(str, padding.name(), Padding.NoPadding.name());
            this.isZeroPadding = true;
        }
        this.cipherWrapper = new cn.hutool.crypto.a(str);
        return this;
    }

    public SymmetricCrypto setIv(IvParameterSpec ivParameterSpec) {
        return setParams(ivParameterSpec);
    }

    public SymmetricCrypto setMode(CipherMode cipherMode) {
        this.lock.lock();
        try {
            try {
                b(cipherMode.getValue());
                return this;
            } catch (Exception e2) {
                throw new CryptoException(e2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    public SymmetricCrypto setParams(AlgorithmParameterSpec algorithmParameterSpec) {
        this.cipherWrapper.c(algorithmParameterSpec);
        return this;
    }

    public SymmetricCrypto setRandom(SecureRandom secureRandom) {
        this.cipherWrapper.d(secureRandom);
        return this;
    }

    public byte[] update(byte[] bArr) {
        Cipher cipherA = this.cipherWrapper.a();
        this.lock.lock();
        try {
            try {
                return cipherA.update(d(bArr, cipherA.getBlockSize()));
            } catch (Exception e2) {
                throw new CryptoException(e2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    public String updateHex(byte[] bArr) {
        return cn.hutool.core.util.d.d(update(bArr));
    }

    public SymmetricCrypto(String str) {
        this(str, (byte[]) null);
    }

    public SymmetricCrypto setIv(byte[] bArr) {
        return setIv(new IvParameterSpec(bArr));
    }

    public SymmetricCrypto(SymmetricAlgorithm symmetricAlgorithm, byte[] bArr) {
        this(symmetricAlgorithm.getValue(), bArr);
    }

    public SymmetricCrypto(SymmetricAlgorithm symmetricAlgorithm, SecretKey secretKey) {
        this(symmetricAlgorithm.getValue(), secretKey);
    }

    public SymmetricCrypto(String str, byte[] bArr) {
        this(str, cn.hutool.crypto.b.f(str, bArr));
    }

    public SymmetricCrypto(String str, SecretKey secretKey) {
        this(str, secretKey, null);
    }

    public SymmetricCrypto(String str, SecretKey secretKey, AlgorithmParameterSpec algorithmParameterSpec) {
        this.lock = new ReentrantLock();
        init(str, secretKey);
        c(str, algorithmParameterSpec);
    }

    public void encrypt(InputStream inputStream, OutputStream outputStream, boolean z) throws Throwable {
        Cipher cipherB;
        CipherOutputStream cipherOutputStream;
        int blockSize;
        int i;
        this.lock.lock();
        CipherOutputStream cipherOutputStream2 = null;
        try {
            try {
                cipherB = b(1);
                cipherOutputStream = new CipherOutputStream(outputStream, cipherB);
            } catch (IORuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                e = e3;
            }
            try {
                long jB = e.b(inputStream, cipherOutputStream);
                if (this.isZeroPadding && (blockSize = cipherB.getBlockSize()) > 0 && (i = (int) (jB % ((long) blockSize))) > 0) {
                    cipherOutputStream.write(new byte[blockSize - i]);
                    cipherOutputStream.flush();
                }
                this.lock.unlock();
                e.a(cipherOutputStream);
                if (z) {
                    e.a(inputStream);
                }
            } catch (IORuntimeException e4) {
            } catch (Exception e5) {
                e = e5;
                throw new CryptoException(e);
            } catch (Throwable th) {
                th = th;
                cipherOutputStream2 = cipherOutputStream;
                this.lock.unlock();
                e.a(cipherOutputStream2);
                if (z) {
                    e.a(inputStream);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void decrypt(InputStream inputStream, OutputStream outputStream, boolean z) throws Throwable {
        int blockSize;
        this.lock.lock();
        CipherInputStream cipherInputStream = null;
        try {
            try {
                Cipher cipherB = b(2);
                CipherInputStream cipherInputStream2 = new CipherInputStream(inputStream, cipherB);
                try {
                    if (this.isZeroPadding && (blockSize = cipherB.getBlockSize()) > 0) {
                        a(cipherInputStream2, outputStream, blockSize);
                        this.lock.unlock();
                        e.a(cipherInputStream2);
                        if (z) {
                            e.a(inputStream);
                            return;
                        }
                        return;
                    }
                    e.b(cipherInputStream2, outputStream);
                    this.lock.unlock();
                    e.a(cipherInputStream2);
                    if (z) {
                        e.a(inputStream);
                    }
                } catch (IORuntimeException e2) {
                } catch (IOException e3) {
                    e = e3;
                    throw new IORuntimeException(e);
                } catch (Exception e4) {
                    e = e4;
                    throw new CryptoException(e);
                } catch (Throwable th) {
                    th = th;
                    cipherInputStream = cipherInputStream2;
                    this.lock.unlock();
                    e.a(cipherInputStream);
                    if (z) {
                        e.a(inputStream);
                    }
                    throw th;
                }
            } catch (IORuntimeException e5) {
                throw e5;
            } catch (IOException e6) {
                e = e6;
            } catch (Exception e7) {
                e = e7;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
