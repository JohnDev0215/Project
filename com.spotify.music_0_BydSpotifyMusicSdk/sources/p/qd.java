package p;

/* JADX INFO: loaded from: classes.dex */
public final class qd {
    public final String a;
    public final pr b;

    public qd(String str, pr prVar) {
        this.a = str;
        this.b = prVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof qd)) {
            return false;
        }
        qd qdVar = (qd) obj;
        return v03.b(this.a, qdVar.a) && v03.b(this.b, qdVar.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Entity(__typename=" + this.a + ", chapterWrapper=" + this.b + ')';
    }
}
