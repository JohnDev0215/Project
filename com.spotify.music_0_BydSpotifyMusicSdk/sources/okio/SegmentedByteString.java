package okio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import p.bb2;
import p.fo;
import p.hf2;
import p.q30;
import p.rb;
import p.u4;
import p.v03;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class SegmentedByteString extends ByteString {
    public final transient byte[][] l;
    public final transient int[] m;

    public SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.k.d());
        this.l = bArr;
        this.m = iArr;
    }

    private final Object writeReplace() {
        return v();
    }

    @Override // okio.ByteString
    public final String a() {
        throw null;
    }

    @Override // okio.ByteString
    public final ByteString c(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        byte[][] bArr = this.l;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.m;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            messageDigest.update(bArr[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        byte[] bArrDigest = messageDigest.digest();
        v03.h(bArrDigest);
        return new ByteString(bArrDigest);
    }

    @Override // okio.ByteString
    public final int e() {
        return this.m[this.l.length - 1];
    }

    @Override // okio.ByteString
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.e() == e() && o(0, byteString, e())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public final String f() {
        return v().f();
    }

    @Override // okio.ByteString
    public final int h(byte[] bArr, int i) {
        v03.j(bArr, "other");
        return v().h(bArr, i);
    }

    @Override // okio.ByteString
    public final int hashCode() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        byte[][] bArr = this.l;
        int length = bArr.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            int[] iArr = this.m;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            byte[] bArr2 = bArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr2[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.c = i3;
        return i3;
    }

    @Override // okio.ByteString
    public final byte[] j() {
        return u();
    }

    @Override // okio.ByteString
    public final byte k(int i) {
        byte[][] bArr = this.l;
        int length = bArr.length - 1;
        int[] iArr = this.m;
        q30.i(iArr[length], i, 1L);
        int iC0 = zu.C0(this, i);
        return bArr[iC0][(i - (iC0 == 0 ? 0 : iArr[iC0 - 1])) + iArr[bArr.length + iC0]];
    }

    @Override // okio.ByteString
    public final int l(byte[] bArr, int i) {
        v03.j(bArr, "other");
        return v().l(bArr, i);
    }

    @Override // okio.ByteString
    public final boolean n(int i, int i2, int i3, byte[] bArr) {
        v03.j(bArr, "other");
        if (i < 0 || i > e() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iC0 = zu.C0(this, i);
        while (i < i4) {
            int[] iArr = this.m;
            int i5 = iC0 == 0 ? 0 : iArr[iC0 - 1];
            int i6 = iArr[iC0] - i5;
            byte[][] bArr2 = this.l;
            int i7 = iArr[bArr2.length + iC0];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!q30.c((i - i5) + i7, i2, iMin, bArr2[iC0], bArr)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iC0++;
        }
        return true;
    }

    @Override // okio.ByteString
    public final boolean o(int i, ByteString byteString, int i2) {
        v03.j(byteString, "other");
        if (i < 0 || i > e() - i2) {
            return false;
        }
        int i3 = i2 + i;
        int iC0 = zu.C0(this, i);
        int i4 = 0;
        while (i < i3) {
            int[] iArr = this.m;
            int i5 = iC0 == 0 ? 0 : iArr[iC0 - 1];
            int i6 = iArr[iC0] - i5;
            byte[][] bArr = this.l;
            int i7 = iArr[bArr.length + iC0];
            int iMin = Math.min(i3, i6 + i5) - i;
            if (!byteString.n(i4, (i - i5) + i7, iMin, bArr[iC0])) {
                return false;
            }
            i4 += iMin;
            i += iMin;
            iC0++;
        }
        return true;
    }

    @Override // okio.ByteString
    public final ByteString p(int i, int i2) {
        int iS = q30.s(i2, this);
        if (i < 0) {
            throw new IllegalArgumentException(u4.f("beginIndex=", i, " < 0").toString());
        }
        if (iS > e()) {
            StringBuilder sbO = u4.o("endIndex=", iS, " > length(");
            sbO.append(e());
            sbO.append(')');
            throw new IllegalArgumentException(sbO.toString().toString());
        }
        int i3 = iS - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(hf2.c("endIndex=", iS, " < beginIndex=", i).toString());
        }
        if (i == 0 && iS == e()) {
            return this;
        }
        if (i == iS) {
            return ByteString.k;
        }
        int iC0 = zu.C0(this, i);
        int iC02 = zu.C0(this, iS - 1);
        byte[][] bArr = this.l;
        byte[][] bArr2 = (byte[][]) rb.n0(iC0, iC02 + 1, bArr);
        int[] iArr = new int[bArr2.length * 2];
        int[] iArr2 = this.m;
        if (iC0 <= iC02) {
            int i4 = iC0;
            int i5 = 0;
            while (true) {
                iArr[i5] = Math.min(iArr2[i4] - i, i3);
                int i6 = i5 + 1;
                iArr[i5 + bArr2.length] = iArr2[bArr.length + i4];
                if (i4 == iC02) {
                    break;
                }
                i4++;
                i5 = i6;
            }
        }
        int i7 = iC0 != 0 ? iArr2[iC0 - 1] : 0;
        int length = bArr2.length;
        iArr[length] = (i - i7) + iArr[length];
        return new SegmentedByteString(bArr2, iArr);
    }

    @Override // okio.ByteString
    public final ByteString r() {
        return v().r();
    }

    @Override // okio.ByteString
    public final void t(fo foVar, int i) {
        v03.j(foVar, "buffer");
        int iC0 = zu.C0(this, 0);
        int i2 = 0;
        while (i2 < i) {
            int[] iArr = this.m;
            int i3 = iC0 == 0 ? 0 : iArr[iC0 - 1];
            int i4 = iArr[iC0] - i3;
            byte[][] bArr = this.l;
            int i5 = iArr[bArr.length + iC0];
            int iMin = Math.min(i, i4 + i3) - i2;
            int i6 = (i2 - i3) + i5;
            bb2 bb2Var = new bb2(bArr[iC0], i6, i6 + iMin, true, false);
            bb2 bb2Var2 = foVar.c;
            if (bb2Var2 == null) {
                bb2Var.g = bb2Var;
                bb2Var.f = bb2Var;
                foVar.c = bb2Var;
            } else {
                bb2 bb2Var3 = bb2Var2.g;
                v03.h(bb2Var3);
                bb2Var3.b(bb2Var);
            }
            i2 += iMin;
            iC0++;
        }
        foVar.e += (long) i;
    }

    @Override // okio.ByteString
    public final String toString() {
        return v().toString();
    }

    public final byte[] u() {
        byte[] bArr = new byte[e()];
        byte[][] bArr2 = this.l;
        int length = bArr2.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int[] iArr = this.m;
            int i4 = iArr[length + i];
            int i5 = iArr[i];
            int i6 = i5 - i2;
            rb.i0(i3, i4, i4 + i6, bArr2[i], bArr);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    public final ByteString v() {
        return new ByteString(u());
    }
}
