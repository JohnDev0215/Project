package com.google.security.cryptauth.lib.securemessage;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/* JADX INFO: loaded from: classes3.dex */
public class SecureMessageParser {
    private SecureMessageParser() {
    }

    public static SecureMessageProto.Header getUnverifiedHeader(SecureMessageProto.SecureMessage secmsg) throws InvalidProtocolBufferException {
        if (!secmsg.hasHeaderAndBody()) {
            throw new InvalidProtocolBufferException("Missing header and body");
        }
        if (!SecureMessageProto.HeaderAndBody.parseFrom(secmsg.getHeaderAndBody()).hasHeader()) {
            throw new InvalidProtocolBufferException("Missing header");
        }
        SecureMessageProto.Header result = SecureMessageProto.HeaderAndBody.parseFrom(secmsg.getHeaderAndBody()).getHeader();
        if (!result.hasSignatureScheme()) {
            throw new InvalidProtocolBufferException("Missing header field(s)");
        }
        try {
            CryptoOps.SigType.valueOf(result.getSignatureScheme());
            if (result.hasEncryptionScheme()) {
                try {
                    CryptoOps.EncType.valueOf(result.getEncryptionScheme());
                } catch (IllegalArgumentException e) {
                    throw new InvalidProtocolBufferException("Corrupt/unsupported EncryptionScheme");
                }
            }
            return result;
        } catch (IllegalArgumentException e2) {
            throw new InvalidProtocolBufferException("Corrupt/unsupported SignatureScheme");
        }
    }

    public static SecureMessageProto.HeaderAndBody parseSignedCleartextMessage(SecureMessageProto.SecureMessage secmsg, Key verificationKey, CryptoOps.SigType sigType) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return parseSignedCleartextMessage(secmsg, verificationKey, sigType, null);
    }

    public static SecureMessageProto.HeaderAndBody parseSignedCleartextMessage(SecureMessageProto.SecureMessage secmsg, Key verificationKey, CryptoOps.SigType sigType, @Nullable byte[] associatedData) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (secmsg == null || verificationKey == null || sigType == null) {
            throw null;
        }
        return verifyHeaderAndBody(secmsg, verificationKey, sigType, CryptoOps.EncType.NONE, associatedData, false);
    }

    public static SecureMessageProto.HeaderAndBody parseSignCryptedMessage(SecureMessageProto.SecureMessage secmsg, Key verificationKey, CryptoOps.SigType sigType, Key decryptionKey, CryptoOps.EncType encType) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return parseSignCryptedMessage(secmsg, verificationKey, sigType, decryptionKey, encType, null);
    }

    public static SecureMessageProto.HeaderAndBody parseSignCryptedMessage(SecureMessageProto.SecureMessage secmsg, Key verificationKey, CryptoOps.SigType sigType, Key decryptionKey, CryptoOps.EncType encType, @Nullable byte[] associatedData) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (secmsg == null || verificationKey == null || sigType == null || decryptionKey == null || encType == null) {
            throw null;
        }
        if (encType == CryptoOps.EncType.NONE) {
            throw new SignatureException("Not a signcrypted message");
        }
        boolean tagRequired = SecureMessageBuilder.taggedPlaintextRequired(verificationKey, sigType, decryptionKey);
        SecureMessageProto.HeaderAndBody headerAndEncryptedBody = verifyHeaderAndBody(secmsg, verificationKey, sigType, encType, associatedData, tagRequired);
        SecureMessageProto.Header header = headerAndEncryptedBody.getHeader();
        if (!header.hasIv()) {
            throw new SignatureException();
        }
        try {
            byte[] rawDecryptedBody = CryptoOps.decrypt(decryptionKey, encType, header.getIv().toByteArray(), headerAndEncryptedBody.getBody().toByteArray());
            if (!tagRequired) {
                return SecureMessageProto.HeaderAndBody.newBuilder(headerAndEncryptedBody).setBody(ByteString.copyFrom(rawDecryptedBody)).build();
            }
            try {
                byte[] headerBytes = SecureMessageProto.HeaderAndBodyInternal.parseFrom(secmsg.getHeaderAndBody()).getHeader().toByteArray();
                boolean verifiedBinding = false;
                byte[] expectedTag = CryptoOps.digest(CryptoOps.concat(headerBytes, associatedData));
                if (rawDecryptedBody.length >= 20) {
                    byte[] actualTag = CryptoOps.subarray(rawDecryptedBody, 0, 20);
                    if (CryptoOps.constantTimeArrayEquals(actualTag, expectedTag)) {
                        verifiedBinding = true;
                    }
                }
                if (!verifiedBinding) {
                    throw new SignatureException();
                }
                int bodyLen = rawDecryptedBody.length - 20;
                return SecureMessageProto.HeaderAndBody.newBuilder(headerAndEncryptedBody).setBody(ByteString.copyFrom(rawDecryptedBody, 20, bodyLen)).build();
            } catch (InvalidProtocolBufferException e) {
                throw new SignatureException(e);
            }
        } catch (InvalidAlgorithmParameterException e2) {
            throw new SignatureException();
        } catch (BadPaddingException e3) {
            throw new SignatureException();
        } catch (IllegalBlockSizeException e4) {
            throw new SignatureException();
        }
    }

    private static SecureMessageProto.HeaderAndBody verifyHeaderAndBody(SecureMessageProto.SecureMessage secmsg, Key verificationKey, CryptoOps.SigType sigType, CryptoOps.EncType encType, @Nullable byte[] associatedData, boolean suppressAssociatedData) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        boolean verified;
        if (!secmsg.hasHeaderAndBody() || !secmsg.hasSignature()) {
            throw new SignatureException("Signature failed verification");
        }
        byte[] signature = secmsg.getSignature().toByteArray();
        byte[] data = secmsg.getHeaderAndBody().toByteArray();
        byte[] signedData = suppressAssociatedData ? data : CryptoOps.concat(data, associatedData);
        boolean verified2 = CryptoOps.verify(verificationKey, sigType, signature, signedData);
        SecureMessageProto.HeaderAndBody result = null;
        try {
            result = SecureMessageProto.HeaderAndBody.parseFrom(secmsg.getHeaderAndBody());
        } catch (InvalidProtocolBufferException e) {
            verified = false;
        }
        if (!result.hasHeader() || !result.hasBody()) {
            throw new SignatureException("Signature failed verification");
        }
        boolean z = true;
        boolean verified3 = verified2 & (result.getHeader().getSignatureScheme() == sigType.getSigScheme()) & (result.getHeader().getEncryptionScheme() == encType.getEncScheme()) & ((encType == CryptoOps.EncType.NONE && result.getHeader().hasDecryptionKeyId()) ? false : true) & (encType == CryptoOps.EncType.NONE || !sigType.isPublicKeyScheme() || result.getHeader().hasVerificationKeyId());
        int associatedDataLength = associatedData == null ? 0 : associatedData.length;
        if (result.getHeader().getAssociatedDataLength() != associatedDataLength) {
            z = false;
        }
        verified = verified3 & z;
        if (verified) {
            return result;
        }
        throw new SignatureException("Signature failed verification");
    }
}
