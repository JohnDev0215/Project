package p;

import com.spotify.eventsender.droppedevents.EventState;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class gk0 {
    public final qb0[] a;

    public gk0() {
        LinkedHashMap linkedHashMap = EventState.c;
        int i = EventState.k;
        qb0[] qb0VarArr = new qb0[i];
        for (int i2 = 0; i2 < i; i2++) {
            qb0VarArr[i2] = new qb0(0L, 0L);
        }
        this.a = qb0VarArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(gk0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.eventsender.droppedevents.EventStatesCounter");
        return Arrays.equals(this.a, ((gk0) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }
}
