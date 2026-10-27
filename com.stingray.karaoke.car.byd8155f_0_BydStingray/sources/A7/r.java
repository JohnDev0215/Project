package A7;

/* JADX INFO: loaded from: classes.dex */
public final class r implements InterfaceC0057e {
    public final Class l;

    public r(Class cls, String str) {
        m.f(cls, "jClass");
        this.l = cls;
    }

    @Override // A7.InterfaceC0057e
    public final Class d() {
        return this.l;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof r) {
            if (m.a(this.l, ((r) obj).l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return this.l + " (Kotlin reflection is not available)";
    }
}
