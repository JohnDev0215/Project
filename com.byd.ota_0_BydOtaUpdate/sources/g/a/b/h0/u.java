package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class u implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4974c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4975d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.e f4976e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4977f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4978g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4979h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4980i;

    public u(g.a.b.e eVar, boolean z) {
        this.f4976e = eVar;
        this.f4980i = z;
        int iB = eVar.b();
        this.f4978g = iB;
        this.f4972a = new byte[iB];
        this.f4973b = new byte[iB];
        this.f4974c = new byte[iB];
        this.f4975d = new byte[iB];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        return this.f4980i ? this.f4979h ? f(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3) : this.f4979h ? e(bArr, i2, bArr2, i3) : c(bArr, i2, bArr2, i3);
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4976e.b();
    }

    public final int c(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = this.f4978g;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i5 = 0;
        this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
        for (int i6 = 0; i6 < this.f4978g; i6++) {
            bArr2[i3 + i6] = g(bArr[i2 + i6], i6);
        }
        while (true) {
            int i7 = this.f4978g;
            if (i5 >= i7) {
                return i7;
            }
            this.f4973b[i5] = bArr[i2 + i5];
            i5++;
        }
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4;
        int i5 = this.f4978g;
        if (i2 + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i5 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i6 = this.f4977f;
        if (i6 == 0) {
            for (int i7 = 0; i7 < this.f4978g; i7++) {
                this.f4973b[i7] = bArr[i2 + i7];
            }
            this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
            this.f4977f += this.f4978g;
            return 0;
        }
        if (i6 != i5) {
            if (i6 >= i5 + 2) {
                System.arraycopy(bArr, i2, this.f4975d, 0, i5);
                bArr2[i3 + 0] = g(this.f4975d[0], this.f4978g - 2);
                bArr2[i3 + 1] = g(this.f4975d[1], this.f4978g - 1);
                System.arraycopy(this.f4975d, 0, this.f4973b, this.f4978g - 2, 2);
                this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
                int i8 = 0;
                while (true) {
                    i4 = this.f4978g;
                    if (i8 >= i4 - 2) {
                        break;
                    }
                    bArr2[i3 + i8 + 2] = g(this.f4975d[i8 + 2], i8);
                    i8++;
                }
                System.arraycopy(this.f4975d, 2, this.f4973b, 0, i4 - 2);
            }
            return this.f4978g;
        }
        System.arraycopy(bArr, i2, this.f4975d, 0, i5);
        byte[] bArr3 = this.f4973b;
        System.arraycopy(bArr3, 2, bArr3, 0, this.f4978g - 2);
        byte[] bArr4 = this.f4973b;
        int i9 = this.f4978g;
        byte[] bArr5 = this.f4975d;
        bArr4[i9 - 2] = bArr5[0];
        bArr4[i9 - 1] = bArr5[1];
        this.f4976e.a(bArr4, 0, this.f4974c, 0);
        int i10 = 0;
        while (true) {
            int i11 = this.f4978g;
            if (i10 >= i11 - 2) {
                System.arraycopy(this.f4975d, 2, this.f4973b, 0, i11 - 2);
                this.f4977f += 2;
                return this.f4978g - 2;
            }
            bArr2[i3 + i10] = g(this.f4975d[i10 + 2], i10);
            i10++;
        }
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = this.f4978g;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i5 = 0;
        this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
        for (int i6 = 0; i6 < this.f4978g; i6++) {
            bArr2[i3 + i6] = g(bArr[i2 + i6], i6);
        }
        while (true) {
            int i7 = this.f4978g;
            if (i5 >= i7) {
                return i7;
            }
            this.f4973b[i5] = bArr2[i3 + i5];
            i5++;
        }
    }

    public final int f(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4;
        int i5;
        int i6 = this.f4978g;
        if (i2 + i6 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int i7 = this.f4977f;
        if (i7 != 0) {
            if (i7 >= i6 + 2) {
                if (i6 + i3 > bArr2.length) {
                    throw new OutputLengthException("output buffer too short");
                }
                this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
                int i8 = 0;
                while (true) {
                    i4 = this.f4978g;
                    if (i8 >= i4) {
                        break;
                    }
                    bArr2[i3 + i8] = g(bArr[i2 + i8], i8);
                    i8++;
                }
                System.arraycopy(bArr2, i3, this.f4973b, 0, i4);
            }
            return this.f4978g;
        }
        if ((i6 * 2) + i3 + 2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
        int i9 = 0;
        while (true) {
            i5 = this.f4978g;
            if (i9 >= i5) {
                break;
            }
            bArr2[i3 + i9] = g(this.f4972a[i9], i9);
            i9++;
        }
        System.arraycopy(bArr2, i3, this.f4973b, 0, i5);
        this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
        int i10 = this.f4978g;
        bArr2[i3 + i10] = g(this.f4972a[i10 - 2], 0);
        int i11 = this.f4978g;
        bArr2[i3 + i11 + 1] = g(this.f4972a[i11 - 1], 1);
        System.arraycopy(bArr2, i3 + 2, this.f4973b, 0, this.f4978g);
        this.f4976e.a(this.f4973b, 0, this.f4974c, 0);
        int i12 = 0;
        while (true) {
            int i13 = this.f4978g;
            if (i12 >= i13) {
                System.arraycopy(bArr2, i3 + i13 + 2, this.f4973b, 0, i13);
                int i14 = this.f4977f;
                int i15 = this.f4978g;
                this.f4977f = i14 + (i15 * 2) + 2;
                return (i15 * 2) + 2;
            }
            bArr2[i13 + i3 + 2 + i12] = g(bArr[i2 + i12], i12);
            i12++;
        }
    }

    public final byte g(byte b2, int i2) {
        return (byte) (b2 ^ this.f4974c[i2]);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        StringBuilder sb;
        String str;
        if (this.f4980i) {
            sb = new StringBuilder();
            sb.append(this.f4976e.getAlgorithmName());
            str = "/PGPCFBwithIV";
        } else {
            sb = new StringBuilder();
            sb.append(this.f4976e.getAlgorithmName());
            str = "/PGPCFB";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        this.f4979h = z;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            int length = bArrA.length;
            byte[] bArr = this.f4972a;
            if (length < bArr.length) {
                System.arraycopy(bArrA, 0, bArr, bArr.length - bArrA.length, bArrA.length);
                int i2 = 0;
                while (true) {
                    byte[] bArr2 = this.f4972a;
                    if (i2 >= bArr2.length - bArrA.length) {
                        break;
                    }
                    bArr2[i2] = 0;
                    i2++;
                }
            } else {
                System.arraycopy(bArrA, 0, bArr, 0, bArr.length);
            }
            reset();
            eVar = this.f4976e;
            iVar = w0Var.b();
        } else {
            reset();
            eVar = this.f4976e;
        }
        eVar.init(true, iVar);
    }

    @Override // g.a.b.e
    public void reset() {
        this.f4977f = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4973b;
            if (i2 == bArr.length) {
                this.f4976e.reset();
                return;
            }
            if (this.f4980i) {
                bArr[i2] = 0;
            } else {
                bArr[i2] = this.f4972a[i2];
            }
            i2++;
        }
    }
}
