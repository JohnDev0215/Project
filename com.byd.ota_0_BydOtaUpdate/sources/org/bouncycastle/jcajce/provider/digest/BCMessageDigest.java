package org.bouncycastle.jcajce.provider.digest;

import g.a.b.n;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class BCMessageDigest extends MessageDigest {
    public n digest;

    public BCMessageDigest(n nVar) {
        super(nVar.getAlgorithmName());
        this.digest = nVar;
    }

    @Override // java.security.MessageDigestSpi
    public byte[] engineDigest() {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        return bArr;
    }

    @Override // java.security.MessageDigestSpi
    public void engineReset() {
        this.digest.reset();
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte b2) {
        this.digest.update(b2);
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte[] bArr, int i2, int i3) {
        this.digest.update(bArr, i2, i3);
    }
}
