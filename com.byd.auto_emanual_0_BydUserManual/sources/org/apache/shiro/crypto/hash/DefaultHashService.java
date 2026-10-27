package org.apache.shiro.crypto.hash;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public class DefaultHashService implements ConfigurableHashService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteSource privateSalt;
    private String algorithmName = Sha512Hash.ALGORITHM_NAME;
    private int iterations = 1;
    private boolean generatePublicSalt = false;
    private RandomNumberGenerator rng = new SecureRandomNumberGenerator();

    @Override // org.apache.shiro.crypto.hash.HashService
    public Hash computeHash(HashRequest hashRequest) {
        if (hashRequest == null || hashRequest.getSource() == null || hashRequest.getSource().isEmpty()) {
            return null;
        }
        String algorithmName = getAlgorithmName(hashRequest);
        ByteSource source = hashRequest.getSource();
        int iterations = getIterations(hashRequest);
        ByteSource publicSalt = getPublicSalt(hashRequest);
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, combine(getPrivateSalt(), publicSalt), iterations);
        SimpleHash simpleHash2 = new SimpleHash(algorithmName);
        simpleHash2.setBytes(simpleHash.getBytes());
        simpleHash2.setIterations(iterations);
        simpleHash2.setSalt(publicSalt);
        return simpleHash2;
    }

    protected String getAlgorithmName(HashRequest hashRequest) {
        String algorithmName = hashRequest.getAlgorithmName();
        return algorithmName == null ? getHashAlgorithmName() : algorithmName;
    }

    protected int getIterations(HashRequest hashRequest) {
        int iMax = Math.max(0, hashRequest.getIterations());
        return iMax < 1 ? Math.max(1, getHashIterations()) : iMax;
    }

    protected ByteSource getPublicSalt(HashRequest hashRequest) {
        ByteSource salt = hashRequest.getSalt();
        if (salt != null && !salt.isEmpty()) {
            return salt;
        }
        ByteSource privateSalt = getPrivateSalt();
        if (((privateSalt == null || privateSalt.isEmpty()) ? false : true) || isGeneratePublicSalt()) {
            return getRandomNumberGenerator().nextBytes();
        }
        return null;
    }

    protected ByteSource combine(ByteSource byteSource, ByteSource byteSource2) {
        byte[] bytes = byteSource != null ? byteSource.getBytes() : null;
        int i = 0;
        int length = bytes != null ? bytes.length : 0;
        byte[] bytes2 = byteSource2 != null ? byteSource2.getBytes() : null;
        int length2 = bytes2 != null ? bytes2.length : 0;
        int i2 = length + length2;
        if (i2 <= 0) {
            return null;
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            bArr[i4] = bytes[i3];
            i3++;
            i4++;
        }
        while (i < length2) {
            bArr[i4] = bytes2[i];
            i++;
            i4++;
        }
        return ByteSource.Util.bytes(bArr);
    }

    @Override // org.apache.shiro.crypto.hash.ConfigurableHashService
    public void setHashAlgorithmName(String str) {
        this.algorithmName = str;
    }

    public String getHashAlgorithmName() {
        return this.algorithmName;
    }

    @Override // org.apache.shiro.crypto.hash.ConfigurableHashService
    public void setPrivateSalt(ByteSource byteSource) {
        this.privateSalt = byteSource;
    }

    public ByteSource getPrivateSalt() {
        return this.privateSalt;
    }

    @Override // org.apache.shiro.crypto.hash.ConfigurableHashService
    public void setHashIterations(int i) {
        this.iterations = i;
    }

    public int getHashIterations() {
        return this.iterations;
    }

    @Override // org.apache.shiro.crypto.hash.ConfigurableHashService
    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.rng = randomNumberGenerator;
    }

    public RandomNumberGenerator getRandomNumberGenerator() {
        return this.rng;
    }

    public boolean isGeneratePublicSalt() {
        return this.generatePublicSalt;
    }

    public void setGeneratePublicSalt(boolean z) {
        this.generatePublicSalt = z;
    }
}
