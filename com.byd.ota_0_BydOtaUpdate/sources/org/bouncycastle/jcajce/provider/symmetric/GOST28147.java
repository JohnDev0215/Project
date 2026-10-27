package org.bouncycastle.jcajce.provider.symmetric;

import g.a.a.d2.a;
import g.a.a.d2.c;
import g.a.a.m;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.b.d0.u;
import g.a.b.d0.v;
import g.a.b.f;
import g.a.b.h;
import g.a.b.h0.b;
import g.a.b.h0.k;
import g.a.b.j;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class GOST28147 {
    private static Map<m, String> oidMappings = new HashMap();
    private static Map<String, m> nameMappings = new HashMap();

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        public byte[] iv = new byte[8];
        public byte[] sBox = u.h("E-A");

        @Override // java.security.AlgorithmParameterGeneratorSpi
        public AlgorithmParameters engineGenerateParameters() {
            if (this.random == null) {
                this.random = j.b();
            }
            this.random.nextBytes(this.iv);
            try {
                AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("GOST28147");
                algorithmParametersCreateParametersInstance.init(new GOST28147ParameterSpec(this.sBox, this.iv));
                return algorithmParametersCreateParametersInstance;
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                throw new InvalidAlgorithmParameterException("parameter spec not supported");
            }
            this.sBox = ((GOST28147ParameterSpec) algorithmParameterSpec).getSBox();
        }
    }

    public static class AlgParams extends BaseAlgParams {
        private byte[] iv;
        private m sBox = a.f3800h;

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams, java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else {
                if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                    throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
                }
                this.iv = ((GOST28147ParameterSpec) algorithmParameterSpec).getIV();
                try {
                    this.sBox = BaseAlgParams.getSBoxOID(((GOST28147ParameterSpec) algorithmParameterSpec).getSBox());
                } catch (IllegalArgumentException e2) {
                    throw new InvalidParameterSpecException(e2.getMessage());
                }
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        public String engineToString() {
            return "GOST 28147 IV Parameters";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams, org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (cls == GOST28147ParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return new GOST28147ParameterSpec(this.sBox, this.iv);
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams
        public byte[] localGetEncoded() throws IOException {
            return new c(this.iv, this.sBox).e();
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams
        public void localInit(byte[] bArr) throws IOException {
            q qVarJ = q.j(bArr);
            if (qVarJ instanceof n) {
                this.iv = n.n(qVarJ).p();
            } else {
                if (!(qVarJ instanceof r)) {
                    throw new IOException("Unable to recognize parameters");
                }
                c cVarI = c.i(qVarJ);
                this.sBox = cVarI.g();
                this.iv = cVarI.h();
            }
        }
    }

    public static abstract class BaseAlgParams extends BaseAlgorithmParameters {
        private byte[] iv;
        private m sBox = a.f3800h;

        public static m getSBoxOID(String str) {
            m mVar = (m) GOST28147.nameMappings.get(str);
            if (mVar != null) {
                return mVar;
            }
            throw new IllegalArgumentException("Unknown SBOX name: " + str);
        }

        public static m getSBoxOID(byte[] bArr) {
            return getSBoxOID(u.i(bArr));
        }

        @Override // java.security.AlgorithmParametersSpi
        public final byte[] engineGetEncoded() throws IOException {
            return engineGetEncoded("ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        public final byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return localGetEncoded();
            }
            throw new IOException("Unknown parameter format: " + str);
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else {
                if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                    throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
                }
                this.iv = ((GOST28147ParameterSpec) algorithmParameterSpec).getIV();
                try {
                    this.sBox = getSBoxOID(((GOST28147ParameterSpec) algorithmParameterSpec).getSBox());
                } catch (IllegalArgumentException e2) {
                    throw new InvalidParameterSpecException(e2.getMessage());
                }
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        public final void engineInit(byte[] bArr) throws IOException {
            engineInit(bArr, "ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        public final void engineInit(byte[] bArr, String str) throws IOException {
            Objects.requireNonNull(bArr, "Encoded parameters cannot be null");
            if (!isASN1FormatString(str)) {
                throw new IOException("Unknown parameter format: " + str);
            }
            try {
                localInit(bArr);
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new IOException("Parameter parsing failed: " + e3.getMessage());
            }
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (cls == GOST28147ParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return new GOST28147ParameterSpec(this.sBox, this.iv);
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }

        public byte[] localGetEncoded() throws IOException {
            return new c(this.iv, this.sBox).e();
        }

        public abstract void localInit(byte[] bArr) throws IOException;
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new u()), 64);
        }
    }

    public static class CryptoProWrap extends BaseWrapCipher {
        public CryptoProWrap() {
            super(new g.a.b.d0.n());
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new u());
        }
    }

    public static class GCFB extends BaseBlockCipher {
        public GCFB() {
            super(new f(new k(new u())), 64);
        }
    }

    public static class GostWrap extends BaseWrapCipher {
        public GostWrap() {
            super(new v());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i2) {
            super("GOST28147", i2, new h());
        }
    }

    public static class Mac extends BaseMac {
        public Mac() {
            super(new g.a.b.g0.f());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = GOST28147.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$ECB");
            configurableProvider.addAlgorithm("Cipher.GOST28147", sb.toString());
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.GOST", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.GOST-28147", "GOST28147");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cipher.");
            m mVar = a.f3798f;
            sb2.append(mVar);
            configurableProvider.addAlgorithm(sb2.toString(), str + "$GCFB");
            configurableProvider.addAlgorithm("KeyGenerator.GOST28147", str + "$KeyGen");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.GOST", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + mVar, "GOST28147");
            configurableProvider.addAlgorithm("AlgorithmParameters.GOST28147", str + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.GOST28147", str + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + mVar, "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar, "GOST28147");
            configurableProvider.addAlgorithm("Cipher." + a.f3797e, str + "$CryptoProWrap");
            configurableProvider.addAlgorithm("Cipher." + a.f3796d, str + "$GostWrap");
            configurableProvider.addAlgorithm("Mac.GOST28147MAC", str + "$Mac");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
        }
    }

    static {
        oidMappings.put(a.f3799g, "E-TEST");
        Map<m, String> map = oidMappings;
        m mVar = a.f3800h;
        map.put(mVar, "E-A");
        Map<m, String> map2 = oidMappings;
        m mVar2 = a.f3801i;
        map2.put(mVar2, "E-B");
        Map<m, String> map3 = oidMappings;
        m mVar3 = a.j;
        map3.put(mVar3, "E-C");
        Map<m, String> map4 = oidMappings;
        m mVar4 = a.k;
        map4.put(mVar4, "E-D");
        nameMappings.put("E-A", mVar);
        nameMappings.put("E-B", mVar2);
        nameMappings.put("E-C", mVar3);
        nameMappings.put("E-D", mVar4);
    }

    private GOST28147() {
    }
}
