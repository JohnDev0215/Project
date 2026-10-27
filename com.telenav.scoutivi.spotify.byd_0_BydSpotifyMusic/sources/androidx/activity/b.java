package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f327b = new CopyOnWriteArrayList<>();

    public b(boolean z6) {
        this.f326a = z6;
    }

    void a(a aVar) {
        this.f327b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f326a;
    }

    public final void d() {
        Iterator<a> it = this.f327b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    void e(a aVar) {
        this.f327b.remove(aVar);
    }

    public final void f(boolean z6) {
        this.f326a = z6;
    }
}
