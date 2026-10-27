package c6;

import h6.e;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Runnable f3827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ExecutorService f3828d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3825a = 64;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3826b = 5;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ArrayDeque<e.a> f3829e = new ArrayDeque<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final ArrayDeque<e.a> f3830f = new ArrayDeque<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ArrayDeque<h6.e> f3831g = new ArrayDeque<>();

    private final e.a c(String str) {
        for (e.a aVar : this.f3830f) {
            if (kotlin.jvm.internal.l.a(aVar.d(), str)) {
                return aVar;
            }
        }
        for (e.a aVar2 : this.f3829e) {
            if (kotlin.jvm.internal.l.a(aVar2.d(), str)) {
                return aVar2;
            }
        }
        return null;
    }

    private final <T> void d(Deque<T> deque, T t6) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t6)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.f3827c;
            g5.r rVar = g5.r.f6563a;
        }
        if (f() || runnable == null) {
            return;
        }
        runnable.run();
    }

    private final boolean f() {
        int i7;
        boolean z6;
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<e.a> it = this.f3829e.iterator();
            kotlin.jvm.internal.l.d(it, "readyAsyncCalls.iterator()");
            while (it.hasNext()) {
                e.a asyncCall = it.next();
                if (this.f3830f.size() >= this.f3825a) {
                    break;
                }
                if (asyncCall.c().get() < this.f3826b) {
                    it.remove();
                    asyncCall.c().incrementAndGet();
                    kotlin.jvm.internal.l.d(asyncCall, "asyncCall");
                    arrayList.add(asyncCall);
                    this.f3830f.add(asyncCall);
                }
            }
            z6 = g() > 0;
            g5.r rVar = g5.r.f6563a;
        }
        int size = arrayList.size();
        for (i7 = 0; i7 < size; i7++) {
            ((e.a) arrayList.get(i7)).a(b());
        }
        return z6;
    }

    public final void a(e.a call) {
        e.a aVarC;
        kotlin.jvm.internal.l.e(call, "call");
        synchronized (this) {
            this.f3829e.add(call);
            if (!call.b().n() && (aVarC = c(call.d())) != null) {
                call.e(aVarC);
            }
            g5.r rVar = g5.r.f6563a;
        }
        f();
    }

    public final synchronized ExecutorService b() {
        ExecutorService executorService;
        if (this.f3828d == null) {
            this.f3828d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), d6.b.H(d6.b.f5968i + " Dispatcher", false));
        }
        executorService = this.f3828d;
        kotlin.jvm.internal.l.c(executorService);
        return executorService;
    }

    public final void e(e.a call) {
        kotlin.jvm.internal.l.e(call, "call");
        call.c().decrementAndGet();
        d(this.f3830f, call);
    }

    public final synchronized int g() {
        return this.f3830f.size() + this.f3831g.size();
    }
}
