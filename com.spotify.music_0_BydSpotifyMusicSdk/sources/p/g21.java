package p;

/* JADX INFO: loaded from: classes.dex */
public final class g21 {
    public final String a;
    public final String b;

    public g21(String str, String str2) {
        v03.j(str, "name");
        v03.j(str2, "value");
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g21)) {
            return false;
        }
        g21 g21Var = (g21) obj;
        return v03.b(this.a, g21Var.a) && v03.b(this.b, g21Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("HttpHeader(name=");
        sb.append(this.a);
        sb.append(", value=");
        return hf2.d(sb, this.b, ')');
    }
}
