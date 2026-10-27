package F;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1864a;

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f1864a == ((b) obj).f1864a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f1864a);
    }

    public final String toString() {
        return "GridItemSpan(packedValue=" + this.f1864a + ')';
    }
}
