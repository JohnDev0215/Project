package h8;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final r f15624k = new r(false, false, false, false, false, new r(false, false, false, false, false, null, false, null, null, false, 1023), false, null, null, false, 988);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f15625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15626b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f15627e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r f15628f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f15629g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final r f15630h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f15631i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f15632j;

    public r(boolean z6, boolean z10, boolean z11, boolean z12, boolean z13, r rVar, boolean z14, r rVar2, r rVar3, boolean z15, int i7) {
        z6 = (i7 & 1) != 0 ? true : z6;
        z10 = (i7 & 2) != 0 ? true : z10;
        z11 = (i7 & 4) != 0 ? false : z11;
        z12 = (i7 & 8) != 0 ? false : z12;
        z13 = (i7 & 16) != 0 ? false : z13;
        rVar = (i7 & 32) != 0 ? null : rVar;
        z14 = (i7 & 64) != 0 ? true : z14;
        rVar2 = (i7 & 128) != 0 ? rVar : rVar2;
        rVar3 = (i7 & 256) != 0 ? rVar : rVar3;
        z15 = (i7 & 512) != 0 ? false : z15;
        this.f15625a = z6;
        this.f15626b = z10;
        this.c = z11;
        this.d = z12;
        this.f15627e = z13;
        this.f15628f = rVar;
        this.f15629g = z14;
        this.f15630h = rVar2;
        this.f15631i = rVar3;
        this.f15632j = z15;
    }
}
