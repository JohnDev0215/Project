package defpackage;

import org.chromium.base.c;

/* JADX INFO: compiled from: UnownedUserDataSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class bj3<E> extends aq1<E> implements w80, zi3 {
    public final aj3<bj3<E>> h;
    public final u80 i = new u80();

    /* JADX WARN: Multi-variable type inference failed */
    public bj3(aj3<? extends bj3<E>> aj3Var) {
        this.h = aj3Var;
    }

    @Override // defpackage.w80
    public final void destroy() {
        this.i.e = true;
        this.h.b(this);
    }

    public final void s(c cVar) {
        this.i.getClass();
        this.h.a(cVar, this);
    }
}
