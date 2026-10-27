package kotlin.q;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h extends f {
    public static final a g = new a(null);
    private static final h f = new h(1, 0);

    /* JADX INFO: compiled from: Ranges.kt */
    public static final class a {
        private a() {
        }

        public final h a() {
            return h.f;
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public h(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // kotlin.q.f
    public boolean equals(Object obj) {
        if (obj instanceof h) {
            if (!isEmpty() || !((h) obj).isEmpty()) {
                h hVar = (h) obj;
                if (a() != hVar.a() || b() != hVar.b()) {
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

    @Override // kotlin.q.f
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (a() * 31) + b();
    }

    @Override // kotlin.q.f
    public boolean isEmpty() {
        return a() > b();
    }

    @Override // kotlin.q.f
    public String toString() {
        return a() + ".." + b();
    }
}
