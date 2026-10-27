package p;

import com.google.android.datatransport.cct.internal.QosTier;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class gi extends oc1 {
    public final long a;
    public final long b;
    public final vt c;
    public final Integer d;
    public final String e;
    public final List f;
    public final QosTier g;

    public gi(long j, long j2, vt vtVar, Integer num, String str, List list, QosTier qosTier) {
        this.a = j;
        this.b = j2;
        this.c = vtVar;
        this.d = num;
        this.e = str;
        this.f = list;
        this.g = qosTier;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof oc1)) {
            return false;
        }
        gi giVar = (gi) ((oc1) obj);
        if (this.a == giVar.a) {
            if (this.b == giVar.b) {
                vt vtVar = giVar.c;
                vt vtVar2 = this.c;
                if (vtVar2 != null ? vtVar2.equals(vtVar) : vtVar == null) {
                    Integer num = giVar.d;
                    Integer num2 = this.d;
                    if (num2 != null ? num2.equals(num) : num == null) {
                        String str = giVar.e;
                        String str2 = this.e;
                        if (str2 != null ? str2.equals(str) : str == null) {
                            List list = giVar.f;
                            List list2 = this.f;
                            if (list2 != null ? list2.equals(list) : list == null) {
                                QosTier qosTier = giVar.g;
                                QosTier qosTier2 = this.g;
                                if (qosTier2 == null) {
                                    if (qosTier == null) {
                                        return true;
                                    }
                                } else if (qosTier2.equals(qosTier)) {
                                    return true;
                                }
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
        long j2 = this.b;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        vt vtVar = this.c;
        int iHashCode = (i ^ (vtVar == null ? 0 : vtVar.hashCode())) * 1000003;
        Integer num = this.d;
        int iHashCode2 = (iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.e;
        int iHashCode3 = (iHashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List list = this.f;
        int iHashCode4 = (iHashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        QosTier qosTier = this.g;
        return iHashCode4 ^ (qosTier != null ? qosTier.hashCode() : 0);
    }

    public final String toString() {
        return "LogRequest{requestTimeMs=" + this.a + ", requestUptimeMs=" + this.b + ", clientInfo=" + this.c + ", logSource=" + this.d + ", logSourceName=" + this.e + ", logEvents=" + this.f + ", qosTier=" + this.g + "}";
    }
}
