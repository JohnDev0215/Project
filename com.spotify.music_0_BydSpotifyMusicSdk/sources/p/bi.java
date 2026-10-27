package p;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;

/* JADX INFO: loaded from: classes.dex */
public final class bi {
    public static final bi f;
    public final long a;
    public final int b;
    public final int c;
    public final long d;
    public final int e;

    static {
        Long l = 10485760L;
        Integer num = 200;
        Integer numValueOf = Integer.valueOf(WebgateAuthorizer.COSMOS_TIMEOUT_MS);
        Long l2 = 604800000L;
        Integer num2 = 81920;
        String strH = l == null ? " maxStorageSizeInBytes" : "";
        if (num == null) {
            strH = strH.concat(" loadBatchSize");
        }
        if (numValueOf == null) {
            strH = u4.h(strH, " criticalSectionEnterTimeoutMs");
        }
        if (l2 == null) {
            strH = u4.h(strH, " eventCleanUpAge");
        }
        if (num2 == null) {
            strH = u4.h(strH, " maxBlobByteSizePerRow");
        }
        if (!strH.isEmpty()) {
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }
        f = new bi(l.longValue(), num.intValue(), numValueOf.intValue(), l2.longValue(), num2.intValue());
    }

    public bi(long j, int i, int i2, long j2, int i3) {
        this.a = j;
        this.b = i;
        this.c = i2;
        this.d = j2;
        this.e = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof bi)) {
            return false;
        }
        bi biVar = (bi) obj;
        return this.a == biVar.a && this.b == biVar.b && this.c == biVar.c && this.d == biVar.d && this.e == biVar.e;
    }

    public final int hashCode() {
        long j = this.a;
        int i = (((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c) * 1000003;
        long j2 = this.d;
        return ((i ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("EventStoreConfig{maxStorageSizeInBytes=");
        sb.append(this.a);
        sb.append(", loadBatchSize=");
        sb.append(this.b);
        sb.append(", criticalSectionEnterTimeoutMs=");
        sb.append(this.c);
        sb.append(", eventCleanUpAge=");
        sb.append(this.d);
        sb.append(", maxBlobByteSizePerRow=");
        return u4.l(sb, this.e, "}");
    }
}
