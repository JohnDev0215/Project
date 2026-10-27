package androidx.lifecycle;

import d2.C1065c;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1065c f11986a = new C1065c();

    public final void a(String str, AutoCloseable autoCloseable) {
        AutoCloseable autoCloseable2;
        C1065c c1065c = this.f11986a;
        if (c1065c != null) {
            if (c1065c.d) {
                C1065c.a(autoCloseable);
                return;
            }
            synchronized (c1065c.f13335a) {
                autoCloseable2 = (AutoCloseable) c1065c.f13336b.put(str, autoCloseable);
            }
            C1065c.a(autoCloseable2);
        }
    }

    public final void b() {
        C1065c c1065c = this.f11986a;
        if (c1065c != null && !c1065c.d) {
            c1065c.d = true;
            synchronized (c1065c.f13335a) {
                try {
                    Iterator it = c1065c.f13336b.values().iterator();
                    while (it.hasNext()) {
                        C1065c.a((AutoCloseable) it.next());
                    }
                    Iterator it2 = c1065c.c.iterator();
                    while (it2.hasNext()) {
                        C1065c.a((AutoCloseable) it2.next());
                    }
                    c1065c.c.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        d();
    }

    public final AutoCloseable c(String str) {
        AutoCloseable autoCloseable;
        C1065c c1065c = this.f11986a;
        if (c1065c == null) {
            return null;
        }
        synchronized (c1065c.f13335a) {
            autoCloseable = (AutoCloseable) c1065c.f13336b.get(str);
        }
        return autoCloseable;
    }

    public void d() {
    }
}
