package d6;

/* JADX INFO: renamed from: d6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1104a implements InterfaceC1108e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f13466a;

    public C1104a(boolean z6) {
        this.f13466a = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C1104a) && this.f13466a == ((C1104a) obj).f13466a;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f13466a);
    }

    public final String toString() {
        return "Added(forceSingNow=" + this.f13466a + ")";
    }
}
