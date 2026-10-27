package p;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class m41 implements de2 {
    public final ko c;
    public final Inflater e;
    public int k;
    public boolean l;

    public m41(w02 w02Var, Inflater inflater) {
        this.c = w02Var;
        this.e = inflater;
    }

    public final long b(fo foVar, long j) {
        Inflater inflater = this.e;
        v03.j(foVar, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        if (!(!this.l)) {
            throw new IllegalStateException("closed".toString());
        }
        if (j == 0) {
            return 0L;
        }
        try {
            bb2 bb2VarS0 = foVar.S0(1);
            int iMin = (int) Math.min(j, 8192 - bb2VarS0.c);
            boolean zNeedsInput = inflater.needsInput();
            ko koVar = this.c;
            if (zNeedsInput && !koVar.Q()) {
                bb2 bb2Var = koVar.d().c;
                v03.h(bb2Var);
                int i = bb2Var.c;
                int i2 = bb2Var.b;
                int i3 = i - i2;
                this.k = i3;
                inflater.setInput(bb2Var.a, i2, i3);
            }
            int iInflate = inflater.inflate(bb2VarS0.a, bb2VarS0.c, iMin);
            int i4 = this.k;
            if (i4 != 0) {
                int remaining = i4 - inflater.getRemaining();
                this.k -= remaining;
                koVar.y(remaining);
            }
            if (iInflate > 0) {
                bb2VarS0.c += iInflate;
                long j2 = iInflate;
                foVar.e += j2;
                return j2;
            }
            if (bb2VarS0.b == bb2VarS0.c) {
                foVar.c = bb2VarS0.a();
                db2.a(bb2VarS0);
            }
            return 0L;
        } catch (DataFormatException e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.l) {
            return;
        }
        this.e.end();
        this.l = true;
        this.c.close();
    }

    @Override // p.de2
    public final tl2 e() {
        return this.c.e();
    }

    @Override // p.de2
    public final long t(fo foVar, long j) throws EOFException {
        v03.j(foVar, "sink");
        do {
            long jB = b(foVar, j);
            if (jB > 0) {
                return jB;
            }
            Inflater inflater = this.e;
            if (inflater.finished() || inflater.needsDictionary()) {
                return -1L;
            }
        } while (!this.c.Q());
        throw new EOFException("source exhausted prematurely");
    }

    public m41(de2 de2Var, Inflater inflater) {
        this(v03.d(de2Var), inflater);
    }
}
