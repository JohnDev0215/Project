package spi;

import android.os.Process;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import spi.n8;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    public final boolean a;

    @VisibleForTesting
    public final Map<ig, c> b;
    public final ReferenceQueue<n8<?>> c;
    public n8.a d;

    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: spi.h$a$a, reason: collision with other inner class name */
        public class RunnableC0126a implements Runnable {
            public final /* synthetic */ Runnable a;

            public RunnableC0126a(a aVar, Runnable runnable) {
                this.a = runnable;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Process.setThreadPriority(10);
                this.a.run();
            }
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(@NonNull Runnable runnable) {
            return new Thread(new RunnableC0126a(this, runnable), "glide-active-resources");
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            h hVar = h.this;
            Objects.requireNonNull(hVar);
            while (true) {
                try {
                    hVar.b((c) hVar.c.remove());
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @VisibleForTesting
    public static final class c extends WeakReference<n8<?>> {
        public final ig a;
        public final boolean b;

        @Nullable
        public wm<?> c;

        public c(@NonNull ig igVar, @NonNull n8<?> n8Var, @NonNull ReferenceQueue<? super n8<?>> referenceQueue, boolean z) {
            wm<?> wmVar;
            super(n8Var, referenceQueue);
            Objects.requireNonNull(igVar, "Argument must not be null");
            this.a = igVar;
            if (n8Var.a && z) {
                wmVar = n8Var.c;
                Objects.requireNonNull(wmVar, "Argument must not be null");
            } else {
                wmVar = null;
            }
            this.c = wmVar;
            this.b = n8Var.a;
        }
    }

    public h(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new a()));
    }

    @VisibleForTesting
    public h(boolean z, Executor executor) {
        this.b = new HashMap();
        this.c = new ReferenceQueue<>();
        this.a = z;
        executor.execute(new b());
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.HashMap, java.util.Map<spi.ig, spi.h$c>] */
    public final synchronized void a(ig igVar, n8<?> n8Var) {
        c cVar = (c) this.b.put(igVar, new c(igVar, n8Var, this.c, this.a));
        if (cVar != null) {
            cVar.c = null;
            cVar.clear();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<spi.ig, spi.h$c>] */
    public final void b(@NonNull c cVar) {
        wm<?> wmVar;
        synchronized (this) {
            this.b.remove(cVar.a);
            if (cVar.b && (wmVar = cVar.c) != null) {
                this.d.a(cVar.a, new n8<>(wmVar, true, false, cVar.a, this.d));
            }
        }
    }
}
