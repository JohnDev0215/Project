package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, Object> f1416a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile boolean f1417b = false;

    private static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    final void a() {
        this.f1417b = true;
        Map<String, Object> map = this.f1416a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f1416a.values().iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
        }
        c();
    }

    protected void c() {
    }
}
