package R6;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c {
    public static final Object c = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile c f7519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Object f7520b;

    public static c a(c cVar) {
        if (cVar instanceof a) {
            return cVar;
        }
        a aVar = new a();
        aVar.f7520b = c;
        aVar.f7519a = cVar;
        return aVar;
    }

    @Override // R6.c
    public final Object get() {
        Object obj = this.f7520b;
        Object obj2 = c;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.f7520b;
                if (obj == obj2) {
                    obj = this.f7519a.get();
                    Object obj3 = this.f7520b;
                    if (obj3 != obj2 && obj3 != obj) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                    }
                    this.f7520b = obj;
                    this.f7519a = null;
                }
            }
        }
        return obj;
    }
}
