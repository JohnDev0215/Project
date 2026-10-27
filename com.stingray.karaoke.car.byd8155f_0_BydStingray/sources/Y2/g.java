package Y2;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Comparable {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f10637m;

    public g(long j3, byte[] bArr) {
        this.l = j3;
        this.f10637m = bArr;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.l, ((g) obj).l);
    }
}
