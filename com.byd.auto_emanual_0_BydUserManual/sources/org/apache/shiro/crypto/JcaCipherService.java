package org.apache.shiro.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class JcaCipherService implements CipherService {
    private static final int BITS_PER_BYTE = 8;
    private static final int DEFAULT_KEY_SIZE = 128;
    private static final int DEFAULT_STREAMING_BUFFER_SIZE = 512;
    private static final String RANDOM_NUM_GENERATOR_ALGORITHM_NAME = "SHA1PRNG";
    private static final Logger log = LoggerFactory.getLogger(JcaCipherService.class);
    private String algorithmName;
    private boolean generateInitializationVectors;
    private int initializationVectorSize;
    private int keySize;
    private SecureRandom secureRandom;
    private int streamingBufferSize;

    protected JcaCipherService(String str) {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("algorithmName argument cannot be null or empty.");
        }
        this.algorithmName = str;
        this.keySize = 128;
        this.initializationVectorSize = 128;
        this.streamingBufferSize = 512;
        this.generateInitializationVectors = true;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getKeySize() {
        return this.keySize;
    }

    public void setKeySize(int i) {
        this.keySize = i;
    }

    public boolean isGenerateInitializationVectors() {
        return this.generateInitializationVectors;
    }

    public void setGenerateInitializationVectors(boolean z) {
        this.generateInitializationVectors = z;
    }

    public int getInitializationVectorSize() {
        return this.initializationVectorSize;
    }

    public void setInitializationVectorSize(int i) throws IllegalArgumentException {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("Initialization vector sizes are specified in bits, but must be a multiple of 8 so they can be easily represented as a byte array.");
        }
        this.initializationVectorSize = i;
    }

    protected boolean isGenerateInitializationVectors(boolean z) {
        return isGenerateInitializationVectors();
    }

    public int getStreamingBufferSize() {
        return this.streamingBufferSize;
    }

    public void setStreamingBufferSize(int i) {
        this.streamingBufferSize = i;
    }

    public SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    public void setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }

    protected static SecureRandom getDefaultSecureRandom() {
        try {
            return SecureRandom.getInstance(RANDOM_NUM_GENERATOR_ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException e) {
            log.debug("The SecureRandom SHA1PRNG algorithm is not available on the current platform.  Using the platform's default SecureRandom algorithm.", e);
            return new SecureRandom();
        }
    }

    protected SecureRandom ensureSecureRandom() {
        SecureRandom secureRandom = getSecureRandom();
        return secureRandom == null ? getDefaultSecureRandom() : secureRandom;
    }

    protected String getTransformationString(boolean z) {
        return getAlgorithmName();
    }

    protected byte[] generateInitializationVector(boolean z) {
        int initializationVectorSize = getInitializationVectorSize();
        if (initializationVectorSize <= 0) {
            throw new IllegalStateException("initializationVectorSize property must be greater than zero.  This number is typically set in the " + CipherService.class.getSimpleName() + " subclass constructor.  Also check your configuration to ensure that if you are setting a value, it is positive.");
        }
        if (initializationVectorSize % 8 != 0) {
            throw new IllegalStateException("initializationVectorSize property must be a multiple of 8 to represent as a byte array.");
        }
        byte[] bArr = new byte[initializationVectorSize / 8];
        ensureSecureRandom().nextBytes(bArr);
        return bArr;
    }

    @Override // org.apache.shiro.crypto.CipherService
    public ByteSource encrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArrGenerateInitializationVector;
        boolean zIsGenerateInitializationVectors = isGenerateInitializationVectors(false);
        if (zIsGenerateInitializationVectors) {
            bArrGenerateInitializationVector = generateInitializationVector(false);
            if (bArrGenerateInitializationVector == null || bArrGenerateInitializationVector.length == 0) {
                throw new IllegalStateException("Initialization vector generation is enabled - generated vector cannot be null or empty.");
            }
        } else {
            bArrGenerateInitializationVector = null;
        }
        return encrypt(bArr, bArr2, bArrGenerateInitializationVector, zIsGenerateInitializationVectors);
    }

    private ByteSource encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z) throws CryptoException {
        byte[] bArrCrypt;
        if (z && bArr3 != null && bArr3.length > 0) {
            byte[] bArrCrypt2 = crypt(bArr, bArr2, bArr3, 1);
            bArrCrypt = new byte[bArr3.length + bArrCrypt2.length];
            System.arraycopy(bArr3, 0, bArrCrypt, 0, bArr3.length);
            System.arraycopy(bArrCrypt2, 0, bArrCrypt, bArr3.length, bArrCrypt2.length);
        } else {
            bArrCrypt = crypt(bArr, bArr2, bArr3, 1);
        }
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("Incoming plaintext of size " + (bArr != null ? bArr.length : 0) + ".  Ciphertext byte array is size " + (bArrCrypt != null ? bArrCrypt.length : 0));
        }
        return ByteSource.Util.bytes(bArrCrypt);
    }

    @Override // org.apache.shiro.crypto.CipherService
    public ByteSource decrypt(byte[] bArr, byte[] bArr2) throws CryptoException {
        byte[] bArr3;
        if (isGenerateInitializationVectors(false)) {
            try {
                int initializationVectorSize = getInitializationVectorSize() / 8;
                bArr3 = new byte[initializationVectorSize];
                System.arraycopy(bArr, 0, bArr3, 0, initializationVectorSize);
                int length = bArr.length - initializationVectorSize;
                byte[] bArr4 = new byte[length];
                System.arraycopy(bArr, initializationVectorSize, bArr4, 0, length);
                bArr = bArr4;
            } catch (Exception e) {
                throw new CryptoException("Unable to correctly extract the Initialization Vector or ciphertext.", e);
            }
        } else {
            bArr3 = null;
        }
        return decrypt(bArr, bArr2, bArr3);
    }

    private ByteSource decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws CryptoException {
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("Attempting to decrypt incoming byte array of length " + (bArr != null ? bArr.length : 0));
        }
        byte[] bArrCrypt = crypt(bArr, bArr2, bArr3, 2);
        if (bArrCrypt == null) {
            return null;
        }
        return ByteSource.Util.bytes(bArrCrypt);
    }

    private Cipher newCipherInstance(boolean z) throws CryptoException {
        String transformationString = getTransformationString(z);
        try {
            return Cipher.getInstance(transformationString);
        } catch (Exception e) {
            throw new CryptoException("Unable to acquire a Java JCA Cipher instance using " + Cipher.class.getName() + ".getInstance( \"" + transformationString + "\" ). " + getAlgorithmName() + " under this configuration is required for the " + getClass().getName() + " instance to function.", e);
        }
    }

    private byte[] crypt(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) throws IllegalArgumentException, CryptoException {
        if (bArr2 == null || bArr2.length == 0) {
            throw new IllegalArgumentException("key argument cannot be null or empty.");
        }
        return crypt(initNewCipher(i, bArr2, bArr3, false), bArr);
    }

    private byte[] crypt(Cipher cipher, byte[] bArr) throws CryptoException {
        try {
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            throw new CryptoException("Unable to execute 'doFinal' with cipher instance [" + cipher + "].", e);
        }
    }

    private void init(Cipher cipher, int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws CryptoException {
        try {
            if (secureRandom != null) {
                if (algorithmParameterSpec != null) {
                    cipher.init(i, key, algorithmParameterSpec, secureRandom);
                } else {
                    cipher.init(i, key, secureRandom);
                }
            } else if (algorithmParameterSpec != null) {
                cipher.init(i, key, algorithmParameterSpec);
            } else {
                cipher.init(i, key);
            }
        } catch (Exception e) {
            throw new CryptoException("Unable to init cipher instance.", e);
        }
    }

    @Override // org.apache.shiro.crypto.CipherService
    public void encrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws CryptoException {
        byte[] bArrGenerateInitializationVector;
        boolean zIsGenerateInitializationVectors = isGenerateInitializationVectors(true);
        if (zIsGenerateInitializationVectors) {
            bArrGenerateInitializationVector = generateInitializationVector(true);
            if (bArrGenerateInitializationVector == null || bArrGenerateInitializationVector.length == 0) {
                throw new IllegalStateException("Initialization vector generation is enabled - generated vector cannot be null or empty.");
            }
        } else {
            bArrGenerateInitializationVector = null;
        }
        encrypt(inputStream, outputStream, bArr, bArrGenerateInitializationVector, zIsGenerateInitializationVectors);
    }

    private void encrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr, byte[] bArr2, boolean z) throws CryptoException {
        if (z && bArr2 != null && bArr2.length > 0) {
            try {
                outputStream.write(bArr2);
            } catch (IOException e) {
                throw new CryptoException(e);
            }
        }
        crypt(inputStream, outputStream, bArr, bArr2, 1);
    }

    @Override // org.apache.shiro.crypto.CipherService
    public void decrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws CryptoException {
        decrypt(inputStream, outputStream, bArr, isGenerateInitializationVectors(true));
    }

    private void decrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr, boolean z) throws CryptoException {
        byte[] bArr2;
        if (z) {
            int initializationVectorSize = getInitializationVectorSize() / 8;
            bArr2 = new byte[initializationVectorSize];
            try {
                if (inputStream.read(bArr2) != initializationVectorSize) {
                    throw new CryptoException("Unable to read initialization vector bytes from the InputStream.  This is required when initialization vectors are autogenerated during an encryption operation.");
                }
            } catch (IOException e) {
                throw new CryptoException("Unable to correctly read the Initialization Vector from the input stream.", e);
            }
        } else {
            bArr2 = null;
        }
        decrypt(inputStream, outputStream, bArr, bArr2);
    }

    private void decrypt(InputStream inputStream, OutputStream outputStream, byte[] bArr, byte[] bArr2) throws CryptoException {
        crypt(inputStream, outputStream, bArr, bArr2, 2);
    }

    private void crypt(InputStream inputStream, OutputStream outputStream, byte[] bArr, byte[] bArr2, int i) throws CryptoException {
        Objects.requireNonNull(inputStream, "InputStream argument cannot be null.");
        Objects.requireNonNull(outputStream, "OutputStream argument cannot be null.");
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, initNewCipher(i, bArr, bArr2, true));
        byte[] bArr3 = new byte[getStreamingBufferSize()];
        while (true) {
            try {
                int i2 = cipherInputStream.read(bArr3);
                if (i2 == -1) {
                    return;
                } else {
                    outputStream.write(bArr3, 0, i2);
                }
            } catch (IOException e) {
                throw new CryptoException(e);
            }
        }
    }

    private Cipher initNewCipher(int i, byte[] bArr, byte[] bArr2, boolean z) throws CryptoException {
        Cipher cipherNewCipherInstance = newCipherInstance(z);
        init(cipherNewCipherInstance, i, new SecretKeySpec(bArr, getAlgorithmName()), (bArr2 == null || bArr2.length <= 0) ? null : createParameterSpec(bArr2, z), getSecureRandom());
        return cipherNewCipherInstance;
    }

    protected AlgorithmParameterSpec createParameterSpec(byte[] bArr, boolean z) {
        return new IvParameterSpec(bArr);
    }
}
