package kotlin.n;

import java.io.Closeable;
import java.io.IOException;
import kotlin.b;

/* JADX INFO: compiled from: Closeable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final void a(Closeable closeable, Throwable th) throws IOException {
        if (closeable == null) {
            return;
        }
        if (th == null) {
            closeable.close();
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th2) {
            b.a(th, th2);
        }
    }
}
