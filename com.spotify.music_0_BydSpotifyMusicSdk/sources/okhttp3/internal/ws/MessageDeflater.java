package okhttp3.internal.ws;

import java.io.Closeable;
import java.util.zip.Deflater;
import p.fo;
import p.n80;

/* JADX INFO: loaded from: classes.dex */
public final class MessageDeflater implements Closeable {
    public final boolean c;
    public final fo e;
    public final Deflater k;
    public final n80 l;

    public MessageDeflater(boolean z) {
        this.c = z;
        fo foVar = new fo();
        this.e = foVar;
        Deflater deflater = new Deflater(-1, true);
        this.k = deflater;
        this.l = new n80(foVar, deflater);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        this.l.close();
    }
}
