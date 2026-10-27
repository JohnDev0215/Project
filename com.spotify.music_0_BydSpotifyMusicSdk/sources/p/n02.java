package p;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class n02 {
    public final boolean a;
    public final Set b;
    public final long c;
    public final String d;

    public n02(boolean z, Set set, long j, String str) {
        v03.j(set, "policyGroupIds");
        v03.j(str, "configurationAssignmentId");
        this.a = z;
        this.b = set;
        this.c = j;
        this.d = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n02)) {
            return false;
        }
        n02 n02Var = (n02) obj;
        return this.a == n02Var.a && v03.b(this.b, n02Var.b) && this.c == n02Var.c && v03.b(this.d, n02Var.d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public final int hashCode() {
        boolean z = this.a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return this.d.hashCode() + ((Long.hashCode(this.c) + ((this.b.hashCode() + (r0 * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RawConfigurationMetadata(isDefault=");
        sb.append(this.a);
        sb.append(", policyGroupIds=");
        sb.append(this.b);
        sb.append(", timestamp=");
        sb.append(this.c);
        sb.append(", configurationAssignmentId=");
        return hf2.d(sb, this.d, ')');
    }
}
