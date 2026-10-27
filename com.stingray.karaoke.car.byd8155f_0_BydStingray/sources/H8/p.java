package h8;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15622a;

    public p(String str) {
        this.f15622a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p) && A7.m.a(this.f15622a, ((p) obj).f15622a);
    }

    public final int hashCode() {
        return this.f15622a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("MemberSignature(signature="), this.f15622a, ')');
    }
}
