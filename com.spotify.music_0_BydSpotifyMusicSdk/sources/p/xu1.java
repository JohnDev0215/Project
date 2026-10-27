package p;

/* JADX INFO: loaded from: classes.dex */
public final class xu1 {
    public final String a;
    public final tu1 b;
    public final String c;
    public final zu1 d;
    public final String e;

    public xu1(String str, tu1 tu1Var, String str2, zu1 zu1Var, String str3) {
        this.a = str;
        this.b = tu1Var;
        this.c = str2;
        this.d = zu1Var;
        this.e = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xu1)) {
            return false;
        }
        xu1 xu1Var = (xu1) obj;
        return v03.b(this.a, xu1Var.a) && v03.b(this.b, xu1Var.b) && v03.b(this.c, xu1Var.c) && v03.b(this.d, xu1Var.d) && v03.b(this.e, xu1Var.e);
    }

    public final int hashCode() {
        String str = this.a;
        return this.e.hashCode() + ((this.d.a.hashCode() + hf2.b(this.c, (this.b.a.hashCode() + ((str == null ? 0 : str.hashCode()) * 31)) * 31, 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OnPlaylist(description=");
        sb.append(this.a);
        sb.append(", images=");
        sb.append(this.b);
        sb.append(", name=");
        sb.append(this.c);
        sb.append(", ownerV2=");
        sb.append(this.d);
        sb.append(", uri=");
        return hf2.d(sb, this.e, ')');
    }
}
