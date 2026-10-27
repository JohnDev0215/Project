package g;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f5484a = Logger.getLogger(j.class.getName());

    /* JADX INFO: compiled from: Okio.java */
    public class a implements o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ q f5485b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ OutputStream f5486c;

        public a(q qVar, OutputStream outputStream) {
            this.f5485b = qVar;
            this.f5486c = outputStream;
        }

        @Override // g.o
        public q b() {
            return this.f5485b;
        }

        @Override // g.o, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f5486c.close();
        }

        @Override // g.o, java.io.Flushable
        public void flush() throws IOException {
            this.f5486c.flush();
        }

        @Override // g.o
        public void g(g.c cVar, long j) throws IOException {
            r.b(cVar.f5471d, 0L, j);
            while (j > 0) {
                this.f5485b.f();
                m mVar = cVar.f5470c;
                int iMin = (int) Math.min(j, mVar.f5498c - mVar.f5497b);
                this.f5486c.write(mVar.f5496a, mVar.f5497b, iMin);
                int i2 = mVar.f5497b + iMin;
                mVar.f5497b = i2;
                long j2 = iMin;
                j -= j2;
                cVar.f5471d -= j2;
                if (i2 == mVar.f5498c) {
                    cVar.f5470c = mVar.b();
                    n.a(mVar);
                }
            }
        }

        public String toString() {
            return "sink(" + this.f5486c + ")";
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    public class b implements p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ q f5487b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ InputStream f5488c;

        public b(q qVar, InputStream inputStream) {
            this.f5487b = qVar;
            this.f5488c = inputStream;
        }

        @Override // g.p
        public q b() {
            return this.f5487b;
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f5488c.close();
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (j == 0) {
                return 0L;
            }
            try {
                this.f5487b.f();
                m mVarI0 = cVar.i0(1);
                int i2 = this.f5488c.read(mVarI0.f5496a, mVarI0.f5498c, (int) Math.min(j, 8192 - mVarI0.f5498c));
                if (i2 == -1) {
                    return -1L;
                }
                mVarI0.f5498c += i2;
                long j2 = i2;
                cVar.f5471d += j2;
                return j2;
            } catch (AssertionError e2) {
                if (j.c(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }

        public String toString() {
            return "source(" + this.f5488c + ")";
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    public class c extends g.a {
        public final /* synthetic */ Socket k;

        public c(Socket socket) {
            this.k = socket;
        }

        @Override // g.a
        public IOException o(@Nullable IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // g.a
        public void t() {
            try {
                this.k.close();
            } catch (AssertionError e2) {
                if (!j.c(e2)) {
                    throw e2;
                }
                j.f5484a.log(Level.WARNING, "Failed to close timed out socket " + this.k, (Throwable) e2);
            } catch (Exception e3) {
                j.f5484a.log(Level.WARNING, "Failed to close timed out socket " + this.k, (Throwable) e3);
            }
        }
    }

    public static d a(o oVar) {
        return new k(oVar);
    }

    public static e b(p pVar) {
        return new l(pVar);
    }

    public static boolean c(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static o d(OutputStream outputStream, q qVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (qVar != null) {
            return new a(qVar, outputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static o e(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        g.a aVarJ = j(socket);
        return aVarJ.r(d(socket.getOutputStream(), aVarJ));
    }

    public static p f(File file) throws FileNotFoundException {
        if (file != null) {
            return g(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static p g(InputStream inputStream) {
        return h(inputStream, new q());
    }

    public static p h(InputStream inputStream, q qVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (qVar != null) {
            return new b(qVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static p i(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        g.a aVarJ = j(socket);
        return aVarJ.s(h(socket.getInputStream(), aVarJ));
    }

    public static g.a j(Socket socket) {
        return new c(socket);
    }
}
