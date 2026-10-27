package org.apache.shiro.crypto.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class SimpleHash extends AbstractHash {
    private static final int DEFAULT_ITERATIONS = 1;
    private final String algorithmName;
    private transient String base64Encoded;
    private byte[] bytes;
    private transient String hexEncoded;
    private int iterations;
    private ByteSource salt;

    public SimpleHash(String str) {
        this.hexEncoded = null;
        this.base64Encoded = null;
        this.algorithmName = str;
        this.iterations = 1;
    }

    public SimpleHash(String str, Object obj) throws UnknownAlgorithmException, CodecException {
        this(str, obj, null, 1);
    }

    public SimpleHash(String str, Object obj, Object obj2) throws UnknownAlgorithmException, CodecException {
        this(str, obj, obj2, 1);
    }

    public SimpleHash(String str, Object obj, Object obj2, int i) throws UnknownAlgorithmException, CodecException {
        ByteSource byteSourceConvertSaltToBytes = null;
        this.hexEncoded = null;
        this.base64Encoded = null;
        if (!StringUtils.hasText(str)) {
            throw new NullPointerException("algorithmName argument cannot be null or empty.");
        }
        this.algorithmName = str;
        this.iterations = Math.max(1, i);
        if (obj2 != null) {
            byteSourceConvertSaltToBytes = convertSaltToBytes(obj2);
            this.salt = byteSourceConvertSaltToBytes;
        }
        hash(convertSourceToBytes(obj), byteSourceConvertSaltToBytes, i);
    }

    protected ByteSource convertSourceToBytes(Object obj) {
        return toByteSource(obj);
    }

    protected ByteSource convertSaltToBytes(Object obj) {
        return toByteSource(obj);
    }

    protected ByteSource toByteSource(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ByteSource) {
            return (ByteSource) obj;
        }
        return ByteSource.Util.bytes(toBytes(obj));
    }

    private void hash(ByteSource byteSource, ByteSource byteSource2, int i) throws UnknownAlgorithmException, CodecException {
        setBytes(hash(byteSource.getBytes(), byteSource2 != null ? byteSource2.getBytes() : null, i));
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash, org.apache.shiro.crypto.hash.Hash
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    @Override // org.apache.shiro.crypto.hash.Hash
    public ByteSource getSalt() {
        return this.salt;
    }

    @Override // org.apache.shiro.crypto.hash.Hash
    public int getIterations() {
        return this.iterations;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash, org.apache.shiro.util.ByteSource
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    public void setBytes(byte[] bArr) {
        this.bytes = bArr;
        this.hexEncoded = null;
        this.base64Encoded = null;
    }

    public void setIterations(int i) {
        this.iterations = Math.max(1, i);
    }

    public void setSalt(ByteSource byteSource) {
        this.salt = byteSource;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    protected MessageDigest getDigest(String str) throws UnknownAlgorithmException {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownAlgorithmException("No native '" + str + "' MessageDigest instance available on the current JVM.", e);
        }
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    protected byte[] hash(byte[] bArr) throws UnknownAlgorithmException {
        return hash(bArr, (byte[]) null, 1);
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    protected byte[] hash(byte[] bArr, byte[] bArr2) throws UnknownAlgorithmException {
        return hash(bArr, bArr2, 1);
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    protected byte[] hash(byte[] bArr, byte[] bArr2, int i) throws UnknownAlgorithmException {
        MessageDigest digest = getDigest(getAlgorithmName());
        if (bArr2 != null) {
            digest.reset();
            digest.update(bArr2);
        }
        byte[] bArrDigest = digest.digest(bArr);
        int i2 = i - 1;
        for (int i3 = 0; i3 < i2; i3++) {
            digest.reset();
            bArrDigest = digest.digest(bArrDigest);
        }
        return bArrDigest;
    }

    @Override // org.apache.shiro.util.ByteSource
    public boolean isEmpty() {
        byte[] bArr = this.bytes;
        return bArr == null || bArr.length == 0;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash, org.apache.shiro.util.ByteSource
    public String toHex() {
        if (this.hexEncoded == null) {
            this.hexEncoded = Hex.encodeToString(getBytes());
        }
        return this.hexEncoded;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash, org.apache.shiro.util.ByteSource
    public String toBase64() {
        if (this.base64Encoded == null) {
            this.base64Encoded = Base64.encodeToString(getBytes());
        }
        return this.base64Encoded;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    public String toString() {
        return toHex();
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    public boolean equals(Object obj) {
        if (obj instanceof Hash) {
            return MessageDigest.isEqual(getBytes(), ((Hash) obj).getBytes());
        }
        return false;
    }

    @Override // org.apache.shiro.crypto.hash.AbstractHash
    public int hashCode() {
        byte[] bArr = this.bytes;
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(bArr);
    }
}
