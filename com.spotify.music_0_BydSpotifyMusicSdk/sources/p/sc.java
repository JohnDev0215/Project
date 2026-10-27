package p;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class sc implements pd2 {
    public final /* synthetic */ int c = 1;
    public final Object e;
    public final Object k;

    public sc(OutputStream outputStream, tl2 tl2Var) {
        this.e = outputStream;
        this.k = tl2Var;
    }

    @Override // p.pd2
    public final void Y(fo foVar, long j) throws IOException {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                v03.j(foVar, "source");
                q30.i(foVar.e, 0L, j);
                while (j > 0) {
                    bb2 bb2Var = foVar.c;
                    v03.h(bb2Var);
                    long j2 = 0;
                    while (true) {
                        if (j2 < 65536) {
                            j2 += (long) (bb2Var.c - bb2Var.b);
                            if (j2 >= j) {
                                j2 = j;
                            } else {
                                bb2Var = bb2Var.f;
                                v03.h(bb2Var);
                            }
                        }
                    }
                    uc ucVar = (uc) obj;
                    pd2 pd2Var = (pd2) obj2;
                    ucVar.i();
                    try {
                        pd2Var.Y(foVar, j2);
                        if (ucVar.j()) {
                            throw ucVar.k(null);
                        }
                        j -= j2;
                    } catch (IOException e) {
                        if (!ucVar.j()) {
                            throw e;
                        }
                        throw ucVar.k(e);
                    } finally {
                        ucVar.j();
                    }
                }
                return;
            default:
                v03.j(foVar, "source");
                q30.i(foVar.e, 0L, j);
                while (j > 0) {
                    ((tl2) obj2).f();
                    bb2 bb2Var2 = foVar.c;
                    v03.h(bb2Var2);
                    int iMin = (int) Math.min(j, bb2Var2.c - bb2Var2.b);
                    ((OutputStream) obj).write(bb2Var2.a, bb2Var2.b, iMin);
                    int i2 = bb2Var2.b + iMin;
                    bb2Var2.b = i2;
                    long j3 = iMin;
                    j -= j3;
                    foVar.e -= j3;
                    if (i2 == bb2Var2.c) {
                        foVar.c = bb2Var2.a();
                        db2.a(bb2Var2);
                    }
                }
                return;
        }
    }

    @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                uc ucVar = (uc) obj;
                pd2 pd2Var = (pd2) this.k;
                ucVar.i();
                try {
                    pd2Var.close();
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
                ((OutputStream) obj).close();
                return;
        }
    }

    @Override // p.pd2
    public final tl2 e() {
        switch (this.c) {
            case 0:
                return (uc) this.e;
            default:
                return (tl2) this.k;
        }
    }

    @Override // p.pd2, java.io.Flushable
    public final void flush() throws IOException {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                uc ucVar = (uc) obj;
                pd2 pd2Var = (pd2) this.k;
                ucVar.i();
                try {
                    pd2Var.flush();
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
                ((OutputStream) obj).flush();
                return;
        }
    }

    public final String toString() {
        switch (this.c) {
            case 0:
                return "AsyncTimeout.sink(" + ((pd2) this.k) + ')';
            default:
                return "sink(" + ((OutputStream) this.e) + ')';
        }
    }

    public sc(be2 be2Var, sc scVar) {
        this.e = be2Var;
        this.k = scVar;
    }
}
