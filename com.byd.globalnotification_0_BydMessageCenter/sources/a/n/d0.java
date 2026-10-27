package a.n;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<String, x> f1085a = new HashMap<>();

    public final void a() {
        for (x xVar : this.f1085a.values()) {
            xVar.f1122b = true;
            Map<String, Object> map = xVar.f1121a;
            if (map != null) {
                synchronized (map) {
                    for (Object obj : xVar.f1121a.values()) {
                        if (obj instanceof Closeable) {
                            try {
                                ((Closeable) obj).close();
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        }
                    }
                }
            }
            xVar.d();
        }
        this.f1085a.clear();
    }
}
