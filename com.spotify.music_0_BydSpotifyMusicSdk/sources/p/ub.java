package p;

/* JADX INFO: loaded from: classes.dex */
public final class ub {
    public final String a;

    public ub(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ub) && v03.b(this.a, ((ub) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Profile(name="), this.a, ')');
    }
}
