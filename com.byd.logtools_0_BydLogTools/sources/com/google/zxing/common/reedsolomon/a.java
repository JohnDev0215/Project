package com.google.zxing.common.reedsolomon;

/* JADX INFO: compiled from: GenericGF.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f4234a = new a(4201, 4096, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f4235b = new a(1033, 1024, 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f4236c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f4237d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f4238e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f4239f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f4240g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f4241h;
    private final int[] i;
    private final int[] j;
    private final b k;
    private final b l;
    private final int m;
    private final int n;
    private final int o;

    static {
        a aVar = new a(67, 64, 1);
        f4236c = aVar;
        f4237d = new a(19, 16, 1);
        f4238e = new a(285, 256, 0);
        a aVar2 = new a(301, 256, 1);
        f4239f = aVar2;
        f4240g = aVar2;
        f4241h = aVar;
    }

    public a(int i, int i2, int i3) {
        this.n = i;
        this.m = i2;
        this.o = i3;
        this.i = new int[i2];
        this.j = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.i[i5] = i4;
            i4 <<= 1;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.j[this.i[i6]] = i6;
        }
        this.k = new b(this, new int[]{0});
        this.l = new b(this, new int[]{1});
    }

    static int a(int i, int i2) {
        return i ^ i2;
    }

    b b(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.k;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new b(this, iArr);
    }

    int c(int i) {
        return this.i[i];
    }

    public int d() {
        return this.o;
    }

    b e() {
        return this.l;
    }

    public int f() {
        return this.m;
    }

    b g() {
        return this.k;
    }

    int h(int i) {
        if (i != 0) {
            return this.i[(this.m - this.j[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int i(int i) {
        if (i != 0) {
            return this.j[i];
        }
        throw new IllegalArgumentException();
    }

    int j(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.m - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.n) + ',' + this.m + ')';
    }
}
