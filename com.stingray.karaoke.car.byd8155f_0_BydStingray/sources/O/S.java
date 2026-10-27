package O;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f4939a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f4940b;

    public S(long j3, long j10) {
        this.f4939a = j3;
        this.f4940b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof S)) {
            return false;
        }
        S s6 = (S) obj;
        return C1891t.c(this.f4939a, s6.f4939a) && C1891t.c(this.f4940b, s6.f4940b);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f4940b) + (Long.hashCode(this.f4939a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SelectionColors(selectionHandleColor=");
        AbstractC1347d.n(this.f4939a, ", selectionBackgroundColor=", sb);
        sb.append((Object) C1891t.i(this.f4940b));
        sb.append(')');
        return sb.toString();
    }
}
