package org.bouncycastle.pqc.jcajce.provider.xmss;

import g.a.a.m;
import g.a.a.w2.u;
import g.a.b.i;
import g.a.e.a.e;
import g.a.e.a.j;
import g.a.e.b.e.l;
import g.a.e.b.e.n;
import g.a.e.c.a.f.a;
import java.io.IOException;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCXMSSMTPublicKey implements PublicKey {
    private final n keyParams;
    private final m treeDigest;

    public BCXMSSMTPublicKey(m mVar, n nVar) {
        this.treeDigest = mVar;
        this.keyParams = nVar;
    }

    public BCXMSSMTPublicKey(u uVar) throws IOException {
        j jVarH = j.h(uVar.g().j());
        m mVarG = jVarH.j().g();
        this.treeDigest = mVarG;
        g.a.e.a.m mVarG2 = g.a.e.a.m.g(uVar.k());
        this.keyParams = new n.b(new l(jVarH.g(), jVarH.i(), a.a(mVarG))).f(mVarG2.h()).g(mVarG2.i()).e();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCXMSSMTPublicKey)) {
            return false;
        }
        BCXMSSMTPublicKey bCXMSSMTPublicKey = (BCXMSSMTPublicKey) obj;
        return this.treeDigest.equals(bCXMSSMTPublicKey.treeDigest) && g.a.f.a.a(this.keyParams.e(), bCXMSSMTPublicKey.keyParams.e());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "XMSSMT";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new u(new g.a.a.w2.a(e.B, new j(this.keyParams.b().c(), this.keyParams.b().d(), new g.a.a.w2.a(this.treeDigest))), new g.a.e.a.m(this.keyParams.c(), this.keyParams.d())).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public int getHeight() {
        return this.keyParams.b().c();
    }

    public i getKeyParams() {
        return this.keyParams;
    }

    public int getLayers() {
        return this.keyParams.b().d();
    }

    public String getTreeDigest() {
        return a.b(this.treeDigest);
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (g.a.f.a.z(this.keyParams.e()) * 37);
    }
}
