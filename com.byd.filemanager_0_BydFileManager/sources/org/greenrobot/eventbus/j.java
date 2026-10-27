package org.greenrobot.eventbus;

/* JADX INFO: compiled from: PendingPostQueue.java */
/* JADX INFO: loaded from: classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private i f4703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f4704b;

    j() {
    }

    synchronized void a(i iVar) {
        try {
            if (iVar == null) {
                throw new NullPointerException("null cannot be enqueued");
            }
            i iVar2 = this.f4704b;
            if (iVar2 != null) {
                iVar2.f4702d = iVar;
                this.f4704b = iVar;
            } else {
                if (this.f4703a != null) {
                    throw new IllegalStateException("Head present, but no tail");
                }
                this.f4704b = iVar;
                this.f4703a = iVar;
            }
            notifyAll();
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized i b() {
        i iVar;
        iVar = this.f4703a;
        if (iVar != null) {
            i iVar2 = iVar.f4702d;
            this.f4703a = iVar2;
            if (iVar2 == null) {
                this.f4704b = null;
            }
        }
        return iVar;
    }

    synchronized i c(int i) throws InterruptedException {
        if (this.f4703a == null) {
            wait(i);
        }
        return b();
    }
}
