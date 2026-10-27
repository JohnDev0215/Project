package okio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
final class g implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f4378b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f4379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f4380d;

    g(j jVar) {
        Objects.requireNonNull(jVar, "source == null");
        this.f4379c = jVar;
    }

    @Override // okio.d
    public boolean a(long j) throws IOException {
        b bVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4380d) {
            throw new IllegalStateException("closed");
        }
        do {
            bVar = this.f4378b;
            if (bVar.f4372d >= j) {
                return true;
            }
        } while (this.f4379c.e(bVar, 8192L) != -1);
        return false;
    }

    @Override // okio.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.f4380d) {
            return;
        }
        this.f4380d = true;
        this.f4379c.close();
        this.f4378b.k();
    }

    @Override // okio.d
    public long d(ByteString byteString) throws IOException {
        return l(byteString, 0L);
    }

    @Override // okio.j
    public long e(b bVar, long j) throws IOException {
        if (bVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4380d) {
            throw new IllegalStateException("closed");
        }
        b bVar2 = this.f4378b;
        if (bVar2.f4372d == 0 && this.f4379c.e(bVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f4378b.e(bVar, Math.min(j, this.f4378b.f4372d));
    }

    @Override // okio.d
    public long g(ByteString byteString) throws IOException {
        return k(byteString, 0L);
    }

    @Override // okio.d
    public b h() {
        return this.f4378b;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f4380d;
    }

    @Override // okio.d
    public int j(f fVar) throws IOException {
        if (this.f4380d) {
            throw new IllegalStateException("closed");
        }
        do {
            int iB = this.f4378b.B(fVar, true);
            if (iB == -1) {
                return -1;
            }
            if (iB != -2) {
                this.f4378b.D(fVar.f4376b[iB].size());
                return iB;
            }
        } while (this.f4379c.e(this.f4378b, 8192L) != -1);
        return -1;
    }

    public long k(ByteString byteString, long j) throws IOException {
        if (this.f4380d) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jO = this.f4378b.o(byteString, j);
            if (jO != -1) {
                return jO;
            }
            b bVar = this.f4378b;
            long j2 = bVar.f4372d;
            if (this.f4379c.e(bVar, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - ((long) byteString.size())) + 1);
        }
    }

    public long l(ByteString byteString, long j) throws IOException {
        if (this.f4380d) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jP = this.f4378b.p(byteString, j);
            if (jP != -1) {
                return jP;
            }
            b bVar = this.f4378b;
            long j2 = bVar.f4372d;
            if (this.f4379c.e(bVar, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        b bVar = this.f4378b;
        if (bVar.f4372d == 0 && this.f4379c.e(bVar, 8192L) == -1) {
            return -1;
        }
        return this.f4378b.read(byteBuffer);
    }

    public String toString() {
        return "buffer(" + this.f4379c + ")";
    }
}
