package p6;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/* JADX INFO: compiled from: SegmentedByteString.kt */
/* JADX INFO: loaded from: classes.dex */
public final class x extends h {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final transient byte[][] f8914k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final transient int[] f8915l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(byte[][] segments, int[] directory) {
        super(h.f8869i.e());
        kotlin.jvm.internal.l.e(segments, "segments");
        kotlin.jvm.internal.l.e(directory, "directory");
        this.f8914k = segments;
        this.f8915l = directory;
    }

    private final Object writeReplace() {
        h hVarZ = z();
        Objects.requireNonNull(hVarZ, "null cannot be cast to non-null type java.lang.Object");
        return hVarZ;
    }

    private final h z() {
        return new h(y());
    }

    @Override // p6.h
    public String a() {
        return z().a();
    }

    @Override // p6.h
    public h c(String algorithm) throws NoSuchAlgorithmException {
        kotlin.jvm.internal.l.e(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        int length = x().length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int i9 = w()[length + i7];
            int i10 = w()[i7];
            messageDigest.update(x()[i7], i9, i10 - i8);
            i7++;
            i8 = i10;
        }
        byte[] bArrDigest = messageDigest.digest();
        kotlin.jvm.internal.l.d(bArrDigest, "digest.digest()");
        return new h(bArrDigest);
    }

    @Override // p6.h
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (hVar.r() == r() && l(0, hVar, 0, r())) {
                return true;
            }
        }
        return false;
    }

    @Override // p6.h
    public int g() {
        return w()[x().length - 1];
    }

    @Override // p6.h
    public int hashCode() {
        int iF = f();
        if (iF != 0) {
            return iF;
        }
        int length = x().length;
        int i7 = 0;
        int i8 = 1;
        int i9 = 0;
        while (i7 < length) {
            int i10 = w()[length + i7];
            int i11 = w()[i7];
            byte[] bArr = x()[i7];
            int i12 = (i11 - i9) + i10;
            while (i10 < i12) {
                i8 = (i8 * 31) + bArr[i10];
                i10++;
            }
            i7++;
            i9 = i11;
        }
        n(i8);
        return i8;
    }

    @Override // p6.h
    public String i() {
        return z().i();
    }

    @Override // p6.h
    public byte[] j() {
        return y();
    }

    @Override // p6.h
    public byte k(int i7) {
        c.b(w()[x().length - 1], i7, 1L);
        int iB = q6.c.b(this, i7);
        return x()[iB][(i7 - (iB == 0 ? 0 : w()[iB - 1])) + w()[x().length + iB]];
    }

    @Override // p6.h
    public boolean l(int i7, h other, int i8, int i9) {
        kotlin.jvm.internal.l.e(other, "other");
        if (i7 < 0 || i7 > r() - i9) {
            return false;
        }
        int i10 = i9 + i7;
        int iB = q6.c.b(this, i7);
        while (i7 < i10) {
            int i11 = iB == 0 ? 0 : w()[iB - 1];
            int i12 = w()[iB] - i11;
            int i13 = w()[x().length + iB];
            int iMin = Math.min(i10, i12 + i11) - i7;
            if (!other.m(i8, x()[iB], i13 + (i7 - i11), iMin)) {
                return false;
            }
            i8 += iMin;
            i7 += iMin;
            iB++;
        }
        return true;
    }

    @Override // p6.h
    public boolean m(int i7, byte[] other, int i8, int i9) {
        kotlin.jvm.internal.l.e(other, "other");
        if (i7 < 0 || i7 > r() - i9 || i8 < 0 || i8 > other.length - i9) {
            return false;
        }
        int i10 = i9 + i7;
        int iB = q6.c.b(this, i7);
        while (i7 < i10) {
            int i11 = iB == 0 ? 0 : w()[iB - 1];
            int i12 = w()[iB] - i11;
            int i13 = w()[x().length + iB];
            int iMin = Math.min(i10, i12 + i11) - i7;
            if (!c.a(x()[iB], i13 + (i7 - i11), other, i8, iMin)) {
                return false;
            }
            i8 += iMin;
            i7 += iMin;
            iB++;
        }
        return true;
    }

    @Override // p6.h
    public h t() {
        return z().t();
    }

    @Override // p6.h
    public String toString() {
        return z().toString();
    }

    @Override // p6.h
    public void v(e buffer, int i7, int i8) {
        kotlin.jvm.internal.l.e(buffer, "buffer");
        int i9 = i8 + i7;
        int iB = q6.c.b(this, i7);
        while (i7 < i9) {
            int i10 = iB == 0 ? 0 : w()[iB - 1];
            int i11 = w()[iB] - i10;
            int i12 = w()[x().length + iB];
            int iMin = Math.min(i9, i11 + i10) - i7;
            int i13 = i12 + (i7 - i10);
            v vVar = new v(x()[iB], i13, i13 + iMin, true, false);
            v vVar2 = buffer.f8866e;
            if (vVar2 == null) {
                vVar.f8908g = vVar;
                vVar.f8907f = vVar;
                buffer.f8866e = vVar;
            } else {
                kotlin.jvm.internal.l.c(vVar2);
                v vVar3 = vVar2.f8908g;
                kotlin.jvm.internal.l.c(vVar3);
                vVar3.c(vVar);
            }
            i7 += iMin;
            iB++;
        }
        buffer.i0(buffer.j0() + ((long) r()));
    }

    public final int[] w() {
        return this.f8915l;
    }

    public final byte[][] x() {
        return this.f8914k;
    }

    public byte[] y() {
        byte[] bArr = new byte[r()];
        int length = x().length;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < length) {
            int i10 = w()[length + i7];
            int i11 = w()[i7];
            int i12 = i11 - i8;
            h5.k.c(x()[i7], bArr, i9, i10, i10 + i12);
            i9 += i12;
            i7++;
            i8 = i11;
        }
        return bArr;
    }
}
