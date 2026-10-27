package org.bouncycastle.pqc.jcajce.provider.newhope;

import g.a.a.w2.u;
import g.a.b.i;
import g.a.e.a.e;
import g.a.e.b.b.b;
import g.a.f.a;
import java.io.IOException;
import java.security.Key;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCNHPublicKey implements Key, PublicKey {
    private static final long serialVersionUID = 1;
    private final b params;

    public BCNHPublicKey(u uVar) {
        this.params = new b(uVar.j().p());
    }

    public BCNHPublicKey(b bVar) {
        this.params = bVar;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCNHPublicKey)) {
            return false;
        }
        return a.a(this.params.b(), ((BCNHPublicKey) obj).params.b());
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "NH";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new u(new g.a.a.w2.a(e.v), this.params.b()).e();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public i getKeyParams() {
        return this.params;
    }

    public byte[] getPublicData() {
        return this.params.b();
    }

    public int hashCode() {
        return a.z(this.params.b());
    }
}
