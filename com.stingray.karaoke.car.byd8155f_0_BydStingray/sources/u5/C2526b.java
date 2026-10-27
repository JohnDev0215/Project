package u5;

/* JADX INFO: renamed from: u5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2526b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2525a f22927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f22928b;

    public C2526b(C2525a c2525a, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f22927a = c2525a;
        int length = iArr.length;
        int i7 = 1;
        if (length <= 1 || iArr[0] != 0) {
            this.f22928b = iArr;
            return;
        }
        while (i7 < length && iArr[i7] == 0) {
            i7++;
        }
        if (i7 == length) {
            this.f22928b = new int[]{0};
            return;
        }
        int i10 = length - i7;
        int[] iArr2 = new int[i10];
        this.f22928b = iArr2;
        System.arraycopy(iArr, i7, iArr2, 0, i10);
    }

    public final C2526b a(C2526b c2526b) {
        C2525a c2525a = c2526b.f22927a;
        C2525a c2525a2 = this.f22927a;
        if (!c2525a2.equals(c2525a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (c()) {
            return c2526b;
        }
        if (c2526b.c()) {
            return this;
        }
        int[] iArr = this.f22928b;
        int length = iArr.length;
        int[] iArr2 = c2526b.f22928b;
        if (length <= iArr2.length) {
            iArr2 = iArr;
            iArr = iArr2;
        }
        int[] iArr3 = new int[iArr.length];
        int length2 = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length2);
        for (int i7 = length2; i7 < iArr.length; i7++) {
            iArr3[i7] = iArr2[i7 - length2] ^ iArr[i7];
        }
        return new C2526b(c2525a2, iArr3);
    }

    public final int b() {
        return this.f22928b.length - 1;
    }

    public final boolean c() {
        return this.f22928b[0] == 0;
    }

    public final String toString() {
        if (c()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(b() * 8);
        for (int iB = b(); iB >= 0; iB--) {
            int[] iArr = this.f22928b;
            int i7 = iArr[(iArr.length - 1) - iB];
            if (i7 != 0) {
                if (i7 < 0) {
                    if (iB == b()) {
                        sb.append("-");
                    } else {
                        sb.append(" - ");
                    }
                    i7 = -i7;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (iB == 0 || i7 != 1) {
                    C2525a c2525a = this.f22927a;
                    if (i7 == 0) {
                        c2525a.getClass();
                        throw new IllegalArgumentException();
                    }
                    int i10 = c2525a.f22924b[i7];
                    if (i10 == 0) {
                        sb.append('1');
                    } else if (i10 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(i10);
                    }
                }
                if (iB != 0) {
                    if (iB == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(iB);
                    }
                }
            }
        }
        return sb.toString();
    }
}
