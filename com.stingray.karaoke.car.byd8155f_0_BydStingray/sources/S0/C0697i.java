package S0;

/* JADX INFO: renamed from: S0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0697i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f7842a;

    public final boolean equals(Object obj) {
        if (obj instanceof C0697i) {
            return A7.m.a(this.f7842a, ((C0697i) obj).f7842a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f7842a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "AsyncTypefaceResult(result=" + this.f7842a + ')';
    }
}
