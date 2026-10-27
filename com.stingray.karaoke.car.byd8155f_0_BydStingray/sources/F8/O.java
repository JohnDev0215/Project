package F8;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public abstract class O {
    public abstract int a();

    public abstract AbstractC0287w b();

    public abstract boolean c();

    public abstract O d(G8.f fVar);

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof O)) {
            return false;
        }
        O o = (O) obj;
        return c() == o.c() && a() == o.a() && b().equals(o.b());
    }

    public final int hashCode() {
        int iC = AbstractC2641j.c(a());
        if (Y.l(b())) {
            return (iC * 31) + 19;
        }
        return (iC * 31) + (c() ? 17 : b().hashCode());
    }

    public final String toString() {
        if (c()) {
            return "*";
        }
        if (a() == 1) {
            return b().toString();
        }
        return A7.k.x(a()) + " " + b();
    }
}
