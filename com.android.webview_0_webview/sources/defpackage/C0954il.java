package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: il, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0954il extends AbstractC1010jl {
    public final InputStream e;
    public final byte[] f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public InterfaceC0899hl m;

    public C0954il(InputStream inputStream, int i, AbstractC0787fl abstractC0787fl) {
        super(null);
        this.l = Integer.MAX_VALUE;
        this.m = null;
        AbstractC0119Gl.b(inputStream, "input");
        this.e = inputStream;
        this.f = new byte[i];
        this.g = 0;
        this.i = 0;
        this.k = 0;
    }

    public byte A() throws C0155Il {
        if (this.i == this.g) {
            K(1);
        }
        byte[] bArr = this.f;
        int i = this.i;
        this.i = i + 1;
        return bArr[i];
    }

    public final byte[] B(int i, boolean z) throws IOException {
        byte[] bArrC = C(i);
        if (bArrC != null) {
            return z ? (byte[]) bArrC.clone() : bArrC;
        }
        int i2 = this.i;
        int i3 = this.g;
        int length = i3 - i2;
        this.k += i3;
        this.i = 0;
        this.g = 0;
        List listD = D(i - length);
        byte[] bArr = new byte[i];
        System.arraycopy(this.f, i2, bArr, 0, length);
        for (byte[] bArr2 : (ArrayList) listD) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }

    public final byte[] C(int i) throws IOException {
        if (i == 0) {
            return AbstractC0119Gl.b;
        }
        if (i < 0) {
            throw C0155Il.e();
        }
        int i2 = this.k;
        int i3 = this.i;
        int i4 = i2 + i3 + i;
        if (i4 - this.c > 0) {
            throw new C0155Il("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }
        int i5 = this.l;
        if (i4 > i5) {
            L((i5 - i2) - i3);
            throw C0155Il.g();
        }
        int i6 = this.g - i3;
        int i7 = i - i6;
        if (i7 >= 4096 && i7 > this.e.available()) {
            return null;
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.f, this.i, bArr, 0, i6);
        this.k += this.g;
        this.i = 0;
        this.g = 0;
        while (i6 < i) {
            int i8 = this.e.read(bArr, i6, i - i6);
            if (i8 == -1) {
                throw C0155Il.g();
            }
            this.k += i8;
            i6 += i8;
        }
        return bArr;
    }

    public final List D(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int iMin = Math.min(i, 4096);
            byte[] bArr = new byte[iMin];
            int i2 = 0;
            while (i2 < iMin) {
                int i3 = this.e.read(bArr, i2, iMin - i2);
                if (i3 == -1) {
                    throw C0155Il.g();
                }
                this.k += i3;
                i2 += i3;
            }
            i -= iMin;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public int E() throws C0155Il {
        int i = this.i;
        if (this.g - i < 4) {
            K(4);
            i = this.i;
        }
        byte[] bArr = this.f;
        this.i = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public long F() throws C0155Il {
        int i = this.i;
        if (this.g - i < 8) {
            K(8);
            i = this.i;
        }
        byte[] bArr = this.f;
        this.i = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
    
        if (r2[r3] < 0) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int G() {
        /*
            r5 = this;
            int r0 = r5.i
            int r1 = r5.g
            if (r1 != r0) goto L7
            goto L6a
        L7:
            byte[] r2 = r5.f
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r5.i = r3
            return r0
        L12:
            int r1 = r1 - r3
            r4 = 9
            if (r1 >= r4) goto L18
            goto L6a
        L18:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L24
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L70
        L24:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L31
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2f:
            r1 = r3
            goto L70
        L31:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3f
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L70
        L3f:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2f
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L70
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2f
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L70
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2f
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 >= 0) goto L70
        L6a:
            long r0 = r5.I()
            int r1 = (int) r0
            return r1
        L70:
            r5.i = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0954il.G():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
    
        if (r2[r0] < 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long H() {
        /*
            r11 = this;
            int r0 = r11.i
            int r1 = r11.g
            if (r1 != r0) goto L8
            goto Lb6
        L8:
            byte[] r2 = r11.f
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L14
            r11.i = r3
            long r0 = (long) r0
            return r0
        L14:
            int r1 = r1 - r3
            r4 = 9
            if (r1 >= r4) goto L1b
            goto Lb6
        L1b:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L29
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
        L26:
            long r2 = (long) r0
            goto Lbd
        L29:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L3a
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto Lbd
        L3a:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L48
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L26
        L48:
            long r3 = (long) r0
            int r0 = r1 + 1
            r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L5f
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L5b:
            long r2 = r3 ^ r1
            r1 = r0
            goto Lbd
        L5f:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L74
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L71:
            long r2 = r3 ^ r5
            goto Lbd
        L74:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L87
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L5b
        L87:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L9a
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L71
        L9a:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto Lbb
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 >= 0) goto Lbc
        Lb6:
            long r0 = r11.I()
            return r0
        Lbb:
            r1 = r0
        Lbc:
            r2 = r3
        Lbd:
            r11.i = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0954il.H():long");
    }

    public long I() throws C0155Il {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bA = A();
            j |= ((long) (bA & 127)) << i;
            if ((bA & 128) == 0) {
                return j;
            }
        }
        throw C0155Il.d();
    }

    public final void J() {
        int i = this.g + this.h;
        this.g = i;
        int i2 = this.k + i;
        int i3 = this.l;
        if (i2 <= i3) {
            this.h = 0;
            return;
        }
        int i4 = i2 - i3;
        this.h = i4;
        this.g = i - i4;
    }

    public final void K(int i) throws C0155Il {
        if (M(i)) {
            return;
        }
        if (i <= (this.c - this.k) - this.i) {
            throw C0155Il.g();
        }
        throw new C0155Il("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public void L(int i) throws C0155Il {
        int i2 = this.g;
        int i3 = this.i;
        if (i <= i2 - i3 && i >= 0) {
            this.i = i3 + i;
            return;
        }
        if (i < 0) {
            throw C0155Il.e();
        }
        int i4 = this.k;
        int i5 = this.i;
        int i6 = i4 + i5;
        int i7 = i6 + i;
        int i8 = this.l;
        if (i7 > i8) {
            L((i8 - i4) - i5);
            throw C0155Il.g();
        }
        this.k = i6;
        int i9 = this.g - i5;
        this.g = 0;
        this.i = 0;
        while (i9 < i) {
            try {
                long j = i - i9;
                long jSkip = this.e.skip(j);
                if (jSkip < 0 || jSkip > j) {
                    throw new IllegalStateException(this.e.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                }
                if (jSkip == 0) {
                    break;
                } else {
                    i9 += (int) jSkip;
                }
            } finally {
                this.k += i9;
                J();
            }
        }
        if (i9 >= i) {
            return;
        }
        int i10 = this.g;
        int i11 = i10 - this.i;
        this.i = i10;
        K(1);
        while (true) {
            int i12 = i - i11;
            int i13 = this.g;
            if (i12 <= i13) {
                this.i = i12;
                return;
            } else {
                i11 += i13;
                this.i = i13;
                K(1);
            }
        }
    }

    public final boolean M(int i) throws IOException {
        int i2 = this.i;
        int i3 = i2 + i;
        int i4 = this.g;
        if (i3 <= i4) {
            throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
        }
        int i5 = this.c;
        int i6 = this.k;
        if (i > (i5 - i6) - i2 || i6 + i2 + i > this.l) {
            return false;
        }
        if (i2 > 0) {
            if (i4 > i2) {
                byte[] bArr = this.f;
                System.arraycopy(bArr, i2, bArr, 0, i4 - i2);
            }
            this.k += i2;
            this.g -= i2;
            this.i = 0;
        }
        InputStream inputStream = this.e;
        byte[] bArr2 = this.f;
        int i7 = this.g;
        int i8 = inputStream.read(bArr2, i7, Math.min(bArr2.length - i7, (this.c - this.k) - i7));
        if (i8 == 0 || i8 < -1 || i8 > this.f.length) {
            throw new IllegalStateException(this.e.getClass() + "#read(byte[]) returned invalid result: " + i8 + "\nThe InputStream implementation is buggy.");
        }
        if (i8 <= 0) {
            return false;
        }
        this.g += i8;
        J();
        if (this.g >= i) {
            return true;
        }
        return M(i);
    }

    @Override // defpackage.AbstractC1010jl
    public void a(int i) throws C0155Il {
        if (this.j != i) {
            throw C0155Il.a();
        }
    }

    @Override // defpackage.AbstractC1010jl
    public int d() {
        return this.k + this.i;
    }

    @Override // defpackage.AbstractC1010jl
    public boolean e() {
        return this.i == this.g && !M(1);
    }

    @Override // defpackage.AbstractC1010jl
    public void g(int i) {
        this.l = i;
        J();
    }

    @Override // defpackage.AbstractC1010jl
    public int h(int i) throws C0155Il {
        if (i < 0) {
            throw C0155Il.e();
        }
        int i2 = this.k + this.i + i;
        int i3 = this.l;
        if (i2 > i3) {
            throw C0155Il.g();
        }
        this.l = i2;
        J();
        return i3;
    }

    @Override // defpackage.AbstractC1010jl
    public boolean i() {
        return H() != 0;
    }

    @Override // defpackage.AbstractC1010jl
    public AbstractC0731el j() throws IOException {
        int iG = G();
        int i = this.g;
        int i2 = this.i;
        if (iG <= i - i2 && iG > 0) {
            AbstractC0731el abstractC0731elH = AbstractC0731el.h(this.f, i2, iG);
            this.i += iG;
            return abstractC0731elH;
        }
        if (iG == 0) {
            return AbstractC0731el.t;
        }
        byte[] bArrC = C(iG);
        if (bArrC != null) {
            return AbstractC0731el.g(bArrC);
        }
        int i3 = this.i;
        int i4 = this.g;
        int length = i4 - i3;
        this.k += i4;
        this.i = 0;
        this.g = 0;
        List listD = D(iG - length);
        byte[] bArr = new byte[iG];
        System.arraycopy(this.f, i3, bArr, 0, length);
        for (byte[] bArr2 : (ArrayList) listD) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return AbstractC0731el.n(bArr);
    }

    @Override // defpackage.AbstractC1010jl
    public double k() {
        return Double.longBitsToDouble(F());
    }

    @Override // defpackage.AbstractC1010jl
    public int l() {
        return G();
    }

    @Override // defpackage.AbstractC1010jl
    public int m() {
        return E();
    }

    @Override // defpackage.AbstractC1010jl
    public long n() {
        return F();
    }

    @Override // defpackage.AbstractC1010jl
    public float o() {
        return Float.intBitsToFloat(E());
    }

    @Override // defpackage.AbstractC1010jl
    public int p() {
        return G();
    }

    @Override // defpackage.AbstractC1010jl
    public long q() {
        return H();
    }

    @Override // defpackage.AbstractC1010jl
    public int r() {
        return E();
    }

    @Override // defpackage.AbstractC1010jl
    public long s() {
        return F();
    }

    @Override // defpackage.AbstractC1010jl
    public int t() {
        return AbstractC1010jl.b(G());
    }

    @Override // defpackage.AbstractC1010jl
    public long u() {
        return AbstractC1010jl.c(H());
    }

    @Override // defpackage.AbstractC1010jl
    public String v() throws C0155Il {
        int iG = G();
        if (iG > 0 && iG <= this.g - this.i) {
            String str = new String(this.f, this.i, iG, AbstractC0119Gl.a);
            this.i += iG;
            return str;
        }
        if (iG == 0) {
            return "";
        }
        if (iG > this.g) {
            return new String(B(iG, false), AbstractC0119Gl.a);
        }
        K(iG);
        String str2 = new String(this.f, this.i, iG, AbstractC0119Gl.a);
        this.i += iG;
        return str2;
    }

    @Override // defpackage.AbstractC1010jl
    public String w() throws IOException {
        byte[] bArrB;
        byte[] bArr;
        int iG = G();
        int i = this.i;
        if (iG <= this.g - i && iG > 0) {
            bArr = this.f;
            this.i = i + iG;
        } else {
            if (iG == 0) {
                return "";
            }
            if (iG <= this.g) {
                K(iG);
                bArrB = this.f;
                this.i = iG + 0;
            } else {
                bArrB = B(iG, false);
            }
            bArr = bArrB;
            i = 0;
        }
        return AbstractC0174Jm.a.a(bArr, i, iG);
    }

    @Override // defpackage.AbstractC1010jl
    public int x() throws C0155Il {
        if (e()) {
            this.j = 0;
            return 0;
        }
        int iG = G();
        this.j = iG;
        if ((iG >>> 3) != 0) {
            return iG;
        }
        throw new C0155Il("Protocol message contained an invalid tag (zero).");
    }

    @Override // defpackage.AbstractC1010jl
    public int y() {
        return G();
    }

    @Override // defpackage.AbstractC1010jl
    public long z() {
        return H();
    }
}
