package h3;

import B2.L;
import B2.N;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f15401a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15402b;
    public final boolean c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final N f15404f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f15405g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15406h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15407i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f15408j;
    public long l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f15412p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f15413q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f15414r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f15415s;
    public final SparseArray d = new SparseArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SparseArray f15403e = new SparseArray();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public o f15410m = new o();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public o f15411n = new o();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f15409k = false;
    public boolean o = false;

    public p(L l, boolean z6, boolean z10) {
        this.f15401a = l;
        this.f15402b = z6;
        this.c = z10;
        byte[] bArr = new byte[128];
        this.f15405g = bArr;
        this.f15404f = new N(0, 0, bArr);
        o oVar = this.f15411n;
        oVar.f15390b = false;
        oVar.f15389a = false;
    }

    public final void a() {
        boolean z6;
        int i7;
        boolean z10 = false;
        if (this.f15402b) {
            o oVar = this.f15411n;
            z6 = oVar.f15390b && ((i7 = oVar.f15391e) == 7 || i7 == 2);
        } else {
            z6 = this.f15415s;
        }
        boolean z11 = this.f15414r;
        int i10 = this.f15407i;
        if (i10 == 5 || (z6 && i10 == 1)) {
            z10 = true;
        }
        this.f15414r = z11 | z10;
    }
}
