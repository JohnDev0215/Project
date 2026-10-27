package d0;

import android.os.CancellationSignal;

/* JADX INFO: compiled from: CancellationSignal.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f5606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f5607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object f5608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5609d;

    /* JADX INFO: compiled from: CancellationSignal.java */
    public interface a {
        void a();
    }

    private void d() {
        while (this.f5609d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a() {
        synchronized (this) {
            if (this.f5606a) {
                return;
            }
            this.f5606a = true;
            this.f5609d = true;
            a aVar = this.f5607b;
            Object obj = this.f5608c;
            if (aVar != null) {
                try {
                    aVar.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f5609d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.f5609d = false;
                notifyAll();
            }
        }
    }

    public boolean b() {
        boolean z6;
        synchronized (this) {
            z6 = this.f5606a;
        }
        return z6;
    }

    public void c(a aVar) {
        synchronized (this) {
            d();
            if (this.f5607b == aVar) {
                return;
            }
            this.f5607b = aVar;
            if (this.f5606a && aVar != null) {
                aVar.a();
            }
        }
    }
}
