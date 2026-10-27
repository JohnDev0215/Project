package a.n;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f1121a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f1122b = false;

    public void d() {
    }

    public <T> T e(String str, T t) {
        Object obj;
        synchronized (this.f1121a) {
            obj = this.f1121a.get(str);
            if (obj == null) {
                this.f1121a.put(str, t);
            }
        }
        if (obj != null) {
            t = (T) obj;
        }
        if (this.f1122b && (t instanceof Closeable)) {
            try {
                t.close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        return t;
    }
}
