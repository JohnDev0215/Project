package u;

import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2481u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22633a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f22634b;
    public int[] c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22635e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22636f;

    public C2481u(int i7) {
        this.f22633a = AbstractC2456D.f22584a;
        this.f22634b = AbstractC2530a.c;
        this.c = AbstractC2470j.f22603a;
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
            long[] jArr = this.f22633a;
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
        long j3;
        int i7;
        long[] jArr;
        Object[] objArr;
        long j10;
        int i10 = -862048943;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i11 = iHashCode ^ (iHashCode << 16);
        int i12 = i11 >>> 7;
        int i13 = i11 & 127;
        int i14 = this.d;
        int i15 = i12 & i14;
        int i16 = 0;
        while (true) {
            long[] jArr2 = this.f22633a;
            int i17 = i15 >> 3;
            int i18 = (i15 & 7) << 3;
            long j11 = ((jArr2[i17 + 1] << (64 - i18)) & ((-i18) >> 63)) | (jArr2[i17] >>> i18);
            long j12 = i13;
            int i19 = i13;
            long j13 = j11 ^ (j12 * 72340172838076673L);
            for (long j14 = (~j13) & (j13 - 72340172838076673L) & (-9187201950435737472L); j14 != 0; j14 &= j14 - 1) {
                int iNumberOfTrailingZeros = (i15 + (Long.numberOfTrailingZeros(j14) >> 3)) & i14;
                if (A7.m.a(this.f22634b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            int i20 = 8;
            if ((((~j11) << 6) & j11 & (-9187201950435737472L)) != 0) {
                int iA = a(i12);
                long j15 = 128;
                long j16 = 255;
                if (this.f22636f != 0 || ((this.f22633a[iA >> 3] >> ((iA & 7) << 3)) & 255) == 254) {
                    j3 = j12;
                    i7 = 0;
                } else {
                    int i21 = this.d;
                    if (i21 <= 8 || Long.compareUnsigned(((long) this.f22635e) * 32, ((long) i21) * 25) > 0) {
                        j3 = j12;
                        i7 = 0;
                        int iD = AbstractC2456D.d(this.d);
                        long[] jArr3 = this.f22633a;
                        Object[] objArr2 = this.f22634b;
                        int[] iArr = this.c;
                        int i22 = this.d;
                        d(iD);
                        long[] jArr4 = this.f22633a;
                        Object[] objArr3 = this.f22634b;
                        int[] iArr2 = this.c;
                        int i23 = this.d;
                        int i24 = 0;
                        while (i24 < i22) {
                            if (((jArr3[i24 >> 3] >> ((i24 & 7) << 3)) & j16) < 128) {
                                Object obj2 = objArr2[i24];
                                int iHashCode2 = (obj2 != null ? obj2.hashCode() : 0) * (-862048943);
                                int i25 = iHashCode2 ^ (iHashCode2 << 16);
                                int iA2 = a(i25 >>> 7);
                                long j17 = i25 & 127;
                                int i26 = iA2 >> 3;
                                int i27 = (iA2 & 7) << 3;
                                jArr = jArr3;
                                objArr = objArr2;
                                long j18 = (jArr4[i26] & (~(255 << i27))) | (j17 << i27);
                                jArr4[i26] = j18;
                                jArr4[(((iA2 - 7) & i23) + (i23 & 7)) >> 3] = j18;
                                objArr3[iA2] = obj2;
                                iArr2[iA2] = iArr[i24];
                            } else {
                                jArr = jArr3;
                                objArr = objArr2;
                            }
                            i24++;
                            jArr3 = jArr;
                            objArr2 = objArr;
                            j16 = 255;
                        }
                    } else {
                        long[] jArr5 = this.f22633a;
                        int i28 = this.d;
                        Object[] objArr4 = this.f22634b;
                        int[] iArr3 = this.c;
                        AbstractC2456D.a(jArr5, i28);
                        int i29 = 0;
                        int iB = -1;
                        while (i29 != i28) {
                            int i30 = i29 >> 3;
                            int i31 = (i29 & 7) << 3;
                            long j19 = (jArr5[i30] >> i31) & 255;
                            if (j19 == j15) {
                                iB = i29;
                                i29++;
                            } else if (j19 != 254) {
                                i29++;
                            } else {
                                Object obj3 = objArr4[i29];
                                int iHashCode3 = (obj3 != null ? obj3.hashCode() : 0) * i10;
                                int i32 = iHashCode3 ^ (iHashCode3 << 16);
                                int i33 = i32 >>> 7;
                                int iA3 = a(i33);
                                int i34 = i33 & i28;
                                if (((iA3 - i34) & i28) / 8 == ((i29 - i34) & i28) / i20) {
                                    jArr5[i30] = (((long) (i32 & 127)) << i31) | (jArr5[i30] & (~(255 << i31)));
                                    jArr5[jArr5.length - 1] = (jArr5[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i29++;
                                    i20 = 8;
                                    i10 = -862048943;
                                } else {
                                    int i35 = i29;
                                    int i36 = iA3 >> 3;
                                    long j20 = jArr5[i36];
                                    int i37 = (iA3 & 7) << 3;
                                    if (((j20 >> i37) & 255) == j15) {
                                        j10 = j12;
                                        jArr5[i36] = (((long) (i32 & 127)) << i37) | (j20 & (~(255 << i37)));
                                        jArr5[i30] = (jArr5[i30] & (~(255 << i31))) | (128 << i31);
                                        objArr4[iA3] = objArr4[i35];
                                        objArr4[i35] = null;
                                        iArr3[iA3] = iArr3[i35];
                                        iArr3[i35] = 0;
                                        iB = i35;
                                    } else {
                                        j10 = j12;
                                        jArr5[i36] = (((long) (i32 & 127)) << i37) | (j20 & (~(255 << i37)));
                                        int i38 = iB;
                                        iB = i38 == -1 ? AbstractC2456D.b(jArr5, i35 + 1, i28) : i38;
                                        objArr4[iB] = objArr4[iA3];
                                        objArr4[iA3] = objArr4[i35];
                                        objArr4[i35] = objArr4[iB];
                                        iArr3[iB] = iArr3[iA3];
                                        iArr3[iA3] = iArr3[i35];
                                        iArr3[i35] = iArr3[iB];
                                        i35--;
                                    }
                                    jArr5[jArr5.length - 1] = (jArr5[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i29 = i35 + 1;
                                    j12 = j10;
                                    i20 = 8;
                                    i10 = -862048943;
                                    j15 = 128;
                                }
                            }
                        }
                        j3 = j12;
                        i7 = 0;
                        this.f22636f = AbstractC2456D.c(this.d) - this.f22635e;
                    }
                    iA = a(i12);
                }
                this.f22635e++;
                int i39 = this.f22636f;
                long[] jArr6 = this.f22633a;
                int i40 = iA >> 3;
                long j21 = jArr6[i40];
                int i41 = (iA & 7) << 3;
                this.f22636f = i39 - (((j21 >> i41) & 255) != 128 ? i7 : 1);
                int i42 = this.d;
                long j22 = ((~(255 << i41)) & j21) | (j3 << i41);
                jArr6[i40] = j22;
                jArr6[(((iA - 7) & i42) + (i42 & 7)) >> 3] = j22;
                return ~iA;
            }
            i16 += 8;
            i15 = (i15 + i16) & i14;
            i13 = i19;
            i10 = -862048943;
        }
    }

    public final int c(Object obj) {
        int i7 = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i10 = iHashCode ^ (iHashCode << 16);
        int i11 = i10 & 127;
        int i12 = this.d;
        int i13 = i10 >>> 7;
        while (true) {
            int i14 = i13 & i12;
            long[] jArr = this.f22633a;
            int i15 = i14 >> 3;
            int i16 = (i14 & 7) << 3;
            long j3 = ((jArr[i15 + 1] << (64 - i16)) & ((-i16) >> 63)) | (jArr[i15] >>> i16);
            long j10 = (((long) i11) * 72340172838076673L) ^ j3;
            for (long j11 = (~j10) & (j10 - 72340172838076673L) & (-9187201950435737472L); j11 != 0; j11 &= j11 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j11) >> 3) + i14) & i12;
                if (A7.m.a(this.f22634b[iNumberOfTrailingZeros], obj)) {
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
        this.f22633a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22636f = AbstractC2456D.c(this.d) - this.f22635e;
        this.f22634b = new Object[iMax];
        this.c = new int[iMax];
    }

    public final void e(int i7) {
        this.f22635e--;
        long[] jArr = this.f22633a;
        int i10 = this.d;
        int i11 = i7 >> 3;
        int i12 = (i7 & 7) << 3;
        long j3 = (jArr[i11] & (~(255 << i12))) | (254 << i12);
        jArr[i11] = j3;
        jArr[(((i7 - 7) & i10) + (i10 & 7)) >> 3] = j3;
        this.f22634b[i7] = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
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
            boolean r3 = r1 instanceof u.C2481u
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            u.u r1 = (u.C2481u) r1
            int r3 = r1.f22635e
            int r5 = r0.f22635e
            if (r3 == r5) goto L17
            return r4
        L17:
            java.lang.Object[] r3 = r0.f22634b
            int[] r5 = r0.c
            long[] r0 = r0.f22633a
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L80
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
            if (r10 == 0) goto L7b
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L3d:
            if (r12 >= r10) goto L79
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L75
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r5[r13]
            int r15 = r1.c(r14)
            if (r15 < 0) goto L5c
            int[] r14 = r1.c
            r14 = r14[r15]
            if (r13 == r14) goto L75
            return r4
        L5c:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "There is no key "
            r1.<init>(r2)
            r1.append(r14)
            java.lang.String r2 = " in the map"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L75:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L3d
        L79:
            if (r10 != r11) goto L80
        L7b:
            if (r7 == r6) goto L80
            int r7 = r7 + 1
            goto L23
        L80:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2481u.equals(java.lang.Object):boolean");
    }

    public final void f(int i7, Object obj) {
        int iB = b(obj);
        if (iB < 0) {
            iB = ~iB;
        }
        this.f22634b[iB] = obj;
        this.c[iB] = i7;
    }

    public final int hashCode() {
        Object[] objArr = this.f22634b;
        int[] iArr = this.c;
        long[] jArr = this.f22633a;
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
                            Object obj = objArr[i13];
                            iHashCode += Integer.hashCode(iArr[i13]) ^ (obj != null ? obj.hashCode() : 0);
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

    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[PHI: r8
  0x006a: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002c, B:22:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.f22635e
            if (r1 != 0) goto L9
            java.lang.String r0 = "{}"
            return r0
        L9:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "{"
            r1.<init>(r2)
            java.lang.Object[] r2 = r0.f22634b
            int[] r3 = r0.c
            long[] r4 = r0.f22633a
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L6f
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
            if (r11 == 0) goto L6a
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L38:
            if (r13 >= r11) goto L68
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L64
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            if (r15 != r0) goto L4e
            java.lang.String r15 = "(this)"
        L4e:
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0.f22635e
            if (r8 >= r14) goto L64
            java.lang.String r14 = ", "
            r1.append(r14)
        L64:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L38
        L68:
            if (r11 != r12) goto L6f
        L6a:
            if (r7 == r5) goto L6f
            int r7 = r7 + 1
            goto L1e
        L6f:
            r0 = 125(0x7d, float:1.75E-43)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "s.append('}').toString()"
            A7.m.e(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2481u.toString():java.lang.String");
    }

    public /* synthetic */ C2481u() {
        this(6);
    }
}
