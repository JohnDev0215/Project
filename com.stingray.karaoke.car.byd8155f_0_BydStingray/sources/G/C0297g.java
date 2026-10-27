package G;

/* JADX INFO: renamed from: G.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0297g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2561b;
    public final r c;

    public C0297g(int i7, int i10, r rVar) {
        this.f2560a = i7;
        this.f2561b = i10;
        this.c = rVar;
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "startIndex should be >= 0, but was ").toString());
        }
        if (i10 <= 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i10, "size should be >0, but was ").toString());
        }
    }
}
