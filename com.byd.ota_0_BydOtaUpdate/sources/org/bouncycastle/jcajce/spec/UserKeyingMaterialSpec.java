package org.bouncycastle.jcajce.spec;

import g.a.f.a;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class UserKeyingMaterialSpec implements AlgorithmParameterSpec {
    private final byte[] userKeyingMaterial;

    public UserKeyingMaterialSpec(byte[] bArr) {
        this.userKeyingMaterial = a.f(bArr);
    }

    public byte[] getUserKeyingMaterial() {
        return a.f(this.userKeyingMaterial);
    }
}
