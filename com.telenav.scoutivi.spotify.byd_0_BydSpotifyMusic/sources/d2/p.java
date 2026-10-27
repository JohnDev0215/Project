package d2;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: TargetTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class p implements i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Set<h2.h<?>> f5652e = Collections.newSetFromMap(new WeakHashMap());

    @Override // d2.i
    public void a() {
        Iterator it = k2.k.i(this.f5652e).iterator();
        while (it.hasNext()) {
            ((h2.h) it.next()).a();
        }
    }

    public void b() {
        this.f5652e.clear();
    }

    public List<h2.h<?>> c() {
        return k2.k.i(this.f5652e);
    }

    @Override // d2.i
    public void g() {
        Iterator it = k2.k.i(this.f5652e).iterator();
        while (it.hasNext()) {
            ((h2.h) it.next()).g();
        }
    }

    public void m(h2.h<?> hVar) {
        this.f5652e.add(hVar);
    }

    public void n(h2.h<?> hVar) {
        this.f5652e.remove(hVar);
    }

    @Override // d2.i
    public void onDestroy() {
        Iterator it = k2.k.i(this.f5652e).iterator();
        while (it.hasNext()) {
            ((h2.h) it.next()).onDestroy();
        }
    }
}
