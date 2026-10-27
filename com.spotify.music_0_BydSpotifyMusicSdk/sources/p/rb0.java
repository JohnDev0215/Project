package p;

import com.spotify.eventsender.droppedevents.EventState;

/* JADX INFO: loaded from: classes.dex */
public final class rb0 {
    public final String a;
    public final long b;
    public final long c;
    public final EventState d;

    public rb0(String str, long j, long j2, EventState eventState) {
        v03.j(str, "eventName");
        v03.j(eventState, "state");
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = eventState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(rb0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.eventsender.droppedevents.DroppedEventsEntity");
        rb0 rb0Var = (rb0) obj;
        return this.d == rb0Var.d && v03.b(this.a, rb0Var.a) && this.b == rb0Var.b && this.c == rb0Var.c;
    }

    public final int hashCode() {
        return this.d.hashCode() + (((((this.a.hashCode() * 31) + ((int) this.b)) * 31) + ((int) this.c)) * 31);
    }

    public final String toString() {
        return "DroppedEventsEntity(eventName=" + this.a + ", count=" + this.b + ", unreportedCount=" + this.c + ", state=" + this.d + ')';
    }
}
