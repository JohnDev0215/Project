package b.a.a.b;

import b.a.a.b.b;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c<T extends b> implements a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<T> f62a;

    @Override // b.a.a.b.a
    public void a() {
        if (this.f62a != null) {
            this.f62a = null;
        }
    }

    @Override // b.a.a.b.a
    public void b() {
    }

    @Override // b.a.a.b.a
    public void c() {
    }

    @Override // b.a.a.b.a
    public void d(T t) {
        this.f62a = new WeakReference<>(t);
    }

    public T e() {
        WeakReference<T> weakReference = this.f62a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }
}
