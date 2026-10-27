package p;

/* JADX INFO: loaded from: classes.dex */
public final class j5 {
    public final int a;

    public j5(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof j5) && this.a == ((j5) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    public final String toString() {
        return u4.k(new StringBuilder("Date(year="), this.a, ')');
    }
}
