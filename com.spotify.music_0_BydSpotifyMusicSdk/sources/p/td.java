package p;

/* JADX INFO: loaded from: classes.dex */
public final class td {
    public final String a;
    public final pr b;

    public td(String str, pr prVar) {
        this.a = str;
        this.b = prVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof td)) {
            return false;
        }
        td tdVar = (td) obj;
        return v03.b(this.a, tdVar.a) && v03.b(this.b, tdVar.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "TrailerV2(__typename=" + this.a + ", chapterWrapper=" + this.b + ')';
    }
}
