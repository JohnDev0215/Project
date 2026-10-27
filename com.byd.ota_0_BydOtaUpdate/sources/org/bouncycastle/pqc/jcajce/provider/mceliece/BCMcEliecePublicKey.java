package org.bouncycastle.pqc.jcajce.provider.mceliece;

import g.a.a.w2.a;
import g.a.a.w2.u;
import g.a.b.j0.b;
import g.a.e.a.d;
import g.a.e.a.e;
import g.a.e.b.a.g;
import java.io.IOException;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMcEliecePublicKey implements PublicKey {
    private static final long serialVersionUID = 1;
    private g params;

    public BCMcEliecePublicKey(g gVar) {
        this.params = gVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCMcEliecePublicKey)) {
            return false;
        }
        BCMcEliecePublicKey bCMcEliecePublicKey = (BCMcEliecePublicKey) obj;
        return this.params.d() == bCMcEliecePublicKey.getN() && this.params.e() == bCMcEliecePublicKey.getT() && this.params.b().equals(bCMcEliecePublicKey.getG());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "McEliece";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new u(new a(e.m), new d(this.params.d(), this.params.e(), this.params.b())).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public g.a.e.d.a.a getG() {
        return this.params.b();
    }

    public int getK() {
        return this.params.c();
    }

    public b getKeyParams() {
        return this.params;
    }

    public int getN() {
        return this.params.d();
    }

    public int getT() {
        return this.params.e();
    }

    public int hashCode() {
        return ((this.params.d() + (this.params.e() * 37)) * 37) + this.params.b().hashCode();
    }

    public String toString() {
        return (("McEliecePublicKey:\n length of the code         : " + this.params.d() + "\n") + " error correction capability: " + this.params.e() + "\n") + " generator matrix           : " + this.params.b();
    }
}
