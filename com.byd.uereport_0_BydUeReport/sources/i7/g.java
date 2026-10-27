package i7;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f5519d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j f5520e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5521f;

    public g(j jVar) {
        Objects.requireNonNull(jVar, "source == null");
        this.f5520e = jVar;
    }

    @Override // i7.d
    public long D(ByteString byteString) throws IOException {
        return e(byteString, 0L);
    }

    @Override // i7.d
    public b G() {
        return this.f5519d;
    }

    @Override // i7.d
    public long Q(ByteString byteString) throws IOException {
        return g(byteString, 0L);
    }

    @Override // i7.j
    public long S(b bVar, long j8) throws IOException {
        if (bVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j8 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j8);
        }
        if (this.f5521f) {
            throw new IllegalStateException("closed");
        }
        b bVar2 = this.f5519d;
        if (bVar2.f5513f == 0 && this.f5520e.S(bVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f5519d.S(bVar, Math.min(j8, this.f5519d.f5513f));
    }

    @Override // i7.j, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.f5521f) {
            return;
        }
        this.f5521f = true;
        this.f5520e.close();
        this.f5519d.e();
    }

    public long e(ByteString byteString, long j8) throws IOException {
        if (this.f5521f) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jM = this.f5519d.m(byteString, j8);
            if (jM != -1) {
                return jM;
            }
            b bVar = this.f5519d;
            long j9 = bVar.f5513f;
            if (this.f5520e.S(bVar, 8192L) == -1) {
                return -1L;
            }
            j8 = Math.max(j8, (j9 - ((long) byteString.size())) + 1);
        }
    }

    public long g(ByteString byteString, long j8) throws IOException {
        if (this.f5521f) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jN = this.f5519d.n(byteString, j8);
            if (jN != -1) {
                return jN;
            }
            b bVar = this.f5519d;
            long j9 = bVar.f5513f;
            if (this.f5520e.S(bVar, 8192L) == -1) {
                return -1L;
            }
            j8 = Math.max(j8, j9);
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f5521f;
    }

    @Override // i7.d
    public boolean p(long j8) throws IOException {
        b bVar;
        if (j8 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j8);
        }
        if (this.f5521f) {
            throw new IllegalStateException("closed");
        }
        do {
            bVar = this.f5519d;
            if (bVar.f5513f >= j8) {
                return true;
            }
        } while (this.f5520e.S(bVar, 8192L) != -1);
        return false;
    }

    @Override // i7.d
    public int p0(f fVar) throws IOException {
        if (this.f5521f) {
            throw new IllegalStateException("closed");
        }
        do {
            int iF = this.f5519d.F(fVar, true);
            if (iF == -1) {
                return -1;
            }
            if (iF != -2) {
                this.f5519d.L(fVar.f5517d[iF].size());
                return iF;
            }
        } while (this.f5520e.S(this.f5519d, 8192L) != -1);
        return -1;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        b bVar = this.f5519d;
        if (bVar.f5513f == 0 && this.f5520e.S(bVar, 8192L) == -1) {
            return -1;
        }
        return this.f5519d.read(byteBuffer);
    }

    public String toString() {
        return "buffer(" + this.f5520e + ")";
    }
}
