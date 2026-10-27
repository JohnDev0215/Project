package org.greenrobot.eventbus;

/* JADX INFO: compiled from: AsyncPoster.java */
/* JADX INFO: loaded from: classes.dex */
class a implements Runnable, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f4670b = new j();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f4671c;

    a(c cVar) {
        this.f4671c = cVar;
    }

    @Override // org.greenrobot.eventbus.k
    public void a(p pVar, Object obj) {
        this.f4670b.a(i.a(pVar, obj));
        this.f4671c.d().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        i iVarB = this.f4670b.b();
        if (iVarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f4671c.g(iVarB);
    }
}
