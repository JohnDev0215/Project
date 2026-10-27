package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: HybridBinarizer.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f4216e;

    public i(com.google.zxing.d dVar) {
        super(dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009c A[PHI: r5
  0x009c: PHI (r5v4 int) = (r5v3 int), (r5v7 int), (r5v7 int) binds: [B:31:0x007a, B:33:0x007e, B:34:0x0080] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[][] i(byte[] r17, int r18, int r19, int r20, int r21) {
        /*
            r0 = r18
            r1 = r19
            r2 = 8
            int r3 = r21 + (-8)
            int r4 = r20 + (-8)
            r5 = 2
            int[] r6 = new int[r5]
            r7 = 1
            r6[r7] = r0
            r8 = 0
            r6[r8] = r1
            java.lang.Class<int> r9 = int.class
            java.lang.Object r6 = java.lang.reflect.Array.newInstance(r9, r6)
            int[][] r6 = (int[][]) r6
            r9 = r8
        L1c:
            if (r9 >= r1) goto Lb3
            int r10 = r9 << 3
            if (r10 <= r3) goto L23
            r10 = r3
        L23:
            r11 = r8
        L24:
            if (r11 >= r0) goto Laa
            int r12 = r11 << 3
            if (r12 <= r4) goto L2b
            r12 = r4
        L2b:
            int r13 = r10 * r20
            int r13 = r13 + r12
            r12 = 255(0xff, float:3.57E-43)
            r14 = r8
            r15 = r14
            r16 = r15
            r8 = r12
        L35:
            if (r14 >= r2) goto L73
            r7 = r16
            r5 = 0
        L3a:
            if (r5 >= r2) goto L4d
            int r16 = r13 + r5
            r2 = r17[r16]
            r2 = r2 & r12
            int r15 = r15 + r2
            if (r2 >= r8) goto L45
            r8 = r2
        L45:
            if (r2 <= r7) goto L48
            r7 = r2
        L48:
            int r5 = r5 + 1
            r2 = 8
            goto L3a
        L4d:
            int r2 = r7 - r8
            r5 = 24
            if (r2 <= r5) goto L69
        L53:
            int r14 = r14 + 1
            int r13 = r13 + r20
            r2 = 8
            if (r14 >= r2) goto L69
            r5 = 0
        L5c:
            if (r5 >= r2) goto L53
            int r16 = r13 + r5
            r2 = r17[r16]
            r2 = r2 & r12
            int r15 = r15 + r2
            int r5 = r5 + 1
            r2 = 8
            goto L5c
        L69:
            r2 = 1
            int r14 = r14 + r2
            int r13 = r13 + r20
            r16 = r7
            r7 = r2
            r2 = 8
            goto L35
        L73:
            r2 = r7
            int r5 = r15 >> 6
            int r7 = r16 - r8
            r12 = 24
            if (r7 > r12) goto L9c
            int r5 = r8 / 2
            if (r9 <= 0) goto L9c
            if (r11 <= 0) goto L9c
            int r7 = r9 + (-1)
            r12 = r6[r7]
            r12 = r12[r11]
            r13 = r6[r9]
            int r14 = r11 + (-1)
            r13 = r13[r14]
            r15 = 2
            int r13 = r13 * r15
            int r12 = r12 + r13
            r7 = r6[r7]
            r7 = r7[r14]
            int r12 = r12 + r7
            int r7 = r12 / 4
            if (r8 >= r7) goto L9d
            r5 = r7
            goto L9d
        L9c:
            r15 = 2
        L9d:
            r7 = r6[r9]
            r7[r11] = r5
            int r11 = r11 + 1
            r7 = r2
            r5 = r15
            r2 = 8
            r8 = 0
            goto L24
        Laa:
            r15 = r5
            r2 = r7
            int r9 = r9 + 1
            r2 = 8
            r8 = 0
            goto L1c
        Lb3:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.i.i(byte[], int, int, int, int):int[][]");
    }

    private static void j(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, b bVar) {
        int i5 = i4 - 8;
        int i6 = i3 - 8;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = i7 << 3;
            int i9 = i8 > i5 ? i5 : i8;
            int iK = k(i7, 2, i2 - 3);
            for (int i10 = 0; i10 < i; i10++) {
                int i11 = i10 << 3;
                int i12 = i11 > i6 ? i6 : i11;
                int iK2 = k(i10, 2, i - 3);
                int i13 = 0;
                for (int i14 = -2; i14 <= 2; i14++) {
                    int[] iArr2 = iArr[iK + i14];
                    i13 += iArr2[iK2 - 2] + iArr2[iK2 - 1] + iArr2[iK2] + iArr2[iK2 + 1] + iArr2[iK2 + 2];
                }
                l(bArr, i12, i9, i13 / 25, i3, bVar);
            }
        }
    }

    private static int k(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    private static void l(byte[] bArr, int i, int i2, int i3, int i4, b bVar) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bVar.m(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    @Override // com.google.zxing.common.g, com.google.zxing.a
    public com.google.zxing.a a(com.google.zxing.d dVar) {
        return new i(dVar);
    }

    @Override // com.google.zxing.common.g, com.google.zxing.a
    public b b() throws NotFoundException {
        b bVar = this.f4216e;
        if (bVar != null) {
            return bVar;
        }
        com.google.zxing.d dVarE = e();
        int iD = dVarE.d();
        int iA = dVarE.a();
        if (iD < 40 || iA < 40) {
            this.f4216e = super.b();
        } else {
            byte[] bArrB = dVarE.b();
            int i = iD >> 3;
            if ((iD & 7) != 0) {
                i++;
            }
            int i2 = i;
            int i3 = iA >> 3;
            if ((iA & 7) != 0) {
                i3++;
            }
            int i4 = i3;
            int[][] iArrI = i(bArrB, i2, i4, iD, iA);
            b bVar2 = new b(iD, iA);
            j(bArrB, i2, i4, iD, iA, iArrI, bVar2);
            this.f4216e = bVar2;
        }
        return this.f4216e;
    }
}
