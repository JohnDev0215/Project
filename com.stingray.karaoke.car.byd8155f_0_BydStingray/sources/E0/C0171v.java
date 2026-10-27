package E0;

import java.util.Arrays;

/* JADX INFO: renamed from: E0.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0171v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f1705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1706b;

    public C0171v() {
        this.f1705a = new int[10];
    }

    public int a() {
        int[] iArr = this.f1705a;
        int i7 = this.f1706b - 1;
        this.f1706b = i7;
        return iArr[i7];
    }

    public void b(int i7) {
        int i10 = this.f1706b;
        int[] iArr = this.f1705a;
        if (i10 >= iArr.length) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length * 2);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f1705a = iArrCopyOf;
        }
        int[] iArr2 = this.f1705a;
        int i11 = this.f1706b;
        this.f1706b = i11 + 1;
        iArr2[i11] = i7;
    }

    public void c(int i7, int i10, int i11) {
        int i12 = this.f1706b;
        int i13 = i12 + 3;
        int[] iArr = this.f1705a;
        if (i13 >= iArr.length) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length * 2);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f1705a = iArrCopyOf;
        }
        int[] iArr2 = this.f1705a;
        iArr2[i12] = i7 + i11;
        iArr2[i12 + 1] = i10 + i11;
        iArr2[i12 + 2] = i11;
        this.f1706b = i13;
    }

    public void d(int i7, int i10, int i11, int i12) {
        int i13 = this.f1706b;
        int i14 = i13 + 4;
        int[] iArr = this.f1705a;
        if (i14 >= iArr.length) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length * 2);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f1705a = iArrCopyOf;
        }
        int[] iArr2 = this.f1705a;
        iArr2[i13] = i7;
        iArr2[i13 + 1] = i10;
        iArr2[i13 + 2] = i11;
        iArr2[i13 + 3] = i12;
        this.f1706b = i14;
    }

    public void e(int i7, int i10) {
        if (i7 < i10) {
            int i11 = i7 - 3;
            for (int i12 = i7; i12 < i10; i12 += 3) {
                int[] iArr = this.f1705a;
                int i13 = iArr[i12];
                int i14 = iArr[i10];
                if (i13 < i14 || (i13 == i14 && iArr[i12 + 1] <= iArr[i10 + 1])) {
                    i11 += 3;
                    f(i11, i12);
                }
            }
            f(i11 + 3, i10);
            e(i7, i11);
            e(i11 + 6, i10);
        }
    }

    public void f(int i7, int i10) {
        int[] iArr = this.f1705a;
        int i11 = iArr[i7];
        iArr[i7] = iArr[i10];
        iArr[i10] = i11;
        int i12 = i7 + 1;
        int i13 = i10 + 1;
        int i14 = iArr[i12];
        iArr[i12] = iArr[i13];
        iArr[i13] = i14;
        int i15 = i7 + 2;
        int i16 = i10 + 2;
        int i17 = iArr[i15];
        iArr[i15] = iArr[i16];
        iArr[i16] = i17;
    }

    public C0171v(int i7) {
        this.f1705a = new int[i7];
    }
}
