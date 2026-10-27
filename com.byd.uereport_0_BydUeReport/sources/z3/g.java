package z3;

import z3.f;

/* JADX INFO: compiled from: XView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface g<P extends f> extends c<P> {
    default void bindEvent() {
    }

    int getLayoutId();

    P getRefP();

    P newP();

    void setRefP(P p7);

    default void unbindP() {
        f refP = getRefP();
        if (refP != null) {
            refP.unbindV();
        }
    }

    @Override // 
    default P getP() {
        P p7 = (P) getRefP();
        if (p7 != null) {
            return p7;
        }
        P p8 = (P) newP();
        setRefP(p8);
        if (p8 != null) {
            p8.bindV(this);
        }
        return p8;
    }
}
