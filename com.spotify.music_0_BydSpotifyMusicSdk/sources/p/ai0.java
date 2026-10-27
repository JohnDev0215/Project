package p;

import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class ai0 {
    public final long a;
    public final String b;
    public final ByteString c;
    public final long d;
    public final Iterable e;

    public ai0(long j, String str, ByteString byteString, long j2, Iterable iterable) {
        v03.j(str, "eventName");
        v03.j(iterable, "fragments");
        this.a = j;
        this.b = str;
        this.c = byteString;
        this.d = j2;
        this.e = iterable;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ai0)) {
            return false;
        }
        ai0 ai0Var = (ai0) obj;
        return this.a == ai0Var.a && v03.b(this.b, ai0Var.b) && v03.b(this.c, ai0Var.c) && this.d == ai0Var.d && v03.b(this.e, ai0Var.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((Long.hashCode(this.d) + ((this.c.hashCode() + hf2.b(this.b, Long.hashCode(this.a) * 31, 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Event(internalId=" + this.a + ", eventName=" + this.b + ", sequenceId=" + this.c + ", sequenceNumber=" + this.d + ", fragments=" + this.e + ')';
    }
}
