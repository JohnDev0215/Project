package i2;

import B2.N;
import F1.C0254m;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f15713a = {0, 0, 0, 1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f15714b = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final Object c = new Object();
    public static int[] d = new int[10];

    public static void a(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static int b(byte[] bArr, int i7, int i10, boolean[] zArr) {
        int i11 = i10 - i7;
        AbstractC1346c.j(i11 >= 0);
        if (i11 == 0) {
            return i10;
        }
        if (zArr[0]) {
            a(zArr);
            return i7 - 3;
        }
        if (i11 > 1 && zArr[1] && bArr[i7] == 1) {
            a(zArr);
            return i7 - 2;
        }
        if (i11 > 2 && zArr[2] && bArr[i7] == 0 && bArr[i7 + 1] == 1) {
            a(zArr);
            return i7 - 1;
        }
        int i12 = i10 - 1;
        int i13 = i7 + 2;
        while (i13 < i12) {
            byte b10 = bArr[i13];
            if ((b10 & 254) == 0) {
                int i14 = i13 - 2;
                if (bArr[i14] == 0 && bArr[i13 - 1] == 0 && b10 == 1) {
                    a(zArr);
                    return i14;
                }
                i13 -= 2;
            }
            i13 += 3;
        }
        zArr[0] = i11 <= 2 ? !(i11 != 2 ? !(zArr[1] && bArr[i12] == 1) : !(zArr[2] && bArr[i10 + (-2)] == 0 && bArr[i12] == 1)) : bArr[i10 + (-3)] == 0 && bArr[i10 + (-2)] == 0 && bArr[i12] == 1;
        zArr[1] = i11 <= 1 ? zArr[2] && bArr[i12] == 0 : bArr[i10 + (-2)] == 0 && bArr[i12] == 0;
        zArr[2] = bArr[i12] == 0;
        return i10;
    }

    public static boolean c(byte b10) {
        if (((b10 & 96) >> 5) != 0) {
            return true;
        }
        int i7 = b10 & 31;
        return (i7 == 1 || i7 == 9 || i7 == 14) ? false : true;
    }

    public static C0254m d(N n10) {
        n10.t();
        int i7 = n10.i(6);
        int i10 = n10.i(6);
        n10.i(3);
        return new C0254m(i7, i10, 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static i2.h e(B2.N r19, boolean r20, int r21, i2.h r22) {
        /*
            r0 = r19
            r1 = r21
            r2 = r22
            r3 = 6
            int[] r4 = new int[r3]
            r5 = 2
            r6 = 8
            r7 = 0
            if (r20 == 0) goto L42
            int r2 = r0.i(r5)
            boolean r8 = r19.h()
            r9 = 5
            int r9 = r0.i(r9)
            r10 = r7
            r11 = r10
        L1e:
            r12 = 32
            if (r10 >= r12) goto L2e
            boolean r12 = r19.h()
            if (r12 == 0) goto L2b
            r12 = 1
            int r12 = r12 << r10
            r11 = r11 | r12
        L2b:
            int r10 = r10 + 1
            goto L1e
        L2e:
            r10 = r7
        L2f:
            if (r10 >= r3) goto L3a
            int r12 = r0.i(r6)
            r4[r10] = r12
            int r10 = r10 + 1
            goto L2f
        L3a:
            r13 = r2
        L3b:
            r17 = r4
            r14 = r8
            r15 = r9
            r16 = r11
            goto L57
        L42:
            if (r2 == 0) goto L50
            int r3 = r2.f15679a
            boolean r8 = r2.f15680b
            int r9 = r2.c
            int r11 = r2.d
            int[] r4 = r2.f15681e
            r13 = r3
            goto L3b
        L50:
            r17 = r4
            r13 = r7
            r14 = r13
            r15 = r14
            r16 = r15
        L57:
            int r18 = r0.i(r6)
            r2 = r7
        L5c:
            if (r7 >= r1) goto L71
            boolean r3 = r19.h()
            if (r3 == 0) goto L66
            int r2 = r2 + 88
        L66:
            boolean r3 = r19.h()
            if (r3 == 0) goto L6e
            int r2 = r2 + 8
        L6e:
            int r7 = r7 + 1
            goto L5c
        L71:
            r0.u(r2)
            if (r1 <= 0) goto L7b
            int r6 = r6 - r1
            int r6 = r6 * r5
            r0.u(r6)
        L7b:
            i2.h r0 = new i2.h
            r12 = r0
            r12.<init>(r13, r14, r15, r16, r17, r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.n.e(B2.N, boolean, int, i2.h):i2.h");
    }

    public static L8.p f(int i7, int i10, byte[] bArr) {
        byte b10;
        int i11 = i7 + 2;
        do {
            i10--;
            b10 = bArr[i10];
            if (b10 != 0) {
                break;
            }
        } while (i10 > i11);
        if (b10 != 0 && i10 > i11) {
            N n10 = new N(i11, i10 + 1, bArr);
            while (n10.d(16)) {
                int i12 = n10.i(8);
                int i13 = 0;
                while (i12 == 255) {
                    i13 += 255;
                    i12 = n10.i(8);
                }
                int i14 = i13 + i12;
                int i15 = n10.i(8);
                int i16 = 0;
                while (i15 == 255) {
                    i16 += 255;
                    i15 = n10.i(8);
                }
                int i17 = i16 + i15;
                if (i17 == 0 || !n10.d(i17)) {
                    break;
                }
                if (i14 == 176) {
                    int iM = n10.m();
                    boolean zH = n10.h();
                    int iM2 = zH ? n10.m() : 0;
                    int iM3 = n10.m();
                    int iM4 = -1;
                    for (int i18 = 0; i18 <= iM3; i18++) {
                        iM4 = n10.m();
                        n10.m();
                        int i19 = n10.i(6);
                        if (i19 == 63) {
                            return null;
                        }
                        n10.i(i19 == 0 ? Math.max(0, iM - 30) : Math.max(0, (i19 + iM) - 31));
                        if (zH) {
                            int i20 = n10.i(6);
                            if (i20 == 63) {
                                return null;
                            }
                            n10.i(i20 == 0 ? Math.max(0, iM2 - 30) : Math.max(0, (i20 + iM2) - 31));
                        }
                        if (n10.h()) {
                            n10.u(10);
                        }
                    }
                    return new L8.p(iM4);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static i2.j g(byte[] r30, int r31, int r32, B8.t r33) {
        /*
            Method dump skipped, instruction units count: 1002
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.n.g(byte[], int, int, B8.t):i2.j");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x05af A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static B8.t h(int r36, int r37, byte[] r38) {
        /*
            Method dump skipped, instruction units count: 2041
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.n.h(int, int, byte[]):B8.t");
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0183  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static i2.m i(int r30, int r31, byte[] r32) {
        /*
            Method dump skipped, instruction units count: 628
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.n.i(int, int, byte[]):i2.m");
    }

    public static void j(N n10) {
        int iM = n10.m() + 1;
        n10.u(8);
        for (int i7 = 0; i7 < iM; i7++) {
            n10.m();
            n10.m();
            n10.t();
        }
        n10.u(20);
    }

    public static int k(int i7, byte[] bArr) {
        int i10;
        synchronized (c) {
            int i11 = 0;
            int i12 = 0;
            while (i11 < i7) {
                while (true) {
                    if (i11 >= i7 - 2) {
                        i11 = i7;
                        break;
                    }
                    try {
                        if (bArr[i11] == 0 && bArr[i11 + 1] == 0 && bArr[i11 + 2] == 3) {
                            break;
                        }
                        i11++;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (i11 < i7) {
                    int[] iArr = d;
                    if (iArr.length <= i12) {
                        d = Arrays.copyOf(iArr, iArr.length * 2);
                    }
                    d[i12] = i11;
                    i11 += 3;
                    i12++;
                }
            }
            i10 = i7 - i12;
            int i13 = 0;
            int i14 = 0;
            for (int i15 = 0; i15 < i12; i15++) {
                int i16 = d[i15] - i14;
                System.arraycopy(bArr, i14, bArr, i13, i16);
                int i17 = i13 + i16;
                int i18 = i17 + 1;
                bArr[i17] = 0;
                i13 = i17 + 2;
                bArr[i18] = 0;
                i14 += i16 + 3;
            }
            System.arraycopy(bArr, i14, bArr, i13, i10 - i13);
        }
        return i10;
    }
}
