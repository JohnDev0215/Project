package p8;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: renamed from: p8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2130e {
    public int c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InputStream f20971e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20972f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20975i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20974h = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f20969a = new byte[4096];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20970b = 0;
    public int d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20973g = 0;

    public C2130e(InputStream inputStream) {
        this.f20971e = inputStream;
    }

    public final void a(int i7) {
        if (this.f20972f != i7) {
            throw new C2142q("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int b() {
        int i7 = this.f20974h;
        if (i7 == Integer.MAX_VALUE) {
            return -1;
        }
        return i7 - (this.f20973g + this.d);
    }

    public final void c(int i7) {
        this.f20974h = i7;
        o();
    }

    public final int d(int i7) {
        if (i7 < 0) {
            throw new C2142q("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i10 = this.f20973g + this.d + i7;
        int i11 = this.f20974h;
        if (i10 > i11) {
            throw C2142q.a();
        }
        this.f20974h = i10;
        o();
        return i11;
    }

    public final t e() {
        int iK = k();
        int i7 = this.f20970b;
        int i10 = this.d;
        if (iK > i7 - i10 || iK <= 0) {
            return iK == 0 ? AbstractC2129d.l : new t(h(iK));
        }
        byte[] bArr = new byte[iK];
        System.arraycopy(this.f20969a, i10, bArr, 0, iK);
        t tVar = new t(bArr);
        this.d += iK;
        return tVar;
    }

    public final int f() {
        return k();
    }

    public final AbstractC2126a g(v vVar, C2132g c2132g) throws C2142q {
        int iK = k();
        if (this.f20975i >= 64) {
            throw new C2142q("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iD = d(iK);
        this.f20975i++;
        AbstractC2126a abstractC2126a = (AbstractC2126a) vVar.a(this, c2132g);
        a(0);
        this.f20975i--;
        c(iD);
        return abstractC2126a;
    }

    public final byte[] h(int i7) throws IOException {
        if (i7 <= 0) {
            if (i7 == 0) {
                return AbstractC2141p.f20989a;
            }
            throw new C2142q("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i10 = this.f20973g;
        int i11 = this.d;
        int i12 = i10 + i11 + i7;
        int i13 = this.f20974h;
        if (i12 > i13) {
            r((i13 - i10) - i11);
            throw C2142q.a();
        }
        byte[] bArr = this.f20969a;
        if (i7 < 4096) {
            byte[] bArr2 = new byte[i7];
            int i14 = this.f20970b - i11;
            System.arraycopy(bArr, i11, bArr2, 0, i14);
            this.d = this.f20970b;
            int i15 = i7 - i14;
            if (i15 > 0) {
                p(i15);
            }
            System.arraycopy(bArr, 0, bArr2, i14, i15);
            this.d = i15;
            return bArr2;
        }
        int i16 = this.f20970b;
        this.f20973g = i10 + i16;
        this.d = 0;
        this.f20970b = 0;
        int length = i16 - i11;
        int i17 = i7 - length;
        ArrayList<byte[]> arrayList = new ArrayList();
        while (i17 > 0) {
            int iMin = Math.min(i17, 4096);
            byte[] bArr3 = new byte[iMin];
            int i18 = 0;
            while (i18 < iMin) {
                int i19 = this.f20971e.read(bArr3, i18, iMin - i18);
                if (i19 == -1) {
                    throw C2142q.a();
                }
                this.f20973g += i19;
                i18 += i19;
            }
            i17 -= iMin;
            arrayList.add(bArr3);
        }
        byte[] bArr4 = new byte[i7];
        System.arraycopy(bArr, i11, bArr4, 0, length);
        for (byte[] bArr5 : arrayList) {
            System.arraycopy(bArr5, 0, bArr4, length, bArr5.length);
            length += bArr5.length;
        }
        return bArr4;
    }

    public final int i() throws C2142q {
        int i7 = this.d;
        if (this.f20970b - i7 < 4) {
            p(4);
            i7 = this.d;
        }
        this.d = i7 + 4;
        byte[] bArr = this.f20969a;
        return ((bArr[i7 + 3] & 255) << 24) | (bArr[i7] & 255) | ((bArr[i7 + 1] & 255) << 8) | ((bArr[i7 + 2] & 255) << 16);
    }

    public final long j() throws C2142q {
        int i7 = this.d;
        if (this.f20970b - i7 < 8) {
            p(8);
            i7 = this.d;
        }
        this.d = i7 + 8;
        byte[] bArr = this.f20969a;
        return ((((long) bArr[i7 + 1]) & 255) << 8) | (((long) bArr[i7]) & 255) | ((((long) bArr[i7 + 2]) & 255) << 16) | ((((long) bArr[i7 + 3]) & 255) << 24) | ((((long) bArr[i7 + 4]) & 255) << 32) | ((((long) bArr[i7 + 5]) & 255) << 40) | ((((long) bArr[i7 + 6]) & 255) << 48) | ((((long) bArr[i7 + 7]) & 255) << 56);
    }

    public final int k() {
        int i7;
        int i10 = this.d;
        int i11 = this.f20970b;
        if (i11 != i10) {
            int i12 = i10 + 1;
            byte[] bArr = this.f20969a;
            byte b10 = bArr[i10];
            if (b10 >= 0) {
                this.d = i12;
                return b10;
            }
            if (i11 - i12 >= 9) {
                int i13 = i10 + 2;
                int i14 = (bArr[i12] << 7) ^ b10;
                long j3 = i14;
                if (j3 < 0) {
                    i7 = (int) ((-128) ^ j3);
                } else {
                    int i15 = i10 + 3;
                    int i16 = (bArr[i13] << 14) ^ i14;
                    long j10 = i16;
                    if (j10 >= 0) {
                        i7 = (int) (16256 ^ j10);
                    } else {
                        int i17 = i10 + 4;
                        int i18 = i16 ^ (bArr[i15] << 21);
                        long j11 = i18;
                        if (j11 < 0) {
                            i7 = (int) ((-2080896) ^ j11);
                        } else {
                            i15 = i10 + 5;
                            byte b11 = bArr[i17];
                            int i19 = (int) (((long) (i18 ^ (b11 << 28))) ^ 266354560);
                            if (b11 < 0) {
                                i17 = i10 + 6;
                                if (bArr[i15] < 0) {
                                    i15 = i10 + 7;
                                    if (bArr[i17] < 0) {
                                        i17 = i10 + 8;
                                        if (bArr[i15] < 0) {
                                            i15 = i10 + 9;
                                            if (bArr[i17] < 0) {
                                                int i20 = i10 + 10;
                                                if (bArr[i15] >= 0) {
                                                    i13 = i20;
                                                    i7 = i19;
                                                }
                                            }
                                        }
                                    }
                                }
                                i7 = i19;
                            }
                            i7 = i19;
                        }
                        i13 = i17;
                    }
                    i13 = i15;
                }
                this.d = i13;
                return i7;
            }
        }
        return (int) m();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
    
        if (r3[r2] < 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long l() {
        /*
            r12 = this;
            int r0 = r12.d
            int r1 = r12.f20970b
            if (r1 != r0) goto L8
            goto Lb8
        L8:
            int r2 = r0 + 1
            byte[] r3 = r12.f20969a
            r4 = r3[r0]
            if (r4 < 0) goto L14
            r12.d = r2
            long r0 = (long) r4
            return r0
        L14:
            int r1 = r1 - r2
            r5 = 9
            if (r1 >= r5) goto L1b
            goto Lb8
        L1b:
            int r1 = r0 + 2
            r2 = r3[r2]
            int r2 = r2 << 7
            r2 = r2 ^ r4
            long r4 = (long) r2
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L2e
            r2 = -128(0xffffffffffffff80, double:NaN)
        L2b:
            long r2 = r2 ^ r4
            goto Lc1
        L2e:
            int r2 = r0 + 3
            r1 = r3[r1]
            int r1 = r1 << 14
            long r8 = (long) r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L42
            r0 = 16256(0x3f80, double:8.0315E-320)
        L3c:
            long r0 = r0 ^ r4
            r10 = r0
            r1 = r2
            r2 = r10
            goto Lc1
        L42:
            int r1 = r0 + 4
            r2 = r3[r2]
            int r2 = r2 << 21
            long r8 = (long) r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L52
            r2 = -2080896(0xffffffffffe03f80, double:NaN)
            goto L2b
        L52:
            int r2 = r0 + 5
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 28
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L63
            r0 = 266354560(0xfe03f80, double:1.315966377E-315)
            goto L3c
        L63:
            int r1 = r0 + 6
            r2 = r3[r2]
            long r8 = (long) r2
            r2 = 35
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L76
            r2 = -34093383808(0xfffffff80fe03f80, double:NaN)
            goto L2b
        L76:
            int r2 = r0 + 7
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 42
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L89
            r0 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L3c
        L89:
            int r1 = r0 + 8
            r2 = r3[r2]
            long r8 = (long) r2
            r2 = 49
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L9c
            r2 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L2b
        L9c:
            int r2 = r0 + 9
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 56
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            r8 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto Lbf
            int r1 = r0 + 10
            r0 = r3[r2]
            long r2 = (long) r0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto Lbd
        Lb8:
            long r0 = r12.m()
            return r0
        Lbd:
            r2 = r4
            goto Lc1
        Lbf:
            r1 = r2
            goto Lbd
        Lc1:
            r12.d = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.C2130e.l():long");
    }

    public final long m() throws C2142q {
        long j3 = 0;
        for (int i7 = 0; i7 < 64; i7 += 7) {
            if (this.d == this.f20970b) {
                p(1);
            }
            int i10 = this.d;
            this.d = i10 + 1;
            byte b10 = this.f20969a[i10];
            j3 |= ((long) (b10 & 127)) << i7;
            if ((b10 & 128) == 0) {
                return j3;
            }
        }
        throw new C2142q("CodedInputStream encountered a malformed varint.");
    }

    public final int n() throws C2142q {
        if (this.d == this.f20970b && !s(1)) {
            this.f20972f = 0;
            return 0;
        }
        int iK = k();
        this.f20972f = iK;
        if ((iK >>> 3) != 0) {
            return iK;
        }
        throw new C2142q("Protocol message contained an invalid tag (zero).");
    }

    public final void o() {
        int i7 = this.f20970b + this.c;
        this.f20970b = i7;
        int i10 = this.f20973g + i7;
        int i11 = this.f20974h;
        if (i10 <= i11) {
            this.c = 0;
            return;
        }
        int i12 = i10 - i11;
        this.c = i12;
        this.f20970b = i7 - i12;
    }

    public final void p(int i7) throws C2142q {
        if (!s(i7)) {
            throw C2142q.a();
        }
    }

    public final boolean q(int i7, A2.g gVar) throws IOException {
        int iN;
        int i10 = i7 & 7;
        if (i10 == 0) {
            long jL = l();
            gVar.L(i7);
            gVar.M(jL);
            return true;
        }
        if (i10 == 1) {
            long j3 = j();
            gVar.L(i7);
            gVar.K(j3);
            return true;
        }
        if (i10 == 2) {
            t tVarE = e();
            gVar.L(i7);
            gVar.L(tVarE.size());
            gVar.H(tVarE);
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw new C2142q("Protocol message tag had invalid wire type.");
            }
            int i11 = i();
            gVar.L(i7);
            gVar.J(i11);
            return true;
        }
        gVar.L(i7);
        do {
            iN = n();
            if (iN == 0) {
                break;
            }
        } while (q(iN, gVar));
        int i12 = ((i7 >>> 3) << 3) | 4;
        a(i12);
        gVar.L(i12);
        return true;
    }

    public final void r(int i7) throws C2142q {
        int i10 = this.f20970b;
        int i11 = this.d;
        int i12 = i10 - i11;
        if (i7 <= i12 && i7 >= 0) {
            this.d = i11 + i7;
            return;
        }
        if (i7 < 0) {
            throw new C2142q("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i13 = this.f20973g;
        int i14 = i13 + i11 + i7;
        int i15 = this.f20974h;
        if (i14 > i15) {
            r((i15 - i13) - i11);
            throw C2142q.a();
        }
        this.d = i10;
        p(1);
        while (true) {
            int i16 = i7 - i12;
            int i17 = this.f20970b;
            if (i16 <= i17) {
                this.d = i16;
                return;
            } else {
                i12 += i17;
                this.d = i17;
                p(1);
            }
        }
    }

    public final boolean s(int i7) throws IOException {
        InputStream inputStream;
        int i10 = this.d;
        int i11 = i10 + i7;
        int i12 = this.f20970b;
        if (i11 <= i12) {
            StringBuilder sb = new StringBuilder(77);
            sb.append("refillBuffer() called when ");
            sb.append(i7);
            sb.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb.toString());
        }
        if (this.f20973g + i10 + i7 <= this.f20974h && (inputStream = this.f20971e) != null) {
            byte[] bArr = this.f20969a;
            if (i10 > 0) {
                if (i12 > i10) {
                    System.arraycopy(bArr, i10, bArr, 0, i12 - i10);
                }
                this.f20973g += i10;
                this.f20970b -= i10;
                this.d = 0;
            }
            int i13 = this.f20970b;
            int i14 = inputStream.read(bArr, i13, bArr.length - i13);
            if (i14 == 0 || i14 < -1 || i14 > bArr.length) {
                StringBuilder sb2 = new StringBuilder(102);
                sb2.append("InputStream#read(byte[]) returned invalid result: ");
                sb2.append(i14);
                sb2.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb2.toString());
            }
            if (i14 > 0) {
                this.f20970b += i14;
                if ((this.f20973g + i7) - 67108864 > 0) {
                    throw new C2142q("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                }
                o();
                if (this.f20970b >= i7) {
                    return true;
                }
                return s(i7);
            }
        }
        return false;
    }
}
