package spi;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.dt;
import spi.fs;
import spi.ln;

/* JADX INFO: loaded from: classes2.dex */
public final class pe implements z8 {
    public int a;
    public final le b;
    public ke c;
    public final dk d;

    @NotNull
    public final wl e;
    public final l2 f;
    public final k2 g;

    public abstract class a implements vr {

        @NotNull
        public final jc a;
        public boolean b;

        public a() {
            this.a = new jc(pe.this.f.timeout());
        }

        public final void e() {
            pe peVar = pe.this;
            int i = peVar.a;
            if (i == 6) {
                return;
            }
            if (i == 5) {
                pe.i(peVar, this.a);
                pe.this.a = 6;
            } else {
                StringBuilder sbK = spi.e.k("state: ");
                sbK.append(pe.this.a);
                throw new IllegalStateException(sbK.toString());
            }
        }

        @Override // spi.vr
        public long read(@NotNull g2 sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            try {
                return pe.this.f.read(sink, j);
            } catch (IOException e) {
                pe.this.e.l();
                e();
                throw e;
            }
        }

        @Override // spi.vr
        @NotNull
        public final dt timeout() {
            return this.a;
        }
    }

    public final class b implements rp {
        public final jc a;
        public boolean b;

        public b() {
            this.a = new jc(pe.this.g.timeout());
        }

        @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
        public final synchronized void close() {
            if (this.b) {
                return;
            }
            this.b = true;
            pe.this.g.l("0\r\n\r\n");
            pe.i(pe.this, this.a);
            pe.this.a = 3;
        }

        @Override // spi.rp, java.io.Flushable
        public final synchronized void flush() {
            if (this.b) {
                return;
            }
            pe.this.g.flush();
        }

        @Override // spi.rp
        public final void p(@NotNull g2 source, long j) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!(!this.b)) {
                throw new IllegalStateException("closed".toString());
            }
            if (j == 0) {
                return;
            }
            pe.this.g.o(j);
            pe.this.g.l("\r\n");
            pe.this.g.p(source, j);
            pe.this.g.l("\r\n");
        }

        @Override // spi.rp
        @NotNull
        public final dt timeout() {
            return this.a;
        }
    }

    public final class c extends a {
        public long d;
        public boolean e;
        public final hf f;
        public final /* synthetic */ pe g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(@NotNull pe peVar, hf url) {
            super();
            Intrinsics.checkNotNullParameter(url, "url");
            this.g = peVar;
            this.f = url;
            this.d = -1L;
            this.e = true;
        }

        @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.b) {
                return;
            }
            if (this.e) {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                if (!uu.g(this)) {
                    this.g.e.l();
                    e();
                }
            }
            this.b = true;
        }

        @Override // spi.pe.a, spi.vr
        public final long read(@NotNull g2 sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            boolean z = true;
            if (!(j >= 0)) {
                throw new IllegalArgumentException(spi.d.d("byteCount < 0: ", j).toString());
            }
            if (!(!this.b)) {
                throw new IllegalStateException("closed".toString());
            }
            if (!this.e) {
                return -1L;
            }
            long j2 = this.d;
            if (j2 == 0 || j2 == -1) {
                if (j2 != -1) {
                    this.g.f.r();
                }
                try {
                    this.d = this.g.f.z();
                    String strR = this.g.f.r();
                    if (strR == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    String string = StringsKt.trim((CharSequence) strR).toString();
                    if (this.d >= 0) {
                        if (string.length() <= 0) {
                            z = false;
                        }
                        if (!z || StringsKt.x(string, ";")) {
                            if (this.d == 0) {
                                this.e = false;
                                pe peVar = this.g;
                                peVar.c = peVar.b.a();
                                dk dkVar = this.g.d;
                                Intrinsics.checkNotNull(dkVar);
                                q4 q4Var = dkVar.j;
                                hf hfVar = this.f;
                                ke keVar = this.g.c;
                                Intrinsics.checkNotNull(keVar);
                                ff.d(q4Var, hfVar, keVar);
                                e();
                            }
                            if (!this.e) {
                                return -1L;
                            }
                        }
                    }
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.d + string + Typography.quote);
                } catch (NumberFormatException e) {
                    throw new ProtocolException(e.getMessage());
                }
            }
            long j3 = super.read(sink, Math.min(j, this.d));
            if (j3 != -1) {
                this.d -= j3;
                return j3;
            }
            this.g.e.l();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            e();
            throw protocolException;
        }
    }

    public static final class d {
        private d() {
        }

        public /* synthetic */ d(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final class e extends a {
        public long d;

        public e(long j) {
            super();
            this.d = j;
            if (j == 0) {
                e();
            }
        }

        @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.b) {
                return;
            }
            if (this.d != 0) {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                if (!uu.g(this)) {
                    pe.this.e.l();
                    e();
                }
            }
            this.b = true;
        }

        @Override // spi.pe.a, spi.vr
        public final long read(@NotNull g2 sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!(j >= 0)) {
                throw new IllegalArgumentException(spi.d.d("byteCount < 0: ", j).toString());
            }
            if (!(!this.b)) {
                throw new IllegalStateException("closed".toString());
            }
            long j2 = this.d;
            if (j2 == 0) {
                return -1L;
            }
            long j3 = super.read(sink, Math.min(j2, j));
            if (j3 == -1) {
                pe.this.e.l();
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                e();
                throw protocolException;
            }
            long j4 = this.d - j3;
            this.d = j4;
            if (j4 == 0) {
                e();
            }
            return j3;
        }
    }

    public final class f implements rp {
        public final jc a;
        public boolean b;

        public f() {
            this.a = new jc(pe.this.g.timeout());
        }

        @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.b) {
                return;
            }
            this.b = true;
            pe.i(pe.this, this.a);
            pe.this.a = 3;
        }

        @Override // spi.rp, java.io.Flushable
        public final void flush() throws IOException {
            if (this.b) {
                return;
            }
            pe.this.g.flush();
        }

        @Override // spi.rp
        public final void p(@NotNull g2 source, long j) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!(!this.b)) {
                throw new IllegalStateException("closed".toString());
            }
            uu.b(source.b, 0L, j);
            pe.this.g.p(source, j);
        }

        @Override // spi.rp
        @NotNull
        public final dt timeout() {
            return this.a;
        }
    }

    public final class g extends a {
        public boolean d;

        public g(pe peVar) {
            super();
        }

        @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.b) {
                return;
            }
            if (!this.d) {
                e();
            }
            this.b = true;
        }

        @Override // spi.pe.a, spi.vr
        public final long read(@NotNull g2 sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!(j >= 0)) {
                throw new IllegalArgumentException(spi.d.d("byteCount < 0: ", j).toString());
            }
            if (!(!this.b)) {
                throw new IllegalStateException("closed".toString());
            }
            if (this.d) {
                return -1L;
            }
            long j2 = super.read(sink, j);
            if (j2 != -1) {
                return j2;
            }
            this.d = true;
            e();
            return -1L;
        }
    }

    static {
        new d(null);
    }

    public pe(@Nullable dk dkVar, @NotNull wl connection, @NotNull l2 source, @NotNull k2 sink) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.d = dkVar;
        this.e = connection;
        this.f = source;
        this.g = sink;
        this.b = new le(source);
    }

    public static final void i(pe peVar, jc jcVar) {
        Objects.requireNonNull(peVar);
        dt dtVar = jcVar.e;
        dt.a delegate = dt.d;
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        jcVar.e = delegate;
        dtVar.a();
        dtVar.b();
    }

    @Override // spi.z8
    public final void a() throws IOException {
        this.g.flush();
    }

    @Override // spi.z8
    @NotNull
    public final vr b(@NotNull ln response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (!ff.a(response)) {
            return j(0L);
        }
        if (StringsKt.h("chunked", ln.B(response, "Transfer-Encoding"))) {
            hf hfVar = response.b.b;
            if (this.a == 4) {
                this.a = 5;
                return new c(this, hfVar);
            }
            StringBuilder sbK = spi.e.k("state: ");
            sbK.append(this.a);
            throw new IllegalStateException(sbK.toString().toString());
        }
        long j = uu.j(response);
        if (j != -1) {
            return j(j);
        }
        if (this.a == 4) {
            this.a = 5;
            this.e.l();
            return new g(this);
        }
        StringBuilder sbK2 = spi.e.k("state: ");
        sbK2.append(this.a);
        throw new IllegalStateException(sbK2.toString().toString());
    }

    @Override // spi.z8
    @Nullable
    public final ln.a c(boolean z) {
        int i = this.a;
        boolean z2 = true;
        if (i != 1 && i != 3) {
            z2 = false;
        }
        if (!z2) {
            StringBuilder sbK = spi.e.k("state: ");
            sbK.append(this.a);
            throw new IllegalStateException(sbK.toString().toString());
        }
        try {
            fs.a aVar = fs.d;
            le leVar = this.b;
            String strK = leVar.b.k(leVar.a);
            leVar.a -= (long) strK.length();
            fs fsVarA = aVar.a(strK);
            ln.a aVar2 = new ln.a();
            aVar2.f(fsVarA.a);
            aVar2.c = fsVarA.b;
            aVar2.e(fsVarA.c);
            aVar2.d(this.b.a());
            if (z && fsVarA.b == 100) {
                return null;
            }
            if (fsVarA.b == 100) {
                this.a = 3;
                return aVar2;
            }
            this.a = 4;
            return aVar2;
        } catch (EOFException e2) {
            throw new IOException(spi.d.f("unexpected end of stream on ", this.e.q.a.a.h()), e2);
        }
    }

    @Override // spi.z8
    public final void cancel() {
        Socket socket = this.e.b;
        if (socket != null) {
            uu.d(socket);
        }
    }

    @Override // spi.z8
    @NotNull
    public final wl d() {
        return this.e;
    }

    @Override // spi.z8
    public final void e() throws IOException {
        this.g.flush();
    }

    @Override // spi.z8
    @NotNull
    public final rp f(@NotNull im request, long j) throws ProtocolException {
        Intrinsics.checkNotNullParameter(request, "request");
        mm mmVar = request.e;
        if (mmVar != null && mmVar.isDuplex()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if (StringsKt.h("chunked", request.b("Transfer-Encoding"))) {
            if (this.a == 1) {
                this.a = 2;
                return new b();
            }
            StringBuilder sbK = spi.e.k("state: ");
            sbK.append(this.a);
            throw new IllegalStateException(sbK.toString().toString());
        }
        if (j == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.a == 1) {
            this.a = 2;
            return new f();
        }
        StringBuilder sbK2 = spi.e.k("state: ");
        sbK2.append(this.a);
        throw new IllegalStateException(sbK2.toString().toString());
    }

    @Override // spi.z8
    public final long g(@NotNull ln response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (!ff.a(response)) {
            return 0L;
        }
        if (StringsKt.h("chunked", ln.B(response, "Transfer-Encoding"))) {
            return -1L;
        }
        return uu.j(response);
    }

    @Override // spi.z8
    public final void h(@NotNull im request) {
        Intrinsics.checkNotNullParameter(request, "request");
        om omVar = om.a;
        Proxy.Type proxyType = this.e.q.b.type();
        Intrinsics.checkNotNullExpressionValue(proxyType, "connection.route().proxy.type()");
        Objects.requireNonNull(omVar);
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(proxyType, "proxyType");
        StringBuilder sb = new StringBuilder();
        sb.append(request.c);
        sb.append(' ');
        hf hfVar = request.b;
        if (!hfVar.a && proxyType == Proxy.Type.HTTP) {
            sb.append(hfVar);
        } else {
            sb.append(omVar.a(hfVar));
        }
        sb.append(" HTTP/1.1");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        k(request.d, string);
    }

    public final vr j(long j) {
        if (this.a == 4) {
            this.a = 5;
            return new e(j);
        }
        StringBuilder sbK = spi.e.k("state: ");
        sbK.append(this.a);
        throw new IllegalStateException(sbK.toString().toString());
    }

    public final void k(@NotNull ke headers, @NotNull String requestLine) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(requestLine, "requestLine");
        if (!(this.a == 0)) {
            StringBuilder sbK = spi.e.k("state: ");
            sbK.append(this.a);
            throw new IllegalStateException(sbK.toString().toString());
        }
        this.g.l(requestLine).l("\r\n");
        int length = headers.a.length / 2;
        for (int i = 0; i < length; i++) {
            this.g.l(headers.b(i)).l(": ").l(headers.d(i)).l("\r\n");
        }
        this.g.l("\r\n");
        this.a = 1;
    }
}
