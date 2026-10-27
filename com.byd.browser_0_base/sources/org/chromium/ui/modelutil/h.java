package org.chromium.ui.modelutil;

import defpackage.q81;
import defpackage.w62;
import defpackage.xm2;
import java.util.Iterator;
import java.util.List;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: PropertyListModel.java */
/* JADX INFO: loaded from: classes3.dex */
public final class h<T extends k<P>, P> extends q81<T, P> {
    public final w62 g = new w62(0, this);

    @Override // defpackage.q81
    public final void A(List list) {
        w62 w62Var;
        Iterator<T> it = iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            w62Var = this.g;
            if (!zHasNext) {
                break;
            } else {
                it.next().d(w62Var);
            }
        }
        super.A(list);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            ((k) it2.next()).a(w62Var);
        }
    }

    @Override // defpackage.q81
    public final void v(xm2<T> xm2Var, int i) {
        throw null;
    }

    @Override // defpackage.q81
    public final Object y(int i) {
        k kVar = (k) super.y(i);
        kVar.d(this.g);
        return kVar;
    }

    @Override // defpackage.q81
    public final void z(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            get(0 + i3).d(this.g);
        }
        super.z(0, i2);
    }
}
