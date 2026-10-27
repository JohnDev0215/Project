package p;

/* JADX INFO: loaded from: classes.dex */
public final class ud {
    public final String a;
    public final od b;
    public final pd c;
    public final td d;

    public ud(String str, od odVar, pd pdVar, td tdVar) {
        this.a = str;
        this.b = odVar;
        this.c = pdVar;
        this.d = tdVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ud)) {
            return false;
        }
        ud udVar = (ud) obj;
        return v03.b(this.a, udVar.a) && v03.b(this.b, udVar.b) && v03.b(this.c, udVar.c) && v03.b(this.d, udVar.d);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        od odVar = this.b;
        int iHashCode2 = (iHashCode + (odVar == null ? 0 : odVar.hashCode())) * 31;
        pd pdVar = this.c;
        int iHashCode3 = (iHashCode2 + (pdVar == null ? 0 : pdVar.hashCode())) * 31;
        td tdVar = this.d;
        return iHashCode3 + (tdVar != null ? tdVar.hashCode() : 0);
    }

    public final String toString() {
        return "Audiobook(name=" + this.a + ", accessInfo=" + this.b + ", chaptersV2=" + this.c + ", trailerV2=" + this.d + ')';
    }
}
