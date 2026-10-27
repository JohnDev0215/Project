package com.google.protobuf;

import p.dr2;
import p.k72;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class f extends h {
    public final byte[] j;
    public final int k;
    public int l;

    public f(byte[] bArr, int i) {
        if (((bArr.length - i) | i) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i)));
        }
        this.j = bArr;
        this.l = 0;
        this.k = i;
    }

    @Override // com.google.protobuf.h
    public final void R(byte b) throws CodedOutputStream$OutOfSpaceException {
        try {
            byte[] bArr = this.j;
            int i = this.l;
            this.l = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), 1), e);
        }
    }

    @Override // com.google.protobuf.h
    public final void S(int i, boolean z) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 0);
        R(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.protobuf.h
    public final void T(byte[] bArr, int i) throws CodedOutputStream$OutOfSpaceException {
        i0(i);
        m0(bArr, 0, i);
    }

    @Override // com.google.protobuf.h
    public final void U(int i, ByteString byteString) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 2);
        V(byteString);
    }

    @Override // com.google.protobuf.h
    public final void V(ByteString byteString) throws CodedOutputStream$OutOfSpaceException {
        i0(byteString.size());
        byteString.t(this);
    }

    @Override // com.google.protobuf.h
    public final void W(int i, int i2) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 5);
        X(i2);
    }

    @Override // com.google.protobuf.h
    public final void X(int i) throws CodedOutputStream$OutOfSpaceException {
        try {
            byte[] bArr = this.j;
            int i2 = this.l;
            bArr[i2] = (byte) (i & 255);
            bArr[i2 + 1] = (byte) ((i >> 8) & 255);
            bArr[i2 + 2] = (byte) ((i >> 16) & 255);
            this.l = i2 + 4;
            bArr[i2 + 3] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), 1), e);
        }
    }

    @Override // com.google.protobuf.h
    public final void Y(int i, long j) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 1);
        Z(j);
    }

    @Override // com.google.protobuf.h
    public final void Z(long j) throws CodedOutputStream$OutOfSpaceException {
        try {
            byte[] bArr = this.j;
            int i = this.l;
            bArr[i] = (byte) (((int) j) & 255);
            bArr[i + 1] = (byte) (((int) (j >> 8)) & 255);
            bArr[i + 2] = (byte) (((int) (j >> 16)) & 255);
            bArr[i + 3] = (byte) (((int) (j >> 24)) & 255);
            bArr[i + 4] = (byte) (((int) (j >> 32)) & 255);
            bArr[i + 5] = (byte) (((int) (j >> 40)) & 255);
            bArr[i + 6] = (byte) (((int) (j >> 48)) & 255);
            this.l = i + 8;
            bArr[i + 7] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), 1), e);
        }
    }

    @Override // com.google.protobuf.h
    public final void a0(int i, int i2) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 0);
        b0(i2);
    }

    @Override // com.google.protobuf.h
    public final void b0(int i) throws CodedOutputStream$OutOfSpaceException {
        if (i >= 0) {
            i0(i);
        } else {
            k0(i);
        }
    }

    @Override // com.google.protobuf.h
    public final void c0(int i, wj1 wj1Var, k72 k72Var) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 2);
        i0(((a) wj1Var).getSerializedSize(k72Var));
        k72Var.e(wj1Var, this.g);
    }

    @Override // com.google.protobuf.h
    public final void d0(wj1 wj1Var) throws CodedOutputStream$OutOfSpaceException {
        i0(wj1Var.getSerializedSize());
        wj1Var.writeTo(this);
    }

    @Override // com.google.protobuf.h
    public final void e0(int i, String str) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 2);
        f0(str);
    }

    @Override // com.google.protobuf.h
    public final void f0(String str) throws CodedOutputStream$OutOfSpaceException {
        int i = this.l;
        try {
            int iN = h.N(str.length() * 3);
            int iN2 = h.N(str.length());
            byte[] bArr = this.j;
            if (iN2 == iN) {
                int i2 = i + iN2;
                this.l = i2;
                int iW = r.a.w(str, bArr, i2, l0());
                this.l = i;
                i0((iW - i) - iN2);
                this.l = iW;
            } else {
                i0(r.b(str));
                this.l = r.a.w(str, bArr, this.l, l0());
            }
        } catch (Utf8$UnpairedSurrogateException e) {
            this.l = i;
            Q(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new CodedOutputStream$OutOfSpaceException(e2);
        }
    }

    @Override // com.google.protobuf.h
    public final void g0(int i, int i2) throws CodedOutputStream$OutOfSpaceException {
        i0((i << 3) | i2);
    }

    @Override // com.google.protobuf.h
    public final void h0(int i, int i2) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 0);
        i0(i2);
    }

    @Override // com.google.protobuf.h
    public final void i0(int i) throws CodedOutputStream$OutOfSpaceException {
        while (true) {
            int i2 = i & (-128);
            byte[] bArr = this.j;
            if (i2 == 0) {
                int i3 = this.l;
                this.l = i3 + 1;
                bArr[i3] = (byte) i;
                return;
            } else {
                try {
                    int i4 = this.l;
                    this.l = i4 + 1;
                    bArr[i4] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), 1), e);
                }
            }
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), 1), e);
        }
    }

    @Override // com.google.protobuf.h
    public final void j0(int i, long j) throws CodedOutputStream$OutOfSpaceException {
        g0(i, 0);
        k0(j);
    }

    @Override // com.google.protobuf.h
    public final void k0(long j) throws CodedOutputStream$OutOfSpaceException {
        boolean z = h.i;
        byte[] bArr = this.j;
        if (z && l0() >= 10) {
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
            try {
                int i3 = this.l;
                this.l = i3 + 1;
                bArr[i3] = (byte) ((((int) j) & 127) | 128);
                j >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), 1), e);
            }
        }
        int i4 = this.l;
        this.l = i4 + 1;
        bArr[i4] = (byte) j;
    }

    public final int l0() {
        return this.k - this.l;
    }

    public final void m0(byte[] bArr, int i, int i2) throws CodedOutputStream$OutOfSpaceException {
        try {
            System.arraycopy(bArr, i, this.j, this.l, i2);
            this.l += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.l), Integer.valueOf(this.k), Integer.valueOf(i2)), e);
        }
    }

    @Override // p.p02
    public final void t(byte[] bArr, int i, int i2) throws CodedOutputStream$OutOfSpaceException {
        m0(bArr, i, i2);
    }
}
