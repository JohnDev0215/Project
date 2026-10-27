package okhttp3.internal.ws;

import com.spotify.playbacknative.AudioDriver;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection$newWebSocketStreams$1;
import okhttp3.internal.ws.WebSocketReader;
import okio.ByteString;
import p.eo;
import p.f80;
import p.fo;
import p.hf2;
import p.io;
import p.ko;
import p.ng2;
import p.qo;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    public static final List x;
    public final Request a;
    public final WebSocketListener b;
    public final Random c;
    public final long d;
    public WebSocketExtensions e;
    public final long f;
    public final String g;
    public RealCall h;
    public Task i;
    public WebSocketReader j;
    public WebSocketWriter k;
    public final TaskQueue l;
    public String m;
    public Streams n;
    public final ArrayDeque o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayDeque f102p;
    public long q;
    public boolean r;
    public int s;
    public String t;
    public boolean u;
    public int v;
    public boolean w;

    public static final class Close {
        public final int a;
        public final ByteString b;
        public final long c = 60000;

        public Close(int i, ByteString byteString) {
            this.a = i;
            this.b = byteString;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static final class Message {
        public final int a;
        public final ByteString b;

        public Message(int i, ByteString byteString) {
            this.a = i;
            this.b = byteString;
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean c = true;
        public final ko e;
        public final io k;

        public Streams(ko koVar, io ioVar) {
            this.e = koVar;
            this.k = ioVar;
        }
    }

    public final class WriterTask extends Task {
        public WriterTask() {
            super(u4.m(new StringBuilder(), RealWebSocket.this.m, " writer"), true);
        }

        @Override // okhttp3.internal.concurrent.Task
        public final long a() {
            RealWebSocket realWebSocket = RealWebSocket.this;
            try {
                return realWebSocket.o() ? 0L : -1L;
            } catch (IOException e) {
                realWebSocket.j(e, null);
                return -1L;
            }
        }
    }

    static {
        new Companion(0);
        x = v03.B(Protocol.HTTP_1_1);
    }

    public RealWebSocket(TaskRunner taskRunner, Request request, f80 f80Var, Random random, long j, long j2) {
        v03.j(taskRunner, "taskRunner");
        this.a = request;
        this.b = f80Var;
        this.c = random;
        this.d = j;
        this.e = null;
        this.f = j2;
        this.l = taskRunner.f();
        this.o = new ArrayDeque();
        this.f102p = new ArrayDeque();
        this.s = -1;
        String str = request.b;
        if (!v03.b(com.spotify.cosmos.cosmos.Request.GET, str)) {
            throw new IllegalArgumentException(u4.x("Request must be GET: ", str).toString());
        }
        ByteString byteString = ByteString.k;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.g = qo.s(bArr).a();
    }

    @Override // okhttp3.WebSocket
    public final boolean a(ByteString byteString) {
        return n(2, byteString);
    }

    @Override // okhttp3.WebSocket
    public final boolean b(int i, String str) {
        ByteString byteStringP;
        synchronized (this) {
            try {
                WebSocketProtocol.a.getClass();
                String strA = WebSocketProtocol.a(i);
                if (strA != null) {
                    throw new IllegalArgumentException(strA.toString());
                }
                if (str != null) {
                    ByteString byteString = ByteString.k;
                    byteStringP = qo.p(str);
                    if (byteStringP.e() > 123) {
                        throw new IllegalArgumentException("reason.size() > 123: ".concat(str).toString());
                    }
                } else {
                    byteStringP = null;
                }
                if (!this.u && !this.r) {
                    this.r = true;
                    this.f102p.add(new Close(i, byteStringP));
                    m();
                    return true;
                }
                return false;
            } finally {
            }
        }
    }

    @Override // okhttp3.WebSocket
    public final boolean c(String str) {
        ByteString byteString = ByteString.k;
        return n(1, qo.p(str));
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public final void d(ByteString byteString) {
        v03.j(byteString, "bytes");
        this.b.e(this, byteString);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public final void e(String str) {
        this.b.d(this, str);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public final synchronized void f(ByteString byteString) {
        v03.j(byteString, "payload");
        this.w = false;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public final synchronized void g(ByteString byteString) {
        try {
            v03.j(byteString, "payload");
            if (!this.u && (!this.r || !this.f102p.isEmpty())) {
                this.o.add(byteString);
                m();
            }
        } finally {
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public final void h(int i, String str) {
        Streams streams;
        WebSocketReader webSocketReader;
        WebSocketWriter webSocketWriter;
        if (i == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        synchronized (this) {
            if (this.s != -1) {
                throw new IllegalStateException("already closed".toString());
            }
            this.s = i;
            this.t = str;
            streams = null;
            if (this.r && this.f102p.isEmpty()) {
                Streams streams2 = this.n;
                this.n = null;
                webSocketReader = this.j;
                this.j = null;
                webSocketWriter = this.k;
                this.k = null;
                this.l.f();
                streams = streams2;
            } else {
                webSocketReader = null;
                webSocketWriter = null;
            }
        }
        try {
            this.b.b(this, i, str);
            if (streams != null) {
                this.b.a(this, str);
            }
        } finally {
            if (streams != null) {
                Util.c(streams);
            }
            if (webSocketReader != null) {
                Util.c(webSocketReader);
            }
            if (webSocketWriter != null) {
                Util.c(webSocketWriter);
            }
        }
    }

    public final void i(Response response, Exchange exchange) throws ProtocolException {
        int i = response.l;
        if (i != 101) {
            StringBuilder sb = new StringBuilder("Expected HTTP 101 response but was '");
            sb.append(i);
            sb.append(' ');
            throw new ProtocolException(hf2.d(sb, response.k, '\''));
        }
        String strC = Response.c(response, "Connection");
        if (!ng2.b0("Upgrade", strC, true)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + strC + '\'');
        }
        String strC2 = Response.c(response, "Upgrade");
        if (!ng2.b0("websocket", strC2, true)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + strC2 + '\'');
        }
        String strC3 = Response.c(response, "Sec-WebSocket-Accept");
        ByteString byteString = ByteString.k;
        String strA = qo.p(this.g + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").c("SHA-1").a();
        if (v03.b(strA, strC3)) {
            if (exchange == null) {
                throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
            }
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strA + "' but was '" + strC3 + '\'');
    }

    public final void j(Exception exc, Response response) {
        synchronized (this) {
            if (this.u) {
                return;
            }
            this.u = true;
            Streams streams = this.n;
            this.n = null;
            WebSocketReader webSocketReader = this.j;
            this.j = null;
            WebSocketWriter webSocketWriter = this.k;
            this.k = null;
            this.l.f();
            try {
                this.b.c(this, exc);
            } finally {
                if (streams != null) {
                    Util.c(streams);
                }
                if (webSocketReader != null) {
                    Util.c(webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.c(webSocketWriter);
                }
            }
        }
    }

    public final void k(String str, RealConnection$newWebSocketStreams$1 realConnection$newWebSocketStreams$1) {
        v03.j(str, "name");
        WebSocketExtensions webSocketExtensions = this.e;
        v03.h(webSocketExtensions);
        synchronized (this) {
            try {
                this.m = str;
                this.n = realConnection$newWebSocketStreams$1;
                boolean z = realConnection$newWebSocketStreams$1.c;
                this.k = new WebSocketWriter(z, realConnection$newWebSocketStreams$1.k, this.c, webSocketExtensions.a, z ? webSocketExtensions.c : webSocketExtensions.e, this.f);
                this.i = new WriterTask();
                long j = this.d;
                if (j != 0) {
                    final long nanos = TimeUnit.MILLISECONDS.toNanos(j);
                    TaskQueue taskQueue = this.l;
                    final String strConcat = str.concat(" ping");
                    taskQueue.c(new Task(strConcat) { // from class: okhttp3.internal.ws.RealWebSocket$initReaderAndWriter$lambda$3$$inlined$schedule$1
                        @Override // okhttp3.internal.concurrent.Task
                        public final long a() {
                            WebSocketWriter webSocketWriter;
                            RealWebSocket realWebSocket = this;
                            synchronized (realWebSocket) {
                                try {
                                    if (!realWebSocket.u && (webSocketWriter = realWebSocket.k) != null) {
                                        int i = realWebSocket.w ? realWebSocket.v : -1;
                                        realWebSocket.v++;
                                        realWebSocket.w = true;
                                        if (i != -1) {
                                            StringBuilder sb = new StringBuilder("sent ping but didn't receive pong within ");
                                            sb.append(realWebSocket.d);
                                            sb.append("ms (after ");
                                            realWebSocket.j(new SocketTimeoutException(u4.l(sb, i - 1, " successful ping/pongs)")), null);
                                        } else {
                                            try {
                                                ByteString byteString = ByteString.k;
                                                v03.j(byteString, "payload");
                                                webSocketWriter.b(9, byteString);
                                            } catch (IOException e) {
                                                realWebSocket.j(e, null);
                                            }
                                        }
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            return nanos;
                        }
                    }, nanos);
                }
                if (!this.f102p.isEmpty()) {
                    m();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        boolean z2 = realConnection$newWebSocketStreams$1.c;
        this.j = new WebSocketReader(z2, realConnection$newWebSocketStreams$1.e, this, webSocketExtensions.a, z2 ^ true ? webSocketExtensions.c : webSocketExtensions.e);
    }

    public final void l() throws IOException {
        while (this.s == -1) {
            WebSocketReader webSocketReader = this.j;
            v03.h(webSocketReader);
            webSocketReader.c();
            if (!webSocketReader.r) {
                int i = webSocketReader.o;
                if (i != 1 && i != 2) {
                    byte[] bArr = Util.a;
                    String hexString = Integer.toHexString(i);
                    v03.i(hexString, "toHexString(this)");
                    throw new ProtocolException("Unknown opcode: ".concat(hexString));
                }
                while (!webSocketReader.n) {
                    long j = webSocketReader.f103p;
                    fo foVar = webSocketReader.u;
                    if (j > 0) {
                        webSocketReader.e.p(foVar, j);
                        if (!webSocketReader.c) {
                            eo eoVar = webSocketReader.x;
                            v03.h(eoVar);
                            foVar.L0(eoVar);
                            eoVar.c(foVar.e - webSocketReader.f103p);
                            WebSocketProtocol webSocketProtocol = WebSocketProtocol.a;
                            byte[] bArr2 = webSocketReader.w;
                            v03.h(bArr2);
                            webSocketProtocol.getClass();
                            WebSocketProtocol.b(eoVar, bArr2);
                            eoVar.close();
                        }
                    }
                    if (webSocketReader.q) {
                        if (webSocketReader.s) {
                            MessageInflater messageInflater = webSocketReader.v;
                            if (messageInflater == null) {
                                messageInflater = new MessageInflater(webSocketReader.m);
                                webSocketReader.v = messageInflater;
                            }
                            v03.j(foVar, "buffer");
                            fo foVar2 = messageInflater.e;
                            if (foVar2.e != 0) {
                                throw new IllegalArgumentException("Failed requirement.".toString());
                            }
                            Inflater inflater = messageInflater.k;
                            if (messageInflater.c) {
                                inflater.reset();
                            }
                            foVar2.L(foVar);
                            foVar2.Z0(AudioDriver.SPOTIFY_MAX_VOLUME);
                            long bytesRead = inflater.getBytesRead() + foVar2.e;
                            do {
                                messageInflater.l.b(foVar, Long.MAX_VALUE);
                            } while (inflater.getBytesRead() < bytesRead);
                        }
                        WebSocketReader.FrameCallback frameCallback = webSocketReader.k;
                        if (i == 1) {
                            frameCallback.e(foVar.Q0());
                        } else {
                            frameCallback.d(foVar.v(foVar.e));
                        }
                    } else {
                        while (!webSocketReader.n) {
                            webSocketReader.c();
                            if (!webSocketReader.r) {
                                break;
                            } else {
                                webSocketReader.b();
                            }
                        }
                        if (webSocketReader.o != 0) {
                            int i2 = webSocketReader.o;
                            byte[] bArr3 = Util.a;
                            String hexString2 = Integer.toHexString(i2);
                            v03.i(hexString2, "toHexString(this)");
                            throw new ProtocolException("Expected continuation opcode. Got: ".concat(hexString2));
                        }
                    }
                }
                throw new IOException("closed");
            }
            webSocketReader.b();
        }
    }

    public final void m() {
        byte[] bArr = Util.a;
        Task task = this.i;
        if (task != null) {
            this.l.c(task, 0L);
        }
    }

    public final synchronized boolean n(int i, ByteString byteString) {
        if (!this.u && !this.r) {
            if (this.q + ((long) byteString.e()) > 16777216) {
                b(1001, null);
                return false;
            }
            this.q += (long) byteString.e();
            this.f102p.add(new Message(i, byteString));
            m();
            return true;
        }
        return false;
    }

    public final boolean o() {
        Streams streams;
        String str;
        WebSocketReader webSocketReader;
        Closeable closeable;
        synchronized (this) {
            try {
                if (this.u) {
                    return false;
                }
                WebSocketWriter webSocketWriter = this.k;
                Object objPoll = this.o.poll();
                Object obj = null;
                if (objPoll == null) {
                    Object objPoll2 = this.f102p.poll();
                    if (objPoll2 instanceof Close) {
                        int i = this.s;
                        str = this.t;
                        if (i != -1) {
                            streams = this.n;
                            this.n = null;
                            webSocketReader = this.j;
                            this.j = null;
                            closeable = this.k;
                            this.k = null;
                            this.l.f();
                            obj = objPoll2;
                        } else {
                            long j = ((Close) objPoll2).c;
                            TaskQueue taskQueue = this.l;
                            final String str2 = this.m + " cancel";
                            taskQueue.c(new Task(str2) { // from class: okhttp3.internal.ws.RealWebSocket$writeOneFrame$lambda$8$$inlined$execute$default$1
                                @Override // okhttp3.internal.concurrent.Task
                                public final long a() {
                                    RealCall realCall = this.h;
                                    v03.h(realCall);
                                    realCall.cancel();
                                    return -1L;
                                }
                            }, TimeUnit.MILLISECONDS.toNanos(j));
                            streams = null;
                            webSocketReader = null;
                        }
                    } else {
                        if (objPoll2 == null) {
                            return false;
                        }
                        streams = null;
                        str = null;
                        webSocketReader = null;
                    }
                    closeable = webSocketReader;
                    obj = objPoll2;
                } else {
                    streams = null;
                    str = null;
                    webSocketReader = null;
                    closeable = null;
                }
                try {
                    if (objPoll != null) {
                        v03.h(webSocketWriter);
                        webSocketWriter.b(10, (ByteString) objPoll);
                    } else if (obj instanceof Message) {
                        Message message = (Message) obj;
                        v03.h(webSocketWriter);
                        webSocketWriter.c(message.a, message.b);
                        synchronized (this) {
                            this.q -= (long) message.b.e();
                        }
                    } else {
                        if (!(obj instanceof Close)) {
                            throw new AssertionError();
                        }
                        Close close = (Close) obj;
                        v03.h(webSocketWriter);
                        int i2 = close.a;
                        ByteString byteString = close.b;
                        ByteString byteStringV = ByteString.k;
                        if (i2 != 0 || byteString != null) {
                            if (i2 != 0) {
                                WebSocketProtocol.a.getClass();
                                String strA = WebSocketProtocol.a(i2);
                                if (strA != null) {
                                    throw new IllegalArgumentException(strA.toString());
                                }
                            }
                            fo foVar = new fo();
                            foVar.b1(i2);
                            if (byteString != null) {
                                foVar.T0(byteString);
                            }
                            byteStringV = foVar.v(foVar.e);
                        }
                        try {
                            webSocketWriter.b(8, byteStringV);
                            if (streams != null) {
                                WebSocketListener webSocketListener = this.b;
                                v03.h(str);
                                webSocketListener.a(this, str);
                            }
                        } finally {
                            webSocketWriter.q = true;
                        }
                    }
                    return true;
                } finally {
                    if (streams != null) {
                        Util.c(streams);
                    }
                    if (webSocketReader != null) {
                        Util.c(webSocketReader);
                    }
                    if (closeable != null) {
                        Util.c(closeable);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
