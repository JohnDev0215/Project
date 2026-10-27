package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final Map<String, Object> f777a = new HashMap();

    private static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @MainThread
    final void a() {
        Map<String, Object> map = this.f777a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f777a.values().iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
        }
        d();
    }

    <T> T c(String str) {
        T t;
        Map<String, Object> map = this.f777a;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t = (T) this.f777a.get(str);
        }
        return t;
    }

    protected void d() {
    }
}
