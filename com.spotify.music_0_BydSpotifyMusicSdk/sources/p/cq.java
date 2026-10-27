package p;

/* JADX INFO: loaded from: classes.dex */
public final class cq {
    public boolean a;
    public bq b;
    public boolean c;

    public final void a() {
        synchronized (this) {
            try {
                if (this.a) {
                    return;
                }
                this.a = true;
                this.c = true;
                bq bqVar = this.b;
                if (bqVar != null) {
                    try {
                        bqVar.c();
                    } catch (Throwable th) {
                        synchronized (this) {
                            this.c = false;
                            notifyAll();
                            throw th;
                        }
                    }
                }
                synchronized (this) {
                    this.c = false;
                    notifyAll();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final void b(bq bqVar) {
        synchronized (this) {
            while (this.c) {
                try {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (this.b == bqVar) {
                return;
            }
            this.b = bqVar;
            if (this.a) {
                bqVar.c();
            }
        }
    }
}
