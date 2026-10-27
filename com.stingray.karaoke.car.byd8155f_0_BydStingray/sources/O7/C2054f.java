package o7;

import A7.m;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import n7.AbstractC1992g;

/* JADX INFO: renamed from: o7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2054f extends AbstractC1992g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2053e f20560m;

    public C2054f(C2053e c2053e, int i7) {
        this.l = i7;
        switch (i7) {
            case 1:
                m.f(c2053e, "backing");
                this.f20560m = c2053e;
                break;
            default:
                m.f(c2053e, "backing");
                this.f20560m = c2053e;
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.l) {
            case 0:
                m.f((Map.Entry) obj, "element");
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        switch (this.l) {
            case 0:
                m.f(collection, "elements");
                throw new UnsupportedOperationException();
            default:
                m.f(collection, "elements");
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.l) {
            case 0:
                this.f20560m.clear();
                break;
            default:
                this.f20560m.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                m.f(entry, "element");
                return this.f20560m.g(entry);
            default:
                return this.f20560m.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.l) {
            case 0:
                m.f(collection, "elements");
                return this.f20560m.f(collection);
            default:
                return super.containsAll(collection);
        }
    }

    @Override // n7.AbstractC1992g
    public final int d() {
        switch (this.l) {
        }
        return this.f20560m.f20555t;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.l) {
        }
        return this.f20560m.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                C2053e c2053e = this.f20560m;
                c2053e.getClass();
                return new C2051c(c2053e, 0);
            default:
                C2053e c2053e2 = this.f20560m;
                c2053e2.getClass();
                return new C2051c(c2053e2, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.l) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    m.f(entry, "element");
                    C2053e c2053e = this.f20560m;
                    c2053e.getClass();
                    c2053e.c();
                    int i7 = c2053e.i(entry.getKey());
                    if (i7 >= 0) {
                        Object[] objArr = c2053e.f20549m;
                        m.c(objArr);
                        if (m.a(objArr[i7], entry.getValue())) {
                            c2053e.o(i7);
                            break;
                        }
                    }
                }
                break;
            default:
                C2053e c2053e2 = this.f20560m;
                c2053e2.c();
                int i10 = c2053e2.i(obj);
                if (i10 >= 0) {
                    c2053e2.o(i10);
                    break;
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        switch (this.l) {
            case 0:
                m.f(collection, "elements");
                this.f20560m.c();
                break;
            default:
                m.f(collection, "elements");
                this.f20560m.c();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        switch (this.l) {
            case 0:
                m.f(collection, "elements");
                this.f20560m.c();
                break;
            default:
                m.f(collection, "elements");
                this.f20560m.c();
                break;
        }
        return super.retainAll(collection);
    }
}
