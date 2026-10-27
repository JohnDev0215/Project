package h1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Comparable {
    public boolean l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f15187p;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f15194w;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15185m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15186n = -1;
    public int o = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f15188q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final float[] f15189r = new float[9];

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float[] f15190s = new float[9];

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C1342b[] f15191t = new C1342b[16];

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f15192u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f15193v = 0;

    public e(int i7) {
        this.f15194w = i7;
    }

    public final void a(C1342b c1342b) {
        int i7 = 0;
        while (true) {
            int i10 = this.f15192u;
            if (i7 >= i10) {
                C1342b[] c1342bArr = this.f15191t;
                if (i10 >= c1342bArr.length) {
                    this.f15191t = (C1342b[]) Arrays.copyOf(c1342bArr, c1342bArr.length * 2);
                }
                C1342b[] c1342bArr2 = this.f15191t;
                int i11 = this.f15192u;
                c1342bArr2[i11] = c1342b;
                this.f15192u = i11 + 1;
                return;
            }
            if (this.f15191t[i7] == c1342b) {
                return;
            } else {
                i7++;
            }
        }
    }

    public final void b(C1342b c1342b) {
        int i7 = this.f15192u;
        int i10 = 0;
        while (i10 < i7) {
            if (this.f15191t[i10] == c1342b) {
                while (i10 < i7 - 1) {
                    C1342b[] c1342bArr = this.f15191t;
                    int i11 = i10 + 1;
                    c1342bArr[i10] = c1342bArr[i11];
                    i10 = i11;
                }
                this.f15192u--;
                return;
            }
            i10++;
        }
    }

    public final void c() {
        this.f15194w = 5;
        this.o = 0;
        this.f15185m = -1;
        this.f15186n = -1;
        this.f15187p = 0.0f;
        this.f15188q = false;
        int i7 = this.f15192u;
        for (int i10 = 0; i10 < i7; i10++) {
            this.f15191t[i10] = null;
        }
        this.f15192u = 0;
        this.f15193v = 0;
        this.l = false;
        Arrays.fill(this.f15190s, 0.0f);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f15185m - ((e) obj).f15185m;
    }

    public final void d(c cVar, float f10) {
        this.f15187p = f10;
        this.f15188q = true;
        int i7 = this.f15192u;
        this.f15186n = -1;
        for (int i10 = 0; i10 < i7; i10++) {
            this.f15191t[i10].h(cVar, this, false);
        }
        this.f15192u = 0;
    }

    public final void e(c cVar, C1342b c1342b) {
        int i7 = this.f15192u;
        for (int i10 = 0; i10 < i7; i10++) {
            this.f15191t[i10].i(cVar, c1342b, false);
        }
        this.f15192u = 0;
    }

    public final String toString() {
        return "" + this.f15185m;
    }
}
