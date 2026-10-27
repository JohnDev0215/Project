package okio;

import com.amazon.music.find.utils.PageUriUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pipe.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b%\u0010&R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u00020\f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\"\u0010\u0016\u001a\u00020\f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\u00020\u00198G¢\u0006\f\n\u0004\b \u0010\u001b\u001a\u0004\b \u0010\u001dR\u0017\u0010\"\u001a\u00020!8G¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b\"\u0010$¨\u0006'"}, d2 = {"Lokio/Pipe;", "", "", "maxBufferSize", "J", "getMaxBufferSize$okio", "()J", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "", "canceled", "Z", "getCanceled$okio", "()Z", "setCanceled$okio", "(Z)V", "sinkClosed", "getSinkClosed$okio", "setSinkClosed$okio", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "Lokio/Sink;", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "()Lokio/Sink;", "setFoldedSink$okio", "(Lokio/Sink;)V", "sink", "Lokio/Source;", PageUriUtils.SOURCE_QUERY_PARAM, "Lokio/Source;", "()Lokio/Source;", "<init>", "(J)V", "okio"}, k = 1, mv = {1, 5, 1})
public final class Pipe {
    private final Buffer buffer = new Buffer();
    private boolean canceled;
    private Sink foldedSink;
    private final long maxBufferSize;
    private final Sink sink;
    private boolean sinkClosed;
    private final Source source;
    private boolean sourceClosed;

    public Pipe(long j) {
        this.maxBufferSize = j;
        if (!(j >= 1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("maxBufferSize < 1: ", Long.valueOf(getMaxBufferSize())).toString());
        }
        this.sink = new Sink() { // from class: okio.Pipe.sink.1
            private final Timeout timeout = new Timeout();

            /* JADX WARN: Code restructure failed: missing block: B:25:0x0074, code lost:
            
                r1 = kotlin.Unit.INSTANCE;
             */
            @Override // okio.Sink
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void write(okio.Buffer r13, long r14) {
                /*
                    Method dump skipped, instruction units count: 291
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.AnonymousClass1.write(okio.Buffer, long):void");
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                Sink foldedSink;
                Buffer buffer = Pipe.this.getBuffer();
                Pipe pipe = Pipe.this;
                synchronized (buffer) {
                    if (!(!pipe.getSinkClosed())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    if (pipe.getCanceled()) {
                        throw new IOException("canceled");
                    }
                    foldedSink = pipe.getFoldedSink();
                    if (foldedSink == null) {
                        if (pipe.getSourceClosed() && pipe.getBuffer().getSize() > 0) {
                            throw new IOException("source is closed");
                        }
                        foldedSink = null;
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (foldedSink == null) {
                    return;
                }
                Pipe pipe2 = Pipe.this;
                Timeout timeout = foldedSink.getTimeout();
                Timeout timeout2 = pipe2.getSink().getTimeout();
                long timeoutNanos = timeout.getTimeoutNanos();
                long jMinTimeout = Timeout.INSTANCE.minTimeout(timeout2.getTimeoutNanos(), timeout.getTimeoutNanos());
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                timeout.timeout(jMinTimeout, timeUnit);
                if (timeout.getHasDeadline()) {
                    long jDeadlineNanoTime = timeout.deadlineNanoTime();
                    if (timeout2.getHasDeadline()) {
                        timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                    }
                    try {
                        foldedSink.flush();
                        timeout.timeout(timeoutNanos, timeUnit);
                        if (timeout2.getHasDeadline()) {
                            timeout.deadlineNanoTime(jDeadlineNanoTime);
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.getHasDeadline()) {
                            timeout.deadlineNanoTime(jDeadlineNanoTime);
                        }
                        throw th;
                    }
                }
                if (timeout2.getHasDeadline()) {
                    timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                }
                try {
                    foldedSink.flush();
                    timeout.timeout(timeoutNanos, timeUnit);
                    if (timeout2.getHasDeadline()) {
                        timeout.clearDeadline();
                    }
                } catch (Throwable th2) {
                    timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout2.getHasDeadline()) {
                        timeout.clearDeadline();
                    }
                    throw th2;
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Buffer buffer = Pipe.this.getBuffer();
                Pipe pipe = Pipe.this;
                synchronized (buffer) {
                    if (pipe.getSinkClosed()) {
                        return;
                    }
                    Sink foldedSink = pipe.getFoldedSink();
                    if (foldedSink == null) {
                        if (pipe.getSourceClosed() && pipe.getBuffer().getSize() > 0) {
                            throw new IOException("source is closed");
                        }
                        pipe.setSinkClosed$okio(true);
                        pipe.getBuffer().notifyAll();
                        foldedSink = null;
                    }
                    Unit unit = Unit.INSTANCE;
                    if (foldedSink == null) {
                        return;
                    }
                    Pipe pipe2 = Pipe.this;
                    Timeout timeout = foldedSink.getTimeout();
                    Timeout timeout2 = pipe2.getSink().getTimeout();
                    long timeoutNanos = timeout.getTimeoutNanos();
                    long jMinTimeout = Timeout.INSTANCE.minTimeout(timeout2.getTimeoutNanos(), timeout.getTimeoutNanos());
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    timeout.timeout(jMinTimeout, timeUnit);
                    if (timeout.getHasDeadline()) {
                        long jDeadlineNanoTime = timeout.deadlineNanoTime();
                        if (timeout2.getHasDeadline()) {
                            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                        }
                        try {
                            foldedSink.close();
                            timeout.timeout(timeoutNanos, timeUnit);
                            if (timeout2.getHasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.getHasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                            throw th;
                        }
                    }
                    if (timeout2.getHasDeadline()) {
                        timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                    }
                    try {
                        foldedSink.close();
                        timeout.timeout(timeoutNanos, timeUnit);
                        if (timeout2.getHasDeadline()) {
                            timeout.clearDeadline();
                        }
                    } catch (Throwable th2) {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.getHasDeadline()) {
                            timeout.clearDeadline();
                        }
                        throw th2;
                    }
                }
            }

            @Override // okio.Sink
            /* JADX INFO: renamed from: timeout, reason: from getter */
            public Timeout getTimeout() {
                return this.timeout;
            }
        };
        this.source = new Source() { // from class: okio.Pipe.source.1
            private final Timeout timeout = new Timeout();

            @Override // okio.Source
            public long read(Buffer sink, long byteCount) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                Buffer buffer = Pipe.this.getBuffer();
                Pipe pipe = Pipe.this;
                synchronized (buffer) {
                    if (!(!pipe.getSourceClosed())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    if (pipe.getCanceled()) {
                        throw new IOException("canceled");
                    }
                    while (pipe.getBuffer().getSize() == 0) {
                        if (pipe.getSinkClosed()) {
                            return -1L;
                        }
                        this.timeout.waitUntilNotified(pipe.getBuffer());
                        if (pipe.getCanceled()) {
                            throw new IOException("canceled");
                        }
                    }
                    long j2 = pipe.getBuffer().read(sink, byteCount);
                    pipe.getBuffer().notifyAll();
                    return j2;
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Buffer buffer = Pipe.this.getBuffer();
                Pipe pipe = Pipe.this;
                synchronized (buffer) {
                    pipe.setSourceClosed$okio(true);
                    pipe.getBuffer().notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Source
            /* JADX INFO: renamed from: timeout, reason: from getter */
            public Timeout getTimeout() {
                return this.timeout;
            }
        };
    }

    /* JADX INFO: renamed from: getMaxBufferSize$okio, reason: from getter */
    public final long getMaxBufferSize() {
        return this.maxBufferSize;
    }

    /* JADX INFO: renamed from: getBuffer$okio, reason: from getter */
    public final Buffer getBuffer() {
        return this.buffer;
    }

    /* JADX INFO: renamed from: getCanceled$okio, reason: from getter */
    public final boolean getCanceled() {
        return this.canceled;
    }

    /* JADX INFO: renamed from: getSinkClosed$okio, reason: from getter */
    public final boolean getSinkClosed() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.sinkClosed = z;
    }

    /* JADX INFO: renamed from: getSourceClosed$okio, reason: from getter */
    public final boolean getSourceClosed() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.sourceClosed = z;
    }

    /* JADX INFO: renamed from: getFoldedSink$okio, reason: from getter */
    public final Sink getFoldedSink() {
        return this.foldedSink;
    }

    @JvmName(name = "sink")
    /* JADX INFO: renamed from: sink, reason: from getter */
    public final Sink getSink() {
        return this.sink;
    }

    @JvmName(name = PageUriUtils.SOURCE_QUERY_PARAM)
    /* JADX INFO: renamed from: source, reason: from getter */
    public final Source getSource() {
        return this.source;
    }
}
