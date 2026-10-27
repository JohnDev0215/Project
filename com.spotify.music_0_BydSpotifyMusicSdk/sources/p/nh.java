package p;

/* JADX INFO: loaded from: classes.dex */
public final class nh extends c40 {
    public final long a;
    public final String b;
    public final z30 c;
    public final a40 d;
    public final b40 e;

    public nh(long j, String str, z30 z30Var, a40 a40Var, b40 b40Var) {
        this.a = j;
        this.b = str;
        this.c = z30Var;
        this.d = a40Var;
        this.e = b40Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c40)) {
            return false;
        }
        nh nhVar = (nh) ((c40) obj);
        if (this.a == nhVar.a) {
            if (this.b.equals(nhVar.b) && this.c.equals(nhVar.c) && this.d.equals(nhVar.d)) {
                b40 b40Var = nhVar.e;
                b40 b40Var2 = this.e;
                if (b40Var2 == null) {
                    if (b40Var == null) {
                        return true;
                    }
                } else if (b40Var2.equals(b40Var)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.a;
        int iHashCode = (((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003;
        b40 b40Var = this.e;
        return iHashCode ^ (b40Var == null ? 0 : b40Var.hashCode());
    }

    public final String toString() {
        return "Event{timestamp=" + this.a + ", type=" + this.b + ", app=" + this.c + ", device=" + this.d + ", log=" + this.e + "}";
    }
}
