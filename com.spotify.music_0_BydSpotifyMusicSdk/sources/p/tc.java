package p;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class tc implements de2 {
    public final /* synthetic */ int c = 1;
    public final Object e;
    public final Object k;

    public tc(InputStream inputStream, tl2 tl2Var) {
        v03.j(inputStream, "input");
        this.e = inputStream;
        this.k = tl2Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                uc ucVar = (uc) obj;
                de2 de2Var = (de2) this.k;
                ucVar.i();
                try {
                    de2Var.close();
                    if (ucVar.j()) {
                        throw ucVar.k(null);
                    }
                    return;
                } catch (IOException e) {
                    if (!ucVar.j()) {
                        throw e;
                    }
                    throw ucVar.k(e);
                } finally {
                    ucVar.j();
                }
            default:
                ((InputStream) obj).close();
                return;
        }
    }

    @Override // p.de2
    public final tl2 e() {
        switch (this.c) {
            case 0:
                return (uc) this.e;
            default:
                return (tl2) this.k;
        }
    }

    @Override // p.de2
    public final long t(fo foVar, long j) {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                v03.j(foVar, "sink");
                uc ucVar = (uc) obj;
                de2 de2Var = (de2) obj2;
                ucVar.i();
                try {
                    long jT = de2Var.t(foVar, j);
                    if (ucVar.j()) {
                        throw ucVar.k(null);
                    }
                    return jT;
                } catch (IOException e) {
                    if (ucVar.j()) {
                        throw ucVar.k(e);
                    }
                    throw e;
                } finally {
                    ucVar.j();
                }
            default:
                v03.j(foVar, "sink");
                if (j == 0) {
                    return 0L;
                }
                if (j < 0) {
                    throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
                }
                try {
                    ((tl2) obj2).f();
                    bb2 bb2VarS0 = foVar.S0(1);
                    int i2 = ((InputStream) obj).read(bb2VarS0.a, bb2VarS0.c, (int) Math.min(j, 8192 - bb2VarS0.c));
                    if (i2 == -1) {
                        if (bb2VarS0.b == bb2VarS0.c) {
                            foVar.c = bb2VarS0.a();
                            db2.a(bb2VarS0);
                        }
                        return -1L;
                    }
                    bb2VarS0.c += i2;
                    long j2 = i2;
                    foVar.e += j2;
                    return j2;
                } catch (AssertionError e2) {
                    if (v03.w(e2)) {
                        throw new IOException(e2);
                    }
                    throw e2;
                }
        }
    }

    public final String toString() {
        switch (this.c) {
            case 0:
                return "AsyncTimeout.source(" + ((de2) this.k) + ')';
            default:
                return "source(" + ((InputStream) this.e) + ')';
        }
    }

    public tc(be2 be2Var, tc tcVar) {
        this.e = be2Var;
        this.k = tcVar;
    }
}
