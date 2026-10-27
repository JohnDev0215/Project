package p;

import com.spotify.performancesdk.timekeeper.TimeMeasurementError$Code;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class ms2 extends qs2 {
    public final Set b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ms2(Set set) {
        super(TimeMeasurementError$Code.DUPLICATED_POINTS);
        v03.j(set, "identifiers");
        this.b = set;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ms2) && v03.b(this.b, ((ms2) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return "DuplicatedPoints(identifiers=" + this.b + ')';
    }
}
