package org.apache.shiro.crypto;

import java.security.SecureRandom;
import java.util.Objects;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public class SecureRandomNumberGenerator implements RandomNumberGenerator {
    protected static final int DEFAULT_NEXT_BYTES_SIZE = 16;
    private int defaultNextBytesSize = 16;
    private SecureRandom secureRandom = new SecureRandom();

    public void setSeed(byte[] bArr) {
        this.secureRandom.setSeed(bArr);
    }

    public SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    public void setSecureRandom(SecureRandom secureRandom) throws NullPointerException {
        Objects.requireNonNull(secureRandom, "SecureRandom argument cannot be null.");
        this.secureRandom = secureRandom;
    }

    public int getDefaultNextBytesSize() {
        return this.defaultNextBytesSize;
    }

    public void setDefaultNextBytesSize(int i) throws IllegalArgumentException {
        if (i <= 0) {
            throw new IllegalArgumentException("size value must be a positive integer (1 or larger)");
        }
        this.defaultNextBytesSize = i;
    }

    @Override // org.apache.shiro.crypto.RandomNumberGenerator
    public ByteSource nextBytes() {
        return nextBytes(getDefaultNextBytesSize());
    }

    @Override // org.apache.shiro.crypto.RandomNumberGenerator
    public ByteSource nextBytes(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("numBytes argument must be a positive integer (1 or larger)");
        }
        byte[] bArr = new byte[i];
        this.secureRandom.nextBytes(bArr);
        return ByteSource.Util.bytes(bArr);
    }
}
