package org.greenrobot.eventbus;

/* JADX INFO: loaded from: classes.dex */
final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private j f3314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private j f3315b;

    k() {
    }

    synchronized void a(j jVar) {
        try {
            if (jVar == null) {
                throw new NullPointerException("null cannot be enqueued");
            }
            j jVar2 = this.f3315b;
            if (jVar2 != null) {
                jVar2.f3313d = jVar;
                this.f3315b = jVar;
            } else {
                if (this.f3314a != null) {
                    throw new IllegalStateException("Head present, but no tail");
                }
                this.f3315b = jVar;
                this.f3314a = jVar;
            }
            notifyAll();
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized j b() {
        j jVar;
        jVar = this.f3314a;
        if (jVar != null) {
            j jVar2 = jVar.f3313d;
            this.f3314a = jVar2;
            if (jVar2 == null) {
                this.f3315b = null;
            }
        }
        return jVar;
    }

    synchronized j c(int i) {
        if (this.f3314a == null) {
            wait(i);
        }
        return b();
    }
}
