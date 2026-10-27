package n0;

/* JADX INFO: renamed from: n0.S, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1868S extends AbstractC1887p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19420a;

    public C1868S(long j3) {
        this.f19420a = j3;
    }

    @Override // n0.AbstractC1887p
    public final void a(float f10, long j3, C1878g c1878g) {
        c1878g.c(1.0f);
        long jB = this.f19420a;
        if (f10 != 1.0f) {
            jB = C1891t.b(C1891t.d(jB) * f10, jB);
        }
        c1878g.e(jB);
        if (c1878g.c != null) {
            c1878g.h(null);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1868S) {
            return C1891t.c(this.f19420a, ((C1868S) obj).f19420a);
        }
        return false;
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f19420a);
    }

    public final String toString() {
        return "SolidColor(value=" + ((Object) C1891t.i(this.f19420a)) + ')';
    }
}
