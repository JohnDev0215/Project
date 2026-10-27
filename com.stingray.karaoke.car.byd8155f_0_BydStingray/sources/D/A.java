package D;

/* JADX INFO: loaded from: classes.dex */
public final class A extends AbstractC0120b {
    public final g0.e c;

    public A(g0.e eVar) {
        this.c = eVar;
    }

    @Override // D.AbstractC0120b
    public final int c(int i7, a1.k kVar) {
        return this.c.a(0, i7, kVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof A) && A7.m.a(this.c, ((A) obj).c);
    }

    public final int hashCode() {
        return Float.hashCode(this.c.f14676a);
    }

    public final String toString() {
        return "HorizontalCrossAxisAlignment(horizontal=" + this.c + ')';
    }
}
