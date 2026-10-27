package com.amazonaws.mobileconnectors.iot;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;

/* JADX INFO: loaded from: classes5.dex */
final class RSA {
    private static final String RSA = "RSA";

    private RSA() {
    }

    public static PrivateKey privateKeyFromPKCS1(byte[] bArr) throws InvalidKeySpecException {
        try {
            return KeyFactory.getInstance(RSA).generatePrivate(newRSAPrivateCrtKeySpec(bArr));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private static RSAPrivateCrtKeySpec newRSAPrivateCrtKeySpec(byte[] bArr) throws IOException {
        Asn1Object asn1Object = new DerParser(bArr).read();
        if (asn1Object.getType() != 16) {
            throw new IllegalArgumentException("Invalid DER: not a sequence");
        }
        DerParser parser = asn1Object.getParser();
        parser.read();
        return new RSAPrivateCrtKeySpec(parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger());
    }
}
