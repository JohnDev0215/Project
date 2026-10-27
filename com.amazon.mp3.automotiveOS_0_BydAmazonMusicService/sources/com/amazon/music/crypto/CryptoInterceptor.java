package com.amazon.music.crypto;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CryptoInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(CryptoInterceptor.class.getSimpleName());
    protected String mAlias;
    protected final Cipher mInput;
    protected final Cipher mOutput;
    protected final SecretKeySpec mSecretKeySpec;

    abstract String decrypt(String str) throws DecryptionException;

    abstract String encrypt(String str) throws EncryptionException;

    abstract String getAlgorithmName();

    abstract Charset getCharset();

    abstract String getTransformationType();

    public CryptoInterceptor(String str) throws Exception {
        try {
            this.mAlias = str;
            this.mSecretKeySpec = new SecretKeySpec(Arrays.copyOfRange(MessageDigest.getInstance("SHA-256").digest(str.getBytes(getCharset())), 0, 16), getAlgorithmName());
            this.mInput = Cipher.getInstance(getTransformationType());
            this.mOutput = Cipher.getInstance(getTransformationType());
        } catch (Exception e) {
            throw e;
        }
    }

    protected void dumpDebugInfo(Cipher cipher) {
        if (cipher != null) {
            LOG.error("Crypto info: provider = " + cipher.getProvider() + " | algorithm = " + cipher.getAlgorithm() + " | blockSize = " + cipher.getBlockSize() + " | parameters = " + cipher.getParameters());
            return;
        }
        LOG.error("Cipher instance is not initiated");
    }
}
