package b.b.m.e;

import b.b.m.e.g;

/* JADX INFO: compiled from: XView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface h<P extends g> extends c<P> {
    default void bindEvent() {
    }

    int getLayoutId();

    P getRefP();

    P newP();

    void setRefP(P p);

    default void unbindP() {
        g refP = getRefP();
        if (refP != null) {
            refP.unbindV();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: getP, reason: merged with bridge method [inline-methods] */
    default P mo7getP() {
        P p = (P) getRefP();
        if (p != null) {
            return p;
        }
        P p2 = (P) newP();
        setRefP(p2);
        if (p2 != null) {
            p2.bindV(this);
        }
        return p2;
    }
}
