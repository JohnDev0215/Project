package okhttp3.internal.connection;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import okhttp3.Address;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.platform.Platform;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RealConnectionPool {
    public final int a;
    public final long b;
    public final TaskQueue c;
    public final RealConnectionPool$cleanupTask$1 d;
    public final ConcurrentLinkedQueue e;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [okhttp3.internal.connection.RealConnectionPool$cleanupTask$1] */
    public RealConnectionPool(TaskRunner taskRunner, TimeUnit timeUnit) {
        v03.j(taskRunner, "taskRunner");
        this.a = 5;
        this.b = timeUnit.toNanos(5L);
        this.c = taskRunner.f();
        final String strM = u4.m(new StringBuilder(), Util.g, " ConnectionPool");
        this.d = new Task(strM) { // from class: okhttp3.internal.connection.RealConnectionPool$cleanupTask$1
            @Override // okhttp3.internal.concurrent.Task
            public final long a() {
                RealConnectionPool realConnectionPool = this.e;
                long jNanoTime = System.nanoTime();
                int i = 0;
                long j = Long.MIN_VALUE;
                RealConnection realConnection = null;
                int i2 = 0;
                for (RealConnection realConnection2 : realConnectionPool.e) {
                    v03.i(realConnection2, "connection");
                    synchronized (realConnection2) {
                        if (realConnectionPool.b(realConnection2, jNanoTime) > 0) {
                            i2++;
                        } else {
                            i++;
                            long j2 = jNanoTime - realConnection2.q;
                            if (j2 > j) {
                                realConnection = realConnection2;
                                j = j2;
                            }
                        }
                    }
                }
                long j3 = realConnectionPool.b;
                if (j < j3 && i <= realConnectionPool.a) {
                    if (i > 0) {
                        return j3 - j;
                    }
                    if (i2 > 0) {
                        return j3;
                    }
                    return -1L;
                }
                v03.h(realConnection);
                synchronized (realConnection) {
                    if (!realConnection.f99p.isEmpty()) {
                        return 0L;
                    }
                    if (realConnection.q + j != jNanoTime) {
                        return 0L;
                    }
                    realConnection.j = true;
                    realConnectionPool.e.remove(realConnection);
                    Socket socket = realConnection.d;
                    v03.h(socket);
                    Util.d(socket);
                    if (!realConnectionPool.e.isEmpty()) {
                        return 0L;
                    }
                    realConnectionPool.c.a();
                    return 0L;
                }
            }
        };
        this.e = new ConcurrentLinkedQueue();
    }

    public final boolean a(Address address, RealCall realCall, List list, boolean z) {
        v03.j(address, "address");
        v03.j(realCall, "call");
        for (RealConnection realConnection : this.e) {
            v03.i(realConnection, "connection");
            synchronized (realConnection) {
                if (z) {
                    if (realConnection.g == null) {
                        continue;
                    }
                }
                if (realConnection.h(address, list)) {
                    realCall.d(realConnection);
                    return true;
                }
            }
        }
        return false;
    }

    public final int b(RealConnection realConnection, long j) {
        byte[] bArr = Util.a;
        ArrayList arrayList = realConnection.f99p;
        int i = 0;
        while (i < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                String str = "A connection to " + realConnection.b.a.i + " was leaked. Did you forget to close a response body?";
                Platform.a.getClass();
                Platform.b.j(((RealCall.CallReference) reference).a, str);
                arrayList.remove(i);
                realConnection.j = true;
                if (arrayList.isEmpty()) {
                    realConnection.q = j - this.b;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }
}
