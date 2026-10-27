package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import g.a.a.p2.n;
import g.a.a.q;
import g.a.a.w2.a0;
import g.a.a.y0;
import g.a.b.a0.d;
import g.a.b.i;
import g.a.b.k;
import g.a.b.m0.c;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;

/* JADX INFO: loaded from: classes.dex */
public class SignatureSpi extends java.security.SignatureSpi implements n, a0 {
    private g.a.b.n digest;
    private k signer = new c();

    @Override // java.security.SignatureSpi
    public Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    @Override // java.security.SignatureSpi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void engineInitSign(java.security.PrivateKey r5) throws java.security.InvalidKeyException {
        /*
            r4 = this;
            boolean r0 = r5 instanceof org.bouncycastle.jcajce.provider.asymmetric.dstu.BCDSTU4145PrivateKey
            if (r0 == 0) goto L18
            g.a.b.j0.b r5 = org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil.generatePrivateKeyParameter(r5)
            g.a.b.a0.d r0 = new g.a.b.a0.d
            byte[] r1 = g.a.a.t2.d.h()
            byte[] r1 = r4.expandSbox(r1)
            r0.<init>(r1)
        L15:
            r4.digest = r0
            goto L2f
        L18:
            boolean r0 = r5 instanceof g.a.c.c.a
            if (r0 == 0) goto L2e
            g.a.b.j0.b r5 = org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil.generatePrivateKeyParameter(r5)
            g.a.b.a0.d r0 = new g.a.b.a0.d
            byte[] r1 = g.a.a.t2.d.h()
            byte[] r1 = r4.expandSbox(r1)
            r0.<init>(r1)
            goto L15
        L2e:
            r5 = 0
        L2f:
            java.security.SecureRandom r0 = r4.appRandom
            r1 = 1
            if (r0 == 0) goto L3f
            g.a.b.k r2 = r4.signer
            g.a.b.j0.x0 r3 = new g.a.b.j0.x0
            r3.<init>(r5, r0)
            r2.init(r1, r3)
            goto L44
        L3f:
            g.a.b.k r0 = r4.signer
            r0.init(r1, r5)
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.dstu.SignatureSpi.engineInitSign(java.security.PrivateKey):void");
    }

    @Override // java.security.SignatureSpi
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        i iVarGeneratePublicKeyParameter;
        if (publicKey instanceof BCDSTU4145PublicKey) {
            BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey) publicKey;
            iVarGeneratePublicKeyParameter = bCDSTU4145PublicKey.engineGetKeyParameters();
            this.digest = new d(expandSbox(bCDSTU4145PublicKey.getSbox()));
        } else {
            iVarGeneratePublicKeyParameter = ECUtil.generatePublicKeyParameter(publicKey);
            this.digest = new d(expandSbox(g.a.a.t2.d.h()));
        }
        this.signer.init(false, iVarGeneratePublicKeyParameter);
    }

    @Override // java.security.SignatureSpi
    public void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    public void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    public byte[] engineSign() throws SignatureException {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            BigInteger[] bigIntegerArrA = this.signer.a(bArr);
            byte[] byteArray = bigIntegerArrA[0].toByteArray();
            byte[] byteArray2 = bigIntegerArrA[1].toByteArray();
            int length = (byteArray.length > byteArray2.length ? byteArray.length : byteArray2.length) * 2;
            byte[] bArr2 = new byte[length];
            System.arraycopy(byteArray2, 0, bArr2, (length / 2) - byteArray2.length, byteArray2.length);
            System.arraycopy(byteArray, 0, bArr2, length - byteArray.length, byteArray.length);
            return new y0(bArr2).e();
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    @Override // java.security.SignatureSpi
    public void engineUpdate(byte b2) throws SignatureException {
        this.digest.update(b2);
    }

    @Override // java.security.SignatureSpi
    public void engineUpdate(byte[] bArr, int i2, int i3) throws SignatureException {
        this.digest.update(bArr, i2, i3);
    }

    @Override // java.security.SignatureSpi
    public boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        try {
            byte[] bArrP = ((g.a.a.n) q.j(bArr)).p();
            byte[] bArr3 = new byte[bArrP.length / 2];
            byte[] bArr4 = new byte[bArrP.length / 2];
            System.arraycopy(bArrP, 0, bArr4, 0, bArrP.length / 2);
            System.arraycopy(bArrP, bArrP.length / 2, bArr3, 0, bArrP.length / 2);
            BigInteger[] bigIntegerArr = {new BigInteger(1, bArr3), new BigInteger(1, bArr4)};
            return this.signer.b(bArr2, bigIntegerArr[0], bigIntegerArr[1]);
        } catch (Exception unused) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }

    public byte[] expandSbox(byte[] bArr) {
        byte[] bArr2 = new byte[128];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 * 2;
            bArr2[i3] = (byte) ((bArr[i2] >> 4) & 15);
            bArr2[i3 + 1] = (byte) (bArr[i2] & 15);
        }
        return bArr2;
    }
}
