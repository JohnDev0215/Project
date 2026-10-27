package N0;

/* JADX INFO: loaded from: classes.dex */
public final class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4360a;

    public M(String str) {
        this.f4360a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof M) {
            return A7.m.a(this.f4360a, ((M) obj).f4360a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f4360a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("UrlAnnotation(url="), this.f4360a, ')');
    }
}
