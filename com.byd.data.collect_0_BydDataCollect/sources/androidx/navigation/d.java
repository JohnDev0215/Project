package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: compiled from: NavArgument.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o f1650a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f1651b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f1652c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Object f1653d;

    /* JADX INFO: compiled from: NavArgument.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private o<?> f1654a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Object f1656c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1655b = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1657d = false;

        public d a() {
            if (this.f1654a == null) {
                this.f1654a = o.e(this.f1656c);
            }
            return new d(this.f1654a, this.f1655b, this.f1656c, this.f1657d);
        }

        public a b(Object obj) {
            this.f1656c = obj;
            this.f1657d = true;
            return this;
        }

        public a c(boolean z) {
            this.f1655b = z;
            return this;
        }

        public a d(o<?> oVar) {
            this.f1654a = oVar;
            return this;
        }
    }

    d(o<?> oVar, boolean z, Object obj, boolean z2) {
        if (!oVar.f() && z) {
            throw new IllegalArgumentException(oVar.c() + " does not allow nullable values");
        }
        if (!z && z2 && obj == null) {
            throw new IllegalArgumentException("Argument with type " + oVar.c() + " has null value but is not nullable.");
        }
        this.f1650a = oVar;
        this.f1651b = z;
        this.f1653d = obj;
        this.f1652c = z2;
    }

    public o<?> a() {
        return this.f1650a;
    }

    public boolean b() {
        return this.f1652c;
    }

    void c(String str, Bundle bundle) {
        if (this.f1652c) {
            this.f1650a.i(bundle, str, this.f1653d);
        }
    }

    boolean d(String str, Bundle bundle) {
        if (!this.f1651b && bundle.containsKey(str) && bundle.get(str) == null) {
            return false;
        }
        try {
            this.f1650a.b(bundle, str);
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
        if (this.f1651b != dVar.f1651b || this.f1652c != dVar.f1652c || !this.f1650a.equals(dVar.f1650a)) {
            return false;
        }
        Object obj2 = this.f1653d;
        return obj2 != null ? obj2.equals(dVar.f1653d) : dVar.f1653d == null;
    }

    public int hashCode() {
        int iHashCode = ((((this.f1650a.hashCode() * 31) + (this.f1651b ? 1 : 0)) * 31) + (this.f1652c ? 1 : 0)) * 31;
        Object obj = this.f1653d;
        return iHashCode + (obj != null ? obj.hashCode() : 0);
    }
}
