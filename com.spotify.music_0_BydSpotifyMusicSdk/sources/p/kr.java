package p;

/* JADX INFO: loaded from: classes.dex */
public final class kr {
    public final String a;
    public final String b;
    public final hr c;
    public final or d;
    public final jr e;
    public final gr f;
    public final mr g;
    public final nr h;

    public kr(String str, String str2, hr hrVar, or orVar, jr jrVar, gr grVar, mr mrVar, nr nrVar) {
        this.a = str;
        this.b = str2;
        this.c = hrVar;
        this.d = orVar;
        this.e = jrVar;
        this.f = grVar;
        this.g = mrVar;
        this.h = nrVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof kr)) {
            return false;
        }
        kr krVar = (kr) obj;
        return v03.b(this.a, krVar.a) && v03.b(this.b, krVar.b) && v03.b(this.c, krVar.c) && v03.b(this.d, krVar.d) && v03.b(this.e, krVar.e) && v03.b(this.f, krVar.f) && v03.b(this.g, krVar.g) && v03.b(this.h, krVar.h);
    }

    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        hr hrVar = this.c;
        int iHashCode = (iB + (hrVar == null ? 0 : hrVar.hashCode())) * 31;
        or orVar = this.d;
        int iA = hf2.a(this.e.a, (iHashCode + (orVar == null ? 0 : orVar.a.hashCode())) * 31, 31);
        gr grVar = this.f;
        return this.h.hashCode() + ((this.g.hashCode() + ((iA + (grVar != null ? grVar.hashCode() : 0)) * 31)) * 31);
    }

    public final String toString() {
        return "OnChapter(name=" + this.a + ", uri=" + this.b + ", coverArt=" + this.c + ", releaseDate=" + this.d + ", duration=" + this.e + ", contentRating=" + this.f + ", playability=" + this.g + ", playedState=" + this.h + ')';
    }
}
