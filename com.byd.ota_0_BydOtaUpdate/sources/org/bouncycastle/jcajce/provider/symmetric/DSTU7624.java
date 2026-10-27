package org.bouncycastle.jcajce.provider.symmetric;

import g.a.a.m;
import g.a.b.d0.r;
import g.a.b.e;
import g.a.b.f;
import g.a.b.g0.i;
import g.a.b.h;
import g.a.b.h0.b;
import g.a.b.h0.d;
import g.a.b.h0.o;
import g.a.b.h0.p;
import g.a.b.h0.q;
import g.a.b.h0.s;
import g.a.b.j;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

/* JADX INFO: loaded from: classes.dex */
public class DSTU7624 {

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        private final int ivLength;

        public AlgParamGen(int i2) {
            this.ivLength = i2 / 8;
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        public AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[this.ivLength];
            if (this.random == null) {
                this.random = j.b();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("DSTU7624");
                algorithmParametersCreateParametersInstance.init(new IvParameterSpec(bArr));
                return algorithmParametersCreateParametersInstance;
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DSTU7624 parameter generation.");
        }
    }

    public static class AlgParamGen128 extends AlgParamGen {
        public AlgParamGen128() {
            super(128);
        }
    }

    public static class AlgParamGen256 extends AlgParamGen {
        public AlgParamGen256() {
            super(256);
        }
    }

    public static class AlgParamGen512 extends AlgParamGen {
        public AlgParamGen512() {
            super(512);
        }
    }

    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        public String engineToString() {
            return "DSTU7624 IV";
        }
    }

    public static class CBC128 extends BaseBlockCipher {
        public CBC128() {
            super(new b(new r(128)), 128);
        }
    }

    public static class CBC256 extends BaseBlockCipher {
        public CBC256() {
            super(new b(new r(256)), 256);
        }
    }

    public static class CBC512 extends BaseBlockCipher {
        public CBC512() {
            super(new b(new r(512)), 512);
        }
    }

    public static class CCM128 extends BaseBlockCipher {
        public CCM128() {
            super(new o(new r(128)));
        }
    }

    public static class CCM256 extends BaseBlockCipher {
        public CCM256() {
            super(new o(new r(256)));
        }
    }

    public static class CCM512 extends BaseBlockCipher {
        public CCM512() {
            super(new o(new r(512)));
        }
    }

    public static class CFB128 extends BaseBlockCipher {
        public CFB128() {
            super(new f(new d(new r(128), 128)), 128);
        }
    }

    public static class CFB256 extends BaseBlockCipher {
        public CFB256() {
            super(new f(new d(new r(256), 256)), 256);
        }
    }

    public static class CFB512 extends BaseBlockCipher {
        public CFB512() {
            super(new f(new d(new r(512), 512)), 512);
        }
    }

    public static class CTR128 extends BaseBlockCipher {
        public CTR128() {
            super(new f(new p(new r(128))), 128);
        }
    }

    public static class CTR256 extends BaseBlockCipher {
        public CTR256() {
            super(new f(new p(new r(256))), 256);
        }
    }

    public static class CTR512 extends BaseBlockCipher {
        public CTR512() {
            super(new f(new p(new r(512))), 512);
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.DSTU7624.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public e get() {
                    return new r(128);
                }
            });
        }
    }

    public static class ECB128 extends BaseBlockCipher {
        public ECB128() {
            super(new r(128));
        }
    }

    public static class ECB256 extends BaseBlockCipher {
        public ECB256() {
            super(new r(256));
        }
    }

    public static class ECB512 extends BaseBlockCipher {
        public ECB512() {
            super(new r(512));
        }
    }

    public static class ECB_128 extends BaseBlockCipher {
        public ECB_128() {
            super(new r(128));
        }
    }

    public static class ECB_256 extends BaseBlockCipher {
        public ECB_256() {
            super(new r(256));
        }
    }

    public static class ECB_512 extends BaseBlockCipher {
        public ECB_512() {
            super(new r(512));
        }
    }

    public static class GCM128 extends BaseBlockCipher {
        public GCM128() {
            super(new q(new r(128)));
        }
    }

    public static class GCM256 extends BaseBlockCipher {
        public GCM256() {
            super(new q(new r(256)));
        }
    }

    public static class GCM512 extends BaseBlockCipher {
        public GCM512() {
            super(new q(new r(512)));
        }
    }

    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new i(new q(new r(128)), 128));
        }
    }

    public static class GMAC128 extends BaseMac {
        public GMAC128() {
            super(new i(new q(new r(128)), 128));
        }
    }

    public static class GMAC256 extends BaseMac {
        public GMAC256() {
            super(new i(new q(new r(256)), 256));
        }
    }

    public static class GMAC512 extends BaseMac {
        public GMAC512() {
            super(new i(new q(new r(512)), 512));
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i2) {
            super("DSTU7624", i2, new h());
        }
    }

    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    public static class KeyGen512 extends KeyGen {
        public KeyGen512() {
            super(512);
        }
    }

    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String PREFIX = DSTU7624.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$AlgParams128");
            configurableProvider.addAlgorithm("AlgorithmParameters.DSTU7624", sb.toString());
            m mVar = g.a.a.t2.f.v;
            configurableProvider.addAlgorithm("AlgorithmParameters", mVar, str + "$AlgParams");
            m mVar2 = g.a.a.t2.f.w;
            configurableProvider.addAlgorithm("AlgorithmParameters", mVar2, str + "$AlgParams");
            m mVar3 = g.a.a.t2.f.x;
            configurableProvider.addAlgorithm("AlgorithmParameters", mVar3, str + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.DSTU7624", str + "$AlgParamGen128");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator", mVar, str + "$AlgParamGen128");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator", mVar2, str + "$AlgParamGen256");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator", mVar3, str + "$AlgParamGen512");
            configurableProvider.addAlgorithm("Cipher.DSTU7624", str + "$ECB_128");
            configurableProvider.addAlgorithm("Cipher.DSTU7624-128", str + "$ECB_128");
            configurableProvider.addAlgorithm("Cipher.DSTU7624-256", str + "$ECB_256");
            configurableProvider.addAlgorithm("Cipher.DSTU7624-512", str + "$ECB_512");
            m mVar4 = g.a.a.t2.f.j;
            configurableProvider.addAlgorithm("Cipher", mVar4, str + "$ECB128");
            m mVar5 = g.a.a.t2.f.k;
            configurableProvider.addAlgorithm("Cipher", mVar5, str + "$ECB256");
            m mVar6 = g.a.a.t2.f.l;
            configurableProvider.addAlgorithm("Cipher", mVar6, str + "$ECB512");
            configurableProvider.addAlgorithm("Cipher", mVar, str + "$CBC128");
            configurableProvider.addAlgorithm("Cipher", mVar2, str + "$CBC256");
            configurableProvider.addAlgorithm("Cipher", mVar3, str + "$CBC512");
            m mVar7 = g.a.a.t2.f.y;
            configurableProvider.addAlgorithm("Cipher", mVar7, str + "$OFB128");
            m mVar8 = g.a.a.t2.f.z;
            configurableProvider.addAlgorithm("Cipher", mVar8, str + "$OFB256");
            m mVar9 = g.a.a.t2.f.A;
            configurableProvider.addAlgorithm("Cipher", mVar9, str + "$OFB512");
            m mVar10 = g.a.a.t2.f.p;
            configurableProvider.addAlgorithm("Cipher", mVar10, str + "$CFB128");
            m mVar11 = g.a.a.t2.f.q;
            configurableProvider.addAlgorithm("Cipher", mVar11, str + "$CFB256");
            m mVar12 = g.a.a.t2.f.r;
            configurableProvider.addAlgorithm("Cipher", mVar12, str + "$CFB512");
            m mVar13 = g.a.a.t2.f.m;
            configurableProvider.addAlgorithm("Cipher", mVar13, str + "$CTR128");
            m mVar14 = g.a.a.t2.f.n;
            configurableProvider.addAlgorithm("Cipher", mVar14, str + "$CTR256");
            m mVar15 = g.a.a.t2.f.o;
            configurableProvider.addAlgorithm("Cipher", mVar15, str + "$CTR512");
            m mVar16 = g.a.a.t2.f.E;
            configurableProvider.addAlgorithm("Cipher", mVar16, str + "$CCM128");
            m mVar17 = g.a.a.t2.f.F;
            configurableProvider.addAlgorithm("Cipher", mVar17, str + "$CCM256");
            m mVar18 = g.a.a.t2.f.G;
            configurableProvider.addAlgorithm("Cipher", mVar18, str + "$CCM512");
            configurableProvider.addAlgorithm("Cipher.DSTU7624KW", str + "$Wrap");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.DSTU7624WRAP", "DSTU7624KW");
            configurableProvider.addAlgorithm("Cipher.DSTU7624-128KW", str + "$Wrap128");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Alg.Alias.Cipher.");
            m mVar19 = g.a.a.t2.f.K;
            sb2.append(mVar19.r());
            configurableProvider.addAlgorithm(sb2.toString(), "DSTU7624-128KW");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.DSTU7624-128WRAP", "DSTU7624-128KW");
            configurableProvider.addAlgorithm("Cipher.DSTU7624-256KW", str + "$Wrap256");
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Alg.Alias.Cipher.");
            m mVar20 = g.a.a.t2.f.L;
            sb3.append(mVar20.r());
            configurableProvider.addAlgorithm(sb3.toString(), "DSTU7624-256KW");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.DSTU7624-256WRAP", "DSTU7624-256KW");
            configurableProvider.addAlgorithm("Cipher.DSTU7624-512KW", str + "$Wrap512");
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Alg.Alias.Cipher.");
            m mVar21 = g.a.a.t2.f.M;
            sb4.append(mVar21.r());
            configurableProvider.addAlgorithm(sb4.toString(), "DSTU7624-512KW");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.DSTU7624-512WRAP", "DSTU7624-512KW");
            configurableProvider.addAlgorithm("Mac.DSTU7624GMAC", str + "$GMAC");
            configurableProvider.addAlgorithm("Mac.DSTU7624-128GMAC", str + "$GMAC128");
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Alg.Alias.Mac.");
            m mVar22 = g.a.a.t2.f.B;
            sb5.append(mVar22.r());
            configurableProvider.addAlgorithm(sb5.toString(), "DSTU7624-128GMAC");
            configurableProvider.addAlgorithm("Mac.DSTU7624-256GMAC", str + "$GMAC256");
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Alg.Alias.Mac.");
            m mVar23 = g.a.a.t2.f.C;
            sb6.append(mVar23.r());
            configurableProvider.addAlgorithm(sb6.toString(), "DSTU7624-256GMAC");
            configurableProvider.addAlgorithm("Mac.DSTU7624-512GMAC", str + "$GMAC512");
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Alg.Alias.Mac.");
            m mVar24 = g.a.a.t2.f.D;
            sb7.append(mVar24.r());
            configurableProvider.addAlgorithm(sb7.toString(), "DSTU7624-512GMAC");
            configurableProvider.addAlgorithm("KeyGenerator.DSTU7624", str + "$KeyGen");
            configurableProvider.addAlgorithm("KeyGenerator", mVar19, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar20, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar21, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar4, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar5, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar6, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar2, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar3, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar7, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar8, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar9, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar10, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar11, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar12, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar13, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar14, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar15, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar16, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar17, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar18, str + "$KeyGen512");
            configurableProvider.addAlgorithm("KeyGenerator", mVar22, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", mVar23, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", mVar24, str + "$KeyGen512");
        }
    }

    public static class OFB128 extends BaseBlockCipher {
        public OFB128() {
            super(new f(new s(new r(128), 128)), 128);
        }
    }

    public static class OFB256 extends BaseBlockCipher {
        public OFB256() {
            super(new f(new s(new r(256), 256)), 256);
        }
    }

    public static class OFB512 extends BaseBlockCipher {
        public OFB512() {
            super(new f(new s(new r(512), 512)), 512);
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new g.a.b.d0.s(128));
        }
    }

    public static class Wrap128 extends BaseWrapCipher {
        public Wrap128() {
            super(new g.a.b.d0.s(128));
        }
    }

    public static class Wrap256 extends BaseWrapCipher {
        public Wrap256() {
            super(new g.a.b.d0.s(256));
        }
    }

    public static class Wrap512 extends BaseWrapCipher {
        public Wrap512() {
            super(new g.a.b.d0.s(512));
        }
    }

    private DSTU7624() {
    }
}
