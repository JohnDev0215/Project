package org.greenrobot.eventbus;

/* JADX INFO: compiled from: AsyncPoster.java */
/* JADX INFO: loaded from: classes.dex */
class a implements Runnable, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f4122b = new j();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f4123c;

    a(c cVar) {
        this.f4123c = cVar;
    }

    @Override // org.greenrobot.eventbus.k
    public void a(p pVar, Object obj) {
        this.f4122b.a(i.a(pVar, obj));
        this.f4123c.d().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        i iVarB = this.f4122b.b();
        if (iVarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f4123c.g(iVarB);
    }
}
