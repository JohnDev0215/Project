package B2;

import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.util.Base64;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: B2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0062b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f530a = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, BYDAutoEngineDevice.ENGINE_SPEED_MAX, 7350};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f531b = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};
    public static final int[] c = {1, 2, 3, 6};
    public static final int[] d = {48000, 44100, 32000};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f532e = {24000, 22050, 16000};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f533f = {2, 1, 2, 3, 3, 4, 4, 5};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f534g = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384, 448, 512, 576, 640};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f535h = {69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f536i = {2002, 2000, 1920, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f537j = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f538k = {-1, BYDAutoEngineDevice.ENGINE_SPEED_MAX, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    public static final int[] l = {64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f539m = {BYDAutoEngineDevice.ENGINE_SPEED_MAX, 16000, 32000, 64000, 128000, 22050, 44100, 88200, 176400, 352800, 12000, 24000, 48000, 96000, 192000, 384000};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f540n = {5, 8, 10, 12};
    public static final int[] o = {6, 9, 12, 15};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f541p = {2, 4, 6, 8};

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int[] f542q = {9, 11, 13, 16};

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f543r = {5, 8, 10, 12};

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String[] f544s = {"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int[] f545t = {44100, 48000, 32000};

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final int[] f546u = {32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final int[] f547v = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final int[] f548w = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final int[] f549x = {32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f550y = {BYDAutoEngineDevice.ENGINE_SPEED_MAX, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000};

    public static ArrayList a(byte[] bArr) {
        long j3 = (((long) (((bArr[11] & 255) << 8) | (bArr[10] & 255))) * 1000000000) / 48000;
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j3).array());
        arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(80000000L).array());
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b(h2.t r20, B2.x r21, int r22, B2.v r23) {
        /*
            r0 = r20
            r1 = r21
            r2 = 10
            r3 = 1
            int r4 = r0.f15242b
            long r5 = r20.x()
            r7 = 16
            long r7 = r5 >>> r7
            r9 = r22
            long r9 = (long) r9
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r10 = 0
            if (r9 == 0) goto L1a
            return r10
        L1a:
            r11 = 1
            long r7 = r7 & r11
            int r7 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r7 != 0) goto L23
            r7 = r3
            goto L24
        L23:
            r7 = r10
        L24:
            r8 = 12
            long r13 = r5 >> r8
            r15 = 15
            long r13 = r13 & r15
            int r9 = (int) r13
            r13 = 8
            long r13 = r5 >> r13
            long r13 = r13 & r15
            int r13 = (int) r13
            r14 = 4
            long r17 = r5 >> r14
            long r14 = r17 & r15
            int r14 = (int) r14
            long r15 = r5 >> r3
            r17 = 7
            r19 = r9
            long r8 = r15 & r17
            int r8 = (int) r8
            long r5 = r5 & r11
            int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r5 != 0) goto L48
            r5 = r3
            goto L49
        L48:
            r5 = r10
        L49:
            r6 = 7
            if (r14 > r6) goto L52
            int r6 = r1.f607g
            int r6 = r6 - r3
            if (r14 != r6) goto Lc3
            goto L59
        L52:
            if (r14 > r2) goto Lc3
            int r6 = r1.f607g
            r9 = 2
            if (r6 != r9) goto Lc3
        L59:
            if (r8 != 0) goto L5c
            goto L60
        L5c:
            int r6 = r1.f609i
            if (r8 != r6) goto Lc3
        L60:
            if (r5 != 0) goto Lc3
            long r5 = r20.C()     // Catch: java.lang.NumberFormatException -> Lc3
            if (r7 == 0) goto L6b
        L68:
            r7 = r23
            goto L70
        L6b:
            int r7 = r1.f604b
            long r7 = (long) r7
            long r5 = r5 * r7
            goto L68
        L70:
            r7.f600a = r5
            r5 = r19
            int r5 = s(r5, r0)
            r6 = -1
            if (r5 == r6) goto Lc3
            int r6 = r1.f604b
            if (r5 > r6) goto Lc3
            if (r13 != 0) goto L82
            goto La7
        L82:
            r5 = 11
            if (r13 > r5) goto L8b
            int r1 = r1.f606f
            if (r13 != r1) goto Lc3
            goto La7
        L8b:
            int r1 = r1.f605e
            r5 = 12
            if (r13 != r5) goto L9a
            int r2 = r20.v()
            int r2 = r2 * 1000
            if (r2 != r1) goto Lc3
            goto La7
        L9a:
            r5 = 14
            if (r13 > r5) goto Lc3
            int r6 = r20.B()
            if (r13 != r5) goto La5
            int r6 = r6 * r2
        La5:
            if (r6 != r1) goto Lc3
        La7:
            int r1 = r20.v()
            int r2 = r0.f15242b
            byte[] r0 = r0.f15241a
            int r2 = r2 - r3
            int r5 = h2.AbstractC1343A.f15195a
            r5 = r10
        Lb3:
            if (r4 >= r2) goto Lc0
            r6 = r0[r4]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5 = r5 ^ r6
            int[] r6 = h2.AbstractC1343A.o
            r5 = r6[r5]
            int r4 = r4 + r3
            goto Lb3
        Lc0:
            if (r1 != r5) goto Lc3
            goto Lc4
        Lc3:
            r3 = r10
        Lc4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.AbstractC0062b.b(h2.t, B2.x, int, B2.v):boolean");
    }

    public static void c(String str, boolean z6) throws e2.M {
        if (!z6) {
            throw e2.M.a(null, str);
        }
    }

    public static void d(long j3, h2.t tVar, L[] lArr) {
        int i7;
        while (true) {
            if (tVar.a() <= 1) {
                return;
            }
            int i10 = 0;
            while (true) {
                if (tVar.a() == 0) {
                    i7 = -1;
                    break;
                }
                int iV = tVar.v();
                i10 += iV;
                if (iV != 255) {
                    i7 = i10;
                    break;
                }
            }
            int i11 = 0;
            while (true) {
                if (tVar.a() == 0) {
                    i11 = -1;
                    break;
                }
                int iV2 = tVar.v();
                i11 += iV2;
                if (iV2 != 255) {
                    break;
                }
            }
            int i12 = tVar.f15242b + i11;
            if (i11 == -1 || i11 > tVar.a()) {
                AbstractC1346c.z("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                i12 = tVar.c;
            } else if (i7 == 4 && i11 >= 8) {
                int iV3 = tVar.v();
                int iB = tVar.B();
                int iH = iB == 49 ? tVar.h() : 0;
                int iV4 = tVar.v();
                if (iB == 47) {
                    tVar.I(1);
                }
                boolean z6 = iV3 == 181 && (iB == 49 || iB == 47) && iV4 == 3;
                if (iB == 49) {
                    z6 &= iH == 1195456820;
                }
                if (z6) {
                    e(j3, tVar, lArr);
                }
            }
            tVar.H(i12);
        }
    }

    public static void e(long j3, h2.t tVar, L[] lArr) {
        int iV = tVar.v();
        if ((iV & 64) != 0) {
            tVar.I(1);
            int i7 = (iV & 31) * 3;
            int i10 = tVar.f15242b;
            for (L l10 : lArr) {
                tVar.H(i10);
                l10.a(tVar, i7, 0);
                AbstractC1346c.j(j3 != -9223372036854775807L);
                l10.b(j3, 1, i7, 0, null);
            }
        }
    }

    public static int f(int i7, int i10) {
        int i11 = i10 / 2;
        if (i7 < 0 || i7 >= 3 || i10 < 0 || i11 >= 19) {
            return -1;
        }
        int i12 = d[i7];
        if (i12 == 44100) {
            return ((i10 % 2) + f535h[i11]) * 2;
        }
        int i13 = f534g[i11];
        return i12 == 32000 ? i13 * 6 : i13 * 4;
    }

    public static void g(int i7, h2.t tVar) {
        tVar.E(7);
        byte[] bArr = tVar.f15241a;
        bArr[0] = -84;
        bArr[1] = 64;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = (byte) ((i7 >> 16) & 255);
        bArr[5] = (byte) ((i7 >> 8) & 255);
        bArr[6] = (byte) (i7 & 255);
    }

    public static int h(int i7) {
        int i10;
        int i11;
        int i12;
        int i13;
        if (!((i7 & (-2097152)) == -2097152) || (i10 = (i7 >>> 19) & 3) == 1 || (i11 = (i7 >>> 17) & 3) == 0 || (i12 = (i7 >>> 12) & 15) == 0 || i12 == 15 || (i13 = (i7 >>> 10) & 3) == 3) {
            return -1;
        }
        int i14 = f545t[i13];
        if (i10 == 2) {
            i14 /= 2;
        } else if (i10 == 0) {
            i14 /= 4;
        }
        int i15 = (i7 >>> 9) & 1;
        if (i11 == 3) {
            return ((((i10 == 3 ? f546u[i12 - 1] : f547v[i12 - 1]) * 12) / i14) + i15) * 4;
        }
        int i16 = i10 == 3 ? i11 == 2 ? f548w[i12 - 1] : f549x[i12 - 1] : f550y[i12 - 1];
        if (i10 == 3) {
            return ((i16 * 144) / i14) + i15;
        }
        return (((i11 == 1 ? 72 : 144) * i16) / i14) + i15;
    }

    public static N i(byte[] bArr) {
        byte b10 = bArr[0];
        if (b10 == 127 || b10 == 100 || b10 == 64 || b10 == 113) {
            return new N(bArr.length, bArr);
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        byte b11 = bArrCopyOf[0];
        if (b11 == -2 || b11 == -1 || b11 == 37 || b11 == -14 || b11 == -24) {
            for (int i7 = 0; i7 < bArrCopyOf.length - 1; i7 += 2) {
                byte b12 = bArrCopyOf[i7];
                int i10 = i7 + 1;
                bArrCopyOf[i7] = bArrCopyOf[i10];
                bArrCopyOf[i10] = b12;
            }
        }
        N n10 = new N(bArrCopyOf.length, bArrCopyOf);
        if (bArrCopyOf[0] == 31) {
            N n11 = new N(bArrCopyOf.length, bArrCopyOf);
            while (n11.b() >= 16) {
                n11.u(2);
                int i11 = n11.i(14) & 16383;
                int iMin = Math.min(8 - n10.c, 14);
                int i12 = n10.c;
                int i13 = (8 - i12) - iMin;
                byte[] bArr2 = n10.d;
                int i14 = n10.f525b;
                byte b13 = (byte) (((65280 >> i12) | ((1 << i13) - 1)) & bArr2[i14]);
                bArr2[i14] = b13;
                int i15 = 14 - iMin;
                bArr2[i14] = (byte) (b13 | ((i11 >>> i15) << i13));
                int i16 = i14 + 1;
                while (i15 > 8) {
                    n10.d[i16] = (byte) (i11 >>> (i15 - 8));
                    i15 -= 8;
                    i16++;
                }
                int i17 = 8 - i15;
                byte[] bArr3 = n10.d;
                byte b14 = (byte) (bArr3[i16] & ((1 << i17) - 1));
                bArr3[i16] = b14;
                bArr3[i16] = (byte) (((i11 & ((1 << i15) - 1)) << i17) | b14);
                n10.u(14);
                n10.a();
            }
        }
        n10.p(bArrCopyOf.length, bArrCopyOf);
        return n10;
    }

    public static long j(byte b10, byte b11) {
        int i7;
        int i10 = b10 & 255;
        int i11 = b10 & 3;
        if (i11 != 0) {
            i7 = 2;
            if (i11 != 1 && i11 != 2) {
                i7 = b11 & 63;
            }
        } else {
            i7 = 1;
        }
        int i12 = i10 >> 3;
        int i13 = i12 & 3;
        return ((long) i7) * ((long) (i12 >= 16 ? 2500 << i13 : i12 >= 12 ? 10000 << (i12 & 1) : i13 == 3 ? 60000 : 10000 << i13));
    }

    public static int k(N n10) throws e2.M {
        int i7 = n10.i(4);
        if (i7 == 15) {
            if (n10.b() >= 24) {
                return n10.i(24);
            }
            throw e2.M.a(null, "AAC header insufficient data");
        }
        if (i7 < 13) {
            return f530a[i7];
        }
        throw e2.M.a(null, "AAC header wrong Sampling Frequency Index");
    }

    public static int l(int i7) {
        int i10 = 0;
        while (i7 > 0) {
            i10++;
            i7 >>>= 1;
        }
        return i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static B2.C0064d m(B2.N r9) {
        /*
            r0 = 16
            int r1 = r9.i(r0)
            int r0 = r9.i(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 4
            if (r0 != r2) goto L18
            r0 = 24
            int r0 = r9.i(r0)
            r2 = 7
            goto L19
        L18:
            r2 = r3
        L19:
            int r0 = r0 + r2
            r2 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r2) goto L21
            int r0 = r0 + 2
        L21:
            r1 = 2
            int r2 = r9.i(r1)
            r4 = 3
            if (r2 != r4) goto L32
        L29:
            r9.i(r1)
            boolean r2 = r9.h()
            if (r2 != 0) goto L29
        L32:
            r2 = 10
            int r2 = r9.i(r2)
            boolean r5 = r9.h()
            if (r5 == 0) goto L47
            int r5 = r9.i(r4)
            if (r5 <= 0) goto L47
            r9.u(r1)
        L47:
            boolean r5 = r9.h()
            r6 = 44100(0xac44, float:6.1797E-41)
            r7 = 48000(0xbb80, float:6.7262E-41)
            if (r5 == 0) goto L55
            r5 = r7
            goto L56
        L55:
            r5 = r6
        L56:
            int r9 = r9.i(r3)
            int[] r8 = B2.AbstractC0062b.f536i
            if (r5 != r6) goto L65
            r6 = 13
            if (r9 != r6) goto L65
            r9 = r8[r9]
            goto L93
        L65:
            if (r5 != r7) goto L92
            r6 = 14
            if (r9 >= r6) goto L92
            r6 = r8[r9]
            int r2 = r2 % 5
            r7 = 1
            r8 = 8
            if (r2 == r7) goto L8b
            r7 = 11
            if (r2 == r1) goto L86
            if (r2 == r4) goto L8b
            if (r2 == r3) goto L7d
            goto L90
        L7d:
            if (r9 == r4) goto L83
            if (r9 == r8) goto L83
            if (r9 != r7) goto L90
        L83:
            int r9 = r6 + 1
            goto L93
        L86:
            if (r9 == r8) goto L83
            if (r9 != r7) goto L90
            goto L83
        L8b:
            if (r9 == r4) goto L83
            if (r9 != r8) goto L90
            goto L83
        L90:
            r9 = r6
            goto L93
        L92:
            r9 = 0
        L93:
            B2.d r1 = new B2.d
            r2 = 0
            r1.<init>(r5, r0, r9, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.AbstractC0062b.m(B2.N):B2.d");
    }

    public static C0061a n(N n10, boolean z6) throws e2.M {
        int i7 = n10.i(5);
        if (i7 == 31) {
            i7 = n10.i(6) + 32;
        }
        int iK = k(n10);
        int i10 = n10.i(4);
        String strE = android.hardware.bydauto.ac.tsimpl.a.e(i7, "mp4a.40.");
        if (i7 == 5 || i7 == 29) {
            iK = k(n10);
            int i11 = n10.i(5);
            if (i11 == 31) {
                i11 = n10.i(6) + 32;
            }
            i7 = i11;
            if (i7 == 22) {
                i10 = n10.i(4);
            }
        }
        if (z6) {
            if (i7 != 1 && i7 != 2 && i7 != 3 && i7 != 4 && i7 != 6 && i7 != 7 && i7 != 17) {
                switch (i7) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        break;
                    default:
                        throw e2.M.b("Unsupported audio object type: " + i7);
                }
            }
            if (n10.h()) {
                AbstractC1346c.z("AacUtil", "Unexpected frameLengthFlag = 1");
            }
            if (n10.h()) {
                n10.u(14);
            }
            boolean zH = n10.h();
            if (i10 == 0) {
                throw new UnsupportedOperationException();
            }
            if (i7 == 6 || i7 == 20) {
                n10.u(3);
            }
            if (zH) {
                if (i7 == 22) {
                    n10.u(16);
                }
                if (i7 == 17 || i7 == 19 || i7 == 20 || i7 == 23) {
                    n10.u(3);
                }
                n10.u(1);
            }
            switch (i7) {
                case 17:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    int i12 = n10.i(2);
                    if (i12 == 2 || i12 == 3) {
                        throw e2.M.b("Unsupported epConfig: " + i12);
                    }
                    break;
            }
        }
        int i13 = f531b[i10];
        if (i13 != -1) {
            return new C0061a(strE, iK, i13);
        }
        throw e2.M.a(null, null);
    }

    public static void o(N n10, C0063c c0063c) throws e2.M {
        int i7 = n10.i(5);
        n10.u(2);
        if (n10.h()) {
            n10.u(5);
        }
        if (i7 >= 7 && i7 <= 10) {
            n10.t();
        }
        if (n10.h()) {
            int i10 = n10.i(3);
            if (c0063c.f551a == -1 && i7 >= 0 && i7 <= 15 && (i10 == 0 || i10 == 1)) {
                c0063c.f551a = i7;
            }
            if (n10.h()) {
                v(n10);
            }
        }
    }

    public static void p(N n10, C0063c c0063c) throws e2.M {
        n10.u(2);
        boolean zH = n10.h();
        int i7 = n10.i(8);
        for (int i10 = 0; i10 < i7; i10++) {
            n10.u(2);
            if (n10.h()) {
                n10.u(5);
            }
            if (zH) {
                n10.u(24);
            } else {
                if (n10.h()) {
                    if (!n10.h()) {
                        n10.u(4);
                    }
                    c0063c.f552b = n10.i(6) + 1;
                }
                n10.u(4);
            }
        }
        if (n10.h()) {
            n10.u(3);
            if (n10.h()) {
                v(n10);
            }
        }
    }

    public static int q(N n10, int[] iArr) {
        int i7 = 0;
        for (int i10 = 0; i10 < 3 && n10.h(); i10++) {
            i7++;
        }
        int i11 = 0;
        for (int i12 = 0; i12 < i7; i12++) {
            i11 += 1 << iArr[i12];
        }
        return n10.i(iArr[i7]) + i11;
    }

    public static e2.K r(List list) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < list.size(); i7++) {
            String str = (String) list.get(i7);
            int i10 = AbstractC1343A.f15195a;
            String[] strArrSplit = str.split("=", 2);
            if (strArrSplit.length != 2) {
                AbstractC1346c.z("VorbisUtil", "Failed to parse Vorbis comment: ".concat(str));
            } else if (strArrSplit[0].equals("METADATA_BLOCK_PICTURE")) {
                try {
                    arrayList.add(N2.a.a(new h2.t(Base64.decode(strArrSplit[1], 0))));
                } catch (RuntimeException e10) {
                    AbstractC1346c.A("VorbisUtil", "Failed to parse vorbis picture", e10);
                }
            } else {
                arrayList.add(new S2.a(strArrSplit[0], strArrSplit[1]));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new e2.K(arrayList);
    }

    public static int s(int i7, h2.t tVar) {
        switch (i7) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i7 - 2);
            case 6:
                return tVar.v() + 1;
            case 7:
                return tVar.B() + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i7 - 8);
            default:
                return -1;
        }
    }

    public static C1489n1 t(h2.t tVar) {
        tVar.I(1);
        int iY = tVar.y();
        long j3 = ((long) tVar.f15242b) + ((long) iY);
        int i7 = iY / 18;
        long[] jArrCopyOf = new long[i7];
        long[] jArrCopyOf2 = new long[i7];
        int i10 = 0;
        while (true) {
            if (i10 >= i7) {
                break;
            }
            long jP = tVar.p();
            if (jP == -1) {
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, i10);
                jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i10);
                break;
            }
            jArrCopyOf[i10] = jP;
            jArrCopyOf2[i10] = tVar.p();
            tVar.I(2);
            i10++;
        }
        tVar.I((int) (j3 - ((long) tVar.f15242b)));
        return new C1489n1(jArrCopyOf, 1, jArrCopyOf2);
    }

    public static O u(h2.t tVar, boolean z6, boolean z10) throws e2.M {
        if (z6) {
            w(3, tVar, false);
        }
        tVar.t((int) tVar.m(), StandardCharsets.UTF_8);
        long jM = tVar.m();
        String[] strArr = new String[(int) jM];
        for (int i7 = 0; i7 < jM; i7++) {
            strArr[i7] = tVar.t((int) tVar.m(), StandardCharsets.UTF_8);
        }
        if (z10 && (tVar.v() & 1) == 0) {
            throw e2.M.a(null, "framing bit expected to be set");
        }
        return new O(strArr, 0);
    }

    public static void v(N n10) throws e2.M {
        int i7 = n10.i(6);
        if (i7 < 2 || i7 > 42) {
            throw e2.M.b(String.format("Invalid language tag bytes number: %d. Must be between 2 and 42.", Integer.valueOf(i7)));
        }
        n10.u(i7 * 8);
    }

    public static boolean w(int i7, h2.t tVar, boolean z6) throws e2.M {
        if (tVar.a() < 7) {
            if (z6) {
                return false;
            }
            throw e2.M.a(null, "too short header: " + tVar.a());
        }
        if (tVar.v() != i7) {
            if (z6) {
                return false;
            }
            throw e2.M.a(null, "expected header type " + Integer.toHexString(i7));
        }
        if (tVar.v() == 118 && tVar.v() == 111 && tVar.v() == 114 && tVar.v() == 98 && tVar.v() == 105 && tVar.v() == 115) {
            return true;
        }
        if (z6) {
            return false;
        }
        throw e2.M.a(null, "expected characters 'vorbis'");
    }
}
