package defpackage;

/* JADX INFO: compiled from: PendingPostQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class im {
    public hm a;
    public hm b;

    public synchronized void a(hm hmVar) {
        try {
            if (hmVar == null) {
                throw new NullPointerException("null cannot be enqueued");
            }
            hm hmVar2 = this.b;
            if (hmVar2 != null) {
                hmVar2.d = hmVar;
                this.b = hmVar;
            } else {
                if (this.a != null) {
                    throw new IllegalStateException("Head present, but no tail");
                }
                this.b = hmVar;
                this.a = hmVar;
            }
            notifyAll();
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized hm b() {
        hm hmVar;
        hmVar = this.a;
        if (hmVar != null) {
            hm hmVar2 = hmVar.d;
            this.a = hmVar2;
            if (hmVar2 == null) {
                this.b = null;
            }
        }
        return hmVar;
    }

    public synchronized hm c(int i) throws InterruptedException {
        if (this.a == null) {
            wait(i);
        }
        return b();
    }
}
