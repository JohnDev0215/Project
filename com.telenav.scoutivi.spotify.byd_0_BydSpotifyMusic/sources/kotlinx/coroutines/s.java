package kotlinx.coroutines;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: CommonPool.kt */
/* JADX INFO: loaded from: classes.dex */
public final class s extends e1 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f7716h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static boolean f7717i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final s f7718j = new s();
    private static volatile Executor pool;

    /* JADX INFO: compiled from: CommonPool.kt */
    static final class a implements ThreadFactory {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicInteger f7719e;

        a(AtomicInteger atomicInteger) {
            this.f7719e = atomicInteger;
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "CommonPool-worker-" + this.f7719e.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }

    /* JADX INFO: compiled from: CommonPool.kt */
    static final class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final b f7720e = new b();

        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
        }
    }

    static {
        String property;
        int iIntValue;
        try {
            property = System.getProperty("kotlinx.coroutines.default.parallelism");
        } catch (Throwable unused) {
            property = null;
        }
        if (property != null) {
            Integer numI = y5.o.i(property);
            if (numI == null || numI.intValue() < 1) {
                throw new IllegalStateException(("Expected positive number in kotlinx.coroutines.default.parallelism, but has " + property).toString());
            }
            iIntValue = numI.intValue();
        } else {
            iIntValue = -1;
        }
        f7716h = iIntValue;
    }

    private s() {
    }

    private final ExecutorService d0() {
        return Executors.newFixedThreadPool(g0(), new a(new AtomicInteger()));
    }

    private final ExecutorService e0() {
        Class<?> cls;
        ExecutorService executorService;
        if (System.getSecurityManager() != null) {
            return d0();
        }
        ExecutorService executorService2 = null;
        try {
            cls = Class.forName("java.util.concurrent.ForkJoinPool");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return d0();
        }
        if (!f7717i && f7716h < 0) {
            try {
                Method method = cls.getMethod("commonPool", new Class[0]);
                Object objInvoke = method != null ? method.invoke(null, new Object[0]) : null;
                if (!(objInvoke instanceof ExecutorService)) {
                    objInvoke = null;
                }
                executorService = (ExecutorService) objInvoke;
            } catch (Throwable unused2) {
                executorService = null;
            }
            if (executorService != null) {
                if (!f7718j.h0(cls, executorService)) {
                    executorService = null;
                }
                if (executorService != null) {
                    return executorService;
                }
            }
        }
        try {
            Object objNewInstance = cls.getConstructor(Integer.TYPE).newInstance(Integer.valueOf(f7718j.g0()));
            if (!(objNewInstance instanceof ExecutorService)) {
                objNewInstance = null;
            }
            executorService2 = (ExecutorService) objNewInstance;
        } catch (Throwable unused3) {
        }
        return executorService2 != null ? executorService2 : d0();
    }

    private final synchronized Executor f0() {
        Executor executorE0;
        executorE0 = pool;
        if (executorE0 == null) {
            executorE0 = e0();
            pool = executorE0;
        }
        return executorE0;
    }

    private final int g0() {
        Integer numValueOf = Integer.valueOf(f7716h);
        if (!(numValueOf.intValue() > 0)) {
            numValueOf = null;
        }
        return numValueOf != null ? numValueOf.intValue() : v5.h.b(Runtime.getRuntime().availableProcessors() - 1, 1);
    }

    @Override // kotlinx.coroutines.e0
    public void a0(k5.g gVar, Runnable runnable) {
        Runnable runnableD;
        try {
            Executor executorF0 = pool;
            if (executorF0 == null) {
                executorF0 = f0();
            }
            i2 i2VarA = j2.a();
            if (i2VarA == null || (runnableD = i2VarA.d(runnable)) == null) {
                runnableD = runnable;
            }
            executorF0.execute(runnableD);
        } catch (RejectedExecutionException unused) {
            i2 i2VarA2 = j2.a();
            if (i2VarA2 != null) {
                i2VarA2.g();
            }
            n0.f7710m.s0(runnable);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on CommonPool".toString());
    }

    public final boolean h0(Class<?> cls, ExecutorService executorService) {
        executorService.submit(b.f7720e);
        Integer num = null;
        try {
            Object objInvoke = cls.getMethod("getPoolSize", new Class[0]).invoke(executorService, new Object[0]);
            if (!(objInvoke instanceof Integer)) {
                objInvoke = null;
            }
            num = (Integer) objInvoke;
        } catch (Throwable unused) {
        }
        return num != null && num.intValue() >= 1;
    }

    @Override // kotlinx.coroutines.e0
    public String toString() {
        return "CommonPool";
    }
}
