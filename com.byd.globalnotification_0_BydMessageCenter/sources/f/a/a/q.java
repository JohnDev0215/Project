package f.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2588a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f2589b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile boolean f2590c = true;

    public q(Object obj, o oVar) {
        this.f2588a = obj;
        this.f2589b = oVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return this.f2588a == qVar.f2588a && this.f2589b.equals(qVar.f2589b);
    }

    public int hashCode() {
        return this.f2589b.f2576f.hashCode() + this.f2588a.hashCode();
    }
}
