package k4;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A4.m {
    @Override // A4.m
    public final void c(Object obj, Object obj2) {
        o oVar = (o) obj;
        oVar.getClass();
        ArrayDeque arrayDeque = o.f18203b;
        synchronized (arrayDeque) {
            arrayDeque.offer(oVar);
        }
    }
}
