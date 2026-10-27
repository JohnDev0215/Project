package b.n;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: ViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f2970a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f2971b = false;

    public static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public final void a() {
        this.f2971b = true;
        Map<String, Object> map = this.f2970a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f2970a.values().iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
        }
        d();
    }

    public <T> T c(String str) {
        T t;
        Map<String, Object> map = this.f2970a;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t = (T) this.f2970a.get(str);
        }
        return t;
    }

    public void d() {
    }

    public <T> T e(String str, T t) {
        Object obj;
        synchronized (this.f2970a) {
            obj = this.f2970a.get(str);
            if (obj == null) {
                this.f2970a.put(str, t);
            }
        }
        if (obj != null) {
            t = (T) obj;
        }
        if (this.f2971b) {
            b(t);
        }
        return t;
    }
}
