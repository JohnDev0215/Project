package p;

/* JADX INFO: loaded from: classes.dex */
public final class pr1 {
    public final Object a;
    public final Object b;

    public pr1(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    public static pr1 a(String str, String str2) {
        return new pr1(str, str2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof pr1)) {
            return false;
        }
        pr1 pr1Var = (pr1) obj;
        return ap1.a(pr1Var.a, this.a) && ap1.a(pr1Var.b, this.b);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.b;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "Pair{" + this.a + " " + this.b + "}";
    }
}
