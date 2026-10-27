package org.bouncycastle.jcajce.spec;

import g.a.f.a;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {
    private byte[] id;

    public SM2ParameterSpec(byte[] bArr) {
        Objects.requireNonNull(bArr, "id string cannot be null");
        this.id = a.f(bArr);
    }

    public byte[] getID() {
        return a.f(this.id);
    }
}
