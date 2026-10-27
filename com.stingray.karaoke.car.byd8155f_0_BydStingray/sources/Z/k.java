package z;

/* JADX INFO: loaded from: classes.dex */
public final class k extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f24250a;

    public k(long j3) {
        this.f24250a = j3;
        if (!h6.e.m(j3)) {
            throw new IllegalStateException("ContextMenuState.Status should never be open with an unspecified offset. Use ContextMenuState.Status.Closed instead.");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        return m0.c.b(this.f24250a, ((k) obj).f24250a);
    }

    public final int hashCode() {
        return Long.hashCode(this.f24250a);
    }

    public final String toString() {
        return "Open(offset=" + ((Object) m0.c.j(this.f24250a)) + ')';
    }
}
