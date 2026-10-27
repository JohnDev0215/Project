package h5;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class j {
    public static /* synthetic */ boolean a(Unsafe unsafe, p pVar, long j3, o oVar, o oVar2) {
        while (!unsafe.compareAndSwapObject(pVar, j3, oVar, oVar2)) {
            if (unsafe.getObject(pVar, j3) != oVar) {
                return false;
            }
        }
        return true;
    }
}
