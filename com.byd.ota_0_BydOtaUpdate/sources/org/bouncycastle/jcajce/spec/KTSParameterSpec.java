package org.bouncycastle.jcajce.spec;

import g.a.a.l2.b;
import g.a.a.w2.a;
import g.a.a.x2.m;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class KTSParameterSpec implements AlgorithmParameterSpec {
    private final a kdfAlgorithm;
    private final int keySizeInBits;
    private byte[] otherInfo;
    private final AlgorithmParameterSpec parameterSpec;
    private final String wrappingKeyAlgorithm;

    public static final class Builder {
        private final String algorithmName;
        private a kdfAlgorithm;
        private final int keySizeInBits;
        private byte[] otherInfo;
        private AlgorithmParameterSpec parameterSpec;

        public Builder(String str, int i2) {
            this(str, i2, null);
        }

        public Builder(String str, int i2, byte[] bArr) {
            this.algorithmName = str;
            this.keySizeInBits = i2;
            this.kdfAlgorithm = new a(m.D3, new a(b.f3909c));
            this.otherInfo = bArr == null ? new byte[0] : g.a.f.a.f(bArr);
        }

        public KTSParameterSpec build() {
            return new KTSParameterSpec(this.algorithmName, this.keySizeInBits, this.parameterSpec, this.kdfAlgorithm, this.otherInfo);
        }

        public Builder withKdfAlgorithm(a aVar) {
            this.kdfAlgorithm = aVar;
            return this;
        }

        public Builder withParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
            this.parameterSpec = algorithmParameterSpec;
            return this;
        }
    }

    private KTSParameterSpec(String str, int i2, AlgorithmParameterSpec algorithmParameterSpec, a aVar, byte[] bArr) {
        this.wrappingKeyAlgorithm = str;
        this.keySizeInBits = i2;
        this.parameterSpec = algorithmParameterSpec;
        this.kdfAlgorithm = aVar;
        this.otherInfo = bArr;
    }

    public a getKdfAlgorithm() {
        return this.kdfAlgorithm;
    }

    public String getKeyAlgorithmName() {
        return this.wrappingKeyAlgorithm;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public byte[] getOtherInfo() {
        return g.a.f.a.f(this.otherInfo);
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return this.parameterSpec;
    }
}
