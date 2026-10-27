package T;

/* JADX INFO: loaded from: classes.dex */
public final class Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8227a;

    public Y(String str) {
        this.f8227a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Y) && A7.m.a(this.f8227a, ((Y) obj).f8227a);
    }

    public final int hashCode() {
        return this.f8227a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("OpaqueKey(key="), this.f8227a, ')');
    }
}
