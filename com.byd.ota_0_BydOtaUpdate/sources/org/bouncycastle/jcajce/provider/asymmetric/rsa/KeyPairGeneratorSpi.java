package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import g.a.b.b;
import g.a.b.e0.x;
import g.a.b.j;
import g.a.b.j0.d1;
import g.a.b.j0.e1;
import g.a.b.j0.f1;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public static final BigInteger defaultPublicExponent = BigInteger.valueOf(65537);
    public x engine;
    public d1 param;

    public KeyPairGeneratorSpi() {
        super("RSA");
        this.engine = new x();
        d1 d1Var = new d1(defaultPublicExponent, j.b(), 2048, PrimeCertaintyCalculator.getDefaultCertainty(2048));
        this.param = d1Var;
        this.engine.d(d1Var);
    }

    public KeyPairGeneratorSpi(String str) {
        super(str);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        b bVarA = this.engine.a();
        return new KeyPair(new BCRSAPublicKey((e1) bVarA.b()), new BCRSAPrivateCrtKey((f1) bVarA.a()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        d1 d1Var = new d1(defaultPublicExponent, secureRandom, i2, PrimeCertaintyCalculator.getDefaultCertainty(i2));
        this.param = d1Var;
        this.engine.d(d1Var);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof RSAKeyGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a RSAKeyGenParameterSpec");
        }
        RSAKeyGenParameterSpec rSAKeyGenParameterSpec = (RSAKeyGenParameterSpec) algorithmParameterSpec;
        d1 d1Var = new d1(rSAKeyGenParameterSpec.getPublicExponent(), secureRandom, rSAKeyGenParameterSpec.getKeysize(), PrimeCertaintyCalculator.getDefaultCertainty(2048));
        this.param = d1Var;
        this.engine.d(d1Var);
    }
}
