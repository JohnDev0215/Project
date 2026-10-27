package c;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
final class g implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f636a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f637b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f638c;

    g(k kVar) {
        Objects.requireNonNull(kVar, "source == null");
        this.f637b = kVar;
    }

    @Override // c.c
    public boolean b(long j) {
        a aVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f638c) {
            throw new IllegalStateException("closed");
        }
        do {
            aVar = this.f636a;
            if (aVar.f627c >= j) {
                return true;
            }
        } while (this.f637b.g(aVar, 8192L) != -1);
        return false;
    }

    @Override // c.c
    public long c(d dVar) {
        return j(dVar, 0L);
    }

    @Override // c.k, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        if (this.f638c) {
            return;
        }
        this.f638c = true;
        this.f637b.close();
        this.f636a.j();
    }

    @Override // c.c
    public a d() {
        return this.f636a;
    }

    @Override // c.c
    public long f(d dVar) {
        return k(dVar, 0L);
    }

    @Override // c.k
    public long g(a aVar, long j) {
        if (aVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f638c) {
            throw new IllegalStateException("closed");
        }
        a aVar2 = this.f636a;
        if (aVar2.f627c == 0 && this.f637b.g(aVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f636a.g(aVar, Math.min(j, this.f636a.f627c));
    }

    @Override // c.c
    public int i(f fVar) throws EOFException {
        if (this.f638c) {
            throw new IllegalStateException("closed");
        }
        do {
            int iZ = this.f636a.z(fVar, true);
            if (iZ == -1) {
                return -1;
            }
            if (iZ != -2) {
                this.f636a.B(fVar.f634a[iZ].j());
                return iZ;
            }
        } while (this.f637b.g(this.f636a, 8192L) != -1);
        return -1;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f638c;
    }

    public long j(d dVar, long j) {
        if (this.f638c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jN = this.f636a.n(dVar, j);
            if (jN != -1) {
                return jN;
            }
            a aVar = this.f636a;
            long j2 = aVar.f627c;
            if (this.f637b.g(aVar, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - ((long) dVar.j())) + 1);
        }
    }

    public long k(d dVar, long j) {
        if (this.f638c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jO = this.f636a.o(dVar, j);
            if (jO != -1) {
                return jO;
            }
            a aVar = this.f636a;
            long j2 = aVar.f627c;
            if (this.f637b.g(aVar, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        a aVar = this.f636a;
        if (aVar.f627c == 0 && this.f637b.g(aVar, 8192L) == -1) {
            return -1;
        }
        return this.f636a.read(byteBuffer);
    }

    public String toString() {
        return "buffer(" + this.f637b + ")";
    }
}
