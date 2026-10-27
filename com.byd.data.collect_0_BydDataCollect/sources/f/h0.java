package f;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSource;

/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h0 implements Closeable {

    /* JADX INFO: compiled from: ResponseBody.java */
    class a extends h0 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ z f5108b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ long f5109c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ BufferedSource f5110d;

        a(z zVar, long j, BufferedSource bufferedSource) {
            this.f5108b = zVar;
            this.f5109c = j;
            this.f5110d = bufferedSource;
        }

        @Override // f.h0
        public BufferedSource S() {
            return this.f5110d;
        }

        @Override // f.h0
        public long j() {
            return this.f5109c;
        }

        @Override // f.h0
        @Nullable
        public z y() {
            return this.f5108b;
        }
    }

    public static h0 C(@Nullable z zVar, long j, BufferedSource bufferedSource) {
        Objects.requireNonNull(bufferedSource, "source == null");
        return new a(zVar, j, bufferedSource);
    }

    public static h0 R(@Nullable z zVar, byte[] bArr) {
        return C(zVar, bArr.length, new Buffer().f(bArr));
    }

    private static /* synthetic */ void a(Throwable th, AutoCloseable autoCloseable) throws Exception {
        if (th == null) {
            autoCloseable.close();
            return;
        }
        try {
            autoCloseable.close();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    private Charset d() {
        z zVarY = y();
        return zVarY != null ? zVarY.b(StandardCharsets.UTF_8) : StandardCharsets.UTF_8;
    }

    public abstract BufferedSource S();

    public final String T() throws Exception {
        BufferedSource bufferedSourceS = S();
        try {
            String strN = bufferedSourceS.N(f.k0.e.b(bufferedSourceS, d()));
            a(null, bufferedSourceS);
            return strN;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bufferedSourceS != null) {
                    a(th, bufferedSourceS);
                }
                throw th2;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        f.k0.e.f(S());
    }

    public abstract long j();

    @Nullable
    public abstract z y();
}
