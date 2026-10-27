package org.bouncycastle.jcajce.provider.symmetric;

import g.a.a.c2.a;
import g.a.a.c2.b;
import g.a.a.m;
import g.a.b.d0.d;
import g.a.b.d0.k0;
import g.a.b.e;
import g.a.b.e0.w;
import g.a.b.f;
import g.a.b.h;
import g.a.b.h0.l;
import g.a.b.h0.s;
import g.a.b.j;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class ARIA {

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        public AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[16];
            if (this.random == null) {
                this.random = j.b();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("ARIA");
                algorithmParametersCreateParametersInstance.init(new IvParameterSpec(bArr));
                return algorithmParametersCreateParametersInstance;
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for ARIA parameter generation.");
        }
    }

    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        public String engineToString() {
            return "ARIA IV";
        }
    }

    public static class AlgParamsCCM extends BaseAlgorithmParameters {
        private a ccmParams;

        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded() throws IOException {
            return this.ccmParams.e();
        }

        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return this.ccmParams.e();
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.ccmParams = a.h(GcmSpecUtil.extractGcmParameters(algorithmParameterSpec));
                return;
            }
            if (algorithmParameterSpec instanceof AEADParameterSpec) {
                AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                this.ccmParams = new a(aEADParameterSpec.getNonce(), aEADParameterSpec.getMacSizeInBits() / 8);
            } else {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr) throws IOException {
            this.ccmParams = a.h(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr, String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("unknown format specified");
            }
            this.ccmParams = a.h(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        public String engineToString() {
            return "CCM";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(cls)) {
                return GcmSpecUtil.gcmSpecExists() ? GcmSpecUtil.extractGcmSpec(this.ccmParams.c()) : new AEADParameterSpec(this.ccmParams.i(), this.ccmParams.g() * 8);
            }
            if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.ccmParams.i(), this.ccmParams.g() * 8);
            }
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.ccmParams.i());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }
    }

    public static class AlgParamsGCM extends BaseAlgorithmParameters {
        private b gcmParams;

        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded() throws IOException {
            return this.gcmParams.e();
        }

        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return this.gcmParams.e();
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.gcmParams = GcmSpecUtil.extractGcmParameters(algorithmParameterSpec);
                return;
            }
            if (algorithmParameterSpec instanceof AEADParameterSpec) {
                AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                this.gcmParams = new b(aEADParameterSpec.getNonce(), aEADParameterSpec.getMacSizeInBits() / 8);
            } else {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr) throws IOException {
            this.gcmParams = b.h(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr, String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("unknown format specified");
            }
            this.gcmParams = b.h(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        public String engineToString() {
            return "GCM";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(cls)) {
                return GcmSpecUtil.gcmSpecExists() ? GcmSpecUtil.extractGcmSpec(this.gcmParams.c()) : new AEADParameterSpec(this.gcmParams.i(), this.gcmParams.g() * 8);
            }
            if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.gcmParams.i(), this.gcmParams.g() * 8);
            }
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.gcmParams.i());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new g.a.b.h0.b(new d()), 128);
        }
    }

    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new f(new g.a.b.h0.d(new d(), 128)), 128);
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.ARIA.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public e get() {
                    return new d();
                }
            });
        }
    }

    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new g.a.b.g0.e(new l(new d())));
        }
    }

    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("ARIA", null);
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i2) {
            super("ARIA", i2, new h());
        }
    }

    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    public static class KeyGen192 extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String PREFIX = ARIA.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameters.ARIA", sb.toString());
            m mVar = g.a.a.m2.a.f3927h;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", mVar, "ARIA");
            m mVar2 = g.a.a.m2.a.m;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", mVar2, "ARIA");
            m mVar3 = g.a.a.m2.a.r;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", mVar3, "ARIA");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIA", str + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar2, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar3, "ARIA");
            m mVar4 = g.a.a.m2.a.j;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar4, "ARIA");
            m mVar5 = g.a.a.m2.a.o;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar5, "ARIA");
            m mVar6 = g.a.a.m2.a.t;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar6, "ARIA");
            m mVar7 = g.a.a.m2.a.f3928i;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar7, "ARIA");
            m mVar8 = g.a.a.m2.a.n;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar8, "ARIA");
            m mVar9 = g.a.a.m2.a.s;
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", mVar9, "ARIA");
            configurableProvider.addAlgorithm("Cipher.ARIA", str + "$ECB");
            m mVar10 = g.a.a.m2.a.f3926g;
            configurableProvider.addAlgorithm("Cipher", mVar10, str + "$ECB");
            m mVar11 = g.a.a.m2.a.l;
            configurableProvider.addAlgorithm("Cipher", mVar11, str + "$ECB");
            m mVar12 = g.a.a.m2.a.q;
            configurableProvider.addAlgorithm("Cipher", mVar12, str + "$ECB");
            configurableProvider.addAlgorithm("Cipher", mVar, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher", mVar2, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher", mVar3, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher", mVar7, str + "$CFB");
            configurableProvider.addAlgorithm("Cipher", mVar8, str + "$CFB");
            configurableProvider.addAlgorithm("Cipher", mVar9, str + "$CFB");
            configurableProvider.addAlgorithm("Cipher", mVar4, str + "$OFB");
            configurableProvider.addAlgorithm("Cipher", mVar5, str + "$OFB");
            configurableProvider.addAlgorithm("Cipher", mVar6, str + "$OFB");
            configurableProvider.addAlgorithm("Cipher.ARIARFC3211WRAP", str + "$RFC3211Wrap");
            configurableProvider.addAlgorithm("Cipher.ARIAWRAP", str + "$Wrap");
            m mVar13 = g.a.a.m2.a.H;
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar13, "ARIAWRAP");
            m mVar14 = g.a.a.m2.a.I;
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar14, "ARIAWRAP");
            m mVar15 = g.a.a.m2.a.J;
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar15, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.ARIAKW", "ARIAWRAP");
            configurableProvider.addAlgorithm("Cipher.ARIAWRAPPAD", str + "$WrapPad");
            m mVar16 = g.a.a.m2.a.K;
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar16, "ARIAWRAPPAD");
            m mVar17 = g.a.a.m2.a.L;
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar17, "ARIAWRAPPAD");
            m mVar18 = g.a.a.m2.a.M;
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar18, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.ARIAKWP", "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("KeyGenerator.ARIA", str + "$KeyGen");
            configurableProvider.addAlgorithm("KeyGenerator", mVar13, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar14, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", mVar15, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar16, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar17, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", mVar18, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar10, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar11, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", mVar12, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar2, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", mVar3, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar7, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar8, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", mVar9, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar4, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar5, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", mVar6, str + "$KeyGen256");
            m mVar19 = g.a.a.m2.a.E;
            configurableProvider.addAlgorithm("KeyGenerator", mVar19, str + "$KeyGen128");
            m mVar20 = g.a.a.m2.a.F;
            configurableProvider.addAlgorithm("KeyGenerator", mVar20, str + "$KeyGen192");
            m mVar21 = g.a.a.m2.a.G;
            configurableProvider.addAlgorithm("KeyGenerator", mVar21, str + "$KeyGen256");
            m mVar22 = g.a.a.m2.a.B;
            configurableProvider.addAlgorithm("KeyGenerator", mVar22, str + "$KeyGen128");
            m mVar23 = g.a.a.m2.a.C;
            configurableProvider.addAlgorithm("KeyGenerator", mVar23, str + "$KeyGen192");
            m mVar24 = g.a.a.m2.a.D;
            configurableProvider.addAlgorithm("KeyGenerator", mVar24, str + "$KeyGen256");
            configurableProvider.addAlgorithm("SecretKeyFactory.ARIA", str + "$KeyFactory");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", mVar, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", mVar2, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", mVar3, "ARIA");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIACCM", str + "$AlgParamGenCCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar19, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar20, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar21, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar19, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar20, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar21, "CCM");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIAGCM", str + "$AlgParamGenGCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar22, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar23, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar24, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar22, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar23, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", mVar24, "GCM");
            addGMacAlgorithm(configurableProvider, "ARIA", str + "$GMAC", str + "$KeyGen");
            addPoly1305Algorithm(configurableProvider, "ARIA", str + "$Poly1305", str + "$Poly1305KeyGen");
        }
    }

    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new f(new s(new d(), 128)), 128);
        }
    }

    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new g.a.b.g0.l(new d()));
        }
    }

    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-ARIA", 256, new w());
        }
    }

    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new k0(new d()), 16);
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new g.a.b.d0.e());
        }
    }

    public static class WrapPad extends BaseWrapCipher {
        public WrapPad() {
            super(new g.a.b.d0.f());
        }
    }

    private ARIA() {
    }
}
