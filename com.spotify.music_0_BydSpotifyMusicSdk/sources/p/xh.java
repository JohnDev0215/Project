package p;

/* JADX INFO: loaded from: classes.dex */
public final class xh extends d40 {
    public final int a;
    public final String b;
    public final String c;
    public final boolean d;

    public xh(int i, String str, String str2, boolean z) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d40)) {
            return false;
        }
        d40 d40Var = (d40) obj;
        if (this.a == ((xh) d40Var).a) {
            xh xhVar = (xh) d40Var;
            if (this.b.equals(xhVar.b) && this.c.equals(xhVar.c) && this.d == xhVar.d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.a ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ (this.d ? 1231 : 1237);
    }

    public final String toString() {
        return "OperatingSystem{platform=" + this.a + ", version=" + this.b + ", buildVersion=" + this.c + ", jailbroken=" + this.d + "}";
    }
}
