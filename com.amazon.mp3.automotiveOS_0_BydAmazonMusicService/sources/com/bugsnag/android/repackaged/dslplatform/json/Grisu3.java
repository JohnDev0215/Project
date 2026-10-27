package com.bugsnag.android.repackaged.dslplatform.json;

import com.amazon.digitalmusicplayback.AudioFormat;

/* JADX INFO: loaded from: classes5.dex */
abstract class Grisu3 {
    Grisu3() {
    }

    private static final class DiyFp {
        long f = 0;
        int e = 0;

        DiyFp() {
        }

        void subtract(DiyFp diyFp) {
            this.f -= diyFp.f;
        }

        void multiply(DiyFp diyFp) {
            long j = this.f;
            long j2 = j >>> 32;
            long j3 = j & 4294967295L;
            long j4 = diyFp.f;
            long j5 = j4 >>> 32;
            long j6 = j4 & 4294967295L;
            long j7 = j2 * j5;
            long j8 = j5 * j3;
            long j9 = j2 * j6;
            long j10 = j7 + (j9 >>> 32) + (j8 >>> 32) + ((((((j3 * j6) >>> 32) + (j9 & 4294967295L)) + (4294967295L & j8)) + AudioFormat.WIDE_LEFT) >>> 32);
            this.e += diyFp.e + 64;
            this.f = j10;
        }

        void normalize() {
            long j = this.f;
            int i = this.e;
            while (((-18014398509481984L) & j) == 0) {
                j <<= 10;
                i -= 10;
            }
            while ((Long.MIN_VALUE & j) == 0) {
                j <<= 1;
                i--;
            }
            this.f = j;
            this.e = i;
        }

        void reset() {
            this.e = 0;
            this.f = 0L;
        }

        public String toString() {
            return "[DiyFp f:" + this.f + ", e:" + this.e + "]";
        }
    }

    private static class CachedPowers {
        static final CachedPower[] CACHED_POWERS = {new CachedPower(-1865951482774665761L, -1087, -308), new CachedPower(-6093090917745768758L, -1060, -300), new CachedPower(-38366372719436721L, -1034, -292), new CachedPower(-4731433901725329908L, -1007, -284), new CachedPower(-8228041688891786180L, -980, -276), new CachedPower(-3219690930897053053L, -954, -268), new CachedPower(-7101705404292871755L, -927, -260), new CachedPower(-1541319077368263733L, -901, -252), new CachedPower(-5851220927660403859L, -874, -244), new CachedPower(-9062348037703676329L, -847, -236), new CachedPower(-4462904269766699465L, -821, -228), new CachedPower(-8027971522334779313L, -794, -220), new CachedPower(-2921563150702462265L, -768, -212), new CachedPower(-6879582898840692748L, -741, -204), new CachedPower(-1210330751515841307L, -715, -196), new CachedPower(-5604615407819967858L, -688, -188), new CachedPower(-8878612607581929669L, -661, -180), new CachedPower(-4189117143640191558L, -635, -172), new CachedPower(-7823984217374209642L, -608, -164), new CachedPower(-2617598379430861436L, -582, -156), new CachedPower(-6653111496142234890L, -555, -148), new CachedPower(-872862063775190746L, -529, -140), new CachedPower(-5353181642124984136L, -502, -132), new CachedPower(-8691279853972075893L, -475, -124), new CachedPower(-3909969587797413805L, -449, -116), new CachedPower(-7616003081050118571L, -422, -108), new CachedPower(-2307682335666372931L, -396, -100), new CachedPower(-6422206049907525489L, -369, -92), new CachedPower(-528786136287117932L, -343, -84), new CachedPower(-5096825099203863601L, -316, -76), new CachedPower(-8500279345513818773L, -289, -68), new CachedPower(-3625356651333078602L, -263, -60), new CachedPower(-7403949918844649556L, -236, -52), new CachedPower(-1991698500497491194L, -210, -44), new CachedPower(-6186779746782440749L, -183, -36), new CachedPower(-177973607073265138L, -157, -28), new CachedPower(-4835449396872013077L, -130, -20), new CachedPower(-8305539271883716404L, -103, -12), new CachedPower(-3335171328526686932L, -77, -4), new CachedPower(-7187745005283311616L, -50, 4), new CachedPower(-1669528073709551616L, -24, 12), new CachedPower(-5946744073709551616L, 3, 20), new CachedPower(-9133518327554766460L, 30, 28), new CachedPower(-4568956265895094861L, 56, 36), new CachedPower(-8106986416796705680L, 83, 44), new CachedPower(-3039304518611664792L, 109, 52), new CachedPower(-6967307053960650171L, 136, 60), new CachedPower(-1341049929119499481L, 162, 68), new CachedPower(-5702008784649933400L, 189, 76), new CachedPower(-8951176327949752869L, 216, 84), new CachedPower(-4297245513042813542L, 242, 92), new CachedPower(-7904546130479028392L, 269, 100), new CachedPower(-2737644984756826646L, 295, 108), new CachedPower(-6742553186979055798L, 322, 116), new CachedPower(-1006140569036166267L, 348, 124), new CachedPower(-5452481866653427593L, 375, 132), new CachedPower(-8765264286586255934L, 402, 140), new CachedPower(-4020214983419339459L, 428, 148), new CachedPower(-7698142301602209613L, 455, 156), new CachedPower(-2430079312244744221L, 481, 164), new CachedPower(-6513398903789220827L, 508, 172), new CachedPower(-664674077828931748L, 534, 180), new CachedPower(-5198069505264599346L, 561, 188), new CachedPower(-8575712306248138270L, 588, 196), new CachedPower(-3737760522056206171L, 614, 204), new CachedPower(-7487697328667536417L, 641, 212), new CachedPower(-2116491865831296966L, 667, 220), new CachedPower(-6279758049420528746L, 694, 228), new CachedPower(-316522074587315140L, 720, 236), new CachedPower(-4938676049251384304L, 747, 244), new CachedPower(-8382449121214030822L, 774, 252), new CachedPower(-3449775934753242068L, 800, 260), new CachedPower(-7273132090830278359L, 827, 268), new CachedPower(-1796764746270372707L, 853, 276), new CachedPower(-6041542782089432023L, 880, 284), new CachedPower(-9204148869281624187L, 907, 292), new CachedPower(-4674203974643163859L, 933, 300), new CachedPower(-8185402070463610993L, 960, 308), new CachedPower(-3156152948152813503L, 986, 316), new CachedPower(-7054365918152680535L, 1013, 324), new CachedPower(-1470777745987373095L, 1039, 332), new CachedPower(-5798663540173640085L, 1066, 340)};

        private CachedPowers() {
        }

        static class CachedPower {
            final short binaryExponent;
            final short decimalExponent;
            final long significand;

            CachedPower(long j, short s, short s2) {
                this.significand = j;
                this.binaryExponent = s;
                this.decimalExponent = s2;
            }
        }

        static int getCachedPower(int i, int i2, DiyFp diyFp) {
            CachedPower cachedPower = CACHED_POWERS[(((((int) Math.ceil(((double) (((i2 - i) + 64) - 1)) * 0.30102999566398114d)) + 308) - 1) / 8) + 1];
            diyFp.f = cachedPower.significand;
            diyFp.e = cachedPower.binaryExponent;
            return cachedPower.decimalExponent;
        }
    }

    private static class DoubleHelper {
        private static boolean isDenormal(long j) {
            return (j & 9218868437227405312L) == 0;
        }

        private DoubleHelper() {
        }

        static void asDiyFp(long j, DiyFp diyFp) {
            diyFp.f = significand(j);
            diyFp.e = exponent(j);
        }

        static void asNormalizedDiyFp(long j, DiyFp diyFp) {
            long jSignificand = significand(j);
            int iExponent = exponent(j);
            while ((4503599627370496L & jSignificand) == 0) {
                jSignificand <<= 1;
                iExponent--;
            }
            diyFp.f = jSignificand << 11;
            diyFp.e = iExponent - 11;
        }

        static int exponent(long j) {
            if (isDenormal(j)) {
                return -1074;
            }
            return ((int) (((j & 9218868437227405312L) >>> 52) & 4294967295L)) - 1075;
        }

        static long significand(long j) {
            long j2 = 4503599627370495L & j;
            return !isDenormal(j) ? j2 + 4503599627370496L : j2;
        }

        static void normalizedBoundaries(DiyFp diyFp, long j, DiyFp diyFp2, DiyFp diyFp3) {
            int i;
            asDiyFp(j, diyFp);
            long j2 = diyFp.f;
            boolean z = j2 == 4503599627370496L;
            diyFp3.f = (j2 << 1) + 1;
            diyFp3.e = diyFp.e - 1;
            diyFp3.normalize();
            if (z && (i = diyFp.e) != -1074) {
                diyFp2.f = (diyFp.f << 2) - 1;
                diyFp2.e = i - 2;
            } else {
                diyFp2.f = (diyFp.f << 1) - 1;
                diyFp2.e = diyFp.e - 1;
            }
            long j3 = diyFp2.f;
            int i2 = diyFp2.e;
            int i3 = diyFp3.e;
            diyFp2.f = j3 << (i2 - i3);
            diyFp2.e = i3;
        }
    }

    static class FastDtoa {
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x002a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        static long biggestPowerTen(int r11, int r12) {
            /*
                r0 = 1000000000(0x3b9aca00, float:0.0047237873)
                r1 = 100000000(0x5f5e100, float:2.3122341E-35)
                r2 = 10000000(0x989680, float:1.4012985E-38)
                r3 = 1000000(0xf4240, float:1.401298E-39)
                r4 = 100000(0x186a0, float:1.4013E-40)
                r5 = 10000(0x2710, float:1.4013E-41)
                r6 = 1000(0x3e8, float:1.401E-42)
                r7 = 100
                r8 = 10
                r9 = 1
                r10 = 0
                switch(r12) {
                    case 0: goto L51;
                    case 1: goto L4c;
                    case 2: goto L4c;
                    case 3: goto L4c;
                    case 4: goto L48;
                    case 5: goto L48;
                    case 6: goto L48;
                    case 7: goto L43;
                    case 8: goto L43;
                    case 9: goto L43;
                    case 10: goto L3e;
                    case 11: goto L3e;
                    case 12: goto L3e;
                    case 13: goto L3e;
                    case 14: goto L39;
                    case 15: goto L39;
                    case 16: goto L39;
                    case 17: goto L34;
                    case 18: goto L34;
                    case 19: goto L34;
                    case 20: goto L2f;
                    case 21: goto L2f;
                    case 22: goto L2f;
                    case 23: goto L2f;
                    case 24: goto L2a;
                    case 25: goto L2a;
                    case 26: goto L2a;
                    case 27: goto L24;
                    case 28: goto L24;
                    case 29: goto L24;
                    case 30: goto L1f;
                    case 31: goto L1f;
                    case 32: goto L1f;
                    default: goto L1c;
                }
            L1c:
                r0 = r10
                r9 = r0
                goto L53
            L1f:
                if (r0 > r11) goto L24
                r9 = 9
                goto L53
            L24:
                if (r1 > r11) goto L2a
                r9 = 8
                r0 = r1
                goto L53
            L2a:
                if (r2 > r11) goto L2f
                r9 = 7
                r0 = r2
                goto L53
            L2f:
                if (r3 > r11) goto L34
                r9 = 6
                r0 = r3
                goto L53
            L34:
                if (r4 > r11) goto L39
                r9 = 5
                r0 = r4
                goto L53
            L39:
                if (r5 > r11) goto L3e
                r9 = 4
                r0 = r5
                goto L53
            L3e:
                if (r6 > r11) goto L43
                r9 = 3
                r0 = r6
                goto L53
            L43:
                if (r7 > r11) goto L48
                r9 = 2
                r0 = r7
                goto L53
            L48:
                if (r8 > r11) goto L4c
                r0 = r8
                goto L53
            L4c:
                if (r9 > r11) goto L51
                r0 = r9
                r9 = r10
                goto L53
            L51:
                r9 = -1
                r0 = r10
            L53:
                long r11 = (long) r0
                r0 = 32
                long r11 = r11 << r0
                r0 = 4294967295(0xffffffff, double:2.1219957905E-314)
                long r2 = (long) r9
                long r0 = r0 & r2
                long r11 = r11 | r0
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.Grisu3.FastDtoa.biggestPowerTen(int, int):long");
        }

        FastDtoa() {
        }

        static boolean roundWeed(FastDtoaBuilder fastDtoaBuilder, long j, long j2, long j3, long j4, long j5) {
            long j6 = j - j5;
            long j7 = j + j5;
            long j8 = j3;
            while (j8 < j6 && j2 - j8 >= j4) {
                long j9 = j8 + j4;
                if (j9 >= j6 && j6 - j8 < j9 - j6) {
                    break;
                }
                fastDtoaBuilder.decreaseLast();
                j8 = j9;
            }
            if (j8 < j7 && j2 - j8 >= j4) {
                long j10 = j8 + j4;
                if (j10 < j7 || j7 - j8 > j10 - j7) {
                    return false;
                }
            }
            return 2 * j5 <= j8 && j8 <= j2 - (4 * j5);
        }

        static boolean digitGen(FastDtoaBuilder fastDtoaBuilder, int i) {
            DiyFp diyFp = fastDtoaBuilder.scaled_boundary_minus;
            DiyFp diyFp2 = fastDtoaBuilder.scaled_w;
            DiyFp diyFp3 = fastDtoaBuilder.scaled_boundary_plus;
            DiyFp diyFp4 = fastDtoaBuilder.too_low;
            diyFp4.f = diyFp.f - 1;
            diyFp4.e = diyFp.e;
            DiyFp diyFp5 = fastDtoaBuilder.too_high;
            diyFp5.f = diyFp3.f + 1;
            diyFp5.e = diyFp3.e;
            DiyFp diyFp6 = fastDtoaBuilder.unsafe_interval;
            diyFp6.f = diyFp5.f;
            diyFp6.e = diyFp5.e;
            diyFp6.subtract(diyFp4);
            DiyFp diyFp7 = fastDtoaBuilder.one;
            int i2 = diyFp2.e;
            long j = 1 << (-i2);
            diyFp7.f = j;
            diyFp7.e = i2;
            long j2 = diyFp5.f;
            int i3 = (int) ((j2 >>> (-i2)) & 4294967295L);
            long j3 = (j - 1) & j2;
            long jBiggestPowerTen = biggestPowerTen(i3, 64 - (-i2));
            int i4 = (int) ((jBiggestPowerTen >>> 32) & 4294967295L);
            int i5 = ((int) (jBiggestPowerTen & 4294967295L)) + 1;
            while (i5 > 0) {
                fastDtoaBuilder.append((byte) ((i3 / i4) + 48));
                i3 %= i4;
                i5--;
                long j4 = (((long) i3) << (-diyFp7.e)) + j3;
                if (j4 >= diyFp6.f) {
                    i4 /= 10;
                } else {
                    fastDtoaBuilder.point = (fastDtoaBuilder.end - i) + i5;
                    DiyFp diyFp8 = fastDtoaBuilder.minus_round;
                    diyFp8.f = diyFp5.f;
                    diyFp8.e = diyFp5.e;
                    diyFp8.subtract(diyFp2);
                    return roundWeed(fastDtoaBuilder, diyFp8.f, diyFp6.f, j4, ((long) i4) << (-diyFp7.e), 1L);
                }
            }
            long j5 = 1;
            while (true) {
                long j6 = j3 * 5;
                long j7 = j5 * 5;
                diyFp6.f *= 5;
                diyFp6.e++;
                diyFp7.f >>>= 1;
                int i6 = diyFp7.e + 1;
                diyFp7.e = i6;
                fastDtoaBuilder.append((byte) (((int) ((j6 >>> (-i6)) & 4294967295L)) + 48));
                j3 = j6 & (diyFp7.f - 1);
                i5--;
                if (j3 < diyFp6.f) {
                    fastDtoaBuilder.point = (fastDtoaBuilder.end - i) + i5;
                    DiyFp diyFp9 = fastDtoaBuilder.minus_round;
                    diyFp9.f = diyFp5.f;
                    diyFp9.e = diyFp5.e;
                    diyFp9.subtract(diyFp2);
                    return roundWeed(fastDtoaBuilder, diyFp9.f * j7, diyFp6.f, j3, diyFp7.f, j7);
                }
                j5 = j7;
            }
        }
    }

    public static boolean tryConvert(double d, FastDtoaBuilder fastDtoaBuilder) {
        long jDoubleToLongBits;
        int i;
        fastDtoaBuilder.reset();
        if (d < 0.0d) {
            fastDtoaBuilder.append((byte) 45);
            jDoubleToLongBits = Double.doubleToLongBits(-d);
            i = 1;
        } else {
            jDoubleToLongBits = Double.doubleToLongBits(d);
            i = 0;
        }
        if (!FastDtoa.digitGen(fastDtoaBuilder, fastDtoaBuilder.initialize(jDoubleToLongBits))) {
            return false;
        }
        fastDtoaBuilder.write(i);
        return true;
    }

    static class FastDtoaBuilder {
        static final byte[] digits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
        private int point;
        private final DiyFp v = new DiyFp();
        private final DiyFp w = new DiyFp();
        private final DiyFp boundary_minus = new DiyFp();
        private final DiyFp boundary_plus = new DiyFp();
        private final DiyFp ten_mk = new DiyFp();
        private final DiyFp scaled_w = new DiyFp();
        private final DiyFp scaled_boundary_minus = new DiyFp();
        private final DiyFp scaled_boundary_plus = new DiyFp();
        private final DiyFp too_low = new DiyFp();
        private final DiyFp too_high = new DiyFp();
        private final DiyFp unsafe_interval = new DiyFp();
        private final DiyFp one = new DiyFp();
        private final DiyFp minus_round = new DiyFp();
        private final byte[] chars = new byte[27];
        private int end = 0;

        FastDtoaBuilder() {
        }

        int initialize(long j) {
            DoubleHelper.asNormalizedDiyFp(j, this.w);
            this.boundary_minus.reset();
            this.boundary_plus.reset();
            DoubleHelper.normalizedBoundaries(this.v, j, this.boundary_minus, this.boundary_plus);
            this.ten_mk.reset();
            int cachedPower = CachedPowers.getCachedPower(this.w.e + 64, -60, this.ten_mk);
            DiyFp diyFp = this.scaled_w;
            DiyFp diyFp2 = this.w;
            diyFp.f = diyFp2.f;
            diyFp.e = diyFp2.e;
            diyFp.multiply(this.ten_mk);
            DiyFp diyFp3 = this.scaled_boundary_minus;
            DiyFp diyFp4 = this.boundary_minus;
            diyFp3.f = diyFp4.f;
            diyFp3.e = diyFp4.e;
            diyFp3.multiply(this.ten_mk);
            DiyFp diyFp5 = this.scaled_boundary_plus;
            DiyFp diyFp6 = this.boundary_plus;
            diyFp5.f = diyFp6.f;
            diyFp5.e = diyFp6.e;
            diyFp5.multiply(this.ten_mk);
            return cachedPower;
        }

        void reset() {
            this.end = 0;
        }

        void append(byte b) {
            byte[] bArr = this.chars;
            int i = this.end;
            this.end = i + 1;
            bArr[i] = b;
        }

        void decreaseLast() {
            this.chars[this.end - 1] = (byte) (r0[r1] - 1);
        }

        public String toString() {
            return "[chars:" + new String(this.chars, 0, this.end) + ", point:" + this.point + "]";
        }

        int copyTo(byte[] bArr, int i) {
            int i2 = 0;
            while (true) {
                int i3 = this.end;
                if (i2 >= i3) {
                    return i3;
                }
                bArr[i2 + i] = this.chars[i2];
                i2++;
            }
        }

        public void write(int i) {
            int i2 = this.point - i;
            if (i2 < -5 || i2 > 21) {
                toExponentialFormat(i, i2);
            } else {
                toFixedFormat(i, i2);
            }
        }

        private void toFixedFormat(int i, int i2) {
            int i3 = this.point;
            int i4 = this.end;
            if (i3 < i4) {
                if (i2 <= 0) {
                    int i5 = 2 - i2;
                    for (int i6 = i4 + i; i6 >= i; i6--) {
                        byte[] bArr = this.chars;
                        bArr[i6 + i5] = bArr[i6];
                    }
                    byte[] bArr2 = this.chars;
                    bArr2[i] = 48;
                    bArr2[i + 1] = 46;
                    if (i2 < 0) {
                        int i7 = i + 2;
                        int i8 = i7 - i2;
                        while (i7 < i8) {
                            this.chars[i7] = 48;
                            i7++;
                        }
                    }
                    this.end += i5;
                    return;
                }
                while (true) {
                    int i9 = this.point;
                    if (i4 >= i9) {
                        byte[] bArr3 = this.chars;
                        bArr3[i4 + 1] = bArr3[i4];
                        i4--;
                    } else {
                        this.chars[i9] = 46;
                        this.end++;
                        return;
                    }
                }
            } else {
                if (i3 <= i4) {
                    byte[] bArr4 = this.chars;
                    bArr4[i4] = 46;
                    bArr4[i4 + 1] = 48;
                    this.end = i4 + 2;
                    return;
                }
                while (true) {
                    int i10 = this.point;
                    if (i4 < i10) {
                        this.chars[i4] = 48;
                        i4++;
                    } else {
                        int i11 = this.end;
                        int i12 = i11 + (i10 - i11);
                        byte[] bArr5 = this.chars;
                        bArr5[i12] = 46;
                        bArr5[i12 + 1] = 48;
                        this.end = i12 + 2;
                        return;
                    }
                }
            }
        }

        private void toExponentialFormat(int i, int i2) {
            int i3 = this.end;
            if (i3 - i > 1) {
                int i4 = i + 1;
                byte[] bArr = this.chars;
                System.arraycopy(bArr, i4, bArr, i4 + 1, i3 - i4);
                this.chars[i4] = 46;
                this.end++;
            }
            byte[] bArr2 = this.chars;
            int i5 = this.end;
            int i6 = i5 + 1;
            this.end = i6;
            bArr2[i5] = 69;
            byte b = 43;
            int i7 = i2 - 1;
            if (i7 < 0) {
                b = 45;
                i7 = -i7;
            }
            int i8 = i6 + 1;
            this.end = i8;
            bArr2[i6] = b;
            if (i7 > 99) {
                i8 += 2;
            } else if (i7 > 9) {
                i8++;
            }
            this.end = i8 + 1;
            while (true) {
                int i9 = i8 - 1;
                this.chars[i8] = digits[i7 % 10];
                i7 /= 10;
                if (i7 == 0) {
                    return;
                } else {
                    i8 = i9;
                }
            }
        }
    }
}
