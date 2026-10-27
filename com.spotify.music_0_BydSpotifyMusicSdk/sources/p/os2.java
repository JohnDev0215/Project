package p;

import com.spotify.performancesdk.timekeeper.TimeMeasurementError$Code;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class os2 extends qs2 {
    public final Set b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public os2(Set set) {
        super(TimeMeasurementError$Code.INVALID_POINT_IDENTIFIERS);
        v03.j(set, "invalidPointIdentifiers");
        this.b = set;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof os2) && v03.b(this.b, ((os2) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return "InvalidPointIdentifiers(invalidPointIdentifiers=" + this.b + ')';
    }
}
