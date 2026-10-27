package p;

/* JADX INFO: loaded from: classes.dex */
public final class aq2 extends f60 {
    public final String h;

    public aq2(String str) {
        v03.j(str, "query");
        this.h = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof aq2) && v03.b(this.h, ((aq2) obj).h);
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("PlayFromSearch(query="), this.h, ')');
    }
}
