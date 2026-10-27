package com.google.security.cryptauth.lib.securemessage;

import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class CryptoOps {
    static final int DIGEST_LENGTH = 20;
    private static final byte[] SALT = sha256("SecureMessage");
    private static final byte[] CONSTANT_01 = {1};

    private CryptoOps() {
    }

    public enum SigType {
        HMAC_SHA256(SecureMessageProto.SigScheme.HMAC_SHA256, "HmacSHA256", false),
        ECDSA_P256_SHA256(SecureMessageProto.SigScheme.ECDSA_P256_SHA256, "SHA256withECDSA", true),
        RSA2048_SHA256(SecureMessageProto.SigScheme.RSA2048_SHA256, "SHA256withRSA", true);

        private final String jcaName;
        private final boolean publicKeyScheme;
        private final SecureMessageProto.SigScheme sigScheme;

        public SecureMessageProto.SigScheme getSigScheme() {
            return this.sigScheme;
        }

        public String getJcaName() {
            return this.jcaName;
        }

        public boolean isPublicKeyScheme() {
            return this.publicKeyScheme;
        }

        public static SigType valueOf(SecureMessageProto.SigScheme sigScheme) {
            for (SigType value : values()) {
                if (value.sigScheme.equals(sigScheme)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Unsupported SigType: " + sigScheme);
        }

        SigType(SecureMessageProto.SigScheme sigType, String jcaName, boolean publicKeyScheme) {
            this.sigScheme = sigType;
            this.jcaName = jcaName;
            this.publicKeyScheme = publicKeyScheme;
        }
    }

    public enum EncType {
        NONE(SecureMessageProto.EncScheme.NONE, "InvalidDoNotUseForJCA"),
        AES_256_CBC(SecureMessageProto.EncScheme.AES_256_CBC, "AES/CBC/PKCS5Padding");

        private final SecureMessageProto.EncScheme encScheme;
        private final String jcaName;

        public SecureMessageProto.EncScheme getEncScheme() {
            return this.encScheme;
        }

        public String getJcaName() {
            return this.jcaName;
        }

        public static EncType valueOf(SecureMessageProto.EncScheme encScheme) {
            for (EncType value : values()) {
                if (value.encScheme.equals(encScheme)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Unsupported EncType: " + encScheme);
        }

        EncType(SecureMessageProto.EncScheme encScheme, String jcaName) {
            this.encScheme = encScheme;
            this.jcaName = jcaName;
        }
    }

    static byte[] sign(SigType sigType, Key signingKey, @Nullable SecureRandom rng, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
        if (signingKey == null || data == null) {
            throw null;
        }
        if (sigType.isPublicKeyScheme()) {
            if (rng == null) {
                throw null;
            }
            if (!(signingKey instanceof PrivateKey)) {
                throw new InvalidKeyException("Expected a PrivateKey");
            }
            Signature sigScheme = Signature.getInstance(sigType.getJcaName());
            sigScheme.initSign((PrivateKey) signingKey, rng);
            try {
                sigScheme.update(SALT);
                sigScheme.update(data);
                return sigScheme.sign();
            } catch (SignatureException e) {
                throw new IllegalStateException(e);
            }
        }
        Mac macScheme = Mac.getInstance(sigType.getJcaName());
        SecretKey derivedKey = deriveAes256KeyFor(getSecretKey(signingKey), getPurpose(sigType));
        macScheme.init(derivedKey);
        return macScheme.doFinal(data);
    }

    static boolean verify(Key verificationKey, SigType sigType, byte[] signature, byte[] data) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (verificationKey == null || signature == null || data == null) {
            throw null;
        }
        if (sigType.isPublicKeyScheme()) {
            if (!(verificationKey instanceof PublicKey)) {
                throw new InvalidKeyException("Expected a PublicKey");
            }
            Signature sigScheme = Signature.getInstance(sigType.getJcaName());
            sigScheme.initVerify((PublicKey) verificationKey);
            sigScheme.update(SALT);
            sigScheme.update(data);
            return sigScheme.verify(signature);
        }
        Mac macScheme = Mac.getInstance(sigType.getJcaName());
        SecretKey derivedKey = deriveAes256KeyFor(getSecretKey(verificationKey), getPurpose(sigType));
        macScheme.init(derivedKey);
        return constantTimeArrayEquals(signature, macScheme.doFinal(data));
    }

    static byte[] generateIv(EncType encType, SecureRandom rng) throws NoSuchAlgorithmException {
        if (rng == null) {
            throw null;
        }
        try {
            Cipher encrypter = Cipher.getInstance(encType.getJcaName());
            byte[] iv = new byte[encrypter.getBlockSize()];
            rng.nextBytes(iv);
            return iv;
        } catch (NoSuchPaddingException e) {
            throw new NoSuchAlgorithmException(e);
        }
    }

    static byte[] encrypt(Key encryptionKey, EncType encType, @Nullable SecureRandom rng, byte[] iv, byte[] plaintext) throws NoSuchAlgorithmException, InvalidKeyException {
        if (encryptionKey == null || iv == null || plaintext == null) {
            throw null;
        }
        if (encType == EncType.NONE) {
            throw new NoSuchAlgorithmException("Cannot use NONE type here");
        }
        try {
            Cipher encrypter = Cipher.getInstance(encType.getJcaName());
            SecretKey derivedKey = deriveAes256KeyFor(getSecretKey(encryptionKey), getPurpose(encType));
            encrypter.init(1, derivedKey, new IvParameterSpec(iv), rng);
            return encrypter.doFinal(plaintext);
        } catch (InvalidAlgorithmParameterException e) {
            throw new AssertionError(e);
        } catch (BadPaddingException e2) {
            throw new AssertionError(e2);
        } catch (IllegalBlockSizeException e3) {
            throw new AssertionError(e3);
        } catch (NoSuchPaddingException e4) {
            throw new NoSuchAlgorithmException(e4);
        }
    }

    static byte[] decrypt(Key decryptionKey, EncType encType, byte[] iv, byte[] ciphertext) throws BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (decryptionKey == null || iv == null || ciphertext == null) {
            throw null;
        }
        if (encType == EncType.NONE) {
            throw new NoSuchAlgorithmException("Cannot use NONE type here");
        }
        try {
            Cipher decrypter = Cipher.getInstance(encType.getJcaName());
            SecretKey derivedKey = deriveAes256KeyFor(getSecretKey(decryptionKey), getPurpose(encType));
            decrypter.init(2, derivedKey, new IvParameterSpec(iv));
            return decrypter.doFinal(ciphertext);
        } catch (NoSuchPaddingException e) {
            throw new AssertionError(e);
        }
    }

    static byte[] digest(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] truncatedHash = new byte[20];
        System.arraycopy(sha256.digest(data), 0, truncatedHash, 0, 20);
        return truncatedHash;
    }

    static boolean constantTimeArrayEquals(@Nullable byte[] a, @Nullable byte[] b) {
        if (a == null || b == null) {
            return a == b;
        }
        if (a.length != b.length) {
            return false;
        }
        byte result = 0;
        for (int i = 0; i < b.length; i++) {
            result = (byte) ((a[i] ^ b[i]) | result);
        }
        return result == 0;
    }

    static String getPurpose(SigType sigType) {
        return "SIG:" + sigType.getSigScheme().getNumber();
    }

    static String getPurpose(EncType encType) {
        return "ENC:" + encType.getEncScheme().getNumber();
    }

    private static SecretKey getSecretKey(Key key) throws InvalidKeyException {
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("Expected a SecretKey");
        }
        return (SecretKey) key;
    }

    public static byte[] utf8StringToBytes(String input) {
        try {
            return input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha256(String input) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return sha256.digest(utf8StringToBytes(input));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No security provider initialized yet?", e);
        }
    }

    static SecretKey deriveAes256KeyFor(SecretKey masterKey, String purpose) throws NoSuchAlgorithmException, InvalidKeyException {
        return new SecretKeySpec(hkdf(masterKey, SALT, utf8StringToBytes(purpose)), "AES");
    }

    public static byte[] hkdf(SecretKey inputKeyMaterial, byte[] salt, byte[] info) throws NoSuchAlgorithmException, InvalidKeyException {
        if (inputKeyMaterial == null || salt == null || info == null) {
            throw null;
        }
        return hkdfSha256Expand(hkdfSha256Extract(inputKeyMaterial, salt), info);
    }

    static byte[] concat(@Nullable byte[] a, @Nullable byte[] b) {
        if (a == null && b == null) {
            return new byte[0];
        }
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    static byte[] subarray(byte[] in, int beginIndex, int endIndex) {
        if (in == null) {
            throw null;
        }
        int length = endIndex - beginIndex;
        if (length < 0 || beginIndex < 0 || endIndex < 0 || beginIndex >= in.length || endIndex > in.length) {
            throw new IndexOutOfBoundsException();
        }
        byte[] result = new byte[length];
        if (length > 0) {
            System.arraycopy(in, beginIndex, result, 0, length);
        }
        return result;
    }

    private static byte[] hkdfSha256Extract(SecretKey inputKeyMaterial, byte[] salt) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac macScheme = Mac.getInstance("HmacSHA256");
        try {
            macScheme.init(new SecretKeySpec(salt, "AES"));
            byte[] encodedKeyMaterial = inputKeyMaterial.getEncoded();
            if (encodedKeyMaterial == null) {
                throw new InvalidKeyException("Cannot get encoded form of SecretKey");
            }
            return macScheme.doFinal(encodedKeyMaterial);
        } catch (InvalidKeyException e) {
            throw new AssertionError(e);
        }
    }

    private static byte[] hkdfSha256Expand(byte[] pseudoRandomKey, byte[] info) throws NoSuchAlgorithmException {
        Mac macScheme = Mac.getInstance("HmacSHA256");
        try {
            macScheme.init(new SecretKeySpec(pseudoRandomKey, "AES"));
            macScheme.update(info);
            return macScheme.doFinal(CONSTANT_01);
        } catch (InvalidKeyException e) {
            throw new AssertionError(e);
        }
    }
}
