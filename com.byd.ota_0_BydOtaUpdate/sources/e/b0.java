package e;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b0 implements Closeable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Reader f3310c;

    /* JADX INFO: compiled from: ResponseBody.java */
    public class a extends b0 {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ u f3311d;
        public final /* synthetic */ long s;
        public final /* synthetic */ f.e x;

        public a(u uVar, long j, f.e eVar) {
            this.f3311d = uVar;
            this.s = j;
            this.x = eVar;
        }

        @Override // e.b0
        @Nullable
        public u H() {
            return this.f3311d;
        }

        @Override // e.b0
        public f.e K() {
            return this.x;
        }

        @Override // e.b0
        public long v() {
            return this.s;
        }
    }

    /* JADX INFO: compiled from: ResponseBody.java */
    public static final class b extends Reader {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f.e f3312c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Charset f3313d;
        public boolean s;
        public Reader x;

        public b(f.e eVar, Charset charset) {
            this.f3312c = eVar;
            this.f3313d = charset;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.s = true;
            Reader reader = this.x;
            if (reader != null) {
                reader.close();
            } else {
                this.f3312c.close();
            }
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i2, int i3) throws IOException {
            if (this.s) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.x;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.f3312c.F(), e.d0.c.a(this.f3312c, this.f3313d));
                this.x = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i2, i3);
        }
    }

    public static b0 I(@Nullable u uVar, long j, f.e eVar) {
        Objects.requireNonNull(eVar, "source == null");
        return new a(uVar, j, eVar);
    }

    public static b0 J(@Nullable u uVar, byte[] bArr) {
        return I(uVar, bArr.length, new f.c().f(bArr));
    }

    @Nullable
    public abstract u H();

    public abstract f.e K();

    public final InputStream c() {
        return K().F();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        e.d0.c.c(K());
    }

    public final Reader d() {
        Reader reader = this.f3310c;
        if (reader != null) {
            return reader;
        }
        b bVar = new b(K(), s());
        this.f3310c = bVar;
        return bVar;
    }

    public final Charset s() {
        u uVarH = H();
        return uVarH != null ? uVarH.b(e.d0.c.j) : e.d0.c.j;
    }

    public abstract long v();
}
