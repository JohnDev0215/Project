package j7;

/* JADX INFO: compiled from: PendingPostQueue.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f5660a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f5661b;

    public synchronized void a(i iVar) {
        try {
            if (iVar == null) {
                throw new NullPointerException("null cannot be enqueued");
            }
            i iVar2 = this.f5661b;
            if (iVar2 != null) {
                iVar2.f5659d = iVar;
                this.f5661b = iVar;
            } else {
                if (this.f5660a != null) {
                    throw new IllegalStateException("Head present, but no tail");
                }
                this.f5661b = iVar;
                this.f5660a = iVar;
            }
            notifyAll();
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized i b() {
        i iVar;
        iVar = this.f5660a;
        if (iVar != null) {
            i iVar2 = iVar.f5659d;
            this.f5660a = iVar2;
            if (iVar2 == null) {
                this.f5661b = null;
            }
        }
        return iVar;
    }

    public synchronized i c(int i8) throws InterruptedException {
        if (this.f5660a == null) {
            wait(i8);
        }
        return b();
    }
}
