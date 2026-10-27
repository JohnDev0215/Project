package androidx.core.os;

import android.os.Build;
import android.os.CancellationSignal;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f504a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f505b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object f506c;
    private boolean d;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a();
    }

    private void d() {
        while (this.d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a() {
        synchronized (this) {
            if (this.f504a) {
                return;
            }
            this.f504a = true;
            this.d = true;
            a aVar = this.f505b;
            Object obj = this.f506c;
            if (aVar != null) {
                try {
                    aVar.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.d = false;
                notifyAll();
            }
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this) {
            z = this.f504a;
        }
        return z;
    }

    public void c(@Nullable a aVar) {
        synchronized (this) {
            d();
            if (this.f505b == aVar) {
                return;
            }
            this.f505b = aVar;
            if (this.f504a && aVar != null) {
                aVar.a();
            }
        }
    }
}
