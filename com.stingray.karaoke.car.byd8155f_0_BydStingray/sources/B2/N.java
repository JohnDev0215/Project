package B2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f525b;
    public int c;
    public byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f526e;

    public N() {
        this.f524a = 2;
        this.d = AbstractC1343A.f15198f;
    }

    public void a() {
        int i7;
        int i10;
        switch (this.f524a) {
            case 2:
                int i11 = this.f525b;
                AbstractC1346c.j(i11 >= 0 && (i11 < (i7 = this.f526e) || (i11 == i7 && this.c == 0)));
                break;
            default:
                int i12 = this.c;
                AbstractC1346c.j(i12 >= 0 && (i12 < (i10 = this.f525b) || (i12 == i10 && this.f526e == 0)));
                break;
        }
    }

    public int b() {
        return ((this.f526e - this.f525b) * 8) - this.c;
    }

    public void c() {
        switch (this.f524a) {
            case 2:
                if (this.c != 0) {
                    this.c = 0;
                    this.f525b++;
                    a();
                    break;
                }
                break;
            default:
                int i7 = this.f526e;
                if (i7 > 0) {
                    u(8 - i7);
                }
                break;
        }
    }

    public boolean d(int i7) {
        int i10 = this.c;
        int i11 = i7 / 8;
        int i12 = i10 + i11;
        int i13 = (this.f526e + i7) - (i11 * 8);
        if (i13 > 7) {
            i12++;
            i13 -= 8;
        }
        while (true) {
            i10++;
            if (i10 > i12 || i12 >= this.f525b) {
                break;
            }
            if (s(i10)) {
                i12++;
                i10 += 2;
            }
        }
        int i14 = this.f525b;
        if (i12 >= i14) {
            return i12 == i14 && i13 == 0;
        }
        return true;
    }

    public boolean e() {
        int i7 = this.c;
        int i10 = this.f526e;
        int i11 = 0;
        while (this.c < this.f525b && !h()) {
            i11++;
        }
        boolean z6 = this.c == this.f525b;
        this.c = i7;
        this.f526e = i10;
        return !z6 && d((i11 * 2) + 1);
    }

    public int f() {
        AbstractC1346c.j(this.c == 0);
        return this.f525b;
    }

    public int g() {
        return (this.f525b * 8) + this.c;
    }

    public boolean h() {
        switch (this.f524a) {
            case 0:
                boolean z6 = (((this.d[this.c] & 255) >> this.f526e) & 1) == 1;
                u(1);
                return z6;
            case 1:
            default:
                boolean z10 = (this.d[this.c] & (128 >> this.f526e)) != 0;
                t();
                return z10;
            case 2:
                boolean z11 = (this.d[this.f525b] & (128 >> this.c)) != 0;
                t();
                return z11;
        }
    }

    public int i(int i7) {
        switch (this.f524a) {
            case 0:
                int i10 = this.c;
                int iMin = Math.min(i7, 8 - this.f526e);
                int i11 = i10 + 1;
                byte[] bArr = this.d;
                int i12 = ((bArr[i10] & 255) >> this.f526e) & (255 >> (8 - iMin));
                while (iMin < i7) {
                    i12 |= (bArr[i11] & 255) << iMin;
                    iMin += 8;
                    i11++;
                }
                int i13 = i12 & ((-1) >>> (32 - i7));
                u(i7);
                return i13;
            case 1:
            default:
                this.f526e += i7;
                int i14 = 0;
                while (true) {
                    int i15 = this.f526e;
                    if (i15 <= 8) {
                        byte[] bArr2 = this.d;
                        int i16 = this.c;
                        int i17 = ((-1) >>> (32 - i7)) & (i14 | ((bArr2[i16] & 255) >> (8 - i15)));
                        if (i15 == 8) {
                            this.f526e = 0;
                            this.c = i16 + (s(i16 + 1) ? 2 : 1);
                        }
                        a();
                        return i17;
                    }
                    int i18 = i15 - 8;
                    this.f526e = i18;
                    byte[] bArr3 = this.d;
                    int i19 = this.c;
                    i14 |= (bArr3[i19] & 255) << i18;
                    if (!s(i19 + 1)) {
                        i = 1;
                    }
                    this.c = i19 + i;
                }
                break;
            case 2:
                if (i7 == 0) {
                    return 0;
                }
                this.c += i7;
                int i20 = 0;
                while (true) {
                    int i21 = this.c;
                    if (i21 <= 8) {
                        byte[] bArr4 = this.d;
                        int i22 = this.f525b;
                        int i23 = ((-1) >>> (32 - i7)) & (i20 | ((bArr4[i22] & 255) >> (8 - i21)));
                        if (i21 == 8) {
                            this.c = 0;
                            this.f525b = i22 + 1;
                        }
                        a();
                        return i23;
                    }
                    int i24 = i21 - 8;
                    this.c = i24;
                    byte[] bArr5 = this.d;
                    int i25 = this.f525b;
                    this.f525b = i25 + 1;
                    i20 |= (bArr5[i25] & 255) << i24;
                }
                break;
        }
    }

    public void j(int i7, byte[] bArr) {
        int i10 = i7 >> 3;
        for (int i11 = 0; i11 < i10; i11++) {
            byte[] bArr2 = this.d;
            int i12 = this.f525b;
            int i13 = i12 + 1;
            this.f525b = i13;
            byte b10 = bArr2[i12];
            int i14 = this.c;
            byte b11 = (byte) (b10 << i14);
            bArr[i11] = b11;
            bArr[i11] = (byte) (((255 & bArr2[i13]) >> (8 - i14)) | b11);
        }
        int i15 = i7 & 7;
        if (i15 == 0) {
            return;
        }
        byte b12 = (byte) (bArr[i10] & (255 >> i15));
        bArr[i10] = b12;
        int i16 = this.c;
        if (i16 + i15 > 8) {
            byte[] bArr3 = this.d;
            int i17 = this.f525b;
            this.f525b = i17 + 1;
            bArr[i10] = (byte) (b12 | ((bArr3[i17] & 255) << i16));
            this.c = i16 - 8;
        }
        int i18 = this.c + i15;
        this.c = i18;
        byte[] bArr4 = this.d;
        int i19 = this.f525b;
        bArr[i10] = (byte) (((byte) (((255 & bArr4[i19]) >> (8 - i18)) << (8 - i15))) | bArr[i10]);
        if (i18 == 8) {
            this.c = 0;
            this.f525b = i19 + 1;
        }
        a();
    }

    public long k(int i7) {
        if (i7 <= 32) {
            int i10 = i(i7);
            int i11 = AbstractC1343A.f15195a;
            return ((long) i10) & 4294967295L;
        }
        int i12 = i(i7 - 32);
        int i13 = i(32);
        int i14 = AbstractC1343A.f15195a;
        return (((long) i13) & 4294967295L) | ((((long) i12) & 4294967295L) << 32);
    }

    public void l(int i7, byte[] bArr) {
        AbstractC1346c.j(this.c == 0);
        System.arraycopy(this.d, this.f525b, bArr, 0, i7);
        this.f525b += i7;
        a();
    }

    public int m() {
        int i7 = 0;
        while (!h()) {
            i7++;
        }
        return ((1 << i7) - 1) + (i7 > 0 ? i(i7) : 0);
    }

    public int n() {
        int iM = m();
        return ((iM + 1) / 2) * (iM % 2 == 0 ? -1 : 1);
    }

    public int o() {
        return m();
    }

    public void p(int i7, byte[] bArr) {
        this.d = bArr;
        this.f525b = 0;
        this.c = 0;
        this.f526e = i7;
    }

    public void q(h2.t tVar) {
        p(tVar.c, tVar.f15241a);
        r(tVar.f15242b * 8);
    }

    public void r(int i7) {
        int i10 = i7 / 8;
        this.f525b = i10;
        this.c = i7 - (i10 * 8);
        a();
    }

    public boolean s(int i7) {
        if (2 <= i7 && i7 < this.f525b) {
            byte[] bArr = this.d;
            if (bArr[i7] == 3 && bArr[i7 - 2] == 0 && bArr[i7 - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    public void t() {
        switch (this.f524a) {
            case 2:
                int i7 = this.c + 1;
                this.c = i7;
                if (i7 == 8) {
                    this.c = 0;
                    this.f525b++;
                }
                a();
                break;
            default:
                int i10 = this.f526e + 1;
                this.f526e = i10;
                if (i10 == 8) {
                    this.f526e = 0;
                    int i11 = this.c;
                    this.c = i11 + (s(i11 + 1) ? 2 : 1);
                }
                a();
                break;
        }
    }

    public void u(int i7) {
        int i10;
        switch (this.f524a) {
            case 0:
                int i11 = i7 / 8;
                int i12 = this.c + i11;
                this.c = i12;
                int i13 = (i7 - (i11 * 8)) + this.f526e;
                this.f526e = i13;
                boolean z6 = true;
                if (i13 > 7) {
                    this.c = i12 + 1;
                    this.f526e = i13 - 8;
                }
                int i14 = this.c;
                if (i14 < 0 || (i14 >= (i10 = this.f525b) && (i14 != i10 || this.f526e != 0))) {
                    z6 = false;
                }
                AbstractC1346c.j(z6);
                break;
            case 1:
            default:
                int i15 = this.c;
                int i16 = i7 / 8;
                int i17 = i15 + i16;
                this.c = i17;
                int i18 = (i7 - (i16 * 8)) + this.f526e;
                this.f526e = i18;
                if (i18 > 7) {
                    this.c = i17 + 1;
                    this.f526e = i18 - 8;
                }
                while (true) {
                    i15++;
                    if (i15 > this.c) {
                        a();
                        break;
                    } else if (s(i15)) {
                        this.c++;
                        i15 += 2;
                    }
                }
                break;
            case 2:
                int i19 = i7 / 8;
                int i20 = this.f525b + i19;
                this.f525b = i20;
                int i21 = (i7 - (i19 * 8)) + this.c;
                this.c = i21;
                if (i21 > 7) {
                    this.f525b = i20 + 1;
                    this.c = i21 - 8;
                }
                a();
                break;
        }
    }

    public void v(int i7) {
        AbstractC1346c.j(this.c == 0);
        this.f525b += i7;
        a();
    }

    public N(int i7, int i10, byte[] bArr) {
        this.f524a = 3;
        this.d = bArr;
        this.c = i7;
        this.f525b = i10;
        this.f526e = 0;
        a();
    }

    public N(byte[] bArr) {
        this.f524a = 0;
        this.d = bArr;
        this.f525b = bArr.length;
    }

    public N(int i7, byte[] bArr) {
        this.f524a = 2;
        this.d = bArr;
        this.f526e = i7;
    }

    public N(int i7, int i10) {
        this.f524a = 1;
        this.f525b = i7;
        this.c = i10;
        this.d = new byte[(i10 * 2) - 1];
        this.f526e = 0;
    }
}
