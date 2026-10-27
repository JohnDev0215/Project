package kotlin.q;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c extends kotlin.q.a {
    public static final a g = new a(null);
    private static final c f = new c((char) 1, (char) 0);

    /* JADX INFO: compiled from: Ranges.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public c(char c2, char c3) {
        super(c2, c3, 1);
    }

    public boolean d(char c2) {
        return kotlin.jvm.internal.h.e(a(), c2) <= 0 && kotlin.jvm.internal.h.e(c2, b()) <= 0;
    }

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

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (a() * 31) + b();
    }

    public boolean isEmpty() {
        return kotlin.jvm.internal.h.e(a(), b()) > 0;
    }

    public String toString() {
        return a() + ".." + b();
    }
}
