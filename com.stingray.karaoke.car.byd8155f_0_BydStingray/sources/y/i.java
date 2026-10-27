package Y;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import o7.C2051c;
import o7.C2053e;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractCollection implements Collection, B7.b {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f10598m;

    public i(e eVar) {
        this.f10598m = eVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection collection) {
        switch (this.l) {
            case 1:
                A7.m.f(collection, "elements");
                throw new UnsupportedOperationException();
            default:
                return super.addAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        switch (this.l) {
            case 0:
                ((e) this.f10598m).clear();
                break;
            default:
                ((C2053e) this.f10598m).clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                return ((e) this.f10598m).containsValue(obj);
            default:
                return ((C2053e) this.f10598m).containsValue(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        switch (this.l) {
            case 1:
                return ((C2053e) this.f10598m).isEmpty();
            default:
                return super.isEmpty();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                n[] nVarArr = new n[8];
                for (int i7 = 0; i7 < 8; i7++) {
                    nVarArr[i7] = new o(2);
                }
                return new h((e) this.f10598m, nVarArr);
            default:
                C2053e c2053e = (C2053e) this.f10598m;
                c2053e.getClass();
                return new C2051c(c2053e, 2);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        switch (this.l) {
            case 1:
                C2053e c2053e = (C2053e) this.f10598m;
                c2053e.c();
                int iJ = c2053e.j(obj);
                if (iJ < 0) {
                    return false;
                }
                c2053e.o(iJ);
                return true;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        switch (this.l) {
            case 1:
                A7.m.f(collection, "elements");
                ((C2053e) this.f10598m).c();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection collection) {
        switch (this.l) {
            case 1:
                A7.m.f(collection, "elements");
                ((C2053e) this.f10598m).c();
                break;
        }
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        switch (this.l) {
            case 0:
                e eVar = (e) this.f10598m;
                eVar.getClass();
                return eVar.f10593q;
            default:
                return ((C2053e) this.f10598m).f20555t;
        }
    }

    public i(C2053e c2053e) {
        A7.m.f(c2053e, "backing");
        this.f10598m = c2053e;
    }
}
