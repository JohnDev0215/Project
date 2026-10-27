package p;

/* JADX INFO: loaded from: classes.dex */
public final class um2 {
    public final nm2 a;
    public final mm2 b;
    public final om2 c;
    public final String d;
    public final String e;
    public final int f;
    public final int g;
    public final sm2 h;
    public final qm2 i;

    public um2(nm2 nm2Var, mm2 mm2Var, om2 om2Var, String str, String str2, int i, int i2, sm2 sm2Var, qm2 qm2Var) {
        this.a = nm2Var;
        this.b = mm2Var;
        this.c = om2Var;
        this.d = str;
        this.e = str2;
        this.f = i;
        this.g = i2;
        this.h = sm2Var;
        this.i = qm2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof um2)) {
            return false;
        }
        um2 um2Var = (um2) obj;
        return v03.b(this.a, um2Var.a) && v03.b(this.b, um2Var.b) && v03.b(this.c, um2Var.c) && v03.b(this.d, um2Var.d) && v03.b(this.e, um2Var.e) && this.f == um2Var.f && this.g == um2Var.g && v03.b(this.h, um2Var.h) && v03.b(this.i, um2Var.i);
    }

    public final int hashCode() {
        int iHashCode = this.a.a.hashCode() * 31;
        mm2 mm2Var = this.b;
        int iHashCode2 = (iHashCode + (mm2Var == null ? 0 : mm2Var.hashCode())) * 31;
        om2 om2Var = this.c;
        return Integer.hashCode(this.i.a) + ((this.h.hashCode() + hf2.a(this.g, hf2.a(this.f, hf2.b(this.e, hf2.b(this.d, (iHashCode2 + (om2Var != null ? om2Var.hashCode() : 0)) * 31, 31), 31), 31), 31)) * 31);
    }

    public final String toString() {
        return "Track(artists=" + this.a + ", albumOfTrack=" + this.b + ", contentRating=" + this.c + ", name=" + this.d + ", uri=" + this.e + ", trackNumber=" + this.f + ", discNumber=" + this.g + ", playability=" + this.h + ", duration=" + this.i + ')';
    }
}
