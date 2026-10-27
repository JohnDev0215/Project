package dagger.hilt.android.internal.managers;

/* JADX INFO: compiled from: ApplicationComponentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements o4.b<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f5989e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object f5990g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final f f5991h;

    public d(f fVar) {
        this.f5991h = fVar;
    }

    @Override // o4.b
    public Object f() {
        if (this.f5989e == null) {
            synchronized (this.f5990g) {
                if (this.f5989e == null) {
                    this.f5989e = this.f5991h.get();
                }
            }
        }
        return this.f5989e;
    }
}
