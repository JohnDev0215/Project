package p;

import com.spotify.performancesdk.timekeeper.TimeMeasurementError$Code;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ns2 extends qs2 {
    public final Map b;

    public ns2(LinkedHashMap linkedHashMap) {
        super(TimeMeasurementError$Code.INVALID_DIMENSIONS);
        this.b = linkedHashMap;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ns2) && v03.b(this.b, ((ns2) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return "InvalidDimensions(invalidDimensions=" + this.b + ')';
    }
}
