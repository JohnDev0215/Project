package com.google.security.cryptauth.lib.securemessage;

import com.google.protobuf.ByteString;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class SecureMessageBuilder {
    private byte[] associatedData;
    private ByteString decryptionKeyId;
    private ByteString publicMetadata;
    private SecureRandom rng;
    private ByteString verificationKeyId;

    public SecureMessageBuilder() {
        reset();
        this.rng = new SecureRandom();
    }

    public SecureMessageBuilder reset() {
        this.publicMetadata = null;
        this.verificationKeyId = null;
        this.decryptionKeyId = null;
        this.associatedData = null;
        return this;
    }

    public SecureMessageBuilder setPublicMetadata(byte[] publicMetadata) {
        this.publicMetadata = ByteString.copyFrom(publicMetadata);
        return this;
    }

    public SecureMessageBuilder setVerificationKeyId(byte[] verificationKeyId) {
        this.verificationKeyId = ByteString.copyFrom(verificationKeyId);
        return this;
    }

    public SecureMessageBuilder setDecryptionKeyId(byte[] decryptionKeyId) {
        this.decryptionKeyId = ByteString.copyFrom(decryptionKeyId);
        return this;
    }

    public SecureMessageBuilder setAssociatedData(@Nullable byte[] associatedData) {
        this.associatedData = associatedData;
        return this;
    }

    SecureMessageBuilder setRng(SecureRandom rng) {
        this.rng = rng;
        return this;
    }

    public SecureMessageProto.SecureMessage buildSignedCleartextMessage(Key signingKey, CryptoOps.SigType sigType, byte[] body) throws NoSuchAlgorithmException, InvalidKeyException {
        if (signingKey == null || sigType == null || body == null) {
            throw null;
        }
        if (this.decryptionKeyId != null) {
            throw new IllegalStateException("Cannot set decryptionKeyId for a cleartext message");
        }
        byte[] headerAndBody = serializeHeaderAndBody(buildHeader(sigType, CryptoOps.EncType.NONE, null).toByteArray(), body);
        return createSignedResult(signingKey, sigType, headerAndBody, this.associatedData);
    }

    public SecureMessageProto.SecureMessage buildSignCryptedMessage(Key signingKey, CryptoOps.SigType sigType, Key encryptionKey, CryptoOps.EncType encType, byte[] body) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] taggedBody;
        byte[] associatedDataToBeSigned;
        if (signingKey == null || sigType == null || encryptionKey == null || encType == null || body == null) {
            throw null;
        }
        if (encType == CryptoOps.EncType.NONE) {
            throw new IllegalArgumentException(encType + " not supported for encrypted messages");
        }
        if (sigType.isPublicKeyScheme() && this.verificationKeyId == null) {
            throw new IllegalStateException("Must set a verificationKeyId when using public key signature with encryption");
        }
        byte[] iv = CryptoOps.generateIv(encType, this.rng);
        byte[] header = buildHeader(sigType, encType, iv).toByteArray();
        if (taggedPlaintextRequired(signingKey, sigType, encryptionKey)) {
            taggedBody = CryptoOps.concat(CryptoOps.digest(CryptoOps.concat(header, this.associatedData)), body);
            associatedDataToBeSigned = null;
        } else {
            taggedBody = body;
            associatedDataToBeSigned = this.associatedData;
        }
        byte[] encryptedBody = CryptoOps.encrypt(encryptionKey, encType, this.rng, iv, taggedBody);
        byte[] headerAndBody = serializeHeaderAndBody(header, encryptedBody);
        return createSignedResult(signingKey, sigType, headerAndBody, associatedDataToBeSigned);
    }

    static boolean taggedPlaintextRequired(Key signingKey, CryptoOps.SigType sigType, Key encryptionKey) {
        return sigType.isPublicKeyScheme() || !Arrays.equals(signingKey.getEncoded(), encryptionKey.getEncoded());
    }

    private SecureMessageProto.Header buildHeader(CryptoOps.SigType sigType, CryptoOps.EncType encType, byte[] iv) {
        SecureMessageProto.Header.Builder result = SecureMessageProto.Header.newBuilder().setSignatureScheme(sigType.getSigScheme()).setEncryptionScheme(encType.getEncScheme());
        ByteString byteString = this.verificationKeyId;
        if (byteString != null) {
            result.setVerificationKeyId(byteString);
        }
        ByteString byteString2 = this.decryptionKeyId;
        if (byteString2 != null) {
            result.setDecryptionKeyId(byteString2);
        }
        ByteString byteString3 = this.publicMetadata;
        if (byteString3 != null) {
            result.setPublicMetadata(byteString3);
        }
        byte[] bArr = this.associatedData;
        if (bArr != null) {
            result.setAssociatedDataLength(bArr.length);
        }
        if (iv != null) {
            result.setIv(ByteString.copyFrom(iv));
        }
        return result.build();
    }

    private byte[] serializeHeaderAndBody(byte[] header, byte[] body) {
        return SecureMessageProto.HeaderAndBodyInternal.newBuilder().setHeader(ByteString.copyFrom(header)).setBody(ByteString.copyFrom(body)).build().toByteArray();
    }

    private SecureMessageProto.SecureMessage createSignedResult(Key signingKey, CryptoOps.SigType sigType, byte[] headerAndBody, @Nullable byte[] associatedData) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] sig = CryptoOps.sign(sigType, signingKey, this.rng, CryptoOps.concat(headerAndBody, associatedData));
        return SecureMessageProto.SecureMessage.newBuilder().setHeaderAndBody(ByteString.copyFrom(headerAndBody)).setSignature(ByteString.copyFrom(sig)).build();
    }
}
