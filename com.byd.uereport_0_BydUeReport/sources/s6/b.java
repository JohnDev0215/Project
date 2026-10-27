package s6;

/* JADX INFO: compiled from: GenericGFPoly.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f7935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f7936b;

    public b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f7935a = aVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.f7936b = iArr;
            return;
        }
        int i8 = 1;
        while (i8 < length && iArr[i8] == 0) {
            i8++;
        }
        if (i8 == length) {
            this.f7936b = new int[]{0};
            return;
        }
        int[] iArr2 = new int[length - i8];
        this.f7936b = iArr2;
        System.arraycopy(iArr, i8, iArr2, 0, iArr2.length);
    }

    public b a(b bVar) {
        if (!this.f7935a.equals(bVar.f7935a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (f()) {
            return bVar;
        }
        if (bVar.f()) {
            return this;
        }
        int[] iArr = this.f7936b;
        int[] iArr2 = bVar.f7936b;
        if (iArr.length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length);
        for (int i8 = length; i8 < iArr.length; i8++) {
            iArr3[i8] = a.a(iArr2[i8 - length], iArr[i8]);
        }
        return new b(this.f7935a, iArr3);
    }

    public b[] b(b bVar) {
        if (!this.f7935a.equals(bVar.f7935a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (bVar.f()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        b bVarE = this.f7935a.e();
        int iF = this.f7935a.f(bVar.c(bVar.e()));
        b bVarA = this;
        while (bVarA.e() >= bVar.e() && !bVarA.f()) {
            int iE = bVarA.e() - bVar.e();
            int iH = this.f7935a.h(bVarA.c(bVarA.e()), iF);
            b bVarH = bVar.h(iE, iH);
            bVarE = bVarE.a(this.f7935a.b(iE, iH));
            bVarA = bVarA.a(bVarH);
        }
        return new b[]{bVarE, bVarA};
    }

    public int c(int i8) {
        return this.f7936b[(r0.length - 1) - i8];
    }

    public int[] d() {
        return this.f7936b;
    }

    public int e() {
        return this.f7936b.length - 1;
    }

    public boolean f() {
        return this.f7936b[0] == 0;
    }

    public b g(b bVar) {
        if (!this.f7935a.equals(bVar.f7935a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (f() || bVar.f()) {
            return this.f7935a.e();
        }
        int[] iArr = this.f7936b;
        int length = iArr.length;
        int[] iArr2 = bVar.f7936b;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = iArr[i8];
            for (int i10 = 0; i10 < length2; i10++) {
                int i11 = i8 + i10;
                iArr3[i11] = a.a(iArr3[i11], this.f7935a.h(i9, iArr2[i10]));
            }
        }
        return new b(this.f7935a, iArr3);
    }

    public b h(int i8, int i9) {
        if (i8 < 0) {
            throw new IllegalArgumentException();
        }
        if (i9 == 0) {
            return this.f7935a.e();
        }
        int length = this.f7936b.length;
        int[] iArr = new int[i8 + length];
        for (int i10 = 0; i10 < length; i10++) {
            iArr[i10] = this.f7935a.h(this.f7936b[i10], i9);
        }
        return new b(this.f7935a, iArr);
    }

    public String toString() {
        if (f()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(e() * 8);
        for (int iE = e(); iE >= 0; iE--) {
            int iC = c(iE);
            if (iC != 0) {
                if (iC < 0) {
                    if (iE == e()) {
                        sb.append("-");
                    } else {
                        sb.append(" - ");
                    }
                    iC = -iC;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (iE == 0 || iC != 1) {
                    int iG = this.f7935a.g(iC);
                    if (iG == 0) {
                        sb.append('1');
                    } else if (iG == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(iG);
                    }
                }
                if (iE != 0) {
                    if (iE == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(iE);
                    }
                }
            }
        }
        return sb.toString();
    }
}
