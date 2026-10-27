package u;

import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2453A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22579a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f22580b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22581e;

    public C2453A(int i7) {
        this.f22579a = AbstractC2456D.f22584a;
        this.f22580b = AbstractC2530a.c;
        if (i7 >= 0) {
            f(AbstractC2456D.f(i7));
        } else {
            AbstractC2530a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final boolean a(Object obj) {
        int i7 = this.d;
        this.f22580b[d(obj)] = obj;
        return this.d != i7;
    }

    public final void b() {
        this.d = 0;
        long[] jArr = this.f22579a;
        if (jArr != AbstractC2456D.f22584a) {
            AbstractC1996k.K(jArr);
            long[] jArr2 = this.f22579a;
            int i7 = this.c;
            int i10 = i7 >> 3;
            long j3 = 255 << ((i7 & 7) << 3);
            jArr2[i10] = (jArr2[i10] & (~j3)) | j3;
        }
        AbstractC1996k.J(this.f22580b, null, 0, this.c);
        this.f22581e = AbstractC2456D.c(this.c) - this.d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0.c
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.f22579a
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.f22580b
            r15 = r15[r11]
            boolean r15 = A7.m.a(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L74
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            return r2
        L74:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2453A.c(java.lang.Object):boolean");
    }

    public final int d(Object obj) {
        long j3;
        int i7;
        long j10;
        int i10;
        int i11;
        int i12 = -862048943;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i13 = iHashCode ^ (iHashCode << 16);
        int i14 = i13 >>> 7;
        int i15 = i13 & 127;
        int i16 = this.c;
        int i17 = i14 & i16;
        int i18 = 0;
        while (true) {
            long[] jArr = this.f22579a;
            int i19 = i17 >> 3;
            int i20 = (i17 & 7) << 3;
            long j11 = ((jArr[i19 + 1] << (64 - i20)) & ((-i20) >> 63)) | (jArr[i19] >>> i20);
            long j12 = i15;
            int i21 = i15;
            long j13 = j11 ^ (j12 * 72340172838076673L);
            for (long j14 = (~j13) & (j13 - 72340172838076673L) & (-9187201950435737472L); j14 != 0; j14 &= j14 - 1) {
                int iNumberOfTrailingZeros = (i17 + (Long.numberOfTrailingZeros(j14) >> 3)) & i16;
                if (A7.m.a(this.f22580b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            int i22 = 8;
            if ((((~j11) << 6) & j11 & (-9187201950435737472L)) != 0) {
                int iE = e(i14);
                long j15 = 128;
                long j16 = 255;
                if (this.f22581e != 0 || ((this.f22579a[iE >> 3] >> ((iE & 7) << 3)) & 255) == 254) {
                    j3 = j12;
                    i7 = 0;
                } else {
                    int i23 = this.c;
                    if (i23 <= 8 || Long.compareUnsigned(((long) this.d) * 32, ((long) i23) * 25) > 0) {
                        j3 = j12;
                        i7 = 0;
                        int iD = AbstractC2456D.d(this.c);
                        long[] jArr2 = this.f22579a;
                        Object[] objArr = this.f22580b;
                        int i24 = this.c;
                        f(iD);
                        long[] jArr3 = this.f22579a;
                        Object[] objArr2 = this.f22580b;
                        int i25 = this.c;
                        int i26 = 0;
                        while (i26 < i24) {
                            if (((jArr2[i26 >> 3] >> ((i26 & 7) << 3)) & j16) < 128) {
                                Object obj2 = objArr[i26];
                                int iHashCode2 = (obj2 != null ? obj2.hashCode() : 0) * (-862048943);
                                int i27 = iHashCode2 ^ (iHashCode2 << 16);
                                int iE2 = e(i27 >>> 7);
                                long j17 = i27 & 127;
                                int i28 = iE2 >> 3;
                                int i29 = (iE2 & 7) << 3;
                                long j18 = (j17 << i29) | (jArr3[i28] & (~(255 << i29)));
                                jArr3[i28] = j18;
                                jArr3[(((iE2 - 7) & i25) + (i25 & 7)) >> 3] = j18;
                                objArr2[iE2] = obj2;
                            }
                            i26++;
                            j16 = 255;
                        }
                    } else {
                        long[] jArr4 = this.f22579a;
                        int i30 = this.c;
                        Object[] objArr3 = this.f22580b;
                        AbstractC2456D.a(jArr4, i30);
                        int i31 = 0;
                        int i32 = -1;
                        while (i31 != i30) {
                            int i33 = i31 >> 3;
                            int i34 = (i31 & 7) << 3;
                            long j19 = (jArr4[i33] >> i34) & 255;
                            if (j19 == j15) {
                                i32 = i31;
                                i31++;
                            } else if (j19 != 254) {
                                i31++;
                            } else {
                                Object obj3 = objArr3[i31];
                                int iHashCode3 = (obj3 != null ? obj3.hashCode() : 0) * i12;
                                int i35 = iHashCode3 ^ (iHashCode3 << 16);
                                int i36 = i35 >>> 7;
                                int iE3 = e(i36);
                                int i37 = i36 & i30;
                                if (((iE3 - i37) & i30) / 8 == ((i31 - i37) & i30) / i22) {
                                    jArr4[i33] = (((long) (i35 & 127)) << i34) | (jArr4[i33] & (~(255 << i34)));
                                    jArr4[jArr4.length - 1] = (jArr4[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i31++;
                                } else {
                                    int iB = i32;
                                    int i38 = iE3 >> 3;
                                    long j20 = jArr4[i38];
                                    int i39 = (iE3 & 7) << 3;
                                    if (((j20 >> i39) & 255) == 128) {
                                        i11 = i31;
                                        j10 = j12;
                                        jArr4[i38] = (j20 & (~(255 << i39))) | (((long) (i35 & 127)) << i39);
                                        jArr4[i33] = (jArr4[i33] & (~(255 << i34))) | (128 << i34);
                                        objArr3[iE3] = objArr3[i11];
                                        objArr3[i11] = null;
                                        i10 = i11;
                                    } else {
                                        j10 = j12;
                                        int i40 = i31;
                                        jArr4[i38] = (j20 & (~(255 << i39))) | (((long) (i35 & 127)) << i39);
                                        if (iB == -1) {
                                            iB = AbstractC2456D.b(jArr4, i40 + 1, i30);
                                        }
                                        objArr3[iB] = objArr3[iE3];
                                        objArr3[iE3] = objArr3[i40];
                                        objArr3[i40] = objArr3[iB];
                                        i10 = i40 - 1;
                                        i11 = iB;
                                    }
                                    jArr4[jArr4.length - 1] = (jArr4[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i31 = i10 + 1;
                                    i32 = i11;
                                    j12 = j10;
                                }
                                i22 = 8;
                                i12 = -862048943;
                                j15 = 128;
                            }
                        }
                        j3 = j12;
                        i7 = 0;
                        this.f22581e = AbstractC2456D.c(this.c) - this.d;
                    }
                    iE = e(i14);
                }
                this.d++;
                int i41 = this.f22581e;
                long[] jArr5 = this.f22579a;
                int i42 = iE >> 3;
                long j21 = jArr5[i42];
                int i43 = (iE & 7) << 3;
                this.f22581e = i41 - (((j21 >> i43) & 255) != 128 ? i7 : 1);
                int i44 = this.c;
                long j22 = ((~(255 << i43)) & j21) | (j3 << i43);
                jArr5[i42] = j22;
                jArr5[(((iE - 7) & i44) + (i44 & 7)) >> 3] = j22;
                return iE;
            }
            i18 += 8;
            i17 = (i17 + i18) & i16;
            i15 = i21;
            i12 = -862048943;
        }
    }

    public final int e(int i7) {
        int i10 = this.c;
        int i11 = i7 & i10;
        int i12 = 0;
        while (true) {
            long[] jArr = this.f22579a;
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
            boolean r1 = r15 instanceof u.C2453A
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            u.A r15 = (u.C2453A) r15
            int r1 = r15.d
            int r3 = r14.d
            if (r1 == r3) goto L13
            return r2
        L13:
            java.lang.Object[] r1 = r14.f22580b
            long[] r14 = r14.f22579a
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
        throw new UnsupportedOperationException("Method not decompiled: u.C2453A.equals(java.lang.Object):boolean");
    }

    public final void f(int i7) {
        long[] jArr;
        int iMax = i7 > 0 ? Math.max(7, AbstractC2456D.e(i7)) : 0;
        this.c = iMax;
        if (iMax == 0) {
            jArr = AbstractC2456D.f22584a;
        } else {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            AbstractC1996k.K(jArr);
        }
        this.f22579a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22581e = AbstractC2456D.c(this.c) - this.d;
        this.f22580b = new Object[iMax];
    }

    public final boolean g() {
        return this.d == 0;
    }

    public final boolean h() {
        return this.d != 0;
    }

    public final int hashCode() {
        Object[] objArr = this.f22580b;
        long[] jArr = this.f22579a;
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
                            Object obj = objArr[(i10 << 3) + i12];
                            iHashCode += obj != null ? obj.hashCode() : 0;
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

    public final void i(C2453A c2453a) {
        A7.m.f(c2453a, "elements");
        Object[] objArr = c2453a.f22580b;
        long[] jArr = c2453a.f22579a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i7 = 0;
        while (true) {
            long j3 = jArr[i7];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i10 = 8 - ((~(i7 - length)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((255 & j3) < 128) {
                        Object obj = objArr[(i7 << 3) + i11];
                        this.f22580b[d(obj)] = obj;
                    }
                    j3 >>= 8;
                }
                if (i10 != 8) {
                    return;
                }
            }
            if (i7 == length) {
                return;
            } else {
                i7++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean j(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0.c
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.f22579a
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.f22580b
            r15 = r15[r11]
            boolean r15 = A7.m.a(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L79
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            if (r2 == 0) goto L78
            r0.k(r11)
        L78:
            return r2
        L79:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2453A.j(java.lang.Object):boolean");
    }

    public final void k(int i7) {
        this.d--;
        long[] jArr = this.f22579a;
        int i10 = this.c;
        int i11 = i7 >> 3;
        int i12 = (i7 & 7) << 3;
        long j3 = (jArr[i11] & (~(255 << i12))) | (254 << i12);
        jArr[i11] = j3;
        jArr[(((i7 - 7) & i10) + (i10 & 7)) >> 3] = j3;
        this.f22580b[i7] = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0066 A[PHI: r7
  0x0066: PHI (r7v2 int) = (r7v1 int), (r7v3 int) binds: [B:6:0x0026, B:21:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r17 = this;
            r0 = r17
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "["
            r1.append(r2)
            java.lang.Object[] r2 = r0.f22580b
            long[] r3 = r0.f22579a
            int r4 = r3.length
            int r4 = r4 + (-2)
            if (r4 < 0) goto L6b
            r5 = 0
            r6 = r5
            r7 = r6
        L18:
            r8 = r3[r6]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L66
            int r10 = r6 - r4
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r5
        L32:
            if (r12 >= r10) goto L64
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L60
            int r13 = r6 << 3
            int r13 = r13 + r12
            r13 = r2[r13]
            r14 = -1
            if (r7 != r14) goto L4b
            java.lang.String r0 = "..."
            r1.append(r0)
            goto L70
        L4b:
            if (r7 == 0) goto L52
            java.lang.String r14 = ", "
            r1.append(r14)
        L52:
            if (r13 != r0) goto L57
            java.lang.String r13 = "(this)"
            goto L5b
        L57:
            java.lang.String r13 = java.lang.String.valueOf(r13)
        L5b:
            r1.append(r13)
            int r7 = r7 + 1
        L60:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L32
        L64:
            if (r10 != r11) goto L6b
        L66:
            if (r6 == r4) goto L6b
            int r6 = r6 + 1
            goto L18
        L6b:
            java.lang.String r0 = "]"
            r1.append(r0)
        L70:
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            A7.m.e(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2453A.toString():java.lang.String");
    }

    public /* synthetic */ C2453A() {
        this(6);
    }
}
