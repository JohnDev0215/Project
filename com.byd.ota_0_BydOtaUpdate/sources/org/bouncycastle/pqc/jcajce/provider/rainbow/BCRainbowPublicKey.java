package org.bouncycastle.pqc.jcajce.provider.rainbow;

import g.a.a.w0;
import g.a.e.a.g;
import g.a.e.b.c.c;
import g.a.e.b.c.e;
import g.a.e.b.c.f.a;
import g.a.e.c.b.b;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCRainbowPublicKey implements PublicKey {
    private static final long serialVersionUID = 1;
    private short[][] coeffquadratic;
    private short[] coeffscalar;
    private short[][] coeffsingular;
    private int docLength;
    private c rainbowParams;

    public BCRainbowPublicKey(int i2, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.docLength = i2;
        this.coeffquadratic = sArr;
        this.coeffsingular = sArr2;
        this.coeffscalar = sArr3;
    }

    public BCRainbowPublicKey(e eVar) {
        throw null;
    }

    public BCRainbowPublicKey(b bVar) {
        this(bVar.d(), bVar.a(), bVar.c(), bVar.b());
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCRainbowPublicKey)) {
            return false;
        }
        BCRainbowPublicKey bCRainbowPublicKey = (BCRainbowPublicKey) obj;
        return this.docLength == bCRainbowPublicKey.getDocLength() && a.j(this.coeffquadratic, bCRainbowPublicKey.getCoeffQuadratic()) && a.j(this.coeffsingular, bCRainbowPublicKey.getCoeffSingular()) && a.i(this.coeffscalar, bCRainbowPublicKey.getCoeffScalar());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "Rainbow";
    }

    public short[][] getCoeffQuadratic() {
        return this.coeffquadratic;
    }

    public short[] getCoeffScalar() {
        return g.a.f.a.l(this.coeffscalar);
    }

    public short[][] getCoeffSingular() {
        short[][] sArr = new short[this.coeffsingular.length][];
        int i2 = 0;
        while (true) {
            short[][] sArr2 = this.coeffsingular;
            if (i2 == sArr2.length) {
                return sArr;
            }
            sArr[i2] = g.a.f.a.l(sArr2[i2]);
            i2++;
        }
    }

    public int getDocLength() {
        return this.docLength;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return g.a.e.c.a.e.a.a(new g.a.a.w2.a(g.a.e.a.e.f5569a, w0.f4105c), new g(this.docLength, this.coeffquadratic, this.coeffsingular, this.coeffscalar));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public int hashCode() {
        return (((((this.docLength * 37) + g.a.f.a.F(this.coeffquadratic)) * 37) + g.a.f.a.F(this.coeffsingular)) * 37) + g.a.f.a.E(this.coeffscalar);
    }
}
