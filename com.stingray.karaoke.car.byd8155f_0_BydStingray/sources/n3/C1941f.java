package n3;

/* JADX INFO: renamed from: n3.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1941f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final M f19858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f19859b;
    public final boolean c;

    public C1941f(M m10, boolean z6) {
        if (!m10.f19840a && z6) {
            throw new IllegalArgumentException(m10.b().concat(" does not allow nullable values").toString());
        }
        this.f19858a = m10;
        this.f19859b = z6;
        this.c = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !C1941f.class.equals(obj.getClass())) {
            return false;
        }
        C1941f c1941f = (C1941f) obj;
        return this.f19859b == c1941f.f19859b && this.c == c1941f.c && this.f19858a.equals(c1941f.f19858a);
    }

    public final int hashCode() {
        return ((((this.f19858a.hashCode() * 31) + (this.f19859b ? 1 : 0)) * 31) + (this.c ? 1 : 0)) * 31;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(C1941f.class.getSimpleName());
        sb.append(" Type: " + this.f19858a);
        sb.append(" Nullable: " + this.f19859b);
        if (this.c) {
            sb.append(" DefaultValue: null");
        }
        String string = sb.toString();
        A7.m.e(string, "sb.toString()");
        return string;
    }
}
