package org.bouncycastle.pqc.jcajce.provider.rainbow;

import g.a.a.p2.p;
import g.a.a.w0;
import g.a.e.a.e;
import g.a.e.a.f;
import g.a.e.b.c.a;
import g.a.e.b.c.d;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BCRainbowPrivateKey implements PrivateKey {
    private static final long serialVersionUID = 1;
    private short[][] A1inv;
    private short[][] A2inv;
    private short[] b1;
    private short[] b2;
    private a[] layers;
    private int[] vi;

    public BCRainbowPrivateKey(d dVar) {
        throw null;
    }

    public BCRainbowPrivateKey(g.a.e.c.b.a aVar) {
        this(aVar.c(), aVar.a(), aVar.d(), aVar.b(), aVar.f(), aVar.e());
    }

    public BCRainbowPrivateKey(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, a[] aVarArr) {
        this.A1inv = sArr;
        this.b1 = sArr2;
        this.A2inv = sArr3;
        this.b2 = sArr4;
        this.vi = iArr;
        this.layers = aVarArr;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCRainbowPrivateKey)) {
            return false;
        }
        BCRainbowPrivateKey bCRainbowPrivateKey = (BCRainbowPrivateKey) obj;
        boolean zEquals = ((((g.a.e.b.c.f.a.j(this.A1inv, bCRainbowPrivateKey.getInvA1())) && g.a.e.b.c.f.a.j(this.A2inv, bCRainbowPrivateKey.getInvA2())) && g.a.e.b.c.f.a.i(this.b1, bCRainbowPrivateKey.getB1())) && g.a.e.b.c.f.a.i(this.b2, bCRainbowPrivateKey.getB2())) && Arrays.equals(this.vi, bCRainbowPrivateKey.getVi());
        if (this.layers.length != bCRainbowPrivateKey.getLayers().length) {
            return false;
        }
        for (int length = this.layers.length - 1; length >= 0; length--) {
            zEquals &= this.layers[length].equals(bCRainbowPrivateKey.getLayers()[length]);
        }
        return zEquals;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "Rainbow";
    }

    public short[] getB1() {
        return this.b1;
    }

    public short[] getB2() {
        return this.b2;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new p(new g.a.a.w2.a(e.f5569a, w0.f4105c), new f(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers)).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public a[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.vi;
    }

    public int hashCode() {
        int length = (((((((((this.layers.length * 37) + g.a.f.a.F(this.A1inv)) * 37) + g.a.f.a.E(this.b1)) * 37) + g.a.f.a.F(this.A2inv)) * 37) + g.a.f.a.E(this.b2)) * 37) + g.a.f.a.B(this.vi);
        for (int length2 = this.layers.length - 1; length2 >= 0; length2--) {
            length = (length * 37) + this.layers[length2].hashCode();
        }
        return length;
    }
}
