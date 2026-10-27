package f2;

import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14557b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f14558e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14559f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f14560g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f14561h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final short[] f14562i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public short[] f14563j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f14564k;
    public short[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14565m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public short[] f14566n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14567p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f14568q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f14569r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f14570s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f14571t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f14572u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f14573v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public double f14574w;

    public f(float f10, float f11, int i7, int i10, int i11) {
        this.f14556a = i7;
        this.f14557b = i10;
        this.c = f10;
        this.d = f11;
        this.f14558e = i7 / i11;
        this.f14559f = i7 / 400;
        int i12 = i7 / 65;
        this.f14560g = i12;
        int i13 = i12 * 2;
        this.f14561h = i13;
        this.f14562i = new short[i13];
        this.f14563j = new short[i13 * i10];
        this.l = new short[i13 * i10];
        this.f14566n = new short[i13 * i10];
    }

    public static void e(int i7, int i10, short[] sArr, int i11, short[] sArr2, int i12, short[] sArr3, int i13) {
        for (int i14 = 0; i14 < i10; i14++) {
            int i15 = (i11 * i10) + i14;
            int i16 = (i13 * i10) + i14;
            int i17 = (i12 * i10) + i14;
            for (int i18 = 0; i18 < i7; i18++) {
                sArr[i15] = (short) (((sArr3[i16] * i18) + ((i7 - i18) * sArr2[i17])) / i7);
                i15 += i10;
                i17 += i10;
                i16 += i10;
            }
        }
    }

    public final void a(short[] sArr, int i7, int i10) {
        short[] sArrC = c(this.l, this.f14565m, i10);
        this.l = sArrC;
        int i11 = this.f14557b;
        System.arraycopy(sArr, i7 * i11, sArrC, this.f14565m * i11, i11 * i10);
        this.f14565m += i10;
    }

    public final void b(short[] sArr, int i7, int i10) {
        int i11 = this.f14561h / i10;
        int i12 = this.f14557b;
        int i13 = i10 * i12;
        int i14 = i7 * i12;
        for (int i15 = 0; i15 < i11; i15++) {
            int i16 = 0;
            for (int i17 = 0; i17 < i13; i17++) {
                i16 += sArr[(i15 * i13) + i14 + i17];
            }
            this.f14562i[i15] = (short) (i16 / i13);
        }
    }

    public final short[] c(short[] sArr, int i7, int i10) {
        int length = sArr.length;
        int i11 = this.f14557b;
        int i12 = length / i11;
        return i7 + i10 <= i12 ? sArr : Arrays.copyOf(sArr, (((i12 * 3) / 2) + i10) * i11);
    }

    public final int d(short[] sArr, int i7, int i10, int i11) {
        int i12 = i7 * this.f14557b;
        int i13 = 255;
        int i14 = 1;
        int i15 = 0;
        int i16 = 0;
        while (i10 <= i11) {
            int iAbs = 0;
            for (int i17 = 0; i17 < i10; i17++) {
                iAbs += Math.abs(sArr[i12 + i17] - sArr[(i12 + i10) + i17]);
            }
            if (iAbs * i15 < i14 * i10) {
                i15 = i10;
                i14 = iAbs;
            }
            if (iAbs * i13 > i16 * i10) {
                i13 = i10;
                i16 = iAbs;
            }
            i10++;
        }
        this.f14572u = i14 / i15;
        this.f14573v = i16 / i13;
        return i15;
    }

    public final void f() {
        int i7;
        int i10;
        int iD;
        int i11;
        int iRound;
        int iRound2;
        int i12;
        int i13;
        int i14;
        int i15;
        long j3;
        long j10;
        int i16 = this.f14565m;
        float f10 = this.c;
        float f11 = this.d;
        double d = f10 / f11;
        float f12 = this.f14558e * f11;
        int i17 = this.f14556a;
        int i18 = this.f14557b;
        int i19 = 0;
        int i20 = 1;
        if (d > 1.00001d || d < 0.99999d) {
            int i21 = this.f14564k;
            int i22 = this.f14561h;
            if (i21 >= i22) {
                int i23 = 0;
                while (true) {
                    int i24 = this.f14569r;
                    if (i24 > 0) {
                        int iMin = Math.min(i22, i24);
                        a(this.f14563j, i23, iMin);
                        this.f14569r -= iMin;
                        i23 += iMin;
                        i10 = i16;
                        i7 = i17;
                    } else {
                        short[] sArr = this.f14563j;
                        int i25 = i17 > 4000 ? i17 / 4000 : i20;
                        int i26 = this.f14560g;
                        int i27 = this.f14559f;
                        if (i18 == i20 && i25 == i20) {
                            iD = d(sArr, i23, i27, i26);
                            i10 = i16;
                            i7 = i17;
                        } else {
                            b(sArr, i23, i25);
                            i7 = i17;
                            i10 = i16;
                            short[] sArr2 = this.f14562i;
                            int iD2 = d(sArr2, 0, i27 / i25, i26 / i25);
                            if (i25 != 1) {
                                int i28 = iD2 * i25;
                                int i29 = i25 * 4;
                                int i30 = i28 - i29;
                                int i31 = i28 + i29;
                                if (i30 >= i27) {
                                    i27 = i30;
                                }
                                if (i31 <= i26) {
                                    i26 = i31;
                                }
                                if (i18 == 1) {
                                    iD = d(sArr, i23, i27, i26);
                                } else {
                                    b(sArr, i23, 1);
                                    iD = d(sArr2, 0, i27, i26);
                                }
                            } else {
                                iD = iD2;
                            }
                        }
                        int i32 = this.f14572u;
                        int i33 = this.f14573v;
                        if (i32 == 0 || (i11 = this.f14570s) == 0 || i33 > i32 * 3 || i32 * 2 <= this.f14571t * 3) {
                            i11 = iD;
                        }
                        this.f14571t = i32;
                        this.f14570s = iD;
                        if (d > 1.0d) {
                            short[] sArr3 = this.f14563j;
                            if (d >= 2.0d) {
                                double d10 = (((double) i11) / (d - 1.0d)) + this.f14574w;
                                iRound2 = (int) Math.round(d10);
                                this.f14574w = d10 - ((double) iRound2);
                            } else {
                                double d11 = (((2.0d - d) * ((double) i11)) / (d - 1.0d)) + this.f14574w;
                                int iRound3 = (int) Math.round(d11);
                                this.f14569r = iRound3;
                                this.f14574w = d11 - ((double) iRound3);
                                iRound2 = i11;
                            }
                            short[] sArrC = c(this.l, this.f14565m, iRound2);
                            this.l = sArrC;
                            int i34 = i23;
                            e(iRound2, this.f14557b, sArrC, this.f14565m, sArr3, i34, sArr3, i23 + i11);
                            this.f14565m += iRound2;
                            i23 = i11 + iRound2 + i34;
                        } else {
                            int i35 = i23;
                            short[] sArr4 = this.f14563j;
                            if (d < 0.5d) {
                                double d12 = ((((double) i11) * d) / (1.0d - d)) + this.f14574w;
                                iRound = (int) Math.round(d12);
                                this.f14574w = d12 - ((double) iRound);
                            } else {
                                double d13 = ((((2.0d * d) - 1.0d) * ((double) i11)) / (1.0d - d)) + this.f14574w;
                                int iRound4 = (int) Math.round(d13);
                                this.f14569r = iRound4;
                                this.f14574w = d13 - ((double) iRound4);
                                iRound = i11;
                            }
                            int i36 = i11 + iRound;
                            short[] sArrC2 = c(this.l, this.f14565m, i36);
                            this.l = sArrC2;
                            System.arraycopy(sArr4, i35 * i18, sArrC2, this.f14565m * i18, i11 * i18);
                            e(iRound, this.f14557b, this.l, this.f14565m + i11, sArr4, i35 + i11, sArr4, i35);
                            this.f14565m += i36;
                            i23 = i35 + iRound;
                        }
                    }
                    if (i23 + i22 > i21) {
                        break;
                    }
                    i17 = i7;
                    i16 = i10;
                    i20 = 1;
                }
                int i37 = this.f14564k - i23;
                short[] sArr5 = this.f14563j;
                System.arraycopy(sArr5, i23 * i18, sArr5, 0, i37 * i18);
                this.f14564k = i37;
            }
            if (f12 != 1.0f || this.f14565m == (i12 = i10)) {
            }
            int i38 = i7;
            long j11 = (long) (i38 / f12);
            long j12 = i38;
            while (j11 != 0 && j12 != 0 && j11 % 2 == 0 && j12 % 2 == 0) {
                j11 /= 2;
                j12 /= 2;
            }
            int i39 = this.f14565m - i12;
            short[] sArrC3 = c(this.f14566n, this.o, i39);
            this.f14566n = sArrC3;
            System.arraycopy(this.l, i12 * i18, sArrC3, this.o * i18, i39 * i18);
            this.f14565m = i12;
            this.o += i39;
            int i40 = 0;
            while (true) {
                i13 = this.o;
                i14 = i13 - 1;
                if (i40 >= i14) {
                    break;
                }
                while (true) {
                    i15 = this.f14567p + 1;
                    j3 = i15;
                    long j13 = j3 * j11;
                    j10 = this.f14568q;
                    if (j13 <= j10 * j12) {
                        break;
                    }
                    this.l = c(this.l, this.f14565m, 1);
                    int i41 = i19;
                    while (i41 < i18) {
                        short[] sArr6 = this.l;
                        int i42 = (this.f14565m * i18) + i41;
                        short[] sArr7 = this.f14566n;
                        int i43 = (i40 * i18) + i41;
                        short s6 = sArr7[i43];
                        short s10 = sArr7[i43 + i18];
                        long j14 = ((long) this.f14568q) * j12;
                        int i44 = this.f14567p;
                        long j15 = j12;
                        long j16 = ((long) (i44 + 1)) * j11;
                        long j17 = j16 - j14;
                        long j18 = j16 - (((long) i44) * j11);
                        sArr6[i42] = (short) ((((j18 - j17) * ((long) s10)) + (((long) s6) * j17)) / j18);
                        i41++;
                        j12 = j15;
                    }
                    this.f14568q++;
                    this.f14565m++;
                    j12 = j12;
                    i19 = 0;
                }
                long j19 = j12;
                this.f14567p = i15;
                if (j3 == j19) {
                    this.f14567p = 0;
                    AbstractC1346c.j(j10 == j11);
                    this.f14568q = 0;
                }
                i40++;
                j12 = j19;
                i19 = 0;
            }
            if (i14 == 0) {
                return;
            }
            short[] sArr8 = this.f14566n;
            System.arraycopy(sArr8, i14 * i18, sArr8, 0, (i13 - i14) * i18);
            this.o -= i14;
            return;
        }
        a(this.f14563j, 0, this.f14564k);
        this.f14564k = 0;
        i10 = i16;
        i7 = i17;
        if (f12 != 1.0f) {
        }
    }
}
