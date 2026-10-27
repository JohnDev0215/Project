package j1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f17000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f17001b;
    public int c;
    public int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f17002e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f17003f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f17004g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String[] f17005h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f17006i;

    public final void a(float f10, int i7) {
        int i10 = this.f17003f;
        int[] iArr = this.d;
        if (i10 >= iArr.length) {
            this.d = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f17002e;
            this.f17002e = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.d;
        int i11 = this.f17003f;
        iArr2[i11] = i7;
        float[] fArr2 = this.f17002e;
        this.f17003f = i11 + 1;
        fArr2[i11] = f10;
    }

    public final void b(int i7, int i10) {
        int i11 = this.c;
        int[] iArr = this.f17000a;
        if (i11 >= iArr.length) {
            this.f17000a = Arrays.copyOf(iArr, iArr.length * 2);
            int[] iArr2 = this.f17001b;
            this.f17001b = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f17000a;
        int i12 = this.c;
        iArr3[i12] = i7;
        int[] iArr4 = this.f17001b;
        this.c = i12 + 1;
        iArr4[i12] = i10;
    }

    public final void c(int i7, String str) {
        int i10 = this.f17006i;
        int[] iArr = this.f17004g;
        if (i10 >= iArr.length) {
            this.f17004g = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f17005h;
            this.f17005h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        }
        int[] iArr2 = this.f17004g;
        int i11 = this.f17006i;
        iArr2[i11] = i7;
        String[] strArr2 = this.f17005h;
        this.f17006i = i11 + 1;
        strArr2[i11] = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TypedBundle{mCountInt=");
        sb.append(this.c);
        sb.append(", mCountFloat=");
        sb.append(this.f17003f);
        sb.append(", mCountString=");
        return M5.b.h(sb, this.f17006i, ", mCountBoolean=0}");
    }
}
