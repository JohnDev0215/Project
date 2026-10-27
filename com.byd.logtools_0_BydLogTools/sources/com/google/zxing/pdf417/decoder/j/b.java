package com.google.zxing.pdf417.decoder.j;

/* JADX INFO: compiled from: ModulusGF.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f4474a = new b(929, 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f4475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int[] f4476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f4477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c f4478e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4479f;

    private b(int i, int i2) {
        this.f4479f = i;
        this.f4475b = new int[i];
        this.f4476c = new int[i];
        int i3 = 1;
        for (int i4 = 0; i4 < i; i4++) {
            this.f4475b[i4] = i3;
            i3 = (i3 * i2) % i;
        }
        for (int i5 = 0; i5 < i - 1; i5++) {
            this.f4476c[this.f4475b[i5]] = i5;
        }
        this.f4477d = new c(this, new int[]{0});
        this.f4478e = new c(this, new int[]{1});
    }

    int a(int i, int i2) {
        return (i + i2) % this.f4479f;
    }

    c b(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f4477d;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new c(this, iArr);
    }

    int c(int i) {
        return this.f4475b[i];
    }

    c d() {
        return this.f4478e;
    }

    int e() {
        return this.f4479f;
    }

    c f() {
        return this.f4477d;
    }

    int g(int i) {
        if (i != 0) {
            return this.f4475b[(this.f4479f - this.f4476c[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int h(int i) {
        if (i != 0) {
            return this.f4476c[i];
        }
        throw new IllegalArgumentException();
    }

    int i(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.f4475b;
        int[] iArr2 = this.f4476c;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.f4479f - 1)];
    }

    int j(int i, int i2) {
        int i3 = this.f4479f;
        return ((i + i3) - i2) % i3;
    }
}
