package p;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class vl1 implements Closeable {
    public final ko c;

    public vl1(ArrayList arrayList, w02 w02Var) {
        this.c = w02Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.c.close();
    }
}
