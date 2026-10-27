package kotlin;

import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class Result<T> implements Serializable {

    public static final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            v03.j(th, "exception");
            this.exception = th;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof Failure) && v03.b(this.exception, ((Failure) obj).exception);
        }

        public final int hashCode() {
            return this.exception.hashCode();
        }

        public final String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    public static final Throwable a(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }
}
