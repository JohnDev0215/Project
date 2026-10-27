package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import g.a.a.n;
import g.a.a.q;
import g.a.a.y0;
import java.io.IOException;
import java.security.SignatureException;

/* JADX INFO: loaded from: classes.dex */
public class SignatureSpiLe extends SignatureSpi {
    @Override // org.bouncycastle.jcajce.provider.asymmetric.dstu.SignatureSpi, java.security.SignatureSpi
    public byte[] engineSign() throws SignatureException {
        byte[] bArrP = n.n(super.engineSign()).p();
        reverseBytes(bArrP);
        try {
            return new y0(bArrP).e();
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.dstu.SignatureSpi, java.security.SignatureSpi
    public boolean engineVerify(byte[] bArr) throws SignatureException {
        try {
            byte[] bArrP = ((n) q.j(bArr)).p();
            reverseBytes(bArrP);
            try {
                return super.engineVerify(new y0(bArrP).e());
            } catch (SignatureException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new SignatureException(e3.toString());
            }
        } catch (IOException unused) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }

    public void reverseBytes(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length / 2; i2++) {
            byte b2 = bArr[i2];
            bArr[i2] = bArr[(bArr.length - 1) - i2];
            bArr[(bArr.length - 1) - i2] = b2;
        }
    }
}
