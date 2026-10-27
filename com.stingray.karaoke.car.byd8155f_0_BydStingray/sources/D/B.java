package D;

/* JADX INFO: loaded from: classes.dex */
public final class B extends AbstractC0120b {
    public final g0.f c;

    public B(g0.f fVar) {
        this.c = fVar;
    }

    @Override // D.AbstractC0120b
    public final int c(int i7, a1.k kVar) {
        return this.c.a(0, i7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof B) && A7.m.a(this.c, ((B) obj).c);
    }

    public final int hashCode() {
        return Float.hashCode(this.c.f14677a);
    }

    public final String toString() {
        return "VerticalCrossAxisAlignment(vertical=" + this.c + ')';
    }
}
