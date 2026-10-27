package c;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f631a = Logger.getLogger(e.class.getName());

    final class a implements k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ l f632a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ InputStream f633b;

        a(l lVar, InputStream inputStream) {
            this.f632a = lVar;
            this.f633b = inputStream;
        }

        @Override // c.k, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            this.f633b.close();
        }

        @Override // c.k
        public long g(c.a aVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (j == 0) {
                return 0L;
            }
            try {
                this.f632a.a();
                h hVarE = aVar.E(1);
                int i = this.f633b.read(hVarE.f639a, hVarE.f641c, (int) Math.min(j, 8192 - hVarE.f641c));
                if (i == -1) {
                    return -1L;
                }
                hVarE.f641c += i;
                long j2 = i;
                aVar.f627c += j2;
                return j2;
            } catch (AssertionError e) {
                if (e.b(e)) {
                    throw new IOException(e);
                }
                throw e;
            }
        }

        public String toString() {
            return "source(" + this.f633b + ")";
        }
    }

    private e() {
    }

    public static c a(k kVar) {
        return new g(kVar);
    }

    static boolean b(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static k c(InputStream inputStream) {
        return d(inputStream, new l());
    }

    private static k d(InputStream inputStream, l lVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (lVar != null) {
            return new a(lVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }
}
