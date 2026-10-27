package j7;

/* JADX INFO: compiled from: Subscription.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5685a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f5686b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile boolean f5687c = true;

    public p(Object obj, n nVar) {
        this.f5685a = obj;
        this.f5686b = nVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f5685a == pVar.f5685a && this.f5686b.equals(pVar.f5686b);
    }

    public int hashCode() {
        return this.f5685a.hashCode() + this.f5686b.f5671f.hashCode();
    }
}
