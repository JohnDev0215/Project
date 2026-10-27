package v6;

/* JADX INFO: compiled from: MaskUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static int a(b bVar) {
        return b(bVar, true) + b(bVar, false);
    }

    public static int b(b bVar, boolean z7) {
        int iD = z7 ? bVar.d() : bVar.e();
        int iE = z7 ? bVar.e() : bVar.d();
        byte[][] bArrC = bVar.c();
        int i8 = 0;
        for (int i9 = 0; i9 < iD; i9++) {
            byte b8 = -1;
            int i10 = 0;
            for (int i11 = 0; i11 < iE; i11++) {
                byte b9 = z7 ? bArrC[i9][i11] : bArrC[i11][i9];
                if (b9 == b8) {
                    i10++;
                } else {
                    if (i10 >= 5) {
                        i8 += (i10 - 5) + 3;
                    }
                    i10 = 1;
                    b8 = b9;
                }
            }
            if (i10 >= 5) {
                i8 += (i10 - 5) + 3;
            }
        }
        return i8;
    }

    public static int c(b bVar) {
        byte[][] bArrC = bVar.c();
        int iE = bVar.e();
        int iD = bVar.d();
        int i8 = 0;
        for (int i9 = 0; i9 < iD - 1; i9++) {
            byte[] bArr = bArrC[i9];
            int i10 = 0;
            while (i10 < iE - 1) {
                byte b8 = bArr[i10];
                int i11 = i10 + 1;
                if (b8 == bArr[i11]) {
                    int i12 = i9 + 1;
                    if (b8 == bArrC[i12][i10] && b8 == bArrC[i12][i11]) {
                        i8++;
                    }
                }
                i10 = i11;
            }
        }
        return i8 * 3;
    }

    public static int d(b bVar) {
        byte[][] bArrC = bVar.c();
        int iE = bVar.e();
        int iD = bVar.d();
        int i8 = 0;
        for (int i9 = 0; i9 < iD; i9++) {
            for (int i10 = 0; i10 < iE; i10++) {
                byte[] bArr = bArrC[i9];
                int i11 = i10 + 6;
                if (i11 < iE && bArr[i10] == 1 && bArr[i10 + 1] == 0 && bArr[i10 + 2] == 1 && bArr[i10 + 3] == 1 && bArr[i10 + 4] == 1 && bArr[i10 + 5] == 0 && bArr[i11] == 1 && (g(bArr, i10 - 4, i10) || g(bArr, i10 + 7, i10 + 11))) {
                    i8++;
                }
                int i12 = i9 + 6;
                if (i12 < iD && bArrC[i9][i10] == 1 && bArrC[i9 + 1][i10] == 0 && bArrC[i9 + 2][i10] == 1 && bArrC[i9 + 3][i10] == 1 && bArrC[i9 + 4][i10] == 1 && bArrC[i9 + 5][i10] == 0 && bArrC[i12][i10] == 1 && (h(bArrC, i10, i9 - 4, i9) || h(bArrC, i10, i9 + 7, i9 + 11))) {
                    i8++;
                }
            }
        }
        return i8 * 40;
    }

    public static int e(b bVar) {
        byte[][] bArrC = bVar.c();
        int iE = bVar.e();
        int iD = bVar.d();
        int i8 = 0;
        for (int i9 = 0; i9 < iD; i9++) {
            byte[] bArr = bArrC[i9];
            for (int i10 = 0; i10 < iE; i10++) {
                if (bArr[i10] == 1) {
                    i8++;
                }
            }
        }
        int iD2 = bVar.d() * bVar.e();
        return ((Math.abs((i8 * 2) - iD2) * 10) / iD2) * 10;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0045 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean f(int r1, int r2, int r3) {
        /*
            r0 = 1
            switch(r1) {
                case 0: goto L3f;
                case 1: goto L40;
                case 2: goto L3c;
                case 3: goto L38;
                case 4: goto L33;
                case 5: goto L2c;
                case 6: goto L24;
                case 7: goto L1b;
                default: goto L4;
            }
        L4:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "Invalid mask pattern: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L1b:
            int r1 = r3 * r2
            int r1 = r1 % 3
            int r3 = r3 + r2
            r2 = r3 & 1
            int r1 = r1 + r2
            goto L2a
        L24:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
        L2a:
            r1 = r1 & r0
            goto L42
        L2c:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L42
        L33:
            int r3 = r3 / 2
            int r2 = r2 / 3
            goto L3f
        L38:
            int r3 = r3 + r2
            int r1 = r3 % 3
            goto L42
        L3c:
            int r1 = r2 % 3
            goto L42
        L3f:
            int r3 = r3 + r2
        L40:
            r1 = r3 & 1
        L42:
            if (r1 != 0) goto L45
            goto L46
        L45:
            r0 = 0
        L46:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: v6.d.f(int, int, int):boolean");
    }

    public static boolean g(byte[] bArr, int i8, int i9) {
        if (i8 < 0 || bArr.length < i9) {
            return false;
        }
        while (i8 < i9) {
            if (bArr[i8] == 1) {
                return false;
            }
            i8++;
        }
        return true;
    }

    public static boolean h(byte[][] bArr, int i8, int i9, int i10) {
        if (i9 < 0 || bArr.length < i10) {
            return false;
        }
        while (i9 < i10) {
            if (bArr[i9][i8] == 1) {
                return false;
            }
            i9++;
        }
        return true;
    }
}
