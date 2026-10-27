package r9;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class G extends C2322l {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient byte[][] f21906p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int[] f21907q;

    public G(byte[][] bArr, int[] iArr) {
        super(C2322l.o.l);
        this.f21906p = bArr;
        this.f21907q = iArr;
    }

    @Override // r9.C2322l
    public final String a() {
        throw null;
    }

    @Override // r9.C2322l
    public final C2322l c(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        byte[][] bArr = this.f21906p;
        int length = bArr.length;
        int i7 = 0;
        int i10 = 0;
        while (i7 < length) {
            int[] iArr = this.f21907q;
            int i11 = iArr[length + i7];
            int i12 = iArr[i7];
            messageDigest.update(bArr[i7], i11, i12 - i10);
            i7++;
            i10 = i12;
        }
        byte[] bArrDigest = messageDigest.digest();
        A7.m.c(bArrDigest);
        return new C2322l(bArrDigest);
    }

    @Override // r9.C2322l
    public final int d() {
        return this.f21907q[this.f21906p.length - 1];
    }

    @Override // r9.C2322l
    public final String e() {
        return t().e();
    }

    @Override // r9.C2322l
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2322l) {
            C2322l c2322l = (C2322l) obj;
            if (c2322l.d() == d() && m(0, c2322l, d())) {
                return true;
            }
        }
        return false;
    }

    @Override // r9.C2322l
    public final int f(int i7, byte[] bArr) {
        A7.m.f(bArr, "other");
        return t().f(i7, bArr);
    }

    @Override // r9.C2322l
    public final byte[] h() {
        return q();
    }

    @Override // r9.C2322l
    public final int hashCode() {
        int i7 = this.f21933m;
        if (i7 != 0) {
            return i7;
        }
        byte[][] bArr = this.f21906p;
        int length = bArr.length;
        int i10 = 0;
        int i11 = 1;
        int i12 = 0;
        while (i10 < length) {
            int[] iArr = this.f21907q;
            int i13 = iArr[length + i10];
            int i14 = iArr[i10];
            byte[] bArr2 = bArr[i10];
            int i15 = (i14 - i12) + i13;
            while (i13 < i15) {
                i11 = (i11 * 31) + bArr2[i13];
                i13++;
            }
            i10++;
            i12 = i14;
        }
        this.f21933m = i11;
        return i11;
    }

    @Override // r9.C2322l
    public final byte i(int i7) {
        byte[][] bArr = this.f21906p;
        int length = bArr.length - 1;
        int[] iArr = this.f21907q;
        AbstractC2312b.e(iArr[length], i7, 1L);
        int iG = s9.b.g(this, i7);
        return bArr[iG][(i7 - (iG == 0 ? 0 : iArr[iG - 1])) + iArr[bArr.length + iG]];
    }

    @Override // r9.C2322l
    public final int j(int i7, byte[] bArr) {
        A7.m.f(bArr, "other");
        return t().j(i7, bArr);
    }

    @Override // r9.C2322l
    public final boolean l(int i7, int i10, int i11, byte[] bArr) {
        A7.m.f(bArr, "other");
        if (i7 < 0 || i7 > d() - i11 || i10 < 0 || i10 > bArr.length - i11) {
            return false;
        }
        int i12 = i11 + i7;
        int iG = s9.b.g(this, i7);
        while (i7 < i12) {
            int[] iArr = this.f21907q;
            int i13 = iG == 0 ? 0 : iArr[iG - 1];
            int i14 = iArr[iG] - i13;
            byte[][] bArr2 = this.f21906p;
            int i15 = iArr[bArr2.length + iG];
            int iMin = Math.min(i12, i14 + i13) - i7;
            if (!AbstractC2312b.a((i7 - i13) + i15, i10, iMin, bArr2[iG], bArr)) {
                return false;
            }
            i10 += iMin;
            i7 += iMin;
            iG++;
        }
        return true;
    }

    @Override // r9.C2322l
    public final boolean m(int i7, C2322l c2322l, int i10) {
        A7.m.f(c2322l, "other");
        if (i7 < 0 || i7 > d() - i10) {
            return false;
        }
        int i11 = i10 + i7;
        int iG = s9.b.g(this, i7);
        int i12 = 0;
        while (i7 < i11) {
            int[] iArr = this.f21907q;
            int i13 = iG == 0 ? 0 : iArr[iG - 1];
            int i14 = iArr[iG] - i13;
            byte[][] bArr = this.f21906p;
            int i15 = iArr[bArr.length + iG];
            int iMin = Math.min(i11, i14 + i13) - i7;
            if (!c2322l.l(i12, (i7 - i13) + i15, iMin, bArr[iG])) {
                return false;
            }
            i12 += iMin;
            i7 += iMin;
            iG++;
        }
        return true;
    }

    @Override // r9.C2322l
    public final C2322l n(int i7, int i10) {
        if (i10 == -1234567890) {
            i10 = d();
        }
        if (i7 < 0) {
            throw new IllegalArgumentException(A7.k.g("beginIndex=", i7, " < 0").toString());
        }
        if (i10 > d()) {
            StringBuilder sbJ = M5.b.j("endIndex=", i10, " > length(");
            sbJ.append(d());
            sbJ.append(')');
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        int i11 = i10 - i7;
        if (i11 < 0) {
            throw new IllegalArgumentException(A7.k.i("endIndex=", " < beginIndex=", i10, i7).toString());
        }
        if (i7 == 0 && i10 == d()) {
            return this;
        }
        if (i7 == i10) {
            return C2322l.o;
        }
        int iG = s9.b.g(this, i7);
        int iG2 = s9.b.g(this, i10 - 1);
        byte[][] bArr = this.f21906p;
        byte[][] bArr2 = (byte[][]) AbstractC1996k.I(bArr, iG, iG2 + 1);
        int[] iArr = new int[bArr2.length * 2];
        int[] iArr2 = this.f21907q;
        if (iG <= iG2) {
            int i12 = iG;
            int i13 = 0;
            while (true) {
                iArr[i13] = Math.min(iArr2[i12] - i7, i11);
                int i14 = i13 + 1;
                iArr[i13 + bArr2.length] = iArr2[bArr.length + i12];
                if (i12 == iG2) {
                    break;
                }
                i12++;
                i13 = i14;
            }
        }
        int i15 = iG != 0 ? iArr2[iG - 1] : 0;
        int length = bArr2.length;
        iArr[length] = (i7 - i15) + iArr[length];
        return new G(bArr2, iArr);
    }

    @Override // r9.C2322l
    public final C2322l p() {
        return t().p();
    }

    @Override // r9.C2322l
    public final byte[] q() {
        byte[] bArr = new byte[d()];
        byte[][] bArr2 = this.f21906p;
        int length = bArr2.length;
        int i7 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i7 < length) {
            int[] iArr = this.f21907q;
            int i12 = iArr[length + i7];
            int i13 = iArr[i7];
            int i14 = i13 - i10;
            AbstractC1996k.B(i11, i12, i12 + i14, bArr2[i7], bArr);
            i11 += i14;
            i7++;
            i10 = i13;
        }
        return bArr;
    }

    @Override // r9.C2322l
    public final void s(C2319i c2319i, int i7) {
        A7.m.f(c2319i, "buffer");
        int iG = s9.b.g(this, 0);
        int i10 = 0;
        while (i10 < i7) {
            int[] iArr = this.f21907q;
            int i11 = iG == 0 ? 0 : iArr[iG - 1];
            int i12 = iArr[iG] - i11;
            byte[][] bArr = this.f21906p;
            int i13 = iArr[bArr.length + iG];
            int iMin = Math.min(i7, i12 + i11) - i10;
            int i14 = (i10 - i11) + i13;
            E e10 = new E(bArr[iG], i14, i14 + iMin, true, false);
            E e11 = c2319i.l;
            if (e11 == null) {
                e10.f21903g = e10;
                e10.f21902f = e10;
                c2319i.l = e10;
            } else {
                E e12 = e11.f21903g;
                A7.m.c(e12);
                e12.b(e10);
            }
            i10 += iMin;
            iG++;
        }
        c2319i.f21932m += (long) i7;
    }

    public final C2322l t() {
        return new C2322l(q());
    }

    @Override // r9.C2322l
    public final String toString() {
        return t().toString();
    }
}
