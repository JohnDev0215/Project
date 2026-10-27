package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;

/* JADX INFO: compiled from: Version.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final d[] f4262a = a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4266e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c f4268g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f4269h;

    /* JADX INFO: compiled from: Version.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f4270a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f4271b;

        int a() {
            return this.f4270a;
        }

        int b() {
            return this.f4271b;
        }

        private b(int i, int i2) {
            this.f4270a = i;
            this.f4271b = i2;
        }
    }

    private d(int i, int i2, int i3, int i4, int i5, c cVar) {
        this.f4263b = i;
        this.f4264c = i2;
        this.f4265d = i3;
        this.f4266e = i4;
        this.f4267f = i5;
        this.f4268g = cVar;
        int iB = cVar.b();
        int iA = 0;
        for (b bVar : cVar.a()) {
            iA += bVar.a() * (bVar.b() + iB);
        }
        this.f4269h = iA;
    }

    private static d[] a() {
        int i = 1;
        int i2 = 5;
        int i3 = 8;
        d dVar = new d(3, 14, 14, 12, 12, new c(10, new b(i, i3)));
        int i4 = 2;
        int i5 = 12;
        int i6 = 18;
        d dVar2 = new d(7, 22, 22, 20, 20, new c(20, new b(i, 30)));
        int i7 = 6;
        int i8 = 36;
        int i9 = 62;
        int i10 = 56;
        int i11 = 68;
        b bVar = new b(i, 5);
        b bVar2 = new b(i, 10);
        b bVar3 = new b(i, 16);
        return new d[]{new d(1, 10, 10, 8, 8, new c(i2, new b(i, 3))), new d(2, 12, 12, 10, 10, new c(7, new b(i, i2))), dVar, new d(4, 16, 16, 14, 14, new c(i5, new b(i, i5))), new d(5, 18, 18, 16, 16, new c(14, new b(i, i6))), new d(6, 20, 20, 18, 18, new c(i6, new b(i, 22))), dVar2, new d(8, 24, 24, 22, 22, new c(24, new b(i, i8))), new d(9, 26, 26, 24, 24, new c(28, new b(i, 44))), new d(10, 32, 32, 14, 14, new c(i8, new b(i, i9))), new d(11, 36, 36, 16, 16, new c(42, new b(i, 86))), new d(12, 40, 40, 18, 18, new c(48, new b(i, 114))), new d(13, 44, 44, 20, 20, new c(i10, new b(i, 144))), new d(14, 48, 48, 22, 22, new c(i11, new b(i, 174))), new d(15, 52, 52, 24, 24, new c(42, new b(i4, 102))), new d(16, 64, 64, 14, 14, new c(i10, new b(i4, 140))), new d(17, 72, 72, 16, 16, new c(i8, new b(4, 92))), new d(18, 80, 80, 18, 18, new c(48, new b(4, 114))), new d(19, 88, 88, 20, 20, new c(i10, new b(4, 144))), new d(20, 96, 96, 22, 22, new c(i11, new b(4, 174))), new d(21, 104, 104, 24, 24, new c(i10, new b(i7, 136))), new d(22, 120, 120, 18, 18, new c(i11, new b(i7, 175))), new d(23, 132, 132, 20, 20, new c(i9, new b(i3, 163))), new d(24, 144, 144, 22, 22, new c(i9, new b(i3, 156), new b(i4, 155))), new d(25, 8, 18, 6, 16, new c(7, bVar)), new d(26, 8, 32, 6, 14, new c(11, bVar2)), new d(27, 12, 26, 10, 24, new c(14, bVar3)), new d(28, 12, 36, 10, 16, new c(i6, new b(i, 22))), new d(29, 16, 36, 14, 16, new c(24, new b(i, 32))), new d(30, 16, 48, 14, 22, new c(28, new b(i, 49)))};
    }

    public static d h(int i, int i2) throws FormatException {
        if ((i & 1) != 0 || (i2 & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        for (d dVar : f4262a) {
            if (dVar.f4264c == i && dVar.f4265d == i2) {
                return dVar;
            }
        }
        throw FormatException.getFormatInstance();
    }

    public int b() {
        return this.f4267f;
    }

    public int c() {
        return this.f4266e;
    }

    c d() {
        return this.f4268g;
    }

    public int e() {
        return this.f4265d;
    }

    public int f() {
        return this.f4264c;
    }

    public int g() {
        return this.f4269h;
    }

    public int i() {
        return this.f4263b;
    }

    public String toString() {
        return String.valueOf(this.f4263b);
    }

    /* JADX INFO: compiled from: Version.java */
    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f4272a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b[] f4273b;

        b[] a() {
            return this.f4273b;
        }

        int b() {
            return this.f4272a;
        }

        private c(int i, b bVar) {
            this.f4272a = i;
            this.f4273b = new b[]{bVar};
        }

        private c(int i, b bVar, b bVar2) {
            this.f4272a = i;
            this.f4273b = new b[]{bVar, bVar2};
        }
    }
}
