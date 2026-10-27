package U8;

/* JADX INFO: loaded from: classes.dex */
public final class q extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f9269a;

    public q(Throwable th) {
        this.f9269a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof q) {
            if (A7.m.a(this.f9269a, ((q) obj).f9269a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.f9269a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // U8.r
    public final String toString() {
        return "Closed(" + this.f9269a + ')';
    }
}
