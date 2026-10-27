package h6;

import c6.d0;
import g5.r;
import h6.e;
import java.lang.ref.Reference;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RealConnectionPool.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f6736f = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f6737a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g6.d f6738b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f6739c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ConcurrentLinkedQueue<f> f6740d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f6741e;

    /* JADX INFO: compiled from: RealConnectionPool.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: RealConnectionPool.kt */
    public static final class b extends g6.a {
        b(String str) {
            super(str, false, 2, null);
        }

        @Override // g6.a
        public long f() {
            return h.this.b(System.nanoTime());
        }
    }

    public h(g6.e taskRunner, int i7, long j7, TimeUnit timeUnit) {
        l.e(taskRunner, "taskRunner");
        l.e(timeUnit, "timeUnit");
        this.f6741e = i7;
        this.f6737a = timeUnit.toNanos(j7);
        this.f6738b = taskRunner.i();
        this.f6739c = new b(d6.b.f5968i + " ConnectionPool");
        this.f6740d = new ConcurrentLinkedQueue<>();
        if (j7 > 0) {
            return;
        }
        throw new IllegalArgumentException(("keepAliveDuration <= 0: " + j7).toString());
    }

    private final int d(f fVar, long j7) {
        if (d6.b.f5967h && !Thread.holdsLock(fVar)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(fVar);
            throw new AssertionError(sb.toString());
        }
        List<Reference<e>> listN = fVar.n();
        int i7 = 0;
        while (i7 < listN.size()) {
            Reference<e> reference = listN.get(i7);
            if (reference.get() != null) {
                i7++;
            } else {
                l6.h.f8004c.g().l("A connection to " + fVar.z().a().l() + " was leaked. Did you forget to close a response body?", ((e.b) reference).a());
                listN.remove(i7);
                fVar.C(true);
                if (listN.isEmpty()) {
                    fVar.B(j7 - this.f6737a);
                    return 0;
                }
            }
        }
        return listN.size();
    }

    public final boolean a(c6.a address, e call, List<d0> list, boolean z6) {
        l.e(address, "address");
        l.e(call, "call");
        for (f connection : this.f6740d) {
            l.d(connection, "connection");
            synchronized (connection) {
                if (z6) {
                    if (!connection.v()) {
                    }
                    r rVar = r.f6563a;
                }
                if (connection.t(address, list)) {
                    call.c(connection);
                    return true;
                }
                r rVar2 = r.f6563a;
            }
        }
        return false;
    }

    public final long b(long j7) {
        int i7 = 0;
        long j8 = Long.MIN_VALUE;
        f fVar = null;
        int i8 = 0;
        for (f connection : this.f6740d) {
            l.d(connection, "connection");
            synchronized (connection) {
                if (d(connection, j7) > 0) {
                    i8++;
                } else {
                    i7++;
                    long jO = j7 - connection.o();
                    if (jO > j8) {
                        r rVar = r.f6563a;
                        fVar = connection;
                        j8 = jO;
                    } else {
                        r rVar2 = r.f6563a;
                    }
                }
            }
        }
        long j9 = this.f6737a;
        if (j8 < j9 && i7 <= this.f6741e) {
            if (i7 > 0) {
                return j9 - j8;
            }
            if (i8 > 0) {
                return j9;
            }
            return -1L;
        }
        l.c(fVar);
        synchronized (fVar) {
            if (!fVar.n().isEmpty()) {
                return 0L;
            }
            if (fVar.o() + j8 != j7) {
                return 0L;
            }
            fVar.C(true);
            this.f6740d.remove(fVar);
            d6.b.j(fVar.D());
            if (this.f6740d.isEmpty()) {
                this.f6738b.a();
            }
            return 0L;
        }
    }

    public final boolean c(f connection) {
        l.e(connection, "connection");
        if (d6.b.f5967h && !Thread.holdsLock(connection)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(connection);
            throw new AssertionError(sb.toString());
        }
        if (!connection.p() && this.f6741e != 0) {
            g6.d.j(this.f6738b, this.f6739c, 0L, 2, null);
            return false;
        }
        connection.C(true);
        this.f6740d.remove(connection);
        if (!this.f6740d.isEmpty()) {
            return true;
        }
        this.f6738b.a();
        return true;
    }

    public final void e(f connection) {
        l.e(connection, "connection");
        if (!d6.b.f5967h || Thread.holdsLock(connection)) {
            this.f6740d.add(connection);
            g6.d.j(this.f6738b, this.f6739c, 0L, 2, null);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        l.d(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(connection);
        throw new AssertionError(sb.toString());
    }
}
