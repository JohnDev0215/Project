package org.chromium.ui.modelutil;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.ui.modelutil.d;

/* JADX INFO: compiled from: ListObservableImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class e<P> implements d<P> {
    public final org.chromium.base.b<d.a<P>> e = new org.chromium.base.b<>();

    public final void g(d.a<P> aVar) {
        this.e.a(aVar);
    }

    public final void p(int i, int i2) {
        Iterator<d.a<P>> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((d.a) aVar.next()).f(i, i2);
            }
        }
    }

    public void q(int i, int i2, P p) {
        Iterator<d.a<P>> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((d.a) aVar.next()).i(i, i2, p);
            }
        }
    }

    public void r(int i, int i2) {
        Iterator<d.a<P>> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((d.a) aVar.next()).b(i, i2);
            }
        }
    }

    public void s(int i, int i2) {
        Iterator<d.a<P>> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((d.a) aVar.next()).j(i, i2);
            }
        }
    }
}
