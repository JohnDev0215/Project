package G;

import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2557a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2558b;
    public Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2559e;

    public int a(int i7) {
        int i10 = this.f2557a + 1;
        int[] iArr = (int[]) this.c;
        int length = iArr.length;
        if (i10 > length) {
            int i11 = length * 2;
            int[] iArr2 = new int[i11];
            int[] iArr3 = new int[i11];
            AbstractC1996k.F(0, 0, 14, iArr, iArr2);
            AbstractC1996k.F(0, 0, 14, (int[]) this.d, iArr3);
            this.c = iArr2;
            this.d = iArr3;
        }
        int i12 = this.f2557a;
        this.f2557a = i12 + 1;
        int length2 = ((int[]) this.f2559e).length;
        if (this.f2558b >= length2) {
            int i13 = length2 * 2;
            int[] iArr4 = new int[i13];
            int i14 = 0;
            while (i14 < i13) {
                int i15 = i14 + 1;
                iArr4[i14] = i15;
                i14 = i15;
            }
            AbstractC1996k.F(0, 0, 14, (int[]) this.f2559e, iArr4);
            this.f2559e = iArr4;
        }
        int i16 = this.f2558b;
        int[] iArr5 = (int[]) this.f2559e;
        this.f2558b = iArr5[i16];
        int[] iArr6 = (int[]) this.c;
        iArr6[i12] = i7;
        ((int[]) this.d)[i12] = i16;
        iArr5[i16] = i12;
        int i17 = iArr6[i12];
        while (i12 > 0) {
            int i18 = ((i12 + 1) >> 1) - 1;
            if (iArr6[i18] <= i17) {
                break;
            }
            b(i18, i12);
            i12 = i18;
        }
        return i16;
    }

    public void b(int i7, int i10) {
        int[] iArr = (int[]) this.c;
        int[] iArr2 = (int[]) this.d;
        int[] iArr3 = (int[]) this.f2559e;
        int i11 = iArr[i7];
        iArr[i7] = iArr[i10];
        iArr[i10] = i11;
        int i12 = iArr2[i7];
        iArr2[i7] = iArr2[i10];
        iArr2[i10] = i12;
        iArr3[iArr2[i7]] = i7;
        iArr3[iArr2[i10]] = i10;
    }
}
