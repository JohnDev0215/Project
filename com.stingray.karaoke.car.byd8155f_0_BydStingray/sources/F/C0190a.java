package F;

/* JADX INFO: renamed from: F.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0190a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1863a;

    public C0190a(int i7) {
        this.f1863a = i7;
        if (i7 <= 0) {
            throw new IllegalArgumentException(A7.k.g("Provided count ", i7, " should be larger than zero").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0190a) {
            if (this.f1863a == ((C0190a) obj).f1863a) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return -this.f1863a;
    }
}
