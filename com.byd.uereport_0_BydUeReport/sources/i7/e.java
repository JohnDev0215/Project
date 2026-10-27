package i7;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f5514a = Logger.getLogger(e.class.getName());

    /* JADX INFO: compiled from: Okio.java */
    public class a implements j {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ k f5515d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ InputStream f5516e;

        public a(k kVar, InputStream inputStream) {
            this.f5515d = kVar;
            this.f5516e = inputStream;
        }

        @Override // i7.j
        public long S(b bVar, long j8) throws IOException {
            if (j8 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j8);
            }
            if (j8 == 0) {
                return 0L;
            }
            try {
                this.f5515d.a();
                h hVarO = bVar.O(1);
                int i8 = this.f5516e.read(hVarO.f5522a, hVarO.f5524c, (int) Math.min(j8, 8192 - hVarO.f5524c));
                if (i8 == -1) {
                    return -1L;
                }
                hVarO.f5524c += i8;
                long j9 = i8;
                bVar.f5513f += j9;
                return j9;
            } catch (AssertionError e8) {
                if (e.b(e8)) {
                    throw new IOException(e8);
                }
                throw e8;
            }
        }

        @Override // i7.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            this.f5516e.close();
        }

        public String toString() {
            return "source(" + this.f5516e + ")";
        }
    }

    public static d a(j jVar) {
        return new g(jVar);
    }

    public static boolean b(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static j c(InputStream inputStream) {
        return d(inputStream, new k());
    }

    public static j d(InputStream inputStream, k kVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (kVar != null) {
            return new a(kVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }
}
