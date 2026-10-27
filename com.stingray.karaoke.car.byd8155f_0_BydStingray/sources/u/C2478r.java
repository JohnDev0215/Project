package u;

import n7.AbstractC1996k;

/* JADX INFO: renamed from: u.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2478r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f22623b;
    public Object[] c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22625f;

    public final int a(int i7) {
        int i10 = this.d;
        int i11 = i7 & i10;
        int i12 = 0;
        while (true) {
            long[] jArr = this.f22622a;
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

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0062, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0064, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(long r14) {
        /*
            r13 = this;
            int r0 = java.lang.Long.hashCode(r14)
            r1 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r0 = r0 * r1
            int r1 = r0 << 16
            r0 = r0 ^ r1
            r1 = r0 & 127(0x7f, float:1.78E-43)
            int r2 = r13.d
            int r0 = r0 >>> 7
            r0 = r0 & r2
            r3 = 0
        L13:
            long[] r4 = r13.f22622a
            int r5 = r0 >> 3
            r6 = r0 & 7
            int r6 = r6 << 3
            r7 = r4[r5]
            long r7 = r7 >>> r6
            int r5 = r5 + 1
            r4 = r4[r5]
            int r9 = 64 - r6
            long r4 = r4 << r9
            long r9 = (long) r6
            long r9 = -r9
            r6 = 63
            long r9 = r9 >> r6
            long r4 = r4 & r9
            long r4 = r4 | r7
            long r6 = (long) r1
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L3e:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L5b
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r0
            r10 = r10 & r2
            long[] r11 = r13.f22623b
            r11 = r11[r10]
            int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r11 != 0) goto L55
            goto L65
        L55:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L3e
        L5b:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L6e
            r10 = -1
        L65:
            if (r10 < 0) goto L6c
            java.lang.Object[] r13 = r13.c
            r13 = r13[r10]
            goto L6d
        L6c:
            r13 = 0
        L6d:
            return r13
        L6e:
            int r3 = r3 + 8
            int r0 = r0 + r3
            r0 = r0 & r2
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2478r.b(long):java.lang.Object");
    }

    public final void c(int i7) {
        long[] jArr;
        int iMax = i7 > 0 ? Math.max(7, AbstractC2456D.e(i7)) : 0;
        this.d = iMax;
        if (iMax == 0) {
            jArr = AbstractC2456D.f22584a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            AbstractC1996k.K(jArr);
        }
        this.f22622a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22625f = AbstractC2456D.c(this.d) - this.f22624e;
        this.f22623b = new long[iMax];
        this.c = new Object[iMax];
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0066, code lost:
    
        r7 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x006f, code lost:
    
        if (((((~r8) << 6) & r8) & (-9187201950435737472L)) == 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0071, code lost:
    
        r1 = a(r3);
        r5 = 128;
        r8 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x007b, code lost:
    
        if (r37.f22625f != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x008d, code lost:
    
        if (((r37.f22622a[r1 >> 3] >> ((r1 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008f, code lost:
    
        r26 = r10;
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0095, code lost:
    
        r1 = r37.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0097, code lost:
    
        if (r1 <= 8) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0099, code lost:
    
        r23 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ab, code lost:
    
        if (java.lang.Long.compareUnsigned(((long) r37.f22624e) * 32, ((long) r1) * 25) > 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00ad, code lost:
    
        r1 = r37.f22622a;
        r2 = r37.d;
        r3 = r37.f22623b;
        r4 = r37.c;
        u.AbstractC2456D.a(r1, r2);
        r12 = -1;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00ba, code lost:
    
        if (r15 == r2) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00bc, code lost:
    
        r18 = r15 >> 3;
        r26 = (r15 & 7) << 3;
        r24 = (r1[r18] >> r26) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ca, code lost:
    
        if (r24 != r5) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00cc, code lost:
    
        r36 = r15;
        r15 = r15 + 1;
        r12 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00d6, code lost:
    
        if (r24 == 254) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d8, code lost:
    
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00db, code lost:
    
        r24 = java.lang.Long.hashCode(r3[r15]) * (-862048943);
        r24 = r24 ^ (r24 << 16);
        r14 = r24 >>> 7;
        r27 = a(r14);
        r14 = r14 & r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0102, code lost:
    
        if ((((r27 - r14) & r2) / 8) != (((r15 - r14) & r2) / r7)) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0104, code lost:
    
        r1[r18] = (((long) (r24 & 127)) << r26) | (r1[r18] & (~(255 << r26)));
        r1[r1.length - 1] = (r1[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r15 + 1;
        r5 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0126, code lost:
    
        r6 = r12;
        r5 = r27 >> 3;
        r13 = r1[r5];
        r19 = (r27 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0137, code lost:
    
        if (((r13 >> r19) & 255) != 128) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0139, code lost:
    
        r1[r5] = (r13 & (~(255 << r19))) | (((long) (r24 & 127)) << r19);
        r1[r18] = (r1[r18] & (~(255 << r26))) | (128 << r26);
        r3[r27] = r3[r15];
        r3[r15] = 0;
        r4[r27] = r4[r15];
        r4[r15] = null;
        r12 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0166, code lost:
    
        r7 = r15;
        r1[r5] = (((long) (r24 & 127)) << r19) | (r13 & (~(255 << r19)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0177, code lost:
    
        if (r6 != (-1)) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0179, code lost:
    
        r12 = u.AbstractC2456D.b(r1, r7 + 1, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0180, code lost:
    
        r12 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0181, code lost:
    
        r3[r12] = r3[r27];
        r3[r27] = r3[r7];
        r3[r7] = r3[r12];
        r4[r12] = r4[r27];
        r4[r27] = r4[r7];
        r4[r7] = r4[r12];
        r15 = r7 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x019b, code lost:
    
        r1[r1.length - 1] = (r1[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r15 + 1;
        r5 = 128;
        r7 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x01b0, code lost:
    
        r19 = 0;
        r37.f22625f = u.AbstractC2456D.c(r37.d) - r37.f22624e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01bd, code lost:
    
        r26 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01c3, code lost:
    
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01c6, code lost:
    
        r23 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01c9, code lost:
    
        r1 = u.AbstractC2456D.d(r37.d);
        r2 = r37.f22622a;
        r3 = r37.f22623b;
        r4 = r37.c;
        r5 = r37.d;
        c(r1);
        r1 = r37.f22622a;
        r6 = r37.f22623b;
        r7 = r37.c;
        r12 = r37.d;
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01e4, code lost:
    
        if (r13 >= r5) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01f5, code lost:
    
        if (((r2[r13 >> 3] >> ((r13 & 7) << 3)) & r8) >= 128) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01f7, code lost:
    
        r14 = r3[r13];
        r16 = java.lang.Long.hashCode(r14) * (-862048943);
        r16 = r16 ^ (r16 << 16);
        r8 = a(r16 >>> 7);
        r9 = r16 & 127;
        r16 = r2;
        r18 = r3;
        r2 = r9;
        r9 = r8 >> 3;
        r22 = (r8 & 7) << 3;
        r26 = r10;
        r2 = (r2 << r22) | (r1[r9] & (~(255 << r22)));
        r1[r9] = r2;
        r1[(((r8 - 7) & r12) + (r12 & 7)) >> 3] = r2;
        r6[r8] = r14;
        r7[r8] = r4[r13];
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x023a, code lost:
    
        r16 = r2;
        r18 = r3;
        r26 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0243, code lost:
    
        r13 = r13 + 1;
        r2 = r16;
        r3 = r18;
        r10 = r26;
        r8 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x024e, code lost:
    
        r1 = a(r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0252, code lost:
    
        r16 = r1;
        r2 = 1;
        r37.f22624e++;
        r1 = r37.f22625f;
        r3 = r37.f22622a;
        r4 = r16 >> 3;
        r5 = r3[r4];
        r7 = (r16 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x026f, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0272, code lost:
    
        r2 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0274, code lost:
    
        r37.f22625f = r1 - r2;
        r1 = r37.d;
        r5 = (r5 & (~(255 << r7))) | (r26 << r7);
        r3[r4] = r5;
        r3[(((r16 - 7) & r1) + (r1 & 7)) >> 3] = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(long r38, u.C2482v r40) {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2478r.d(long, u.v):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d1, code lost:
    
        r26 = r3;
        r7 = -9187201950435737472L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
    
        if (((r5 & ((~r5) << 6)) & (-9187201950435737472L)) == 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e1, code lost:
    
        r9 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r28) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2478r.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        long[] jArr = this.f22623b;
        Object[] objArr = this.c;
        long[] jArr2 = this.f22622a;
        int length = jArr2.length - 2;
        int i7 = 0;
        if (length >= 0) {
            int i10 = 0;
            int iHashCode = 0;
            while (true) {
                long j3 = jArr2[i10];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    for (int i12 = 0; i12 < i11; i12++) {
                        if ((255 & j3) < 128) {
                            int i13 = (i10 << 3) + i12;
                            long j10 = jArr[i13];
                            Object obj = objArr[i13];
                            iHashCode += (obj != null ? obj.hashCode() : 0) ^ Long.hashCode(j10);
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

    public final String toString() {
        int i7;
        int i10;
        if (this.f22624e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        long[] jArr = this.f22623b;
        Object[] objArr = this.c;
        long[] jArr2 = this.f22622a;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i11 = 0;
            int i12 = 0;
            while (true) {
                long j3 = jArr2[i11];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i13 = 8 - ((~(i11 - length)) >>> 31);
                    int i14 = 0;
                    while (i14 < i13) {
                        if ((255 & j3) < 128) {
                            int i15 = (i11 << 3) + i14;
                            i10 = i11;
                            long j10 = jArr[i15];
                            Object obj = objArr[i15];
                            sb.append(j10);
                            sb.append("=");
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            i12++;
                            if (i12 < this.f22624e) {
                                sb.append(", ");
                            }
                        } else {
                            i10 = i11;
                        }
                        j3 >>= 8;
                        i14++;
                        i11 = i10;
                    }
                    int i16 = i11;
                    if (i13 != 8) {
                        break;
                    }
                    i7 = i16;
                } else {
                    i7 = i11;
                }
                if (i7 == length) {
                    break;
                }
                i11 = i7 + 1;
            }
        }
        sb.append('}');
        String string = sb.toString();
        A7.m.e(string, "s.append('}').toString()");
        return string;
    }
}
