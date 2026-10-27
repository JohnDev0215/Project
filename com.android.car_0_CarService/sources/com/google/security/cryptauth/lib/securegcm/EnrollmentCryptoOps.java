package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.SecureGcmProto;
import com.google.security.cryptauth.lib.securegcm.TransportCryptoOps;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import com.google.security.cryptauth.lib.securemessage.PublicKeyProtoUtil;
import com.google.security.cryptauth.lib.securemessage.SecureMessageBuilder;
import com.google.security.cryptauth.lib.securemessage.SecureMessageParser;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes3.dex */
public class EnrollmentCryptoOps {
    private static final String KA_ALG = "ECDH";
    private static final String LEGACY_KA_ALG = "DH";
    private static final CryptoOps.SigType OUTER_SIG_TYPE = CryptoOps.SigType.HMAC_SHA256;
    private static final CryptoOps.EncType OUTER_ENC_TYPE = CryptoOps.EncType.AES_256_CBC;
    private static final CryptoOps.SigType INNER_SIG_TYPE = CryptoOps.SigType.ECDSA_P256_SHA256;
    private static final CryptoOps.SigType LEGACY_INNER_SIG_TYPE = CryptoOps.SigType.RSA2048_SHA256;

    private EnrollmentCryptoOps() {
    }

    public static SecretKey doKeyAgreement(PrivateKey myKey, PublicKey peerKey) throws InvalidKeyException {
        String alg = KA_ALG;
        if (KeyEncoding.isLegacyPrivateKey(myKey)) {
            alg = LEGACY_KA_ALG;
        }
        try {
            KeyAgreement agreement = KeyAgreement.getInstance(alg);
            agreement.init(myKey);
            agreement.doPhase(peerKey, true);
            byte[] agreedKey = agreement.generateSecret();
            return KeyEncoding.parseMasterKey(sha256(agreedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static KeyPair generateEnrollmentKeyAgreementKeyPair(boolean isLegacy) {
        if (isLegacy) {
            return PublicKeyProtoUtil.generateDh2048KeyPair();
        }
        return PublicKeyProtoUtil.generateEcP256KeyPair();
    }

    public static byte[] getMasterKeyHash(SecretKey masterKey) {
        return sha256(masterKey.getEncoded());
    }

    public static byte[] encryptEnrollmentMessage(SecureGcmProto.GcmDeviceInfo enrollmentInfo, SecretKey masterKey, PrivateKey signingKey) throws NoSuchAlgorithmException, InvalidKeyException {
        if (enrollmentInfo == null || masterKey == null || signingKey == null) {
            throw null;
        }
        if (!Arrays.equals(enrollmentInfo.getDeviceMasterKeyHash().toByteArray(), getMasterKeyHash(masterKey))) {
            throw new IllegalArgumentException("DeviceMasterKeyHash not set correctly");
        }
        CryptoOps.SigType sigType = KeyEncoding.isLegacyPrivateKey(signingKey) ? LEGACY_INNER_SIG_TYPE : INNER_SIG_TYPE;
        SecureMessageProto.SecureMessage innerMsg = new SecureMessageBuilder().setVerificationKeyId(enrollmentInfo.getUserPublicKey().toByteArray()).buildSignedCleartextMessage(signingKey, sigType, enrollmentInfo.toByteArray());
        SecureMessageProto.SecureMessage outerMsg = new SecureMessageBuilder().setVerificationKeyId(new byte[0]).setPublicMetadata(SecureGcmProto.GcmMetadata.newBuilder().setType(TransportCryptoOps.PayloadType.ENROLLMENT.getType()).setVersion(1).build().toByteArray()).buildSignCryptedMessage(masterKey, OUTER_SIG_TYPE, masterKey, OUTER_ENC_TYPE, innerMsg.toByteArray());
        return outerMsg.toByteArray();
    }

    public static SecureGcmProto.GcmDeviceInfo decryptEnrollmentMessage(byte[] enrollmentMessage, SecretKey masterKey, boolean isLegacy) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (enrollmentMessage == null || masterKey == null) {
            throw null;
        }
        try {
            SecureMessageProto.SecureMessage outerMsg = SecureMessageProto.SecureMessage.parseFrom(enrollmentMessage);
            SecureMessageProto.HeaderAndBody outerHeaderAndBody = SecureMessageParser.parseSignCryptedMessage(outerMsg, masterKey, OUTER_SIG_TYPE, masterKey, OUTER_ENC_TYPE);
            SecureGcmProto.GcmMetadata outerMetadata = SecureGcmProto.GcmMetadata.parseFrom(outerHeaderAndBody.getHeader().getPublicMetadata());
            SecureMessageProto.SecureMessage innerMsg = SecureMessageProto.SecureMessage.parseFrom(outerHeaderAndBody.getBody());
            byte[] encodedUserPublicKey = SecureMessageParser.getUnverifiedHeader(innerMsg).getVerificationKeyId().toByteArray();
            PublicKey userPublicKey = KeyEncoding.parseUserPublicKey(encodedUserPublicKey);
            CryptoOps.SigType sigType = isLegacy ? LEGACY_INNER_SIG_TYPE : INNER_SIG_TYPE;
            SecureMessageProto.HeaderAndBody innerHeaderAndBody = SecureMessageParser.parseSignedCleartextMessage(innerMsg, userPublicKey, sigType);
            SecureGcmProto.GcmDeviceInfo enrollmentInfo = SecureGcmProto.GcmDeviceInfo.parseFrom(innerHeaderAndBody.getBody());
            boolean verified = outerMetadata.getType() == TransportCryptoOps.PayloadType.ENROLLMENT.getType() && outerMetadata.getVersion() <= 1 && outerHeaderAndBody.getHeader().getVerificationKeyId().isEmpty() && innerHeaderAndBody.getHeader().getPublicMetadata().isEmpty() && Arrays.equals(encodedUserPublicKey, enrollmentInfo.getUserPublicKey().toByteArray()) && Arrays.equals(getMasterKeyHash(masterKey), enrollmentInfo.getDeviceMasterKeyHash().toByteArray());
            if (verified) {
                return enrollmentInfo;
            }
            throw new SignatureException();
        } catch (InvalidProtocolBufferException e) {
            throw new SignatureException(e);
        } catch (InvalidKeySpecException e2) {
            throw new SignatureException(e2);
        }
    }

    static byte[] sha256(byte[] input) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return sha256.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
