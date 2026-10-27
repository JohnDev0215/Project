package b.b.m.e;

import b.b.m.e.c;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: XPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g<V extends c> {
    public static final int ERR_SUCCESS = 0;
    public static final int SUCCESS = 200;
    public WeakReference<V> vRef;

    public V getV() {
        WeakReference<V> weakReference = this.vRef;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void unbindV() {
        this.vRef = null;
    }

    @Override // 
    public void bindV(V v) {
        this.vRef = new WeakReference<>(v);
    }
}
