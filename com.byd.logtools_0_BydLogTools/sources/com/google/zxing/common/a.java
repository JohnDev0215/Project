package com.google.zxing.common;

import java.util.Arrays;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: BitArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int[] f4193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4194c;

    public a() {
        this.f4194c = 0;
        this.f4193b = new int[1];
    }

    private void g(int i) {
        if (i > (this.f4193b.length << 5)) {
            int[] iArrO = o(i);
            int[] iArr = this.f4193b;
            System.arraycopy(iArr, 0, iArrO, 0, iArr.length);
            this.f4193b = iArrO;
        }
    }

    private static int[] o(int i) {
        return new int[(i + 31) / 32];
    }

    public void a(boolean z) {
        g(this.f4194c + 1);
        if (z) {
            int[] iArr = this.f4193b;
            int i = this.f4194c;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.f4194c++;
    }

    public void b(a aVar) {
        int i = aVar.f4194c;
        g(this.f4194c + i);
        for (int i2 = 0; i2 < i; i2++) {
            a(aVar.h(i2));
        }
    }

    public void c(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        g(this.f4194c + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            a(z);
            i2--;
        }
    }

    public void d() {
        int length = this.f4193b.length;
        for (int i = 0; i < length; i++) {
            this.f4193b[i] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f4194c == aVar.f4194c && Arrays.equals(this.f4193b, aVar.f4193b);
    }

    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a clone() {
        return new a((int[]) this.f4193b.clone(), this.f4194c);
    }

    public boolean h(int i) {
        return ((1 << (i & 31)) & this.f4193b[i / 32]) != 0;
    }

    public int hashCode() {
        return (this.f4194c * 31) + Arrays.hashCode(this.f4193b);
    }

    public int[] i() {
        return this.f4193b;
    }

    public int j(int i) {
        int i2 = this.f4194c;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (~((1 << (i & 31)) - 1)) & this.f4193b[i3];
        while (i4 == 0) {
            i3++;
            int[] iArr = this.f4193b;
            if (i3 == iArr.length) {
                return this.f4194c;
            }
            i4 = iArr[i3];
        }
        int iNumberOfTrailingZeros = (i3 << 5) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.f4194c;
        return iNumberOfTrailingZeros > i5 ? i5 : iNumberOfTrailingZeros;
    }

    public int k(int i) {
        int i2 = this.f4194c;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (~((1 << (i & 31)) - 1)) & (~this.f4193b[i3]);
        while (i4 == 0) {
            i3++;
            int[] iArr = this.f4193b;
            if (i3 == iArr.length) {
                return this.f4194c;
            }
            i4 = ~iArr[i3];
        }
        int iNumberOfTrailingZeros = (i3 << 5) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.f4194c;
        return iNumberOfTrailingZeros > i5 ? i5 : iNumberOfTrailingZeros;
    }

    public int l() {
        return this.f4194c;
    }

    public int m() {
        return (this.f4194c + 7) / 8;
    }

    public boolean n(int i, int i2, boolean z) {
        if (i2 < i || i < 0 || i2 > this.f4194c) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return true;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            int i7 = (2 << (i6 >= i5 ? 31 & i3 : 31)) - (1 << (i6 > i4 ? 0 : i & 31));
            int i8 = this.f4193b[i6] & i7;
            if (!z) {
                i7 = 0;
            }
            if (i8 != i7) {
                return false;
            }
            i6++;
        }
        return true;
    }

    public void p() {
        int[] iArr = new int[this.f4193b.length];
        int i = (this.f4194c - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = this.f4193b[i3];
            long j2 = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i - i3] = (int) (((j5 & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((j5 >> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        }
        int i4 = this.f4194c;
        int i5 = i2 << 5;
        if (i4 != i5) {
            int i6 = i5 - i4;
            int i7 = iArr[0] >>> i6;
            for (int i8 = 1; i8 < i2; i8++) {
                int i9 = iArr[i8];
                iArr[i8 - 1] = i7 | (i9 << (32 - i6));
                i7 = i9 >>> i6;
            }
            iArr[i2 - 1] = i7;
        }
        this.f4193b = iArr;
    }

    public void q(int i) {
        int[] iArr = this.f4193b;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void r(int i, int i2) {
        this.f4193b[i / 32] = i2;
    }

    public void s(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                if (h(i)) {
                    i5 |= 1 << (7 - i6);
                }
                i++;
            }
            bArr[i2 + i4] = (byte) i5;
        }
    }

    public void t(a aVar) {
        if (this.f4194c != aVar.f4194c) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.f4193b;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = iArr[i] ^ aVar.f4193b[i];
            i++;
        }
    }

    public String toString() {
        int i = this.f4194c;
        StringBuilder sb = new StringBuilder(i + (i / 8) + 1);
        for (int i2 = 0; i2 < this.f4194c; i2++) {
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(h(i2) ? 'X' : '.');
        }
        return sb.toString();
    }

    public a(int i) {
        this.f4194c = i;
        this.f4193b = o(i);
    }

    a(int[] iArr, int i) {
        this.f4193b = iArr;
        this.f4194c = i;
    }
}
