package p3;

/* JADX INFO: renamed from: p3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2101a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20866a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20867b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f20868e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20869f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20870g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20871h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20872i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ b f20873j;

    public C2101a(b bVar, int i7, int i10) {
        this.f20873j = bVar;
        this.f20866a = i7;
        this.f20867b = i10;
        a();
    }

    public final void a() {
        b bVar = this.f20873j;
        int[] iArr = bVar.f20875a;
        int[] iArr2 = bVar.f20876b;
        int i7 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MIN_VALUE;
        int i13 = 0;
        int i14 = Integer.MAX_VALUE;
        int i15 = Integer.MAX_VALUE;
        for (int i16 = this.f20866a; i16 <= this.f20867b; i16++) {
            int i17 = iArr[i16];
            i13 += iArr2[i17];
            int i18 = (i17 >> 10) & 31;
            int i19 = (i17 >> 5) & 31;
            int i20 = i17 & 31;
            if (i18 > i10) {
                i10 = i18;
            }
            if (i18 < i7) {
                i7 = i18;
            }
            if (i19 > i11) {
                i11 = i19;
            }
            if (i19 < i14) {
                i14 = i19;
            }
            if (i20 > i12) {
                i12 = i20;
            }
            if (i20 < i15) {
                i15 = i20;
            }
        }
        this.d = i7;
        this.f20868e = i10;
        this.f20869f = i14;
        this.f20870g = i11;
        this.f20871h = i15;
        this.f20872i = i12;
        this.c = i13;
    }

    public final int b() {
        return ((this.f20872i - this.f20871h) + 1) * ((this.f20870g - this.f20869f) + 1) * ((this.f20868e - this.d) + 1);
    }
}
