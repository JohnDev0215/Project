package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: Result.kt */
/* JADX INFO: loaded from: classes.dex */
public final class vb2<T> implements Serializable {

    /* JADX INFO: compiled from: Result.kt */
    public static final class a implements Serializable {
        public final Throwable e;

        public a(Throwable th) {
            i21.e(th, "exception");
            this.e = th;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof a) {
                if (i21.a(this.e, ((a) obj).e)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return this.e.hashCode();
        }

        public final String toString() {
            return "Failure(" + this.e + ')';
        }
    }

    public static final Throwable a(Object obj) {
        if (obj instanceof a) {
            return ((a) obj).e;
        }
        return null;
    }
}
