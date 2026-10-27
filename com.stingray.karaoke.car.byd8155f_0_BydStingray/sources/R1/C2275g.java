package r1;

import java.util.Arrays;

/* JADX INFO: renamed from: r1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2275g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f21662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f21663b;
    public int c;
    public int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f21664e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21665f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f21666g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String[] f21667h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f21668i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f21669j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean[] f21670k;
    public int l;

    public final void a(float f10, int i7) {
        int i10 = this.f21665f;
        int[] iArr = this.d;
        if (i10 >= iArr.length) {
            this.d = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f21664e;
            this.f21664e = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.d;
        int i11 = this.f21665f;
        iArr2[i11] = i7;
        float[] fArr2 = this.f21664e;
        this.f21665f = i11 + 1;
        fArr2[i11] = f10;
    }

    public final void b(int i7, int i10) {
        int i11 = this.c;
        int[] iArr = this.f21662a;
        if (i11 >= iArr.length) {
            this.f21662a = Arrays.copyOf(iArr, iArr.length * 2);
            int[] iArr2 = this.f21663b;
            this.f21663b = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f21662a;
        int i12 = this.c;
        iArr3[i12] = i7;
        int[] iArr4 = this.f21663b;
        this.c = i12 + 1;
        iArr4[i12] = i10;
    }

    public final void c(int i7, String str) {
        int i10 = this.f21668i;
        int[] iArr = this.f21666g;
        if (i10 >= iArr.length) {
            this.f21666g = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f21667h;
            this.f21667h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        }
        int[] iArr2 = this.f21666g;
        int i11 = this.f21668i;
        iArr2[i11] = i7;
        String[] strArr2 = this.f21667h;
        this.f21668i = i11 + 1;
        strArr2[i11] = str;
    }

    public final void d(int i7, boolean z6) {
        int i10 = this.l;
        int[] iArr = this.f21669j;
        if (i10 >= iArr.length) {
            this.f21669j = Arrays.copyOf(iArr, iArr.length * 2);
            boolean[] zArr = this.f21670k;
            this.f21670k = Arrays.copyOf(zArr, zArr.length * 2);
        }
        int[] iArr2 = this.f21669j;
        int i11 = this.l;
        iArr2[i11] = i7;
        boolean[] zArr2 = this.f21670k;
        this.l = i11 + 1;
        zArr2[i11] = z6;
    }
}
