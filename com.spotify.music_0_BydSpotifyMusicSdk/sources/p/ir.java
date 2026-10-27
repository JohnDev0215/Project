package p;

/* JADX INFO: loaded from: classes.dex */
public final class ir {
    public final String a;
    public final lr b;
    public final kr c;

    public ir(String str, lr lrVar, kr krVar) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = lrVar;
        this.c = krVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ir)) {
            return false;
        }
        ir irVar = (ir) obj;
        return v03.b(this.a, irVar.a) && v03.b(this.b, irVar.b) && v03.b(this.c, irVar.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        lr lrVar = this.b;
        int iHashCode2 = (iHashCode + (lrVar == null ? 0 : lrVar.hashCode())) * 31;
        kr krVar = this.c;
        return iHashCode2 + (krVar != null ? krVar.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", onChapter=" + this.c + ')';
    }
}
