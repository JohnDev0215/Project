package p;

import java.io.IOException;
import java.util.zip.Deflater;

/* JADX INFO: loaded from: classes.dex */
public final class n80 implements pd2 {
    public final io c;
    public final Deflater e;
    public boolean k;

    public n80(fo foVar, Deflater deflater) {
        this.c = v03.c(foVar);
        this.e = deflater;
    }

    @Override // p.pd2
    public final void Y(fo foVar, long j) throws IOException {
        v03.j(foVar, "source");
        q30.i(foVar.e, 0L, j);
        while (j > 0) {
            bb2 bb2Var = foVar.c;
            v03.h(bb2Var);
            int iMin = (int) Math.min(j, bb2Var.c - bb2Var.b);
            this.e.setInput(bb2Var.a, bb2Var.b, iMin);
            b(false);
            long j2 = iMin;
            foVar.e -= j2;
            int i = bb2Var.b + iMin;
            bb2Var.b = i;
            if (i == bb2Var.c) {
                foVar.c = bb2Var.a();
                db2.a(bb2Var);
            }
            j -= j2;
        }
    }

    public final void b(boolean z) throws IOException {
        bb2 bb2VarS0;
        int iDeflate;
        io ioVar = this.c;
        fo foVarD = ioVar.d();
        while (true) {
            bb2VarS0 = foVarD.S0(1);
            Deflater deflater = this.e;
            byte[] bArr = bb2VarS0.a;
            if (z) {
                try {
                    int i = bb2VarS0.c;
                    iDeflate = deflater.deflate(bArr, i, 8192 - i, 2);
                } catch (NullPointerException e) {
                    throw new IOException("Deflater already closed", e);
                }
            } else {
                int i2 = bb2VarS0.c;
                iDeflate = deflater.deflate(bArr, i2, 8192 - i2);
            }
            if (iDeflate > 0) {
                bb2VarS0.c += iDeflate;
                foVarD.e += (long) iDeflate;
                ioVar.c0();
            } else if (deflater.needsInput()) {
                break;
            }
        }
        if (bb2VarS0.b == bb2VarS0.c) {
            foVarD.c = bb2VarS0.a();
            db2.a(bb2VarS0);
        }
    }

    @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        Deflater deflater = this.e;
        if (this.k) {
            return;
        }
        try {
            deflater.finish();
            b(false);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            deflater.end();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        try {
            this.c.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.k = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // p.pd2
    public final tl2 e() {
        return this.c.e();
    }

    @Override // p.pd2, java.io.Flushable
    public final void flush() throws IOException {
        b(true);
        this.c.flush();
    }

    public final String toString() {
        return "DeflaterSink(" + this.c + ')';
    }
}
