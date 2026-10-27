package com.google.zxing.common.reedsolomon;

/* JADX INFO: compiled from: GenericGFPoly.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f4242a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f4243b;

    b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f4242a = aVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.f4243b = iArr;
            return;
        }
        int i = 1;
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.f4243b = new int[]{0};
            return;
        }
        int[] iArr2 = new int[length - i];
        this.f4243b = iArr2;
        System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
    }

    b a(b bVar) {
        if (!this.f4242a.equals(bVar.f4242a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (g()) {
            return bVar;
        }
        if (bVar.g()) {
            return this;
        }
        int[] iArr = this.f4243b;
        int[] iArr2 = bVar.f4243b;
        if (iArr.length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length);
        for (int i = length; i < iArr.length; i++) {
            iArr3[i] = a.a(iArr2[i - length], iArr[i]);
        }
        return new b(this.f4242a, iArr3);
    }

    b[] b(b bVar) {
        if (!this.f4242a.equals(bVar.f4242a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (bVar.g()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        b bVarG = this.f4242a.g();
        int iH = this.f4242a.h(bVar.d(bVar.f()));
        b bVarA = this;
        while (bVarA.f() >= bVar.f() && !bVarA.g()) {
            int iF = bVarA.f() - bVar.f();
            int iJ = this.f4242a.j(bVarA.d(bVarA.f()), iH);
            b bVarJ = bVar.j(iF, iJ);
            bVarG = bVarG.a(this.f4242a.b(iF, iJ));
            bVarA = bVarA.a(bVarJ);
        }
        return new b[]{bVarG, bVarA};
    }

    int c(int i) {
        if (i == 0) {
            return d(0);
        }
        if (i == 1) {
            int iA = 0;
            for (int i2 : this.f4243b) {
                iA = a.a(iA, i2);
            }
            return iA;
        }
        int[] iArr = this.f4243b;
        int iA2 = iArr[0];
        int length = iArr.length;
        for (int i3 = 1; i3 < length; i3++) {
            iA2 = a.a(this.f4242a.j(i, iA2), this.f4243b[i3]);
        }
        return iA2;
    }

    int d(int i) {
        return this.f4243b[(r0.length - 1) - i];
    }

    int[] e() {
        return this.f4243b;
    }

    int f() {
        return this.f4243b.length - 1;
    }

    boolean g() {
        return this.f4243b[0] == 0;
    }

    b h(int i) {
        if (i == 0) {
            return this.f4242a.g();
        }
        if (i == 1) {
            return this;
        }
        int length = this.f4243b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f4242a.j(this.f4243b[i2], i);
        }
        return new b(this.f4242a, iArr);
    }

    b i(b bVar) {
        if (!this.f4242a.equals(bVar.f4242a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (g() || bVar.g()) {
            return this.f4242a.g();
        }
        int[] iArr = this.f4243b;
        int length = iArr.length;
        int[] iArr2 = bVar.f4243b;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            for (int i3 = 0; i3 < length2; i3++) {
                int i4 = i + i3;
                iArr3[i4] = a.a(iArr3[i4], this.f4242a.j(i2, iArr2[i3]));
            }
        }
        return new b(this.f4242a, iArr3);
    }

    b j(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f4242a.g();
        }
        int length = this.f4243b.length;
        int[] iArr = new int[i + length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.f4242a.j(this.f4243b[i3], i2);
        }
        return new b(this.f4242a, iArr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(f() * 8);
        for (int iF = f(); iF >= 0; iF--) {
            int iD = d(iF);
            if (iD != 0) {
                if (iD < 0) {
                    sb.append(" - ");
                    iD = -iD;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (iF == 0 || iD != 1) {
                    int i = this.f4242a.i(iD);
                    if (i == 0) {
                        sb.append('1');
                    } else if (i == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(i);
                    }
                }
                if (iF != 0) {
                    if (iF == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(iF);
                    }
                }
            }
        }
        return sb.toString();
    }
}
