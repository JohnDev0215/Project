package org.bouncycastle.pqc.jcajce.provider.xmss;

import g.a.a.p2.p;
import g.a.b.i;
import g.a.e.a.e;
import g.a.e.a.j;
import g.a.e.a.k;
import g.a.e.a.l;
import g.a.e.b.e.m;
import g.a.e.b.e.t;
import g.a.e.c.a.f.a;
import java.io.IOException;
import java.security.PrivateKey;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;

/* JADX INFO: loaded from: classes.dex */
public class BCXMSSMTPrivateKey implements PrivateKey {
    private final m keyParams;
    private final g.a.a.m treeDigest;

    public BCXMSSMTPrivateKey(g.a.a.m mVar, m mVar2) {
        this.treeDigest = mVar;
        this.keyParams = mVar2;
    }

    public BCXMSSMTPrivateKey(p pVar) throws IOException {
        j jVarH = j.h(pVar.h().j());
        g.a.a.m mVarG = jVarH.j().g();
        this.treeDigest = mVarG;
        l lVarI = l.i(pVar.i());
        try {
            m.b bVarN = new m.b(new g.a.e.b.e.l(jVarH.g(), jVarH.i(), a.a(mVarG))).l(lVarI.h()).p(lVarI.m()).o(lVarI.l()).m(lVarI.j()).n(lVarI.k());
            if (lVarI.g() != null) {
                bVarN.k((BDSStateMap) t.f(lVarI.g(), BDSStateMap.class));
            }
            this.keyParams = bVarN.j();
        } catch (ClassNotFoundException e2) {
            throw new IOException("ClassNotFoundException processing BDS state: " + e2.getMessage());
        }
    }

    public final k a() {
        byte[] bArrC = this.keyParams.c();
        int iB = this.keyParams.b().b();
        int iC = this.keyParams.b().c();
        int i2 = (iC + 7) / 8;
        int iA = (int) t.a(bArrC, 0, i2);
        if (!t.l(iC, iA)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        int i3 = i2 + 0;
        byte[] bArrG = t.g(bArrC, i3, iB);
        int i4 = i3 + iB;
        byte[] bArrG2 = t.g(bArrC, i4, iB);
        int i5 = i4 + iB;
        byte[] bArrG3 = t.g(bArrC, i5, iB);
        int i6 = i5 + iB;
        byte[] bArrG4 = t.g(bArrC, i6, iB);
        int i7 = i6 + iB;
        return new k(iA, bArrG, bArrG2, bArrG3, bArrG4, t.g(bArrC, i7, bArrC.length - i7));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCXMSSMTPrivateKey)) {
            return false;
        }
        BCXMSSMTPrivateKey bCXMSSMTPrivateKey = (BCXMSSMTPrivateKey) obj;
        return this.treeDigest.equals(bCXMSSMTPrivateKey.treeDigest) && g.a.f.a.a(this.keyParams.c(), bCXMSSMTPrivateKey.keyParams.c());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "XMSSMT";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new p(new g.a.a.w2.a(e.B, new j(this.keyParams.b().c(), this.keyParams.b().d(), new g.a.a.w2.a(this.treeDigest))), a()).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
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

    public g.a.a.m getTreeDigestOID() {
        return this.treeDigest;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (g.a.f.a.z(this.keyParams.c()) * 37);
    }
}
