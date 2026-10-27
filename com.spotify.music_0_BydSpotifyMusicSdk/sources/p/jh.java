package p;

/* JADX INFO: loaded from: classes.dex */
public final class jh extends f40 {
    public final String a;
    public final String b;
    public final long c;
    public final Long d;
    public final boolean e;
    public final r30 f;
    public final e40 g;
    public final d40 h;
    public final s30 i;
    public final z31 j;
    public final int k;

    public jh(String str, String str2, long j, Long l, boolean z, r30 r30Var, e40 e40Var, d40 d40Var, s30 s30Var, z31 z31Var, int i) {
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = l;
        this.e = z;
        this.f = r30Var;
        this.g = e40Var;
        this.h = d40Var;
        this.i = s30Var;
        this.j = z31Var;
        this.k = i;
    }

    @Override // p.f40
    public final ih a() {
        ih ihVar = new ih();
        ihVar.a = this.a;
        ihVar.b = this.b;
        ihVar.c = Long.valueOf(this.c);
        ihVar.d = this.d;
        ihVar.e = Boolean.valueOf(this.e);
        ihVar.f = this.f;
        ihVar.g = this.g;
        ihVar.h = this.h;
        ihVar.i = this.i;
        ihVar.j = this.j;
        ihVar.k = Integer.valueOf(this.k);
        return ihVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f40)) {
            return false;
        }
        jh jhVar = (jh) ((f40) obj);
        if (this.a.equals(jhVar.a)) {
            if (this.b.equals(jhVar.b) && this.c == jhVar.c) {
                Long l = jhVar.d;
                Long l2 = this.d;
                if (l2 != null ? l2.equals(l) : l == null) {
                    if (this.e == jhVar.e && this.f.equals(jhVar.f)) {
                        e40 e40Var = jhVar.g;
                        e40 e40Var2 = this.g;
                        if (e40Var2 != null ? e40Var2.equals(e40Var) : e40Var == null) {
                            d40 d40Var = jhVar.h;
                            d40 d40Var2 = this.h;
                            if (d40Var2 != null ? d40Var2.equals(d40Var) : d40Var == null) {
                                s30 s30Var = jhVar.i;
                                s30 s30Var2 = this.i;
                                if (s30Var2 != null ? s30Var2.equals(s30Var) : s30Var == null) {
                                    z31 z31Var = jhVar.j;
                                    z31 z31Var2 = this.j;
                                    if (z31Var2 != null ? z31Var2.c.equals(z31Var) : z31Var == null) {
                                        if (this.k == jhVar.k) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        long j = this.c;
        int i = (iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        Long l = this.d;
        int iHashCode2 = (((((i ^ (l == null ? 0 : l.hashCode())) * 1000003) ^ (this.e ? 1231 : 1237)) * 1000003) ^ this.f.hashCode()) * 1000003;
        e40 e40Var = this.g;
        int iHashCode3 = (iHashCode2 ^ (e40Var == null ? 0 : e40Var.hashCode())) * 1000003;
        d40 d40Var = this.h;
        int iHashCode4 = (iHashCode3 ^ (d40Var == null ? 0 : d40Var.hashCode())) * 1000003;
        s30 s30Var = this.i;
        int iHashCode5 = (iHashCode4 ^ (s30Var == null ? 0 : s30Var.hashCode())) * 1000003;
        z31 z31Var = this.j;
        return ((iHashCode5 ^ (z31Var != null ? z31Var.c.hashCode() : 0)) * 1000003) ^ this.k;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Session{generator=");
        sb.append(this.a);
        sb.append(", identifier=");
        sb.append(this.b);
        sb.append(", startedAt=");
        sb.append(this.c);
        sb.append(", endedAt=");
        sb.append(this.d);
        sb.append(", crashed=");
        sb.append(this.e);
        sb.append(", app=");
        sb.append(this.f);
        sb.append(", user=");
        sb.append(this.g);
        sb.append(", os=");
        sb.append(this.h);
        sb.append(", device=");
        sb.append(this.i);
        sb.append(", events=");
        sb.append(this.j);
        sb.append(", generatorType=");
        return u4.l(sb, this.k, "}");
    }
}
