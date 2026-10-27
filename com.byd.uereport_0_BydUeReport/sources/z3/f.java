package z3;

import java.lang.ref.WeakReference;
import z3.c;

/* JADX INFO: compiled from: XPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f<V extends c> {
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
    public void bindV(V v7) {
        this.vRef = new WeakReference<>(v7);
    }
}
