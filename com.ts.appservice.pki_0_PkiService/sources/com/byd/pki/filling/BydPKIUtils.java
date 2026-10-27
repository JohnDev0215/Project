package com.byd.pki.filling;

import a.a.a.a.a;
import a.a.a.a.b;
import a.a.a.a.c;
import a.a.a.a.g;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BydPKIUtils {
    public static byte[] decryptAesKeyTee(String str, byte[] bArr, String str2) {
        return a.e(str, bArr, str2);
    }

    public static byte[] eccDecryptStrTee(byte[] bArr, byte[] bArr2, String str) {
        return c.b(bArr, bArr2, str);
    }

    public static byte[] eccEnCodeStr(String str, PublicKey publicKey) {
        return c.a(str, publicKey);
    }

    public static byte[] encryptStrRSA(String str, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        if (x509Certificate == null) {
            return null;
        }
        cipher.init(1, x509Certificate.getPublicKey());
        return cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
    }

    public static CsrP12Info generateECC256Csr(String str) {
        return b.a(str);
    }

    public static CsrP12Info generateRsa2048Csr(String str) {
        return b.b(str);
    }

    public static byte[] sign(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bArrDecode = Base64.decode(str2);
        Security.addProvider(new BouncyCastleProvider());
        PrivateKey privateKeyGeneratePrivate = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrDecode));
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKeyGeneratePrivate);
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }

    public static byte[] sign(byte[] bArr, String str) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bArrDecode = Base64.decode(str);
        Security.addProvider(new BouncyCastleProvider());
        PrivateKey privateKeyGeneratePrivate = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrDecode));
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKeyGeneratePrivate);
        signature.update(bArr);
        return signature.sign();
    }

    public static CertP12Info storeCert(String str, String str2, String str3) {
        return g.f(str, str2, str3);
    }

    public static CertP12Info storeDeviceRSAKey(String str, String str2) {
        return g.a(str, str2);
    }

    public static String translateAesKey(String str, String str2, String str3) {
        return a.a(str, str2, str3);
    }

    public static String translateAesKey(byte[] bArr) {
        return a.b(bArr);
    }

    public static boolean verifyCertTee(byte[] bArr, String str, byte[] bArr2, String str2) {
        return a.c(bArr, str, bArr2, str2);
    }
}
