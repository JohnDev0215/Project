package l2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class r extends BufferedOutputStream {
    public boolean l;

    public final void b(OutputStream outputStream) {
        AbstractC1346c.j(this.l);
        ((BufferedOutputStream) this).out = outputStream;
        ((BufferedOutputStream) this).count = 0;
        this.l = false;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        this.l = true;
        try {
            flush();
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            ((BufferedOutputStream) this).out.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        if (th == null) {
            return;
        }
        int i7 = AbstractC1343A.f15195a;
        throw th;
    }
}
