package org.bouncycastle.pqc.jcajce.provider.newhope;

import g.a.a.n;
import g.a.a.p2.p;
import g.a.a.y0;
import g.a.b.i;
import g.a.e.a.e;
import g.a.e.b.b.a;
import g.a.f.f;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCNHPrivateKey implements Key, PrivateKey {
    private static final long serialVersionUID = 1;
    private final a params;

    public BCNHPrivateKey(p pVar) throws IOException {
        this.params = new a(a(n.n(pVar.i()).p()));
    }

    public BCNHPrivateKey(a aVar) {
        this.params = aVar;
    }

    public static short[] a(byte[] bArr) {
        int length = bArr.length / 2;
        short[] sArr = new short[length];
        for (int i2 = 0; i2 != length; i2++) {
            sArr[i2] = f.p(bArr, i2 * 2);
        }
        return sArr;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCNHPrivateKey)) {
            return false;
        }
        return g.a.f.a.d(this.params.b(), ((BCNHPrivateKey) obj).params.b());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "NH";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            g.a.a.w2.a aVar = new g.a.a.w2.a(e.v);
            short[] sArrB = this.params.b();
            byte[] bArr = new byte[sArrB.length * 2];
            for (int i2 = 0; i2 != sArrB.length; i2++) {
                f.y(sArrB[i2], bArr, i2 * 2);
            }
            return new p(aVar, new y0(bArr)).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public i getKeyParams() {
        return this.params;
    }

    public short[] getSecretData() {
        return this.params.b();
    }

    public int hashCode() {
        return g.a.f.a.E(this.params.b());
    }
}
