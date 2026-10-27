package g.a.b.h0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class t implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4965a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4966b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4967c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.e f4968d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4969e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4970f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4971g;

    public t(g.a.b.e eVar) {
        this.f4968d = eVar;
        int iB = eVar.b();
        this.f4970f = iB;
        this.f4965a = new byte[iB];
        this.f4966b = new byte[iB];
        this.f4967c = new byte[iB];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        return this.f4971g ? d(bArr, i2, bArr2, i3) : c(bArr, i2, bArr2, i3);
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4968d.b();
    }

    public final int c(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4;
        int i5;
        int i6;
        int i7 = this.f4970f;
        if (i2 + i7 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i7 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i8 = this.f4969e;
        int i9 = 2;
        int i10 = 0;
        if (i8 > i7) {
            byte b2 = bArr[i2];
            this.f4966b[i7 - 2] = b2;
            bArr2[i3] = e(b2, i7 - 2);
            byte b3 = bArr[i2 + 1];
            byte[] bArr3 = this.f4966b;
            int i11 = this.f4970f;
            bArr3[i11 - 1] = b3;
            bArr2[i3 + 1] = e(b3, i11 - 1);
            this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
            while (i9 < this.f4970f) {
                byte b4 = bArr[i2 + i9];
                int i12 = i9 - 2;
                this.f4966b[i12] = b4;
                bArr2[i3 + i9] = e(b4, i12);
                i9++;
            }
        } else {
            if (i8 == 0) {
                this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
                while (true) {
                    i6 = this.f4970f;
                    if (i10 >= i6) {
                        break;
                    }
                    int i13 = i2 + i10;
                    this.f4966b[i10] = bArr[i13];
                    bArr2[i10] = e(bArr[i13], i10);
                    i10++;
                }
                i5 = this.f4969e + i6;
            } else if (i8 == i7) {
                this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
                byte b5 = bArr[i2];
                byte b6 = bArr[i2 + 1];
                bArr2[i3] = e(b5, 0);
                bArr2[i3 + 1] = e(b6, 1);
                byte[] bArr4 = this.f4966b;
                System.arraycopy(bArr4, 2, bArr4, 0, this.f4970f - 2);
                byte[] bArr5 = this.f4966b;
                int i14 = this.f4970f;
                bArr5[i14 - 2] = b5;
                bArr5[i14 - 1] = b6;
                this.f4968d.a(bArr5, 0, this.f4967c, 0);
                while (true) {
                    i4 = this.f4970f;
                    if (i9 >= i4) {
                        break;
                    }
                    byte b7 = bArr[i2 + i9];
                    int i15 = i9 - 2;
                    this.f4966b[i15] = b7;
                    bArr2[i3 + i9] = e(b7, i15);
                    i9++;
                }
                i5 = this.f4969e + i4;
            }
            this.f4969e = i5;
        }
        return this.f4970f;
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4;
        int i5 = this.f4970f;
        if (i2 + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i5 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i6 = this.f4969e;
        int i7 = 2;
        int i8 = 0;
        if (i6 > i5) {
            byte[] bArr3 = this.f4966b;
            int i9 = i5 - 2;
            byte bE = e(bArr[i2], i5 - 2);
            bArr2[i3] = bE;
            bArr3[i9] = bE;
            byte[] bArr4 = this.f4966b;
            int i10 = this.f4970f;
            int i11 = i10 - 1;
            byte bE2 = e(bArr[i2 + 1], i10 - 1);
            bArr2[i3 + 1] = bE2;
            bArr4[i11] = bE2;
            this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
            while (i7 < this.f4970f) {
                byte[] bArr5 = this.f4966b;
                int i12 = i7 - 2;
                byte bE3 = e(bArr[i2 + i7], i12);
                bArr2[i3 + i7] = bE3;
                bArr5[i12] = bE3;
                i7++;
            }
        } else {
            if (i6 != 0) {
                if (i6 == i5) {
                    this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
                    bArr2[i3] = e(bArr[i2], 0);
                    bArr2[i3 + 1] = e(bArr[i2 + 1], 1);
                    byte[] bArr6 = this.f4966b;
                    System.arraycopy(bArr6, 2, bArr6, 0, this.f4970f - 2);
                    System.arraycopy(bArr2, i3, this.f4966b, this.f4970f - 2, 2);
                    this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
                    while (true) {
                        i4 = this.f4970f;
                        if (i7 >= i4) {
                            break;
                        }
                        byte[] bArr7 = this.f4966b;
                        int i13 = i7 - 2;
                        byte bE4 = e(bArr[i2 + i7], i13);
                        bArr2[i3 + i7] = bE4;
                        bArr7[i13] = bE4;
                        i7++;
                    }
                }
            } else {
                this.f4968d.a(this.f4966b, 0, this.f4967c, 0);
                while (true) {
                    i4 = this.f4970f;
                    if (i8 >= i4) {
                        break;
                    }
                    byte[] bArr8 = this.f4966b;
                    byte bE5 = e(bArr[i2 + i8], i8);
                    bArr2[i3 + i8] = bE5;
                    bArr8[i8] = bE5;
                    i8++;
                }
            }
            this.f4969e += i4;
        }
        return this.f4970f;
    }

    public final byte e(byte b2, int i2) {
        return (byte) (b2 ^ this.f4967c[i2]);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4968d.getAlgorithmName() + "/OpenPGPCFB";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        this.f4971g = z;
        reset();
        this.f4968d.init(true, iVar);
    }

    @Override // g.a.b.e
    public void reset() {
        this.f4969e = 0;
        byte[] bArr = this.f4965a;
        byte[] bArr2 = this.f4966b;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.f4968d.reset();
    }
}
