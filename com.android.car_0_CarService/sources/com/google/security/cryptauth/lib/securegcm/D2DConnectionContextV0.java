package com.google.security.cryptauth.lib.securegcm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes3.dex */
public class D2DConnectionContextV0 extends D2DConnectionContext {
    public static final int PROTOCOL_VERSION = 0;
    private int sequenceNumber;
    private final SecretKey sharedKey;

    D2DConnectionContextV0(SecretKey sharedKey, int initialSequenceNumber) {
        super(0);
        this.sharedKey = sharedKey;
        this.sequenceNumber = initialSequenceNumber;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    public byte[] getSessionUnique() throws NoSuchAlgorithmException {
        if (this.sharedKey == null) {
            throw new IllegalStateException("Connection has not been correctly initialized; shared key is null");
        }
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(D2DCryptoOps.SALT);
        return md.digest(this.sharedKey.getEncoded());
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    protected void incrementSequenceNumberForEncoding() {
        this.sequenceNumber++;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    protected void incrementSequenceNumberForDecoding() {
        this.sequenceNumber++;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    int getSequenceNumberForEncoding() {
        return this.sequenceNumber;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    int getSequenceNumberForDecoding() {
        return this.sequenceNumber;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    SecretKey getEncodeKey() {
        return this.sharedKey;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    SecretKey getDecodeKey() {
        return this.sharedKey;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    public byte[] saveSession() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            bytes.write(0);
            bytes.write(signedIntToBytes(this.sequenceNumber));
            bytes.write(this.sharedKey.getEncoded());
            return bytes.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
