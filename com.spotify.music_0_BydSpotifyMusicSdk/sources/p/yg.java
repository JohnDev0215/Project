package p;

import com.google.android.datatransport.runtime.backends.BackendResponse$Status;

/* JADX INFO: loaded from: classes.dex */
public final class yg {
    public final BackendResponse$Status a;
    public final long b;

    public yg(BackendResponse$Status backendResponse$Status, long j) {
        this.a = backendResponse$Status;
        this.b = j;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yg)) {
            return false;
        }
        yg ygVar = (yg) obj;
        return this.a.equals(ygVar.a) && this.b == ygVar.b;
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() ^ 1000003) * 1000003;
        long j = this.b;
        return iHashCode ^ ((int) ((j >>> 32) ^ j));
    }

    public final String toString() {
        return "BackendResponse{status=" + this.a + ", nextRequestWaitMillis=" + this.b + "}";
    }
}
