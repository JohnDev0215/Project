package v5;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c extends v5.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f9691j = new a(null);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final c f9692k = new c(1, 0);

    /* JADX INFO: compiled from: Ranges.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final c a() {
            return c.f9692k;
        }
    }

    public c(int i7, int i8) {
        super(i7, i8, 1);
    }

    @Override // v5.a
    public boolean equals(Object obj) {
        if (obj instanceof c) {
            if (!isEmpty() || !((c) obj).isEmpty()) {
                c cVar = (c) obj;
                if (c() != cVar.c() || d() != cVar.d()) {
                }
            }
            return true;
        }
        return false;
    }

    public Integer h() {
        return Integer.valueOf(d());
    }

    @Override // v5.a
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (c() * 31) + d();
    }

    public Integer i() {
        return Integer.valueOf(c());
    }

    @Override // v5.a
    public boolean isEmpty() {
        return c() > d();
    }

    @Override // v5.a
    public String toString() {
        return c() + ".." + d();
    }
}
