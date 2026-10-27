package b.h.i;

import android.os.Build;
import android.os.CancellationSignal;

/* JADX INFO: compiled from: CancellationSignal.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2473a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InterfaceC0036a f2474b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f2475c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2476d;

    /* JADX INFO: renamed from: b.h.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancellationSignal.java */
    public interface InterfaceC0036a {
        void a();
    }

    public void a() {
        synchronized (this) {
            if (this.f2473a) {
                return;
            }
            this.f2473a = true;
            this.f2476d = true;
            InterfaceC0036a interfaceC0036a = this.f2474b;
            Object obj = this.f2475c;
            if (interfaceC0036a != null) {
                try {
                    interfaceC0036a.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f2476d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.f2476d = false;
                notifyAll();
            }
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this) {
            z = this.f2473a;
        }
        return z;
    }

    public void c(InterfaceC0036a interfaceC0036a) {
        synchronized (this) {
            d();
            if (this.f2474b == interfaceC0036a) {
                return;
            }
            this.f2474b = interfaceC0036a;
            if (this.f2473a && interfaceC0036a != null) {
                interfaceC0036a.a();
            }
        }
    }

    public final void d() {
        while (this.f2476d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }
}
