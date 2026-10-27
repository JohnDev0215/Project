package org.bouncycastle.pqc.jcajce.provider.mceliece;

import g.a.a.w2.a;
import g.a.a.w2.u;
import g.a.b.i;
import g.a.e.a.b;
import g.a.e.a.e;
import g.a.e.b.a.c;
import java.io.IOException;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMcElieceCCA2PublicKey implements i, PublicKey {
    private static final long serialVersionUID = 1;
    private c params;

    public BCMcElieceCCA2PublicKey(c cVar) {
        this.params = cVar;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCMcElieceCCA2PublicKey)) {
            return false;
        }
        BCMcElieceCCA2PublicKey bCMcElieceCCA2PublicKey = (BCMcElieceCCA2PublicKey) obj;
        return this.params.e() == bCMcElieceCCA2PublicKey.getN() && this.params.f() == bCMcElieceCCA2PublicKey.getT() && this.params.c().equals(bCMcElieceCCA2PublicKey.getG());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "McEliece-CCA2";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new u(new a(e.n), new b(this.params.e(), this.params.f(), this.params.c(), g.a.e.c.a.a.c.a(this.params.b()))).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public g.a.e.d.a.a getG() {
        return this.params.c();
    }

    public int getK() {
        return this.params.d();
    }

    public g.a.b.j0.b getKeyParams() {
        return this.params;
    }

    public int getN() {
        return this.params.e();
    }

    public int getT() {
        return this.params.f();
    }

    public int hashCode() {
        return ((this.params.e() + (this.params.f() * 37)) * 37) + this.params.c().hashCode();
    }

    public String toString() {
        return (("McEliecePublicKey:\n length of the code         : " + this.params.e() + "\n") + " error correction capability: " + this.params.f() + "\n") + " generator matrix           : " + this.params.c().toString();
    }
}
