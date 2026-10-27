package com.google.security.cryptauth.lib.securemessage;

import com.google.protobuf.ByteString;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class PublicKeyProtoUtil {
    public static final int ANDROID_HONEYCOMB_SDK_INT = 11;
    private static final String DH_ALG = "DH";
    public static final BigInteger DH_G;
    public static final int DH_LEN = 512;
    public static final BigInteger DH_P;
    private static final String EC_ALG = "EC";
    private static final BigInteger EC_P256_A;
    private static final BigInteger EC_P256_B;
    private static final String EC_P256_COMMON_NAME = "secp256r1";
    private static final String EC_P256_OPENSSL_NAME = "prime256v1";
    private static final BigInteger EC_P256_P;
    private static final ECParameterSpec EC_P256_PARAMS;
    private static final int MAX_DH2048_ENCODING_BYTES = 257;
    private static final int MAX_P256_ENCODING_BYTES = 33;
    private static final int MAX_RSA2048_ENCODING_BYTES = 257;
    private static final int RSA2048_MODULUS_BITS = 2048;
    private static final String RSA_ALG = "RSA";
    private static final Boolean IS_LEGACY_CRYPTO_REQUIRED = Boolean.valueOf(determineIfLegacyCryptoRequired());
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");

    private PublicKeyProtoUtil() {
    }

    static {
        EC_P256_PARAMS = isLegacyCryptoRequired() ? null : ((ECPublicKey) generateEcP256KeyPair().getPublic()).getParams();
        EC_P256_P = isLegacyCryptoRequired() ? null : ((ECFieldFp) EC_P256_PARAMS.getCurve().getField()).getP();
        EC_P256_A = isLegacyCryptoRequired() ? null : EC_P256_PARAMS.getCurve().getA();
        EC_P256_B = isLegacyCryptoRequired() ? null : EC_P256_PARAMS.getCurve().getB();
        DH_P = new BigInteger("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);
        DH_G = TWO;
    }

    public static SecureMessageProto.GenericPublicKey encodePublicKey(PublicKey pk) {
        if (pk == null) {
            throw null;
        }
        if (pk instanceof ECPublicKey) {
            return SecureMessageProto.GenericPublicKey.newBuilder().setType(SecureMessageProto.PublicKeyType.EC_P256).setEcP256PublicKey(encodeEcPublicKey(pk)).build();
        }
        if (pk instanceof RSAPublicKey) {
            return SecureMessageProto.GenericPublicKey.newBuilder().setType(SecureMessageProto.PublicKeyType.RSA2048).setRsa2048PublicKey(encodeRsa2048PublicKey(pk)).build();
        }
        if (pk instanceof DHPublicKey) {
            return SecureMessageProto.GenericPublicKey.newBuilder().setType(SecureMessageProto.PublicKeyType.DH2048_MODP).setDh2048PublicKey(encodeDh2048PublicKey(pk)).build();
        }
        throw new IllegalArgumentException("Unsupported PublicKey type");
    }

    public static SecureMessageProto.EcP256PublicKey encodeEcPublicKey(PublicKey pk) {
        ECPublicKey epk = pkToECPublicKey(pk);
        return SecureMessageProto.EcP256PublicKey.newBuilder().setX(extractX(epk)).setY(extractY(epk)).build();
    }

    public static SecureMessageProto.SimpleRsaPublicKey encodeRsa2048PublicKey(PublicKey pk) {
        RSAPublicKey rpk = pkToRSAPublicKey(pk);
        return SecureMessageProto.SimpleRsaPublicKey.newBuilder().setN(ByteString.copyFrom(rpk.getModulus().toByteArray())).setE(rpk.getPublicExponent().intValue()).build();
    }

    public static SecureMessageProto.DhPublicKey encodeDh2048PublicKey(PublicKey pk) {
        DHPublicKey dhpk = pkToDHPublicKey(pk);
        return SecureMessageProto.DhPublicKey.newBuilder().setY(ByteString.copyFrom(dhpk.getY().toByteArray())).build();
    }

    public static PublicKey parsePublicKey(SecureMessageProto.GenericPublicKey gpk) throws InvalidKeySpecException {
        if (!gpk.hasType()) {
            throw new InvalidKeySpecException("GenericPublicKey.type is a required field");
        }
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securemessage$SecureMessageProto$PublicKeyType[gpk.getType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (gpk.hasDh2048PublicKey()) {
                        return parseDh2048PublicKey(gpk.getDh2048PublicKey());
                    }
                } else {
                    throw new InvalidKeySpecException("Unsupported GenericPublicKey type: " + gpk.getType());
                }
            } else if (gpk.hasRsa2048PublicKey()) {
                return parseRsa2048PublicKey(gpk.getRsa2048PublicKey());
            }
        } else if (gpk.hasEcP256PublicKey()) {
            return parseEcPublicKey(gpk.getEcP256PublicKey());
        }
        throw new InvalidKeySpecException("key object is missing for key type: " + gpk.getType());
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securemessage.PublicKeyProtoUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securemessage$SecureMessageProto$PublicKeyType;

        static {
            int[] iArr = new int[SecureMessageProto.PublicKeyType.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securemessage$SecureMessageProto$PublicKeyType = iArr;
            try {
                iArr[SecureMessageProto.PublicKeyType.EC_P256.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securemessage$SecureMessageProto$PublicKeyType[SecureMessageProto.PublicKeyType.RSA2048.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securemessage$SecureMessageProto$PublicKeyType[SecureMessageProto.PublicKeyType.DH2048_MODP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static ECPublicKey parseEcPublicKey(SecureMessageProto.EcP256PublicKey p256pk) throws InvalidKeySpecException {
        if (!p256pk.hasX() || !p256pk.hasY()) {
            throw new InvalidKeySpecException("Key is missing a required coordinate");
        }
        if (isLegacyCryptoRequired()) {
            throw new InvalidKeySpecException("Elliptic Curve keys not supported on this platform");
        }
        byte[] encodedX = p256pk.getX().toByteArray();
        byte[] encodedY = p256pk.getY().toByteArray();
        try {
            validateEcP256CoordinateEncoding(encodedX);
            validateEcP256CoordinateEncoding(encodedY);
            BigInteger wX = new BigInteger(encodedX);
            BigInteger wY = new BigInteger(encodedY);
            validateEcP256CurvePoint(wX, wY);
            return (ECPublicKey) KeyFactory.getInstance(EC_ALG).generatePublic(new ECPublicKeySpec(new ECPoint(wX, wY), EC_P256_PARAMS));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAPublicKey parseRsa2048PublicKey(SecureMessageProto.SimpleRsaPublicKey pk) throws InvalidKeySpecException {
        if (!pk.hasN()) {
            throw new InvalidKeySpecException("required field is missing");
        }
        byte[] encodedN = pk.getN().toByteArray();
        validateSimpleRsaEncoding(encodedN);
        BigInteger n = new BigInteger(encodedN);
        if (n.bitLength() != 2048) {
            throw new InvalidKeySpecException();
        }
        BigInteger e = BigInteger.valueOf(pk.getE());
        try {
            return (RSAPublicKey) KeyFactory.getInstance(RSA_ALG).generatePublic(new RSAPublicKeySpec(n, e));
        } catch (NoSuchAlgorithmException e1) {
            throw new AssertionError(e1);
        }
    }

    public static DHPublicKey parseDh2048PublicKey(SecureMessageProto.DhPublicKey pk) throws InvalidKeySpecException {
        if (!pk.hasY()) {
            throw new InvalidKeySpecException("required field is missing");
        }
        byte[] encodedY = pk.getY().toByteArray();
        validateDhEncoding(encodedY);
        try {
            BigInteger y = new BigInteger(encodedY);
            validateDhGroupElement(y);
            try {
                return (DHPublicKey) KeyFactory.getInstance(DH_ALG).generatePublic(new DHPublicKeySpec(y, DH_P, DH_G));
            } catch (NoSuchAlgorithmException e) {
                throw new AssertionError(e);
            }
        } catch (NumberFormatException e2) {
            throw new InvalidKeySpecException();
        }
    }

    public static KeyPair generateEcP256KeyPair() {
        return getEcKeyGen().generateKeyPair();
    }

    public static KeyPair generateRSA2048KeyPair() {
        return getRsaKeyGen().generateKeyPair();
    }

    public static KeyPair generateDh2048KeyPair() {
        try {
            return getDhKeyGen().generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            DHParameterSpec spec = new DHParameterSpec(DH_P, DH_G);
            BigInteger x = new BigInteger(512, new SecureRandom());
            DHPrivateKey privateKey = new DHPrivateKeyShim(x, spec);
            DHPublicKey publicKey = new DHPublicKeyShim(DH_G.modPow(x, DH_P), spec);
            return new KeyPair(publicKey, privateKey);
        }
    }

    public static byte[] encodeDh2048PrivateKey(DHPrivateKey sk) {
        return sk.getX().toByteArray();
    }

    public static DHPrivateKey parseDh2048PrivateKey(byte[] encodedX) throws InvalidKeySpecException {
        validateDhEncoding(encodedX);
        try {
            BigInteger x = new BigInteger(encodedX);
            validateDhGroupElement(x);
            return new DHPrivateKeyShim(x, new DHParameterSpec(DH_P, DH_G));
        } catch (NumberFormatException e) {
            throw new InvalidKeySpecException();
        }
    }

    private static void validateEcP256CurvePoint(BigInteger x, BigInteger y) throws InvalidKeySpecException {
        if (x.signum() == -1 || y.signum() == -1) {
            throw new InvalidKeySpecException("Point encoding must use only non-negative integers");
        }
        BigInteger p = EC_P256_P;
        if (x.compareTo(p) >= 0 || y.compareTo(p) >= 0) {
            throw new InvalidKeySpecException("Point lies outside of the expected field");
        }
        BigInteger lhs = squareMod(y, p);
        BigInteger rhs = squareMod(x, p).add(EC_P256_A).multiply(x).mod(p).add(EC_P256_B).mod(p);
        if (!lhs.equals(rhs)) {
            throw new InvalidKeySpecException("Point does not lie on the expected curve");
        }
    }

    private static BigInteger squareMod(BigInteger x, BigInteger p) {
        return x.multiply(x).mod(p);
    }

    private static void validateEcP256CoordinateEncoding(byte[] p) throws InvalidKeySpecException {
        if (p.length == 0 || p.length > 33 || (p.length == 33 && p[0] != 0)) {
            throw new InvalidKeySpecException();
        }
    }

    private static void validateSimpleRsaEncoding(byte[] n) throws InvalidKeySpecException {
        if (n.length == 0 || n.length > 257) {
            throw new InvalidKeySpecException();
        }
    }

    private static void validateDhEncoding(byte[] y) throws InvalidKeySpecException {
        if (y.length == 0 || y.length > 257) {
            throw new InvalidKeySpecException();
        }
    }

    private static void validateDhGroupElement(BigInteger y) throws InvalidKeySpecException {
        if (y.compareTo(ONE) < 1 || y.compareTo(DH_P.subtract(ONE)) > -1) {
            throw new InvalidKeySpecException();
        }
    }

    private static ByteString extractY(ECPublicKey epk) {
        return ByteString.copyFrom(epk.getW().getAffineY().toByteArray());
    }

    private static ByteString extractX(ECPublicKey epk) {
        return ByteString.copyFrom(epk.getW().getAffineX().toByteArray());
    }

    private static ECPublicKey pkToECPublicKey(PublicKey pk) {
        if (pk == null) {
            throw null;
        }
        if (!(pk instanceof ECPublicKey)) {
            throw new IllegalArgumentException("Not an EC Public Key");
        }
        return (ECPublicKey) pk;
    }

    private static RSAPublicKey pkToRSAPublicKey(PublicKey pk) {
        if (pk == null) {
            throw null;
        }
        if (!(pk instanceof RSAPublicKey)) {
            throw new IllegalArgumentException("Not an RSA Public Key");
        }
        return (RSAPublicKey) pk;
    }

    private static DHPublicKey pkToDHPublicKey(PublicKey pk) {
        if (pk == null) {
            throw null;
        }
        if (!(pk instanceof DHPublicKey)) {
            throw new IllegalArgumentException("Not a DH Public Key");
        }
        return (DHPublicKey) pk;
    }

    private static KeyPairGenerator getEcKeyGen() {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(EC_ALG);
            try {
                keygen.initialize(new ECGenParameterSpec(EC_P256_OPENSSL_NAME));
                return keygen;
            } catch (InvalidAlgorithmParameterException e) {
                try {
                    keygen.initialize(new ECGenParameterSpec(EC_P256_COMMON_NAME));
                    return keygen;
                } catch (InvalidAlgorithmParameterException e2) {
                    throw new RuntimeException("Unable to find the NIST P-256 curve");
                }
            }
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static KeyPairGenerator getRsaKeyGen() {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_ALG);
            keygen.initialize(2048);
            return keygen;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static KeyPairGenerator getDhKeyGen() throws InvalidAlgorithmParameterException {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(DH_ALG);
            keygen.initialize(new DHParameterSpec(DH_P, DH_G, 512));
            return keygen;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static abstract class DHKeyShim {
        private BigInteger eitherXorY;
        private DHParameterSpec params;

        public DHKeyShim(BigInteger eitherXorY, DHParameterSpec params) {
            this.eitherXorY = eitherXorY;
            this.params = params;
        }

        public DHParameterSpec getParams() {
            return this.params;
        }

        public String getAlgorithm() {
            return PublicKeyProtoUtil.DH_ALG;
        }

        public String getFormat() {
            return null;
        }

        public byte[] getEncoded() {
            return null;
        }

        public BigInteger getX() {
            return this.eitherXorY;
        }

        public BigInteger getY() {
            return this.eitherXorY;
        }
    }

    public static class DHPublicKeyShim extends DHKeyShim implements DHPublicKey {
        public DHPublicKeyShim(BigInteger y, DHParameterSpec params) {
            super(y, params);
        }
    }

    public static class DHPrivateKeyShim extends DHKeyShim implements DHPrivateKey {
        public DHPrivateKeyShim(BigInteger x, DHParameterSpec params) {
            super(x, params);
        }
    }

    public static boolean isLegacyCryptoRequired() {
        return IS_LEGACY_CRYPTO_REQUIRED.booleanValue();
    }

    private static boolean determineIfLegacyCryptoRequired() {
        try {
            getEcKeyGen();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
