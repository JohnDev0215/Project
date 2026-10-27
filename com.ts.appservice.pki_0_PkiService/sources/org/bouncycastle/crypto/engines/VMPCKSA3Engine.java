package org.bouncycastle.crypto.engines;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class VMPCKSA3Engine extends VMPCEngine {
    @Override // org.bouncycastle.crypto.engines.VMPCEngine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }

    @Override // org.bouncycastle.crypto.engines.VMPCEngine
    protected void initKey(byte[] bArr, byte[] bArr2) {
        this.s = (byte) 0;
        this.P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.P[i] = (byte) i;
        }
        for (int i2 = 0; i2 < 768; i2++) {
            byte[] bArr3 = this.P;
            int i3 = i2 & 255;
            byte b2 = bArr3[(this.s + bArr3[i3] + bArr[i2 % bArr.length]) & 255];
            this.s = b2;
            byte b3 = bArr3[i3];
            bArr3[i3] = bArr3[b2 & 255];
            bArr3[b2 & 255] = b3;
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.P;
            int i5 = i4 & 255;
            byte b4 = bArr4[(this.s + bArr4[i5] + bArr2[i4 % bArr2.length]) & 255];
            this.s = b4;
            byte b5 = bArr4[i5];
            bArr4[i5] = bArr4[b4 & 255];
            bArr4[b4 & 255] = b5;
        }
        for (int i6 = 0; i6 < 768; i6++) {
            byte[] bArr5 = this.P;
            int i7 = i6 & 255;
            byte b6 = bArr5[(this.s + bArr5[i7] + bArr[i6 % bArr.length]) & 255];
            this.s = b6;
            byte b7 = bArr5[i7];
            bArr5[i7] = bArr5[b6 & 255];
            bArr5[b6 & 255] = b7;
        }
        this.n = (byte) 0;
    }
}
