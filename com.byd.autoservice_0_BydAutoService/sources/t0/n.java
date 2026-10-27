package t0;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: ViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f5011a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f5012b = false;

    public static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    public final void a() {
        this.f5012b = true;
        Map<String, Object> map = this.f5011a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f5011a.values().iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
        }
        c();
    }

    public void c() {
    }
}
