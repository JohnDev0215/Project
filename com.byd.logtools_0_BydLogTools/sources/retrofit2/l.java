package retrofit2;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: OkHttpCall.java */
/* JADX INFO: loaded from: classes.dex */
final class l<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final q f5226b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object[] f5227c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Call.Factory f5228d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h<ResponseBody, T> f5229e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile boolean f5230f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @GuardedBy("this")
    @Nullable
    private Call f5231g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @GuardedBy("this")
    @Nullable
    private Throwable f5232h;

    @GuardedBy("this")
    private boolean i;

    /* JADX INFO: compiled from: OkHttpCall.java */
    class a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f5233a;

        a(f fVar) {
            this.f5233a = fVar;
        }

        private void a(Throwable th) {
            try {
                this.f5233a.a(l.this, th);
            } catch (Throwable th2) {
                w.s(th2);
                th2.printStackTrace();
            }
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            a(iOException);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            try {
                try {
                    this.f5233a.b(l.this, l.this.d(response));
                } catch (Throwable th) {
                    w.s(th);
                    th.printStackTrace();
                }
            } catch (Throwable th2) {
                w.s(th2);
                a(th2);
            }
        }
    }

    /* JADX INFO: compiled from: OkHttpCall.java */
    static final class b extends ResponseBody {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ResponseBody f5235b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final BufferedSource f5236c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Nullable
        IOException f5237d;

        /* JADX INFO: compiled from: OkHttpCall.java */
        class a extends ForwardingSource {
            a(Source source) {
                super(source);
            }

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                try {
                    return super.read(buffer, j);
                } catch (IOException e2) {
                    b.this.f5237d = e2;
                    throw e2;
                }
            }
        }

        b(ResponseBody responseBody) {
            this.f5235b = responseBody;
            this.f5236c = Okio.buffer(new a(responseBody.source()));
        }

        void a() throws IOException {
            IOException iOException = this.f5237d;
            if (iOException != null) {
                throw iOException;
            }
        }

        @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f5235b.close();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.f5235b.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.f5235b.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.f5236c;
        }
    }

    /* JADX INFO: compiled from: OkHttpCall.java */
    static final class c extends ResponseBody {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        private final MediaType f5239b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f5240c;

        c(@Nullable MediaType mediaType, long j) {
            this.f5239b = mediaType;
            this.f5240c = j;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.f5240c;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.f5239b;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    l(q qVar, Object[] objArr, Call.Factory factory, h<ResponseBody, T> hVar) {
        this.f5226b = qVar;
        this.f5227c = objArr;
        this.f5228d = factory;
        this.f5229e = hVar;
    }

    private Call b() throws IOException {
        Call callNewCall = this.f5228d.newCall(this.f5226b.a(this.f5227c));
        Objects.requireNonNull(callNewCall, "Call.Factory returned null.");
        return callNewCall;
    }

    @GuardedBy("this")
    private Call c() throws IOException {
        Call call = this.f5231g;
        if (call != null) {
            return call;
        }
        Throwable th = this.f5232h;
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
            Call callB = b();
            this.f5231g = callB;
            return callB;
        } catch (IOException | Error | RuntimeException e2) {
            w.s(e2);
            this.f5232h = e2;
            throw e2;
        }
    }

    @Override // retrofit2.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public l<T> clone() {
        return new l<>(this.f5226b, this.f5227c, this.f5228d, this.f5229e);
    }

    @Override // retrofit2.d
    public void cancel() {
        Call call;
        this.f5230f = true;
        synchronized (this) {
            call = this.f5231g;
        }
        if (call != null) {
            call.cancel();
        }
    }

    r<T> d(Response response) throws IOException {
        ResponseBody responseBodyBody = response.body();
        Response responseBuild = response.newBuilder().body(new c(responseBodyBody.contentType(), responseBodyBody.contentLength())).build();
        int iCode = responseBuild.code();
        if (iCode < 200 || iCode >= 300) {
            try {
                return r.c(w.a(responseBodyBody), responseBuild);
            } finally {
                responseBodyBody.close();
            }
        }
        if (iCode == 204 || iCode == 205) {
            responseBodyBody.close();
            return r.f(null, responseBuild);
        }
        b bVar = new b(responseBodyBody);
        try {
            return r.f(this.f5229e.a(bVar), responseBuild);
        } catch (RuntimeException e2) {
            bVar.a();
            throw e2;
        }
    }

    @Override // retrofit2.d
    public void e(f<T> fVar) {
        Call call;
        Throwable th;
        Objects.requireNonNull(fVar, "callback == null");
        synchronized (this) {
            if (this.i) {
                throw new IllegalStateException("Already executed.");
            }
            this.i = true;
            call = this.f5231g;
            th = this.f5232h;
            if (call == null && th == null) {
                try {
                    Call callB = b();
                    this.f5231g = callB;
                    call = callB;
                } catch (Throwable th2) {
                    th = th2;
                    w.s(th);
                    this.f5232h = th;
                }
            }
        }
        if (th != null) {
            fVar.a(this, th);
            return;
        }
        if (this.f5230f) {
            call.cancel();
        }
        call.enqueue(new a(fVar));
    }

    @Override // retrofit2.d
    public boolean isCanceled() {
        boolean z = true;
        if (this.f5230f) {
            return true;
        }
        synchronized (this) {
            Call call = this.f5231g;
            if (call == null || !call.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    @Override // retrofit2.d
    public synchronized Request request() {
        try {
        } catch (IOException e2) {
            throw new RuntimeException("Unable to create request.", e2);
        }
        return c().request();
    }
}
