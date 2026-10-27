package d2;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RequestTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<g2.c> f5642a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<g2.c> f5643b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f5644c;

    public boolean a(g2.c cVar) {
        boolean z6 = true;
        if (cVar == null) {
            return true;
        }
        boolean zRemove = this.f5642a.remove(cVar);
        if (!this.f5643b.remove(cVar) && !zRemove) {
            z6 = false;
        }
        if (z6) {
            cVar.clear();
        }
        return z6;
    }

    public void b() {
        Iterator it = k2.k.i(this.f5642a).iterator();
        while (it.hasNext()) {
            a((g2.c) it.next());
        }
        this.f5643b.clear();
    }

    public void c() {
        this.f5644c = true;
        for (g2.c cVar : k2.k.i(this.f5642a)) {
            if (cVar.isRunning() || cVar.h()) {
                cVar.clear();
                this.f5643b.add(cVar);
            }
        }
    }

    public void d() {
        this.f5644c = true;
        for (g2.c cVar : k2.k.i(this.f5642a)) {
            if (cVar.isRunning()) {
                cVar.c();
                this.f5643b.add(cVar);
            }
        }
    }

    public void e() {
        for (g2.c cVar : k2.k.i(this.f5642a)) {
            if (!cVar.h() && !cVar.i()) {
                cVar.clear();
                if (this.f5644c) {
                    this.f5643b.add(cVar);
                } else {
                    cVar.d();
                }
            }
        }
    }

    public void f() {
        this.f5644c = false;
        for (g2.c cVar : k2.k.i(this.f5642a)) {
            if (!cVar.h() && !cVar.isRunning()) {
                cVar.d();
            }
        }
        this.f5643b.clear();
    }

    public void g(g2.c cVar) {
        this.f5642a.add(cVar);
        if (!this.f5644c) {
            cVar.d();
            return;
        }
        cVar.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.f5643b.add(cVar);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f5642a.size() + ", isPaused=" + this.f5644c + "}";
    }
}
