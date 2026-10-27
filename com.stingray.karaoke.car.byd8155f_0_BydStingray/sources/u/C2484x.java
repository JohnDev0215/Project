package u;

import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2484x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f22643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f22644b;
    public Object[] c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22645e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22646f;

    public C2484x(int i7) {
        this.f22643a = AbstractC2456D.f22584a;
        Object[] objArr = AbstractC2530a.c;
        this.f22644b = objArr;
        this.c = objArr;
        if (i7 >= 0) {
            f(AbstractC2456D.f(i7));
        } else {
            AbstractC2530a.c("Capacity must be a positive value.");
            throw null;
        }
    }

    public final void a() {
        this.f22645e = 0;
        long[] jArr = this.f22643a;
        if (jArr != AbstractC2456D.f22584a) {
            AbstractC1996k.K(jArr);
            long[] jArr2 = this.f22643a;
            int i7 = this.d;
            int i10 = i7 >> 3;
            long j3 = 255 << ((i7 & 7) << 3);
            jArr2[i10] = (jArr2[i10] & (~j3)) | j3;
        }
        AbstractC1996k.J(this.c, null, 0, this.d);
        AbstractC1996k.J(this.f22644b, null, 0, this.d);
        this.f22646f = AbstractC2456D.c(this.d) - this.f22645e;
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
    public final boolean b(java.lang.Object r18) {
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
            int r5 = r0.d
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.f22643a
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
            java.lang.Object[] r15 = r0.f22644b
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
        throw new UnsupportedOperationException("Method not decompiled: u.C2484x.b(java.lang.Object):boolean");
    }

    public final int c(int i7) {
        int i10 = this.d;
        int i11 = i7 & i10;
        int i12 = 0;
        while (true) {
            long[] jArr = this.f22643a;
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

    public final int d(Object obj) {
        long j3;
        int i7;
        long[] jArr;
        Object[] objArr;
        long j10;
        int i10;
        int i11 = -862048943;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i12 = iHashCode ^ (iHashCode << 16);
        int i13 = i12 >>> 7;
        int i14 = i12 & 127;
        int i15 = this.d;
        int i16 = i13 & i15;
        int i17 = 0;
        while (true) {
            long[] jArr2 = this.f22643a;
            int i18 = i16 >> 3;
            int i19 = (i16 & 7) << 3;
            long j11 = ((jArr2[i18 + 1] << (64 - i19)) & ((-i19) >> 63)) | (jArr2[i18] >>> i19);
            long j12 = i14;
            int i20 = i14;
            long j13 = j11 ^ (j12 * 72340172838076673L);
            for (long j14 = (~j13) & (j13 - 72340172838076673L) & (-9187201950435737472L); j14 != 0; j14 &= j14 - 1) {
                int iNumberOfTrailingZeros = (i16 + (Long.numberOfTrailingZeros(j14) >> 3)) & i15;
                if (A7.m.a(this.f22644b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            int i21 = 8;
            if ((((~j11) << 6) & j11 & (-9187201950435737472L)) != 0) {
                int iC = c(i13);
                long j15 = 128;
                long j16 = 255;
                if (this.f22646f != 0 || ((this.f22643a[iC >> 3] >> ((iC & 7) << 3)) & 255) == 254) {
                    j3 = j12;
                    i7 = 0;
                } else {
                    int i22 = this.d;
                    if (i22 <= 8 || Long.compareUnsigned(((long) this.f22645e) * 32, ((long) i22) * 25) > 0) {
                        j3 = j12;
                        i7 = 0;
                        int iD = AbstractC2456D.d(this.d);
                        long[] jArr3 = this.f22643a;
                        Object[] objArr2 = this.f22644b;
                        Object[] objArr3 = this.c;
                        int i23 = this.d;
                        f(iD);
                        long[] jArr4 = this.f22643a;
                        Object[] objArr4 = this.f22644b;
                        Object[] objArr5 = this.c;
                        int i24 = this.d;
                        int i25 = 0;
                        while (i25 < i23) {
                            if (((jArr3[i25 >> 3] >> ((i25 & 7) << 3)) & j16) < 128) {
                                Object obj2 = objArr2[i25];
                                int iHashCode2 = (obj2 != null ? obj2.hashCode() : 0) * (-862048943);
                                int i26 = iHashCode2 ^ (iHashCode2 << 16);
                                int iC2 = c(i26 >>> 7);
                                long j17 = i26 & 127;
                                int i27 = iC2 >> 3;
                                int i28 = (iC2 & 7) << 3;
                                jArr = jArr3;
                                objArr = objArr2;
                                long j18 = (jArr4[i27] & (~(255 << i28))) | (j17 << i28);
                                jArr4[i27] = j18;
                                jArr4[(((iC2 - 7) & i24) + (i24 & 7)) >> 3] = j18;
                                objArr4[iC2] = obj2;
                                objArr5[iC2] = objArr3[i25];
                            } else {
                                jArr = jArr3;
                                objArr = objArr2;
                            }
                            i25++;
                            jArr3 = jArr;
                            objArr2 = objArr;
                            j16 = 255;
                        }
                    } else {
                        long[] jArr5 = this.f22643a;
                        int i29 = this.d;
                        Object[] objArr6 = this.f22644b;
                        Object[] objArr7 = this.c;
                        AbstractC2456D.a(jArr5, i29);
                        int i30 = 0;
                        int iB = -1;
                        while (i30 != i29) {
                            int i31 = i30 >> 3;
                            int i32 = (i30 & 7) << 3;
                            long j19 = (jArr5[i31] >> i32) & 255;
                            if (j19 == j15) {
                                iB = i30;
                                i30++;
                            } else if (j19 != 254) {
                                i30++;
                            } else {
                                Object obj3 = objArr6[i30];
                                int iHashCode3 = (obj3 != null ? obj3.hashCode() : 0) * i11;
                                int i33 = iHashCode3 ^ (iHashCode3 << 16);
                                int i34 = i33 >>> 7;
                                int iC3 = c(i34);
                                int i35 = i34 & i29;
                                if (((iC3 - i35) & i29) / 8 == ((i30 - i35) & i29) / i21) {
                                    jArr5[i31] = (((long) (i33 & 127)) << i32) | (jArr5[i31] & (~(255 << i32)));
                                    jArr5[jArr5.length - 1] = jArr5[0];
                                    i30++;
                                    i21 = 8;
                                    i11 = -862048943;
                                } else {
                                    int i36 = i30;
                                    int i37 = iC3 >> 3;
                                    long j20 = jArr5[i37];
                                    int i38 = (iC3 & 7) << 3;
                                    if (((j20 >> i38) & 255) == j15) {
                                        j10 = j12;
                                        jArr5[i37] = (((long) (i33 & 127)) << i38) | (j20 & (~(255 << i38)));
                                        jArr5[i31] = (jArr5[i31] & (~(255 << i32))) | (128 << i32);
                                        objArr6[iC3] = objArr6[i36];
                                        objArr6[i36] = null;
                                        objArr7[iC3] = objArr7[i36];
                                        objArr7[i36] = null;
                                        i10 = i36;
                                        iB = i10;
                                    } else {
                                        j10 = j12;
                                        jArr5[i37] = (((long) (i33 & 127)) << i38) | (j20 & (~(255 << i38)));
                                        int i39 = iB;
                                        iB = i39 == -1 ? AbstractC2456D.b(jArr5, i36 + 1, i29) : i39;
                                        objArr6[iB] = objArr6[iC3];
                                        objArr6[iC3] = objArr6[i36];
                                        objArr6[i36] = objArr6[iB];
                                        objArr7[iB] = objArr7[iC3];
                                        objArr7[iC3] = objArr7[i36];
                                        objArr7[i36] = objArr7[iB];
                                        i10 = i36 - 1;
                                    }
                                    jArr5[jArr5.length - 1] = jArr5[0];
                                    i30 = i10 + 1;
                                    j12 = j10;
                                    i21 = 8;
                                    i11 = -862048943;
                                    j15 = 128;
                                }
                            }
                        }
                        j3 = j12;
                        i7 = 0;
                        this.f22646f = AbstractC2456D.c(this.d) - this.f22645e;
                    }
                    iC = c(i13);
                }
                this.f22645e++;
                int i40 = this.f22646f;
                long[] jArr6 = this.f22643a;
                int i41 = iC >> 3;
                long j21 = jArr6[i41];
                int i42 = (iC & 7) << 3;
                this.f22646f = i40 - (((j21 >> i42) & 255) != 128 ? i7 : 1);
                int i43 = this.d;
                long j22 = ((~(255 << i42)) & j21) | (j3 << i42);
                jArr6[i41] = j22;
                jArr6[(((iC - 7) & i43) + (i43 & 7)) >> 3] = j22;
                return ~iC;
            }
            i17 += 8;
            i16 = (i16 + i17) & i15;
            i14 = i20;
            i11 = -862048943;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(java.lang.Object r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L8
            int r1 = r14.hashCode()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r13.d
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.f22643a
            int r5 = r1 >> 3
            r6 = r1 & 7
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
            long r6 = (long) r2
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L42:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L61
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            java.lang.Object[] r11 = r13.f22644b
            r11 = r11[r10]
            boolean r11 = A7.m.a(r11, r14)
            if (r11 == 0) goto L5b
            goto L6b
        L5b:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L42
        L61:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L74
            r10 = -1
        L6b:
            if (r10 < 0) goto L72
            java.lang.Object[] r13 = r13.c
            r13 = r13[r10]
            goto L73
        L72:
            r13 = 0
        L73:
            return r13
        L74:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2484x.e(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        return false;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006f  */
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
            boolean r3 = r1 instanceof u.C2484x
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            u.x r1 = (u.C2484x) r1
            int r3 = r1.f22645e
            int r5 = r0.f22645e
            if (r3 == r5) goto L17
            return r4
        L17:
            java.lang.Object[] r3 = r0.f22644b
            java.lang.Object[] r5 = r0.c
            long[] r0 = r0.f22643a
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L74
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
            if (r10 == 0) goto L6f
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L3d:
            if (r12 >= r10) goto L6d
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L69
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r5[r13]
            if (r13 != 0) goto L5e
            java.lang.Object r13 = r1.e(r14)
            if (r13 != 0) goto L5d
            boolean r13 = r1.b(r14)
            if (r13 != 0) goto L69
        L5d:
            return r4
        L5e:
            java.lang.Object r14 = r1.e(r14)
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L69
            return r4
        L69:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L3d
        L6d:
            if (r10 != r11) goto L74
        L6f:
            if (r7 == r6) goto L74
            int r7 = r7 + 1
            goto L23
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2484x.equals(java.lang.Object):boolean");
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
        this.f22643a = jArr;
        int i10 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i10] = (jArr[i10] & (~j3)) | j3;
        this.f22646f = AbstractC2456D.c(this.d) - this.f22645e;
        this.f22644b = new Object[iMax];
        this.c = new Object[iMax];
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(java.lang.Object r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L8
            int r1 = r14.hashCode()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r13.d
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.f22643a
            int r5 = r1 >> 3
            r6 = r1 & 7
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
            long r6 = (long) r2
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L42:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L61
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            java.lang.Object[] r11 = r13.f22644b
            r11 = r11[r10]
            boolean r11 = A7.m.a(r11, r14)
            if (r11 == 0) goto L5b
            goto L6b
        L5b:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L42
        L61:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L74
            r10 = -1
        L6b:
            if (r10 < 0) goto L72
            java.lang.Object r13 = r13.h(r10)
            return r13
        L72:
            r13 = 0
            return r13
        L74:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2484x.g(java.lang.Object):java.lang.Object");
    }

    public final Object h(int i7) {
        this.f22645e--;
        long[] jArr = this.f22643a;
        int i10 = this.d;
        int i11 = i7 >> 3;
        int i12 = (i7 & 7) << 3;
        long j3 = (jArr[i11] & (~(255 << i12))) | (254 << i12);
        jArr[i11] = j3;
        jArr[(((i7 - 7) & i10) + (i10 & 7)) >> 3] = j3;
        this.f22644b[i7] = null;
        Object[] objArr = this.c;
        Object obj = objArr[i7];
        objArr[i7] = null;
        return obj;
    }

    public final int hashCode() {
        Object[] objArr = this.f22644b;
        Object[] objArr2 = this.c;
        long[] jArr = this.f22643a;
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
                            Object obj2 = objArr2[i13];
                            iHashCode += (obj2 != null ? obj2.hashCode() : 0) ^ (obj != null ? obj.hashCode() : 0);
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

    public final void i(Object obj, Object obj2) {
        int iD = d(obj);
        if (iD < 0) {
            iD = ~iD;
        }
        this.f22644b[iD] = obj;
        this.c[iD] = obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0070 A[PHI: r8
  0x0070: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002c, B:25:0x006e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.f22645e
            if (r1 != 0) goto L9
            java.lang.String r0 = "{}"
            return r0
        L9:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "{"
            r1.<init>(r2)
            java.lang.Object[] r2 = r0.f22644b
            java.lang.Object[] r3 = r0.c
            long[] r4 = r0.f22643a
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L75
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
            if (r11 == 0) goto L70
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L38:
            if (r13 >= r11) goto L6e
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L6a
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            java.lang.String r16 = "(this)"
            if (r15 != r0) goto L50
            r15 = r16
        L50:
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            if (r14 != r0) goto L5c
            r14 = r16
        L5c:
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0.f22645e
            if (r8 >= r14) goto L6a
            java.lang.String r14 = ", "
            r1.append(r14)
        L6a:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L38
        L6e:
            if (r11 != r12) goto L75
        L70:
            if (r7 == r5) goto L75
            int r7 = r7 + 1
            goto L1e
        L75:
            r0 = 125(0x7d, float:1.75E-43)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "s.append('}').toString()"
            A7.m.e(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2484x.toString():java.lang.String");
    }

    public /* synthetic */ C2484x() {
        this(6);
    }
}
