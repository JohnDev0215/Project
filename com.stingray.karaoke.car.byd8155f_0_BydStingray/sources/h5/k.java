package h5;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class k {
    public static /* synthetic */ boolean a(Unsafe unsafe, p pVar, long j3, C1378d c1378d, C1378d c1378d2) {
        while (!unsafe.compareAndSwapObject(pVar, j3, c1378d, c1378d2)) {
            if (unsafe.getObject(pVar, j3) != c1378d) {
                return false;
            }
        }
        return true;
    }
}
