package O8;

import A7.C0054b;
import A7.m;
import Y.n;
import Y.p;
import java.util.Iterator;
import java.util.Map;
import s0.AbstractC2332F;
import s0.C2330D;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Iterator, B7.a {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f5193m;

    public h(Object[] objArr) {
        m.f(objArr, "array");
        this.f5193m = m.h(objArr);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                return ((C0054b) this.f5193m).hasNext();
            case 1:
                return ((Y.f) this.f5193m).f10589n;
            default:
                return this.f5193m.hasNext();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                return ((C0054b) this.f5193m).next();
            case 1:
                return (Map.Entry) ((Y.f) this.f5193m).next();
            default:
                return (AbstractC2332F) this.f5193m.next();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException();
            case 1:
                ((Y.f) this.f5193m).remove();
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public h(Y.e eVar) {
        n[] nVarArr = new n[8];
        for (int i7 = 0; i7 < 8; i7++) {
            nVarArr[i7] = new p(this);
        }
        this.f5193m = new Y.f(eVar, nVarArr);
    }

    public h(C2330D c2330d) {
        this.f5193m = c2330d.f21973u.iterator();
    }
}
