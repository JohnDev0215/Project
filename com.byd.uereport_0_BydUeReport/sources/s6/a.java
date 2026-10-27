package s6;

/* JADX INFO: compiled from: GenericGF.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7920a = new a(4201, 4096, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f7921b = new a(1033, 1024, 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f7922c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f7923d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f7924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f7925f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f7926g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f7927h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f7928i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f7929j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final b f7930k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b f7931l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f7932m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f7933n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f7934o;

    static {
        a aVar = new a(67, 64, 1);
        f7922c = aVar;
        f7923d = new a(19, 16, 1);
        f7924e = new a(285, 256, 0);
        a aVar2 = new a(301, 256, 1);
        f7925f = aVar2;
        f7926g = aVar2;
        f7927h = aVar;
    }

    public a(int i8, int i9, int i10) {
        this.f7933n = i8;
        this.f7932m = i9;
        this.f7934o = i10;
        this.f7928i = new int[i9];
        this.f7929j = new int[i9];
        int i11 = 1;
        for (int i12 = 0; i12 < i9; i12++) {
            this.f7928i[i12] = i11;
            i11 *= 2;
            if (i11 >= i9) {
                i11 = (i11 ^ i8) & (i9 - 1);
            }
        }
        for (int i13 = 0; i13 < i9 - 1; i13++) {
            this.f7929j[this.f7928i[i13]] = i13;
        }
        this.f7930k = new b(this, new int[]{0});
        this.f7931l = new b(this, new int[]{1});
    }

    public static int a(int i8, int i9) {
        return i8 ^ i9;
    }

    public b b(int i8, int i9) {
        if (i8 < 0) {
            throw new IllegalArgumentException();
        }
        if (i9 == 0) {
            return this.f7930k;
        }
        int[] iArr = new int[i8 + 1];
        iArr[0] = i9;
        return new b(this, iArr);
    }

    public int c(int i8) {
        return this.f7928i[i8];
    }

    public int d() {
        return this.f7934o;
    }

    public b e() {
        return this.f7930k;
    }

    public int f(int i8) {
        if (i8 != 0) {
            return this.f7928i[(this.f7932m - this.f7929j[i8]) - 1];
        }
        throw new ArithmeticException();
    }

    public int g(int i8) {
        if (i8 != 0) {
            return this.f7929j[i8];
        }
        throw new IllegalArgumentException();
    }

    public int h(int i8, int i9) {
        if (i8 == 0 || i9 == 0) {
            return 0;
        }
        int[] iArr = this.f7928i;
        int[] iArr2 = this.f7929j;
        return iArr[(iArr2[i8] + iArr2[i9]) % (this.f7932m - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f7933n) + ',' + this.f7932m + ')';
    }
}
