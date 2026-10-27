package p;

/* JADX INFO: loaded from: classes.dex */
public final class d90 {
    public final dz1 a;
    public final int b;
    public final int c;

    public d90(int i, int i2, Class cls) {
        this(dz1.a(cls), i, i2);
    }

    public static d90 a(Class cls) {
        return new d90(1, 0, cls);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d90)) {
            return false;
        }
        d90 d90Var = (d90) obj;
        return this.a.equals(d90Var.a) && this.b == d90Var.b && this.c == d90Var.c;
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.a);
        sb.append(", type=");
        int i = this.b;
        sb.append(i == 1 ? "required" : i == 0 ? "optional" : "set");
        sb.append(", injection=");
        int i2 = this.c;
        if (i2 == 0) {
            str = "direct";
        } else if (i2 == 1) {
            str = "provider";
        } else {
            if (i2 != 2) {
                throw new AssertionError(u4.e("Unsupported injection: ", i2));
            }
            str = "deferred";
        }
        return u4.m(sb, str, "}");
    }

    public d90(dz1 dz1Var, int i, int i2) {
        this.a = dz1Var;
        this.b = i;
        this.c = i2;
    }
}
