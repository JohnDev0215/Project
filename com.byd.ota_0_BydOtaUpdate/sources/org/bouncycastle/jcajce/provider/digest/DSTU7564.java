package org.bouncycastle.jcajce.provider.digest;

import g.a.a.t2.f;
import g.a.b.a0.c;
import g.a.b.g0.d;
import g.a.b.h;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;

/* JADX INFO: loaded from: classes.dex */
public class DSTU7564 {

    public static class Digest256 extends DigestDSTU7564 {
        public Digest256() {
            super(256);
        }
    }

    public static class Digest384 extends DigestDSTU7564 {
        public Digest384() {
            super(384);
        }
    }

    public static class Digest512 extends DigestDSTU7564 {
        public Digest512() {
            super(512);
        }
    }

    public static class DigestDSTU7564 extends BCMessageDigest implements Cloneable {
        public DigestDSTU7564(int i2) {
            super(new c(i2));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            BCMessageDigest bCMessageDigest = (BCMessageDigest) super.clone();
            bCMessageDigest.digest = new c((c) this.digest);
            return bCMessageDigest;
        }
    }

    public static class HashMac256 extends BaseMac {
        public HashMac256() {
            super(new d(256));
        }
    }

    public static class HashMac384 extends BaseMac {
        public HashMac384() {
            super(new d(384));
        }
    }

    public static class HashMac512 extends BaseMac {
        public HashMac512() {
            super(new d(512));
        }
    }

    public static class KeyGenerator256 extends BaseKeyGenerator {
        public KeyGenerator256() {
            super("HMACDSTU7564-256", 256, new h());
        }
    }

    public static class KeyGenerator384 extends BaseKeyGenerator {
        public KeyGenerator384() {
            super("HMACDSTU7564-384", 384, new h());
        }
    }

    public static class KeyGenerator512 extends BaseKeyGenerator {
        public KeyGenerator512() {
            super("HMACDSTU7564-512", 512, new h());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = DSTU7564.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$Digest256");
            configurableProvider.addAlgorithm("MessageDigest.DSTU7564-256", sb.toString());
            configurableProvider.addAlgorithm("MessageDigest.DSTU7564-384", str + "$Digest384");
            configurableProvider.addAlgorithm("MessageDigest.DSTU7564-512", str + "$Digest512");
            configurableProvider.addAlgorithm("MessageDigest", f.f4068d, str + "$Digest256");
            configurableProvider.addAlgorithm("MessageDigest", f.f4069e, str + "$Digest384");
            configurableProvider.addAlgorithm("MessageDigest", f.f4070f, str + "$Digest512");
            addHMACAlgorithm(configurableProvider, "DSTU7564-256", str + "$HashMac256", str + "$KeyGenerator256");
            addHMACAlgorithm(configurableProvider, "DSTU7564-384", str + "$HashMac384", str + "$KeyGenerator384");
            addHMACAlgorithm(configurableProvider, "DSTU7564-512", str + "$HashMac512", str + "$KeyGenerator512");
            addHMACAlias(configurableProvider, "DSTU7564-256", f.f4071g);
            addHMACAlias(configurableProvider, "DSTU7564-384", f.f4072h);
            addHMACAlias(configurableProvider, "DSTU7564-512", f.f4073i);
        }
    }

    private DSTU7564() {
    }
}
