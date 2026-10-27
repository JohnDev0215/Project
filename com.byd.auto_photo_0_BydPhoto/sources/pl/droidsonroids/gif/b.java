package pl.droidsonroids.gif;

/* JADX INFO: loaded from: classes2.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile boolean f3185a;

    b() {
    }

    synchronized void a() {
        this.f3185a = false;
    }

    synchronized void b() {
        boolean z = this.f3185a;
        this.f3185a = true;
        if (!z) {
            notify();
        }
    }
}
