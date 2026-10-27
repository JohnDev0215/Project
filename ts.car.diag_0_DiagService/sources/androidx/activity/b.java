package androidx.activity;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f80a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f81b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f80a = z;
    }

    void a(@NonNull a aVar) {
        this.f81b.add(aVar);
    }

    @MainThread
    public abstract void b();

    @MainThread
    public final boolean c() {
        return this.f80a;
    }

    @MainThread
    public final void d() {
        Iterator<a> it = this.f81b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    void e(@NonNull a aVar) {
        this.f81b.remove(aVar);
    }

    @MainThread
    public final void f(boolean z) {
        this.f80a = z;
    }
}
