package p;

/* JADX INFO: loaded from: classes.dex */
public final class rx2 {
    public final String a;
    public final int b;

    public rx2(String str, int i) {
        v03.j(str, "workSpecId");
        this.a = str;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rx2)) {
            return false;
        }
        rx2 rx2Var = (rx2) obj;
        return v03.b(this.a, rx2Var.a) && this.b == rx2Var.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WorkGenerationalId(workSpecId=");
        sb.append(this.a);
        sb.append(", generation=");
        return u4.k(sb, this.b, ')');
    }
}
