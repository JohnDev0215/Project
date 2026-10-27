package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import g.a.b.b;
import g.a.b.e0.l;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.j0.y;
import g.a.c.e.e;
import g.a.d.b.d;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public String algorithm;
    public Object ecParams;
    public l engine;
    public boolean initialised;
    public y param;
    public SecureRandom random;
    public int strength;

    public KeyPairGeneratorSpi() {
        super("ECGOST3410-2012");
        this.ecParams = null;
        this.engine = new l();
        this.algorithm = "ECGOST3410-2012";
        this.strength = 239;
        this.random = null;
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            throw new IllegalStateException("EC Key Pair Generator not initialised");
        }
        b bVarA = this.engine.a();
        c0 c0Var = (c0) bVarA.b();
        b0 b0Var = (b0) bVarA.a();
        Object obj = this.ecParams;
        if (obj instanceof e) {
            e eVar = (e) obj;
            BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = new BCECGOST3410_2012PublicKey(this.algorithm, c0Var, eVar);
            return new KeyPair(bCECGOST3410_2012PublicKey, new BCECGOST3410_2012PrivateKey(this.algorithm, b0Var, bCECGOST3410_2012PublicKey, eVar));
        }
        if (obj == null) {
            return new KeyPair(new BCECGOST3410_2012PublicKey(this.algorithm, c0Var), new BCECGOST3410_2012PrivateKey(this.algorithm, b0Var));
        }
        ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
        BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey2 = new BCECGOST3410_2012PublicKey(this.algorithm, c0Var, eCParameterSpec);
        return new KeyPair(bCECGOST3410_2012PublicKey2, new BCECGOST3410_2012PrivateKey(this.algorithm, b0Var, bCECGOST3410_2012PublicKey2, eCParameterSpec));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.strength = i2;
        this.random = secureRandom;
        Object obj = this.ecParams;
        if (obj == null) {
            throw new InvalidParameterException("unknown key size.");
        }
        try {
            initialize((ECGenParameterSpec) obj, secureRandom);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("key size not configurable.");
        }
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        y yVar;
        y yVar2;
        if (!(algorithmParameterSpec instanceof e)) {
            if (algorithmParameterSpec instanceof ECParameterSpec) {
                ECParameterSpec eCParameterSpec = (ECParameterSpec) algorithmParameterSpec;
                this.ecParams = algorithmParameterSpec;
                d dVarConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                yVar = new y(new x(dVarConvertCurve, EC5Util.convertPoint(dVarConvertCurve, eCParameterSpec.getGenerator(), false), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor())), secureRandom);
            } else {
                boolean z = algorithmParameterSpec instanceof ECGenParameterSpec;
                if (!z && !(algorithmParameterSpec instanceof g.a.c.e.b)) {
                    if (algorithmParameterSpec == null) {
                        ProviderConfiguration providerConfiguration = BouncyCastleProvider.CONFIGURATION;
                        if (providerConfiguration.getEcImplicitlyCa() != null) {
                            e ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
                            this.ecParams = algorithmParameterSpec;
                            yVar2 = new y(new x(ecImplicitlyCa.a(), ecImplicitlyCa.b(), ecImplicitlyCa.d(), ecImplicitlyCa.c()), secureRandom);
                        }
                    }
                    if (algorithmParameterSpec == null && BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa() == null) {
                        throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
                    }
                    throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec: " + algorithmParameterSpec.getClass().getName());
                }
                String name = z ? ((ECGenParameterSpec) algorithmParameterSpec).getName() : ((g.a.c.e.b) algorithmParameterSpec).a();
                x xVarA = g.a.a.d2.b.a(name);
                if (xVarA == null) {
                    throw new InvalidAlgorithmParameterException("unknown curve name: " + name);
                }
                g.a.c.e.d dVar = new g.a.c.e.d(name, xVarA.a(), xVarA.b(), xVarA.e(), xVarA.c(), xVarA.f());
                this.ecParams = dVar;
                g.a.c.e.d dVar2 = dVar;
                d dVarConvertCurve2 = EC5Util.convertCurve(dVar2.getCurve());
                yVar = new y(new x(dVarConvertCurve2, EC5Util.convertPoint(dVarConvertCurve2, dVar2.getGenerator(), false), dVar2.getOrder(), BigInteger.valueOf(dVar2.getCofactor())), secureRandom);
            }
            this.param = yVar;
            this.engine.c(yVar);
            this.initialised = true;
        }
        e eVar = (e) algorithmParameterSpec;
        this.ecParams = algorithmParameterSpec;
        yVar2 = new y(new x(eVar.a(), eVar.b(), eVar.d(), eVar.c()), secureRandom);
        this.param = yVar2;
        this.engine.c(yVar2);
        this.initialised = true;
    }
}
