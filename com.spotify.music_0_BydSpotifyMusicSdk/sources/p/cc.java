package p;

/* JADX INFO: loaded from: classes.dex */
public final class cc {
    public final String a;
    public final ec b;
    public final dc c;

    public cc(String str, ec ecVar, dc dcVar) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = ecVar;
        this.c = dcVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cc)) {
            return false;
        }
        cc ccVar = (cc) obj;
        return v03.b(this.a, ccVar.a) && v03.b(this.b, ccVar.b) && v03.b(this.c, ccVar.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        ec ecVar = this.b;
        int iHashCode2 = (iHashCode + (ecVar == null ? 0 : ecVar.hashCode())) * 31;
        dc dcVar = this.c;
        return iHashCode2 + (dcVar != null ? dcVar.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", onArtist=" + this.c + ')';
    }
}
