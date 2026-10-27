package c.b.d;

import android.os.Looper;
import b.e.a.a.a;
import c.b.c;
import c.b.e.b;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f2206a = new AtomicBoolean();

    /* JADX INFO: renamed from: c.b.d.a$a, reason: collision with other inner class name */
    public class RunnableC0047a implements Runnable {
        public RunnableC0047a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ((a.ViewOnClickListenerC0046a) a.this).f2198b.setOnClickListener(null);
        }
    }

    @Override // c.b.e.b
    public final void d() {
        if (this.f2206a.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                ((a.ViewOnClickListenerC0046a) this).f2198b.setOnClickListener(null);
                return;
            }
            c cVar = c.b.d.b.a.f2208a;
            if (cVar == null) {
                throw new NullPointerException("scheduler == null");
            }
            cVar.b(new RunnableC0047a());
        }
    }
}
