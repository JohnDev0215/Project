package p;

import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class hm0 extends ow0 {
    public final ry0 e;
    public boolean k;

    public hm0(pd2 pd2Var, ry0 ry0Var) {
        super(pd2Var);
        this.e = ry0Var;
    }

    @Override // p.ow0, p.pd2
    public final void Y(fo foVar, long j) throws EOFException {
        if (this.k) {
            foVar.y(j);
            return;
        }
        try {
            super.Y(foVar, j);
        } catch (IOException e) {
            this.k = true;
            this.e.invoke(e);
        }
    }

    @Override // p.ow0, p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            super.close();
        } catch (IOException e) {
            this.k = true;
            this.e.invoke(e);
        }
    }

    @Override // p.ow0, p.pd2, java.io.Flushable
    public final void flush() {
        try {
            super.flush();
        } catch (IOException e) {
            this.k = true;
            this.e.invoke(e);
        }
    }
}
