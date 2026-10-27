package org.bouncycastle.jcajce.provider.asymmetric.ec;

import g.a.a.m;
import g.a.a.x2.b;
import g.a.a.x2.g;
import g.a.b.e0.l;
import g.a.b.j;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.j0.y;
import g.a.c.e.e;
import g.a.f.d;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class KeyPairGeneratorSpi extends KeyPairGenerator {

    public static class EC extends KeyPairGeneratorSpi {
        private static Hashtable ecParameters;
        public String algorithm;
        public ProviderConfiguration configuration;
        public Object ecParams;
        public l engine;
        public boolean initialised;
        public y param;
        public SecureRandom random;
        public int strength;

        static {
            Hashtable hashtable = new Hashtable();
            ecParameters = hashtable;
            hashtable.put(d.b(192), new ECGenParameterSpec("prime192v1"));
            ecParameters.put(d.b(239), new ECGenParameterSpec("prime239v1"));
            ecParameters.put(d.b(256), new ECGenParameterSpec("prime256v1"));
            ecParameters.put(d.b(224), new ECGenParameterSpec("P-224"));
            ecParameters.put(d.b(384), new ECGenParameterSpec("P-384"));
            ecParameters.put(d.b(521), new ECGenParameterSpec("P-521"));
        }

        public EC() {
            super("EC");
            this.engine = new l();
            this.ecParams = null;
            this.strength = 239;
            this.random = j.b();
            this.initialised = false;
            this.algorithm = "EC";
            this.configuration = BouncyCastleProvider.CONFIGURATION;
        }

        public EC(String str, ProviderConfiguration providerConfiguration) {
            super(str);
            this.engine = new l();
            this.ecParams = null;
            this.strength = 239;
            this.random = j.b();
            this.initialised = false;
            this.algorithm = str;
            this.configuration = providerConfiguration;
        }

        public y createKeyGenParamsBC(e eVar, SecureRandom secureRandom) {
            return new y(new x(eVar.a(), eVar.b(), eVar.d(), eVar.c()), secureRandom);
        }

        public y createKeyGenParamsJCE(ECParameterSpec eCParameterSpec, SecureRandom secureRandom) {
            g.a.d.b.d dVarConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            return new y(new x(dVarConvertCurve, EC5Util.convertPoint(dVarConvertCurve, eCParameterSpec.getGenerator(), false), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor())), secureRandom);
        }

        public g.a.c.e.d createNamedCurveSpec(String str) throws InvalidAlgorithmParameterException {
            g domainParametersFromName = ECUtils.getDomainParametersFromName(str);
            if (domainParametersFromName == null) {
                try {
                    domainParametersFromName = b.d(new m(str));
                    if (domainParametersFromName == null && (domainParametersFromName = (g) this.configuration.getAdditionalECParameters().get(new m(str))) == null) {
                        throw new InvalidAlgorithmParameterException("unknown curve OID: " + str);
                    }
                } catch (IllegalArgumentException unused) {
                    throw new InvalidAlgorithmParameterException("unknown curve name: " + str);
                }
            }
            return new g.a.c.e.d(str, domainParametersFromName.g(), domainParametersFromName.h(), domainParametersFromName.k(), domainParametersFromName.i(), null);
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public KeyPair generateKeyPair() {
            if (!this.initialised) {
                initialize(this.strength, new SecureRandom());
            }
            g.a.b.b bVarA = this.engine.a();
            c0 c0Var = (c0) bVarA.b();
            b0 b0Var = (b0) bVarA.a();
            Object obj = this.ecParams;
            if (obj instanceof e) {
                e eVar = (e) obj;
                BCECPublicKey bCECPublicKey = new BCECPublicKey(this.algorithm, c0Var, eVar, this.configuration);
                return new KeyPair(bCECPublicKey, new BCECPrivateKey(this.algorithm, b0Var, bCECPublicKey, eVar, this.configuration));
            }
            if (obj == null) {
                return new KeyPair(new BCECPublicKey(this.algorithm, c0Var, this.configuration), new BCECPrivateKey(this.algorithm, b0Var, this.configuration));
            }
            ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
            BCECPublicKey bCECPublicKey2 = new BCECPublicKey(this.algorithm, c0Var, eCParameterSpec, this.configuration);
            return new KeyPair(bCECPublicKey2, new BCECPrivateKey(this.algorithm, b0Var, bCECPublicKey2, eCParameterSpec, this.configuration));
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(int i2, SecureRandom secureRandom) {
            this.strength = i2;
            this.random = secureRandom;
            ECGenParameterSpec eCGenParameterSpec = (ECGenParameterSpec) ecParameters.get(d.b(i2));
            if (eCGenParameterSpec == null) {
                throw new InvalidParameterException("unknown key size.");
            }
            try {
                initialize(eCGenParameterSpec, secureRandom);
            } catch (InvalidAlgorithmParameterException unused) {
                throw new InvalidParameterException("key size not configurable.");
            }
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            String strA;
            y yVarCreateKeyGenParamsJCE;
            e ecImplicitlyCa;
            if (algorithmParameterSpec == null) {
                ecImplicitlyCa = this.configuration.getEcImplicitlyCa();
                if (ecImplicitlyCa == null) {
                    throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
                }
                this.ecParams = null;
            } else {
                if (!(algorithmParameterSpec instanceof e)) {
                    if (algorithmParameterSpec instanceof ECParameterSpec) {
                        this.ecParams = algorithmParameterSpec;
                        yVarCreateKeyGenParamsJCE = createKeyGenParamsJCE((ECParameterSpec) algorithmParameterSpec, secureRandom);
                        this.param = yVarCreateKeyGenParamsJCE;
                        this.engine.c(this.param);
                        this.initialised = true;
                    }
                    if (algorithmParameterSpec instanceof ECGenParameterSpec) {
                        strA = ((ECGenParameterSpec) algorithmParameterSpec).getName();
                    } else {
                        if (!(algorithmParameterSpec instanceof g.a.c.e.b)) {
                            throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec");
                        }
                        strA = ((g.a.c.e.b) algorithmParameterSpec).a();
                    }
                    initializeNamedCurve(strA, secureRandom);
                    this.engine.c(this.param);
                    this.initialised = true;
                }
                this.ecParams = algorithmParameterSpec;
                ecImplicitlyCa = (e) algorithmParameterSpec;
            }
            yVarCreateKeyGenParamsJCE = createKeyGenParamsBC(ecImplicitlyCa, secureRandom);
            this.param = yVarCreateKeyGenParamsJCE;
            this.engine.c(this.param);
            this.initialised = true;
        }

        public void initializeNamedCurve(String str, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            g.a.c.e.d dVarCreateNamedCurveSpec = createNamedCurveSpec(str);
            this.ecParams = dVarCreateNamedCurveSpec;
            this.param = createKeyGenParamsJCE(dVarCreateNamedCurveSpec, secureRandom);
        }
    }

    public static class ECDH extends EC {
        public ECDH() {
            super("ECDH", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDHC extends EC {
        public ECDHC() {
            super("ECDHC", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDSA extends EC {
        public ECDSA() {
            super("ECDSA", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECMQV extends EC {
        public ECMQV() {
            super("ECMQV", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public KeyPairGeneratorSpi(String str) {
        super(str);
    }
}
