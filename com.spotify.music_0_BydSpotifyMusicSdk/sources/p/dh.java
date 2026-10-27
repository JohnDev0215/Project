package p;

/* JADX INFO: loaded from: classes.dex */
public final class dh extends m30 {
    public final int a;
    public final String b;
    public final int c;
    public final int d;
    public final long e;
    public final long f;
    public final long g;
    public final String h;
    public final z31 i;

    public dh(int i, String str, int i2, int i3, long j, long j2, long j3, String str2, z31 z31Var) {
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = j;
        this.f = j2;
        this.g = j3;
        this.h = str2;
        this.i = z31Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m30)) {
            return false;
        }
        m30 m30Var = (m30) obj;
        if (this.a == ((dh) m30Var).a) {
            dh dhVar = (dh) m30Var;
            if (this.b.equals(dhVar.b) && this.c == dhVar.c && this.d == dhVar.d && this.e == dhVar.e && this.f == dhVar.f && this.g == dhVar.g) {
                String str = dhVar.h;
                String str2 = this.h;
                if (str2 != null ? str2.equals(str) : str == null) {
                    z31 z31Var = dhVar.i;
                    z31 z31Var2 = this.i;
                    if (z31Var2 == null) {
                        if (z31Var == null) {
                            return true;
                        }
                    } else if (z31Var2.c.equals(z31Var)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (((((((this.a ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c) * 1000003) ^ this.d) * 1000003;
        long j = this.e;
        int i = (iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.f;
        int i2 = (i ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        long j3 = this.g;
        int i3 = (i2 ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003;
        String str = this.h;
        int iHashCode2 = (i3 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        z31 z31Var = this.i;
        return iHashCode2 ^ (z31Var != null ? z31Var.c.hashCode() : 0);
    }

    public final String toString() {
        return "ApplicationExitInfo{pid=" + this.a + ", processName=" + this.b + ", reasonCode=" + this.c + ", importance=" + this.d + ", pss=" + this.e + ", rss=" + this.f + ", timestamp=" + this.g + ", traceFile=" + this.h + ", buildIdMappingForArch=" + this.i + "}";
    }
}
