package p;

import com.spotify.eventsender.internalerrors.EventSenderInternalError;

/* JADX INFO: loaded from: classes.dex */
public final class mj0 {
    public final EventSenderInternalError a;
    public final long b;
    public final long c;

    public mj0(EventSenderInternalError eventSenderInternalError, long j, long j2) {
        v03.j(eventSenderInternalError, "error");
        this.a = eventSenderInternalError;
        this.b = j;
        this.c = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mj0)) {
            return false;
        }
        mj0 mj0Var = (mj0) obj;
        return this.a == mj0Var.a && this.b == mj0Var.b && this.c == mj0Var.c;
    }

    public final int hashCode() {
        return Long.hashCode(this.c) + ((Long.hashCode(this.b) + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "EventSenderInternalErrorEntity(error=" + this.a + ", totalCount=" + this.b + ", unreportedCount=" + this.c + ')';
    }
}
