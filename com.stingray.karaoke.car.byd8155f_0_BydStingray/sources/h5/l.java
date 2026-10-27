package h5;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class l {
    public static /* synthetic */ boolean a(Unsafe unsafe, p pVar, long j3, Object obj, Object obj2) {
        while (!unsafe.compareAndSwapObject(pVar, j3, obj, obj2)) {
            if (unsafe.getObject(pVar, j3) != obj) {
                return false;
            }
        }
        return true;
    }
}
