package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f80a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f81b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f80a = z;
    }

    void a(a aVar) {
        this.f81b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f80a;
    }

    public final void d() {
        Iterator<a> it = this.f81b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    void e(a aVar) {
        this.f81b.remove(aVar);
    }

    public final void f(boolean z) {
        this.f80a = z;
    }
}
