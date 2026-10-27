package d2;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ActivityFragmentLifecycle.java */
/* JADX INFO: loaded from: classes.dex */
class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<i> f5617a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f5618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f5619c;

    a() {
    }

    @Override // d2.h
    public void a(i iVar) {
        this.f5617a.add(iVar);
        if (this.f5619c) {
            iVar.onDestroy();
        } else if (this.f5618b) {
            iVar.a();
        } else {
            iVar.g();
        }
    }

    @Override // d2.h
    public void b(i iVar) {
        this.f5617a.remove(iVar);
    }

    void c() {
        this.f5619c = true;
        Iterator it = k2.k.i(this.f5617a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).onDestroy();
        }
    }

    void d() {
        this.f5618b = true;
        Iterator it = k2.k.i(this.f5617a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).a();
        }
    }

    void e() {
        this.f5618b = false;
        Iterator it = k2.k.i(this.f5617a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).g();
        }
    }
}
