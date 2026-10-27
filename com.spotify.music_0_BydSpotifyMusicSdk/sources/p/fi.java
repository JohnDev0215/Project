package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class fi extends kc1 {
    public final long a;
    public final Integer b;
    public final long c;
    public final byte[] d;
    public final String e;
    public final long f;
    public final wm1 g;

    public fi(long j, Integer num, long j2, byte[] bArr, String str, long j3, wm1 wm1Var) {
        this.a = j;
        this.b = num;
        this.c = j2;
        this.d = bArr;
        this.e = str;
        this.f = j3;
        this.g = wm1Var;
    }

    public final boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kc1)) {
            return false;
        }
        kc1 kc1Var = (kc1) obj;
        fi fiVar = (fi) kc1Var;
        if (this.a == fiVar.a && ((num = this.b) != null ? num.equals(fiVar.b) : fiVar.b == null)) {
            if (this.c == fiVar.c) {
                if (Arrays.equals(this.d, kc1Var instanceof fi ? ((fi) kc1Var).d : fiVar.d)) {
                    String str = fiVar.e;
                    String str2 = this.e;
                    if (str2 != null ? str2.equals(str) : str == null) {
                        if (this.f == fiVar.f) {
                            wm1 wm1Var = fiVar.g;
                            wm1 wm1Var2 = this.g;
                            if (wm1Var2 == null) {
                                if (wm1Var == null) {
                                    return true;
                                }
                            } else if (wm1Var2.equals(wm1Var)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.a;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.b;
        int iHashCode = (i ^ (num == null ? 0 : num.hashCode())) * 1000003;
        long j2 = this.c;
        int iHashCode2 = (((iHashCode ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.d)) * 1000003;
        String str = this.e;
        int iHashCode3 = (iHashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j3 = this.f;
        int i2 = (iHashCode3 ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003;
        wm1 wm1Var = this.g;
        return i2 ^ (wm1Var != null ? wm1Var.hashCode() : 0);
    }

    public final String toString() {
        return "LogEvent{eventTimeMs=" + this.a + ", eventCode=" + this.b + ", eventUptimeMs=" + this.c + ", sourceExtension=" + Arrays.toString(this.d) + ", sourceExtensionJsonProto3=" + this.e + ", timezoneOffsetSeconds=" + this.f + ", networkConnectionInfo=" + this.g + "}";
    }
}
