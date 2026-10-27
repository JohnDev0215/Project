package g5;

import java.io.Serializable;

/* JADX INFO: compiled from: Tuples.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k<A, B> implements Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final A f6555e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final B f6556g;

    public k(A a7, B b7) {
        this.f6555e = a7;
        this.f6556g = b7;
    }

    public final A a() {
        return this.f6555e;
    }

    public final B b() {
        return this.f6556g;
    }

    public final A c() {
        return this.f6555e;
    }

    public final B d() {
        return this.f6556g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return kotlin.jvm.internal.l.a(this.f6555e, kVar.f6555e) && kotlin.jvm.internal.l.a(this.f6556g, kVar.f6556g);
    }

    public int hashCode() {
        A a7 = this.f6555e;
        int iHashCode = (a7 == null ? 0 : a7.hashCode()) * 31;
        B b7 = this.f6556g;
        return iHashCode + (b7 != null ? b7.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.f6555e + ", " + this.f6556g + ')';
    }
}
