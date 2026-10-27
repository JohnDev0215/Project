package p;

import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public final class j91 {
    public static Lifecycle$Event a(Lifecycle$State lifecycle$State) {
        v03.j(lifecycle$State, "state");
        int iOrdinal = lifecycle$State.ordinal();
        if (iOrdinal == 1) {
            return Lifecycle$Event.ON_CREATE;
        }
        if (iOrdinal == 2) {
            return Lifecycle$Event.ON_START;
        }
        if (iOrdinal != 3) {
            return null;
        }
        return Lifecycle$Event.ON_RESUME;
    }
}
