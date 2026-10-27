package b.g.g;

import android.os.Build;
import android.os.CancellationSignal;

/* JADX INFO: compiled from: CancellationSignal.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private InterfaceC0039a f2556b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object f2557c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2558d;

    /* JADX INFO: renamed from: b.g.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancellationSignal.java */
    public interface InterfaceC0039a {
        void a();
    }

    private void d() {
        while (this.f2558d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a() {
        synchronized (this) {
            if (this.f2555a) {
                return;
            }
            this.f2555a = true;
            this.f2558d = true;
            InterfaceC0039a interfaceC0039a = this.f2556b;
            Object obj = this.f2557c;
            if (interfaceC0039a != null) {
                try {
                    interfaceC0039a.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f2558d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.f2558d = false;
                notifyAll();
            }
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this) {
            z = this.f2555a;
        }
        return z;
    }

    public void c(InterfaceC0039a interfaceC0039a) {
        synchronized (this) {
            d();
            if (this.f2556b == interfaceC0039a) {
                return;
            }
            this.f2556b = interfaceC0039a;
            if (this.f2555a && interfaceC0039a != null) {
                interfaceC0039a.a();
            }
        }
    }
}
