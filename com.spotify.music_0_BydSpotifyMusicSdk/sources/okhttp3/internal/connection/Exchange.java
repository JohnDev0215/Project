package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import okhttp3.EventListener;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.ws.RealWebSocket;
import p.de2;
import p.fo;
import p.ow0;
import p.pd2;
import p.pw0;
import p.v02;
import p.v03;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class Exchange {
    public final RealCall a;
    public final EventListener b;
    public final ExchangeFinder c;
    public final ExchangeCodec d;
    public boolean e;
    public boolean f;
    public final RealConnection g;

    public final class RequestBodySink extends ow0 {
        public final long e;
        public boolean k;
        public long l;
        public boolean m;
        public final /* synthetic */ Exchange n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RequestBodySink(Exchange exchange, pd2 pd2Var, long j) {
            super(pd2Var);
            v03.j(pd2Var, "delegate");
            this.n = exchange;
            this.e = j;
        }

        @Override // p.ow0, p.pd2
        public final void Y(fo foVar, long j) throws IOException {
            v03.j(foVar, "source");
            if (!(!this.m)) {
                throw new IllegalStateException("closed".toString());
            }
            long j2 = this.e;
            if (j2 == -1 || this.l + j <= j2) {
                try {
                    super.Y(foVar, j);
                    this.l += j;
                    return;
                } catch (IOException e) {
                    throw b(e);
                }
            }
            throw new ProtocolException("expected " + j2 + " bytes but received " + (this.l + j));
        }

        public final IOException b(IOException iOException) {
            if (this.k) {
                return iOException;
            }
            this.k = true;
            return this.n.a(false, true, iOException);
        }

        @Override // p.ow0, p.pd2, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            if (this.m) {
                return;
            }
            this.m = true;
            long j = this.e;
            if (j != -1 && this.l != j) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                b(null);
            } catch (IOException e) {
                throw b(e);
            }
        }

        @Override // p.ow0, p.pd2, java.io.Flushable
        public final void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e) {
                throw b(e);
            }
        }
    }

    public final class ResponseBodySource extends pw0 {
        public final long e;
        public long k;
        public boolean l;
        public boolean m;
        public boolean n;
        public final /* synthetic */ Exchange o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ResponseBodySource(Exchange exchange, de2 de2Var, long j) {
            super(de2Var);
            v03.j(de2Var, "delegate");
            this.o = exchange;
            this.e = j;
            this.l = true;
            if (j == 0) {
                b(null);
            }
        }

        public final IOException b(IOException iOException) {
            if (this.m) {
                return iOException;
            }
            this.m = true;
            Exchange exchange = this.o;
            if (iOException == null && this.l) {
                this.l = false;
                exchange.b.getClass();
                v03.j(exchange.a, "call");
            }
            return exchange.a(true, false, iOException);
        }

        @Override // p.pw0, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            if (this.n) {
                return;
            }
            this.n = true;
            try {
                super.close();
                b(null);
            } catch (IOException e) {
                throw b(e);
            }
        }

        @Override // p.pw0, p.de2
        public final long t(fo foVar, long j) throws IOException {
            v03.j(foVar, "sink");
            if (!(!this.n)) {
                throw new IllegalStateException("closed".toString());
            }
            try {
                long jT = this.c.t(foVar, j);
                if (this.l) {
                    this.l = false;
                    Exchange exchange = this.o;
                    EventListener eventListener = exchange.b;
                    RealCall realCall = exchange.a;
                    eventListener.getClass();
                    v03.j(realCall, "call");
                }
                if (jT == -1) {
                    b(null);
                    return -1L;
                }
                long j2 = this.k + jT;
                long j3 = this.e;
                if (j3 == -1 || j2 <= j3) {
                    this.k = j2;
                    if (j2 == j3) {
                        b(null);
                    }
                    return jT;
                }
                throw new ProtocolException("expected " + j3 + " bytes but received " + j2);
            } catch (IOException e) {
                throw b(e);
            }
        }
    }

    public Exchange(RealCall realCall, EventListener eventListener, ExchangeFinder exchangeFinder, ExchangeCodec exchangeCodec) {
        v03.j(eventListener, "eventListener");
        this.a = realCall;
        this.b = eventListener;
        this.c = exchangeFinder;
        this.d = exchangeCodec;
        this.g = exchangeCodec.h();
    }

    public final IOException a(boolean z, boolean z2, IOException iOException) {
        if (iOException != null) {
            f(iOException);
        }
        EventListener eventListener = this.b;
        RealCall realCall = this.a;
        if (z2) {
            if (iOException != null) {
                eventListener.getClass();
                v03.j(realCall, "call");
            } else {
                eventListener.getClass();
                v03.j(realCall, "call");
            }
        }
        if (z) {
            if (iOException != null) {
                eventListener.getClass();
                v03.j(realCall, "call");
            } else {
                eventListener.getClass();
                v03.j(realCall, "call");
            }
        }
        return realCall.h(this, z2, z, iOException);
    }

    public final pd2 b(Request request) {
        this.e = false;
        RequestBody requestBody = request.d;
        v03.h(requestBody);
        long jA = requestBody.a();
        this.b.getClass();
        v03.j(this.a, "call");
        return new RequestBodySink(this, this.d.f(request, jA), jA);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [okhttp3.internal.connection.RealConnection$newWebSocketStreams$1] */
    public final RealConnection$newWebSocketStreams$1 c() throws SocketException {
        RealCall realCall = this.a;
        if (!(!realCall.s)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        realCall.s = true;
        realCall.n.j();
        RealConnection realConnectionH = this.d.h();
        realConnectionH.getClass();
        Socket socket = realConnectionH.d;
        v03.h(socket);
        final w02 w02Var = realConnectionH.h;
        v03.h(w02Var);
        final v02 v02Var = realConnectionH.i;
        v03.h(v02Var);
        socket.setSoTimeout(0);
        realConnectionH.k();
        return new RealWebSocket.Streams(w02Var, v02Var) { // from class: okhttp3.internal.connection.RealConnection$newWebSocketStreams$1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                this.a(true, true, null);
            }
        };
    }

    public final RealResponseBody d(Response response) throws IOException {
        ExchangeCodec exchangeCodec = this.d;
        try {
            String strC = Response.c(response, "Content-Type");
            long jD = exchangeCodec.d(response);
            return new RealResponseBody(strC, jD, v03.d(new ResponseBodySource(this, exchangeCodec.e(response), jD)));
        } catch (IOException e) {
            this.b.getClass();
            v03.j(this.a, "call");
            f(e);
            throw e;
        }
    }

    public final Response.Builder e(boolean z) throws IOException {
        try {
            Response.Builder builderG = this.d.g(z);
            if (builderG != null) {
                builderG.m = this;
            }
            return builderG;
        } catch (IOException e) {
            this.b.getClass();
            v03.j(this.a, "call");
            f(e);
            throw e;
        }
    }

    public final void f(IOException iOException) {
        this.f = true;
        this.c.c(iOException);
        RealConnection realConnectionH = this.d.h();
        RealCall realCall = this.a;
        synchronized (realConnectionH) {
            try {
                v03.j(realCall, "call");
                if (iOException instanceof StreamResetException) {
                    if (((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
                        int i = realConnectionH.n + 1;
                        realConnectionH.n = i;
                        if (i > 1) {
                            realConnectionH.j = true;
                            realConnectionH.l++;
                        }
                    } else if (((StreamResetException) iOException).errorCode != ErrorCode.CANCEL || !realCall.x) {
                        realConnectionH.j = true;
                        realConnectionH.l++;
                    }
                } else if (realConnectionH.g == null || (iOException instanceof ConnectionShutdownException)) {
                    realConnectionH.j = true;
                    if (realConnectionH.m == 0) {
                        RealConnection.d(realCall.c, realConnectionH.b, iOException);
                        realConnectionH.l++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
