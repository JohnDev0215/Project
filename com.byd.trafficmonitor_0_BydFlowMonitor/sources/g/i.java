package g;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: InflaterSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f5480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Inflater f5481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5482d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5483e;

    public i(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f5480b = eVar;
        this.f5481c = inflater;
    }

    @Override // g.p
    public q b() {
        return this.f5480b.b();
    }

    @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f5483e) {
            return;
        }
        this.f5481c.end();
        this.f5483e = true;
        this.f5480b.close();
    }

    @Override // g.p
    public long m(c cVar, long j) throws IOException {
        boolean zW;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f5483e) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            zW = w();
            try {
                m mVarI0 = cVar.i0(1);
                int iInflate = this.f5481c.inflate(mVarI0.f5496a, mVarI0.f5498c, (int) Math.min(j, 8192 - mVarI0.f5498c));
                if (iInflate > 0) {
                    mVarI0.f5498c += iInflate;
                    long j2 = iInflate;
                    cVar.f5471d += j2;
                    return j2;
                }
                if (!this.f5481c.finished() && !this.f5481c.needsDictionary()) {
                }
                z();
                if (mVarI0.f5497b != mVarI0.f5498c) {
                    return -1L;
                }
                cVar.f5470c = mVarI0.b();
                n.a(mVarI0);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!zW);
        throw new EOFException("source exhausted prematurely");
    }

    public final boolean w() throws IOException {
        if (!this.f5481c.needsInput()) {
            return false;
        }
        z();
        if (this.f5481c.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f5480b.D()) {
            return true;
        }
        m mVar = this.f5480b.a().f5470c;
        int i2 = mVar.f5498c;
        int i3 = mVar.f5497b;
        int i4 = i2 - i3;
        this.f5482d = i4;
        this.f5481c.setInput(mVar.f5496a, i3, i4);
        return false;
    }

    public final void z() throws IOException {
        int i2 = this.f5482d;
        if (i2 == 0) {
            return;
        }
        int remaining = i2 - this.f5481c.getRemaining();
        this.f5482d -= remaining;
        this.f5480b.o(remaining);
    }
}
