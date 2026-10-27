package org.bouncycastle.jcajce.spec;

import g.a.f.a;
import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class TLSKeyMaterialSpec implements KeySpec {
    public static final String KEY_EXPANSION = "key expansion";
    public static final String MASTER_SECRET = "master secret";
    private final String label;
    private final int length;
    private final byte[] secret;
    private final byte[] seed;

    public TLSKeyMaterialSpec(byte[] bArr, String str, int i2, byte[]... bArr2) {
        this.secret = a.f(bArr);
        this.label = str;
        this.length = i2;
        this.seed = a.p(bArr2);
    }

    public String getLabel() {
        return this.label;
    }

    public int getLength() {
        return this.length;
    }

    public byte[] getSecret() {
        return a.f(this.secret);
    }

    public byte[] getSeed() {
        return a.f(this.seed);
    }
}
