package d.a.l;

import android.os.Looper;
import io.reactivex.disposables.b;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: MainThreadDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicBoolean f4223b = new AtomicBoolean();

    /* JADX INFO: renamed from: d.a.l.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MainThreadDisposable.java */
    class RunnableC0106a implements Runnable {
        RunnableC0106a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a();
        }
    }

    protected abstract void a();

    @Override // io.reactivex.disposables.b
    public final void dispose() {
        if (this.f4223b.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                a();
            } else {
                d.a.l.c.a.a().b(new RunnableC0106a());
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return this.f4223b.get();
    }
}
