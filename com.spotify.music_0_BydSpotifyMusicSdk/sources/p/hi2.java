package p;

/* JADX INFO: loaded from: classes.dex */
public final class hi2 {
    public final String a;
    public final int b;
    public final int c;

    public hi2(String str, int i, int i2) {
        v03.j(str, "workSpecId");
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hi2)) {
            return false;
        }
        hi2 hi2Var = (hi2) obj;
        return v03.b(this.a, hi2Var.a) && this.b == hi2Var.b && this.c == hi2Var.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + hf2.a(this.b, this.a.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SystemIdInfo(workSpecId=");
        sb.append(this.a);
        sb.append(", generation=");
        sb.append(this.b);
        sb.append(", systemId=");
        return u4.k(sb, this.c, ')');
    }
}
