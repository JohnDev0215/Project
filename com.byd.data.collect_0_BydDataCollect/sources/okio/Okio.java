package okio;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class Okio {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f5808a = Logger.getLogger(Okio.class.getName());

    /* JADX INFO: renamed from: okio.Okio$3, reason: invalid class name */
    final class AnonymousClass3 implements Sink {
        AnonymousClass3() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // okio.Sink
        public Timeout e() {
            return Timeout.f5853a;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
        }

        @Override // okio.Sink
        public void h(Buffer buffer, long j) throws EOFException {
            buffer.s(j);
        }
    }

    private Okio() {
    }

    public static Sink a(File file) {
        if (file != null) {
            return f(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static BufferedSink b(Sink sink) {
        return new RealBufferedSink(sink);
    }

    public static BufferedSource c(Source source) {
        return new RealBufferedSource(source);
    }

    static boolean d(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Sink e(File file) {
        if (file != null) {
            return f(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink f(OutputStream outputStream) {
        return g(outputStream, new Timeout());
    }

    private static Sink g(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (timeout != null) {
            return new Sink() { // from class: okio.Okio.1
                @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // okio.Sink
                public Timeout e() {
                    return timeout;
                }

                @Override // okio.Sink, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                @Override // okio.Sink
                public void h(Buffer buffer, long j) throws IOException {
                    Util.b(buffer.f5771d, 0L, j);
                    while (j > 0) {
                        timeout.g();
                        Segment segment = buffer.f5770c;
                        int iMin = (int) Math.min(j, segment.f5844c - segment.f5843b);
                        outputStream.write(segment.f5842a, segment.f5843b, iMin);
                        int i2 = segment.f5843b + iMin;
                        segment.f5843b = i2;
                        long j2 = iMin;
                        j -= j2;
                        buffer.f5771d -= j2;
                        if (i2 == segment.f5844c) {
                            buffer.f5770c = segment.b();
                            SegmentPool.a(segment);
                        }
                    }
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static Sink h(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        AsyncTimeout asyncTimeoutM = m(socket);
        return asyncTimeoutM.t(g(socket.getOutputStream(), asyncTimeoutM));
    }

    public static Source i(File file) {
        if (file != null) {
            return j(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Source j(InputStream inputStream) {
        return k(inputStream, new Timeout());
    }

    private static Source k(final InputStream inputStream, final Timeout timeout) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout != null) {
            return new Source() { // from class: okio.Okio.2
                @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    inputStream.close();
                }

                @Override // okio.Source
                public Timeout e() {
                    return timeout;
                }

                @Override // okio.Source
                public long p(Buffer buffer, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    }
                    if (j == 0) {
                        return 0L;
                    }
                    try {
                        timeout.g();
                        Segment segmentJ0 = buffer.j0(1);
                        int i2 = inputStream.read(segmentJ0.f5842a, segmentJ0.f5844c, (int) Math.min(j, 8192 - segmentJ0.f5844c));
                        if (i2 == -1) {
                            return -1L;
                        }
                        segmentJ0.f5844c += i2;
                        long j2 = i2;
                        buffer.f5771d += j2;
                        return j2;
                    } catch (AssertionError e2) {
                        if (Okio.d(e2)) {
                            throw new IOException(e2);
                        }
                        throw e2;
                    }
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static Source l(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        AsyncTimeout asyncTimeoutM = m(socket);
        return asyncTimeoutM.u(k(socket.getInputStream(), asyncTimeoutM));
    }

    private static AsyncTimeout m(final Socket socket) {
        return new AsyncTimeout() { // from class: okio.Okio.4
            @Override // okio.AsyncTimeout
            protected IOException q(@Nullable IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // okio.AsyncTimeout
            protected void v() {
                try {
                    socket.close();
                } catch (AssertionError e2) {
                    if (!Okio.d(e2)) {
                        throw e2;
                    }
                    Okio.f5808a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e2);
                } catch (Exception e3) {
                    Okio.f5808a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e3);
                }
            }
        };
    }
}
