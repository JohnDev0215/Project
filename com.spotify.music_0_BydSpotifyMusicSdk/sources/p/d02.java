package p;

/* JADX INFO: loaded from: classes.dex */
public final class d02 {
    public final String a;
    public final int b;
    public final long c;

    public d02(int i, long j, String str) {
        v03.j(str, "eventName");
        this.a = str;
        this.b = i;
        this.c = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d02)) {
            return false;
        }
        d02 d02Var = (d02) obj;
        return v03.b(this.a, d02Var.a) && this.b == d02Var.b && this.c == d02Var.c;
    }

    public final int hashCode() {
        return Long.hashCode(this.c) + hf2.a(this.b, this.a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "RateLimitedEventEntity(eventName=" + this.a + ", count=" + this.b + ", timestamp=" + this.c + ')';
    }
}
