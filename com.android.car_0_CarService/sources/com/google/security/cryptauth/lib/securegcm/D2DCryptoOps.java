package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto;
import com.google.security.cryptauth.lib.securegcm.SecureGcmProto;
import com.google.security.cryptauth.lib.securegcm.TransportCryptoOps;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import com.google.security.cryptauth.lib.securemessage.PublicKeyProtoUtil;
import com.google.security.cryptauth.lib.securemessage.SecureMessageBuilder;
import com.google.security.cryptauth.lib.securemessage.SecureMessageParser;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes3.dex */
class D2DCryptoOps {
    static final byte[] SALT = {-126, -86, 85, -96, -45, -105, -8, -125, 70, -54, 28, -18, -115, 57, 9, -71, 95, 19, -6, 125, -21, 29, 74, -77, -125, 118, -72, 37, 109, -88, 85, 16};

    private D2DCryptoOps() {
    }

    static byte[] signcryptMessageAndResponderHello(TransportCryptoOps.Payload payload, SecretKey sharedKey, PublicKey publicDhKey, int protocolVersion) throws NoSuchAlgorithmException, InvalidKeyException {
        DeviceToDeviceMessagesProto.ResponderHello.Builder responderHello = DeviceToDeviceMessagesProto.ResponderHello.newBuilder();
        responderHello.setPublicDhKey(PublicKeyProtoUtil.encodePublicKey(publicDhKey));
        responderHello.setProtocolVersion(protocolVersion);
        return signcryptPayload(payload, sharedKey, responderHello.build().toByteArray());
    }

    static byte[] signcryptPayload(TransportCryptoOps.Payload payload, SecretKey masterKey) throws NoSuchAlgorithmException, InvalidKeyException {
        return signcryptPayload(payload, masterKey, null);
    }

    static byte[] signcryptPayload(TransportCryptoOps.Payload payload, SecretKey masterKey, @Nullable byte[] responderHello) throws NoSuchAlgorithmException, InvalidKeyException {
        if (payload == null || masterKey == null) {
            throw null;
        }
        SecureMessageBuilder secureMessageBuilder = new SecureMessageBuilder().setPublicMetadata(SecureGcmProto.GcmMetadata.newBuilder().setType(payload.getPayloadType().getType()).setVersion(1).build().toByteArray());
        if (responderHello != null) {
            secureMessageBuilder.setDecryptionKeyId(responderHello);
        }
        return secureMessageBuilder.buildSignCryptedMessage(masterKey, CryptoOps.SigType.HMAC_SHA256, masterKey, CryptoOps.EncType.AES_256_CBC, payload.getMessage()).toByteArray();
    }

    static DeviceToDeviceMessagesProto.ResponderHello parseAndValidateResponderHello(byte[] signcryptedMessageFromResponder) throws InvalidProtocolBufferException {
        if (signcryptedMessageFromResponder == null) {
            throw null;
        }
        SecureMessageProto.SecureMessage secmsg = SecureMessageProto.SecureMessage.parseFrom(signcryptedMessageFromResponder);
        SecureMessageProto.Header messageHeader = SecureMessageParser.getUnverifiedHeader(secmsg);
        if (!messageHeader.hasDecryptionKeyId()) {
            throw new InvalidProtocolBufferException("Missing decryption key id");
        }
        byte[] encodedResponderHello = messageHeader.getDecryptionKeyId().toByteArray();
        DeviceToDeviceMessagesProto.ResponderHello responderHello = DeviceToDeviceMessagesProto.ResponderHello.parseFrom(encodedResponderHello);
        if (!responderHello.hasPublicDhKey()) {
            throw new InvalidProtocolBufferException("Missing public key in responder hello");
        }
        return responderHello;
    }

    static TransportCryptoOps.Payload verifydecryptPayload(byte[] signcryptedMessage, SecretKey masterKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (signcryptedMessage == null || masterKey == null) {
            throw null;
        }
        try {
            SecureMessageProto.SecureMessage secmsg = SecureMessageProto.SecureMessage.parseFrom(signcryptedMessage);
            SecureMessageProto.HeaderAndBody parsed = SecureMessageParser.parseSignCryptedMessage(secmsg, masterKey, CryptoOps.SigType.HMAC_SHA256, masterKey, CryptoOps.EncType.AES_256_CBC);
            if (!parsed.getHeader().hasPublicMetadata()) {
                throw new SignatureException("missing metadata");
            }
            SecureGcmProto.GcmMetadata metadata = SecureGcmProto.GcmMetadata.parseFrom(parsed.getHeader().getPublicMetadata());
            if (metadata.getVersion() > 1) {
                throw new SignatureException("Unsupported protocol version");
            }
            return new TransportCryptoOps.Payload(TransportCryptoOps.PayloadType.valueOf(metadata.getType()), parsed.getBody().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new SignatureException(e);
        } catch (IllegalArgumentException e2) {
            throw new SignatureException(e2);
        }
    }

    static SecretKey deriveSharedKeyFromGenericPublicKey(PrivateKey ourPrivateKey, SecureMessageProto.GenericPublicKey theirGenericPublicKey) throws SignatureException {
        try {
            PublicKey theirPublicKey = PublicKeyProtoUtil.parsePublicKey(theirGenericPublicKey);
            return EnrollmentCryptoOps.doKeyAgreement(ourPrivateKey, theirPublicKey);
        } catch (InvalidKeyException e) {
            throw new SignatureException(e);
        } catch (InvalidKeySpecException e2) {
            throw new SignatureException(e2);
        }
    }

    static SecretKey deriveNewKeyForPurpose(SecretKey masterKey, String purpose) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] info = purpose.getBytes();
        return KeyEncoding.parseMasterKey(CryptoOps.hkdf(masterKey, SALT, info));
    }

    static DeviceToDeviceMessagesProto.DeviceToDeviceMessage decryptResponderHelloMessage(SecretKey sharedKey, byte[] responderHelloAndPayload) throws SignatureException {
        try {
            TransportCryptoOps.Payload payload = verifydecryptPayload(responderHelloAndPayload, sharedKey);
            if (!TransportCryptoOps.PayloadType.DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD.equals(payload.getPayloadType())) {
                throw new SignatureException("wrong message type in responder hello");
            }
            return DeviceToDeviceMessagesProto.DeviceToDeviceMessage.parseFrom(payload.getMessage());
        } catch (InvalidProtocolBufferException e) {
            throw new SignatureException(e);
        } catch (InvalidKeyException e2) {
            throw new SignatureException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new SignatureException(e3);
        }
    }
}
