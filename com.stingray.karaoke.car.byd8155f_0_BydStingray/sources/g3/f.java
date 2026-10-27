package g3;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Comparable {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1293b f14834m;

    public f(int i7, C1293b c1293b) {
        this.l = i7;
        this.f14834m = c1293b;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Integer.compare(this.l, ((f) obj).l);
    }
}
