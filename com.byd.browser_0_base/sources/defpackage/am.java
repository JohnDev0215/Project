package defpackage;

import android.os.CancellationSignal;

/* JADX INFO: compiled from: CancellationSignal.java */
/* JADX INFO: loaded from: classes.dex */
public final class am {
    public boolean a;
    public a b;
    public CancellationSignal c;
    public boolean d;

    /* JADX INFO: compiled from: CancellationSignal.java */
    public interface a {
        void onCancel();
    }

    public final void a() {
        synchronized (this) {
            if (this.a) {
                return;
            }
            this.a = true;
            this.d = true;
            a aVar = this.b;
            CancellationSignal cancellationSignal = this.c;
            if (aVar != null) {
                try {
                    aVar.onCancel();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
            }
            synchronized (this) {
                this.d = false;
                notifyAll();
            }
        }
    }

    public void setOnCancelListener(a aVar) {
        synchronized (this) {
            while (this.d) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                }
            }
            if (this.b == aVar) {
                return;
            }
            this.b = aVar;
            if (this.a && aVar != null) {
                aVar.onCancel();
            }
        }
    }
}
