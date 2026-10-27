package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class x0 implements g1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f7848e;

    public x0(boolean z6) {
        this.f7848e = z6;
    }

    @Override // kotlinx.coroutines.g1
    public boolean isActive() {
        return this.f7848e;
    }

    @Override // kotlinx.coroutines.g1
    public w1 p() {
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empty{");
        sb.append(isActive() ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
