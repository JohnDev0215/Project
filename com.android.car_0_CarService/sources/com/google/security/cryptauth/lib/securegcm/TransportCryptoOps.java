package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.SecureGcmProto;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import com.google.security.cryptauth.lib.securemessage.SecureMessageBuilder;
import com.google.security.cryptauth.lib.securemessage.SecureMessageParser;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes3.dex */
public class TransportCryptoOps {
    private TransportCryptoOps() {
    }

    public enum PayloadType {
        ENROLLMENT(SecureGcmProto.Type.ENROLLMENT),
        TICKLE(SecureGcmProto.Type.TICKLE),
        TX_REQUEST(SecureGcmProto.Type.TX_REQUEST),
        TX_REPLY(SecureGcmProto.Type.TX_REPLY),
        TX_SYNC_REQUEST(SecureGcmProto.Type.TX_SYNC_REQUEST),
        TX_SYNC_RESPONSE(SecureGcmProto.Type.TX_SYNC_RESPONSE),
        TX_PING(SecureGcmProto.Type.TX_PING),
        DEVICE_INFO_UPDATE(SecureGcmProto.Type.DEVICE_INFO_UPDATE),
        TX_CANCEL_REQUEST(SecureGcmProto.Type.TX_CANCEL_REQUEST),
        LOGIN_NOTIFICATION(SecureGcmProto.Type.LOGIN_NOTIFICATION),
        PROXIMITYAUTH_PAIRING(SecureGcmProto.Type.PROXIMITYAUTH_PAIRING),
        GCMV1_IDENTITY_ASSERTION(SecureGcmProto.Type.GCMV1_IDENTITY_ASSERTION),
        DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD(SecureGcmProto.Type.DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD),
        DEVICE_TO_DEVICE_MESSAGE(SecureGcmProto.Type.DEVICE_TO_DEVICE_MESSAGE),
        DEVICE_PROXIMITY_CALLBACK(SecureGcmProto.Type.DEVICE_PROXIMITY_CALLBACK),
        UNLOCK_KEY_SIGNED_CHALLENGE(SecureGcmProto.Type.UNLOCK_KEY_SIGNED_CHALLENGE);

        private final SecureGcmProto.Type type;

        PayloadType(SecureGcmProto.Type type) {
            this.type = type;
        }

        public SecureGcmProto.Type getType() {
            return this.type;
        }

        public static PayloadType valueOf(SecureGcmProto.Type type) {
            return valueOf(type.getNumber());
        }

        public static PayloadType valueOf(int type) {
            for (PayloadType payloadType : values()) {
                if (payloadType.getType().getNumber() == type) {
                    return payloadType;
                }
            }
            throw new IllegalArgumentException("Unsupported payload type: " + type);
        }
    }

    public static class Payload {
        private final byte[] message;
        private final PayloadType payloadType;

        public Payload(PayloadType payloadType, byte[] message) {
            if (payloadType == null || message == null) {
                throw null;
            }
            this.payloadType = payloadType;
            this.message = message;
        }

        public PayloadType getPayloadType() {
            return this.payloadType;
        }

        public byte[] getMessage() {
            return this.message;
        }
    }

    public static byte[] signcryptServerMessage(Payload payload, SecretKey masterKey, byte[] keyHandle) throws NoSuchAlgorithmException, InvalidKeyException {
        if (payload == null || masterKey == null || keyHandle == null) {
            throw null;
        }
        return new SecureMessageBuilder().setVerificationKeyId(keyHandle).setPublicMetadata(SecureGcmProto.GcmMetadata.newBuilder().setType(payload.getPayloadType().getType()).setVersion(1).build().toByteArray()).buildSignCryptedMessage(masterKey, CryptoOps.SigType.HMAC_SHA256, masterKey, CryptoOps.EncType.AES_256_CBC, payload.getMessage()).toByteArray();
    }

    public static byte[] getKeyHandleFor(byte[] signcryptedServerMessage) throws InvalidProtocolBufferException {
        if (signcryptedServerMessage == null) {
            throw null;
        }
        SecureMessageProto.SecureMessage secmsg = SecureMessageProto.SecureMessage.parseFrom(signcryptedServerMessage);
        return SecureMessageParser.getUnverifiedHeader(secmsg).getVerificationKeyId().toByteArray();
    }

    public static Payload verifydecryptServerMessage(byte[] signcryptedServerMessage, SecretKey masterKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        if (signcryptedServerMessage == null || masterKey == null) {
            throw null;
        }
        try {
            SecureMessageProto.SecureMessage secmsg = SecureMessageProto.SecureMessage.parseFrom(signcryptedServerMessage);
            SecureMessageProto.HeaderAndBody parsed = SecureMessageParser.parseSignCryptedMessage(secmsg, masterKey, CryptoOps.SigType.HMAC_SHA256, masterKey, CryptoOps.EncType.AES_256_CBC);
            SecureGcmProto.GcmMetadata metadata = SecureGcmProto.GcmMetadata.parseFrom(parsed.getHeader().getPublicMetadata());
            if (metadata.getVersion() > 1) {
                throw new SignatureException("Unsupported protocol version");
            }
            return new Payload(PayloadType.valueOf(metadata.getType()), parsed.getBody().toByteArray());
        } catch (InvalidProtocolBufferException | IllegalArgumentException e) {
            throw new SignatureException(e);
        }
    }

    public static byte[] signcryptClientMessage(Payload payload, KeyPair userKeyPair, SecretKey masterKey) throws NoSuchAlgorithmException, InvalidKeyException {
        if (payload == null || masterKey == null) {
            throw null;
        }
        PublicKey userPublicKey = userKeyPair.getPublic();
        PrivateKey userPrivateKey = userKeyPair.getPrivate();
        return new SecureMessageBuilder().setVerificationKeyId(KeyEncoding.encodeUserPublicKey(userPublicKey)).setPublicMetadata(SecureGcmProto.GcmMetadata.newBuilder().setType(payload.getPayloadType().getType()).setVersion(1).build().toByteArray()).buildSignCryptedMessage(userPrivateKey, getSigTypeFor(userPublicKey), masterKey, CryptoOps.EncType.AES_256_CBC, payload.getMessage()).toByteArray();
    }

    public static Payload verifydecryptClientMessage(byte[] signcryptedClientMessage, PublicKey userPublicKey, SecretKey masterKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        if (signcryptedClientMessage == null || masterKey == null) {
            throw null;
        }
        try {
            SecureMessageProto.SecureMessage secmsg = SecureMessageProto.SecureMessage.parseFrom(signcryptedClientMessage);
            SecureMessageProto.HeaderAndBody parsed = SecureMessageParser.parseSignCryptedMessage(secmsg, userPublicKey, getSigTypeFor(userPublicKey), masterKey, CryptoOps.EncType.AES_256_CBC);
            SecureGcmProto.GcmMetadata metadata = SecureGcmProto.GcmMetadata.parseFrom(parsed.getHeader().getPublicMetadata());
            if (metadata.getVersion() > 1) {
                throw new SignatureException("Unsupported protocol version");
            }
            return new Payload(PayloadType.valueOf(metadata.getType()), parsed.getBody().toByteArray());
        } catch (InvalidProtocolBufferException | IllegalArgumentException e) {
            throw new SignatureException(e);
        }
    }

    public static byte[] getEncodedUserPublicKeyFor(byte[] signcryptedClientMessage) throws InvalidProtocolBufferException {
        if (signcryptedClientMessage == null) {
            throw null;
        }
        SecureMessageProto.SecureMessage secmsg = SecureMessageProto.SecureMessage.parseFrom(signcryptedClientMessage);
        return SecureMessageParser.getUnverifiedHeader(secmsg).getVerificationKeyId().toByteArray();
    }

    private static CryptoOps.SigType getSigTypeFor(PublicKey userPublicKey) throws InvalidKeyException {
        if (userPublicKey instanceof ECPublicKey) {
            return CryptoOps.SigType.ECDSA_P256_SHA256;
        }
        if (userPublicKey instanceof RSAPublicKey) {
            return CryptoOps.SigType.RSA2048_SHA256;
        }
        throw new InvalidKeyException("Unsupported key type");
    }
}
