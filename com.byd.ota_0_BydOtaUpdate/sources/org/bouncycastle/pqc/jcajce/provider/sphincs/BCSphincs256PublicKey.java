package org.bouncycastle.pqc.jcajce.provider.sphincs;

import g.a.a.m;
import g.a.a.w2.u;
import g.a.b.i;
import g.a.e.a.e;
import g.a.e.a.h;
import g.a.e.b.d.b;
import g.a.f.a;
import java.io.IOException;
import java.security.Key;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCSphincs256PublicKey implements PublicKey, Key {
    private static final long serialVersionUID = 1;
    private final b params;
    private final m treeDigest;

    public BCSphincs256PublicKey(m mVar, b bVar) {
        this.treeDigest = mVar;
        this.params = bVar;
    }

    public BCSphincs256PublicKey(u uVar) {
        this.treeDigest = h.g(uVar.g().j()).h().g();
        this.params = new b(uVar.j().p());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCSphincs256PublicKey)) {
            return false;
        }
        BCSphincs256PublicKey bCSphincs256PublicKey = (BCSphincs256PublicKey) obj;
        return this.treeDigest.equals(bCSphincs256PublicKey.treeDigest) && a.a(this.params.b(), bCSphincs256PublicKey.params.b());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "SPHINCS-256";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new u(new g.a.a.w2.a(e.r, new h(new g.a.a.w2.a(this.treeDigest))), this.params.b()).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public byte[] getKeyData() {
        return this.params.b();
    }

    public i getKeyParams() {
        return this.params;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (a.z(this.params.b()) * 37);
    }
}
