package okhttp3.internal.http2;

import com.spotify.playbacknative.AudioDriver;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.http2.PushObserver;
import okhttp3.internal.platform.Platform;
import okio.ByteString;
import p.io;
import p.ko;
import p.lq2;
import p.py0;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http2Connection implements Closeable {
    public static final Companion J = new Companion(0);
    public static final Settings K;
    public Settings A;
    public long B;
    public long C;
    public long D;
    public long E;
    public final Socket F;
    public final Http2Writer G;
    public final ReaderRunnable H;
    public final LinkedHashSet I;
    public final boolean c;
    public final Listener e;
    public final LinkedHashMap k;
    public final String l;
    public int m;
    public int n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final TaskRunner f101p;
    public final TaskQueue q;
    public final TaskQueue r;
    public final TaskQueue s;
    public final PushObserver t;
    public long u;
    public long v;
    public long w;
    public long x;
    public long y;
    public final Settings z;

    public static final class Builder {
        public final boolean a;
        public final TaskRunner b;
        public Socket c;
        public String d;
        public ko e;
        public io f;
        public Listener g;
        public final PushObserver h;
        public int i;

        public Builder(TaskRunner taskRunner) {
            v03.j(taskRunner, "taskRunner");
            this.a = true;
            this.b = taskRunner;
            this.g = Listener.a;
            this.h = PushObserver.a;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static abstract class Listener {
        public static final Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1 a;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(int i) {
                this();
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [okhttp3.internal.http2.Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1] */
        static {
            new Companion(0);
            a = new Listener() { // from class: okhttp3.internal.http2.Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1
                @Override // okhttp3.internal.http2.Http2Connection.Listener
                public final void b(Http2Stream http2Stream) {
                    v03.j(http2Stream, "stream");
                    http2Stream.c(ErrorCode.REFUSED_STREAM, null);
                }
            };
        }

        public void a(Http2Connection http2Connection, Settings settings) {
            v03.j(http2Connection, "connection");
            v03.j(settings, "settings");
        }

        public abstract void b(Http2Stream http2Stream);
    }

    public final class ReaderRunnable implements Http2Reader.Handler, py0 {
        public final Http2Reader c;

        public ReaderRunnable(Http2Reader http2Reader) {
            this.c = http2Reader;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void a() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:57:0x00f2, code lost:
        
            if (r20 == false) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00f4, code lost:
        
            r5.i(okhttp3.internal.Util.b, true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00fa, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:?, code lost:
        
            return;
         */
        @Override // okhttp3.internal.http2.Http2Reader.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(final int r17, final int r18, p.ko r19, final boolean r20) throws java.io.EOFException {
            /*
                Method dump skipped, instruction units count: 251
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.ReaderRunnable.b(int, int, p.ko, boolean):void");
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void c() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void d(final List list, final int i) {
            v03.j(list, "requestHeaders");
            final Http2Connection http2Connection = Http2Connection.this;
            http2Connection.getClass();
            synchronized (http2Connection) {
                if (http2Connection.I.contains(Integer.valueOf(i))) {
                    http2Connection.o0(i, ErrorCode.PROTOCOL_ERROR);
                    return;
                }
                http2Connection.I.add(Integer.valueOf(i));
                TaskQueue taskQueue = http2Connection.r;
                final String str = http2Connection.l + '[' + i + "] onRequest";
                taskQueue.c(new Task(str) { // from class: okhttp3.internal.http2.Http2Connection$pushRequestLater$$inlined$execute$default$1
                    @Override // okhttp3.internal.concurrent.Task
                    public final long a() {
                        PushObserver pushObserver = http2Connection.t;
                        List list2 = list;
                        ((PushObserver.Companion.PushObserverCancel) pushObserver).getClass();
                        v03.j(list2, "requestHeaders");
                        try {
                            http2Connection.G.J(i, ErrorCode.CANCEL);
                            synchronized (http2Connection) {
                                http2Connection.I.remove(Integer.valueOf(i));
                            }
                            return -1L;
                        } catch (IOException unused) {
                            return -1L;
                        }
                    }
                }, 0L);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void f(final Settings settings) {
            Http2Connection http2Connection = Http2Connection.this;
            TaskQueue taskQueue = http2Connection.q;
            final String strM = u4.m(new StringBuilder(), http2Connection.l, " applyAndAckSettings");
            taskQueue.c(new Task(strM) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$settings$$inlined$execute$default$1
                public final /* synthetic */ boolean f = false;

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r2v1 */
                /* JADX WARN: Type inference failed for: r2v2, types: [T, okhttp3.internal.http2.Settings] */
                /* JADX WARN: Type inference failed for: r2v3 */
                @Override // okhttp3.internal.concurrent.Task
                public final long a() {
                    ?? r2;
                    long jA;
                    int i;
                    Http2Stream[] http2StreamArr;
                    Http2Connection.ReaderRunnable readerRunnable = this;
                    boolean z = this.f;
                    Settings settings2 = settings;
                    readerRunnable.getClass();
                    v03.j(settings2, "settings");
                    final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                    final Http2Connection http2Connection2 = Http2Connection.this;
                    synchronized (http2Connection2.G) {
                        synchronized (http2Connection2) {
                            try {
                                Settings settings3 = http2Connection2.A;
                                if (z) {
                                    r2 = settings2;
                                } else {
                                    Settings settings4 = new Settings();
                                    settings4.b(settings3);
                                    settings4.b(settings2);
                                    r2 = settings4;
                                }
                                ref$ObjectRef.element = r2;
                                jA = ((long) r2.a()) - ((long) settings3.a());
                                http2StreamArr = (jA == 0 || http2Connection2.k.isEmpty()) ? null : (Http2Stream[]) http2Connection2.k.values().toArray(new Http2Stream[0]);
                                Settings settings5 = (Settings) ref$ObjectRef.element;
                                v03.j(settings5, "<set-?>");
                                http2Connection2.A = settings5;
                                http2Connection2.s.c(new Task(http2Connection2.l + " onSettings") { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$lambda$7$lambda$6$$inlined$execute$default$1
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // okhttp3.internal.concurrent.Task
                                    public final long a() {
                                        Http2Connection http2Connection3 = http2Connection2;
                                        http2Connection3.e.a(http2Connection3, (Settings) ref$ObjectRef.element);
                                        return -1L;
                                    }
                                }, 0L);
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        try {
                            http2Connection2.G.b((Settings) ref$ObjectRef.element);
                        } catch (IOException e) {
                            http2Connection2.c(e);
                        }
                    }
                    if (http2StreamArr == null) {
                        return -1L;
                    }
                    for (Http2Stream http2Stream : http2StreamArr) {
                        synchronized (http2Stream) {
                            http2Stream.f += jA;
                            if (jA > 0) {
                                http2Stream.notifyAll();
                            }
                        }
                    }
                    return -1L;
                }
            }, 0L);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void h(final int i, final ErrorCode errorCode) {
            final Http2Connection http2Connection = Http2Connection.this;
            http2Connection.getClass();
            if (i == 0 || (i & 1) != 0) {
                Http2Stream http2StreamQ = http2Connection.q(i);
                if (http2StreamQ != null) {
                    http2StreamQ.j(errorCode);
                    return;
                }
                return;
            }
            final String str = http2Connection.l + '[' + i + "] onReset";
            http2Connection.r.c(new Task(str) { // from class: okhttp3.internal.http2.Http2Connection$pushResetLater$$inlined$execute$default$1
                @Override // okhttp3.internal.concurrent.Task
                public final long a() {
                    PushObserver pushObserver = http2Connection.t;
                    ErrorCode errorCode2 = errorCode;
                    ((PushObserver.Companion.PushObserverCancel) pushObserver).getClass();
                    v03.j(errorCode2, "errorCode");
                    synchronized (http2Connection) {
                        http2Connection.I.remove(Integer.valueOf(i));
                    }
                    return -1L;
                }
            }, 0L);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void i(final int i, final List list, final boolean z) {
            v03.j(list, "headerBlock");
            Http2Connection.this.getClass();
            if (i != 0 && (i & 1) == 0) {
                final Http2Connection http2Connection = Http2Connection.this;
                http2Connection.getClass();
                final String str = http2Connection.l + '[' + i + "] onHeaders";
                http2Connection.r.c(new Task(str, http2Connection, i, list, z) { // from class: okhttp3.internal.http2.Http2Connection$pushHeadersLater$$inlined$execute$default$1
                    public final /* synthetic */ Http2Connection e;
                    public final /* synthetic */ int f;
                    public final /* synthetic */ List g;

                    @Override // okhttp3.internal.concurrent.Task
                    public final long a() {
                        PushObserver pushObserver = this.e.t;
                        List list2 = this.g;
                        ((PushObserver.Companion.PushObserverCancel) pushObserver).getClass();
                        v03.j(list2, "responseHeaders");
                        try {
                            this.e.G.J(this.f, ErrorCode.CANCEL);
                            synchronized (this.e) {
                                this.e.I.remove(Integer.valueOf(this.f));
                            }
                            return -1L;
                        } catch (IOException unused) {
                            return -1L;
                        }
                    }
                }, 0L);
                return;
            }
            final Http2Connection http2Connection2 = Http2Connection.this;
            synchronized (http2Connection2) {
                Http2Stream http2StreamI = http2Connection2.i(i);
                if (http2StreamI != null) {
                    http2StreamI.i(Util.w(list), z);
                    return;
                }
                if (http2Connection2.o) {
                    return;
                }
                if (i <= http2Connection2.m) {
                    return;
                }
                if (i % 2 == http2Connection2.n % 2) {
                    return;
                }
                final Http2Stream http2Stream = new Http2Stream(i, http2Connection2, false, z, Util.w(list));
                http2Connection2.m = i;
                http2Connection2.k.put(Integer.valueOf(i), http2Stream);
                TaskQueue taskQueueF = http2Connection2.f101p.f();
                final String str2 = http2Connection2.l + '[' + i + "] onStream";
                taskQueueF.c(new Task(str2) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$headers$lambda$2$$inlined$execute$default$1
                    @Override // okhttp3.internal.concurrent.Task
                    public final long a() {
                        try {
                            http2Connection2.e.b(http2Stream);
                            return -1L;
                        } catch (IOException e) {
                            Platform.a.getClass();
                            Platform platform = Platform.b;
                            String str3 = "Http2Connection.Listener failure for " + http2Connection2.l;
                            platform.getClass();
                            Platform.i(4, str3, e);
                            try {
                                http2Stream.c(ErrorCode.PROTOCOL_ERROR, e);
                                return -1L;
                            } catch (IOException unused) {
                                return -1L;
                            }
                        }
                    }
                }, 0L);
            }
        }

        @Override // p.py0
        public final Object invoke() throws Throwable {
            ErrorCode errorCode;
            Http2Connection http2Connection = Http2Connection.this;
            Http2Reader http2Reader = this.c;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            IOException e = null;
            try {
                try {
                    http2Reader.c(this);
                    while (http2Reader.b(false, this)) {
                    }
                    errorCode = ErrorCode.NO_ERROR;
                } catch (IOException e2) {
                    e = e2;
                    errorCode = errorCode2;
                } catch (Throwable th) {
                    th = th;
                    errorCode = errorCode2;
                    http2Connection.b(errorCode, errorCode2, e);
                    Util.c(http2Reader);
                    throw th;
                }
                try {
                    errorCode2 = ErrorCode.CANCEL;
                    http2Connection.b(errorCode, errorCode2, null);
                } catch (IOException e3) {
                    e = e3;
                    errorCode2 = ErrorCode.PROTOCOL_ERROR;
                    http2Connection.b(errorCode2, errorCode2, e);
                }
                Util.c(http2Reader);
                return lq2.a;
            } catch (Throwable th2) {
                th = th2;
                http2Connection.b(errorCode, errorCode2, e);
                Util.c(http2Reader);
                throw th;
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void j(int i, long j) {
            if (i == 0) {
                Http2Connection http2Connection = Http2Connection.this;
                synchronized (http2Connection) {
                    http2Connection.E += j;
                    http2Connection.notifyAll();
                }
                return;
            }
            Http2Stream http2StreamI = Http2Connection.this.i(i);
            if (http2StreamI != null) {
                synchronized (http2StreamI) {
                    http2StreamI.f += j;
                    if (j > 0) {
                        http2StreamI.notifyAll();
                    }
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void k(final int i, final int i2, boolean z) {
            if (!z) {
                TaskQueue taskQueue = Http2Connection.this.q;
                final String strM = u4.m(new StringBuilder(), Http2Connection.this.l, " ping");
                final Http2Connection http2Connection = Http2Connection.this;
                taskQueue.c(new Task(strM) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$ping$$inlined$execute$default$1
                    @Override // okhttp3.internal.concurrent.Task
                    public final long a() {
                        int i3 = i;
                        int i4 = i2;
                        Http2Connection http2Connection2 = http2Connection;
                        http2Connection2.getClass();
                        try {
                            http2Connection2.G.E(i3, i4, true);
                            return -1L;
                        } catch (IOException e) {
                            http2Connection2.c(e);
                            return -1L;
                        }
                    }
                }, 0L);
                return;
            }
            Http2Connection http2Connection2 = Http2Connection.this;
            synchronized (http2Connection2) {
                try {
                    if (i == 1) {
                        http2Connection2.v++;
                    } else if (i == 2) {
                        http2Connection2.x++;
                    } else if (i == 3) {
                        http2Connection2.notifyAll();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public final void l(int i, ErrorCode errorCode, ByteString byteString) {
            int i2;
            Object[] array;
            v03.j(byteString, "debugData");
            byteString.e();
            Http2Connection http2Connection = Http2Connection.this;
            synchronized (http2Connection) {
                array = http2Connection.k.values().toArray(new Http2Stream[0]);
                http2Connection.o = true;
            }
            for (Http2Stream http2Stream : (Http2Stream[]) array) {
                if (http2Stream.a > i && http2Stream.g()) {
                    http2Stream.j(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.q(http2Stream.a);
                }
            }
        }
    }

    static {
        Settings settings = new Settings();
        settings.c(7, AudioDriver.SPOTIFY_MAX_VOLUME);
        settings.c(5, 16384);
        K = settings;
    }

    public Http2Connection(Builder builder) {
        boolean z = builder.a;
        this.c = z;
        this.e = builder.g;
        this.k = new LinkedHashMap();
        String str = builder.d;
        if (str == null) {
            v03.Q("connectionName");
            throw null;
        }
        this.l = str;
        this.n = z ? 3 : 2;
        TaskRunner taskRunner = builder.b;
        this.f101p = taskRunner;
        TaskQueue taskQueueF = taskRunner.f();
        this.q = taskQueueF;
        this.r = taskRunner.f();
        this.s = taskRunner.f();
        this.t = builder.h;
        Settings settings = new Settings();
        if (z) {
            settings.c(7, 16777216);
        }
        this.z = settings;
        this.A = K;
        this.E = r3.a();
        Socket socket = builder.c;
        if (socket == null) {
            v03.Q("socket");
            throw null;
        }
        this.F = socket;
        io ioVar = builder.f;
        if (ioVar == null) {
            v03.Q("sink");
            throw null;
        }
        this.G = new Http2Writer(ioVar, z);
        ko koVar = builder.e;
        if (koVar == null) {
            v03.Q("source");
            throw null;
        }
        this.H = new ReaderRunnable(new Http2Reader(koVar, z));
        this.I = new LinkedHashSet();
        int i = builder.i;
        if (i != 0) {
            final long nanos = TimeUnit.MILLISECONDS.toNanos(i);
            final String strConcat = str.concat(" ping");
            taskQueueF.c(new Task(strConcat) { // from class: okhttp3.internal.http2.Http2Connection$special$$inlined$schedule$1
                @Override // okhttp3.internal.concurrent.Task
                public final long a() {
                    Http2Connection http2Connection;
                    boolean z2;
                    synchronized (this) {
                        http2Connection = this;
                        long j = http2Connection.v;
                        long j2 = http2Connection.u;
                        if (j < j2) {
                            z2 = true;
                        } else {
                            http2Connection.u = j2 + 1;
                            z2 = false;
                        }
                    }
                    if (z2) {
                        http2Connection.c(null);
                        return -1L;
                    }
                    try {
                        http2Connection.G.E(1, 0, false);
                    } catch (IOException e) {
                        http2Connection.c(e);
                    }
                    return nanos;
                }
            }, nanos);
        }
    }

    public final void E(ErrorCode errorCode) {
        synchronized (this.G) {
            Ref$IntRef ref$IntRef = new Ref$IntRef();
            synchronized (this) {
                if (this.o) {
                    return;
                }
                this.o = true;
                int i = this.m;
                ref$IntRef.element = i;
                this.G.q(i, errorCode, Util.a);
            }
        }
    }

    public final void E0(final int i, final long j) {
        final String str = this.l + '[' + i + "] windowUpdate";
        this.q.c(new Task(str) { // from class: okhttp3.internal.http2.Http2Connection$writeWindowUpdateLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public final long a() {
                Http2Connection http2Connection = this;
                try {
                    http2Connection.G.X(i, j);
                    return -1L;
                } catch (IOException e) {
                    Http2Connection.Companion companion = Http2Connection.J;
                    http2Connection.c(e);
                    return -1L;
                }
            }
        }, 0L);
    }

    public final synchronized void J(long j) {
        long j2 = this.B + j;
        this.B = j2;
        long j3 = j2 - this.C;
        if (j3 >= this.z.a() / 2) {
            E0(0, j3);
            this.C += j3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0035, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.G.l);
        r6 = r2;
        r8.D += r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void X(int r9, boolean r10, p.fo r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            okhttp3.internal.http2.Http2Writer r12 = r8.G
            r12.c(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L68
            monitor-enter(r8)
        L12:
            long r4 = r8.D     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            long r6 = r8.E     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L34
            java.util.LinkedHashMap r2 = r8.k     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            if (r2 == 0) goto L2c
            r8.wait()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            goto L12
        L2a:
            r9 = move-exception
            goto L66
        L2c:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            throw r9     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
        L34:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L2a
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L2a
            okhttp3.internal.http2.Http2Writer r4 = r8.G     // Catch: java.lang.Throwable -> L2a
            int r4 = r4.l     // Catch: java.lang.Throwable -> L2a
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L2a
            long r4 = r8.D     // Catch: java.lang.Throwable -> L2a
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L2a
            long r4 = r4 + r6
            r8.D = r4     // Catch: java.lang.Throwable -> L2a
            monitor-exit(r8)
            long r12 = r12 - r6
            okhttp3.internal.http2.Http2Writer r4 = r8.G
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.c(r5, r9, r11, r2)
            goto Ld
        L59:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r9.interrupt()     // Catch: java.lang.Throwable -> L2a
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L2a
            r9.<init>()     // Catch: java.lang.Throwable -> L2a
            throw r9     // Catch: java.lang.Throwable -> L2a
        L66:
            monitor-exit(r8)
            throw r9
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.X(int, boolean, p.fo, long):void");
    }

    public final void b(ErrorCode errorCode, ErrorCode errorCode2, IOException iOException) {
        int i;
        Object[] array;
        byte[] bArr = Util.a;
        try {
            E(errorCode);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (!this.k.isEmpty()) {
                array = this.k.values().toArray(new Http2Stream[0]);
                this.k.clear();
            } else {
                array = null;
            }
        }
        Http2Stream[] http2StreamArr = (Http2Stream[]) array;
        if (http2StreamArr != null) {
            for (Http2Stream http2Stream : http2StreamArr) {
                try {
                    http2Stream.c(errorCode2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.G.close();
        } catch (IOException unused3) {
        }
        try {
            this.F.close();
        } catch (IOException unused4) {
        }
        this.q.f();
        this.r.f();
        this.s.f();
    }

    public final void c(IOException iOException) {
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        b(errorCode, errorCode, iOException);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        b(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void flush() {
        this.G.flush();
    }

    public final synchronized Http2Stream i(int i) {
        return (Http2Stream) this.k.get(Integer.valueOf(i));
    }

    public final void o0(final int i, final ErrorCode errorCode) {
        final String str = this.l + '[' + i + "] writeSynReset";
        this.q.c(new Task(str) { // from class: okhttp3.internal.http2.Http2Connection$writeSynResetLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public final long a() {
                Http2Connection http2Connection = this;
                try {
                    int i2 = i;
                    ErrorCode errorCode2 = errorCode;
                    http2Connection.getClass();
                    v03.j(errorCode2, "statusCode");
                    http2Connection.G.J(i2, errorCode2);
                    return -1L;
                } catch (IOException e) {
                    Http2Connection.Companion companion = Http2Connection.J;
                    http2Connection.c(e);
                    return -1L;
                }
            }
        }, 0L);
    }

    public final synchronized Http2Stream q(int i) {
        Http2Stream http2Stream;
        http2Stream = (Http2Stream) this.k.remove(Integer.valueOf(i));
        notifyAll();
        return http2Stream;
    }
}
