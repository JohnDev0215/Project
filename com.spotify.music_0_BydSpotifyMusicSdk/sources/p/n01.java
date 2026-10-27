package p;

/* JADX INFO: loaded from: classes.dex */
public final class n01 {
    public final String a;
    public final String b;
    public final Object c;
    public final Long d;

    public n01(String str, String str2, Object obj, Long l) {
        v03.j(str, "identifier");
        this.a = str;
        this.b = str2;
        this.c = obj;
        this.d = l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n01)) {
            return false;
        }
        n01 n01Var = (n01) obj;
        return v03.b(this.a, n01Var.a) && v03.b(this.b, n01Var.b) && v03.b(this.c, n01Var.c) && v03.b(this.d, n01Var.d);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        String str = this.b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Object obj = this.c;
        int iHashCode3 = (iHashCode2 + (obj == null ? 0 : obj.hashCode())) * 31;
        Long l = this.d;
        return iHashCode3 + (l != null ? l.hashCode() : 0);
    }

    public final String toString() {
        return "GenericPoint(identifier=" + this.a + ", featureId=" + this.b + ", timestamp=" + this.c + ", duration=" + this.d + ')';
    }
}
