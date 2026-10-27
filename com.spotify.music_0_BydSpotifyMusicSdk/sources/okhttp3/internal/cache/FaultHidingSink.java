package okhttp3.internal.cache;

import java.io.EOFException;
import java.io.IOException;
import p.fo;
import p.ow0;
import p.pd2;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class FaultHidingSink extends ow0 {
    public final ry0 e;
    public boolean k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaultHidingSink(pd2 pd2Var, ry0 ry0Var) {
        super(pd2Var);
        v03.j(pd2Var, "delegate");
        this.e = ry0Var;
    }

    @Override // p.ow0, p.pd2
    public final void Y(fo foVar, long j) throws EOFException {
        v03.j(foVar, "source");
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
        if (this.k) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.k = true;
            this.e.invoke(e);
        }
    }

    @Override // p.ow0, p.pd2, java.io.Flushable
    public final void flush() {
        if (this.k) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.k = true;
            this.e.invoke(e);
        }
    }
}
