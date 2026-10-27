package org.bouncycastle.jcajce;

import g.a.f.a;
import javax.crypto.interfaces.PBEKey;

/* JADX INFO: loaded from: classes.dex */
public class PKCS12KeyWithParameters extends PKCS12Key implements PBEKey {
    private final int iterationCount;
    private final byte[] salt;

    public PKCS12KeyWithParameters(char[] cArr, boolean z, byte[] bArr, int i2) {
        super(cArr, z);
        this.salt = a.f(bArr);
        this.iterationCount = i2;
    }

    public PKCS12KeyWithParameters(char[] cArr, byte[] bArr, int i2) {
        super(cArr);
        this.salt = a.f(bArr);
        this.iterationCount = i2;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        return this.iterationCount;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        return this.salt;
    }
}
