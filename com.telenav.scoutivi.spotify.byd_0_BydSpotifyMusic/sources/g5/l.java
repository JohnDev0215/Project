package g5;

import java.io.Serializable;

/* JADX INFO: compiled from: Result.kt */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> implements Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f6557e = new a(null);

    /* JADX INFO: compiled from: Result.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: Result.kt */
    public static final class b implements Serializable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Throwable f6558e;

        public b(Throwable exception) {
            kotlin.jvm.internal.l.e(exception, "exception");
            this.f6558e = exception;
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && kotlin.jvm.internal.l.a(this.f6558e, ((b) obj).f6558e);
        }

        public int hashCode() {
            return this.f6558e.hashCode();
        }

        public String toString() {
            return "Failure(" + this.f6558e + ')';
        }
    }

    public static <T> Object a(Object obj) {
        return obj;
    }

    public static final Throwable b(Object obj) {
        if (obj instanceof b) {
            return ((b) obj).f6558e;
        }
        return null;
    }

    public static final boolean c(Object obj) {
        return obj instanceof b;
    }

    public static final boolean d(Object obj) {
        return !(obj instanceof b);
    }
}
