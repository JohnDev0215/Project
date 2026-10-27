package p;

/* JADX INFO: loaded from: classes.dex */
public final class jr {
    public final int a;

    public jr(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof jr) && this.a == ((jr) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    public final String toString() {
        return u4.k(new StringBuilder("Duration(totalMilliseconds="), this.a, ')');
    }
}
