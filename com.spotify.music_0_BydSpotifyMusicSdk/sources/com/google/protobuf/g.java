package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import p.dr2;
import p.k72;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class g extends h {
    public final byte[] j;
    public final int k;
    public int l;
    public final OutputStream m;

    public g(OutputStream outputStream, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }
        int iMax = Math.max(i, 20);
        this.j = new byte[iMax];
        this.k = iMax;
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        this.m = outputStream;
    }

    @Override // com.google.protobuf.h
    public final void R(byte b) {
        if (this.l == this.k) {
            q0();
        }
        int i = this.l;
        this.l = i + 1;
        this.j[i] = b;
    }

    @Override // com.google.protobuf.h
    public final void S(int i, boolean z) {
        r0(11);
        n0(i, 0);
        byte b = z ? (byte) 1 : (byte) 0;
        int i2 = this.l;
        this.l = i2 + 1;
        this.j[i2] = b;
    }

    @Override // com.google.protobuf.h
    public final void T(byte[] bArr, int i) throws IOException {
        i0(i);
        s0(bArr, 0, i);
    }

    @Override // com.google.protobuf.h
    public final void U(int i, ByteString byteString) {
        g0(i, 2);
        V(byteString);
    }

    @Override // com.google.protobuf.h
    public final void V(ByteString byteString) {
        i0(byteString.size());
        byteString.t(this);
    }

    @Override // com.google.protobuf.h
    public final void W(int i, int i2) {
        r0(14);
        n0(i, 5);
        l0(i2);
    }

    @Override // com.google.protobuf.h
    public final void X(int i) {
        r0(4);
        l0(i);
    }

    @Override // com.google.protobuf.h
    public final void Y(int i, long j) {
        r0(18);
        n0(i, 1);
        m0(j);
    }

    @Override // com.google.protobuf.h
    public final void Z(long j) {
        r0(8);
        m0(j);
    }

    @Override // com.google.protobuf.h
    public final void a0(int i, int i2) {
        r0(20);
        n0(i, 0);
        if (i2 >= 0) {
            o0(i2);
        } else {
            p0(i2);
        }
    }

    @Override // com.google.protobuf.h
    public final void b0(int i) {
        if (i >= 0) {
            i0(i);
        } else {
            k0(i);
        }
    }

    @Override // com.google.protobuf.h
    public final void c0(int i, wj1 wj1Var, k72 k72Var) {
        g0(i, 2);
        i0(((a) wj1Var).getSerializedSize(k72Var));
        k72Var.e(wj1Var, this.g);
    }

    @Override // com.google.protobuf.h
    public final void d0(wj1 wj1Var) {
        i0(wj1Var.getSerializedSize());
        wj1Var.writeTo(this);
    }

    @Override // com.google.protobuf.h
    public final void e0(int i, String str) throws IOException {
        g0(i, 2);
        f0(str);
    }

    @Override // com.google.protobuf.h
    public final void f0(String str) throws IOException {
        try {
            int length = str.length() * 3;
            int iN = h.N(length);
            int i = iN + length;
            int i2 = this.k;
            if (i > i2) {
                byte[] bArr = new byte[length];
                int iW = r.a.w(str, bArr, 0, length);
                i0(iW);
                s0(bArr, 0, iW);
                return;
            }
            if (i > i2 - this.l) {
                q0();
            }
            int iN2 = h.N(str.length());
            int i3 = this.l;
            byte[] bArr2 = this.j;
            try {
                try {
                    if (iN2 == iN) {
                        int i4 = i3 + iN2;
                        this.l = i4;
                        int iW2 = r.a.w(str, bArr2, i4, i2 - i4);
                        this.l = i3;
                        o0((iW2 - i3) - iN2);
                        this.l = iW2;
                    } else {
                        int iB = r.b(str);
                        o0(iB);
                        this.l = r.a.w(str, bArr2, this.l, iB);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new CodedOutputStream$OutOfSpaceException(e);
                }
            } catch (Utf8$UnpairedSurrogateException e2) {
                this.l = i3;
                throw e2;
            }
        } catch (Utf8$UnpairedSurrogateException e3) {
            Q(str, e3);
        }
    }

    @Override // com.google.protobuf.h
    public final void g0(int i, int i2) {
        i0((i << 3) | i2);
    }

    @Override // com.google.protobuf.h
    public final void h0(int i, int i2) {
        r0(20);
        n0(i, 0);
        o0(i2);
    }

    @Override // com.google.protobuf.h
    public final void i0(int i) {
        r0(5);
        o0(i);
    }

    @Override // com.google.protobuf.h
    public final void j0(int i, long j) {
        r0(20);
        n0(i, 0);
        p0(j);
    }

    @Override // com.google.protobuf.h
    public final void k0(long j) {
        r0(10);
        p0(j);
    }

    public final void l0(int i) {
        int i2 = this.l;
        byte[] bArr = this.j;
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
        bArr[i2 + 2] = (byte) ((i >> 16) & 255);
        this.l = i2 + 4;
        bArr[i2 + 3] = (byte) ((i >> 24) & 255);
    }

    public final void m0(long j) {
        int i = this.l;
        byte[] bArr = this.j;
        bArr[i] = (byte) (j & 255);
        bArr[i + 1] = (byte) ((j >> 8) & 255);
        bArr[i + 2] = (byte) ((j >> 16) & 255);
        bArr[i + 3] = (byte) (255 & (j >> 24));
        bArr[i + 4] = (byte) (((int) (j >> 32)) & 255);
        bArr[i + 5] = (byte) (((int) (j >> 40)) & 255);
        bArr[i + 6] = (byte) (((int) (j >> 48)) & 255);
        this.l = i + 8;
        bArr[i + 7] = (byte) (((int) (j >> 56)) & 255);
    }

    public final void n0(int i, int i2) {
        o0((i << 3) | i2);
    }

    public final void o0(int i) {
        boolean z = h.i;
        byte[] bArr = this.j;
        if (z) {
            while ((i & (-128)) != 0) {
                int i2 = this.l;
                this.l = i2 + 1;
                dr2.o(bArr, i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            int i3 = this.l;
            this.l = i3 + 1;
            dr2.o(bArr, i3, (byte) i);
            return;
        }
        while ((i & (-128)) != 0) {
            int i4 = this.l;
            this.l = i4 + 1;
            bArr[i4] = (byte) ((i & 127) | 128);
            i >>>= 7;
        }
        int i5 = this.l;
        this.l = i5 + 1;
        bArr[i5] = (byte) i;
    }

    public final void p0(long j) {
        boolean z = h.i;
        byte[] bArr = this.j;
        if (z) {
            while ((j & (-128)) != 0) {
                int i = this.l;
                this.l = i + 1;
                dr2.o(bArr, i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            int i2 = this.l;
            this.l = i2 + 1;
            dr2.o(bArr, i2, (byte) j);
            return;
        }
        while ((j & (-128)) != 0) {
            int i3 = this.l;
            this.l = i3 + 1;
            bArr[i3] = (byte) ((((int) j) & 127) | 128);
            j >>>= 7;
        }
        int i4 = this.l;
        this.l = i4 + 1;
        bArr[i4] = (byte) j;
    }

    public final void q0() {
        this.m.write(this.j, 0, this.l);
        this.l = 0;
    }

    public final void r0(int i) {
        if (this.k - this.l < i) {
            q0();
        }
    }

    public final void s0(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.l;
        int i4 = this.k;
        int i5 = i4 - i3;
        byte[] bArr2 = this.j;
        if (i5 >= i2) {
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.l += i2;
            return;
        }
        System.arraycopy(bArr, i, bArr2, i3, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.l = i4;
        q0();
        if (i7 > i4) {
            this.m.write(bArr, i6, i7);
        } else {
            System.arraycopy(bArr, i6, bArr2, 0, i7);
            this.l = i7;
        }
    }

    @Override // p.p02
    public final void t(byte[] bArr, int i, int i2) throws IOException {
        s0(bArr, i, i2);
    }
}
