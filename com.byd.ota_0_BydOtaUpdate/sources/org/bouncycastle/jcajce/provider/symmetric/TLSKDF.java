package org.bouncycastle.jcajce.provider.symmetric;

import g.a.b.a0.u;
import g.a.b.a0.v;
import g.a.b.a0.x;
import g.a.b.g0.g;
import g.a.b.j0.s0;
import g.a.b.t;
import g.a.f.a;
import g.a.f.i;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.TLSKeyMaterialSpec;

/* JADX INFO: loaded from: classes.dex */
public class TLSKDF {

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = TLSKDF.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$TLS10");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS10KDF", sb.toString());
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS11KDF", str + "$TLS11");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS12WITHSHA256KDF", str + "$TLS12withSHA256");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS12WITHSHA384KDF", str + "$TLS12withSHA384");
            configurableProvider.addAlgorithm("SecretKeyFactory.TLS12WITHSHA512KDF", str + "$TLS12withSHA512");
        }
    }

    public static final class TLS10 extends TLSKeyMaterialFactory {
        public TLS10() {
            super("TLS10KDF");
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof TLSKeyMaterialSpec) {
                return new SecretKeySpec(TLSKDF.PRF_legacy((TLSKeyMaterialSpec) keySpec), this.algName);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static final class TLS11 extends TLSKeyMaterialFactory {
        public TLS11() {
            super("TLS11KDF");
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof TLSKeyMaterialSpec) {
                return new SecretKeySpec(TLSKDF.PRF_legacy((TLSKeyMaterialSpec) keySpec), this.algName);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static class TLS12 extends TLSKeyMaterialFactory {
        private final t prf;

        public TLS12(String str, t tVar) {
            super(str);
            this.prf = tVar;
        }

        private byte[] PRF(TLSKeyMaterialSpec tLSKeyMaterialSpec, t tVar) {
            byte[] bArrM = a.m(i.e(tLSKeyMaterialSpec.getLabel()), tLSKeyMaterialSpec.getSeed());
            byte[] secret = tLSKeyMaterialSpec.getSecret();
            byte[] bArr = new byte[tLSKeyMaterialSpec.getLength()];
            TLSKDF.hmac_hash(tVar, secret, bArrM, bArr);
            return bArr;
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof TLSKeyMaterialSpec) {
                return new SecretKeySpec(PRF((TLSKeyMaterialSpec) keySpec, this.prf), this.algName);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static final class TLS12withSHA256 extends TLS12 {
        public TLS12withSHA256() {
            super("TLS12withSHA256KDF", new g(new u()));
        }
    }

    public static final class TLS12withSHA384 extends TLS12 {
        public TLS12withSHA384() {
            super("TLS12withSHA384KDF", new g(new v()));
        }
    }

    public static final class TLS12withSHA512 extends TLS12 {
        public TLS12withSHA512() {
            super("TLS12withSHA512KDF", new g(new x()));
        }
    }

    public static class TLSKeyMaterialFactory extends BaseSecretKeyFactory {
        public TLSKeyMaterialFactory(String str) {
            super(str, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] PRF_legacy(TLSKeyMaterialSpec tLSKeyMaterialSpec) {
        g gVar = new g(g.a.b.n0.a.a());
        g gVar2 = new g(g.a.b.n0.a.b());
        byte[] bArrM = a.m(i.e(tLSKeyMaterialSpec.getLabel()), tLSKeyMaterialSpec.getSeed());
        byte[] secret = tLSKeyMaterialSpec.getSecret();
        int length = (secret.length + 1) / 2;
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length];
        System.arraycopy(secret, 0, bArr, 0, length);
        System.arraycopy(secret, secret.length - length, bArr2, 0, length);
        int length2 = tLSKeyMaterialSpec.getLength();
        byte[] bArr3 = new byte[length2];
        byte[] bArr4 = new byte[length2];
        hmac_hash(gVar, bArr, bArrM, bArr3);
        hmac_hash(gVar2, bArr2, bArrM, bArr4);
        for (int i2 = 0; i2 < length2; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr4[i2]);
        }
        return bArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hmac_hash(t tVar, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        tVar.init(new s0(bArr));
        int macSize = tVar.getMacSize();
        int length = ((bArr3.length + macSize) - 1) / macSize;
        int macSize2 = tVar.getMacSize();
        byte[] bArr4 = new byte[macSize2];
        byte[] bArr5 = new byte[tVar.getMacSize()];
        byte[] bArr6 = bArr2;
        int i2 = 0;
        while (i2 < length) {
            tVar.update(bArr6, 0, bArr6.length);
            tVar.doFinal(bArr4, 0);
            tVar.update(bArr4, 0, macSize2);
            tVar.update(bArr2, 0, bArr2.length);
            tVar.doFinal(bArr5, 0);
            int i3 = macSize * i2;
            System.arraycopy(bArr5, 0, bArr3, i3, Math.min(macSize, bArr3.length - i3));
            i2++;
            bArr6 = bArr4;
        }
    }
}
