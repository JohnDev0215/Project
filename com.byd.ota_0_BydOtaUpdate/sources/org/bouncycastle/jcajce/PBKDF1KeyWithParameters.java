package org.bouncycastle.jcajce;

import g.a.b.g;
import g.a.f.a;
import javax.crypto.interfaces.PBEKey;

/* JADX INFO: loaded from: classes.dex */
public class PBKDF1KeyWithParameters extends PBKDF1Key implements PBEKey {
    private final int iterationCount;
    private final byte[] salt;

    public PBKDF1KeyWithParameters(char[] cArr, g gVar, byte[] bArr, int i2) {
        super(cArr, gVar);
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
