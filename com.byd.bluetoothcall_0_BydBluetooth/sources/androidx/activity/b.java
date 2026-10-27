package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f181b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f180a = z;
    }

    void a(a aVar) {
        this.f181b.add(aVar);
    }

    public abstract void b();

    public final boolean c() {
        return this.f180a;
    }

    public final void d() {
        Iterator<a> it = this.f181b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    void e(a aVar) {
        this.f181b.remove(aVar);
    }

    public final void f(boolean z) {
        this.f180a = z;
    }
}
