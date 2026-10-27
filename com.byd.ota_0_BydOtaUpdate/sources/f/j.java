package f;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: InflaterSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f3709c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Inflater f3710d;
    public int s;
    public boolean x;

    public j(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f3709c = eVar;
        this.f3710d = inflater;
    }

    public boolean c() throws IOException {
        if (!this.f3710d.needsInput()) {
            return false;
        }
        d();
        if (this.f3710d.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f3709c.z()) {
            return true;
        }
        n nVar = this.f3709c.a().f3701d;
        int i2 = nVar.f3723c;
        int i3 = nVar.f3722b;
        int i4 = i2 - i3;
        this.s = i4;
        this.f3710d.setInput(nVar.f3721a, i3, i4);
        return false;
    }

    @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.x) {
            return;
        }
        this.f3710d.end();
        this.x = true;
        this.f3709c.close();
    }

    public final void d() throws IOException {
        int i2 = this.s;
        if (i2 == 0) {
            return;
        }
        int remaining = i2 - this.f3710d.getRemaining();
        this.s -= remaining;
        this.f3709c.skip(remaining);
    }

    @Override // f.q
    public r e() {
        return this.f3709c.e();
    }

    @Override // f.q
    public long n(c cVar, long j) throws IOException {
        boolean zC;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.x) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            zC = c();
            try {
                n nVarB0 = cVar.b0(1);
                Inflater inflater = this.f3710d;
                byte[] bArr = nVarB0.f3721a;
                int i2 = nVarB0.f3723c;
                int iInflate = inflater.inflate(bArr, i2, 8192 - i2);
                if (iInflate > 0) {
                    nVarB0.f3723c += iInflate;
                    long j2 = iInflate;
                    cVar.s += j2;
                    return j2;
                }
                if (!this.f3710d.finished() && !this.f3710d.needsDictionary()) {
                }
                d();
                if (nVarB0.f3722b != nVarB0.f3723c) {
                    return -1L;
                }
                cVar.f3701d = nVarB0.b();
                o.a(nVarB0);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!zC);
        throw new EOFException("source exhausted prematurely");
    }
}
