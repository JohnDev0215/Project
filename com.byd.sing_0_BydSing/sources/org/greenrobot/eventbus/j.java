package org.greenrobot.eventbus;

/* JADX INFO: compiled from: PendingPostQueue.java */
/* JADX INFO: loaded from: classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private i f4151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f4152b;

    j() {
    }

    synchronized void a(i iVar) {
        try {
            if (iVar == null) {
                throw new NullPointerException("null cannot be enqueued");
            }
            i iVar2 = this.f4152b;
            if (iVar2 != null) {
                iVar2.f4150d = iVar;
                this.f4152b = iVar;
            } else {
                if (this.f4151a != null) {
                    throw new IllegalStateException("Head present, but no tail");
                }
                this.f4152b = iVar;
                this.f4151a = iVar;
            }
            notifyAll();
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized i b() {
        i iVar;
        iVar = this.f4151a;
        if (iVar != null) {
            i iVar2 = iVar.f4150d;
            this.f4151a = iVar2;
            if (iVar2 == null) {
                this.f4152b = null;
            }
        }
        return iVar;
    }

    synchronized i c(int i) throws InterruptedException {
        if (this.f4151a == null) {
            wait(i);
        }
        return b();
    }
}
