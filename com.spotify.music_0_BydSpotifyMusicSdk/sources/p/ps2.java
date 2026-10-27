package p;

import com.spotify.performancesdk.timekeeper.TimeMeasurementError$Code;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class ps2 extends qs2 {
    public final Set b;
    public final Set c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ps2(Set set, Set set2) {
        super(TimeMeasurementError$Code.POINTS_NOT_COMPLETED);
        v03.j(set, "notStartedPointIdentifiers");
        v03.j(set2, "notFinishedPointIdentifiers");
        this.b = set;
        this.c = set2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ps2)) {
            return false;
        }
        ps2 ps2Var = (ps2) obj;
        return v03.b(this.b, ps2Var.b) && v03.b(this.c, ps2Var.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        return "NotCompletedPoints(notStartedPointIdentifiers=" + this.b + ", notFinishedPointIdentifiers=" + this.c + ')';
    }
}
