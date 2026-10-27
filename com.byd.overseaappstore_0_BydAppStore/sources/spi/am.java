package spi;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.vl;

/* JADX INFO: loaded from: classes2.dex */
public final class am {
    public final long a;
    public final vs b;
    public final b c;
    public final ConcurrentLinkedQueue<wl> d;
    public final int e;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class b extends ts {
        public b(String str) {
            super(str, false, 2, null);
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [java.util.ArrayList, java.util.List<java.lang.ref.Reference<spi.vl>>] */
        @Override // spi.ts
        public final long a() {
            am amVar = am.this;
            long jNanoTime = System.nanoTime();
            int i = 0;
            long j = Long.MIN_VALUE;
            wl wlVar = null;
            int i2 = 0;
            for (wl connection : amVar.d) {
                Intrinsics.checkNotNullExpressionValue(connection, "connection");
                synchronized (connection) {
                    if (amVar.b(connection, jNanoTime) > 0) {
                        i2++;
                    } else {
                        i++;
                        long j2 = jNanoTime - connection.p;
                        if (j2 > j) {
                            Unit unit = Unit.INSTANCE;
                            wlVar = connection;
                            j = j2;
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
            }
            long j3 = amVar.a;
            if (j < j3 && i <= amVar.e) {
                if (i > 0) {
                    return j3 - j;
                }
                if (i2 > 0) {
                    return j3;
                }
                return -1L;
            }
            Intrinsics.checkNotNull(wlVar);
            synchronized (wlVar) {
                if (!(!wlVar.o.isEmpty()) && wlVar.p + j == jNanoTime) {
                    wlVar.i = true;
                    amVar.d.remove(wlVar);
                    Socket socket = wlVar.c;
                    Intrinsics.checkNotNull(socket);
                    uu.d(socket);
                    if (!amVar.d.isEmpty()) {
                        return 0L;
                    }
                    amVar.b.a();
                    return 0L;
                }
                return 0L;
            }
        }
    }

    static {
        new a(null);
    }

    public am(@NotNull ws taskRunner, int i, long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        this.e = i;
        this.a = timeUnit.toNanos(j);
        this.b = taskRunner.f();
        this.c = new b(hi.f(new StringBuilder(), uu.g, " ConnectionPool"));
        this.d = new ConcurrentLinkedQueue<>();
        if (!(j > 0)) {
            throw new IllegalArgumentException(d.d("keepAliveDuration <= 0: ", j).toString());
        }
    }

    public final boolean a(@NotNull t address, @NotNull vl call, @Nullable List<rn> list, boolean z) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        for (wl connection : this.d) {
            Intrinsics.checkNotNullExpressionValue(connection, "connection");
            synchronized (connection) {
                if (z) {
                    if (!connection.j()) {
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (connection.h(address, list)) {
                    call.b(connection);
                    return true;
                }
                Unit unit2 = Unit.INSTANCE;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List, java.util.List<java.lang.ref.Reference<spi.vl>>] */
    public final int b(wl wlVar, long j) {
        byte[] bArr = uu.a;
        ?? r0 = wlVar.o;
        int i = 0;
        while (i < r0.size()) {
            Reference reference = (Reference) r0.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                StringBuilder sbK = e.k("A connection to ");
                sbK.append(wlVar.q.a.a);
                sbK.append(" was leaked. ");
                sbK.append("Did you forget to close a response body?");
                String string = sbK.toString();
                Objects.requireNonNull(zk.c);
                zk.a.k(string, ((vl.b) reference).a);
                r0.remove(i);
                wlVar.i = true;
                if (r0.isEmpty()) {
                    wlVar.p = j - this.a;
                    return 0;
                }
            }
        }
        return r0.size();
    }
}
