package org.bouncycastle.pqc.jcajce.provider.mceliece;

import g.a.a.p2.p;
import g.a.a.w2.a;
import g.a.b.i;
import g.a.e.a.c;
import g.a.e.a.e;
import g.a.e.b.a.f;
import g.a.e.d.a.b;
import g.a.e.d.a.h;
import java.io.IOException;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMcEliecePrivateKey implements i, PrivateKey {
    private static final long serialVersionUID = 1;
    private f params;

    public BCMcEliecePrivateKey(f fVar) {
        this.params = fVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCMcEliecePrivateKey)) {
            return false;
        }
        BCMcEliecePrivateKey bCMcEliecePrivateKey = (BCMcEliecePrivateKey) obj;
        return getN() == bCMcEliecePrivateKey.getN() && getK() == bCMcEliecePrivateKey.getK() && getField().equals(bCMcEliecePrivateKey.getField()) && getGoppaPoly().equals(bCMcEliecePrivateKey.getGoppaPoly()) && getSInv().equals(bCMcEliecePrivateKey.getSInv()) && getP1().equals(bCMcEliecePrivateKey.getP1()) && getP2().equals(bCMcEliecePrivateKey.getP2());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "McEliece";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new p(new a(e.m), new c(this.params.f(), this.params.e(), this.params.b(), this.params.c(), this.params.g(), this.params.h(), this.params.j())).e();
        } catch (IOException unused) {
            return null;
        }
    }

    public b getField() {
        return this.params.b();
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public g.a.e.d.a.i getGoppaPoly() {
        return this.params.c();
    }

    public g.a.e.d.a.a getH() {
        return this.params.d();
    }

    public int getK() {
        return this.params.e();
    }

    public g.a.b.j0.b getKeyParams() {
        return this.params;
    }

    public int getN() {
        return this.params.f();
    }

    public h getP1() {
        return this.params.g();
    }

    public h getP2() {
        return this.params.h();
    }

    public g.a.e.d.a.i[] getQInv() {
        return this.params.i();
    }

    public g.a.e.d.a.a getSInv() {
        return this.params.j();
    }

    public int hashCode() {
        return (((((((((((this.params.e() * 37) + this.params.f()) * 37) + this.params.b().hashCode()) * 37) + this.params.c().hashCode()) * 37) + this.params.g().hashCode()) * 37) + this.params.h().hashCode()) * 37) + this.params.j().hashCode();
    }
}
