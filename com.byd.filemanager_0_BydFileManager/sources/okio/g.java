package okio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
final class g implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f4656b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f4657c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f4658d;

    g(j jVar) {
        Objects.requireNonNull(jVar, "source == null");
        this.f4657c = jVar;
    }

    @Override // okio.d
    public boolean a(long j) throws IOException {
        b bVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4658d) {
            throw new IllegalStateException("closed");
        }
        do {
            bVar = this.f4656b;
            if (bVar.f4650d >= j) {
                return true;
            }
        } while (this.f4657c.d(bVar, 8192L) != -1);
        return false;
    }

    @Override // okio.d
    public long c(ByteString byteString) throws IOException {
        return k(byteString, 0L);
    }

    @Override // okio.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.f4658d) {
            return;
        }
        this.f4658d = true;
        this.f4657c.close();
        this.f4656b.j();
    }

    @Override // okio.j
    public long d(b bVar, long j) throws IOException {
        if (bVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4658d) {
            throw new IllegalStateException("closed");
        }
        b bVar2 = this.f4656b;
        if (bVar2.f4650d == 0 && this.f4657c.d(bVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f4656b.d(bVar, Math.min(j, this.f4656b.f4650d));
    }

    @Override // okio.d
    public long f(ByteString byteString) throws IOException {
        return j(byteString, 0L);
    }

    @Override // okio.d
    public b g() {
        return this.f4656b;
    }

    @Override // okio.d
    public int i(f fVar) throws IOException {
        if (this.f4658d) {
            throw new IllegalStateException("closed");
        }
        do {
            int iA = this.f4656b.A(fVar, true);
            if (iA == -1) {
                return -1;
            }
            if (iA != -2) {
                this.f4656b.C(fVar.f4654b[iA].size());
                return iA;
            }
        } while (this.f4657c.d(this.f4656b, 8192L) != -1);
        return -1;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f4658d;
    }

    public long j(ByteString byteString, long j) throws IOException {
        if (this.f4658d) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jN = this.f4656b.n(byteString, j);
            if (jN != -1) {
                return jN;
            }
            b bVar = this.f4656b;
            long j2 = bVar.f4650d;
            if (this.f4657c.d(bVar, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - ((long) byteString.size())) + 1);
        }
    }

    public long k(ByteString byteString, long j) throws IOException {
        if (this.f4658d) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jO = this.f4656b.o(byteString, j);
            if (jO != -1) {
                return jO;
            }
            b bVar = this.f4656b;
            long j2 = bVar.f4650d;
            if (this.f4657c.d(bVar, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        b bVar = this.f4656b;
        if (bVar.f4650d == 0 && this.f4657c.d(bVar, 8192L) == -1) {
            return -1;
        }
        return this.f4656b.read(byteBuffer);
    }

    public String toString() {
        return "buffer(" + this.f4657c + ")";
    }
}
