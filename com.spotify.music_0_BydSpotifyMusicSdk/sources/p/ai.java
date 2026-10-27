package p;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ai {
    public final String a;
    public final Integer b;
    public final xd0 c;
    public final long d;
    public final long e;
    public final Map f;

    public ai(String str, Integer num, xd0 xd0Var, long j, long j2, Map map) {
        this.a = str;
        this.b = num;
        this.c = xd0Var;
        this.d = j;
        this.e = j2;
        this.f = map;
    }

    public final String a(String str) {
        String str2 = (String) this.f.get(str);
        return str2 == null ? "" : str2;
    }

    public final int b(String str) {
        String str2 = (String) this.f.get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public final gu0 c() {
        gu0 gu0Var = new gu0(4);
        String str = this.a;
        if (str == null) {
            throw new NullPointerException("Null transportName");
        }
        gu0Var.a = str;
        gu0Var.b = this.b;
        gu0Var.r(this.c);
        gu0Var.d = Long.valueOf(this.d);
        gu0Var.e = Long.valueOf(this.e);
        gu0Var.f = new HashMap(this.f);
        return gu0Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ai)) {
            return false;
        }
        ai aiVar = (ai) obj;
        if (this.a.equals(aiVar.a)) {
            Integer num = aiVar.b;
            Integer num2 = this.b;
            if (num2 != null ? num2.equals(num) : num == null) {
                if (this.c.equals(aiVar.c) && this.d == aiVar.d && this.e == aiVar.e && this.f.equals(aiVar.f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() ^ 1000003) * 1000003;
        Integer num = this.b;
        int iHashCode2 = (((iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.c.hashCode()) * 1000003;
        long j = this.d;
        int i = (iHashCode2 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.e;
        return ((i ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.f.hashCode();
    }

    public final String toString() {
        return "EventInternal{transportName=" + this.a + ", code=" + this.b + ", encodedPayload=" + this.c + ", eventMillis=" + this.d + ", uptimeMillis=" + this.e + ", autoMetadata=" + this.f + "}";
    }
}
