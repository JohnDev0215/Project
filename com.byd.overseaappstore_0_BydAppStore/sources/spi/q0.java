package spi;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class q0 extends dt {
    public static final long h;
    public static final long i;
    public static q0 j;
    public static final a k = new a(null);
    public boolean e;
    public q0 f;
    public long g;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final q0 a() throws InterruptedException {
            q0 q0Var = q0.j;
            Intrinsics.checkNotNull(q0Var);
            q0 q0Var2 = q0Var.f;
            long jNanoTime = System.nanoTime();
            if (q0Var2 == null) {
                q0.class.wait(q0.h);
                q0 q0Var3 = q0.j;
                Intrinsics.checkNotNull(q0Var3);
                if (q0Var3.f != null || System.nanoTime() - jNanoTime < q0.i) {
                    return null;
                }
                return q0.j;
            }
            long j = q0Var2.g - jNanoTime;
            if (j > 0) {
                long j2 = j / 1000000;
                q0.class.wait(j2, (int) (j - (1000000 * j2)));
                return null;
            }
            q0 q0Var4 = q0.j;
            Intrinsics.checkNotNull(q0Var4);
            q0Var4.f = q0Var2.f;
            q0Var2.f = null;
            return q0Var2;
        }
    }

    public static final class b extends Thread {
        public b() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            q0 q0VarA;
            while (true) {
                try {
                    synchronized (q0.class) {
                        q0VarA = q0.k.a();
                        if (q0VarA == q0.j) {
                            q0.j = null;
                            return;
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    if (q0VarA != null) {
                        q0VarA.k();
                    }
                } catch (InterruptedException unused) {
                    continue;
                }
            }
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        h = millis;
        i = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0058 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:10:0x001d, B:12:0x0021, B:13:0x0030, B:16:0x0038, B:18:0x0044, B:21:0x004c, B:22:0x0051, B:24:0x0058, B:27:0x0063, B:28:0x0066, B:30:0x0070, B:31:0x0073, B:20:0x0048, B:34:0x0077, B:35:0x007c), top: B:41:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0070 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:10:0x001d, B:12:0x0021, B:13:0x0030, B:16:0x0038, B:18:0x0044, B:21:0x004c, B:22:0x0051, B:24:0x0058, B:27:0x0063, B:28:0x0066, B:30:0x0070, B:31:0x0073, B:20:0x0048, B:34:0x0077, B:35:0x007c), top: B:41:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0066 A[EDGE_INSN: B:42:0x0066->B:28:0x0066 BREAK  A[LOOP:0: B:22:0x0051->B:27:0x0063], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            r9 = this;
            boolean r0 = r9.e
            r1 = 1
            r0 = r0 ^ r1
            if (r0 == 0) goto L80
            long r2 = r9.c
            boolean r0 = r9.a
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 != 0) goto L13
            if (r0 != 0) goto L13
            return
        L13:
            r9.e = r1
            spi.q0$a r1 = spi.q0.k
            java.util.Objects.requireNonNull(r1)
            java.lang.Class<spi.q0> r1 = spi.q0.class
            monitor-enter(r1)
            spi.q0 r5 = spi.q0.j     // Catch: java.lang.Throwable -> L7d
            if (r5 != 0) goto L30
            spi.q0 r5 = new spi.q0     // Catch: java.lang.Throwable -> L7d
            r5.<init>()     // Catch: java.lang.Throwable -> L7d
            spi.q0.j = r5     // Catch: java.lang.Throwable -> L7d
            spi.q0$b r5 = new spi.q0$b     // Catch: java.lang.Throwable -> L7d
            r5.<init>()     // Catch: java.lang.Throwable -> L7d
            r5.start()     // Catch: java.lang.Throwable -> L7d
        L30:
            long r5 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L42
            if (r0 == 0) goto L42
            long r7 = r9.c()     // Catch: java.lang.Throwable -> L7d
            long r7 = r7 - r5
            long r2 = java.lang.Math.min(r2, r7)     // Catch: java.lang.Throwable -> L7d
            goto L44
        L42:
            if (r4 == 0) goto L46
        L44:
            long r2 = r2 + r5
            goto L4c
        L46:
            if (r0 == 0) goto L77
            long r2 = r9.c()     // Catch: java.lang.Throwable -> L7d
        L4c:
            r9.g = r2     // Catch: java.lang.Throwable -> L7d
            long r2 = r2 - r5
            spi.q0 r0 = spi.q0.j     // Catch: java.lang.Throwable -> L7d
        L51:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L7d
            spi.q0 r4 = r0.f     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L66
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch: java.lang.Throwable -> L7d
            long r7 = r4.g     // Catch: java.lang.Throwable -> L7d
            long r7 = r7 - r5
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 >= 0) goto L63
            goto L66
        L63:
            spi.q0 r0 = r0.f     // Catch: java.lang.Throwable -> L7d
            goto L51
        L66:
            spi.q0 r2 = r0.f     // Catch: java.lang.Throwable -> L7d
            r9.f = r2     // Catch: java.lang.Throwable -> L7d
            r0.f = r9     // Catch: java.lang.Throwable -> L7d
            spi.q0 r2 = spi.q0.j     // Catch: java.lang.Throwable -> L7d
            if (r0 != r2) goto L73
            r1.notify()     // Catch: java.lang.Throwable -> L7d
        L73:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L7d
            monitor-exit(r1)
            return
        L77:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L7d
            r0.<init>()     // Catch: java.lang.Throwable -> L7d
            throw r0     // Catch: java.lang.Throwable -> L7d
        L7d:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L80:
            java.lang.String r0 = "Unbalanced enter/exit"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.q0.h():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
    
        r2.f = r4.f;
        r4.f = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean i() {
        /*
            r4 = this;
            boolean r0 = r4.e
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            r4.e = r1
            spi.q0$a r0 = spi.q0.k
            java.util.Objects.requireNonNull(r0)
            java.lang.Class<spi.q0> r0 = spi.q0.class
            monitor-enter(r0)
            spi.q0 r2 = spi.q0.j     // Catch: java.lang.Throwable -> L25
        L12:
            if (r2 == 0) goto L22
            spi.q0 r3 = r2.f     // Catch: java.lang.Throwable -> L25
            if (r3 != r4) goto L20
            spi.q0 r3 = r4.f     // Catch: java.lang.Throwable -> L25
            r2.f = r3     // Catch: java.lang.Throwable -> L25
            r2 = 0
            r4.f = r2     // Catch: java.lang.Throwable -> L25
            goto L23
        L20:
            r2 = r3
            goto L12
        L22:
            r1 = 1
        L23:
            monitor-exit(r0)
            return r1
        L25:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.q0.i():boolean");
    }

    @NotNull
    public IOException j(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public void k() {
    }
}
