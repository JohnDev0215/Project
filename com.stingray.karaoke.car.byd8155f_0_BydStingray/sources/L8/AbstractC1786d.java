package l8;

/* JADX INFO: renamed from: l8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1786d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18825a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18826b;

    public AbstractC1786d(int i7, int i10) {
        this.f18825a = i7;
        this.f18826b = i10;
    }

    public static C1784b a(AbstractC1786d abstractC1786d) {
        return new C1784b(abstractC1786d.f18825a + abstractC1786d.f18826b, 1);
    }

    public static C1784b b() {
        return new C1784b(0, 1);
    }
}
