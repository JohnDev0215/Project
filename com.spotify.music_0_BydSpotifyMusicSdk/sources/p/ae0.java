package p;

/* JADX INFO: loaded from: classes.dex */
public final class ae0 {
    public final String a;

    public ae0(String str) {
        if (str == null) {
            throw new NullPointerException("name is null");
        }
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ae0)) {
            return false;
        }
        return this.a.equals(((ae0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return u4.m(new StringBuilder("Encoding{name=\""), this.a, "\"}");
    }
}
