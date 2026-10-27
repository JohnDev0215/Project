package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ik0 {
    public final String a;
    public final String b;
    public final byte[] c;
    public final String d;
    public final Long e;
    public final long f;
    public final long g;

    public ik0(String str, String str2, byte[] bArr, String str3, Long l, long j, long j2) {
        v03.j(str, "eventId");
        v03.j(str2, "eventName");
        v03.j(bArr, "sequenceId");
        v03.j(str3, "sequenceStr");
        this.a = str;
        this.b = str2;
        this.c = bArr;
        this.d = str3;
        this.e = l;
        this.f = j;
        this.g = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(ik0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.eventsender.eventsender.dao.EventStatsData");
        ik0 ik0Var = (ik0) obj;
        return v03.b(this.a, ik0Var.a) && v03.b(this.b, ik0Var.b) && Arrays.equals(this.c, ik0Var.c) && v03.b(this.d, ik0Var.d) && v03.b(this.e, ik0Var.e) && this.f == ik0Var.f && this.g == ik0Var.g;
    }

    public final int hashCode() {
        int iB = hf2.b(this.d, (Arrays.hashCode(this.c) + hf2.b(this.a, this.b.hashCode() * 31, 31)) * 31, 31);
        Long l = this.e;
        return Long.hashCode(this.g) + ((Long.hashCode(this.f) + ((iB + (l != null ? l.hashCode() : 0)) * 31)) * 31);
    }

    public final String toString() {
        return "EventStatsData(eventId=" + this.a + ", eventName=" + this.b + ", sequenceId=" + Arrays.toString(this.c) + ", sequenceStr=" + this.d + ", sequenceNumberMin=" + this.e + ", sequenceNumberNext=" + this.f + ", storageSize=" + this.g + ')';
    }
}
