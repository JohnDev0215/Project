package p;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class gq1 {
    public final Set a;
    public final long b;

    public gq1(Set set, long j) {
        v03.j(set, "uris");
        this.a = set;
        this.b = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gq1)) {
            return false;
        }
        gq1 gq1Var = (gq1) obj;
        return v03.b(this.a, gq1Var.a) && this.b == gq1Var.b;
    }

    public final int hashCode() {
        return Long.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OndemandResponseModel(uris=" + this.a + ", expireTimestampMillis=" + this.b + ')';
    }
}
