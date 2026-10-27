package kotlin.o;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c extends kotlin.o.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f5140g = new a(null);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final c f5139f = new c(1, 0);

    /* JADX INFO: compiled from: Ranges.kt */
    public static final class a {
        private a() {
        }

        public final c a() {
            return c.f5139f;
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public c(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // kotlin.o.a
    public boolean equals(Object obj) {
        if (obj instanceof c) {
            if (!isEmpty() || !((c) obj).isEmpty()) {
                c cVar = (c) obj;
                if (a() != cVar.a() || b() != cVar.b()) {
                }
            }
            return true;
        }
        return false;
    }

    public Integer f() {
        return Integer.valueOf(b());
    }

    public Integer g() {
        return Integer.valueOf(a());
    }

    @Override // kotlin.o.a
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (a() * 31) + b();
    }

    @Override // kotlin.o.a
    public boolean isEmpty() {
        return a() > b();
    }

    @Override // kotlin.o.a
    public String toString() {
        return a() + ".." + b();
    }
}
