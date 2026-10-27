package t8;

/* JADX INFO: loaded from: classes.dex */
public final class p extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f22507a;

    public p(f fVar) {
        this.f22507a = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p) && A7.m.a(this.f22507a, ((p) obj).f22507a);
    }

    public final int hashCode() {
        return this.f22507a.hashCode();
    }

    public final String toString() {
        return "NormalClass(value=" + this.f22507a + ')';
    }
}
