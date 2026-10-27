package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Tables1kGCMExponentiator implements GCMExponentiator {
    byte[][] lookupPowX2 = new byte[64][];

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        byte[] bArrOneAsBytes = GCMUtil.oneAsBytes();
        int i = 1;
        while (j > 0) {
            if ((1 & j) != 0) {
                GCMUtil.multiply(bArrOneAsBytes, this.lookupPowX2[i]);
            }
            i++;
            j >>>= 1;
        }
        System.arraycopy(bArrOneAsBytes, 0, bArr, 0, 16);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        byte[][] bArr2 = this.lookupPowX2;
        byte[] bArr3 = new byte[16];
        bArr2[0] = bArr3;
        bArr3[0] = -128;
        bArr2[1] = Arrays.clone(bArr);
        for (int i = 2; i != 64; i++) {
            byte[] bArrClone = Arrays.clone(this.lookupPowX2[i - 1]);
            GCMUtil.multiply(bArrClone, bArrClone);
            this.lookupPowX2[i] = bArrClone;
        }
    }
}
