package u5;

/* JADX INFO: renamed from: u5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2525a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2525a f22922g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f22923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f22924b;
    public final C2526b c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22925e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f22926f;

    static {
        new C2525a(4201, 4096, 1);
        new C2525a(1033, 1024, 1);
        new C2525a(67, 64, 1);
        new C2525a(19, 16, 1);
        f22922g = new C2525a(285, 256, 0);
        new C2525a(301, 256, 1);
    }

    public C2525a(int i7, int i10, int i11) {
        this.f22925e = i7;
        this.d = i10;
        this.f22926f = i11;
        this.f22923a = new int[i10];
        this.f22924b = new int[i10];
        int i12 = 1;
        for (int i13 = 0; i13 < i10; i13++) {
            this.f22923a[i13] = i12;
            i12 *= 2;
            if (i12 >= i10) {
                i12 = (i12 ^ i7) & (i10 - 1);
            }
        }
        for (int i14 = 0; i14 < i10 - 1; i14++) {
            this.f22924b[this.f22923a[i14]] = i14;
        }
        this.c = new C2526b(this, new int[]{0});
    }

    public final int a(int i7, int i10) {
        if (i7 == 0 || i10 == 0) {
            return 0;
        }
        int[] iArr = this.f22924b;
        return this.f22923a[(iArr[i7] + iArr[i10]) % (this.d - 1)];
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GF(0x");
        sb.append(Integer.toHexString(this.f22925e));
        sb.append(',');
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.d, ')');
    }
}
