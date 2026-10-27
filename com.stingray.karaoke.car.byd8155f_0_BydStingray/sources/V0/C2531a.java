package v0;

/* JADX INFO: renamed from: v0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2531a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22940a;

    public final boolean equals(Object obj) {
        if (obj instanceof C2531a) {
            return this.f22940a == ((C2531a) obj).f22940a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f22940a);
    }

    public final String toString() {
        int i7 = this.f22940a;
        return i7 == 1 ? "Touch" : i7 == 2 ? "Keyboard" : "Error";
    }
}
