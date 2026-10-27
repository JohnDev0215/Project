package b.a;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CopyOnWriteArrayList<a> f703b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f702a = z;
    }

    public void a(a aVar) {
        this.f703b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f702a;
    }

    public final void d() {
        Iterator<a> it = this.f703b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void e(a aVar) {
        this.f703b.remove(aVar);
    }

    public final void f(boolean z) {
        this.f702a = z;
    }
}
