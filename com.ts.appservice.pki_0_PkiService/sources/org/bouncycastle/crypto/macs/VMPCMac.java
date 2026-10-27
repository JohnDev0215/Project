package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class VMPCMac implements Mac {
    private byte[] T;
    private byte g;
    private byte[] workingIV;
    private byte[] workingKey;
    private byte x1;
    private byte x2;
    private byte x3;
    private byte x4;
    private byte n = 0;
    private byte[] P = null;
    private byte s = 0;

    private void initKey(byte[] bArr, byte[] bArr2) {
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
        this.n = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        for (int i2 = 1; i2 < 25; i2++) {
            byte[] bArr2 = this.P;
            byte b2 = this.s;
            byte b3 = this.n;
            byte b4 = bArr2[(b2 + bArr2[b3 & 255]) & 255];
            this.s = b4;
            byte b5 = this.x4;
            byte b6 = this.x3;
            byte b7 = bArr2[(b5 + b6 + i2) & 255];
            this.x4 = b7;
            byte b8 = this.x2;
            byte b9 = bArr2[(b6 + b8 + i2) & 255];
            this.x3 = b9;
            byte b10 = this.x1;
            byte b11 = bArr2[(b8 + b10 + i2) & 255];
            this.x2 = b11;
            byte b12 = bArr2[(b10 + b4 + i2) & 255];
            this.x1 = b12;
            byte[] bArr3 = this.T;
            byte b13 = this.g;
            bArr3[b13 & 31] = (byte) (b12 ^ bArr3[b13 & 31]);
            bArr3[(b13 + 1) & 31] = (byte) (b11 ^ bArr3[(b13 + 1) & 31]);
            bArr3[(b13 + 2) & 31] = (byte) (b9 ^ bArr3[(b13 + 2) & 31]);
            bArr3[(b13 + 3) & 31] = (byte) (b7 ^ bArr3[(b13 + 3) & 31]);
            this.g = (byte) ((b13 + 4) & 31);
            byte b14 = bArr2[b3 & 255];
            bArr2[b3 & 255] = bArr2[b4 & 255];
            bArr2[b4 & 255] = b14;
            this.n = (byte) ((b3 + 1) & 255);
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr4 = this.P;
            int i4 = i3 & 255;
            byte b15 = bArr4[(this.s + bArr4[i4] + this.T[i3 & 31]) & 255];
            this.s = b15;
            byte b16 = bArr4[i4];
            bArr4[i4] = bArr4[b15 & 255];
            bArr4[b15 & 255] = b16;
        }
        byte[] bArr5 = new byte[20];
        for (int i5 = 0; i5 < 20; i5++) {
            byte[] bArr6 = this.P;
            int i6 = i5 & 255;
            byte b17 = bArr6[(this.s + bArr6[i6]) & 255];
            this.s = b17;
            bArr5[i5] = bArr6[(bArr6[bArr6[b17 & 255] & 255] + 1) & 255];
            byte b18 = bArr6[i6];
            bArr6[i6] = bArr6[b17 & 255];
            bArr6[b17 & 255] = b18;
        }
        System.arraycopy(bArr5, 0, bArr, i, 20);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        byte[] iv = parametersWithIV.getIV();
        this.workingIV = iv;
        if (iv == null || iv.length < 1 || iv.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.workingKey = keyParameter.getKey();
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        initKey(this.workingKey, this.workingIV);
        this.n = (byte) 0;
        this.x4 = (byte) 0;
        this.x3 = (byte) 0;
        this.x2 = (byte) 0;
        this.x1 = (byte) 0;
        this.g = (byte) 0;
        this.T = new byte[32];
        for (int i = 0; i < 32; i++) {
            this.T[i] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b2) {
        byte[] bArr = this.P;
        byte b3 = this.s;
        byte b4 = this.n;
        byte b5 = bArr[(b3 + bArr[b4 & 255]) & 255];
        this.s = b5;
        byte b6 = (byte) (b2 ^ bArr[(bArr[bArr[b5 & 255] & 255] + 1) & 255]);
        byte b7 = this.x4;
        byte b8 = this.x3;
        byte b9 = bArr[(b7 + b8) & 255];
        this.x4 = b9;
        byte b10 = this.x2;
        byte b11 = bArr[(b8 + b10) & 255];
        this.x3 = b11;
        byte b12 = this.x1;
        byte b13 = bArr[(b10 + b12) & 255];
        this.x2 = b13;
        byte b14 = bArr[(b12 + b5 + b6) & 255];
        this.x1 = b14;
        byte[] bArr2 = this.T;
        byte b15 = this.g;
        bArr2[b15 & 31] = (byte) (b14 ^ bArr2[b15 & 31]);
        bArr2[(b15 + 1) & 31] = (byte) (b13 ^ bArr2[(b15 + 1) & 31]);
        bArr2[(b15 + 2) & 31] = (byte) (b11 ^ bArr2[(b15 + 2) & 31]);
        bArr2[(b15 + 3) & 31] = (byte) (b9 ^ bArr2[(b15 + 3) & 31]);
        this.g = (byte) ((b15 + 4) & 31);
        byte b16 = bArr[b4 & 255];
        bArr[b4 & 255] = bArr[b5 & 255];
        bArr[b5 & 255] = b16;
        this.n = (byte) ((b4 + 1) & 255);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            update(bArr[i + i3]);
        }
    }
}
