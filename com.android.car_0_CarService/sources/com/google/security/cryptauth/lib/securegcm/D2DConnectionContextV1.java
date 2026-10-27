package com.google.security.cryptauth.lib.securegcm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes3.dex */
public class D2DConnectionContextV1 extends D2DConnectionContext {
    public static final int PROTOCOL_VERSION = 1;
    private final SecretKey decodeKey;
    private int decodeSequenceNumber;
    private final SecretKey encodeKey;
    private int encodeSequenceNumber;

    D2DConnectionContextV1(SecretKey encodeKey, SecretKey decodeKey, int initialEncodeSequenceNumber, int initialDecodeSequenceNumber) {
        super(1);
        this.encodeKey = encodeKey;
        this.decodeKey = decodeKey;
        this.encodeSequenceNumber = initialEncodeSequenceNumber;
        this.decodeSequenceNumber = initialDecodeSequenceNumber;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    public byte[] getSessionUnique() throws NoSuchAlgorithmException {
        SecretKey secretKey = this.encodeKey;
        if (secretKey == null || this.decodeKey == null) {
            throw new IllegalStateException("Connection has not been correctly initialized; encode key or decode key is null");
        }
        byte[] encodeKeyBytes = secretKey.getEncoded();
        byte[] decodeKeyBytes = this.decodeKey.getEncoded();
        int encodeKeyHash = Arrays.hashCode(encodeKeyBytes);
        int decodeKeyHash = Arrays.hashCode(decodeKeyBytes);
        byte[] firstKeyBytes = encodeKeyHash < decodeKeyHash ? encodeKeyBytes : decodeKeyBytes;
        byte[] secondKeyBytes = firstKeyBytes == encodeKeyBytes ? decodeKeyBytes : encodeKeyBytes;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(D2DCryptoOps.SALT);
        md.update(firstKeyBytes);
        md.update(secondKeyBytes);
        return md.digest();
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    protected void incrementSequenceNumberForEncoding() {
        this.encodeSequenceNumber++;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    protected void incrementSequenceNumberForDecoding() {
        this.decodeSequenceNumber++;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    int getSequenceNumberForEncoding() {
        return this.encodeSequenceNumber;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    int getSequenceNumberForDecoding() {
        return this.decodeSequenceNumber;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    SecretKey getEncodeKey() {
        return this.encodeKey;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    SecretKey getDecodeKey() {
        return this.decodeKey;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DConnectionContext
    public byte[] saveSession() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            bytes.write(1);
            bytes.write(signedIntToBytes(this.encodeSequenceNumber));
            bytes.write(signedIntToBytes(this.decodeSequenceNumber));
            bytes.write(this.encodeKey.getEncoded());
            bytes.write(this.decodeKey.getEncoded());
            return bytes.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
