package a.a.a.a;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECFieldFp;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Provider f3a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SecureRandom f4b;

    public f(Provider provider) {
        this(new SecureRandom(), provider);
    }

    public f(SecureRandom secureRandom, Provider provider) {
        this.f3a = provider;
        this.f4b = secureRandom;
        Security.addProvider(provider);
    }

    public static PublicKey d(String str, byte[] bArr, Provider provider) {
        return KeyFactory.getInstance(str, provider).generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static PublicKey f(byte[] bArr, Provider provider) {
        return d("EC", bArr, provider);
    }

    public KeyPair a(String str) throws NoSuchAlgorithmException {
        ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec(str);
        if (parameterSpec != null) {
            return b(parameterSpec);
        }
        throw new NoSuchAlgorithmException("not found curve name: " + str);
    }

    public KeyPair b(ECParameterSpec eCParameterSpec) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", this.f3a);
        keyPairGenerator.initialize(eCParameterSpec, this.f4b);
        return keyPairGenerator.generateKeyPair();
    }

    public PublicKey c(String str, byte[] bArr) {
        return f(bArr, this.f3a);
    }

    public PublicKey e(ECParameterSpec eCParameterSpec, byte[] bArr, byte[] bArr2) {
        System.out.println("x len = " + bArr.length + " y len = " + bArr2.length);
        return KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(new ECPoint(new BigInteger(1, bArr), new BigInteger(1, bArr2)), new java.security.spec.ECParameterSpec(new EllipticCurve(new ECFieldFp(eCParameterSpec.getCurve().getField().getCharacteristic()), eCParameterSpec.getCurve().getA().toBigInteger(), eCParameterSpec.getCurve().getB().toBigInteger()), new ECPoint(eCParameterSpec.getG().getXCoord().toBigInteger(), eCParameterSpec.getG().getYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue())));
    }
}
