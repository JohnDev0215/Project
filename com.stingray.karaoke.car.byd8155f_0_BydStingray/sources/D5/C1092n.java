package d5;

import a.AbstractC0904a;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: d5.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1092n extends AbstractCollection {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f13444m;

    public /* synthetic */ C1092n(int i7, Serializable serializable) {
        this.l = i7;
        this.f13444m = serializable;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        switch (this.l) {
            case 0:
                ((Y) this.f13444m).b();
                break;
            case 1:
                ((C1098u) this.f13444m).clear();
                break;
            default:
                ((C1082d) this.f13444m).clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                Iterator it = ((C1092n) ((Y) this.f13444m).a().values()).iterator();
                while (it.hasNext()) {
                    if (((Collection) it.next()).contains(obj)) {
                        return true;
                    }
                }
                return false;
            case 1:
            default:
                return super.contains(obj);
            case 2:
                return ((C1082d) this.f13444m).containsValue(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        switch (this.l) {
            case 2:
                return ((C1082d) this.f13444m).isEmpty();
            default:
                return super.isEmpty();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                return new C1079a((Y) this.f13444m);
            case 1:
                C1098u c1098u = (C1098u) this.f13444m;
                Map mapC = c1098u.c();
                return mapC != null ? mapC.values().iterator() : new r(c1098u, 2);
            default:
                return new U(((C1082d) this.f13444m).entrySet().iterator());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        switch (this.l) {
            case 2:
                try {
                    return super.remove(obj);
                } catch (UnsupportedOperationException unused) {
                    C1082d c1082d = (C1082d) this.f13444m;
                    for (Map.Entry entry : c1082d.entrySet()) {
                        if (AbstractC0904a.A(obj, entry.getValue())) {
                            c1082d.remove(entry.getKey());
                            return true;
                        }
                    }
                    return false;
                }
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        switch (this.l) {
            case 2:
                try {
                    collection.getClass();
                    return super.removeAll(collection);
                } catch (UnsupportedOperationException unused) {
                    HashSet hashSet = new HashSet();
                    C1082d c1082d = (C1082d) this.f13444m;
                    for (Map.Entry entry : c1082d.entrySet()) {
                        if (collection.contains(entry.getValue())) {
                            hashSet.add(entry.getKey());
                        }
                    }
                    return c1082d.keySet().removeAll(hashSet);
                }
            default:
                return super.removeAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection collection) {
        switch (this.l) {
            case 2:
                try {
                    collection.getClass();
                    return super.retainAll(collection);
                } catch (UnsupportedOperationException unused) {
                    HashSet hashSet = new HashSet();
                    C1082d c1082d = (C1082d) this.f13444m;
                    for (Map.Entry entry : c1082d.entrySet()) {
                        if (collection.contains(entry.getValue())) {
                            hashSet.add(entry.getKey());
                        }
                    }
                    return c1082d.keySet().retainAll(hashSet);
                }
            default:
                return super.retainAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        switch (this.l) {
            case 0:
                return ((Y) this.f13444m).f13401p;
            case 1:
                return ((C1098u) this.f13444m).size();
            default:
                return ((C1082d) this.f13444m).f13414n.size();
        }
    }

    public C1092n(C1082d c1082d) {
        this.l = 2;
        this.f13444m = c1082d;
    }
}
