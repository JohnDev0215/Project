package p;

/* JADX INFO: loaded from: classes.dex */
public final class pd {
    public final String a;
    public final sd b;

    public pd(String str, sd sdVar) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = sdVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pd)) {
            return false;
        }
        pd pdVar = (pd) obj;
        return v03.b(this.a, pdVar.a) && v03.b(this.b, pdVar.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        sd sdVar = this.b;
        return iHashCode + (sdVar == null ? 0 : sdVar.a.hashCode());
    }

    public final String toString() {
        return "ChaptersV2(__typename=" + this.a + ", onContextChapterPage=" + this.b + ')';
    }
}
