package w6;

/* JADX INFO: loaded from: classes.dex */
public final class c implements d {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f23341m;

    public c(Object obj) {
        this.l = obj;
    }

    public final void a(Object obj) {
        if (this.l.equals(obj)) {
            return;
        }
        this.l = obj;
        this.f23341m = true;
    }

    @Override // w6.d
    public final boolean d() {
        return this.f23341m;
    }

    @Override // w6.d
    public final void l() {
        this.f23341m = false;
    }
}
