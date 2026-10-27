package Z7;

/* JADX INFO: loaded from: classes.dex */
public final class r {
    public static final r d = new r(B.STRICT, 6);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B f10953a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m7.e f10954b;
    public final B c;

    public r(B b10, m7.e eVar, B b11) {
        A7.m.f(b11, "reportLevelAfter");
        this.f10953a = b10;
        this.f10954b = eVar;
        this.c = b11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f10953a == rVar.f10953a && A7.m.a(this.f10954b, rVar.f10954b) && this.c == rVar.c;
    }

    public final int hashCode() {
        int iHashCode = this.f10953a.hashCode() * 31;
        m7.e eVar = this.f10954b;
        return this.c.hashCode() + ((iHashCode + (eVar == null ? 0 : eVar.o)) * 31);
    }

    public final String toString() {
        return "JavaNullabilityAnnotationsStatus(reportLevelBefore=" + this.f10953a + ", sinceVersion=" + this.f10954b + ", reportLevelAfter=" + this.c + ')';
    }

    public r(B b10, int i7) {
        this(b10, (i7 & 2) != 0 ? new m7.e(1, 0, 0) : null, b10);
    }
}
