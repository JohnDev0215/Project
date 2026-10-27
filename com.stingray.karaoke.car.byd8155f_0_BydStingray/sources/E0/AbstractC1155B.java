package e0;

import T.C0737e;

/* JADX INFO: renamed from: e0.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1155B implements InterfaceC1154A {
    public final C0737e l = new C0737e(0);

    public final boolean g(int i7) {
        return (this.l.get() & i7) != 0;
    }

    public final void h(int i7) {
        C0737e c0737e;
        int i10;
        do {
            c0737e = this.l;
            i10 = c0737e.get();
            if ((i10 & i7) != 0) {
                return;
            }
        } while (!c0737e.compareAndSet(i10, i10 | i7));
    }
}
