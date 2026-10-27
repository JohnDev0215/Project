package org.bouncycastle.pqc.jcajce.provider.sphincs;

import g.a.a.m;
import g.a.a.n;
import g.a.a.p2.p;
import g.a.a.y0;
import g.a.b.i;
import g.a.e.a.e;
import g.a.e.a.h;
import g.a.e.b.d.a;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCSphincs256PrivateKey implements PrivateKey, Key {
    private static final long serialVersionUID = 1;
    private final a params;
    private final m treeDigest;

    public BCSphincs256PrivateKey(m mVar, a aVar) {
        this.treeDigest = mVar;
        this.params = aVar;
    }

    public BCSphincs256PrivateKey(p pVar) throws IOException {
        this.treeDigest = h.g(pVar.h().j()).h().g();
        this.params = new a(n.n(pVar.i()).p());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCSphincs256PrivateKey)) {
            return false;
        }
        BCSphincs256PrivateKey bCSphincs256PrivateKey = (BCSphincs256PrivateKey) obj;
        return this.treeDigest.equals(bCSphincs256PrivateKey.treeDigest) && g.a.f.a.a(this.params.b(), bCSphincs256PrivateKey.params.b());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "SPHINCS-256";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new p(new g.a.a.w2.a(e.r, new h(new g.a.a.w2.a(this.treeDigest))), new y0(this.params.b())).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getKeyData() {
        return this.params.b();
    }

    public i getKeyParams() {
        return this.params;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (g.a.f.a.z(this.params.b()) * 37);
    }
}
