package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f77a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f78b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f77a = z;
    }

    void a(a aVar) {
        this.f78b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f77a;
    }

    public final void d() {
        Iterator<a> it = this.f78b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    void e(a aVar) {
        this.f78b.remove(aVar);
    }

    public final void f(boolean z) {
        this.f77a = z;
    }
}
