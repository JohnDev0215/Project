package okhttp3.internal.http2;

import com.android.internal.location.GpsNetInitiatedHandler;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: classes5.dex */
public final class Http2Stream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    final Http2Connection connection;

    @Nullable
    ErrorCode errorCode;

    @Nullable
    IOException errorException;
    private boolean hasResponseHeaders;
    final int id;
    final FramingSink sink;
    private final FramingSource source;
    long unacknowledgedBytesRead = 0;
    private final Deque<Headers> headersQueue = new ArrayDeque();
    final StreamTimeout readTimeout = new StreamTimeout();
    final StreamTimeout writeTimeout = new StreamTimeout();

    Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, @Nullable Headers headers) {
        if (http2Connection == null) {
            throw new NullPointerException("connection == null");
        }
        this.id = i;
        this.connection = http2Connection;
        this.bytesLeftInWriteWindow = http2Connection.peerSettings.getInitialWindowSize();
        this.source = new FramingSource(http2Connection.okHttpSettings.getInitialWindowSize());
        this.sink = new FramingSink();
        this.source.finished = z2;
        this.sink.finished = z;
        if (headers != null) {
            this.headersQueue.add(headers);
        }
        if (isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public int getId() {
        return this.id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed)) {
            if (this.hasResponseHeaders) {
                return false;
            }
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.id & 1) == 1);
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public synchronized Headers takeHeaders() throws IOException {
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty() && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (!this.headersQueue.isEmpty()) {
        } else {
            if (this.errorException != null) {
                throw this.errorException;
            }
            throw new StreamResetException(this.errorCode);
        }
        return this.headersQueue.removeFirst();
    }

    public synchronized Headers trailers() throws IOException {
        if (this.errorCode != null) {
            if (this.errorException != null) {
                throw this.errorException;
            }
            throw new StreamResetException(this.errorCode);
        }
        if (!this.source.finished || !this.source.receiveBuffer.exhausted() || !this.source.readBuffer.exhausted()) {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        return this.source.trailers != null ? this.source.trailers : Util.EMPTY_HEADERS;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void writeHeaders(List<Header> list, boolean z, boolean z2) throws IOException {
        if (list == null) {
            throw new NullPointerException("headers == null");
        }
        synchronized (this) {
            this.hasResponseHeaders = true;
            if (z) {
                this.sink.finished = true;
            }
        }
        if (!z2) {
            synchronized (this.connection) {
                z2 = this.connection.bytesLeftInWriteWindow == 0;
            }
        }
        this.connection.writeHeaders(this.id, z, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public void enqueueTrailers(Headers headers) {
        synchronized (this) {
            if (this.sink.finished) {
                throw new IllegalStateException("already finished");
            }
            if (headers.size() == 0) {
                throw new IllegalArgumentException("trailers.size() == 0");
            }
            this.sink.trailers = headers;
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode, @Nullable IOException iOException) throws IOException {
        if (closeInternal(errorCode, iOException)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode, null)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    private boolean closeInternal(ErrorCode errorCode, @Nullable IOException iOException) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            this.errorException = iOException;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, i);
    }

    void receiveHeaders(Headers headers, boolean z) {
        boolean zIsOpen;
        synchronized (this) {
            if (!this.hasResponseHeaders || !z) {
                this.hasResponseHeaders = true;
                this.headersQueue.add(headers);
            } else {
                this.source.trailers = headers;
            }
            if (z) {
                this.source.finished = true;
            }
            zIsOpen = isOpen();
            notifyAll();
        }
        if (zIsOpen) {
            return;
        }
        this.connection.removeStream(this.id);
    }

    synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    private final class FramingSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean closed;
        boolean finished;
        private final long maxByteCount;
        private Headers trailers;
        private final Buffer receiveBuffer = new Buffer();
        private final Buffer readBuffer = new Buffer();

        FramingSource(long j) {
            this.maxByteCount = j;
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long read(okio.Buffer r12, long r13) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 210
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        private void updateConnectionFlowControl(long j) {
            Http2Stream.this.connection.updateConnectionFlowControl(j);
        }

        void receive(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            long size;
            while (j > 0) {
                synchronized (Http2Stream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.size() + j > this.maxByteCount;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    bufferedSource.skip(j);
                    return;
                }
                long j2 = bufferedSource.read(this.receiveBuffer, j);
                if (j2 == -1) {
                    throw new EOFException();
                }
                j -= j2;
                synchronized (Http2Stream.this) {
                    if (this.closed) {
                        size = this.receiveBuffer.size();
                        this.receiveBuffer.clear();
                    } else {
                        if (this.readBuffer.size() != 0) {
                            z2 = false;
                        }
                        this.readBuffer.writeAll(this.receiveBuffer);
                        if (z2) {
                            Http2Stream.this.notifyAll();
                        }
                        size = 0;
                    }
                }
                if (size > 0) {
                    updateConnectionFlowControl(size);
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long size;
            synchronized (Http2Stream.this) {
                this.closed = true;
                size = this.readBuffer.size();
                this.readBuffer.clear();
                Http2Stream.this.notifyAll();
            }
            if (size > 0) {
                updateConnectionFlowControl(size);
            }
            Http2Stream.this.cancelStreamIfNecessary();
        }
    }

    void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean zIsOpen;
        synchronized (this) {
            z = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            zIsOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL, null);
        } else {
            if (zIsOpen) {
                return;
            }
            this.connection.removeStream(this.id);
        }
    }

    final class FramingSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        boolean closed;
        boolean finished;
        private final Buffer sendBuffer = new Buffer();
        private Headers trailers;

        FramingSink() {
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            this.sendBuffer.write(buffer, j);
            while (this.sendBuffer.size() >= 16384) {
                emitFrame(false);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void emitFrame(boolean r12) throws java.io.IOException {
            /*
                r11 = this;
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r0)
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream$StreamTimeout r1 = r1.writeTimeout     // Catch: java.lang.Throwable -> L89
                r1.enter()     // Catch: java.lang.Throwable -> L89
            La:
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L80
                long r1 = r1.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L80
                r3 = 0
                int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r1 > 0) goto L28
                boolean r1 = r11.finished     // Catch: java.lang.Throwable -> L80
                if (r1 != 0) goto L28
                boolean r1 = r11.closed     // Catch: java.lang.Throwable -> L80
                if (r1 != 0) goto L28
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L80
                okhttp3.internal.http2.ErrorCode r1 = r1.errorCode     // Catch: java.lang.Throwable -> L80
                if (r1 != 0) goto L28
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L80
                r1.waitForIo()     // Catch: java.lang.Throwable -> L80
                goto La
            L28:
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream$StreamTimeout r1 = r1.writeTimeout     // Catch: java.lang.Throwable -> L89
                r1.exitAndThrowIfTimedOut()     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L89
                r1.checkOutNotClosed()     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L89
                long r1 = r1.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L89
                okio.Buffer r3 = r11.sendBuffer     // Catch: java.lang.Throwable -> L89
                long r3 = r3.size()     // Catch: java.lang.Throwable -> L89
                long r9 = java.lang.Math.min(r1, r3)     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L89
                long r2 = r1.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L89
                long r2 = r2 - r9
                r1.bytesLeftInWriteWindow = r2     // Catch: java.lang.Throwable -> L89
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Stream$StreamTimeout r0 = r0.writeTimeout
                r0.enter()
                if (r12 == 0) goto L61
                okio.Buffer r12 = r11.sendBuffer     // Catch: java.lang.Throwable -> L5f
                long r0 = r12.size()     // Catch: java.lang.Throwable -> L5f
                int r12 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r12 != 0) goto L61
                r12 = 1
                goto L62
            L5f:
                r12 = move-exception
                goto L78
            L61:
                r12 = 0
            L62:
                r7 = r12
                okhttp3.internal.http2.Http2Stream r12 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L5f
                okhttp3.internal.http2.Http2Connection r5 = r12.connection     // Catch: java.lang.Throwable -> L5f
                okhttp3.internal.http2.Http2Stream r12 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L5f
                int r6 = r12.id     // Catch: java.lang.Throwable -> L5f
                okio.Buffer r8 = r11.sendBuffer     // Catch: java.lang.Throwable -> L5f
                r5.writeData(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L5f
                okhttp3.internal.http2.Http2Stream r12 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Stream$StreamTimeout r12 = r12.writeTimeout
                r12.exitAndThrowIfTimedOut()
                return
            L78:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Stream$StreamTimeout r0 = r0.writeTimeout
                r0.exitAndThrowIfTimedOut()
                throw r12
            L80:
                r12 = move-exception
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L89
                okhttp3.internal.http2.Http2Stream$StreamTimeout r1 = r1.writeTimeout     // Catch: java.lang.Throwable -> L89
                r1.exitAndThrowIfTimedOut()     // Catch: java.lang.Throwable -> L89
                throw r12     // Catch: java.lang.Throwable -> L89
            L89:
                r12 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.emitFrame(boolean):void");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Http2Stream.this) {
                if (this.closed) {
                    return;
                }
                if (!Http2Stream.this.sink.finished) {
                    boolean z = this.sendBuffer.size() > 0;
                    if (this.trailers != null) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(false);
                        }
                        Http2Stream.this.connection.writeHeaders(Http2Stream.this.id, true, Util.toHeaderBlock(this.trailers));
                    } else if (z) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(true);
                        }
                    } else {
                        Http2Stream.this.connection.writeData(Http2Stream.this.id, true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    this.closed = true;
                }
                Http2Stream.this.connection.flush();
                Http2Stream.this.cancelStreamIfNecessary();
            }
        }
    }

    void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        }
        if (this.sink.finished) {
            throw new IOException("stream finished");
        }
        if (this.errorCode != null) {
            IOException iOException = this.errorException;
            if (iOException == null) {
                throw new StreamResetException(this.errorCode);
            }
        }
    }

    void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        @Override // okio.AsyncTimeout
        protected void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
            Http2Stream.this.connection.sendDegradedPingLater();
        }

        @Override // okio.AsyncTimeout
        protected IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException(GpsNetInitiatedHandler.NI_INTENT_KEY_TIMEOUT);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }
}
