package h;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2948a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CopyOnWriteArrayList<a> f2949b = new CopyOnWriteArrayList<>();

    public b(boolean z3) {
        this.f2948a = z3;
    }

    public void a(a aVar) {
        this.f2949b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f2948a;
    }

    public final void d() {
        Iterator<a> it = this.f2949b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void e(a aVar) {
        this.f2949b.remove(aVar);
    }

    public final void f(boolean z3) {
        this.f2948a = z3;
    }
}
