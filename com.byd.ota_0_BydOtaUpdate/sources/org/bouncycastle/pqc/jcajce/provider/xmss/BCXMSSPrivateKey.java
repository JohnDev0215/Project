package org.bouncycastle.pqc.jcajce.provider.xmss;

import g.a.a.m;
import g.a.a.p2.p;
import g.a.e.a.e;
import g.a.e.a.i;
import g.a.e.a.l;
import g.a.e.b.e.q;
import g.a.e.b.e.r;
import g.a.e.b.e.t;
import g.a.e.c.a.f.a;
import java.io.IOException;
import java.security.PrivateKey;
import org.bouncycastle.pqc.crypto.xmss.BDS;

/* JADX INFO: loaded from: classes.dex */
public class BCXMSSPrivateKey implements PrivateKey {
    private final r keyParams;
    private final m treeDigest;

    public BCXMSSPrivateKey(m mVar, r rVar) {
        this.treeDigest = mVar;
        this.keyParams = rVar;
    }

    public BCXMSSPrivateKey(p pVar) throws IOException {
        i iVarH = i.h(pVar.h().j());
        m mVarG = iVarH.i().g();
        this.treeDigest = mVarG;
        l lVarI = l.i(pVar.i());
        try {
            r.b bVarN = new r.b(new q(iVarH.g(), a.a(mVarG))).l(lVarI.h()).p(lVarI.m()).o(lVarI.l()).m(lVarI.j()).n(lVarI.k());
            if (lVarI.g() != null) {
                bVarN.k((BDS) t.f(lVarI.g(), BDS.class));
            }
            this.keyParams = bVarN.j();
        } catch (ClassNotFoundException e2) {
            throw new IOException("ClassNotFoundException processing BDS state: " + e2.getMessage());
        }
    }

    public final l a() {
        byte[] bArrC = this.keyParams.c();
        int iC = this.keyParams.b().c();
        int iD = this.keyParams.b().d();
        int iA = (int) t.a(bArrC, 0, 4);
        if (!t.l(iD, iA)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        byte[] bArrG = t.g(bArrC, 4, iC);
        int i2 = 4 + iC;
        byte[] bArrG2 = t.g(bArrC, i2, iC);
        int i3 = i2 + iC;
        byte[] bArrG3 = t.g(bArrC, i3, iC);
        int i4 = i3 + iC;
        byte[] bArrG4 = t.g(bArrC, i4, iC);
        int i5 = i4 + iC;
        return new l(iA, bArrG, bArrG2, bArrG3, bArrG4, t.g(bArrC, i5, bArrC.length - i5));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCXMSSPrivateKey)) {
            return false;
        }
        BCXMSSPrivateKey bCXMSSPrivateKey = (BCXMSSPrivateKey) obj;
        return this.treeDigest.equals(bCXMSSPrivateKey.treeDigest) && g.a.f.a.a(this.keyParams.c(), bCXMSSPrivateKey.keyParams.c());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "XMSS";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new p(new g.a.a.w2.a(e.w, new i(this.keyParams.b().d(), new g.a.a.w2.a(this.treeDigest))), a()).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
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

    public m getTreeDigestOID() {
        return this.treeDigest;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (g.a.f.a.z(this.keyParams.c()) * 37);
    }
}
