package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ts1 {
    public final String a;
    public final byte[] b;
    public final String c;
    public final boolean d;
    public final String e;
    public final long f;

    public ts1(long j, String str, String str2, String str3, boolean z, byte[] bArr) {
        v03.j(bArr, "payload");
        this.a = str;
        this.b = bArr;
        this.c = str2;
        this.d = z;
        this.e = str3;
        this.f = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(ts1.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.eventsender.eventsender.PendingEvent");
        ts1 ts1Var = (ts1) obj;
        return v03.b(this.a, ts1Var.a) && Arrays.equals(this.b, ts1Var.b) && ng2.b0(this.c, ts1Var.c, false) && this.d == ts1Var.d && ng2.b0(this.e, ts1Var.e, false) && this.f == ts1Var.f;
    }

    public final int hashCode() {
        int iHashCode = (Arrays.hashCode(this.b) + (this.a.hashCode() * 31)) * 31;
        String str = this.c;
        int iHashCode2 = (Boolean.hashCode(this.d) + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31)) * 31;
        String str2 = this.e;
        return Long.hashCode(this.f) + ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    public final String toString() {
        return "PendingEvent(eventName=" + this.a + ", payload=" + Arrays.toString(this.b) + ", overrideOwner=" + this.c + ", authenticated=" + this.d + ", clientContextId=" + this.e + ", timestamp=" + this.f + ')';
    }
}
