package u;

import java.util.NoSuchElementException;
import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2483w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22639a = AbstractC2456D.f22584a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f22640b = AbstractC2530a.c;
    public long[] c = AbstractC2471k.f22604a;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22641e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22642f;

    public C2483w(int i7) {
        if (i7 >= 0) {
            d(AbstractC2456D.f(i7));
        } else {
            AbstractC2530a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final int a(int i7) {
        int i10 = this.d;
        int i11 = i7 & i10;
        int i12 = 0;
        while (true) {
            long[] jArr = this.f22639a;
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

    public final int b(Object obj) {
        int i7 = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i10 = iHashCode ^ (iHashCode << 16);
        int i11 = i10 & 127;
        int i12 = this.d;
        int i13 = i10 >>> 7;
        while (true) {
            int i14 = i13 & i12;
            long[] jArr = this.f22639a;
            int i15 = i14 >> 3;
            int i16 = (i14 & 7) << 3;
            long j3 = ((jArr[i15 + 1] << (64 - i16)) & ((-i16) >> 63)) | (jArr[i15] >>> i16);
            long j10 = (((long) i11) * 72340172838076673L) ^ j3;
            for (long j11 = (~j10) & (j10 - 72340172838076673L) & (-9187201950435737472L); j11 != 0; j11 &= j11 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j11) >> 3) + i14) & i12;
                if (A7.m.a(this.f22640b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j3 & ((~j3) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i7 += 8;
            i13 = i14 + i7;
        }
    }

    public final long c(Object obj) {
        int iB = b(obj);
        if (iB >= 0) {
            return this.c[iB];
        }
        throw new NoSuchElementException("There is no key " + obj + " in the map");
    }

    public final void d(int i7) {
        long[] jArr;
        int iMax = i7 > 0 ? Math.max(7, AbstractC2456D.e(i7)) : 0;
        this.d = iMax;
        if (iMax == 0) {
            jArr = AbstractC2456D.f22584a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            AbstractC1996k.K(jArr);
        }
        this.f22639a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22642f = AbstractC2456D.c(this.d) - this.f22641e;
        this.f22640b = new Object[iMax];
        this.c = new long[iMax];
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0076, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0078, code lost:
    
        r2 = r0.a(r4);
        r6 = 128;
        r11 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0082, code lost:
    
        if (r0.f22642f != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0094, code lost:
    
        if (((r0.f22639a[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0096, code lost:
    
        r1 = r0;
        r23 = r9;
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x009d, code lost:
    
        r2 = r0.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x009f, code lost:
    
        if (r2 <= 8) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a1, code lost:
    
        r23 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b1, code lost:
    
        if (java.lang.Long.compareUnsigned(((long) r0.f22641e) * 32, ((long) r2) * 25) > 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b3, code lost:
    
        r2 = r0.f22639a;
        r5 = r0.d;
        r8 = r0.f22640b;
        r9 = r0.c;
        u.AbstractC2456D.a(r2, r5);
        r13 = -1;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c0, code lost:
    
        if (r15 == r5) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c2, code lost:
    
        r16 = r15 >> 3;
        r27 = (r15 & 7) << 3;
        r25 = (r2[r16] >> r27) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00d0, code lost:
    
        if (r25 != r6) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d2, code lost:
    
        r38 = r15;
        r15 = r15 + 1;
        r13 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00dc, code lost:
    
        if (r25 == 254) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00de, code lost:
    
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00e1, code lost:
    
        r25 = r8[r15];
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e3, code lost:
    
        if (r25 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00e5, code lost:
    
        r25 = r25.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ea, code lost:
    
        r25 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ec, code lost:
    
        r25 = r25 * r3;
        r25 = r25 ^ (r25 << 16);
        r10 = r25 >>> 7;
        r28 = r0.a(r10);
        r10 = r10 & r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x010d, code lost:
    
        if ((((r28 - r10) & r5) / 8) != (((r15 - r10) & r5) / 8)) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x010f, code lost:
    
        r2[r16] = (r2[r16] & (~(255 << r27))) | (((long) (r25 & 127)) << r27);
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r15 + 1;
        r13 = r13;
        r3 = -862048943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0136, code lost:
    
        r6 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0139, code lost:
    
        r14 = r13;
        r6 = r15;
        r3 = r28 >> 3;
        r19 = r2[r3];
        r7 = (r28 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x014b, code lost:
    
        if (((r19 >> r7) & 255) != 128) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x014d, code lost:
    
        r2[r3] = (r19 & (~(255 << r7))) | (((long) (r25 & 127)) << r7);
        r2[r16] = (r2[r16] & (~(255 << r27))) | (128 << r27);
        r8[r28] = r8[r6];
        r8[r6] = null;
        r9[r28] = r9[r6];
        r9[r6] = 0;
        r1 = r6;
        r16 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0179, code lost:
    
        r16 = r9;
        r2[r3] = (((long) (r25 & 127)) << r7) | (r19 & (~(255 << r7)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0188, code lost:
    
        if (r14 != (-1)) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x018a, code lost:
    
        r13 = u.AbstractC2456D.b(r2, r6 + 1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0191, code lost:
    
        r13 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0192, code lost:
    
        r8[r13] = r8[r28];
        r8[r28] = r8[r6];
        r8[r6] = r8[r13];
        r16[r13] = r16[r28];
        r16[r28] = r16[r6];
        r16[r6] = r16[r13];
        r1 = r6 - 1;
        r6 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01ad, code lost:
    
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r1 + 1;
        r3 = -862048943;
        r0 = r39;
        r13 = r6;
        r9 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01c8, code lost:
    
        r1 = r0;
        r19 = 0;
        r1.f22642f = u.AbstractC2456D.c(r1.d) - r1.f22641e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01d8, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01d9, code lost:
    
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01dc, code lost:
    
        r1 = r0;
        r23 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01e0, code lost:
    
        r0 = u.AbstractC2456D.d(r1.d);
        r2 = r1.f22639a;
        r3 = r1.f22640b;
        r5 = r1.c;
        r6 = r1.d;
        r1.d(r0);
        r0 = r1.f22639a;
        r7 = r1.f22640b;
        r8 = r1.c;
        r9 = r1.d;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01fb, code lost:
    
        if (r10 >= r6) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x020c, code lost:
    
        if (((r2[r10 >> 3] >> ((r10 & 7) << 3)) & r11) >= 128) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x020e, code lost:
    
        r14 = r3[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0210, code lost:
    
        if (r14 == null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0212, code lost:
    
        r15 = r14.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x021a, code lost:
    
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x021d, code lost:
    
        r15 = r15 * (-862048943);
        r15 = r15 ^ (r15 << 16);
        r13 = r1.a(r15 >>> 7);
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
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0255, code lost:
    
        r18 = r2;
        r27 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x025c, code lost:
    
        r10 = r10 + 1;
        r2 = r18;
        r3 = r27;
        r11 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0265, code lost:
    
        r2 = r1.a(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0269, code lost:
    
        r3 = 1;
        r1.f22641e++;
        r0 = r1.f22642f;
        r4 = r1.f22639a;
        r5 = r2 >> 3;
        r6 = r4[r5];
        r8 = (r2 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0284, code lost:
    
        if (((r6 >> r8) & 255) != 128) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0287, code lost:
    
        r3 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0289, code lost:
    
        r1.f22642f = r0 - r3;
        r0 = r1.d;
        r6 = (r6 & (~(255 << r8))) | (r23 << r8);
        r4[r5] = r6;
        r4[(((r2 - 7) & r0) + (r0 & 7)) >> 3] = r6;
        r0 = ~r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(long r40, java.lang.Object r42) {
        /*
            Method dump skipped, instruction units count: 701
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2483w.e(long, java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
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
            boolean r3 = r1 instanceof u.C2483w
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            u.w r1 = (u.C2483w) r1
            int r3 = r1.f22641e
            int r5 = r0.f22641e
            if (r3 == r5) goto L17
            return r4
        L17:
            java.lang.Object[] r3 = r0.f22640b
            long[] r5 = r0.c
            long[] r0 = r0.f22639a
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L63
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
            if (r10 == 0) goto L5e
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L3d:
            if (r12 >= r10) goto L5c
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L58
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r15 = r5[r13]
            long r13 = r1.c(r14)
            int r13 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r13 == 0) goto L58
            return r4
        L58:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L3d
        L5c:
            if (r10 != r11) goto L63
        L5e:
            if (r7 == r6) goto L63
            int r7 = r7 + 1
            goto L23
        L63:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2483w.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Object[] objArr = this.f22640b;
        long[] jArr = this.c;
        long[] jArr2 = this.f22639a;
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
                            Object obj = objArr[i13];
                            iHashCode += (obj != null ? obj.hashCode() : 0) ^ Long.hashCode(jArr[i13]);
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
        if (this.f22641e == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Object[] objArr = this.f22640b;
        long[] jArr = this.c;
        long[] jArr2 = this.f22639a;
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
                            Object obj = objArr[i15];
                            i10 = i11;
                            long j10 = jArr[i15];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            sb.append(j10);
                            i12++;
                            if (i12 < this.f22641e) {
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
