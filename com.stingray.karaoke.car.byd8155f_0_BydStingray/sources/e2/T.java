package e2;

/* JADX INFO: loaded from: classes.dex */
public final class T {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1206n f14001a;

    public T(C1206n c1206n) {
        this.f14001a = c1206n;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof T) {
            return this.f14001a.equals(((T) obj).f14001a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f14001a.hashCode();
    }
}
