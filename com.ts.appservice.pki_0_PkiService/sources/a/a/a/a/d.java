package a.a.a.a;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.BitSet;
import javax.crypto.KeyAgreement;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static final Logger c = LoggerFactory.getLogger((Class<?>) d.class);
    public static Provider d = new BouncyCastleProvider();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f1a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PublicKey f2b;

    public static KeyPair a() {
        KeyPair keyPairA = new f(d).a("prime256v1");
        System.out.println("generateECCKey finish ");
        return keyPairA;
    }

    public static byte[] d(String str, PrivateKey privateKey) {
        return new d().e(privateKey, Base64.decode(str));
    }

    public void b(KeyPair keyPair, PublicKey publicKey) {
        if (ObjectUtils.isEmpty(keyPair) || ObjectUtils.isEmpty(publicKey)) {
            throw new InvalidParameterException("Input key is empty.");
        }
        this.f2b = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", d);
        Logger logger = c;
        logger.trace("temp sk: {}", Hex.encodeHexString(privateKey.getEncoded()));
        keyAgreement.init(privateKey);
        logger.trace("user pk: {}", Hex.encodeHexString(publicKey.getEncoded()));
        keyAgreement.doPhase(publicKey, true);
        byte[] bArrGenerateSecret = keyAgreement.generateSecret();
        this.f1a = bArrGenerateSecret;
        logger.trace("secret: {}", Hex.encodeHexString(bArrGenerateSecret));
    }

    public void c(PublicKey publicKey, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException {
        if (ObjectUtils.isEmpty(publicKey) || ObjectUtils.isEmpty(privateKey)) {
            throw new InvalidParameterException("Input key is empty.");
        }
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", d);
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        this.f1a = keyAgreement.generateSecret();
    }

    public byte[] e(PrivateKey privateKey, byte[] bArr) throws NoSuchAlgorithmException, InvalidKeyException {
        int length = ((BCECPrivateKey) privateKey).getParams().getCurve().getB().toByteArray().length;
        int i = length * 2;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.get();
        byte[] bArr2 = new byte[i];
        byteBufferWrap.get(bArr2);
        byte[] bArr3 = new byte[((bArr.length - 1) - i) - 32];
        byteBufferWrap.get(bArr3);
        byte[] bArr4 = new byte[32];
        byteBufferWrap.get(bArr4);
        f fVar = new f(d);
        c(fVar.c("ECC", fVar.e(((ECPrivateKey) privateKey).getParameters(), Arrays.copyOfRange(bArr2, 0, length), Arrays.copyOfRange(bArr2, length, i)).getEncoded()), privateKey);
        return g(bArr3, bArr4);
    }

    public byte[] f(byte[] bArr) {
        if (ObjectUtils.isEmpty(bArr)) {
            throw new InvalidParameterException("Plaint is empty.");
        }
        KDF2BytesGenerator kDF2BytesGenerator = new KDF2BytesGenerator(new SHA256Digest());
        kDF2BytesGenerator.init(new KDFParameters(this.f1a, new byte[0]));
        int length = bArr.length + 32;
        byte[] bArr2 = new byte[length];
        kDF2BytesGenerator.generateBytes(bArr2, 0, length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr2, 0, bArr.length);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr2, bArr.length, length);
        Logger logger = c;
        logger.trace("plaint: " + Hex.encodeHexString(bArr));
        logger.trace("ek: " + Hex.encodeHexString(bArrCopyOfRange));
        logger.trace("mk: " + Hex.encodeHexString(bArrCopyOfRange2));
        BitSet bitSetValueOf = BitSet.valueOf(bArr);
        bitSetValueOf.xor(BitSet.valueOf(bArrCopyOfRange));
        byte[] byteArray = bitSetValueOf.toByteArray();
        byte[] bArrAddAll = ArrayUtils.addAll(byteArray, new byte[bArr.length - byteArray.length]);
        logger.trace("eData: " + Hex.encodeHexString(bArrAddAll));
        logger.trace("mac in: " + Hex.encodeHexString(bArrAddAll));
        byte[] bArrD = h.d(bArrCopyOfRange2, bArrAddAll);
        logger.trace("mac out: " + Hex.encodeHexString(bArrD));
        byte[] encoded = ((BCECPublicKey) this.f2b).getQ().getEncoded(false);
        return ByteBuffer.allocate(encoded.length + bArrAddAll.length + bArrD.length).put(encoded).put(bArrAddAll).put(bArrD).array();
    }

    public byte[] g(byte[] bArr, byte[] bArr2) throws CryptoException {
        if (ObjectUtils.isEmpty(bArr)) {
            throw new InvalidParameterException("encrypted data is empty.");
        }
        KDF2BytesGenerator kDF2BytesGenerator = new KDF2BytesGenerator(new SHA256Digest());
        kDF2BytesGenerator.init(new KDFParameters(this.f1a, new byte[0]));
        int length = bArr.length + 32;
        byte[] bArr3 = new byte[length];
        kDF2BytesGenerator.generateBytes(bArr3, 0, length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr3, 0, bArr.length);
        byte[] bArrD = h.d(Arrays.copyOfRange(bArr3, bArr.length, length), bArr);
        if (Arrays.equals(bArr2, bArrD)) {
            BitSet bitSetValueOf = BitSet.valueOf(bArr);
            bitSetValueOf.xor(BitSet.valueOf(bArrCopyOfRange));
            byte[] byteArray = bitSetValueOf.toByteArray();
            byte[] bArrAddAll = ArrayUtils.addAll(byteArray, new byte[bArr.length - byteArray.length]);
            c.trace("plaint: " + Hex.encodeHexString(bArrAddAll));
            return bArrAddAll;
        }
        Logger logger = c;
        logger.trace("mac in: " + Hex.encodeHexString(bArr));
        logger.trace("dataMac out: " + Hex.encodeHexString(bArrD));
        throw new CryptoException("Decrypt error. mac verify error");
    }
}
