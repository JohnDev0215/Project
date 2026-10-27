package spi;

import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ws {

    @JvmField
    @NotNull
    public static final ws h;

    @NotNull
    public static final Logger i;
    public static final b j = new b(null);
    public int a;
    public boolean b;
    public long c;
    public final List<vs> d;
    public final List<vs> e;
    public final d f;

    @NotNull
    public final a g;

    public interface a {
        void a(@NotNull ws wsVar, long j);

        void b(@NotNull ws wsVar);

        long c();

        void execute(@NotNull Runnable runnable);
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class c implements a {
        public final ThreadPoolExecutor a;

        public c(@NotNull ThreadFactory threadFactory) {
            Intrinsics.checkNotNullParameter(threadFactory, "threadFactory");
            this.a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
        }

        @Override // spi.ws.a
        public final void a(@NotNull ws taskRunner, long j) throws InterruptedException {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            long j2 = j / 1000000;
            long j3 = j - (1000000 * j2);
            if (j2 > 0 || j > 0) {
                taskRunner.wait(j2, (int) j3);
            }
        }

        @Override // spi.ws.a
        public final void b(@NotNull ws taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            taskRunner.notify();
        }

        @Override // spi.ws.a
        public final long c() {
            return System.nanoTime();
        }

        @Override // spi.ws.a
        public final void execute(@NotNull Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            this.a.execute(runnable);
        }
    }

    public static final class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ts tsVarC;
            while (true) {
                synchronized (ws.this) {
                    tsVarC = ws.this.c();
                }
                if (tsVarC == null) {
                    return;
                }
                vs vsVar = tsVarC.a;
                Intrinsics.checkNotNull(vsVar);
                long jC = -1;
                Objects.requireNonNull(ws.j);
                boolean zIsLoggable = ws.i.isLoggable(Level.FINE);
                if (zIsLoggable) {
                    jC = vsVar.e.g.c();
                    spi.c.b(tsVarC, vsVar, "starting");
                }
                try {
                    try {
                        ws.a(ws.this, tsVarC);
                        Unit unit = Unit.INSTANCE;
                        if (zIsLoggable) {
                            long jC2 = vsVar.e.g.c() - jC;
                            StringBuilder sbK = e.k("finished run in ");
                            sbK.append(spi.c.f(jC2));
                            spi.c.b(tsVarC, vsVar, sbK.toString());
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    if (zIsLoggable) {
                        long jC3 = vsVar.e.g.c() - jC;
                        StringBuilder sbK2 = e.k("failed a run in ");
                        sbK2.append(spi.c.f(jC3));
                        spi.c.b(tsVarC, vsVar, sbK2.toString());
                    }
                    throw th;
                }
            }
        }
    }

    static {
        String name = uu.g + " TaskRunner";
        Intrinsics.checkNotNullParameter(name, "name");
        h = new ws(new c(new su(name, true)));
        Logger logger = Logger.getLogger(ws.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger, "Logger.getLogger(TaskRunner::class.java.name)");
        i = logger;
    }

    public ws(@NotNull a backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        this.g = backend;
        this.a = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new d();
    }

    public static final void a(ws wsVar, ts tsVar) {
        Objects.requireNonNull(wsVar);
        byte[] bArr = uu.a;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(tsVar.c);
        try {
            long jA = tsVar.a();
            synchronized (wsVar) {
                wsVar.b(tsVar, jA);
                Unit unit = Unit.INSTANCE;
            }
            currentThread.setName(name);
        } catch (Throwable th) {
            synchronized (wsVar) {
                wsVar.b(tsVar, -1L);
                Unit unit2 = Unit.INSTANCE;
                currentThread.setName(name);
                throw th;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    public final void b(ts tsVar, long j2) {
        byte[] bArr = uu.a;
        vs vsVar = tsVar.a;
        Intrinsics.checkNotNull(vsVar);
        if (!(vsVar.b == tsVar)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean z = vsVar.d;
        vsVar.d = false;
        vsVar.b = null;
        this.d.remove(vsVar);
        if (j2 != -1 && !z && !vsVar.a) {
            vsVar.e(tsVar, j2, true);
        }
        if (!vsVar.c.isEmpty()) {
            this.e.add(vsVar);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    @Nullable
    public final ts c() {
        boolean z;
        byte[] bArr = uu.a;
        while (!this.e.isEmpty()) {
            long jC = this.g.c();
            long jMin = Long.MAX_VALUE;
            Iterator it = this.e.iterator();
            ts tsVar = null;
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ts tsVar2 = (ts) ((vs) it.next()).c.get(0);
                long jMax = Math.max(0L, tsVar2.b - jC);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (tsVar != null) {
                        z = true;
                        break;
                    }
                    tsVar = tsVar2;
                }
            }
            if (tsVar != null) {
                byte[] bArr2 = uu.a;
                tsVar.b = -1L;
                vs vsVar = tsVar.a;
                Intrinsics.checkNotNull(vsVar);
                vsVar.c.remove(tsVar);
                this.e.remove(vsVar);
                vsVar.b = tsVar;
                this.d.add(vsVar);
                if (z || (!this.b && (!this.e.isEmpty()))) {
                    this.g.execute(this.f);
                }
                return tsVar;
            }
            if (this.b) {
                if (jMin < this.c - jC) {
                    this.g.b(this);
                }
                return null;
            }
            this.b = true;
            this.c = jC + jMin;
            try {
                try {
                    this.g.a(this, jMin);
                } catch (InterruptedException unused) {
                    d();
                }
            } finally {
                this.b = false;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    public final void d() {
        int size = this.d.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else {
                ((vs) this.d.get(size)).b();
            }
        }
        int size2 = this.e.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                return;
            }
            vs vsVar = (vs) this.e.get(size2);
            vsVar.b();
            if (vsVar.c.isEmpty()) {
                this.e.remove(size2);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.ArrayList, java.util.List<spi.vs>] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object, java.util.ArrayList, java.util.List, java.util.List<spi.vs>] */
    public final void e(@NotNull vs taskQueue) {
        Intrinsics.checkNotNullParameter(taskQueue, "taskQueue");
        byte[] bArr = uu.a;
        if (taskQueue.b == null) {
            if (!taskQueue.c.isEmpty()) {
                ?? addIfAbsent = this.e;
                Intrinsics.checkNotNullParameter(addIfAbsent, "$this$addIfAbsent");
                if (!addIfAbsent.contains(taskQueue)) {
                    addIfAbsent.add(taskQueue);
                }
            } else {
                this.e.remove(taskQueue);
            }
        }
        if (this.b) {
            this.g.b(this);
        } else {
            this.g.execute(this.f);
        }
    }

    @NotNull
    public final vs f() {
        int i2;
        synchronized (this) {
            i2 = this.a;
            this.a = i2 + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('Q');
        sb.append(i2);
        return new vs(this, sb.toString());
    }
}
