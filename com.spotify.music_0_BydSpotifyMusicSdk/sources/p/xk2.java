package p;

import com.spotify.performancesdk.timekeeper.TimeMeasurementError$Code;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class xk2 {
    public final TimeMeasurementError$Code a;
    public final Map b;
    public final Set c;

    public xk2(TimeMeasurementError$Code timeMeasurementError$Code, Map map, Set set) {
        v03.j(timeMeasurementError$Code, "errorCode");
        v03.j(set, "ongoingPoints");
        this.a = timeMeasurementError$Code;
        this.b = map;
        this.c = set;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xk2)) {
            return false;
        }
        xk2 xk2Var = (xk2) obj;
        return this.a == xk2Var.a && v03.b(this.b, xk2Var.b) && v03.b(this.c, xk2Var.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "TimeMeasurementError(errorCode=" + this.a + ", errorData=" + this.b + ", ongoingPoints=" + this.c + ')';
    }
}
