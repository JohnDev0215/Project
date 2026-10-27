package C3;

import B5.G;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Closeable {
    public final G l;

    public g(G g7) {
        this.l = g7;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.l.close();
    }
}
