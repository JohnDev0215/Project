package org.bouncycastle.pqc.jcajce.provider.xmss;

import g.a.a.m;
import g.a.a.w2.u;
import g.a.e.a.e;
import g.a.e.a.i;
import g.a.e.b.e.q;
import g.a.e.b.e.s;
import g.a.e.c.a.f.a;
import java.io.IOException;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCXMSSPublicKey implements PublicKey {
    private final s keyParams;
    private final m treeDigest;

    public BCXMSSPublicKey(m mVar, s sVar) {
        this.treeDigest = mVar;
        this.keyParams = sVar;
    }

    public BCXMSSPublicKey(u uVar) throws IOException {
        i iVarH = i.h(uVar.g().j());
        m mVarG = iVarH.i().g();
        this.treeDigest = mVarG;
        g.a.e.a.m mVarG2 = g.a.e.a.m.g(uVar.k());
        this.keyParams = new s.b(new q(iVarH.g(), a.a(mVarG))).f(mVarG2.h()).g(mVarG2.i()).e();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCXMSSPublicKey)) {
            return false;
        }
        BCXMSSPublicKey bCXMSSPublicKey = (BCXMSSPublicKey) obj;
        return this.treeDigest.equals(bCXMSSPublicKey.treeDigest) && g.a.f.a.a(this.keyParams.e(), bCXMSSPublicKey.keyParams.e());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "XMSS";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new u(new g.a.a.w2.a(e.w, new i(this.keyParams.b().d(), new g.a.a.w2.a(this.treeDigest))), new g.a.e.a.m(this.keyParams.c(), this.keyParams.d())).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public int getHeight() {
        return this.keyParams.b().d();
    }

    public g.a.b.i getKeyParams() {
        return this.keyParams;
    }

    public String getTreeDigest() {
        return a.b(this.treeDigest);
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (g.a.f.a.z(this.keyParams.e()) * 37);
    }
}
