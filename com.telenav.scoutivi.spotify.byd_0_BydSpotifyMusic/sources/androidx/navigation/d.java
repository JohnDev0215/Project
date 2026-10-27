package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: compiled from: NavArgument.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p f2637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f2638b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2639c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Object f2640d;

    /* JADX INFO: compiled from: NavArgument.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private p<?> f2641a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Object f2643c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2642b = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2644d = false;

        public d a() {
            if (this.f2641a == null) {
                this.f2641a = p.e(this.f2643c);
            }
            return new d(this.f2641a, this.f2642b, this.f2643c, this.f2644d);
        }

        public a b(Object obj) {
            this.f2643c = obj;
            this.f2644d = true;
            return this;
        }

        public a c(boolean z6) {
            this.f2642b = z6;
            return this;
        }

        public a d(p<?> pVar) {
            this.f2641a = pVar;
            return this;
        }
    }

    d(p<?> pVar, boolean z6, Object obj, boolean z7) {
        if (!pVar.f() && z6) {
            throw new IllegalArgumentException(pVar.c() + " does not allow nullable values");
        }
        if (!z6 && z7 && obj == null) {
            throw new IllegalArgumentException("Argument with type " + pVar.c() + " has null value but is not nullable.");
        }
        this.f2637a = pVar;
        this.f2638b = z6;
        this.f2640d = obj;
        this.f2639c = z7;
    }

    public p<?> a() {
        return this.f2637a;
    }

    public boolean b() {
        return this.f2639c;
    }

    void c(String str, Bundle bundle) {
        if (this.f2639c) {
            this.f2637a.i(bundle, str, this.f2640d);
        }
    }

    boolean d(String str, Bundle bundle) {
        if (!this.f2638b && bundle.containsKey(str) && bundle.get(str) == null) {
            return false;
        }
        try {
            this.f2637a.b(bundle, str);
            return true;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.f2638b != dVar.f2638b || this.f2639c != dVar.f2639c || !this.f2637a.equals(dVar.f2637a)) {
            return false;
        }
        Object obj2 = this.f2640d;
        return obj2 != null ? obj2.equals(dVar.f2640d) : dVar.f2640d == null;
    }

    public int hashCode() {
        int iHashCode = ((((this.f2637a.hashCode() * 31) + (this.f2638b ? 1 : 0)) * 31) + (this.f2639c ? 1 : 0)) * 31;
        Object obj = this.f2640d;
        return iHashCode + (obj != null ? obj.hashCode() : 0);
    }
}
