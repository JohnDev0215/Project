package org.greenrobot.eventbus;

/* JADX INFO: loaded from: classes.dex */
class a implements Runnable, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final k f3285b = new k();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f3286c;

    a(c cVar) {
        this.f3286c = cVar;
    }

    @Override // org.greenrobot.eventbus.l
    public void a(q qVar, Object obj) {
        this.f3285b.a(j.a(qVar, obj));
        this.f3286c.d().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        j jVarB = this.f3285b.b();
        if (jVarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f3286c.g(jVarB);
    }
}
