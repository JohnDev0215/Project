package retrofit2;

import java.io.IOException;
import java.util.Objects;
import spi.c3;
import spi.dt;
import spi.e3;
import spi.g2;
import spi.ic;
import spi.im;
import spi.l2;
import spi.ln;
import spi.mi;
import spi.mn;

/* JADX INFO: loaded from: classes2.dex */
final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private final c3.a callFactory;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private c3 rawCall;
    private final RequestFactory requestFactory;
    private final Converter<mn, T> responseConverter;

    public static final class ExceptionCatchingResponseBody extends mn {
        private final mn delegate;
        private final l2 delegateSource;
        public IOException thrownException;

        public ExceptionCatchingResponseBody(mn mnVar) {
            this.delegate = mnVar;
            this.delegateSource = spi.b.c(new ic(mnVar.source()) { // from class: retrofit2.OkHttpCall.ExceptionCatchingResponseBody.1
                @Override // spi.ic, spi.vr
                public long read(g2 g2Var, long j) throws IOException {
                    try {
                        return super.read(g2Var, j);
                    } catch (IOException e) {
                        ExceptionCatchingResponseBody.this.thrownException = e;
                        throw e;
                    }
                }
            });
        }

        @Override // spi.mn, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // spi.mn
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // spi.mn
        public mi contentType() {
            return this.delegate.contentType();
        }

        @Override // spi.mn
        public l2 source() {
            return this.delegateSource;
        }

        public void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    public static final class NoContentResponseBody extends mn {
        private final long contentLength;
        private final mi contentType;

        public NoContentResponseBody(mi miVar, long j) {
            this.contentType = miVar;
            this.contentLength = j;
        }

        @Override // spi.mn
        public long contentLength() {
            return this.contentLength;
        }

        @Override // spi.mn
        public mi contentType() {
            return this.contentType;
        }

        @Override // spi.mn
        public l2 source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public OkHttpCall(RequestFactory requestFactory, Object[] objArr, c3.a aVar, Converter<mn, T> converter) {
        this.requestFactory = requestFactory;
        this.args = objArr;
        this.callFactory = aVar;
        this.responseConverter = converter;
    }

    private c3 createRawCall() throws IOException {
        c3 c3VarA = this.callFactory.a(this.requestFactory.create(this.args));
        Objects.requireNonNull(c3VarA, "Call.Factory returned null.");
        return c3VarA;
    }

    private c3 getRawCall() throws IOException {
        c3 c3Var = this.rawCall;
        if (c3Var != null) {
            return c3Var;
        }
        Throwable th = this.creationFailure;
        if (th != null) {
            if (th instanceof IOException) {
                throw ((IOException) th);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw ((Error) th);
        }
        try {
            c3 c3VarCreateRawCall = createRawCall();
            this.rawCall = c3VarCreateRawCall;
            return c3VarCreateRawCall;
        } catch (IOException | Error | RuntimeException e) {
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        }
    }

    @Override // retrofit2.Call
    public void cancel() {
        c3 c3Var;
        this.canceled = true;
        synchronized (this) {
            c3Var = this.rawCall;
        }
        if (c3Var != null) {
            c3Var.cancel();
        }
    }

    @Override // retrofit2.Call
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.requestFactory, this.args, this.callFactory, this.responseConverter);
    }

    @Override // retrofit2.Call
    public void enqueue(final Callback<T> callback) {
        c3 c3Var;
        Throwable th;
        Objects.requireNonNull(callback, "callback == null");
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            c3Var = this.rawCall;
            th = this.creationFailure;
            if (c3Var == null && th == null) {
                try {
                    c3 c3VarCreateRawCall = createRawCall();
                    this.rawCall = c3VarCreateRawCall;
                    c3Var = c3VarCreateRawCall;
                } catch (Throwable th2) {
                    th = th2;
                    Utils.throwIfFatal(th);
                    this.creationFailure = th;
                }
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            c3Var.cancel();
        }
        c3Var.e(new e3() { // from class: retrofit2.OkHttpCall.1
            private void callFailure(Throwable th3) {
                try {
                    callback.onFailure(OkHttpCall.this, th3);
                } catch (Throwable th4) {
                    Utils.throwIfFatal(th4);
                    th4.printStackTrace();
                }
            }

            @Override // spi.e3
            public void onFailure(c3 c3Var2, IOException iOException) {
                callFailure(iOException);
            }

            @Override // spi.e3
            public void onResponse(c3 c3Var2, ln lnVar) {
                try {
                    try {
                        callback.onResponse(OkHttpCall.this, OkHttpCall.this.parseResponse(lnVar));
                    } catch (Throwable th3) {
                        Utils.throwIfFatal(th3);
                        th3.printStackTrace();
                    }
                } catch (Throwable th4) {
                    Utils.throwIfFatal(th4);
                    callFailure(th4);
                }
            }
        });
    }

    @Override // retrofit2.Call
    public Response<T> execute() throws IOException {
        c3 rawCall;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            rawCall = getRawCall();
        }
        if (this.canceled) {
            rawCall.cancel();
        }
        return parseResponse(rawCall.execute());
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            c3 c3Var = this.rawCall;
            if (c3Var == null || !c3Var.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public Response<T> parseResponse(ln lnVar) throws IOException {
        mn mnVar = lnVar.h;
        ln.a aVar = new ln.a(lnVar);
        aVar.g = new NoContentResponseBody(mnVar.contentType(), mnVar.contentLength());
        ln lnVarA = aVar.a();
        int i = lnVarA.e;
        if (i < 200 || i >= 300) {
            try {
                return Response.error(Utils.buffer(mnVar), lnVarA);
            } finally {
                mnVar.close();
            }
        }
        if (i == 204 || i == 205) {
            mnVar.close();
            return Response.success((Object) null, lnVarA);
        }
        ExceptionCatchingResponseBody exceptionCatchingResponseBody = new ExceptionCatchingResponseBody(mnVar);
        try {
            return Response.success(this.responseConverter.convert(exceptionCatchingResponseBody), lnVarA);
        } catch (RuntimeException e) {
            exceptionCatchingResponseBody.throwIfCaught();
            throw e;
        }
    }

    @Override // retrofit2.Call
    public synchronized im request() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create request.", e);
        }
        return getRawCall().request();
    }

    @Override // retrofit2.Call
    public synchronized dt timeout() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create call.", e);
        }
        return getRawCall().timeout();
    }
}
