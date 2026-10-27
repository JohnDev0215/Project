package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j0<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f7570b = new b(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f7571a;

    /* JADX INFO: compiled from: Channel.kt */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f7572a;

        public a(Throwable th) {
            this.f7572a = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && kotlin.jvm.internal.l.a(this.f7572a, ((a) obj).f7572a);
        }

        public int hashCode() {
            Throwable th = this.f7572a;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Closed(" + this.f7572a + ')';
        }
    }

    /* JADX INFO: compiled from: Channel.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    private /* synthetic */ j0(Object obj) {
        this.f7571a = obj;
    }

    public static final /* synthetic */ j0 a(Object obj) {
        return new j0(obj);
    }

    public static Object b(Object obj) {
        return obj;
    }

    public static boolean c(Object obj, Object obj2) {
        return (obj2 instanceof j0) && kotlin.jvm.internal.l.a(obj, ((j0) obj2).i());
    }

    public static final Throwable d(Object obj) {
        if (obj instanceof a) {
            return ((a) obj).f7572a;
        }
        throw new IllegalStateException("Channel was not closed".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final T e(Object obj) {
        if (obj instanceof a) {
            throw new IllegalStateException("Channel was closed".toString());
        }
        return obj;
    }

    public static int f(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static final boolean g(Object obj) {
        return obj instanceof a;
    }

    public static String h(Object obj) {
        if (obj instanceof a) {
            return obj.toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return c(this.f7571a, obj);
    }

    public int hashCode() {
        return f(this.f7571a);
    }

    public final /* synthetic */ Object i() {
        return this.f7571a;
    }

    public String toString() {
        return h(this.f7571a);
    }
}
