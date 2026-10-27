package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securemessage.PublicKeyProtoUtil;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class KeyEncoding {
    static final String SYMMETRIC_KEY_ENCODING_ALG = "AES";
    private static boolean simulateLegacyCryptoRequired = false;

    private KeyEncoding() {
    }

    public static byte[] encodeMasterKey(SecretKey masterKey) {
        return masterKey.getEncoded();
    }

    public static SecretKey parseMasterKey(byte[] encodedMasterKey) {
        return new SecretKeySpec(encodedMasterKey, SYMMETRIC_KEY_ENCODING_ALG);
    }

    public static byte[] encodeUserPublicKey(PublicKey pk) {
        return encodePublicKey(pk);
    }

    public static byte[] encodeUserPrivateKey(PrivateKey sk) {
        return sk.getEncoded();
    }

    public static PrivateKey parseUserPrivateKey(byte[] encodedPrivateKey, boolean isLegacy) throws InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        if (isLegacy) {
            return getRsaKeyFactory().generatePrivate(keySpec);
        }
        return getEcKeyFactory().generatePrivate(keySpec);
    }

    public static PublicKey parseUserPublicKey(byte[] keyBytes) throws InvalidKeySpecException {
        return parsePublicKey(keyBytes);
    }

    public static byte[] encodeKeyAgreementPublicKey(PublicKey pk) {
        return encodePublicKey(pk);
    }

    public static PublicKey parseKeyAgreementPublicKey(byte[] keyBytes) throws InvalidKeySpecException {
        return parsePublicKey(keyBytes);
    }

    public static byte[] encodeKeyAgreementPrivateKey(PrivateKey sk) {
        if (isLegacyPrivateKey(sk)) {
            return PublicKeyProtoUtil.encodeDh2048PrivateKey((DHPrivateKey) sk);
        }
        return sk.getEncoded();
    }

    public static PrivateKey parseKeyAgreementPrivateKey(byte[] keyBytes, boolean isLegacy) throws InvalidKeySpecException {
        if (isLegacy) {
            return PublicKeyProtoUtil.parseDh2048PrivateKey(keyBytes);
        }
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return getEcKeyFactory().generatePrivate(keySpec);
    }

    public static byte[] encodeSigningPublicKey(PublicKey pk) {
        return encodePublicKey(pk);
    }

    public static PublicKey parseSigningPublicKey(byte[] keyBytes) throws InvalidKeySpecException {
        return parsePublicKey(keyBytes);
    }

    public static byte[] encodeSigningPrivateKey(PrivateKey sk) {
        return sk.getEncoded();
    }

    public static PrivateKey parseSigningPrivateKey(byte[] keyBytes) throws InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return getEcKeyFactory().generatePrivate(keySpec);
    }

    public static boolean isLegacyPublicKey(PublicKey pk) {
        if (pk instanceof ECPublicKey) {
            return false;
        }
        return true;
    }

    public static boolean isLegacyPrivateKey(PrivateKey sk) {
        if (sk instanceof ECPrivateKey) {
            return false;
        }
        return true;
    }

    public static boolean isLegacyCryptoRequired() {
        return PublicKeyProtoUtil.isLegacyCryptoRequired() || simulateLegacyCryptoRequired;
    }

    public static void setSimulateLegacyCrypto(boolean forceLegacy) {
        simulateLegacyCryptoRequired = forceLegacy;
    }

    private static byte[] encodePublicKey(PublicKey pk) {
        return PublicKeyProtoUtil.encodePublicKey(pk).toByteArray();
    }

    private static PublicKey parsePublicKey(byte[] keyBytes) throws InvalidKeySpecException {
        try {
            return PublicKeyProtoUtil.parsePublicKey(SecureMessageProto.GenericPublicKey.parseFrom(keyBytes));
        } catch (InvalidProtocolBufferException e) {
            throw new InvalidKeySpecException("Unable to parse GenericPublicKey", e);
        } catch (IllegalArgumentException e2) {
            throw new InvalidKeySpecException("Unable to parse GenericPublicKey", e2);
        }
    }

    static KeyFactory getEcKeyFactory() {
        try {
            return KeyFactory.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static KeyFactory getRsaKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
