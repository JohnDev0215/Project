package p;

/* JADX INFO: loaded from: classes.dex */
public final class ke {
    public final String a;
    public final le b;
    public final fe c;

    public ke(String str, le leVar, fe feVar) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = leVar;
        this.c = feVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ke)) {
            return false;
        }
        ke keVar = (ke) obj;
        return v03.b(this.a, keVar.a) && v03.b(this.b, keVar.b) && v03.b(this.c, keVar.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        le leVar = this.b;
        int iHashCode2 = (iHashCode + (leVar == null ? 0 : leVar.hashCode())) * 31;
        fe feVar = this.c;
        return iHashCode2 + (feVar != null ? feVar.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", audiobookInfo=" + this.c + ')';
    }
}
