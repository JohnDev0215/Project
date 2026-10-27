package org.bouncycastle.pqc.jcajce.provider.mceliece;

import g.a.a.p2.p;
import g.a.a.w2.a;
import g.a.e.a.e;
import g.a.e.b.a.b;
import g.a.e.c.a.a.c;
import g.a.e.d.a.h;
import g.a.e.d.a.i;
import java.io.IOException;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMcElieceCCA2PrivateKey implements PrivateKey {
    private static final long serialVersionUID = 1;
    private b params;

    public BCMcElieceCCA2PrivateKey(b bVar) {
        this.params = bVar;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCMcElieceCCA2PrivateKey)) {
            return false;
        }
        BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey = (BCMcElieceCCA2PrivateKey) obj;
        return getN() == bCMcElieceCCA2PrivateKey.getN() && getK() == bCMcElieceCCA2PrivateKey.getK() && getField().equals(bCMcElieceCCA2PrivateKey.getField()) && getGoppaPoly().equals(bCMcElieceCCA2PrivateKey.getGoppaPoly()) && getP().equals(bCMcElieceCCA2PrivateKey.getP()) && getH().equals(bCMcElieceCCA2PrivateKey.getH());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "McEliece-CCA2";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new p(new a(e.n), new g.a.e.a.a(getN(), getK(), getField(), getGoppaPoly(), getP(), c.a(this.params.b()))).e();
        } catch (IOException unused) {
            return null;
        }
    }

    public g.a.e.d.a.b getField() {
        return this.params.c();
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public i getGoppaPoly() {
        return this.params.d();
    }

    public g.a.e.d.a.a getH() {
        return this.params.e();
    }

    public int getK() {
        return this.params.f();
    }

    public g.a.b.j0.b getKeyParams() {
        return this.params;
    }

    public int getN() {
        return this.params.g();
    }

    public h getP() {
        return this.params.h();
    }

    public i[] getQInv() {
        return this.params.i();
    }

    public int getT() {
        return this.params.d().g();
    }

    public int hashCode() {
        return (((((((((this.params.f() * 37) + this.params.g()) * 37) + this.params.c().hashCode()) * 37) + this.params.d().hashCode()) * 37) + this.params.h().hashCode()) * 37) + this.params.e().hashCode();
    }
}
