package p;

/* JADX INFO: loaded from: classes.dex */
public final class jr1 implements ts {
    public final Class a;

    public jr1(Class cls) {
        v03.j(cls, "jClass");
        this.a = cls;
    }

    @Override // p.ts
    public final Class a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof jr1) {
            if (v03.b(this.a, ((jr1) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString() + " (Kotlin reflection is not available)";
    }
}
