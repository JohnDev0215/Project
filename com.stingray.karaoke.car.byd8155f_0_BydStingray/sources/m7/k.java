package m7;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class k implements Serializable {
    public final Throwable l;

    public k(Throwable th) {
        A7.m.f(th, "exception");
        this.l = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            if (A7.m.a(this.l, ((k) obj).l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.l + ')';
    }
}
