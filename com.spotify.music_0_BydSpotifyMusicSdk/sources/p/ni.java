package p;

import com.google.android.datatransport.Priority;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ni {
    public final yt a;
    public final Map b;

    public ni(yt ytVar, Map map) {
        if (ytVar == null) {
            throw new NullPointerException("Null clock");
        }
        this.a = ytVar;
        if (map == null) {
            throw new NullPointerException("Null values");
        }
        this.b = map;
    }

    public final long a(Priority priority, long j, int i) {
        long jA = j - ((lr2) this.a).a();
        oi oiVar = (oi) this.b.get(priority);
        long j2 = oiVar.a;
        return Math.min(Math.max((long) (Math.pow(3.0d, i - 1) * j2 * Math.max(1.0d, Math.log(10000.0d) / Math.log((j2 > 1 ? j2 : 2L) * ((long) r12)))), jA), oiVar.b);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ni)) {
            return false;
        }
        ni niVar = (ni) obj;
        return this.a.equals(niVar.a) && this.b.equals(niVar.b);
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public final String toString() {
        return "SchedulerConfig{clock=" + this.a + ", values=" + this.b + "}";
    }
}
