package defpackage;

import java.util.ArrayDeque;

/* JADX INFO: compiled from: ChildConnectionAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public final class fn implements Runnable {
    public final /* synthetic */ qn e;
    public final /* synthetic */ en f;

    public fn(en enVar, qn qnVar) {
        this.f = enVar;
        this.e = qnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable;
        gn gnVar = this.f.b;
        gnVar.c(this.e);
        ArrayDeque arrayDeque = gnVar.b;
        if (arrayDeque.isEmpty()) {
            return;
        }
        ((Runnable) arrayDeque.remove()).run();
        if (arrayDeque.isEmpty() || (runnable = gnVar.a) == null) {
            return;
        }
        runnable.run();
    }
}
