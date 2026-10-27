package u;

import java.util.NoSuchElementException;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: u.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2474n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22609a = AbstractC2456D.f22584a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f22610b;
    public int[] c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22611e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22612f;

    public C2474n() {
        int[] iArr = AbstractC2470j.f22603a;
        this.f22610b = iArr;
        this.c = iArr;
        f(AbstractC2456D.f(6));
    }

    public final void a() {
        this.f22611e = 0;
        long[] jArr = this.f22609a;
        if (jArr != AbstractC2456D.f22584a) {
            AbstractC1996k.K(jArr);
            long[] jArr2 = this.f22609a;
            int i7 = this.d;
            int i10 = i7 >> 3;
            long j3 = 255 << ((i7 & 7) << 3);
            jArr2[i10] = (jArr2[i10] & (~j3)) | j3;
        }
        this.f22612f = AbstractC2456D.c(this.d) - this.f22611e;
    }

    public final int b(int i7) {
        int i10 = this.d;
        int i11 = i7 & i10;
        int i12 = 0;
        while (true) {
            long[] jArr = this.f22609a;
            int i13 = i11 >> 3;
            int i14 = (i11 & 7) << 3;
            long j3 = ((jArr[i13 + 1] << (64 - i14)) & ((-i14) >> 63)) | (jArr[i13] >>> i14);
            long j10 = j3 & ((~j3) << 7) & (-9187201950435737472L);
            if (j10 != 0) {
                return (i11 + (Long.numberOfTrailingZeros(j10) >> 3)) & i10;
            }
            i12 += 8;
            i11 = (i11 + i12) & i10;
        }
    }

    public final int c(int i7) {
        int iHashCode = Integer.hashCode(i7) * (-862048943);
        int i10 = iHashCode ^ (iHashCode << 16);
        int i11 = i10 & 127;
        int i12 = this.d;
        int i13 = (i10 >>> 7) & i12;
        int i14 = 0;
        while (true) {
            long[] jArr = this.f22609a;
            int i15 = i13 >> 3;
            int i16 = (i13 & 7) << 3;
            long j3 = ((jArr[i15 + 1] << (64 - i16)) & ((-i16) >> 63)) | (jArr[i15] >>> i16);
            long j10 = (((long) i11) * 72340172838076673L) ^ j3;
            for (long j11 = (~j10) & (j10 - 72340172838076673L) & (-9187201950435737472L); j11 != 0; j11 &= j11 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j11) >> 3) + i13) & i12;
                if (this.f22610b[iNumberOfTrailingZeros] == i7) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j3 & ((~j3) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i14 += 8;
            i13 = (i13 + i14) & i12;
        }
    }

    public final int d(int i7) {
        int iC = c(i7);
        if (iC >= 0) {
            return this.c[iC];
        }
        throw new NoSuchElementException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Cannot find value for key "));
    }

    public final int e(int i7) {
        int iC = c(i7);
        if (iC >= 0) {
            return this.c[iC];
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 1
            if (r1 != r0) goto L8
            return r2
        L8:
            boolean r3 = r1 instanceof u.C2474n
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            u.n r1 = (u.C2474n) r1
            int r3 = r1.f22611e
            int r5 = r0.f22611e
            if (r3 == r5) goto L17
            return r4
        L17:
            int[] r3 = r0.f22610b
            int[] r5 = r0.c
            long[] r0 = r0.f22609a
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L61
            r7 = r4
        L23:
            r8 = r0[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L5c
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L3d:
            if (r12 >= r10) goto L5a
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L56
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r5[r13]
            int r14 = r1.d(r14)
            if (r13 == r14) goto L56
            return r4
        L56:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L3d
        L5a:
            if (r10 != r11) goto L61
        L5c:
            if (r7 == r6) goto L61
            int r7 = r7 + 1
            goto L23
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2474n.equals(java.lang.Object):boolean");
    }

    public final void f(int i7) {
        long[] jArr;
        int iMax = i7 > 0 ? Math.max(7, AbstractC2456D.e(i7)) : 0;
        this.d = iMax;
        if (iMax == 0) {
            jArr = AbstractC2456D.f22584a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            AbstractC1996k.K(jArr);
        }
        this.f22609a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22612f = AbstractC2456D.c(this.d) - this.f22611e;
        this.f22610b = new int[iMax];
        this.c = new int[iMax];
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0072, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0074, code lost:
    
        r2 = r0.b(r4);
        r6 = 128;
        r11 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x007e, code lost:
    
        if (r0.f22612f != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0090, code lost:
    
        if (((r0.f22609a[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0092, code lost:
    
        r1 = r0;
        r21 = r9;
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0099, code lost:
    
        r2 = r0.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x009b, code lost:
    
        if (r2 <= 8) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x009d, code lost:
    
        r21 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00af, code lost:
    
        if (java.lang.Long.compareUnsigned(((long) r0.f22611e) * 32, ((long) r2) * 25) > 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b1, code lost:
    
        r2 = r0.f22609a;
        r5 = r0.d;
        r8 = r0.f22610b;
        r9 = r0.c;
        u.AbstractC2456D.a(r2, r5);
        r13 = -1;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00be, code lost:
    
        if (r15 == r5) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c0, code lost:
    
        r18 = r15 >> 3;
        r25 = (r15 & 7) << 3;
        r23 = (r2[r18] >> r25) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ce, code lost:
    
        if (r23 != r6) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d0, code lost:
    
        r36 = r15;
        r15 = r15 + 1;
        r13 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00da, code lost:
    
        if (r23 == 254) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00dc, code lost:
    
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00df, code lost:
    
        r23 = java.lang.Integer.hashCode(r8[r15]) * r3;
        r23 = r23 ^ (r23 << 16);
        r10 = r23 >>> 7;
        r26 = r0.b(r10);
        r10 = r10 & r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0106, code lost:
    
        if ((((r26 - r10) & r5) / 8) != (((r15 - r10) & r5) / 8)) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0108, code lost:
    
        r2[r18] = (r2[r18] & (~(255 << r25))) | (((long) (r23 & 127)) << r25);
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r15 + 1;
        r13 = r13;
        r3 = -862048943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x012f, code lost:
    
        r6 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0132, code lost:
    
        r14 = r13;
        r6 = r15;
        r3 = r26 >> 3;
        r19 = r2[r3];
        r7 = (r26 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0144, code lost:
    
        if (((r19 >> r7) & 255) != 128) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0146, code lost:
    
        r2[r3] = (r19 & (~(255 << r7))) | (((long) (r23 & 127)) << r7);
        r2[r18] = (r2[r18] & (~(255 << r25))) | (128 << r25);
        r8[r26] = r8[r6];
        r8[r6] = 0;
        r9[r26] = r9[r6];
        r9[r6] = 0;
        r1 = r6;
        r18 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0172, code lost:
    
        r18 = r9;
        r2[r3] = (((long) (r23 & 127)) << r7) | (r19 & (~(255 << r7)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0181, code lost:
    
        if (r14 != (-1)) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0183, code lost:
    
        r13 = u.AbstractC2456D.b(r2, r6 + 1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x018a, code lost:
    
        r13 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x018b, code lost:
    
        r8[r13] = r8[r26];
        r8[r26] = r8[r6];
        r8[r6] = r8[r13];
        r18[r13] = r18[r26];
        r18[r26] = r18[r6];
        r18[r6] = r18[r13];
        r1 = r6 - 1;
        r6 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x01a6, code lost:
    
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r1 + 1;
        r3 = -862048943;
        r0 = r37;
        r13 = r6;
        r9 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01c1, code lost:
    
        r1 = r0;
        r19 = 0;
        r1.f22612f = u.AbstractC2456D.c(r1.d) - r1.f22611e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01d1, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01d2, code lost:
    
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01d5, code lost:
    
        r1 = r0;
        r21 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01d9, code lost:
    
        r0 = u.AbstractC2456D.d(r1.d);
        r2 = r1.f22609a;
        r3 = r1.f22610b;
        r5 = r1.c;
        r6 = r1.d;
        r1.f(r0);
        r0 = r1.f22609a;
        r7 = r1.f22610b;
        r8 = r1.c;
        r9 = r1.d;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01f4, code lost:
    
        if (r10 >= r6) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0205, code lost:
    
        if (((r2[r10 >> 3] >> ((r10 & 7) << 3)) & r11) >= 128) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0207, code lost:
    
        r14 = r3[r10];
        r15 = java.lang.Integer.hashCode(r14) * (-862048943);
        r15 = r15 ^ (r15 << 16);
        r13 = r1.b(r15 >>> 7);
        r11 = r15 & 127;
        r15 = r13 >> 3;
        r16 = (r13 & 7) << 3;
        r18 = r2;
        r27 = r3;
        r2 = (r0[r15] & (~(255 << r16))) | (r11 << r16);
        r0[r15] = r2;
        r0[(((r13 - 7) & r9) + (r9 & 7)) >> 3] = r2;
        r7[r13] = r14;
        r8[r13] = r5[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0248, code lost:
    
        r18 = r2;
        r27 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x024f, code lost:
    
        r10 = r10 + 1;
        r2 = r18;
        r3 = r27;
        r11 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0258, code lost:
    
        r2 = r1.b(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x025c, code lost:
    
        r3 = 1;
        r1.f22611e++;
        r0 = r1.f22612f;
        r4 = r1.f22609a;
        r5 = r2 >> 3;
        r6 = r4[r5];
        r8 = (r2 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0277, code lost:
    
        if (((r6 >> r8) & 255) != 128) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x027a, code lost:
    
        r3 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x027c, code lost:
    
        r1.f22612f = r0 - r3;
        r0 = r1.d;
        r6 = (r6 & (~(255 << r8))) | (r21 << r8);
        r4[r5] = r6;
        r4[(((r2 - 7) & r0) + (r0 & 7)) >> 3] = r6;
        r0 = ~r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2474n.g(int, int):void");
    }

    public final int hashCode() {
        int[] iArr = this.f22610b;
        int[] iArr2 = this.c;
        long[] jArr = this.f22609a;
        int length = jArr.length - 2;
        int i7 = 0;
        if (length >= 0) {
            int i10 = 0;
            int iHashCode = 0;
            while (true) {
                long j3 = jArr[i10];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    for (int i12 = 0; i12 < i11; i12++) {
                        if ((255 & j3) < 128) {
                            int i13 = (i10 << 3) + i12;
                            int i14 = iArr[i13];
                            iHashCode += Integer.hashCode(iArr2[i13]) ^ Integer.hashCode(i14);
                        }
                        j3 >>= 8;
                    }
                    if (i11 != 8) {
                        return iHashCode;
                    }
                }
                if (i10 == length) {
                    i7 = iHashCode;
                    break;
                }
                i10++;
            }
        }
        return i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0066 A[PHI: r8
  0x0066: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002c, B:19:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.f22611e
            if (r1 != 0) goto L9
            java.lang.String r0 = "{}"
            return r0
        L9:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "{"
            r1.<init>(r2)
            int[] r2 = r0.f22610b
            int[] r3 = r0.c
            long[] r4 = r0.f22609a
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L6b
            r6 = 0
            r7 = r6
            r8 = r7
        L1e:
            r9 = r4[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L38:
            if (r13 >= r11) goto L64
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L60
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0.f22611e
            if (r8 >= r14) goto L60
            java.lang.String r14 = ", "
            r1.append(r14)
        L60:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L38
        L64:
            if (r11 != r12) goto L6b
        L66:
            if (r7 == r5) goto L6b
            int r7 = r7 + 1
            goto L1e
        L6b:
            r0 = 125(0x7d, float:1.75E-43)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "s.append('}').toString()"
            A7.m.e(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2474n.toString():java.lang.String");
    }
}
