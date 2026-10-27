package N0;

/* JADX INFO: loaded from: classes.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4361a;

    public N(String str) {
        this.f4361a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof N) {
            return A7.m.a(this.f4361a, ((N) obj).f4361a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f4361a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("VerbatimTtsAnnotation(verbatim="), this.f4361a, ')');
    }
}
