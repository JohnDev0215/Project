package p;

/* JADX INFO: loaded from: classes.dex */
public final class oe0 {
    public final String a;

    public oe0(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof oe0) && v03.b(this.a, ((oe0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("ReleaseDate(isoString="), this.a, ')');
    }
}
