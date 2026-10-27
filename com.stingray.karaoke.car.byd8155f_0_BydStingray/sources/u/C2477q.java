package u;

import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2477q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f22620b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22621e;

    public C2477q(int i7) {
        this.f22619a = AbstractC2456D.f22584a;
        this.f22620b = AbstractC2470j.f22603a;
        if (i7 >= 0) {
            e(AbstractC2456D.f(i7));
        } else {
            AbstractC2530a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0077, code lost:
    
        if (((((~r8) << 6) & r8) & (-9187201950435737472L)) == 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0079, code lost:
    
        r3 = d(r20);
        r7 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0085, code lost:
    
        if (r37.f22621e != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0099, code lost:
    
        if (((r37.f22619a[r3 >> 3] >> ((r3 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x009b, code lost:
    
        r35 = r2;
        r22 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a1, code lost:
    
        r3 = r37.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a3, code lost:
    
        if (r3 <= 8) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a5, code lost:
    
        r22 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b7, code lost:
    
        if (java.lang.Long.compareUnsigned(((long) r37.d) * 32, ((long) r3) * 25) > 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b9, code lost:
    
        r3 = r37.f22619a;
        r5 = r37.c;
        r6 = r37.f22620b;
        u.AbstractC2456D.a(r3, r5);
        r9 = 0;
        r14 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c4, code lost:
    
        if (r9 == r5) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c6, code lost:
    
        r17 = r9 >> 3;
        r18 = (r9 & 7) << 3;
        r24 = (r3[r17] >> r18) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00d4, code lost:
    
        if (r24 != r7) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d6, code lost:
    
        r14 = r9;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00e0, code lost:
    
        if (r24 == 254) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00e2, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00e5, code lost:
    
        r24 = java.lang.Integer.hashCode(r6[r9]) * (-862048943);
        r11 = r14;
        r21 = r24 ^ (r24 << 16);
        r14 = r21 >>> 7;
        r26 = d(r14);
        r14 = r14 & r5;
        r25 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0112, code lost:
    
        if ((((r26 - r14) & r5) / 8) != (((r9 - r14) & r5) / 8)) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0114, code lost:
    
        r3[r17] = (r3[r17] & (~(255 << r18))) | (((long) (r21 & 127)) << r18);
        r3[r3.length - r15] = (r3[0] & 72057594037927935L) | Long.MIN_VALUE;
        r9 = r25 + 1;
        r14 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0131, code lost:
    
        r7 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0134, code lost:
    
        r7 = r26 >> 3;
        r8 = r3[r7];
        r10 = (r26 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0144, code lost:
    
        if (((r8 >> r10) & 255) != 128) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0146, code lost:
    
        r35 = r2;
        r3[r7] = ((~(255 << r10)) & r8) | (((long) (r21 & 127)) << r10);
        r3[r17] = (r3[r17] & (~(255 << r18))) | (128 << r18);
        r6[r26] = r6[r25];
        r6[r25] = 0;
        r9 = r25;
        r14 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x016d, code lost:
    
        r35 = r2;
        r3[r7] = (((long) (r21 & 127)) << r10) | (r8 & (~(255 << r10)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x017b, code lost:
    
        if (r11 != (-1)) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x017d, code lost:
    
        r14 = u.AbstractC2456D.b(r3, r25 + 1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0184, code lost:
    
        r14 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0185, code lost:
    
        r6[r14] = r6[r26];
        r6[r26] = r6[r25];
        r6[r25] = r6[r14];
        r9 = r25 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0193, code lost:
    
        r3[r3.length - 1] = (r3[0] & 72057594037927935L) | Long.MIN_VALUE;
        r9 = r9 + 1;
        r15 = 1;
        r2 = r35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01a6, code lost:
    
        r35 = r2;
        r37.f22621e = u.AbstractC2456D.c(r37.c) - r37.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01b6, code lost:
    
        r35 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01ba, code lost:
    
        r35 = r2;
        r22 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01bf, code lost:
    
        r1 = u.AbstractC2456D.d(r37.c);
        r2 = r37.f22619a;
        r3 = r37.f22620b;
        r5 = r37.c;
        e(r1);
        r1 = r37.f22619a;
        r6 = r37.f22620b;
        r7 = r37.c;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01d5, code lost:
    
        if (r9 >= r5) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01e5, code lost:
    
        if (((r2[r9 >> 3] >> ((r9 & 7) << 3)) & 255) >= 128) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01e7, code lost:
    
        r10 = r3[r9];
        r11 = java.lang.Integer.hashCode(r10) * (-862048943);
        r11 = r11 ^ (r11 << 16);
        r15 = d(r11 >>> 7);
        r17 = r9;
        r8 = r11 & 127;
        r11 = r15 >> 3;
        r18 = (r15 & 7) << 3;
        r8 = (r8 << r18) | (r1[r11] & (~(255 << r18)));
        r1[r11] = r8;
        r1[(((r15 - 7) & r7) + (r7 & 7)) >> 3] = r8;
        r6[r15] = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0221, code lost:
    
        r17 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0223, code lost:
    
        r9 = r17 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0227, code lost:
    
        r18 = d(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x022e, code lost:
    
        r18 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0230, code lost:
    
        r2 = true;
        r37.d++;
        r1 = r37.f22621e;
        r3 = r37.f22619a;
        r4 = r18 >> 3;
        r5 = r3[r4];
        r7 = (r18 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0249, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x024b, code lost:
    
        r8 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x024d, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x024e, code lost:
    
        r37.f22621e = r1 - r8;
        r1 = r37.c;
        r5 = (r5 & (~(255 << r7))) | (r22 << r7);
        r3[r4] = r5;
        r3[(((r18 - 7) & r1) + (r1 & 7)) >> 3] = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(int r38) {
        /*
            Method dump skipped, instruction units count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2477q.a(int):boolean");
    }

    public final void b() {
        this.d = 0;
        long[] jArr = this.f22619a;
        if (jArr != AbstractC2456D.f22584a) {
            AbstractC1996k.K(jArr);
            long[] jArr2 = this.f22619a;
            int i7 = this.c;
            int i10 = i7 >> 3;
            long j3 = 255 << ((i7 & 7) << 3);
            jArr2[i10] = (jArr2[i10] & (~j3)) | j3;
        }
        this.f22621e = AbstractC2456D.c(this.c) - this.d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0066, code lost:
    
        if (((r6 & ((~r6) << 6)) & (-9187201950435737472L)) == 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0068, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c(int r19) {
        /*
            r18 = this;
            r0 = r18
            int r1 = java.lang.Integer.hashCode(r19)
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r0.c
            int r1 = r1 >>> 7
            r1 = r1 & r3
            r4 = 0
            r5 = r4
        L16:
            long[] r6 = r0.f22619a
            int r7 = r1 >> 3
            r8 = r1 & 7
            int r8 = r8 << 3
            r9 = r6[r7]
            long r9 = r9 >>> r8
            r11 = 1
            int r7 = r7 + r11
            r6 = r6[r7]
            int r12 = 64 - r8
            long r6 = r6 << r12
            long r12 = (long) r8
            long r12 = -r12
            r8 = 63
            long r12 = r12 >> r8
            long r6 = r6 & r12
            long r6 = r6 | r9
            long r8 = (long) r2
            r12 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r8 = r8 * r12
            long r8 = r8 ^ r6
            long r12 = r8 - r12
            long r8 = ~r8
            long r8 = r8 & r12
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r12
        L41:
            r14 = 0
            int r10 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r10 == 0) goto L5f
            int r10 = java.lang.Long.numberOfTrailingZeros(r8)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            int[] r14 = r0.f22620b
            r14 = r14[r10]
            r15 = r19
            if (r14 != r15) goto L58
            goto L69
        L58:
            r16 = 1
            long r16 = r8 - r16
            long r8 = r8 & r16
            goto L41
        L5f:
            long r8 = ~r6
            r10 = 6
            long r8 = r8 << r10
            long r6 = r6 & r8
            long r6 = r6 & r12
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L6d
            r10 = -1
        L69:
            if (r10 < 0) goto L6c
            r4 = r11
        L6c:
            return r4
        L6d:
            int r5 = r5 + 8
            int r1 = r1 + r5
            r1 = r1 & r3
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2477q.c(int):boolean");
    }

    public final int d(int i7) {
        int i10 = this.c;
        int i11 = i7 & i10;
        int i12 = 0;
        while (true) {
            long[] jArr = this.f22619a;
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

    public final void e(int i7) {
        long[] jArr;
        int iMax = i7 > 0 ? Math.max(7, AbstractC2456D.e(i7)) : 0;
        this.c = iMax;
        if (iMax == 0) {
            jArr = AbstractC2456D.f22584a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            AbstractC1996k.K(jArr);
        }
        this.f22619a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22621e = AbstractC2456D.c(this.c) - this.d;
        this.f22620b = new int[iMax];
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r15) {
        /*
            r14 = this;
            r0 = 1
            if (r15 != r14) goto L4
            return r0
        L4:
            boolean r1 = r15 instanceof u.C2477q
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            u.q r15 = (u.C2477q) r15
            int r1 = r15.d
            int r3 = r14.d
            if (r1 == r3) goto L13
            return r2
        L13:
            int[] r1 = r14.f22620b
            long[] r14 = r14.f22619a
            int r3 = r14.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L59
            r4 = r2
        L1d:
            r5 = r14[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L54
            int r7 = r4 - r3
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r2
        L37:
            if (r9 >= r7) goto L52
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L4e
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]
            boolean r10 = r15.c(r10)
            if (r10 != 0) goto L4e
            return r2
        L4e:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L37
        L52:
            if (r7 != r8) goto L59
        L54:
            if (r4 == r3) goto L59
            int r4 = r4 + 1
            goto L1d
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2477q.equals(java.lang.Object):boolean");
    }

    public final void f(int i7) {
        this.d--;
        long[] jArr = this.f22619a;
        int i10 = this.c;
        int i11 = i7 >> 3;
        int i12 = (i7 & 7) << 3;
        long j3 = (jArr[i11] & (~(255 << i12))) | (254 << i12);
        jArr[i11] = j3;
        jArr[(((i7 - 7) & i10) + (i10 & 7)) >> 3] = j3;
    }

    public final int hashCode() {
        int[] iArr = this.f22620b;
        long[] jArr = this.f22619a;
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
                            iHashCode = Integer.hashCode(iArr[(i10 << 3) + i12]) + iHashCode;
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

    /* JADX WARN: Removed duplicated region for block: B:19:0x005b A[PHI: r5
  0x005b: PHI (r5v2 int) = (r5v1 int), (r5v3 int) binds: [B:6:0x0024, B:18:0x0059] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r15 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "["
            r0.append(r1)
            int[] r1 = r15.f22620b
            long[] r15 = r15.f22619a
            int r2 = r15.length
            int r2 = r2 + (-2)
            if (r2 < 0) goto L60
            r3 = 0
            r4 = r3
            r5 = r4
        L16:
            r6 = r15[r4]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L5b
            int r8 = r4 - r2
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r3
        L30:
            if (r10 >= r8) goto L59
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L55
            int r11 = r4 << 3
            int r11 = r11 + r10
            r11 = r1[r11]
            r12 = -1
            if (r5 != r12) goto L49
            java.lang.String r15 = "..."
            r0.append(r15)
            goto L65
        L49:
            if (r5 == 0) goto L50
            java.lang.String r12 = ", "
            r0.append(r12)
        L50:
            r0.append(r11)
            int r5 = r5 + 1
        L55:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L30
        L59:
            if (r8 != r9) goto L60
        L5b:
            if (r4 == r2) goto L60
            int r4 = r4 + 1
            goto L16
        L60:
            java.lang.String r15 = "]"
            r0.append(r15)
        L65:
            java.lang.String r15 = r0.toString()
            java.lang.String r0 = "StringBuilder().apply(builderAction).toString()"
            A7.m.e(r15, r0)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2477q.toString():java.lang.String");
    }

    public /* synthetic */ C2477q() {
        this(6);
    }
}
