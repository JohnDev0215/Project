package okio;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f4373a = Logger.getLogger(e.class.getName());

    /* JADX INFO: compiled from: Okio.java */
    final class a implements j {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ k f4374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ InputStream f4375c;

        a(k kVar, InputStream inputStream) {
            this.f4374b = kVar;
            this.f4375c = inputStream;
        }

        @Override // okio.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            this.f4375c.close();
        }

        @Override // okio.j
        public long e(b bVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (j == 0) {
                return 0L;
            }
            try {
                this.f4374b.a();
                h hVarG = bVar.G(1);
                int i = this.f4375c.read(hVarG.f4381a, hVarG.f4383c, (int) Math.min(j, 8192 - hVarG.f4383c));
                if (i == -1) {
                    return -1L;
                }
                hVarG.f4383c += i;
                long j2 = i;
                bVar.f4372d += j2;
                return j2;
            } catch (AssertionError e2) {
                if (e.b(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }

        public String toString() {
            return "source(" + this.f4375c + ")";
        }
    }

    private e() {
    }

    public static d a(j jVar) {
        return new g(jVar);
    }

    static boolean b(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static j c(InputStream inputStream) {
        return d(inputStream, new k());
    }

    private static j d(InputStream inputStream, k kVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (kVar != null) {
            return new a(kVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }
}
