package D;

/* JADX INFO: renamed from: D.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0127i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0119a f1076a = new C0119a(3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0119a f1077b = new C0119a(2);
    public static final C0121c c = new C0121c();
    public static final C0122d d = new C0122d(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0122d f1078e = new C0122d(3);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0122d f1079f = new C0122d(2);

    static {
        new C0122d(1);
    }

    public static void a(int i7, int[] iArr, int[] iArr2, boolean z6) {
        int i10 = 0;
        int i11 = 0;
        for (int i12 : iArr) {
            i11 += i12;
        }
        float f10 = (i7 - i11) / 2;
        if (!z6) {
            int length = iArr.length;
            int i13 = 0;
            while (i10 < length) {
                int i14 = iArr[i10];
                iArr2[i13] = Math.round(f10);
                f10 += i14;
                i10++;
                i13++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i15 = iArr[length2];
            iArr2[length2] = Math.round(f10);
            f10 += i15;
        }
    }

    public static void b(int[] iArr, int[] iArr2, boolean z6) {
        int i7 = 0;
        if (!z6) {
            int length = iArr.length;
            int i10 = 0;
            int i11 = 0;
            while (i7 < length) {
                int i12 = iArr[i7];
                iArr2[i10] = i11;
                i11 += i12;
                i7++;
                i10++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i13 = iArr[length2];
            iArr2[length2] = i7;
            i7 += i13;
        }
    }

    public static void c(int i7, int[] iArr, int[] iArr2, boolean z6) {
        int i10 = 0;
        int i11 = 0;
        for (int i12 : iArr) {
            i11 += i12;
        }
        int i13 = i7 - i11;
        if (!z6) {
            int length = iArr.length;
            int i14 = 0;
            while (i10 < length) {
                int i15 = iArr[i10];
                iArr2[i14] = i13;
                i13 += i15;
                i10++;
                i14++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i16 = iArr[length2];
            iArr2[length2] = i13;
            i13 += i16;
        }
    }

    public static void d(int i7, int[] iArr, int[] iArr2, boolean z6) {
        int i10 = 0;
        int i11 = 0;
        for (int i12 : iArr) {
            i11 += i12;
        }
        float length = iArr.length == 0 ? 0.0f : (i7 - i11) / iArr.length;
        float f10 = length / 2;
        if (!z6) {
            int length2 = iArr.length;
            int i13 = 0;
            while (i10 < length2) {
                int i14 = iArr[i10];
                iArr2[i13] = Math.round(f10);
                f10 += i14 + length;
                i10++;
                i13++;
            }
            return;
        }
        int length3 = iArr.length;
        while (true) {
            length3--;
            if (-1 >= length3) {
                return;
            }
            int i15 = iArr[length3];
            iArr2[length3] = Math.round(f10);
            f10 += i15 + length;
        }
    }

    public static void e(int i7, int[] iArr, int[] iArr2, boolean z6) {
        if (iArr.length == 0) {
            return;
        }
        int i10 = 0;
        int i11 = 0;
        for (int i12 : iArr) {
            i11 += i12;
        }
        float fMax = (i7 - i11) / Math.max(iArr.length - 1, 1);
        float f10 = (z6 && iArr.length == 1) ? fMax : 0.0f;
        if (z6) {
            for (int length = iArr.length - 1; -1 < length; length--) {
                int i13 = iArr[length];
                iArr2[length] = Math.round(f10);
                f10 += i13 + fMax;
            }
            return;
        }
        int length2 = iArr.length;
        int i14 = 0;
        while (i10 < length2) {
            int i15 = iArr[i10];
            iArr2[i14] = Math.round(f10);
            f10 += i15 + fMax;
            i10++;
            i14++;
        }
    }

    public static void f(int i7, int[] iArr, int[] iArr2, boolean z6) {
        int i10 = 0;
        int i11 = 0;
        for (int i12 : iArr) {
            i11 += i12;
        }
        float length = (i7 - i11) / (iArr.length + 1);
        if (z6) {
            float f10 = length;
            for (int length2 = iArr.length - 1; -1 < length2; length2--) {
                int i13 = iArr[length2];
                iArr2[length2] = Math.round(f10);
                f10 += i13 + length;
            }
            return;
        }
        int length3 = iArr.length;
        float f11 = length;
        int i14 = 0;
        while (i10 < length3) {
            int i15 = iArr[i10];
            iArr2[i14] = Math.round(f11);
            f11 += i15 + length;
            i10++;
            i14++;
        }
    }
}
