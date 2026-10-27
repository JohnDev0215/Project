package p;

/* JADX INFO: loaded from: classes.dex */
public final class h31 {
    public final String a;
    public final Integer b;
    public final Integer c;

    public h31(String str, Integer num, Integer num2) {
        this.a = str;
        this.b = num;
        this.c = num2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h31)) {
            return false;
        }
        h31 h31Var = (h31) obj;
        return v03.b(this.a, h31Var.a) && v03.b(this.b, h31Var.b) && v03.b(this.c, h31Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        Integer num = this.b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.c;
        return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public final String toString() {
        return "Source(url=" + this.a + ", width=" + this.b + ", height=" + this.c + ')';
    }
}
