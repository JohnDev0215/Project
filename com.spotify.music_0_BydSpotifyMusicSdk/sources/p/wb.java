package p;

/* JADX INFO: loaded from: classes.dex */
public final class wb {
    public final String a;
    public final ub b;
    public final vb c;

    public wb(String str, ub ubVar, vb vbVar) {
        this.a = str;
        this.b = ubVar;
        this.c = vbVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wb)) {
            return false;
        }
        wb wbVar = (wb) obj;
        return v03.b(this.a, wbVar.a) && v03.b(this.b, wbVar.b) && v03.b(this.c, wbVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + hf2.b(this.b.a, this.a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "Artist(uri=" + this.a + ", profile=" + this.b + ", visuals=" + this.c + ')';
    }
}
