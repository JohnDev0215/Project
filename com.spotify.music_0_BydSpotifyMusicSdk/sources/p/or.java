package p;

/* JADX INFO: loaded from: classes.dex */
public final class or {
    public final String a;

    public or(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof or) && v03.b(this.a, ((or) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("ReleaseDate(isoString="), this.a, ')');
    }
}
