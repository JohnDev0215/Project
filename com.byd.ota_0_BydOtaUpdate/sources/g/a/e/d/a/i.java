package g.a.e.d.a;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f5714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5715b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f5716c;

    public i(b bVar, int i2) {
        this.f5714a = bVar;
        this.f5715b = i2;
        int[] iArr = new int[i2 + 1];
        this.f5716c = iArr;
        iArr[i2] = 1;
    }

    public i(b bVar, byte[] bArr) {
        this.f5714a = bVar;
        int i2 = 8;
        int i3 = 1;
        while (bVar.d() > i2) {
            i3++;
            i2 += 8;
        }
        if (bArr.length % i3 != 0) {
            throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
        }
        this.f5716c = new int[bArr.length / i3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = this.f5716c;
            if (i4 >= iArr.length) {
                if (iArr.length != 1 && iArr[iArr.length - 1] == 0) {
                    throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
                }
                d();
                return;
            }
            int i6 = 0;
            while (i6 < i2) {
                int[] iArr2 = this.f5716c;
                iArr2[i4] = ((bArr[i5] & 255) << i6) ^ iArr2[i4];
                i6 += 8;
                i5++;
            }
            if (!this.f5714a.g(this.f5716c[i4])) {
                throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
            }
            i4++;
        }
    }

    public i(b bVar, int[] iArr) {
        this.f5714a = bVar;
        this.f5716c = q(iArr);
        d();
    }

    public i(i iVar) {
        this.f5714a = iVar.f5714a;
        this.f5715b = iVar.f5715b;
        this.f5716c = d.a(iVar.f5716c);
    }

    public static int c(int[] iArr) {
        int length = iArr.length - 1;
        while (length >= 0 && iArr[length] == 0) {
            length--;
        }
        return length;
    }

    public static int i(int[] iArr) {
        int iC = c(iArr);
        if (iC == -1) {
            return 0;
        }
        return iArr[iC];
    }

    public static boolean j(int[] iArr, int[] iArr2) {
        int iC = c(iArr);
        if (iC != c(iArr2)) {
            return false;
        }
        for (int i2 = 0; i2 <= iC; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] p(int[] iArr, int i2) {
        int iC = c(iArr);
        if (iC == -1) {
            return new int[1];
        }
        int[] iArr2 = new int[iC + i2 + 1];
        System.arraycopy(iArr, 0, iArr2, i2, iC + 1);
        return iArr2;
    }

    public static int[] q(int[] iArr) {
        int iC = c(iArr);
        if (iC == -1) {
            return new int[1];
        }
        int i2 = iC + 1;
        if (iArr.length == i2) {
            return d.a(iArr);
        }
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }

    public final int[] a(int[] iArr, int[] iArr2) {
        int[] iArr3;
        if (iArr.length < iArr2.length) {
            iArr3 = new int[iArr2.length];
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
        } else {
            iArr3 = new int[iArr.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            iArr = iArr2;
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr3[length] = this.f5714a.a(iArr3[length], iArr[length]);
        }
        return iArr3;
    }

    public void b(i iVar) {
        this.f5716c = a(this.f5716c, iVar.f5716c);
        d();
    }

    public final void d() {
        int length = this.f5716c.length;
        do {
            this.f5715b = length - 1;
            length = this.f5715b;
            if (length < 0) {
                return;
            }
        } while (this.f5716c[length] == 0);
    }

    public int e(int i2) {
        int[] iArr = this.f5716c;
        int i3 = this.f5715b;
        int iH = iArr[i3];
        for (int i4 = i3 - 1; i4 >= 0; i4--) {
            iH = this.f5714a.h(iH, i2) ^ this.f5716c[i4];
        }
        return iH;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof i)) {
            i iVar = (i) obj;
            if (this.f5714a.equals(iVar.f5714a) && this.f5715b == iVar.f5715b && j(this.f5716c, iVar.f5716c)) {
                return true;
            }
        }
        return false;
    }

    public int f(int i2) {
        if (i2 < 0 || i2 > this.f5715b) {
            return 0;
        }
        return this.f5716c[i2];
    }

    public int g() {
        int[] iArr = this.f5716c;
        int length = iArr.length - 1;
        if (iArr[length] == 0) {
            return -1;
        }
        return length;
    }

    public byte[] h() {
        int i2 = 8;
        int i3 = 1;
        while (this.f5714a.d() > i2) {
            i3++;
            i2 += 8;
        }
        byte[] bArr = new byte[this.f5716c.length * i3];
        int i4 = 0;
        for (int i5 = 0; i5 < this.f5716c.length; i5++) {
            int i6 = 0;
            while (i6 < i2) {
                bArr[i4] = (byte) (this.f5716c[i5] >>> i6);
                i6 += 8;
                i4++;
            }
        }
        return bArr;
    }

    public int hashCode() {
        int iHashCode = this.f5714a.hashCode();
        int i2 = 0;
        while (true) {
            int[] iArr = this.f5716c;
            if (i2 >= iArr.length) {
                return iHashCode;
            }
            iHashCode = (iHashCode * 31) + iArr[i2];
            i2++;
        }
    }

    public i k(i iVar) {
        return new i(this.f5714a, l(this.f5716c, iVar.f5716c));
    }

    public final int[] l(int[] iArr, int[] iArr2) {
        int iC = c(iArr2);
        if (iC == -1) {
            throw new ArithmeticException("Division by zero");
        }
        int length = iArr.length;
        int[] iArrA = new int[length];
        int iF = this.f5714a.f(i(iArr2));
        System.arraycopy(iArr, 0, iArrA, 0, length);
        while (iC <= c(iArrA)) {
            iArrA = a(o(p(iArr2, c(iArrA) - iC), this.f5714a.h(i(iArrA), iF)), iArrA);
        }
        return iArrA;
    }

    public void m(int i2) {
        if (!this.f5714a.g(i2)) {
            throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
        }
        this.f5716c = o(this.f5716c, i2);
        d();
    }

    public i n(int i2) {
        if (!this.f5714a.g(i2)) {
            throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
        }
        return new i(this.f5714a, o(this.f5716c, i2));
    }

    public final int[] o(int[] iArr, int i2) {
        int iC = c(iArr);
        if (iC == -1 || i2 == 0) {
            return new int[1];
        }
        if (i2 == 1) {
            return d.a(iArr);
        }
        int[] iArr2 = new int[iC + 1];
        while (iC >= 0) {
            iArr2[iC] = this.f5714a.h(iArr[iC], i2);
            iC--;
        }
        return iArr2;
    }

    public String toString() {
        String str = " Polynomial over " + this.f5714a.toString() + ": \n";
        for (int i2 = 0; i2 < this.f5716c.length; i2++) {
            str = str + this.f5714a.b(this.f5716c[i2]) + "Y^" + i2 + "+";
        }
        return str + ";";
    }
}
