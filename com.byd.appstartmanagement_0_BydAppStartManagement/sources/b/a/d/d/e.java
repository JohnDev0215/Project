package b.a.d.d;

import b.a.d.d.b;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: XPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e<V extends b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<V> f331a;

    public void a(V v) {
        this.f331a = new WeakReference<>(v);
    }

    public V b() {
        WeakReference<V> weakReference = this.f331a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void c() {
        this.f331a = null;
    }
}
