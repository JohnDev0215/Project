package kotlin;

import com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason;
import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Triple<A, B, C> implements Serializable {
    private final A first;
    private final B second;
    private final C third;

    /* JADX WARN: Multi-variable type inference failed */
    public Triple(Integer num, EventLogger$FetchErrorReason eventLogger$FetchErrorReason, String str) {
        this.first = num;
        this.second = eventLogger$FetchErrorReason;
        this.third = str;
    }

    public final Object a() {
        return this.first;
    }

    public final Object b() {
        return this.second;
    }

    public final Object c() {
        return this.third;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple triple = (Triple) obj;
        return v03.b(this.first, triple.first) && v03.b(this.second, triple.second) && v03.b(this.third, triple.third);
    }

    public final int hashCode() {
        A a = this.first;
        int iHashCode = (a == null ? 0 : a.hashCode()) * 31;
        B b = this.second;
        int iHashCode2 = (iHashCode + (b == null ? 0 : b.hashCode())) * 31;
        C c = this.third;
        return iHashCode2 + (c != null ? c.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ')';
    }
}
