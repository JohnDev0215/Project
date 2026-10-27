package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.a;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.platform.Platform;
import p.uc;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RealCall implements Call {
    public final OkHttpClient c;
    public final Request e;
    public final boolean k;
    public final RealConnectionPool l;
    public final EventListener m;
    public final RealCall$timeout$1 n;
    public final AtomicBoolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f98p;
    public ExchangeFinder q;
    public RealConnection r;
    public boolean s;
    public Exchange t;
    public boolean u;
    public boolean v;
    public boolean w;
    public volatile boolean x;
    public volatile Exchange y;
    public volatile RealConnection z;

    public final class AsyncCall implements Runnable {
        public final Callback c;
        public volatile AtomicInteger e = new AtomicInteger(0);

        public AsyncCall(Callback callback) {
            this.c = callback;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Dispatcher dispatcher;
            String str = "OkHttp " + RealCall.this.e.a.g();
            RealCall realCall = RealCall.this;
            Thread threadCurrentThread = Thread.currentThread();
            String name = threadCurrentThread.getName();
            threadCurrentThread.setName(str);
            try {
                realCall.n.i();
                boolean z = false;
                try {
                    try {
                    } catch (IOException e) {
                        e = e;
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        this.c.onResponse(realCall, realCall.g());
                        dispatcher = realCall.c.c;
                    } catch (IOException e2) {
                        e = e2;
                        z = true;
                        if (z) {
                            Platform.a.getClass();
                            Platform platform = Platform.b;
                            String str2 = "Callback failure for " + RealCall.a(realCall);
                            platform.getClass();
                            Platform.i(4, str2, e);
                        } else {
                            this.c.onFailure(realCall, e);
                        }
                        dispatcher = realCall.c.c;
                    } catch (Throwable th2) {
                        th = th2;
                        z = true;
                        realCall.cancel();
                        if (!z) {
                            IOException iOException = new IOException("canceled due to " + th);
                            a.a(iOException, th);
                            this.c.onFailure(realCall, iOException);
                        }
                        throw th;
                    }
                    dispatcher.b(this);
                } catch (Throwable th3) {
                    realCall.c.c.b(this);
                    throw th3;
                }
            } finally {
                threadCurrentThread.setName(name);
            }
        }
    }

    public static final class CallReference extends WeakReference<RealCall> {
        public final Object a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CallReference(RealCall realCall, Object obj) {
            super(realCall);
            v03.j(realCall, "referent");
            this.a = obj;
        }
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [okhttp3.internal.connection.RealCall$timeout$1, p.tl2] */
    public RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        v03.j(okHttpClient, "client");
        v03.j(request, "originalRequest");
        this.c = okHttpClient;
        this.e = request;
        this.k = z;
        this.l = okHttpClient.e.a;
        this.m = okHttpClient.m.a(this);
        ?? r4 = new uc() { // from class: okhttp3.internal.connection.RealCall$timeout$1
            @Override // p.uc
            public final void l() {
                this.m.cancel();
            }
        };
        r4.g(okHttpClient.F, TimeUnit.MILLISECONDS);
        this.n = r4;
        this.o = new AtomicBoolean();
        this.w = true;
    }

    public static final String a(RealCall realCall) {
        StringBuilder sb = new StringBuilder();
        sb.append(realCall.x ? "canceled " : "");
        sb.append(realCall.k ? "web socket" : "call");
        sb.append(" to ");
        sb.append(realCall.e.a.g());
        return sb.toString();
    }

    @Override // okhttp3.Call
    public final void J(Callback callback) {
        AsyncCall asyncCall;
        if (!this.o.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        Platform.a.getClass();
        this.f98p = Platform.b.g();
        this.m.getClass();
        Dispatcher dispatcher = this.c.c;
        AsyncCall asyncCall2 = new AsyncCall(callback);
        dispatcher.getClass();
        synchronized (dispatcher) {
            dispatcher.d.add(asyncCall2);
            if (!this.k) {
                String str = this.e.a.d;
                Iterator it = dispatcher.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        Iterator it2 = dispatcher.d.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                asyncCall = null;
                                break;
                            } else {
                                asyncCall = (AsyncCall) it2.next();
                                if (v03.b(RealCall.this.e.a.d, str)) {
                                    break;
                                }
                            }
                        }
                    } else {
                        asyncCall = (AsyncCall) it.next();
                        if (v03.b(RealCall.this.e.a.d, str)) {
                            break;
                        }
                    }
                }
                if (asyncCall != null) {
                    asyncCall2.e = asyncCall.e;
                }
            }
        }
        dispatcher.c();
    }

    @Override // okhttp3.Call
    public final Response b() {
        if (!this.o.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        i();
        Platform.a.getClass();
        this.f98p = Platform.b.g();
        this.m.getClass();
        try {
            Dispatcher dispatcher = this.c.c;
            synchronized (dispatcher) {
                dispatcher.f.add(this);
            }
            return g();
        } finally {
            Dispatcher dispatcher2 = this.c.c;
            dispatcher2.getClass();
            dispatcher2.a(dispatcher2.f, this);
        }
    }

    @Override // okhttp3.Call
    public final Request c() {
        return this.e;
    }

    @Override // okhttp3.Call
    public final void cancel() {
        Socket socket;
        if (this.x) {
            return;
        }
        this.x = true;
        Exchange exchange = this.y;
        if (exchange != null) {
            exchange.d.cancel();
        }
        RealConnection realConnection = this.z;
        if (realConnection != null && (socket = realConnection.c) != null) {
            Util.d(socket);
        }
        this.m.getClass();
    }

    public final Object clone() {
        return new RealCall(this.c, this.e, this.k);
    }

    public final void d(RealConnection realConnection) {
        byte[] bArr = Util.a;
        if (this.r != null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.r = realConnection;
        realConnection.f99p.add(new CallReference(this, this.f98p));
    }

    public final IOException e(IOException iOException) {
        IOException interruptedIOException;
        Socket socketK;
        byte[] bArr = Util.a;
        RealConnection realConnection = this.r;
        if (realConnection != null) {
            synchronized (realConnection) {
                socketK = k();
            }
            if (this.r == null) {
                if (socketK != null) {
                    Util.d(socketK);
                }
                this.m.getClass();
            } else if (socketK != null) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        if (!this.s && j()) {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        } else {
            interruptedIOException = iOException;
        }
        if (iOException != null) {
            EventListener eventListener = this.m;
            v03.h(interruptedIOException);
            eventListener.getClass();
        } else {
            this.m.getClass();
        }
        return interruptedIOException;
    }

    public final void f(boolean z) {
        Exchange exchange;
        synchronized (this) {
            if (!this.w) {
                throw new IllegalStateException("released".toString());
            }
        }
        if (z && (exchange = this.y) != null) {
            exchange.d.cancel();
            exchange.a.h(exchange, true, true, null);
        }
        this.t = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okhttp3.Response g() throws java.lang.Throwable {
        /*
            r11 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            okhttp3.OkHttpClient r0 = r11.c
            java.util.List r0 = r0.k
            p.nu.X(r0, r2)
            okhttp3.internal.http.RetryAndFollowUpInterceptor r0 = new okhttp3.internal.http.RetryAndFollowUpInterceptor
            okhttp3.OkHttpClient r1 = r11.c
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.http.BridgeInterceptor r0 = new okhttp3.internal.http.BridgeInterceptor
            okhttp3.OkHttpClient r1 = r11.c
            okhttp3.CookieJar r1 = r1.r
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.cache.CacheInterceptor r0 = new okhttp3.internal.cache.CacheInterceptor
            okhttp3.OkHttpClient r1 = r11.c
            okhttp3.Cache r1 = r1.s
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.connection.ConnectInterceptor r0 = okhttp3.internal.connection.ConnectInterceptor.b
            r2.add(r0)
            boolean r0 = r11.k
            if (r0 != 0) goto L3e
            okhttp3.OkHttpClient r0 = r11.c
            java.util.List r0 = r0.l
            p.nu.X(r0, r2)
        L3e:
            okhttp3.internal.http.CallServerInterceptor r0 = new okhttp3.internal.http.CallServerInterceptor
            boolean r1 = r11.k
            r0.<init>(r1)
            r2.add(r0)
            okhttp3.internal.http.RealInterceptorChain r9 = new okhttp3.internal.http.RealInterceptorChain
            r3 = 0
            r4 = 0
            okhttp3.Request r5 = r11.e
            okhttp3.OkHttpClient r0 = r11.c
            int r6 = r0.G
            int r7 = r0.H
            int r8 = r0.I
            r0 = r9
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            r1 = 0
            okhttp3.Request r2 = r11.e     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            okhttp3.Response r2 = r9.a(r2)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            boolean r3 = r11.x     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            if (r3 != 0) goto L6b
            r11.j(r0)
            return r2
        L6b:
            okhttp3.internal.Util.c(r2)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            throw r2     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
        L76:
            r2 = move-exception
            goto L88
        L78:
            r1 = move-exception
            r2 = 1
            java.io.IOException r1 = r11.j(r1)     // Catch: java.lang.Throwable -> L84
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            p.v03.g(r1, r3)     // Catch: java.lang.Throwable -> L84
            throw r1     // Catch: java.lang.Throwable -> L84
        L84:
            r1 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
        L88:
            if (r1 != 0) goto L8d
            r11.j(r0)
        L8d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealCall.g():okhttp3.Response");
    }

    public final IOException h(Exchange exchange, boolean z, boolean z2, IOException iOException) {
        boolean z3;
        boolean z4;
        v03.j(exchange, "exchange");
        if (!v03.b(exchange, this.y)) {
            return iOException;
        }
        synchronized (this) {
            z3 = false;
            if (z) {
                try {
                    if (!this.u) {
                        if (z2 || !this.v) {
                            z4 = false;
                        }
                    }
                    if (z) {
                        this.u = false;
                    }
                    if (z2) {
                        this.v = false;
                    }
                    boolean z5 = this.u;
                    boolean z6 = (z5 || this.v) ? false : true;
                    if (!z5 && !this.v) {
                        if (!this.w) {
                            z3 = true;
                        }
                    }
                    z4 = z3;
                    z3 = z6;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (z2) {
                }
                z4 = false;
            }
        }
        if (z3) {
            this.y = null;
            RealConnection realConnection = this.r;
            if (realConnection != null) {
                synchronized (realConnection) {
                    realConnection.m++;
                }
            }
        }
        return z4 ? e(iOException) : iOException;
    }

    @Override // okhttp3.Call
    public final boolean i() {
        return this.x;
    }

    public final IOException j(IOException iOException) {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.w) {
                this.w = false;
                if (!this.u) {
                    if (!this.v) {
                        z = true;
                    }
                }
            }
        }
        return z ? e(iOException) : iOException;
    }

    public final Socket k() {
        RealConnection realConnection = this.r;
        v03.h(realConnection);
        byte[] bArr = Util.a;
        ArrayList arrayList = realConnection.f99p;
        Iterator it = arrayList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (v03.b(((Reference) it.next()).get(), this)) {
                break;
            }
            i++;
        }
        if (i == -1) {
            throw new IllegalStateException("Check failed.".toString());
        }
        arrayList.remove(i);
        this.r = null;
        if (arrayList.isEmpty()) {
            realConnection.q = System.nanoTime();
            RealConnectionPool realConnectionPool = this.l;
            realConnectionPool.getClass();
            byte[] bArr2 = Util.a;
            boolean z = realConnection.j;
            TaskQueue taskQueue = realConnectionPool.c;
            if (z || realConnectionPool.a == 0) {
                realConnection.j = true;
                ConcurrentLinkedQueue concurrentLinkedQueue = realConnectionPool.e;
                concurrentLinkedQueue.remove(realConnection);
                if (concurrentLinkedQueue.isEmpty()) {
                    taskQueue.a();
                }
                Socket socket = realConnection.d;
                v03.h(socket);
                return socket;
            }
            taskQueue.c(realConnectionPool.d, 0L);
        }
        return null;
    }
}
