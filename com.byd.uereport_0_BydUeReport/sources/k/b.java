package k;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CopyOnWriteArrayList<a> f5689b = new CopyOnWriteArrayList<>();

    public b(boolean z7) {
        this.f5688a = z7;
    }

    public void a(a aVar) {
        this.f5689b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f5688a;
    }

    public final void d() {
        Iterator<a> it = this.f5689b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void e(a aVar) {
        this.f5689b.remove(aVar);
    }

    public final void f(boolean z7) {
        this.f5688a = z7;
    }
}
