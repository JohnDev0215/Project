package b.a;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CopyOnWriteArrayList<a> f570b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f569a = z;
    }

    public void a(a aVar) {
        this.f570b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f569a;
    }

    public final void d() {
        Iterator<a> it = this.f570b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void e(a aVar) {
        this.f570b.remove(aVar);
    }

    public final void f(boolean z) {
        this.f569a = z;
    }
}
