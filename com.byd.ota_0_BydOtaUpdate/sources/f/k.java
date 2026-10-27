package f;

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
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f3711a = Logger.getLogger(k.class.getName());

    /* JADX INFO: compiled from: Okio.java */
    public class a implements p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ r f3712c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ OutputStream f3713d;

        public a(r rVar, OutputStream outputStream) {
            this.f3712c = rVar;
            this.f3713d = outputStream;
        }

        @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f3713d.close();
        }

        @Override // f.p
        public r e() {
            return this.f3712c;
        }

        @Override // f.p, java.io.Flushable
        public void flush() throws IOException {
            this.f3713d.flush();
        }

        @Override // f.p
        public void h(f.c cVar, long j) throws IOException {
            s.b(cVar.s, 0L, j);
            while (j > 0) {
                this.f3712c.f();
                n nVar = cVar.f3701d;
                int iMin = (int) Math.min(j, nVar.f3723c - nVar.f3722b);
                this.f3713d.write(nVar.f3721a, nVar.f3722b, iMin);
                int i2 = nVar.f3722b + iMin;
                nVar.f3722b = i2;
                long j2 = iMin;
                j -= j2;
                cVar.s -= j2;
                if (i2 == nVar.f3723c) {
                    cVar.f3701d = nVar.b();
                    o.a(nVar);
                }
            }
        }

        public String toString() {
            return "sink(" + this.f3713d + ")";
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    public class b implements q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ r f3714c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ InputStream f3715d;

        public b(r rVar, InputStream inputStream) {
            this.f3714c = rVar;
            this.f3715d = inputStream;
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f3715d.close();
        }

        @Override // f.q
        public r e() {
            return this.f3714c;
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (j == 0) {
                return 0L;
            }
            try {
                this.f3714c.f();
                n nVarB0 = cVar.b0(1);
                int i2 = this.f3715d.read(nVarB0.f3721a, nVarB0.f3723c, (int) Math.min(j, 8192 - nVarB0.f3723c));
                if (i2 == -1) {
                    return -1L;
                }
                nVarB0.f3723c += i2;
                long j2 = i2;
                cVar.s += j2;
                return j2;
            } catch (AssertionError e2) {
                if (k.c(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }

        public String toString() {
            return "source(" + this.f3715d + ")";
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    public class c extends f.a {
        public final /* synthetic */ Socket k;

        public c(Socket socket) {
            this.k = socket;
        }

        @Override // f.a
        public IOException o(@Nullable IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // f.a
        public void t() {
            try {
                this.k.close();
            } catch (AssertionError e2) {
                if (!k.c(e2)) {
                    throw e2;
                }
                k.f3711a.log(Level.WARNING, "Failed to close timed out socket " + this.k, (Throwable) e2);
            } catch (Exception e3) {
                k.f3711a.log(Level.WARNING, "Failed to close timed out socket " + this.k, (Throwable) e3);
            }
        }
    }

    public static d a(p pVar) {
        return new l(pVar);
    }

    public static e b(q qVar) {
        return new m(qVar);
    }

    public static boolean c(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static p d(OutputStream outputStream, r rVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (rVar != null) {
            return new a(rVar, outputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static p e(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        f.a aVarJ = j(socket);
        return aVarJ.r(d(socket.getOutputStream(), aVarJ));
    }

    public static q f(File file) throws FileNotFoundException {
        if (file != null) {
            return g(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q g(InputStream inputStream) {
        return h(inputStream, new r());
    }

    public static q h(InputStream inputStream, r rVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (rVar != null) {
            return new b(rVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static q i(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        f.a aVarJ = j(socket);
        return aVarJ.s(h(socket.getInputStream(), aVarJ));
    }

    public static f.a j(Socket socket) {
        return new c(socket);
    }
}
