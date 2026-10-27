package okio;

import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class Pipe {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final long f5821a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Buffer f5822b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f5823c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f5824d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    private Sink f5825e;

    final class PipeSink implements Sink {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final PushableTimeout f5826b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Pipe f5827c;

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Sink sink;
            synchronized (this.f5827c.f5822b) {
                Pipe pipe = this.f5827c;
                if (pipe.f5823c) {
                    return;
                }
                if (pipe.f5825e != null) {
                    sink = this.f5827c.f5825e;
                } else {
                    Pipe pipe2 = this.f5827c;
                    if (pipe2.f5824d && pipe2.f5822b.g0() > 0) {
                        throw new IOException("source is closed");
                    }
                    Pipe pipe3 = this.f5827c;
                    pipe3.f5823c = true;
                    pipe3.f5822b.notifyAll();
                    sink = null;
                }
                if (sink != null) {
                    this.f5826b.l(sink.e());
                    try {
                        sink.close();
                    } finally {
                        this.f5826b.k();
                    }
                }
            }
        }

        @Override // okio.Sink
        public Timeout e() {
            return this.f5826b;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            Sink sink;
            synchronized (this.f5827c.f5822b) {
                Pipe pipe = this.f5827c;
                if (pipe.f5823c) {
                    throw new IllegalStateException("closed");
                }
                if (pipe.f5825e != null) {
                    sink = this.f5827c.f5825e;
                } else {
                    Pipe pipe2 = this.f5827c;
                    if (pipe2.f5824d && pipe2.f5822b.g0() > 0) {
                        throw new IOException("source is closed");
                    }
                    sink = null;
                }
            }
            if (sink != null) {
                this.f5826b.l(sink.e());
                try {
                    sink.flush();
                } finally {
                    this.f5826b.k();
                }
            }
        }

        @Override // okio.Sink
        public void h(Buffer buffer, long j) {
            Sink sink;
            synchronized (this.f5827c.f5822b) {
                if (!this.f5827c.f5823c) {
                    while (true) {
                        if (j <= 0) {
                            sink = null;
                            break;
                        }
                        if (this.f5827c.f5825e != null) {
                            sink = this.f5827c.f5825e;
                            break;
                        }
                        Pipe pipe = this.f5827c;
                        if (pipe.f5824d) {
                            throw new IOException("source is closed");
                        }
                        long jG0 = pipe.f5821a - pipe.f5822b.g0();
                        if (jG0 == 0) {
                            this.f5826b.j(this.f5827c.f5822b);
                        } else {
                            long jMin = Math.min(jG0, j);
                            this.f5827c.f5822b.h(buffer, jMin);
                            j -= jMin;
                            this.f5827c.f5822b.notifyAll();
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
            if (sink != null) {
                this.f5826b.l(sink.e());
                try {
                    sink.h(buffer, j);
                } finally {
                    this.f5826b.k();
                }
            }
        }
    }

    final class PipeSource implements Source {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Timeout f5828b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Pipe f5829c;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            synchronized (this.f5829c.f5822b) {
                Pipe pipe = this.f5829c;
                pipe.f5824d = true;
                pipe.f5822b.notifyAll();
            }
        }

        @Override // okio.Source
        public Timeout e() {
            return this.f5828b;
        }

        @Override // okio.Source
        public long p(Buffer buffer, long j) {
            synchronized (this.f5829c.f5822b) {
                if (this.f5829c.f5824d) {
                    throw new IllegalStateException("closed");
                }
                while (this.f5829c.f5822b.g0() == 0) {
                    Pipe pipe = this.f5829c;
                    if (pipe.f5823c) {
                        return -1L;
                    }
                    this.f5828b.j(pipe.f5822b);
                }
                long jP = this.f5829c.f5822b.p(buffer, j);
                this.f5829c.f5822b.notifyAll();
                return jP;
            }
        }
    }
}
